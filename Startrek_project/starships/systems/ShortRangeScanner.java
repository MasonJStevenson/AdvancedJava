package starships.systems;

import universe.*;
import util.*;
import starships.Starship;
import starships.Enterprise;

/**
 * Scans an (8 Sector) X (8 Sector) area of space located
 * at a ship's current quadrant.
 * 
 * NOTE: Currently compatable with Starship and Enterprise.
 * 
 * @author Mason Steveson 
 * @version 0.2
 */
public class ShortRangeScanner extends Subsystem
{   
    private Starship ship;
    public static final String kKey = "srs";
    public static final int kOne = 1;
    public static final int kTwo = 2;
    public static final int kThree = 3;
    public static final int kFour = 4;
    public static final int kFive = 5;
    public static final int kSix = 6;
    public static final int kSeven = 7;
    public static final int kEight = 8;
    
    /**
     * Constructor for ShortRangeScanner
     * 
     * @param newShip a reference to this module's ship.
     */
    public ShortRangeScanner(Starship newShip)
    {
        super("Short Range Sensors");
        ship = newShip;
    }
    
    /**
     * Scans an (8 Sector) X (8 Sector) area of space.
     * prints out some ship data.
     * 
     * @param commands a list of commands for this module.
     * @return returns the log.
     */
    public String launch(String[] commands)
    {
        String condition;
        
        Quadrant curQuad = ship.getQuadrant();
        
        condition = "GREEN";
        ship.setAlertLevel("G");

        //if low energy
        if (ship.getEnergy() < ship.getEnergyStart() * Constants.kTenthsPlace)
        {
            condition = "YELLOW";
            ship.setAlertLevel("Y");
        }

        //enemy in quadrent
        if (curQuad.getEnemyNum() > 0)
        {
            condition = "*RED*";
            ship.setAlertLevel("R");
        }
        
        
        ship.setDocked(false); // indicate not docked
        
        //check to see if the ship is docked
        if (checkForDock())
        {
            condition = "DOCKED";
            ship.setAlertLevel("D");
        }
        
        //if this module is damaged, abort the launch()
        if (isDamaged())
        {
            print("*** Short Range Sensors are out ***\n");
            return getLog();
        }

        /*
        print("  -1--2--3--4--5--6--7--8-\n");
        
        //loop through rows
        for (int row = 1; row <= 8; row++)
        {
            print(String.format("%d ",row));
            
            //loop through collumns
            for (int col = 1; col <= 8; col++)
            {
                print(curQuad.getSector(row, col).toString()); 
            }

            printInfo(row, condition);
        }
        println("  ------------------------\n");
        */
       
        return getLog();
    }
    
    /*
    private void printInfo(int row, String condition)
    {
        //row 1
        if (row == kOne)
        {
                print(String.format("    Stardate            %d\n", 
                    (int) ship.getGalaxy().getTime().getCurrent()));
        }
        
        //row 2
        else if (row == kTwo)
        {
            print(String.format("    Condition           %s\n", condition));
        }
        
        //row 3
        else if (row == kThree)
        {
            print(String.format("    Quadrant            %d, %d\n", 
                ship.getQuadrantRow(), ship.getQuadrantCol()));
        }
        
        //row 4
        else if (row == kFour)
        {
            print(String.format("    Sector              %d, %d\n", 
                SpaceMath.cint(ship.getSectorRow()),
                SpaceMath.cint(ship.getSectorCol())));
        }
        
        //row 5 Note: will only print out if the ship has the proper 
        //designation. This is to avoid a runtime casting exception
        else if (row == kFive)
        {
            //If the ship is a constitution-class starship
            if (ship.getDesignation()[0].equals("Constitution"))
            {
                print(String.format("    Photon Torpedoes    %d\n", 
                    ((Enterprise)ship).getTorpedos()));
            }
        }
        
        //row 6
        else if (row == kSix)
        {
            print(String.format("    Total Energy        %d\n", 
                ship.getEnergy() + ship.getShields()));
        }
        
        //row 7
        else if (row == kSeven)
        {
            print(String.format("    Shields             %d\n", 
                ship.getShields()));
        }
        
        //row 8
        else if (row == kEight)
        {
            print(String.format("    Klingons Remaining  %d\n", 
                ship.getGalaxy().getTotalEnemies()));
        }
    }*/
    
    private boolean checkForDock()
    {
        int shipRow = (int) ship.getSectorRow();
        int shipCol = (int) ship.getSectorCol();
        
        // look in all adjacent cells to our current location for a starbase
        //loop through rows
        for (int row = shipRow - 1; row <= shipRow + 1; row++)
        {
            //loop through collumns
            for (int col = shipCol - 1; col <= shipCol + 1; col++)
            {
                //if inside bounds
                if (row >= 1 && row <= Constants.kQuadrant && col >= 1 && 
                    col <= Constants.kQuadrant)
                {
                    //if starbase found. Double if-statement Unnecessary??
                    if (ship.getQuadrant().getSector(row, col)
                        .toString().equals(">!<"))
                    {
                        
                        ship.setDocked(true);
                        ship.resetEnergy();
                        
                        //if Constitution class starship
                        if (ship.getDesignation()[0].equals("Constitution"))
                        {
                            ((Enterprise)ship).resetTorpedos();
                        }
                        
                        print(
                            "Shields dropped for docking purposes.\n");
                        ship.resetShields();
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Checks to see if this system is operable.
     * @return returns true if this system is not operational.
     * (damage value less than 0.0)
     */
    public boolean isDamaged()
    {
        return getDamage() < 0.0;
    }
}
