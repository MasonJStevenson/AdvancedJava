import java.util.*;
import java.awt.Point;

import util.*;
import starships.*;
import starships.systems.*;
import universe.*;

/**
 * Game logic for startrek
 * 
 * @author Mason Stevenson 
 * @version 0.1
 */
public class StartrekGame3 extends AbstractGameModel
{
    private int seed;
    private double stardateCur; // Current Stardate (t)
    private int stardateInit; // Starting Stardate (t0)
    private int stardateEnd; // End of time (t9)
    private boolean continuePlaying;
    private LoggingScanner logscanner;
    private Galaxy galaxy;
    private int enemyCount = 0;
    private int starbaseCount = 0;
    private Time time;
    private Enterprise ship;
    
    public static final int kTimeFactor = 20;
    public static final int kTimeMult = 100;
    public static final int kCurrentTimeFactor = 10;
    public static final int kCurrentTimeAdd = 25;
    public static final int kMinEnergy = 10;
    public static final int kInputLength = 3;
    
    private String log = "";
    
    /**
     * Constructor for SpaceGame
     * @param newSeed a seed for the the random number generator
     */
    public StartrekGame3(int newSeed)
    {
        super(newSeed);
        seed = newSeed;
        SpaceMath.randomize(seed);
        
        continuePlaying = true;
        logscanner = new LoggingScanner(System.in, true);
        
        time = new Time();
        time.setCurrent((SpaceMath.getRand(kTimeFactor) 
            + kTimeFactor) * kTimeMult);
        initialize();
        
        newGame();
        
        setChanged();
        notifyObservers();
        
        /*
        int[][] gal = galaxy.toArray();
        System.out.println("    1    2    3    4    5    6    7    8");
        for (int row = 1; row < 9; row++)
        {
            System.out.print(row + "|");
            for (int col = 1; col < 9; col++)
            {
                if (galaxy.getQuadrant(row, col).hasSupernova())
                {
                    System.out.printf(" #@& ");
                }
                
                else
                {
                    System.out.printf(" %3s ", gal[row][col]);
                }
            }
            
            System.out.println();
        }*/
    }
    
    /**
     * Inintializes some key values for the game.
     */
    public void initialize()
    {
        int temp;
        
        //time
        time.setStart((int) time.getCurrent());
        
        temp = kCurrentTimeAdd + SpaceMath.getRand(kCurrentTimeFactor);
        ///System.out.println("ENDING TIME: "  + temp);
        time.setEnd(temp);
        //ship
        ship = new Enterprise(seed);
        //world
        galaxy = new Galaxy(ship, time, enemyCount, starbaseCount);
    }

    /**
     * Starts a new game.
     */
    public void newGame()
    {
        String sTemp; 
        log += galaxy.printIntro();
        
        //calculates and places ship, enemies, stars, and bases
        log += ship.getQuadrant().generate(ship);
        
        //short_range_scan();
        log += ship.getSystems().get("srs").launch(null);
        
        checkShip();
    }
    
    private void checkShip()
    {
        //check to see if ship is destroyed
        if (ship.isDestroyed())
        {
            ///System.out.println("The Enterprise has been destroyed. ");
            ///System.out.println("The Federation will be conquered.\n");
            log += "The Enterprise has been destroyed. \n";
            log += "The Federation will be conquered.\n";
            reportEndingTime();
        }
    }
    
    private void checkTime()
    {
        //check to see if time is up
        if (time.isOut())
        {
            log += "You ran out of time to complete your mission.\n";
            reportEndingTime();
        }
    }
    
    private void checkBases()
    {
        //starbases destroyed
        if (galaxy.getTotalStarbases() <= 0)
        {   
            log += "That does it, Captain!!  \n";
            log += "You are hereby relieved of command\n";
            log += "and sentenced to 99 stardates of hard";
            log += "labor on Cygnus 12!!\n";
            resignCommision();
        }
    }
    
    private void checkWin()
    {
        double temp;
        
        //check to see if game won
        if (galaxy.getTotalEnemies() <= 0)
        {   
            log += "Congratulations, Captain!  "
                + "The last Klingon Battle Cruiser\n";
            log += "menacing the Federation has been destroyed.\n";
    
            //time spent
            if (time.getCurrent() - time.getStart() > 0)
            {
                temp = galaxy.getStartingEnemies() / 
                    (time.getCurrent() - time.getStart());
                
                log += String.format("Your efficiency rating is %4.2f\n", 
                    Constants.kThousandsPlace * (temp * temp));
            }
            
            continuePlaying = false;
        }
    }
    
    private void checkStardatesRemain()
    {
        //check out of time
        if (time.isOut())
        {
            ///System.out.println("You ran out of time to complete your mission.");
            log += "You ran out of time to complete your mission.\n";
            reportEndingTime();
        }
    }
    
    private void checkStranded()
    {
        //check ship energy levels
        if (ship.getShields() + ship.getEnergy() <= kMinEnergy && 
                (ship.getEnergy() < kMinEnergy || 
                ship.getSystems().get("shi").getDamage() < 0))
        {
            log += "** Fatal Error **   \n";
            log += "You've just stranded your ship in space.\n";
            log += "You have insufficient maneuvering energy,\n";
            log += "and Shield Control is presently\n";
            log += "incapable of cross circuiting to engine room!!\n";
            reportEndingTime();
        }
    }
    
