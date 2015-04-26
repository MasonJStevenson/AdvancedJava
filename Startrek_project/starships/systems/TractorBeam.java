package starships.systems;


/**
 * Tractor beam module for enterprise.
 * 
 * @author Mason Stevenson
 * @version 0.1
 */
public class TractorBeam extends Subsystem
{
    private boolean engaged;
    public static final int kEnergyReq = 25;
    public static final String kKey = "bea";
    
    /**
     * Constructor for tractor beam module.
     */
    public TractorBeam()
    {
        super("Tractor beam");
        engaged = false;
    }
    
    /**
     * Launches this module.
     * @param commands a list of commands for this module.
     * @return returns the log.
     */
    public String launch(String[] commands)
    {
        //check for on command
        if (commands.length > 1 && commands[1].toLowerCase().equals("on"))
        {
            engaged = true;
            println("Tractor beam on.");
        }
        
        //turn the beam off
        else
        {
            engaged = false;
            println("Tractor beam off.");
        }
        
        return getLog();
    }
    
    /**
     * Checks to see if this modules is engaged.
     * 
     * @return returns true if this module is engaged
     */
    public boolean isEngaged()
    {
        return engaged;
    }
    
    /**
     * Disengages beam.
     */
    public void disengage()
    {
        engaged = false;
    }
    
    /**
     * Checks to see if this module is damaged.
     * @return returns true if this module is damaged.
     */
    public boolean isDamaged()
    {
        return getDamage() < 0.0;
    }
}
