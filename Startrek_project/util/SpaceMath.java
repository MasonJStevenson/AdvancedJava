package util;


/**
 * Performs calculations for startrek.
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class SpaceMath
{
    private static java.util.Random rand;
    public static final int kEnterpriseSystemNum = 8;
    public static final double kRound = 0.5;
    
    /** 
     * Seed the randomizer with the timer.
     * @param seed an initial value for the randomizer.
     */
    public static void randomize(int seed)
    {
        rand = new java.util.Random(seed);
    }

    /** 
     * Returns an integer from 1 to iSpread.
     * @param iSpread a the range.
     * @return Returns an integer from 1 to iSpread 
     */
    public static int getRand(int iSpread)
    {
        int temp = rand.nextInt(iSpread) + 1;
        return(temp);
    }
    
    /**
     * gets a random number.
     * @return returns the random number.
     */
    public static double rnd()
    {
        return Math.abs(rand.nextInt()) / (double) Integer.MAX_VALUE;
    }

    /**
     * Gets a random number from 1 to kEnterpriseSystemNum;
     * @return returns the random number.
     */
    public static int functionR()
    {
        return(getRand(kEnterpriseSystemNum));
    }
    
    /**
     * Rounds double up to the nearest int.
     * @param target the double to round
     * @return returns the rounded double.
     */
    public static int cint(double target)
    {
        int result;

        result = (int) (target + kRound);

        return result;
    }
}
