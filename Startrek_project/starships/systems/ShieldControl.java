package starships.systems;
import util.*;
import starships.Starship;
import starships.Enterprise;

/**
 * Shield control module for a starship.
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class ShieldControl extends Subsystem
{
    private Starship ship;
    public static final String kKey = "shi";
    public static final int kCommandLen = 2; //ex: shi 500
    
    /**
     * Constructor for this module.
     * 
     * @param newShip a reference to the ship containing this module.
     */
    public ShieldControl(Starship newShip)
    {
        super("Shield Control");
        ship = newShip;
    }
    
    /**
     * Launches the module.
     * 
     * @param commands a list of commands for this module.
     * @return Returns the log.
     */
    public String launch(String[] commands)
    {
        int input;
        
        //check for damage
        if (isDamaged())
        {
            print("Shield Control inoperable\n");
            return getLog();
        }
        
        //check command length
        if (commands.length < kCommandLen)
        {
            return getLog();
        }

        try
        {
            input = Integer.parseInt(commands[1]);
        }
        catch (NumberFormatException ex)
        {
            println("Invalid integer.");
            input = -1;
        }

        //check input
        if (input < 0 || ship.getShields() == input)
        {
            print("<Shields Unchanged>\n");
            return getLog();
        }

        //check for invalid inptu
        if (input >= ship.getEnergy() + ship.getShields())
        {
            print("Shield Control Reports:\n");
            print(
                "  'This is not the Federation Treasury.'\n");
            print("<Shields Unchanged>\n");
            return getLog();
        }
        
        ship.setEnergy(ship.getEnergy() + ship.getShields() - input);
        ship.setShields(input);

        print("Deflector Control Room report:\n");
        print("  'Shields now at " + 
            ship.getShields() + " units per your command.'\n");
            
        return getLog();
    }
    
    /**
     * Checks this module for damage.
     * @return returns true if this module has been damaged.
     */
    public boolean isDamaged()
    {
        return getDamage() < 0.0;
    }
}
