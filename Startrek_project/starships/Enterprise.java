package starships;
import java.util.*;

import util.*;
import starships.systems.*;
/**
 * 
 * More specific instance of Starship
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class Enterprise extends Starship
{
    public static final int kStartingEnergy = 3000;
    public static final int kTorpedoCapacity = 10;
    
    private int seed;
    private int torpedos;
    private int torpedoCapacity;
    
    /**
     * Constructor for the enterprise class.
     * 
     * @param newSeed the seed for the game. Used by the shipInfo 
     * method.
     */
    public Enterprise(int newSeed)
    {
        super(new String[] {"Constitution", "USS Enterprise"});
        seed = newSeed;
        
        setEnergyStart(kStartingEnergy);
        setEnergy(kStartingEnergy);
        setShields(0);
        torpedoCapacity = kTorpedoCapacity;
        torpedos = torpedoCapacity;
        
        initSubsystems(); 
    }
    
    /**
     * Accessor for torpedos.
     * @return returns torpedos.
     */
    public int getTorpedos()
    {
        return torpedos;
    }
    
    /**
     * Resets torpedos to the intial value.
     */
    public void resetTorpedos()
    {
        torpedos = torpedoCapacity;
    }
    
    /**
     * Sets torpedos. Does not allow the amount to go over the
     * torpedo capacity.
     * 
     * @param newTor A number of torpedos to set.
     */
    /*
    public void setTorpedos(int newTor)
    {
        //restrict the set amount
        if (newTor > torpedoCapacity)
        {
            newTor = torpedoCapacity;
        }
        
        torpedos = newTor;
    }*/
    
    /**
     * Decrements the torpedo count.
     */
    public void fireTorpedo()
    {
        torpedos--;
    }
    
    /**
     * Prints ship info.
     */
    /*
    public void shipInfo()
    {
        System.out.println();
        System.out.println("                         ------*------");
        System.out.println("         -------------   `---  ------'");
        System.out.println("         `-------- --'      / /");
        System.out.println("                  \\\\-------  --");
        System.out.println("                  '-----------'\n");
        System.out.println("       The USS Enterprise --- NCC - 1701-"+seed+"\n\n");
    }*/
    
    /**
     * Adds all the necessary systems for a enterprise ship.
     */
    private void initSubsystems()
    {
        Navigation nav = new Navigation(this);
        ShortRangeScanner srs = new ShortRangeScanner(this);
        LongRangeScanner lrs = new LongRangeScanner(this);
        PhaserControl pha = new PhaserControl(this);
        PhotonTorpedoLauncher tor = new PhotonTorpedoLauncher(this);
        ShieldControl shi = new ShieldControl(this);
        LibraryComputer com = new LibraryComputer(this);
        DamageControl dam = new DamageControl(this);
        TractorBeam bea = new TractorBeam();
        ImpulseEngines imp = new ImpulseEngines(this);
        
        //ORDER IS IMPORTANT HERE
        getSystems().put(Navigation.kKey, nav); //1
        getSystems().put(ImpulseEngines.kKey, imp); //10
        getSystems().put(ShortRangeScanner.kKey, srs); //2
        getSystems().put(LongRangeScanner.kKey, lrs); //3
        getSystems().put(PhaserControl.kKey, pha); //4
        getSystems().put(PhotonTorpedoLauncher.kKey, tor); //5
        getSystems().put(TractorBeam.kKey, bea); //9
        getSystems().put(DamageControl.kKey, dam); //6
        getSystems().put(ShieldControl.kKey, shi); //7
        getSystems().put(LibraryComputer.kKey, com); //8
        
        
        ///getSystems().put(NavWheel.key, new NavWheel());
    }
}
