import java.util.*;
/**
 * AbstractGameModel describes the public interface of 
 * a Startrek game model.  The Startrek graphical user interface expects
 * games to implement this model.  The model provides accessor
 * methods to the current state of the game so that it can be
 * displayed in the graphical view.
 * 
 * @author jdalbey
 * @version 2014.11.2
 */
public abstract class AbstractGameModel extends java.util.Observable 
{
    /** A random number generator for subclasses to use. */
    public final java.util.Random rand;
    /** The seed used for random number generation. 
     *  This also serves as the game number.
     */
    public final int gameNumber;

    /** Construct the game model with a random number seed of zero. */
    protected AbstractGameModel()
    {
        rand = new java.util.Random(0);
        gameNumber = 0;
    }

    /** Construct the game model with the given random number seed.
     *  Informally, the seed is called the "game number."
     *  @param seed a natural number to seed the random number generator.
     */
    protected AbstractGameModel(int seed)
    {
        rand = new java.util.Random(seed);
        this.gameNumber = seed;
    }

    /** Accessor to the occupied sectors in the current quadrant. 
     *  @return a set of just those sectors that contain a SpaceItem.
     */
    public abstract TreeSet<Sector> getQuadrant();

    /** Accessor to a copy of the galaxy map provided by the library computer. 
     *  @return the map data for the galaxy as a 2-d array of integers.
     *  The value of each integer represents the content of a quadrant.
     */
    public abstract int[][] getGalaxy();

    /** Return a list of five key ship indicators, in this order:
     *  Energy, Shields, Torpedoes, Klingons, Days left
     *  @return the quantity for each indicator, as a list of Integers.
     */  
    public abstract List<Integer> getIndicators();

    /** Return status of devices, in this order:
     * Warp Engines, Short Range Sensors, Long Range Sensors, Phaser Control, 
     * Photon Tubes, Damage Control, Shield Control, Library-Computer.
     * @return a numerical value for each device representing its operational
     * state as a positive or negative floating point value.
     */   
    public abstract List<Double> getDeviceStatus();

    /** Accessor to the ship's current alert level.
     *  @return current alert level
     */
    public abstract AlertLevel getAlertLevel();

    /** Accessor to the Enterprise position in the galaxy.
     * @return array of two Points specifying ship position.  
     * First item is Quadrant, second is Sector.
     */
    public abstract java.awt.Point[] getShipPosition();

    /** Accessor to most recent log message.
     * @return log message to be displayed as a string
     */
    public abstract String getLog();

    /** Carry out the given command from the user.
     * @param command the user's input
     */
    public abstract void doCommand(String command);

}
