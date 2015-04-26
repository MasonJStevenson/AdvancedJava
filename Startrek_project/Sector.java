
/**
 * Sector is a coordinate in a quadrant and what SpaceItem 
 * is found there.
 * @author jdalbey
 * @version 1 
 */
public final class Sector implements Comparable<Sector>
{
    // coordinate are final, because a sector's location doesn't change
    public final int row;
    public final int col;
    private SpaceItem item;
    
    /**
     * Constructor for objects of class SectorItem
     * @param row vertical coordinate
     * @param col horizontal coordinate
     * @param item the contents of this sector
     */
    public Sector(int row, int col, SpaceItem item)
    {
        this.row = row;
        this.col = col;
        this.item = item;
    }
    /** Set a new item into this sector.
     *  @param newItem a SpaceItem to be placed in this sector
     */
    public void setItem(SpaceItem newItem)
    {
        this.item = item;
    }
    /** Accessor to the item in this sector.
     *  @return the SpaceItem located in this sector
     */
    public SpaceItem getItem()
    {
        return item;
    }
    /**
     * Return a printable representation of this sector
     * @return diagnostic string depiction of this sector 
     */
    @Override
    public String toString()
    {
        return "" + row + "," + col + " " + item.toString();
    }
    
    /** Compare this Sector to another.
     * @param other the Sector to which this Sector is compared
     * @return -1, 0, +1 as this Sector's item is less, equal or greater than other's.
     */
    public int compareTo(Sector other)
    {
        return this.toString().compareTo(other.toString());
    }
}
