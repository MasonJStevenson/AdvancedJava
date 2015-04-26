
/**
 * SpaceItems are items found in space.
 * Each item has a printable representation.
 * 
 * @author jdalbey
 * @version 1
 */
public enum SpaceItem
{
    /** Items found in space */
    kStar(" * "),
    kEnterprise("<0>"),
    kKlingon("+K+"),
    kStarbase(">!<"),
    kSupernova(" $ ");
    
    private String symbol;
    private SpaceItem(String symbol)
    {
        this.symbol = symbol;
    }
    /** Return this item's symbol.
     * @return string representation of this space item.
     */
    public String toString()
    {
        return symbol;
    }
}
