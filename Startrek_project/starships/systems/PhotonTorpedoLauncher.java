package starships.systems;
import util.*;
import starships.Starship;
import starships.Enterprise;
import universe.*;

/**
 * Photon torpedo launcher for an Enterprise type ship.
 * 
 * REQUIRES SYSTEM: LibraryComputer
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class PhotonTorpedoLauncher extends Subsystem
{
    private Starship baseShip;
    private Enterprise ship;
    public static final String kKey = "tor";
    private static final double kCourseLimit = 9.0;
    private static int kEnergyLost = 2;
    private static final int kCommandLen = 2; //ex: tor 3.5
    
    /**
     * Constructor for the photon torpedo launcher.
     * @param newShip a reference to the ship that contains this
     * system.
     */
    public PhotonTorpedoLauncher(Starship newShip)
    {
        super("Photon Tubes");
        
        baseShip = newShip;
        
        //check ship class
        if (!baseShip.getDesignation()[0].equals("Constitution"))
        {
            println(
                "***WARNING*** INCOMPATABLE SUBSYSTEM MODULE LOADED ON SHIP");
        }
        
        //correct ship type
        else
        {
            ship = (Enterprise)baseShip;
        }
    }
    
    /**
     * Given a direction supplied by the user, fires a torpedo in a line across 
     * a quadrant utill it hits an object or the edge of the quadrant.
     * 
     * @param commands A list of commands for this module.
     * @return returns the log.
     */
    public String launch(String[] commands)
    {
        ///int c2, c3, rowInt, colInt, x5;
        //String sTemp;
        double course;
        double rowTemp; //x1
        double colTemp; //y1
        double row; //x
        double col; //y
        int rowInt; //rowInt
        int colInt; //colInt
        int temp;
        int[][] directions = new int[][]{{ 0, 0, -1, -1, -1,  0,  1, 1, 1, 0, },
            { 1, 1,  1,  0, -1, -1, -1, 0, 1, 1 }};

        //check ship class
        if (!baseShip.getDesignation()[0].equals("Constitution"))
        {
            println(
                "***WARNING*** INCOMPATABLE SUBSYSTEM MODULE LOADED ON SHIP");
            return getLog();
        }
        
        //if no torpedos left
        if (ship.getTorpedos() <= 0)
        {
            println("All photon torpedoes expended");
            return getLog();
        }
        
        //check to see if this module is operation
        if (isDamaged())
        {
            println("Photon Tubes not operational");
            return getLog();
        }
        
        //check for invalid command length;
        if (commands.length < kCommandLen)
        {
            return getLog();
        }
        
        course = getCourse(commands);

        //course out of bounds
        if (course < 0 || course > kCourseLimit)
        {
            println("Ensign Chekov reports:");
            println("  Incorrect course data, sir!");
            return getLog();
        }

        ship.setEnergy(ship.getEnergy() - kEnergyLost);
        ship.fireTorpedo();

        temp = SpaceMath.cint(course);
        ///c3 = c2 + 1;

        rowTemp = directions[0][temp] + 
            (directions[0][temp + 1] - directions[0][temp]) * (course - temp);
        colTemp = directions[1][temp] + 
            (directions[1][temp + 1] - directions[1][temp]) * (course - temp);

        row = ship.getSectorRow() + rowTemp;
        col = ship.getSectorCol() + colTemp;

        rowInt = SpaceMath.cint(row);
        colInt = SpaceMath.cint(col);

        temp = 0;

        //move the torpedo
        if (moveTorpedo(rowInt, colInt, row, col, rowTemp, colTemp))
        {
            println("Torpedo Missed");
            
            //need to print the shoot info to the log
            print(ship.getQuadrant().enemiesShoot(ship));
        }
        
        return getLog();
    }
    
    private double getCourse(String[] commands)
    {   
        double course;
        
        try
        {
            course = Double.parseDouble(commands[1]);
        }
        catch (NumberFormatException ex)
        {
            course = -1.0;
        }

        //if course on bound limit
        if (course == kCourseLimit)
        {
            course = 1.0;
        }
        
        return course;
    }
    
    private boolean moveTorpedo(int rowInt, int colInt, double row, 
        double col, double rowTemp, double colTemp)
    {
        println("Torpedo Track:");

        //make a move
        while (rowInt >= 1 && rowInt < Constants.kQuadrantSize && 
            colInt >= 1 && colInt < Constants.kQuadrantSize)
        {
            print(String.format("    %d, %d\n", rowInt, colInt));

            //sector at rowInt, colInt is not empty
            if (!ship.getQuadrant().checkEmpty(rowInt, colInt))
            {
                //check to see if the torpedo hit
                if (torpedoHit(rowInt, colInt) && 
                    ship.getQuadrant().getEnemyNum() > 0)
                {
                    print(ship.getQuadrant().enemiesShoot(ship));
                }
                
                //return getLog();
                return false;
            }

            row += rowTemp;
            col += colTemp;

            rowInt = SpaceMath.cint(row);
            colInt = SpaceMath.cint(col);
        }
        
        return true;
    }
    
    private boolean torpedoHit(int row, int col)
    {
        ///int i, rowInt, colInt;
        int rowInt = SpaceMath.cint(row); //x3
        int colInt = SpaceMath.cint(col); //y3
        String objectInSector = ship.getQuadrant().getSector(rowInt, colInt).toString();
        boolean returnVal = true;
        LibraryComputer com = (LibraryComputer) ship.getSystems().get("com");
        int[][] enemyData = ship.getQuadrant().getEnemyData();

        //if sector at rowInt, colInt is a star
        if (objectInSector.equals(Star.kKey))
        {
            print("Star at " + rowInt +", " + colInt 
                + " absorbed torpedo energy.\n");
            returnVal = true;
        }

        //torpedo hit enemy ship
        else if (objectInSector.equals(Klingon.kKey))
        {
            print("*** Klingon Destroyed ***\n");
            ship.getQuadrant().enemyDestroyed(rowInt, colInt);

            //if no more enemies in galaxy, GAME WON!
            if (ship.getGalaxy().getTotalEnemies() <= 0)
            {
                //won game;
                returnVal = false;
            }

            destroyEnemy(enemyData, rowInt, colInt);
        }
        
        //topedo hit starbase
        else if (objectInSector.equals(Starbase.kKey))
        {
            print("*** Starbase Destroyed ***\n");
            ship.getQuadrant().baseDestroyed(rowInt, colInt);
            
            // Changed to: IF all the starbases are gone THEN
            if (ship.getGalaxy().getTotalStarbases() <= 0)
            {
                returnVal = false;
            }
            
            //if the player destroyed all the starbases
            else
            {
                print("Starfleet Command reviewing your record to consider\n");
                print("court martial!\n");
            }

            ship.setDocked(false);    // Undock 
        }
        
        com.addToRecord(
            ship.getQuadrantRow(), ship.getQuadrantCol(), 
            (ship.getQuadrant().getEnemyNum() * Constants.kHundredsPlace) + 
            (ship.getQuadrant().getBaseNum() * Constants.kTensPlace) + 
            ship.getQuadrant().getStarNum()); 
        return returnVal;
    }
    
    private void destroyEnemy(int[][] enemyData, int rowInt, int colInt)
    {
        //loop through enemies
        for (int count = 0; count <= Constants.kMaxEnemyQuad; count++)
        {
            //get the hit enemy
            if (rowInt == enemyData[count][1] 
                && colInt == enemyData[count][Constants.kEnemyColIndex])
            {
                // Negative energy indicates dead klingon
                enemyData[count][Constants.kEnemyShieldIndex] = -1;
            }
        }
    }

    /**
     * Checks to see if this system is operational.
     * @return Returns true is this system is not operational.
     */
    public boolean isDamaged()
    {
        return getDamage() < 0.0;
    }
}
