package starships.systems;
import util.*;
import universe.*;
import starships.Starship;
import starships.Enterprise;

/**
 * Moves a starship through space.
 * 
 * @author Mason Stevenson
 * @version 0.2
 */
public class Navigation extends Subsystem
{
    ///private LoggingScanner logscanner;
    private double damage;
    private double warp; // Warp Factor
    private Starship ship;
    public static final String kKey = "nav";
    public static final double kMaxCourse = 9.0;
    public static final double kWarpLimit = 0.21;
    public static final double kInvalidWarp = 8.1;
    public static final double kMaxWarp = 8.0;
    public static final int kEnergyLost = 10;
    public static final int kCommandLength = 3; // ex nav 1 1
    
    /**
     * Constructor for Navigation module.
     * @param newShip a reference to the ship containing this module.
     */
    public Navigation(Starship newShip)
    {
        super("Warp Engines");
        ship = newShip;
    }
    
    /**
     * Launches this module.
     * @param commands commands for this module.
     * @return returns the log.
     */
    public String launch(String[] commands)
    {
        courseControl(commands);
        return getLog();
    }
    
     /* file02 Javon Negahban and Lilly Paul */
    private void courseControl(String[] commands)
    {
        String sTemp;
        double course;
        int num = 1;
        
        //check command length
        if (commands.length < kCommandLength - 1)
        {
            return;
        }
        
        course = getCourse(commands);
        
        //check course data
        if(course < 0 || course > kMaxCourse)
        {
            println(
                "Lt. Sulu reports:  Incorrect course data, sir!");
            return;
        }
        
        //check command length
        if (commands.length < kCommandLength)
        {
            return;
        }

        try 
        {
            warp = Float.parseFloat(commands[++num]);
        }
        
        catch (NumberFormatException ex)
        {
            println(
                "Lt. Sulu reports:  Incorrect warp factor, sir!");
            return;
        }

        //check for invalid warp
        if(!checkWarp())
        {
            return;
        }

        //check ship energy levels
        if (!checkEnergy())
        {
            return;
        }

        //check for tractor beam
        if (((TractorBeam)ship.getSystems().get(TractorBeam.kKey)).isEngaged() 
            && ship.getEnergy() >= TractorBeam.kEnergyReq)
        {
            ship.setEnergy(ship.getEnergy() - TractorBeam.kEnergyReq);
            print(ship.getQuadrant().enemiesShoot(ship));
        }
        
        //no beam
        else
        {
            print(ship.getQuadrant().enemiesMove(ship));
        }
        
        // check to see if klingons destroyed us
        if (ship.isDestroyed())
        {
            return;
        }

        print(ship.repairDamage(warp));

        makeMove(course, SpaceMath.cint(warp * kMaxWarp));
    }
    
    private double getCourse(String[] commands)
    {
        double course;
        int num = 1;
        
        try
        {
            course = Float.parseFloat(
                Compass.getDirection(commands[num]));
                
            //course = Float.parseFloat(commands[num]);
        }
        
        catch (NumberFormatException ex)
        {
            course = -1;
        }

        //wraparound
        if(course == kMaxCourse)
        {
            course = 1.0;
        }
        
        return course;
    }
    
    private boolean checkEnergy()
    {
        int numSectors;
        numSectors = SpaceMath.cint(warp * kMaxWarp);
        
        //check energy
        if (ship.getEnergy() - numSectors < 0)
        {
            println("Engineering reports:");
            println(
                "  Insufficient energy available for maneuvering");
            print(String.format("  at warp %3.1f!\n", warp));
            
            //if shields > numSectors && shields are not damaged
            if (ship.getShields() >= numSectors && 
                !ship.getSystems().get("shi").isDamaged())
            {
                println("Deflector Control Room acknowledges:");
                println("  " + ship.getShields() + 
                    " units of energy presently deployed to shields.");
            }

            return false;
        }
        
        return true;
    }
    
    private boolean checkWarp()
    {
        //if damaged and warp greater than warp limit
        if(isDamaged() && warp > kWarpLimit)
        {
            println(
                "Warp Engines are damaged. Maximum speed = Warp 0.2.");
            //do nothing
            return false;
        }
        
        // if invalid warp
        if (warp <= 0.0)
        {
            //do nothing
            return false;
        }
        
        // invalid warp
        else if(warp > kInvalidWarp)
        {
            println("Chief Engineer Scott reports:");
            println("  The engines won't take warp " + warp + "!");
            //do nothing
            return false;
        }
        
        return true;
    }
    
