package starships.systems;
import util.*;
import starships.Starship;
import starships.Enterprise;

/**
 * Controls a ship's phaser system. Automatic targeting.
 * 
 * REQUIRES SUBSYSTEM: LibraryComputer
 * 
 * @author Mason Stevenson 
 * @version 0.2
 */
public class PhaserControl extends Subsystem
{
    public static final String kKey = "pha";
    public static final double kDamAdd = 2.0;
    public static final double kDamPercent = .15;
    private Starship ship;
    
    /**
     * Constructor for the phaser control module.
     * @param newShip a reference to the ship containing this module.
     */
    public PhaserControl(Starship newShip)
    {
        super("Phaser Control");
        ship = newShip;
    }
    
    /**
     * If there are any ememies in the quadrant, 
     * targets each one and fires a phaser.
     * 
     * @param commands the commands for this module (1).
     * @return returns the log
     */
    public String launch(String[] commands)
    {
        int iEnergy;
        int damageTemp, damage;
        int[][] enemyData;
        String sTemp;
        LibraryComputer com;
        
        //if system non-operational
        if (isDamaged())
        {
            println("Phasers Inoperative");
            return getLog();
        }

        //if no enemies in the quadrant
        if (ship.getQuadrant().getEnemyNum() <= 0)
        {
            println("Science Officer Spock reports:");
            println("  'Sensors show no enemy ships in this quadrant'");
            return getLog();
        }

        //if the ship's computer is down
        if (ship.getSystems().get(LibraryComputer.kKey).isDamaged())
        {
            println("Computer failure hampers accuracy.");
        }
        
        //check command length
        if (commands.length < 2)
        {
            return getLog();
        }

        try
        {
            iEnergy = Integer.parseInt(commands[1]);
        }
        catch (NumberFormatException ex)
        {
            println("Invalid integer.");
            iEnergy = 0;
        }
        
        //check for invalid input
        if (iEnergy <= 0)
        {
            return getLog();
        }

        //make sure the ship has enough energy to complete the action
        if (ship.getEnergy() - iEnergy < 0)
        {
            println("Not enough energy available.");
            return getLog();
        }

        ship.setEnergy(ship.getEnergy() - iEnergy);

        //damage gets reduced if the ship's computer is damaged
        if (ship.getSystems().get(LibraryComputer.kKey).isDamaged())
        {
            iEnergy = (int) (iEnergy * SpaceMath.rnd());
        }

        damageTemp = iEnergy / ship.getQuadrant().getEnemyNum();
        com = (LibraryComputer) ship.getSystems().get(LibraryComputer.kKey); 
        enemyData = ship.getQuadrant().getEnemyData();
        
        //if enemies remain after targeting
        if (targetEnemies(enemyData, damageTemp, com))
        {
            print(ship.getQuadrant().enemiesShoot(ship));
        }
        
        return getLog();
    }
    
    private boolean targetEnemies(int[][] enemyData, int damageTemp, 
        LibraryComputer com)
    {
        int damage;
        
        //loop through enemies
        for (int count = 1; count <= Constants.kMaxEnemyQuad; count++)
        {
            // if alive
            if (enemyData[count][Constants.kEnemyShieldIndex] >= 0)  
            {
                damage = (int) (damageTemp / calculateDamage(
                    enemyData, 0, ship.getSectorRow(), 
                    ship.getSectorCol()) * (SpaceMath.rnd() + kDamAdd));
                
                //check for damage
                if (damage <= kDamPercent * 
                    enemyData[count][Constants.kEnemyShieldIndex])
                {
                    print("Sensors show no damage to enemy at ");
                    println(enemyData[count][1] + ", " + 
                        enemyData[count][Constants.kEnemyColIndex]);
                }
                
                //if enemy damaged
                else
                {
                    enemyData[count][Constants.kEnemyShieldIndex] = 
                        enemyData[count][Constants.kEnemyShieldIndex] - damage;
                    print(damage + " unit hit on Klingon at sector ");
                    println(enemyData[count][1] + ", " 
                        + enemyData[count][Constants.kEnemyColIndex]);
                    
                    // See if negative energy; 
                    //should not be dead just because no shields
                    if (enemyData[count][Constants.kEnemyShieldIndex] < 0) 
                    {
                        println("*** Klingon Destroyed ***");
                        ship.getQuadrant().enemyDestroyed(enemyData[count][1], 
                            enemyData[count][Constants.kEnemyColIndex]);
                        
                        com.addToRecord(ship.getQuadrantRow(), 
                            ship.getQuadrantCol(), 
                            com.getRecord(ship.getQuadrantRow(), 
                            ship.getQuadrantCol()) - Constants.kHundredsPlace);
                            
                        //all enemies dead
                        if (ship.getGalaxy().getTotalEnemies() <= 0)
                        {
                            //won game
                            //return getLog();
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    private int calculateDamage(int[][] enemyData, int row, double targetRow, 
        double targetCol)
    {
        int dam;
        double temp1 = enemyData[row][1] - targetRow;
        double temp2 = enemyData[row][Constants.kEnemyColIndex] - targetCol;
        
        dam = (int) Math.sqrt(temp1 * temp1 + temp2 * temp2);

        return dam;
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
