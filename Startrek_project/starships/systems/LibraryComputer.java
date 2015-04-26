package starships.systems;
import util.*;
import starships.Starship;
import starships.Enterprise;

/**
 * Main computer for a starship.
 * 
 * @author Mason Stevenson 
 * @version 0.2
 */
public class LibraryComputer extends Subsystem
{
    public static final String kKey = "com";
    public static final char kZero = '0';
    public static final char kOne = '1';
    public static final char kTwo = '2';
    public static final char kThree = '3';
    public static final char kFour = '4';
    public static final char kFive = '5';
    public static final int kTimeMult = 10;
    public static final double kRowVector1 = 3.0;
    public static final double kRowVector2 = 5.0;
    public static final double kRowVector3 = 7.0;
    public static final double kRowVector4 = 1.0;
    public static final int kColMult = 2;
    
    public static final int kGMEleven = 11;
    public static final int kGMTwelve = 12;
    public static final int kGMTwo = 2;
    public static final int kGMFive = 5;
    
    public static final int kDirDistLen = 6; // ex: com 4 1 1 3 4
    
    private int[][] galacticRecord;
    private LoggingScanner logscanner;
    private Starship ship;
    
    /**
     * Constructor for the LibraryComputer
     * @param newShip a reference to the ship that contains this system.
     */
    public LibraryComputer(Starship newShip)
    {
        super("Library-Computer");
        ship = newShip;
        galacticRecord = new int[Constants.kGalaxySize][Constants.kGalaxySize];
        
        //Initialize galactic record- loop through rows
        for (int row = 1; row < Constants.kGalaxySize; row++)
        {
            //loop through collumns
            for (int col = 1; col < Constants.kGalaxySize; col++)
            {
                galacticRecord[row][col] = 0;
            }
        }
    }
    
    /**
     * Opens up the ship's computer and allows the pilot to 
     * access its features.
     * 
     * @param commands a list of commands for this module.
     * @return returns the log
     */
    public String launch(String[] commands)
    {
        String sTemp = "";
        
        //if this system is damaged
        if (isDamaged())
        {
            //println("Library Computer inoperable");
            print("Library Computer inoperable");
            return getLog();
        }
        
        //check command length
        if (commands.length == 1)
        {
            //set to "com"
            sTemp = commands[0];
        }
        
        //set to the next command
        else
        {
            sTemp = commands[1];
        }
        
        //computer actions
        switch (sTemp.toUpperCase().charAt(0))
        {
            ///case kZero:
                ///galacticRecord();
                ///break;
            case kOne:
                statusReport();
                break;
            case kTwo:
                torpedoData();
                break;
            case kThree:
                navData();
                break;
            case kFour:
                dirdistCalc(commands);
                break;
            case kFive:
                galaxyMap();
                break;
            default:
                println(
                    "Functions available from Library-Computer:");
                ///println("   0 = Cumulative Galactic Record");
                println("   1 = Mission Progress Report");
                println("   2 = Photon Torpedo Data");
                println("   3 = Starbase Nav Data");
                println("   4 = Direction/Distance Calculator");
                println("   5 = Galaxy 'Region Name' Map");
                break;
        }
        
        return getLog();
    }
    
    /**
     * Adds some data of format xyz to the galactic record, where
     * x is the number of enemies, y is the number of starbases, and 
     * z is the number of stars.
     * 
     * @param row the row index of this record
     * @param col the collumn index of this record
     * @param data the data of format xyz
     */
    public void addToRecord(int row, int col, int data)
    {
        galacticRecord[row][col] = data;
    }
    
    /**
     * retrieves a record from the galactic record.
     * @param row the row index of this record
     * @param col the collumn index of this record
     * @return getLog() return getLog()s a record of format xyz
     */
    public int getRecord(int row, int col)
    {
        return galacticRecord[row][col];
    }
    
    /**
     * Returns the galactic record.
     * @return returns the galactic record.
     */
    public int[][] getGalacticRecord()
    {
        return galacticRecord;
    }
    
    /**
     * Prints the galactic record
     */
    private void galacticRecord()
    {
        print(String.format("     Computer Record of Galaxy for Quadrant %d,%d\n", 
            ship.getQuadrantRow(), ship.getQuadrantCol()));
        println("     1     2     3     4     5     6     7     8");

        //loop through rows
        for (int row = 1; row < Constants.kGalaxySize; row++)
        {
            println(
                "   ----- ----- ----- ----- ----- ----- ----- -----");

            print(String.format("%d", row));

            //loop through collumns
            for (int col = 1; col < Constants.kGalaxySize; col++)
            {
                print("   ");

                //out of bounds
                if (galacticRecord[row][col] == 0)
                {
                    print("***");
                }
                
                //not out of bounds
                else
                {
                    //check for supernova
                    if (ship.getGalaxy().getQuadrant(row, col).hasSupernova())
                    {
                        print(String.format("%3c", '*'));
                    }
                    
                    //no supernova
                    else
                    {
                        print(String.format("%3d", galacticRecord[row][col]));
                    }
                }
            }

            println();
        }

        println(
            "   ----- ----- ----- ----- ----- ----- ----- -----");
    }