    private void makeMove(double course, int distance)
    {
        Quadrant curQuad = ship.getQuadrant();
        int shipRow;
        int shipCol;
        int roundedCourse;
        int nextLoc;
        double locRow; //x1
        double locCol; //x2
        double locX; //x
        double locY; //y
        int prevQuadRow; //q4
        int prevQuadCol; //q5
        
        
        // Used for location and movement 
        int[][] move = new int[][]{
            { 0, 0, -1, -1, -1,  0,  1, 1, 1, 0, },
            { 1, 1,  1,  0, -1, -1, -1, 0, 1, 1 }};
        
        // Remove from current location
        shipRow = SpaceMath.cint(ship.getSectorRow());
        shipCol = SpaceMath.cint(ship.getSectorCol());
        
        curQuad.getSectors()[shipRow][shipCol] = new EmptySpace();
        
        // advance to next location
        roundedCourse = SpaceMath.cint(course);  //c2;
        nextLoc = roundedCourse + 1; //c3
        
        
        locRow = move[0][roundedCourse] 
            + (move[0][nextLoc] - move[0][roundedCourse]) 
            * (course - roundedCourse);
        locCol = move[1][roundedCourse] 
            + (move[1][nextLoc] - move[1][roundedCourse]) 
            * (course - roundedCourse);

        
        locX = ship.getSectorRow();
        locY = ship.getSectorCol();
        
        ship.storeQuadrant();
        
        prevQuadRow = ship.getQuadrantRow();
        prevQuadCol = ship.getQuadrantCol();

        movementHelper(distance, locRow, locCol, locX, locY);
    }
    
    private void movementHelper(int distance, double locRow, 
        double locCol, double locX, double locY)
    {
        int temp1; //z1
        int temp2; //z2
        
        // move 'n' cells
        for (int index = 1; index <= distance; index++)
        {
            ship.setSector(ship.getSectorRow() + locRow, 
                ship.getSectorCol() + locCol);
            
            temp1 = SpaceMath.cint(ship.getSectorRow());
            temp2 = SpaceMath.cint(ship.getSectorCol());

            //if outside of quadrant
            if (temp1 < 1 || temp1 >= Constants.kGalaxySize || temp2 < 1 
                || temp2 >= Constants.kGalaxySize)
            {
                exceedQuadrantLimits(locX, locY, locRow, locCol, distance);
                
                //check for supernova
                if (ship.getQuadrant().hasSupernova())
                {
                    return;
                }
                
                //check time
                if (!ship.getGalaxy().getTime().isOut())
                {
                    completeManeuver(distance);
                }
                
                return;
            }

            // Sector not empty 
            if (!ship.getQuadrant().checkEmpty(temp1, temp2)) 
            {
                ship.setSector(
                    ship.getSectorRow() - locRow, ship.getSectorCol() - locCol);
                println("Warp Engines shut down at sector " 
                    + temp1 + ", " + temp2 + " due to bad navigation.");
                index = distance + 1;
            }
        }
        
        completeManeuver(distance);
    }

    private void completeManeuver(int distance)
    {
        double time;
        Quadrant curQuad = ship.getQuadrant();
        int shipRow = SpaceMath.cint(ship.getSectorRow());
        int shipCol = SpaceMath.cint(ship.getSectorCol());
        
        //println(
            //"reinserting ship at: " + shipRow + ", " + shipCol);
        curQuad.getSectors()[shipRow][shipCol] = new PlayerShip();
        
        maneuverEnergy(distance);

        time = 1.0;

        //check warp
        if (warp < 1.0)
        {
            time = warp;
        }
        
        ship.getGalaxy().getTime().increment(time);
        ///check_stardates_remain();
       
        //check time
        if (!ship.getGalaxy().getTime().isOut())
        {
            print(ship.getSystems().get("srs").launch(null));
        }
    }