    private void reportEndingTime()
    {
        log += String.format("It is stardate %d.\n\n", (int) time.getCurrent());
        resignCommision();
    }

    private void resignCommision()
    {
        log += String.format(
            "There were %d Klingon Battlecruisers left at the\n", 
            galaxy.getTotalEnemies());
        log += " end of your mission.\n";
        continuePlaying = false;
    }
    
    /** {@inheritDoc} */
    @Override
    public TreeSet<Sector> getQuadrant()
    {
        SectorObject[][] sectors = ship.getQuadrant().getSectors();
        TreeSet<Sector> quadrant = new TreeSet<Sector>();
        String object;
        
        //loop through rows
        for (int row = 1; row < Constants.kQuadrantSize; row++)
        {
            //loop through collumns
            for (int col = 1; col < Constants.kQuadrantSize; col++)
            {
                object = sectors[row][col].toString();
                //if not empty space
                //star
                if (!ship.getQuadrant().hasSupernova() && object.equals(" * "))
                {
                    quadrant.add(new Sector(row, col, SpaceItem.kStar));
                }
                
                //ship
                else if (object.equals("<0>"))
                {
                    quadrant.add(new Sector(row, col, SpaceItem.kEnterprise));
                }
                
                //enemy
                else if (!ship.getQuadrant().hasSupernova() && object.equals("+K+"))
                {
                    quadrant.add(new Sector(row, col, SpaceItem.kKlingon));
                }
                
                //starbase
                else if (!ship.getQuadrant().hasSupernova() && object.equals(">!<"))
                {
                    quadrant.add(new Sector(row, col, SpaceItem.kStarbase));
                }
            }
        }
        return quadrant;
    }

    /** {@inheritDoc} */
    @Override
    public int[][] getGalaxy()
    {
        return ((LibraryComputer)ship.getSystems().get(
                "com")).getGalacticRecord();
    }
    
    /** {@inheritDoc} */
    @Override
    public List<Integer> getIndicators()
    {
        int timeRemaining = 0;
        
        //check time
        if (!time.isOut())
        {
            timeRemaining = SpaceMath.cint((time.getStart() 
                + time.getEnd()) - time.getCurrent());
        }
        return Arrays.asList(ship.getEnergy() + ship.getShields(), 
            ship.getShields(), ship.getTorpedos(), 
            galaxy.getTotalEnemies(), timeRemaining);
    }
    
    /** {@inheritDoc} */
    @Override
    public List<Double> getDeviceStatus()
    {
        LinkedList<Double> list = new LinkedList<Double>();
        
        //loop through ship systems
        for (String key : ship.getKeys())
        {
            //GUI doesn't take new systems
            if (!key.equals("bea") && !key.equals("imp"))
            {
                list.add(ship.getSystems().get(key).getDamage());
            }
        }
        
        return list;
    }
    
    /** {@inheritDoc} */
    @Override
    public AlertLevel getAlertLevel()
    {
        AlertLevel lvl;
       
        //check alert level
        switch (ship.getAlertLevel().charAt(0))
        {
            case 'G':
                lvl = AlertLevel.GREEN;
                break;
            
            case 'Y':
                lvl = AlertLevel.YELLOW;
                break;
            
            case 'R':
                lvl = AlertLevel.RED;
                break;
            
            case 'D':
                lvl = AlertLevel.DOCKED;
                break;
            
            default:
                lvl = null;
                break;
        }
        
        return lvl;
    }
    
    /** {@inheritDoc} */
    @Override
    public java.awt.Point[] getShipPosition()
    {
        Point[] location = {new Point(ship.getQuadrantRow(), 
            ship.getQuadrantCol()), 
            new Point(SpaceMath.cint(ship.getSectorRow()), 
                SpaceMath.cint(ship.getSectorCol()))};
        return location;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getLog()
    {
        String temp = log;
        log = "";
        return temp;
    }

    /** Carry out the given command from the user.
     * Default implementation appends the given command to the log
     * and notify's observers.
     * @param command the user's input
     */
    @Override
    public void doCommand(String command)
    {
        log = "";
        
        //if game still running
        if (continuePlaying  && command.length() > 0)
        {
            //if user wants to quit
            if ( command.charAt(0) == 'q' )
            {
                resignCommision();
            }
            
            //if command is valid
            else if (command.length() >= kInputLength 
                && ship.getSystems().containsKey(
                    command.substring(0, kInputLength)))
            {
                log = ship.getSystems().get(
                    command.substring(0, kInputLength)).launch(
                        command.split("\\s+"));
                
                checkStranded();
                checkShip();
                checkTime();
                checkBases();
                checkWin();
            }
            
            else
            {
                log += "Enter one of the following:\n";
                log += "  nav - To Set Course\n";
                log += "  imp - Impulse engines\n";
                log += "  srs - Short Range Sensors\n";
                log += "  lrs - Long Range Sensors\n";
                log += "  pha - Phasers\n";
                log += "  tor - Photon Torpedoes\n";
                log += "  bea - Tractor Beam\n";
                log += "  shi - Shield Control\n";
                log += "  dam - Damage Control\n";
                log += "  com - Library Computer\n";
                log += "  q - Resign Command\n";
            }
            
            setChanged();
            notifyObservers();
        }
    }
}
