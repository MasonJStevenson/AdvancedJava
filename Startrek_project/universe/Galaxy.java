package universe;
import util.*;
import starships.*;
import starships.systems.*;

/**
 * Class that contains galaxy data
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class Galaxy
{
    private static final int kStarbaseGen = 96;
    private static final int kSupernovaGen = 2;
    private static final int kThreeEnemy = 98;
    private static final int kTwoEnemy = 95;
    private static final int kOneEnemy = 80;
    private Quadrant[][] quadrants = 
        new Quadrant[Constants.kGalaxySize][Constants.kGalaxySize];
    private double repairDuration;
    
    private int totalEnemies;
    private int startingEnemies;
    private int totalStarbases;
    
    private Time time;
    
    /**
     * Constructor for galaxy.
     * @param ship The player ship in the galaxy.
     * @param newTime The stardate.
     * @param enemyCount An initial enemy count to build off of.
     * @param starbaseCount An initial starbase count to build off of.
     */
    public Galaxy(Starship ship, Time newTime, int enemyCount, 
        int starbaseCount)
    {
        totalEnemies = enemyCount;
        startingEnemies = totalEnemies;
        totalStarbases = starbaseCount;
        ship.setGalaxy(this);
        time = newTime;
        initialize(ship, time.getEnd());
    }
    
    /*
    public int[][] toArray()
    {
        int[][] gArray = new int[Constants.kGalaxySize][Constants.kGalaxySize];
        
        for (int row = 1; row < Constants.kGalaxySize; row++)
        {
            for (int col = 1; col < Constants.kGalaxySize; col++)
            {
                gArray[row][col] = quadrants[row][col].toInt();
            }
        }
        
        return gArray;
    }*/
    
    /**
     * Mutator method for time.
     * @param newTime The stardate.
     */
    public void setTime(Time newTime)
    {
        time = newTime;
    }
    
    /**
     * Accessor method for time.
     * @return returns time.
     */
    public Time getTime()
    {
        return time;
    }
    
     /**
     * Accessor method for a quadrant.
     * @param row the galaxy row
     * @param col the galaxy collumn.
     * @return Returns the quadrant located at row, col.
     */
    public Quadrant getQuadrant(int row, int col)
    {
        return quadrants[row][col];
    }
    
    /**
     * Accessor method for totalEnemies.
     * @return returns totalEnemies.
     */
    public int getTotalEnemies()
    {
        return totalEnemies;
    }
    
    /**
     * Accessor method for startingEnemies.
     * @return returns startingEnemies.
     */
    public int getStartingEnemies()
    {
        return startingEnemies;
    }
    
    /**
     * Removes one enemy from the total enemy count.
     */
    public void enemyDestroyed()
    {
        totalEnemies--;
    }
    
    /**
     * Accessor method for totalStarbases.
     * @return returns totalStarbases.
     */
    public int getTotalStarbases()
    {
        return totalStarbases;
    }
    
    /**
     * Removes one starbase from the total base count.
     */
    public void baseDestroyed()
    {
        totalStarbases--;
    }
    
    /**
     * Generates all quadrant data. (number of enemies per quadrant, 
     * number of bases per quadrant, and number of stars per quadrant)
     * 
     * @param ship The player ship.
     * @param timeEnd Number of years the player has to complete the mission.
     */
    public void initialize(Starship ship, int timeEnd)
    {
        int enemyNum;
        int starbases;
        int randomBase;
        boolean supernova = false;
        
        /* Setup What Exists in Galaxy */
        
        //loop through rows
        for (int row = 1; row <= Constants.kGalaxy; row++)
        {
            //loop through collumns
            for (int col = 1; col <= Constants.kGalaxy; col++)
            {
                //get enemies
                enemyNum = getEnemyNum(
                    SpaceMath.getRand(Constants.kHundredsPlace));
                totalEnemies += enemyNum;
                //message += ("ADDED ENEMY(S) "  + enemyNum);
                
                starbases = 0;
                randomBase = SpaceMath.getRand(Constants.kHundredsPlace);
                //get starbases
                if (randomBase > kStarbaseGen)
                {
                    starbases = 1;
                    //message += ("ADDED A STARBASE");
                }
                totalStarbases += starbases;
                
                //check for supernova quadrant
                if (randomBase < kSupernovaGen)
                {
                    supernova = true;
                    //System.out.println("SUPERNOVA in " + row + " " + col);
                }

                quadrants[row][col] = new Quadrant(enemyNum, starbases, 
                    SpaceMath.functionR(), this, supernova);
                quadrants[row][col].genName(row, col);
                
                supernova = false;
            }
        }
        

        //check if more enemies than years
        if (totalEnemies > time.getEnd())
        {
            time.setEnd(totalEnemies + 1);
            //message += ("Special METHOD CALL");
        }
   
        checkForBases(ship);

        startingEnemies = totalEnemies;
    }
    
    /**
     * Prints out a game intro for this galaxy.
     * @return returns the intro message.
     */
    public String printIntro()
    {
        String tempOne = "";
        String tempTwo = "is";
        String message = "";
        
        //change wording
        if (totalStarbases != 1)
        {
            tempOne = "s";
            tempTwo = "are";
        }

        message += ("Your orders are as follows:\n");
        message += ("   Destroy the " + totalEnemies 
            + " Klingon warships which have invaded\n");
        message += 
            ("the galaxy before they can attack Federation Headquarters\n");
        message += ("on stardate " + (time.getStart() + time.getEnd()) 
            + ". This gives you " + time.getEnd() + " days. There " 
            + tempTwo + "\n");
        message += (" " + totalStarbases + " starbase" + tempOne 
            + " in the galaxy for resupplying your ship.\n");
        
        return message;
    }
    
    private void checkForBases(Starship ship)
    {
        Quadrant shipCurrent;
        
        //no starbases
        if (totalStarbases == 0)
        {
            shipCurrent = 
                quadrants[ship.getQuadrantRow()][ship.getQuadrantCol()];
            
            //if less than 2 enemies in ship's quadrant
            if (shipCurrent.getEnemyNum() < 2)
            {
                shipCurrent.setEnemyNum(shipCurrent.getEnemyNum() + 1);
                totalEnemies++;
            }
            
            shipCurrent.setBaseNum(1);
            totalStarbases++;
            
            //Reposition ship
            ship.setQuadrant(SpaceMath.functionR(), SpaceMath.functionR());
            
        }
    }
    
    /**
     * 20% total chance of (1-3) enemies spawning.
     * @param randNum a number 1-100
     * @return Returns the number of enemies 0-3
     */
    private int getEnemyNum(int randNum)
    {
        int enemyNum = 0;
        
        //2% chance of 3 enemies
        if (randNum > kThreeEnemy)
        {
            enemyNum = Constants.kMaxEnemyQuad;
        }
        
        //3% chance of 2 enemies
        else if (randNum > kTwoEnemy)
        {
            enemyNum = 2;
        }
        
        //15% chance of 1 enemy
        else if (randNum > kOneEnemy)
        {
            enemyNum = 1;
        }
        
        return enemyNum;
    }
}
