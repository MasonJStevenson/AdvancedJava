package universe;


/**
 * A class that contains data for a ship that occupies a 
 * sector of a quadrant of a galaxy;
 * 
 * !IMPORTANT! key must be unique: no other SectorObject should have 
 * the same key.
 * 
 * @author Mason Stevenson 
 * @version 1.0
 */
public class Klingon implements SectorObject
{
    public static final String kKey = "+K+";
    
    /**
     * Retrieves this object's key
     * 
     * @return returns a string representation of this object.
     */
    public String toString()
    {
        return kKey;
    }
}