    private void statusReport()
    {
        String temp = "";

        print("   Mission Progress Report:\n");

        //if multiple enemies in galaxy
        if (ship.getGalaxy().getTotalEnemies() > 1)
        {
            temp = "s";
        }

        print(String.format("Klingon%s Left: %d\n", temp, 
            ship.getGalaxy().getTotalEnemies()));

        print(String.format("Mission must be completed in %4.1f stardates\n",
            Constants.kTenthsPlace * 
            SpaceMath.cint((ship.getGalaxy().getTime().getStart() + 
            ship.getGalaxy().getTime().getEnd() - 
            ship.getGalaxy().getTime().getCurrent()) * kTimeMult)));

        // if no starbases
        if (ship.getGalaxy().getTotalStarbases() < 1)
        {
            print(
                "Your stupidity has left you on your own in the galaxy\n");
            print(" -- you have no starbases left!\n");
        }
        
        // bases remain
        else
        {  
            temp = "s";
            
            //no starbases remain
            if (ship.getGalaxy().getTotalStarbases() <= 1)
            {
                temp = "";
            }

            print(String.format(
                "The Federation is maintaining %d starbase%s in the galaxy\n\n",
                ship.getGalaxy().getTotalStarbases(), temp));
        }

        //println();
    }

    private void torpedoData()
    {
        int[][] enemyData = ship.getQuadrant().getEnemyData();
        int enemiesInQuadrant = ship.getQuadrant().getEnemyNum();
        String temp = "";
      
        //no enemies
        if (enemiesInQuadrant <= 0)
        {
            println(
                "Science Officer Spock reports:");
            println(
                "  'Sensors show no enemy ships in this quadrant.'");
            return;
        }

        //multiple enemies
        if (enemiesInQuadrant > 1)
        {
            temp = "s";
        }

        print(String.format("From Enterprise to Klingon battlecruiser%s:\n\n", temp));

        //loop through enemies in quadrant
        for (int count = 1; count <= Constants.kMaxEnemyQuad; count++)
        {
            // if still alive
            if (enemyData[count][Constants.kEnemyShieldIndex] >= 0)
            {
                computeVector(ship.getSectorRow(), ship.getSectorCol(), 
                    enemyData[count][1], 
                    enemyData[count][Constants.kEnemyColIndex]);
            }
        }
    }

    private void navData()
    {
        //no bases in quadrant
        if (ship.getQuadrant().getBaseNum() <= 0)
        {
            println("Mr. Spock reports,");
            println(
                "  'Sensors show no starbases in this quadrant.'");
            return;
        }

        /*
        warp = b4;
        x  = b5;
        c1 = s1;
        a  = s2;
        */
        computeVector(ship.getSectorRow(), ship.getSectorCol(), 
            ship.getQuadrant().getBaseRow(), ship.getQuadrant().getBaseCol());
    }

    private void dirdistCalc(String[] commands)
    { 
        //warp = targetRow
        //x = targetCol
        //c1 = shipRow
        //a = shipCol
        
        String sTemp;
        int targetRow;
        int targetCol;
        double shipRow;
        double shipCol;
        int index = 1;
        
        //invalid command length
        if (commands.length < kDirDistLen)
        {
            return;
        }

        ///println("Direction/Distance Calculator\n");
        ///print(String.format("You are at quadrant %d,%d sector %d,%d\n\n", 
            ///ship.getQuadrantRow(), ship.getQuadrantCol(),
            ///SpaceMath.cint(ship.getSectorRow()), 
            ///SpaceMath.cint(ship.getSectorCol())));

        ///println("Please enter initial X coordinate: ");
        ///sTemp = logscanner.nextLine();
        shipRow = Integer.parseInt(commands[++index]);

        ///println("Please enter initial Y coordinate: ");
        ///sTemp = logscanner.nextLine();
        shipCol = Integer.parseInt(commands[++index]);

        ///println("Please enter final X coordinate: ");
        ///sTemp = logscanner.nextLine();
        targetRow = Integer.parseInt(commands[++index]);

        ///println("Please enter final Y coordinate: ");
        ///sTemp = logscanner.nextLine();
        targetCol = Integer.parseInt(commands[++index]);

        computeVector(shipRow, shipCol, targetRow, targetCol);
    }
    
