package starships.systems;
import util.*;
import starships.Starship;
import starships.Enterprise;

/**
 * DamageControl module for starship.
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class DamageControl extends Subsystem
{
    private Starship ship;
    public static final String kKey = "dam";
    public static final int kNumSpaces = 25;
    public static final double kMaxRepair = 0.9;
    public static final double kTimeInc = 0.1;
    
    /**
     * Constructor for Damage Control module.
     * 
     * @param newShip a reference to the ship containing this module.
     */
    public DamageControl(Starship newShip)
    {
        super("Damage Control");
        ship = newShip;
    }
    
    /**
     * Activates damage control.
     * @param commands the command list.
     * @return returns the log.
     */
    public String launch(String[] commands)
    {
        String response;
        double temp = 0.0; //d3
        Subsystem sys;
        
        // If we're not docked there's no repairmen around
        if (!ship.isDocked())
        {
            return getLog();
        }

        // compute total repair time needed, one-tenth day per device    
        temp = 0.0;
        
        //loop through this ship's keys
        for (String key : ship.getKeys())
        {
            sys = ship.getSystems().get(key);
            
            //check to see if the system is damaged
            if (sys.isDamaged())
            {
                temp += kTimeInc;
            }
        }
        
        // if no repairs needed, return
        if (temp == 0.0)
        {
            return getLog();
        }
            
        // add a random factor for repair duration
        temp += ship.getQuadrant().getRepairDuration();
        
        // Set upper limit for repair time
        if (temp >= 1.0)
        {
            temp = kMaxRepair;
        }
        
        //loop through ship systems
        for (String key : ship.getKeys())
        {
            sys = ship.getSystems().get(key);
            
            //check for system damage
            if (sys.isDamaged())
            {
                sys.setDamage(0.0);
            }
        }
            
        ship.getGalaxy().getTime().increment(temp + kTimeInc);
        print("Repairs completed, ");
        print(String.format("%6.4f", temp));
        println(" stardates elapsed.");
        
        return getLog();
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
