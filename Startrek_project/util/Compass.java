package util;


/**
 * Performs string conversions for the spaceship navigation system.
 * 
 * @author Mason Stevenson
 * @version 0.1
 */
public class Compass
{
    private static final String[] kStringDirections = {
        "e", "ne", "n", "nw", "w", "sw", "s", "se"};
    private static final String[] kIntDirections = {
        "1", "2", "3", "4", "5", "6", "7", "8"};
    
    /**
     * Checks a direction input.
     * @param dir a direction input.
     * @return returns the converted input.
     */
    public static String getDirection(String dir)
    {
        int index;
        
        //if number
        try
        {
            Double.parseDouble(dir);
        }
        
        catch(NumberFormatException exception)
        {
            return getResult(dir);
        }
        
        return dir;        
    }
    
    private static String getResult(String dir)
    {   
        //loop through intDirections
        for (int index = 0; index < kIntDirections.length; index++)
        {
            //check for a match
            if (dir.toLowerCase().equals(kStringDirections[index]))
            {
                return kIntDirections[index];
            }
        }
        
        return "-1";
    }
}
