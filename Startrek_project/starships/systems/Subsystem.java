package starships.systems;
import util.*;
import starships.Starship;
import starships.Enterprise;

/**
 * Generic class for a ship's system.
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public abstract class Subsystem
{
    ///private Starship ship;
    private String name;
    private double damage;
    private String log;
    
    /**
     * Constructor for Subsystem.
     * @param newName a name for this system
     */
    public Subsystem(String newName)
    {
        name = newName;
        log = "";
    }
    
    /**
     * Returns this system's name.
     * @return Returns this system's name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the log, flushing it in the process.
     * @return returns the current log.
     */
    public String getLog()
    {
        String temp = log;
        log = "";
        return temp;
    }
    
    /**
     * Gets the current damage value.
     * @return Returns the current damage value.
     */
    public double getDamage()
    {
        return damage;
    }
    
    /**
     * Sets the current damage value.
     * @param newDamage The current damage value.
     */
    public void setDamage(double newDamage)
    {
        damage = newDamage;
    }
    
    /**
     * Prints some text to the log.
     * @param text the text to print.
     */
    public void print(String text)
    {
        log += text;
        ///System.out.print(text); //for testing
    }
    
    /**
     * Prints some text to the log, adding a newline.
     * @param text the text to print.
     */
    public void println(String text)
    {
        log += (text + "\n");
        ///System.out.println(text); //for testing
    }
    
    /**
     * Prints a newline to the log.
     */
    public void println()
    {
        log += "\n";
        ///System.out.println(); //for testing
    }
    
    /**
     * Tells the system to perform its intended action.
     * @param commands a list of commands for user input.
     * @return returns the log.
     */
    public abstract String launch(String[] commands);
    
    /**
     * Checks this system to determine if it is operational.
     * @return returns true if this system is non-operational.
     */
    public abstract boolean isDamaged();
}