    private void computeVector(double shipRow, double shipCol, 
        double targetRow, double targetCol)
    {
        //warp = targetRow
        //x = targetCol
        //c1 = shipRow
        //a = shipCol
        
        targetCol -= shipCol;
        shipCol = shipRow - targetRow;

        //check valid col
        if (targetCol <= 0.0)
        {
            //check valid col
            if (shipCol > 0.0)
            {    
                shipRow = kRowVector1;
                sub2(shipRow, shipCol, targetCol);
                return;
            }
            
            //different sub
            else
            {
                shipRow = kRowVector2;
                sub1(shipRow, shipCol, targetCol);
                return;
            }
        }
        
        //check valid ship col
        else if (shipCol < 0.0)
        {
            shipRow = kRowVector3;
            sub2(shipRow, shipCol, targetCol);
            return;
        }
        
        //default
        else
        {
            shipRow = kRowVector4;
            sub1(shipRow, shipCol, targetCol);
            return;
        }
    }
    
    private void sub1(double shipRow, double shipCol, double targetCol)
    {
        //warp = targetRow
        //x = targetCol
        //c1 = shipRow
        //a = shipCol
        double distance;
        
        targetCol = Math.abs(targetCol);
        shipCol = Math.abs(shipCol);

        //check col
        if (shipCol <= targetCol)
        {
            print(String.format("  DIRECTION = %4.2f\n", 
                shipRow + (shipCol / targetCol)));
        }
        
        //default
        else
        {
            print(String.format("  DIRECTION = %4.2f\n", 
                shipRow + (((shipCol * kColMult) - targetCol) / shipCol)));
        }

        //check targetCol
        if (targetCol > shipCol)
        {
            distance = targetCol;
        }
        
        //set distance
        else
        {
            distance = shipCol;
        }
        
        print(String.format("  DISTANCE = %4.2f\n", distance));
    }

    private void sub2(double shipRow, double shipCol, double targetCol)
    {
        //warp = targetRow
        //x = targetCol
        //c1 = shipRow
        //a = shipCol
        double distance;
        
        targetCol = Math.abs(targetCol);
        shipCol = Math.abs(shipCol);

        //check col
        if (shipCol >= targetCol)
        {
            print(String.format("  DIRECTION = %4.2f\n", 
                shipRow + (targetCol / shipCol)));
        }

        //default
        else
        {
            print(String.format("  DIRECTION = %4.2f\n", shipRow + 
                (((targetCol * kColMult) - shipCol) / targetCol)));
        }

        //check targetCol
        if (targetCol > shipCol)
        {
            distance = targetCol;
        }
        
        //get distance
        else
        {
            distance = shipCol;
        }
        
        print(String.format("  DISTANCE = %4.2f\n", distance));
    }
    
    private void galaxyMap()
    {
        ///int i, j, j0;
        int tempRow;
        int tempCol;
        int temp;
        String quadrantName;
        ///g5 = 1;

        print(String.format(("                   The Galaxy\n")));
        print(String.format(("    1     2     3     4     5     6     7     8\n")));

        //loop through rows
        for (int row = 1; row < Constants.kGalaxySize; row++)
        {
            print(String.format((
                "  ----- ----- ----- ----- ----- ----- ----- -----\n")));

            print(String.format("%d ", row));
            
            quadrantName = ship.getGalaxy().getQuadrant(row, 1).getName();

            temp = (int)(kGMEleven - (quadrantName.length() / kGMTwo));

            //print spaces
            for (int count = 0; count < temp; count++)
            {
                print(String.format(" "));
            }

            print(String.format(quadrantName));

            //print spaces
            for (int count = 0; count < temp; count++)
            {
                print(String.format(" "));
            }

            //print space
            if (!(quadrantName.length() % kGMTwo == 0))
            {
                print(String.format(" "));
            }

            quadrantName = ship.getGalaxy().getQuadrant(row, kGMFive).getName();

            temp = (int)(kGMTwelve - (quadrantName.length() / kGMTwo));

            //print spaces
            for (int count = 0; count < temp; count++)
            {
                print(String.format(" "));
            }

            print(String.format(quadrantName)); 

            print(String.format("\n"));
        }

        print(String.format(
            "  ----- ----- ----- ----- ----- ----- ----- -----\n"));
    }
    
    /**
     * Checks to see if this system is operational.
     * @return returnss true is this system is not operational.
     */
    public boolean isDamaged()
    {
        return getDamage() < 0.0;
    }
}
