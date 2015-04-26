package starships.systems;
import util.*;
import universe.*;
import starships.Starship;
import starships.Enterprise;

/**
 * Scans an area of space.
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class LongRangeScanner extends Subsystem
{
    private Starship ship;
    public static final String kKey = "lrs";
    
    /**
     * Constructor for the Long Range Scanner.
     * @param newShip the ship containing this system.
     */
    public LongRangeScanner(Starship newShip)
    {
        super("Long Range Sensors");
        ship = newShip;
    }
    
    /**
     * Scans a (3 Quadrant)X(3 Quadrant) area of space located 
     * around the ship's current quadrant.
     * 
     * @param command the lrs command (does nothing).
     * @return returns the log
     */
    public String launch(String[] command)
    {
        Quadrant temp;
        
        //check to see if this system is operational
        if (isDamaged())
        {
            print(String.format("Long Range Sensors are inoperable.\n"));
            return getLog();
        }

        ///print(String.format("Long Range Scan for Quadrant %d, %d\n", 
            ///ship.getQuadrantRow(), ship.getQuadrantCol()));

        //loop through rows
        for (int row = ship.getQuadrantRow() - 1; 
            row <= ship.getQuadrantRow() + 1; row++)
        {
            ///print(String.format("--------------------\n:"));
            print(":");
            
            //loop thorugh collumns
            for (int col = ship.getQuadrantCol() - 1; 
                col <= ship.getQuadrantCol() + 1; col++)
            {
                //if inside galaxy bounds
                if (row > 0 && row < Constants.kGalaxySize && col > 0 
                    && col < Constants.kGalaxySize)
                {
                    temp = ship.getGalaxy().getQuadrant(row, col);
                    ((LibraryComputer)ship.getSystems().get("com"))
                        .addToRecord(row, col, temp.toInt());
                    
                    print(String.format(" %3s :", temp.toString()));
                }
                
                //out of bounds prints ***
                else
                {
                    print(String.format(" *** :"));
                }
            }
            
            print(String.format("\n"));
        }

        ///print(String.format("--------------------\n"));
        
        return getLog();
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
