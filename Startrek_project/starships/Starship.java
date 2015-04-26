package starships;
import java.util.*;
import java.util.Map.Entry;

import util.*;
import universe.*;
import starships.systems.Subsystem;

/**
 * Base class of a starship
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public abstract class Starship
{
    public static final double kDamageCheck1 = 0.2;
    public static final double kDamageCheck2 = .6;
    public static final double kDamageMult = 5.0;
    public static final double kRepairMult = 3.0;
    public static final int kRepairDiv = 10;
    public static final double kDamageOffset = -0.1;
    
    private String[] designation = {"CLASS", "NAME"};
    private String[] keys = {"nav", "srs", "lrs", "pha", "tor", "dam", "shi", "com"};
    private boolean docked; // Docked flag (d0)
    private boolean destroyed; 
    private int damaged; // Damage Repair Flag (d1)
    private int energy; // Current Energy (e)
    private int energyStart; // Starting Energy (e0)
    private int shields; // Current shield value (s)
    private double sectorRow;
    private double sectorCol;
    private int quadrantRow;
    private int quadrantCol;
    private int prevQuadrantRow;
    private int prevQuadrantCol;
    private double repairDuration;  /* Used for computing damage repair time */
    private HashMap<String, Subsystem> systems;
    private Galaxy galaxy;
    
    private String alertLevel = "R";
    
    /**
     * Constructor for Starship. 
     * 
     * @param newDes a new designation for the starship. Use format
     * {"SHIPCLASS", "SHIPNAME"} e.g. {"Starfighter", "X-wing"}
     */
    public Starship(String[] newDes)
    {
        systems = new HashMap<String, Subsystem>();
        designation = newDes;
        quadrantRow = SpaceMath.functionR();
        quadrantCol = SpaceMath.functionR();
        storeQuadrant();
        sectorRow = SpaceMath.functionR();
        sectorCol = SpaceMath.functionR();
        destroyed = false;
    }
    
    /**
     * Returns the alert level for the StartrekGUI.
     * @return returns the alert level.
     */
    public String getAlertLevel()
    {
        return alertLevel;
    }
    
    /**
     * Sets the alert level.
     * @param newAlert a new alert level.
     */
    public void setAlertLevel(String newAlert)
    {
        alertLevel = newAlert;
    }
    
    /**
     * Accessor for keys.
     * @return returns keys.
     */
    public String[] getKeys()
    {
        return keys;
    }
    
    /**
     * Checks to see if the ship is destroyed.
     * @return Returns true if this ship is destroyed.
     */
    public boolean isDestroyed()
    {
        return destroyed;
    }
    
    /**
     * Toggles the destroyed state.
     * @param newDes The destroyed state.
     */
    public void setDestroyed(boolean newDes)
    {
        destroyed = newDes;
    }
    
    /**
     * Accessor for designation.
     * @return Returns designation.
     */
    public String[] getDesignation()
    {
        return designation;
    }
    
    /**
     * Accessor for sectorRow.
     * @return Returns sectorRow.
     */
    public double getSectorRow()
    {
        return sectorRow;
    }
    
    /**
     * Accessor for sectorCol.
     * @return Returns sectorCol.
     */
    public double getSectorCol()
    {
        return sectorCol;
    }
    
    /**
     * Sets the current sector location.
     * @param row the current row index.
     * @param col the current collumn index.
     */
    public void setSector(double row, double col)
    {
        sectorRow = row;
        sectorCol = col;
    }
    
    /**
     * Accessor for quadrantRow.
     * @return Returns quadrantRow.
     */
    public int getQuadrantRow()
    {
        return quadrantRow;
    }
    
    /**
     * Accessor for quadrantCol.
     * @return Returns quadrantCol.
     */
    public int getQuadrantCol()
    {
        return quadrantCol;
    }
    
    /**
     * Accessor for prevQuadrantRow.
     * @return Returns prevQuadrantRow.
     */
    public int getPrevQuadrantRow()
    {
        return prevQuadrantRow;
    }
    
    /**
     * Accessor for prevQuadrantCol.
     * @return Returns prevQuadrantCol.
     */
    public int getPrevQuadrantCol()
    {
        return prevQuadrantCol;
    }
    
    /**
     * Sets the current quadrant location.
     * @param row the current row index.
     * @param col the current collumn index.
     */
    public void setQuadrant(int row, int col)
    {
        quadrantRow = row;
        quadrantCol = col;
    }
    
    /**
     * Saves the current location.
     */
    public void storeQuadrant()
    {
        prevQuadrantRow = quadrantRow;
        prevQuadrantCol = quadrantCol;
    }
    
    /**
     * Checks to see if the ship is docked.
     * @return Returns docked.
     */
    public boolean isDocked()
    {
        return docked;
    }
    
    /**
     * Toggles the docked state.
     * @param dock the docked state.
     */
    public void setDocked(boolean dock)
    {
        docked = dock;
    }
    
    /**
     * Accessor for damaged.
     * @return Returns damaged.
     */
    public int getDamaged()
    {
        return damaged;
    }
    
    /**
     * Accessor for energy.
     * @return Returns energy.
     */
    public int getEnergy()
    {
        return energy;
    }
    
    /**
     * Sets the current energy.
     * @param eng the current energy.
     */
    public void setEnergy(int eng)
    {
        energy = eng;
    }
    
    /**
     * Sets the starting energy.
     * @param eng the starting energy.
     */
    public void setEnergyStart(int eng)
    {
        energyStart = eng;
    }
    
    /**
     * Resets current energy to starting energy.
     */
    public void resetEnergy()
    {
        energy = energyStart;
    }
    
    /**
     * Accessor for energyStart.
     * @return Returns energyStart.
     */
    public int getEnergyStart()
    {
        return energyStart;
    }
    
    /**
     * Accessor for shields.
     * @return Returns shields.
     */
    public int getShields()
    {
        return shields;
    }
    
    /**
     * Sets the current shields.
     * @param shi the current shields.
     */
    public void setShields(int shi)
    {
        shields = shi;
    }
    
    /**
     * Resets the shields to the starting value.
     */
    public void resetShields()
    {
        shields = 0;
    }
    
    /**
     * Gets all this ship's systems.
     * @return returns a hashmap containing this ship's systems.
     * Calling getSystems().get(String key) will get a particular system.
     */
    public HashMap<String, Subsystem> getSystems()
    {
        return systems;
    }
    
    /**
     * Accessor for galaxy.
     * @return Returns galaxy.
     */
    public Galaxy getGalaxy()
    {
        return galaxy;
    }
    
    /**
     * Accessor for the current quadrant this ship is in.
     * @return Returns the current quadrant at quadrantRow, quadrantCol.
     */
    public Quadrant getQuadrant()
    {
        return galaxy.getQuadrant(quadrantRow, quadrantCol);
    }
    
    /**
     * Sets the current galaxy.
     * @param newGal A reference to the galaxy this ship resides in.
     */
    public void setGalaxy(Galaxy newGal)
    {
        galaxy = newGal;
    }
    
    /**
     * Prints ship info.
     */
    ///public abstract void shipInfo();
    
    /**
     * Handles damage calculations after ship movement.
     * @param warp the warp value of a move.
     * @return returns the log
     */
    public String repairDamage(double warp)
    {
        int index, temp;
        double repairFactor; 
        Subsystem system;
        String message  = "";
        
        repairFactor = warp;

        //if warping at a one-quadrant factor or more
        if (warp >= 1.0)
        {
            repairFactor = warp / kRepairDiv;
        }

        //loop through subsystems
        //for (Entry<String, Subsystem> entry : systems.entrySet())
        for(String key : keys)
        {
            ///system = entry.getValue();
            system = systems.get(key);
            
            //if subsystem is damaged
            if (system.isDamaged())
            {
                //repair it by repair factor
                system.setDamage(system.getDamage() + repairFactor);
                
                // not sure what is happening here
                if (system.getDamage() > kDamageOffset && system.getDamage() < 0)
                {
                    system.setDamage(kDamageOffset);
                }

                //if system not damaged
                else if (system.getDamage() >= 0.0)
                {
                    //if not damaged Does this even do anything?
                    if (damaged != 1)
                    {
                        damaged = 1;
                    }

                    ///System.out.println("Damage Control report:\n");
                    ///System.out.println("    " + system.getName() 
                    ///+" repair completed\n");
                    message += "Damage Control report:\n";
                    message += "    " + system.getName() +" repair completed\n";
                }
            }
        }
        
        message += inflictRandomDamage();
        return message;
    }
    
    private String inflictRandomDamage()
    {
        int temp;
        double temp2;
        Subsystem system;
        String message = "";
        
        
        temp2 = SpaceMath.rnd();
        //System.out.println("SYSTEM " + temp2 + "was chosen");
        
        // randomly inflict or repair damage
        if (temp2 <= kDamageCheck1)
        {
            temp = SpaceMath.functionR();
            system = systems.get(keys[temp - 1]);
            
            //check for inflict damage
            if (SpaceMath.rnd() < kDamageCheck2)
            {
                //set subsystem damage
                system.setDamage(system.getDamage() - 
                    (SpaceMath.rnd() * kDamageMult + 1.0));
                ///System.out.println("Damage Control report:\n");
                ///System.out.println("    "+ system.getName() + " damaged\n");
                message += "Damage Control report:\n";
                message += "    "+ system.getName() + " damaged\n";
            }
            
            //otherwise, repair damage
            else
            {
                //set subsystem damage
                system.setDamage(system.getDamage() + 
                    (SpaceMath.rnd() * kRepairMult + 1.0));
                ///System.out.println("Damage Control report:\n");
                ///System.out.println("    " + system.getName() 
                ///+ " state of repair improved\n");
                message += "Damage Control report:\n";
                message += "    " + system.getName() + " state of repair improved\n";
            }
        }
        
        return message;
    }
}
