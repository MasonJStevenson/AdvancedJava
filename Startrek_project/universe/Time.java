package universe;


/**
 * Holds time data for the space game.
 * 
 * @author Mason Stevenson
 * @version 1.0
 */
public class Time
{
    private int start; //the starting stardate
    private int end; //number of years to the end
    private double current; //current stardate
    
    /**
     * Accessor method for start.
     * @return returns start.
     */
    public int getStart()
    {
        return start;
    }
    
    /**
     * Accessor method for end.
     * @return returns end.
     */
    public int getEnd()
    {
        return end;
    }
    
    /**
     * Accessor method for current.
     * @return returns current.
     */
    public double getCurrent()
    {
        return current;
    }
    
    /**
     * Mutator method for start.
     * @param newStart a new starting date.
     */
    public void setStart(int newStart)
    {
        start = newStart;
    }
    
    /**
     * Mutator method for end.
     * @param newEnd a new ending time.
     */
    public void setEnd(int newEnd)
    {
        end = newEnd;
    }
    
    /**
     * Mutator method for current.
     * @param newCurrent a new current date.
     */
    public void setCurrent(int newCurrent)
    {
        current = newCurrent;
    }
    
    /**
     * Mutator method for current.
     * @param incValue a value to add to the current date.
     */
    public void increment(double incValue)
    {
        current += incValue;
    }
    
    /**
     * Checks to see if the current date is past the end date.
     * @return returns true if time has run out.
     */
    public boolean isOut()
    {
        return current > start + end;
    }
}
