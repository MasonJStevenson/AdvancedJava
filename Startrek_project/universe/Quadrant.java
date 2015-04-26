package universe;

import java.util.TreeSet;

import starships.*;
import starships.systems.*;
import util.*;

/**
 * Contains quadrant data for the space game.
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class Quadrant
{
    private static final int kDamageThreshold = 20;
    private static final double kDamPercent = 0.6;
    private static final double kShiPercent = 0.2;
    private static final double kHalf = 0.5;
    private static final double kThree = 3.0;
    private static final int kEnemyDataSize = 4;
    private static final int kLowShields = 200;
    private SectorObject[][] sectors = 
        new SectorObject[Constants.kQuadrantSize][Constants.kQuadrantSize];
    private int enemyNum;
    private int baseNum;
    private int starNum;
    private int baseRow; //location of base in quadrant (if any)
    private int baseCol; //location of base in quadrant (if any)
    private String name = "NO NAME";
    private double repairDuration;
    private Galaxy galaxy;
    private int[][] enemyData;
    private boolean supernova;
    
    /**
     * Constructor for Quadrant.
     * 
     * @param newEnemy The number of enemies in this quadrant.
     * @param newBase The number of bases in this quadrant.
     * @param newStar The number of stars in this quadrant.
     * @param newGal The Galaxy that contains this quadrant.
     * @param newSupernova Does this quadrant have a supernova?
     */
    public Quadrant(int newEnemy, int newBase, int newStar, 
        Galaxy newGal, boolean newSupernova)
    {
        enemyNum = newEnemy;
        baseNum = newBase;
        starNum = newStar;
        galaxy = newGal;
        enemyData = new int[kEnemyDataSize][kEnemyDataSize];
        supernova = newSupernova;
    }
    
    /**
     * HAPPENS WHEN A SHIP ENTERS THIS QUADRANT.
     * 
     * @param ship the ship in this quadrant
     * @return returns the log.
     */    
    public String generate(Starship ship)
    {
        String message = "";
        
        int shipQRow = ship.getQuadrantRow();
        int shipQCol = ship.getQuadrantCol();
       
        repairDuration = (double) SpaceMath.getRand(Constants.kHundredsPlace) 
            / Constants.kHundredsPlace / Constants.kFifty;
        
        //save quadrant in galactic record
        ((LibraryComputer)ship.getSystems().get("com")).addToRecord(
            ship.getQuadrantRow(), ship.getQuadrantCol(), 
            ship.getQuadrant().toInt());

        //if ship is in bounds
        if (shipQRow >= 1 && shipQRow <= Constants.kQuadrant 
            && shipQCol >= 1 && shipQCol <= Constants.kQuadrant)
        {
            //genName(shipQRow, shipQCol);

            // Are we at the initial stardate?
            if (galaxy.getTime().getCurrent() - 
                galaxy.getTime().getStart() > Constants.kHundredthsPlace)
            {
                ///System.out.printf("Now entering %s quadrant...\n", name);
                message += String.format("Now entering %s quadrant...\n", name);
            }
            
            //regular message
            else
            {
                ///System.out.printf(
                    ///"Your mission begins with your starship located\n");
                ///System.out.printf("in the galactic quadrant %s.\n", name);
                message += "Your mission begins with your starship located\n";
                message += String.format(
                    "in the galactic quadrant %s.\n", name);
            }
        }
        
        //check for supernova
        if (supernova)
        {
            message += "You entered a quadrant with a SUPERNOVA.\n";
            ship.setDestroyed(true);
            genSpace();
            genShip(SpaceMath.cint(ship.getSectorRow()),
                SpaceMath.cint(ship.getSectorCol()));
            return message;
        }

        //enemies present
        if (enemyNum > 0)
        {
            ///System.out.printf("Combat Area  Condition Red\n");
            message += "Combat Area  Condition Red\n";

            //warn the player
            if (ship.getShields() < kLowShields)
            {
                ///System.out.printf("Shields Dangerously Low\n");
                message += "Shields Dangerously Low\n";
            }
        }
        
        //loop through enemy data
        for (int count = 1; count <= Constants.kMaxEnemyQuad; count++)
        {
            enemyData[count][1] = 0;
            enemyData[count][2] = 0;
            enemyData[count][Constants.kEnemyShieldIndex] = -1;  // indicate not alive
        }

        //place space
        genSpace();
        

        // Position Enterprise, then Klingons, Starbases, and stars 
        //   in new quadrant
        
        genShip(SpaceMath.cint(ship.getSectorRow()),
            SpaceMath.cint(ship.getSectorCol()));

        genEnemies();
        genBases();
        genStars();
        
        return message;
    }
    
    /**
     * Generates this quadrant's name.
     * @param row The row index of this quadrant.
     * @param col The collumn index of this quadrant.
     */
    public void genName(int row, int col)
    {
        String[] quadName = {"", "Antares", "Rigel", "Procyon", "Vega", 
            "Canopus", "Altair", "Sagittarius", "Pollux", "Sirius", 
            "Deneb", "Capella", "Betelgeuse", "Aldebaran", "Regulus", 
            "Arcturus", "Spica"};

        String[] sectName = {"", " I", " II", " III", " IV"};
        
        String temp;
        
        //check for out of bounds
        if (row < 1 || row > Constants.kQuadrant || col < 1 
            || col > Constants.kQuadrant)
        {
            temp = "Unknown";
        }

        //name logic- each region has four quads
        if (col <= (Constants.kQuadrant / 2))
        {
            temp = quadName[row];
        }

        //name logic- each region has four quads
        else
        {
            temp = quadName[row + Constants.kQuadrant];
        }

        //name logic- each quad has a section suffix
        if (col != 1)
        {
            //name logic- each quad has a section suffix
            if (col > (Constants.kQuadrant / 2))
            {
                col = col - (Constants.kQuadrant / 2);
            }

            //BUG?
            temp.concat(sectName[col]);
            //strcat(sG2, sect_name[z5]);
        }
        
        name = temp;
    }
    
    /**
     * Moves an enemy. Then it shoots.
     * @param ship The player's ship.
     * @return returns the log.
     */
    public String enemiesMove(Starship ship)
    {
        int enemyRow;
        int enemyCol;

        //loop through enemies
        for (int count = 1; count <= Constants.kMaxEnemyQuad; count++)
        {
            //if still have energy
            if (enemyData[count][Constants.kEnemyShieldIndex] > 0)
            {
                enemyRow = enemyData[count][1];
                enemyCol = enemyData[count][2];
                sectors[enemyRow][enemyCol] = new EmptySpace();

                //find empty space
                do
                {
                    enemyRow = SpaceMath.functionR();
                    enemyCol = SpaceMath.functionR();
                } while(!checkEmpty(enemyRow, enemyCol));

                enemyData[count][1] = enemyRow;
                enemyData[count][2] = enemyCol;
                sectors[enemyRow][enemyCol] = new Klingon();
            }
        }

        return enemiesShoot(ship);
    }
    
    /**
     * Enemy shoots at player.
     * @param ship The player ship.
     * @return returns the log.
     */
    public String enemiesShoot(Starship ship)
    {
        String message = "";
        int damage;
        boolean continuePlaying = true;
        String[] keys;
        int systemNum;
        Subsystem targetSystem;
        
        //check this quadrant for enemies
        if (enemyNum <= 0)
        {
            return message;
        }

        //ship takes no damage if it is docked
        if (ship.isDocked())
        {
            //System.out.println("Starbase shields protect the Enterprise\n");
            message += "Starbase shields protect the Enterprise\n";
            return message;
        }
        
        // Check each potential attacker
        for (int count = 1; count <= Constants.kMaxEnemyQuad; count++)
        {
            // If we're killed by first klingon, we don't need to consider
            // the attacks by the others
            // only let them shoot if they have energy
            if (enemyData[count][Constants.kEnemyShieldIndex] > 0 
                && continuePlaying)
            {    
                damage = (int) ((enemyData[count][Constants.kEnemyShieldIndex] 
                    / calculateDamage(
                        count, ship.getSectorRow(), 
                        ship.getSectorCol())) * (2 + SpaceMath.rnd()));
                    
                ship.setShields(ship.getShields() - damage);
                enemyData[count][Constants.kEnemyShieldIndex] = (int) 
                    ((double)enemyData[count][Constants.kEnemyShieldIndex] / 
                    (kThree + SpaceMath.rnd()));
                    
                message += String.format(
                    "%d unit hit on Enterprise from sector ", damage);
                message += String.format(
                    "%d, %d\n", enemyData[count][1], enemyData[count][2]);

                //game over if ship loses its shields
                if (ship.getShields() <= 0)
                {
                    ///System.out.print("\n");
                    message += "\n";
                    ship.setDestroyed(true);
                }
                
                //otherwise keep going
                else
                {
                    message += String.format(
                        "    <Shields down to %d units>\n", ship.getShields());
    
                    //if damage greater than threshold, see if system gets damaged
                    //Double if? not necessary??
                    if (damage >= kDamageThreshold)
                    {
                        //see if system gets damaged
                        if (SpaceMath.rnd() <= kDamPercent || 
                            (damage / ship.getShields()) > kShiPercent)
                        {
                            systemNum = SpaceMath.functionR();
                            keys = ship.getKeys();
                            targetSystem = ship.getSystems().get(
                                keys[systemNum - 1]);
                            
                            targetSystem.setDamage(
                                targetSystem.getDamage() - 
                                (damage / ship.getShields()) - 
                                (kHalf * SpaceMath.rnd()));
    
                            ///System.out.print("Damage Control reports\n");
                            ///System.out.print(String.format(
                                ///"   '%s' damaged by hit\n", 
                                ///targetSystem.getName()));
                                
                            message += "Damage Control reports\n";
                            message += String.format(
                                "   '%s' damaged by hit\n", 
                                targetSystem.getName());
                        }
                    }
                }
            }
        }
        
        return message;
    }
    
    private int calculateDamage(int row, double targetRow, double targetCol)
    {
        int dam;

        dam = (int) Math.sqrt(Math.pow((enemyData[row][1] - targetRow), 2) 
            + Math.pow((enemyData[row][2] - targetCol), 2));

        return dam;
    }
    
    private void genSpace()
    {
        //loop through rows
        for (int row = 1; row <= Constants.kQuadrant; row++)
        { 
            //loop through collumns
            for (int col = 1; col <= Constants.kQuadrant; col++)
            {
                sectors[row][col] = new EmptySpace();
            }
        }
    }
    
    private void genShip(int shipRow, int shipCol)
    {
        sectors[shipRow][shipCol] = new PlayerShip();
    }
    
    private void genEnemies()
    {
        int row;
        int col;
        
        //check for enemies
        if (enemyNum > 0)
        {
            //loop through enemies
            for (int count = 1; count <= enemyNum; count++)
            {
                //get empty space
                do
                {
                    row = SpaceMath.functionR();
                    col = SpaceMath.functionR();
                } while(!checkEmpty(row, col));
                
                sectors[row][col] = new Klingon();

                // Gen Enemy Stats
                enemyData[count][1] = row;
                enemyData[count][2] = col;
                enemyData[count][Constants.kEnemyShieldIndex] = 
                    Constants.kHundredsPlace + SpaceMath.getRand(kLowShields);
            }
        }
    }
    
    private void genBases()
    {
        int row;
        int col;
        
        // position a starbase
        // Defect?  Should this be a loop inside a decision? 
        //          Like the previous segment? 
        // It seems to assume there's only one starbase per quadrant
        if (baseNum > 0)
        {
            //get empty space
            do
            {
                row = SpaceMath.functionR();
                col = SpaceMath.functionR();
            } while(!checkEmpty(row, col));

            sectors[row][col] = new Starbase();
            
            baseRow = row;
            baseCol = col;
        }
    }
    
    private void genStars()
    {
        int row;
        int col;
        
        // Notice the stars get repositioned every time you enter a quadrant.
        // A more realistic simulation would put the stars in the same place.
        for (int count = 1; count <= starNum; count++)
        {
            //get empty space
            do
            {
                row = SpaceMath.functionR();
                col = SpaceMath.functionR();
            } while(!checkEmpty(row, col));
            
            sectors[row][col] = new Star();
        }
    }
    
    /**
     * Checks this quadrant for a supernova.
     * @return returns true if this quadrant contains a supernova.
     */
    public boolean hasSupernova()
    {
        return supernova;
    }
    
    /**
     * Accessor for enemyData.
     * @return returns enemyData.
     */
    public int[][] getEnemyData()
    {
        return enemyData;
    }
    
    /**
     * Checks a space to see if it is empty.
     * @param row the row index.
     * @param col the collumn index. 
     * @return returns true if the space is empty.
     */
    public boolean checkEmpty(int row, int col)
    {
        return sectors[row][col].toString().equals("   ");
    }
    
    /**
     * Accessor for repairDuration.
     * @return returns repairDuration.
     */
    public double getRepairDuration()
    {
        return repairDuration;
    }
    
    /**
     * Accessor for baseRow.
     * @return returns baseRow.
     */
    public int getBaseRow()
    {
        return baseRow;
    }
    
    /**
     * Accessor for baseCol.
     * @return returns baseCol.
     */
    public int getBaseCol()
    {
        return baseCol;
    }
    
    /**
     * Accessor for enemyNum.
     * @return returns enemyNum.
     */
    public int getEnemyNum()
    {
        return enemyNum;
    }
    
    /**
     * Removes an enemy located at row, col from the quadrant.
     * Note that enemyData still contains information about the enemy
     * and may need to be wiped as well.
     * 
     * @param row The row index.
     * @param col The collumn index.
     */
    public void enemyDestroyed(int row, int col)
    {
        sectors[row][col] = new EmptySpace();
        enemyNum--;
        galaxy.enemyDestroyed();
    }
    
    /**
     * Accessor for baseNum.
     * @return returns baseNum.
     */
    public int getBaseNum()
    {
        return baseNum;
    }
    
    /**
     * Removes an starbase located at row, col from the quadrant.
     * 
     * @param row The row index.
     * @param col The collumn index.
     */
    public void baseDestroyed(int row, int col)
    {
        sectors[row][col] = new EmptySpace();
        baseNum--;
        galaxy.baseDestroyed();
    }
    
    /**
     * Accessor for starNum.
     * @return returns starNum.
     */
    public int getStarNum()
    {
        return starNum;
    }
    
    /**
     * Accessor for name.
     * @return returns name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessor for sectors.
     * @return returns sectors.
     */
    public SectorObject[][] getSectors()
    {
        return sectors;
    }
    
    /**
     * Accessor for a single sector.
     * @param row The row index.
     * @param col The collumn index.
     * @return returns returns sector at [row][col].
     */
    public SectorObject getSector(int row, int col)
    {
        return sectors[row][col];
    }
    
    /**
     * Mutator method for enemyNum.
     * @param newNum the number of enemies for this qudrant.
     */
    public void setEnemyNum(int newNum)
    {
        enemyNum = newNum;
    }
    
    /**
     * Mutator method for baseNum.
     * @param newNum the number of starbases for this qudrant.
     */
    public void setBaseNum(int newNum)
    {
        baseNum = newNum;
    }
    
    /**
     * Mutator method for starNum.
     * @param newNum the number of stars for this qudrant.
     */
    public void setStarNum(int newNum)
    {
        starNum = newNum;
    }
    
    /**
     * Formats quadrant data into xyz format, where x is the
     * number of enemies, y is the number of starbases, and 
     * z is the number of stars. e.g. a base with format
     * 205 has 2 enemy ships, 0 starbases, and 5 stars.
     * 
     * @return Returns an int of format xyz.
     */
    public int toInt()
    {
        int stars;
        
        return enemyNum * Constants.kHundredsPlace + 
            baseNum * Constants.kTensPlace + starNum;
    }
    
    /**
     * Same as toInt() just in string form.
     * Takes supernovas into account.
     * 
     * @return returns the string representation of this quadrant.
     */
    public String toString()
    {
        //check supernova
        if (supernova)
        {
            //check for enemies and bases
            if (enemyNum == 0 && baseNum == 0)
            {
                return "*";
            }
            
            //enemies and bases
            else
            {
                return enemyNum * Constants.kTensPlace + 
                    baseNum + "*";
            }
        }
        
        //regular
        else
        {
            return enemyNum * Constants.kHundredsPlace + 
                baseNum * Constants.kTensPlace + starNum
                + "";
        }
    }
}