    // file 07 Neal N and Evan P. 
    private void exceedQuadrantLimits(double locX, double locY, 
        double locRow, double locCol, int distance)
    {
        boolean outsideGalaxy = false; // x5 Outside galaxy flag 
        ship.getGalaxy().getTime().increment(1);    // increment stardate
        locX = (kMaxWarp * (ship.getQuadrantRow() - 1)) + 
            locX + (distance * locRow);
        locY = (kMaxWarp * (ship.getQuadrantCol() - 1)) + 
            locY + (distance * locCol);

        ship.setQuadrant(
            SpaceMath.cint(locX / kMaxWarp), SpaceMath.cint(locY / kMaxWarp));

        ship.setSector(
            locX - ((ship.getQuadrantRow() - 1) * Constants.kQuadrant), 
            locY - ((ship.getQuadrantCol() - 1) * Constants.kQuadrant));

        outsideGalaxy = repositionShip();
        
        //if out of bounds
        if (outsideGalaxy)
        {

            print(String.format("LT. Uhura reports:\n"));
            print(String.format("  Message from Starfleet Command:\n"));
            print(String.format(
                "  Permission to attempt crossing of galactic perimeter\n"));
            print(String.format(
                "  is hereby *denied*. Shut down your engines.\n"));
            print(String.format("Chief Engineer Scott reports:\n"));
            print(String.format("  Warp Engines shut down at sector %d, ", 
                SpaceMath.cint(ship.getSectorRow())));
            print(String.format(
                "%d of quadrant %d, %d.\n", 
                SpaceMath.cint(ship.getSectorCol()),
                ship.getQuadrantRow(), ship.getQuadrantCol()));
        }
        
        //new quadrant
        if (ship.getQuadrantRow() != ship.getPrevQuadrantRow() 
            || ship.getQuadrantCol() != ship.getPrevQuadrantCol())
        {
            print(ship.getQuadrant().generate(ship));
            
            /*
            if (ship.getQuadrant().hasSupernova())
            {
                println("You entered a quadrant with a SUPERNOVA.");
                ship.setDestroyed(true);
                return;
            }*/
        }

        maneuverEnergy(distance);
    }
    
    private boolean repositionShip()
    {
        boolean outsideGalaxy = false;
        
        //check row
        if (SpaceMath.cint(ship.getSectorRow()) == 0)
        {
            ship.setQuadrant(ship.getQuadrantRow() - 1, ship.getQuadrantCol());
            ship.setSector(ship.getSectorRow() + kMaxWarp, ship.getSectorCol());
        }

        //check col
        if (SpaceMath.cint(ship.getSectorCol()) == 0)
        {
            ship.setQuadrant(ship.getQuadrantRow(), ship.getQuadrantCol() - 1);
            ship.setSector(ship.getSectorRow(), ship.getSectorCol() + kMaxWarp);
        }
        
        // Defect repair by jd: Add the following two decisisons
        //    to detect sector out of bounds 

        //row
        if (SpaceMath.cint(ship.getSectorRow()) > Constants.kQuadrant)
        {
            ship.setQuadrant(ship.getQuadrantRow() + 1, ship.getQuadrantCol());
            ship.setSector(ship.getSectorRow() - kMaxWarp, ship.getSectorCol());
        }

        //col
        if (SpaceMath.cint(ship.getSectorCol()) > Constants.kQuadrant)
        {
            ship.setQuadrant(ship.getQuadrantRow(), ship.getQuadrantCol() + 1);
            ship.setSector(ship.getSectorRow(), ship.getSectorCol() - kMaxWarp);
        }
        
        // check if outside galaxy 
        //row
        if (ship.getQuadrantRow() < 1)
        {
            outsideGalaxy = true;
            ship.setQuadrant(1, ship.getQuadrantCol());
            ship.setSector(1.0, ship.getSectorCol());
        }

        //row
        if (ship.getQuadrantRow() > Constants.kQuadrant)
        {
            outsideGalaxy = true;
            ship.setQuadrant(Constants.kQuadrant, ship.getQuadrantCol());
            ship.setSector(kMaxWarp, ship.getSectorCol());
        }

        //col
        if (ship.getQuadrantCol() < 1)
        {
            outsideGalaxy = true;
            ship.setQuadrant(ship.getQuadrantRow(), 1);
            ship.setSector(ship.getSectorRow(), 1.0);
        }

        //col
        if (ship.getQuadrantCol() > Constants.kQuadrant)
        {
            outsideGalaxy = true;
            ship.setQuadrant(ship.getQuadrantRow(), Constants.kQuadrant);
            ship.setSector(ship.getSectorRow(), kMaxWarp);
        }
        
        return outsideGalaxy;
    }

    // file08 Yash M. and Chris H. 
    private void maneuverEnergy(int distance)
    {
        ship.setEnergy(ship.getEnergy() - distance - kEnergyLost);

        //if energy left
        if (ship.getEnergy() >= 0)
        {
            return;
        }
        println(
            "Shield Control supplies energy to complete maneuver.");

        ship.setShields(ship.getShields() + ship.getEnergy());
        ship.setEnergy(0);

        //shield floor is 0
        if (ship.getShields() <= 0)
        {
            ship.setShields(0);
        }
    }
    
    /**
     * Gets the damage value.
     * @return returns the damage value.
     */
    public double getDamage()
    {
        return damage;
    }
    
    /**
     * Sets the damage value.
     * @param dam the new damage value.
     */
    public void setDamage(double dam)
    {
        damage = dam;
    }
    
    /**
     * Checks to see if this module is damaged.
     * @return returns true if this module is damaged.
     */
    public boolean isDamaged()
    {
        return damage < 0.0;
    }
}
