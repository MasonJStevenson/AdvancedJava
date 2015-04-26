
import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StartrekGame3Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StartrekGame3Test
{
    /**
     * Default constructor for test class StartrekGame3Test
     */
    public StartrekGame3Test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    @Test
    public void testImpBea()
    {
        StartrekGame3 model = new StartrekGame3(0);
        model.doCommand("imp");
        model.doCommand("imp $");
        model.doCommand("imp 9");
        model.doCommand("imp 24");
        model.doCommand("imp E");
        model.doCommand("imp E");
        model.doCommand("shi 500");
        model.doCommand("bea on");
        assertEquals("Quadrant mismatch", "[1,5  * , 2,5  * , 3,1  * , 5,4 +K+, 6,1 <0>, 8,3  * , 8,7  * ]", model.getQuadrant().toString());
        model.doCommand("imp S");
        assertEquals("Quadrant mismatch", "[1,5  * , 2,5  * , 3,1  * , 5,4 +K+, 8,1 <0>, 8,3  * , 8,7  * ]", model.getQuadrant().toString());
        model.doCommand("imp NE");
        model.doCommand("shi 500");
        model.doCommand("nav N 1");
        model.doCommand("imp N");
        model.doCommand("imp N");
        model.doCommand("imp N");
    }
    
    @Test 
    public void testGameWon()
    {
        StartrekGame3 model = new StartrekGame3(398);
        String[] temp;
        //DefaultGameModel model = new DefaultGameModel(0);
        //StartrekGUI gui =  new StartrekGUI(model);
        
        //model.addObserver(gui);
        //gui.setVisible(true);
        
        model.doCommand("shi 1000");
        model.doCommand("nav 1 .1");
        model.doCommand("nav 3 4");
        model.doCommand("tor 8");
        model.doCommand("nav 3 2");
        model.doCommand("pha 500");
        model.doCommand("pha 200"); 
        model.doCommand("nav 5 7 ");
        model.doCommand("lrs ");
        model.doCommand("nav 1 1 ");
        model.doCommand("pha 600 ");
        model.doCommand("pha 100 ");
        model.doCommand("nav 5 .2 ");
        model.doCommand("nav 7 5 ");
        model.doCommand("tor 7 ");
        model.doCommand("nav 3 2 ");
        model.doCommand("nav 3 .1 ");
        model.doCommand("nav 1 1");
        model.doCommand("nav 1 .4 ");
        model.doCommand("pha 300 ");
        model.doCommand("tor  7.3 ");
        model.doCommand("nav 7 .2 ");
        model.doCommand("nav 1 1 ");
        model.doCommand("tor 1 ");
        model.doCommand("lrs ");
        model.doCommand("nav 1 1 ");
        model.doCommand("shi 300 ");
        model.doCommand("nav 1 1 ");
        model.doCommand("nav 1 .1 ");
        model.doCommand("nav 7 3 ");
        model.doCommand("shi 500 ");
        model.doCommand("nav 7 .3 ");
        model.doCommand("nav 5 .2 ");
        model.doCommand("com ");
        model.doCommand("nav 5 .1");
        model.doCommand("nav 1 .1");
        model.doCommand("tor 2");
        
        temp = model.getLog().split("\n");
        assertEquals("Log mismatch", "Torpedo Track:", temp[0]);
        assertEquals("Log mismatch", "    3, 5", temp[1]);
        assertEquals("Log mismatch", "    2, 6", temp[2]);
        assertEquals("Log mismatch", "    1, 7", temp[3]);
        assertEquals("Log mismatch", "*** Klingon Destroyed ***", temp[4]);
        assertEquals("Log mismatch", "Congratulations, Captain!  The last Klingon Battle Cruiser", temp[5]);
        assertEquals("Log mismatch", "menacing the Federation has been destroyed.", temp[6]);
        assertEquals("Log mismatch", "Your efficiency rating is 106.00", temp[7]);
        //System.out.println(model.getLog());
    }
    
    @Test
    public void testDock()
    {
        StartrekGame3 model = new StartrekGame3(16);
        model.doCommand("tor 2");
        model.doCommand("nav 1 1");
        model.doCommand("shi 1000");
        model.doCommand("nav 7 .4");
        model.doCommand("nav 5 .2");
        model.doCommand("dam");
        model.doCommand("pha 20");
        model.doCommand("shi 500");
        model.doCommand("tor 5");
        model.doCommand("pha 20");
        model.doCommand("tor 1.5");
        
    }
    
    @Test
    public void testClass()
    {
        int[][] galaxy;
        StartrekGame3 model = new StartrekGame3(0);
        assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,3 <0>, 7,6  * , 8,6  * ]", model.getQuadrant().toString());
        galaxy = model.getGalaxy();
        
        //loop rows
        for (int row = 1; row < galaxy.length; row++)
        {
            //loop cols
            for (int col = 1; col < galaxy.length; col++)
            {
                if (row == 2 && col == 5)
                {
                    assertEquals("Galaxy Mismatch!", 6, galaxy[row][col]);
                }
                
                else
                {
                    assertEquals("Galaxy Mismatch!", 0, galaxy[row][col]);
                }
            }
        }
        model.getIndicators();
        model.getDeviceStatus();
        model.getAlertLevel();
        model.getShipPosition();
        model.getLog();
    }
    
    @Test public void testAll()
    {
        String[] files = {
            "CourseControl", "DamageControl", "DamageHeal", "DeathByKlingons", "DestroyStarbases",
            "DirDistCalc", "ExceedQuadrantLimits", "ExpendsTorpedoes", "GalaxyLimit", "LibComp1", "LibComp2", "LibComp3", 
            "LibComp5", "ManeuverEnergy", "NewQuadrantAfterLimit", "NoNewQuadrant", "OutOfTime", "OutOfTimeEnteringQuadrant",
            "PhaserControl", "Quitgame", "RepairCrew", "ShieldControl", "Start", "Sub1", "Sub2", "Stranded_1", 
            "TorpedoMiss", "UndockProperly", "UnkillableKlingon"};
            
        StartrekGame3 model;
        Scanner scanner;
        
        String prefex = "command_scripts/test";
        String suffex = ".txt";
            
        try
        {
            for (String fileName : files)
            {
                model = new StartrekGame3(0);
                scanner = new Scanner(new File(prefex + fileName + suffex));
            
                while (scanner.hasNextLine())
                {
                    model.doCommand(scanner.nextLine());
                }
            }
        }
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    @Test 
    public void libComputer()
    {
        StartrekGame3 model = new StartrekGame3(0);
        model.doCommand("com");
        model.doCommand("com 1");
        model.doCommand("com 2");
        model.doCommand("com 3");
        model.doCommand("com 4");
        model.doCommand("com 4 1 2");
        model.doCommand("com 4 1 2 3 4");
        model.doCommand("com 5");
    }
    
    @Test 
    public void shieldTest()
    {
        StartrekGame3 model = new StartrekGame3(0);
        String log = "";
        
        log += model.getLog();
        model.doCommand("shi 1");
        log += model.getLog();
        model.doCommand("shi 100000000");
        log += model.getLog();
        model.doCommand("shi -1");
        log += model.getLog();
        model.doCommand("shi 500");
        log += model.getLog();
        model.doCommand("nav 1 1");
        log += model.getLog();
        model.doCommand("shi 200");
        log += model.getLog();
        model.doCommand("nav 1 .1");
        log += model.getLog();
        model.doCommand("shi 200");
        log += model.getLog();
        
        ///System.out.print(log);
        ///assertEquals("log must equal oracle", log + "\n", readFile(new File("mjsTests/1.txt")));
    }
    
    // test tractor beam keeps klingon from moving
    @Test
    public void testGame3()
    {
        StartrekGame3 game = new StartrekGame3(3);
        String[] commands = {"lrs","shi 1000","nav 5.2 .8","bea on",};
        for (String cmd: Arrays.asList(commands))
        {
            game.doCommand(cmd);
        }
        assertEquals("Quadrant mismatch", "[1,3  * , 2,7 <0>, 3,2 +K+, 3,4  * , 3,5  * , 4,6  * , 6,5  * ]", game.getQuadrant().toString());
        assertEquals("Indicator 0 mismatch", 2968, (int) game.getIndicators().get(0));
        assertEquals("Indicator 1 mismatch", 1000, (int) game.getIndicators().get(1));
        assertEquals("Indicator 2 mismatch", 10, (int) game.getIndicators().get(2));
        assertEquals("Indicator 3 mismatch", 21, (int) game.getIndicators().get(3));
        assertEquals("Indicator 4 mismatch", 24, (int) game.getIndicators().get(4));
        String[] logOut = game.getLog().split("\n");  
        assertEquals("Log expecting:Tractor beam on., was:"+logOut[0], "Tractor beam on.", logOut[0]);
        assertEquals("Wrong log length, 1 != "+logOut.length, 1, logOut.length);

        game.doCommand("nav 5 .6");
        assertEquals("Quadrant mismatch", "[1,3  * , 2,2 <0>, 3,2 +K+, 3,4  * , 3,5  * , 4,6  * , 6,5  * ]", game.getQuadrant().toString());
        assertEquals("Indicator 0 mismatch", 2858, (int) game.getIndicators().get(0));
        assertEquals("Indicator 1 mismatch", 930, (int) game.getIndicators().get(1));
        assertEquals("Indicator 2 mismatch", 10, (int) game.getIndicators().get(2));
        assertEquals("Indicator 3 mismatch", 21, (int) game.getIndicators().get(3));
        assertEquals("Indicator 4 mismatch", 24, (int) game.getIndicators().get(4));
        logOut = game.getLog().split("\n");  
        assertEquals("Log expecting:70 unit hit on Enterprise from sector 3, 2, was:"+logOut[0], "70 unit hit on Enterprise from sector 3, 2", logOut[0]);

    }
    // test tractor beam uses 25 energy
    @Test
    public void testBeamEnergy()
    {
        StartrekGame3 game = new StartrekGame3(0);
        String[] commands = {"bea on","nav 1 .1",};
        for (String cmd: Arrays.asList(commands))
        {
            game.doCommand(cmd);
        }
        assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,4 <0>, 7,6  * , 8,6  * ]", game.getQuadrant().toString());
        assertEquals("Indicator 0 mismatch", 2964, (int) game.getIndicators().get(0));

        game.doCommand("bea off");
        game.doCommand("nav 1 .1");

        assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,5 <0>, 7,6  * , 8,6  * ]", game.getQuadrant().toString());
        assertEquals("Indicator 0 mismatch", 2953, (int) game.getIndicators().get(0));
    }
    // Test supernova appears on long range scan
    @Test
    public void testScanNova()
    {
        StartrekGame3 game = new StartrekGame3(5);
        String[] commands = {"nav 1 1","nav 6.9 2","lrs",};
        for (String cmd: Arrays.asList(commands))
        {
            game.doCommand(cmd);
        }
        assertEquals("Quadrant mismatch", "[1,4  * , 1,7  * , 4,5  * , 4,6 <0>, 5,1  * , 6,1  * , 6,2  * , 6,7  * , 7,8  * ]", game.getQuadrant().toString());
        assertEquals("Indicator 0 mismatch", 2912, (int) game.getIndicators().get(0));
        assertEquals("Indicator 1 mismatch", 0, (int) game.getIndicators().get(1));
        assertEquals("Indicator 2 mismatch", 10, (int) game.getIndicators().get(2));
        assertEquals("Indicator 3 mismatch", 26, (int) game.getIndicators().get(3));
        assertEquals("Indicator 4 mismatch", 24, (int) game.getIndicators().get(4));
        String[] logOut = game.getLog().split("\n");  
        assertEquals("Log expecting::   1 : 307 :   3 :, was:"+logOut[0], ":   1 : 307 :   3 :", logOut[0]);
        assertEquals("Log expecting::  11 :   8 :   7 :, was:"+logOut[1], ":  11 :   8 :   7 :", logOut[1]);
        assertEquals("Log expecting::   5 :   * :   2 :, was:"+logOut[2], ":   5 :   * :   2 :", logOut[2]);
        assertEquals("Wrong log length, 3 != "+logOut.length, 3, logOut.length);

        game.doCommand("nav 7 5");
        game.doCommand("lrs");

        assertEquals("Quadrant mismatch", "[4,6 <0>, 7,5  * ]", game.getQuadrant().toString());
        logOut = game.getLog().split("\n");  
        assertEquals("Log expecting:: 307 : 106 : 103 :, was:"+logOut[0], ": 307 : 106 : 103 :", logOut[0]);
        assertEquals("Log expecting::   7 :   1 : 10* :, was:"+logOut[1], ":   7 :   1 : 10* :", logOut[1]);
        assertEquals("Log expecting:: *** : *** : *** :, was:"+logOut[2], ": *** : *** : *** :", logOut[2]);
        assertEquals("Wrong log length, 3 != "+logOut.length, 3, logOut.length);
    }
    // test moving into supernova quadrant kills Enterprise
    @Test
    public void testDeathByNova()
    {
        StartrekGame3 game = new StartrekGame3(5);
        String[] commands = {"nav 1 1","nav 6.9 2","lrs","nav 7 5","lrs","nav 1 1",};
        for (String cmd: Arrays.asList(commands))
        {
            game.doCommand(cmd);
        }
        assertEquals("Quadrant mismatch", "[4,6 <0>]", game.getQuadrant().toString());
        String[] logOut = game.getLog().split("\n");  
        assertEquals("Log expecting:Damage Control report:, was:"+logOut[0], "Damage Control report:", logOut[0]);
        assertEquals("Log expecting:    Phaser Control state of repair improved, was:"+logOut[1], "    Phaser Control state of repair improved", logOut[1]);
        assertEquals("Log expecting:Now entering Spica quadrant..., was:"+logOut[2], "Now entering Spica quadrant...", logOut[2]);
        assertEquals("Log expecting:You entered a quadrant with a SUPERNOVA., was:"+logOut[3], "You entered a quadrant with a SUPERNOVA.", logOut[3]);
        assertEquals("Log expecting:The Enterprise has been destroyed. , was:"+logOut[4], "The Enterprise has been destroyed. ", logOut[4]);
        assertEquals("Log expecting:The Federation will be conquered., was:"+logOut[5], "The Federation will be conquered.", logOut[5]);
        assertEquals("Log expecting:It is stardate 2807., was:"+logOut[6], "It is stardate 2807.", logOut[6]);
        assertEquals("Log expecting:, was:"+logOut[7], "", logOut[7]);
        assertEquals("Log expecting:There were 26 Klingon Battlecruisers left at the, was:"+logOut[8], "There were 26 Klingon Battlecruisers left at the", logOut[8]);
        assertEquals("Log expecting: end of your mission., was:"+logOut[9], " end of your mission.", logOut[9]);
        assertEquals("Wrong log length, 10 != "+logOut.length, 10, logOut.length);
    }
    // test game starts with enterprise in supernova quadrant
    @Test
    public void testOpenInSupernova()
    {
        StartrekGame3 game = new StartrekGame3(2);
        String[] commands = {};
        for (String cmd: Arrays.asList(commands))
        {
            game.doCommand(cmd);
        }
        String qLoc = game.getShipPosition()[0].toString().replace("java.awt.Point","");
        assertEquals("Quadrant point incorrect", "[x=8,y=1]",qLoc);
        String sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=4,y=7]",sLoc);
        String[] logOut = game.getLog().split("\n");  
        assertEquals("Log expecting:Your orders are as follows:, was:"+logOut[0], "Your orders are as follows:", logOut[0]);
        assertEquals("Log expecting:   Destroy the 14 Klingon warships which have invaded, was:"+logOut[1], "   Destroy the 14 Klingon warships which have invaded", logOut[1]);
        assertEquals("Log expecting:the galaxy before they can attack Federation Headquarters, was:"+logOut[2], "the galaxy before they can attack Federation Headquarters", logOut[2]);
        assertEquals("Log expecting:on stardate 2928. This gives you 28 days. There are, was:"+logOut[3], "on stardate 2928. This gives you 28 days. There are", logOut[3]);
        assertEquals("Log expecting: 5 starbases in the galaxy for resupplying your ship., was:"+logOut[4], " 5 starbases in the galaxy for resupplying your ship.", logOut[4]);
        assertEquals("Log expecting:Your mission begins with your starship located, was:"+logOut[5], "Your mission begins with your starship located", logOut[5]);
        assertEquals("Log expecting:in the galactic quadrant Pollux., was:"+logOut[6], "in the galactic quadrant Pollux.", logOut[6]);
        assertEquals("Log expecting:You entered a quadrant with a SUPERNOVA., was:"+logOut[7], "You entered a quadrant with a SUPERNOVA.", logOut[7]);
        assertEquals("Log expecting:The Enterprise has been destroyed. , was:"+logOut[8], "The Enterprise has been destroyed. ", logOut[8]);
        assertEquals("Log expecting:The Federation will be conquered., was:"+logOut[9], "The Federation will be conquered.", logOut[9]);
        assertEquals("Log expecting:It is stardate 2900., was:"+logOut[10], "It is stardate 2900.", logOut[10]);
        assertEquals("Log expecting:, was:"+logOut[11], "", logOut[11]);
        assertEquals("Log expecting:There were 14 Klingon Battlecruisers left at the, was:"+logOut[12], "There were 14 Klingon Battlecruisers left at the", logOut[12]);
        assertEquals("Log expecting: end of your mission., was:"+logOut[13], " end of your mission.", logOut[13]);
        assertEquals("Wrong log length, 14 != "+logOut.length, 14, logOut.length);
    }
    // test impulse engines use no energy
    @Test
    public void testImpulseEnergy()
    {
        // impulse engines use no energy
        StartrekGame3 game = new StartrekGame3(0);
        String[] commands = {"imp 1 ","imp 1","imp 1",};
        for (String cmd: Arrays.asList(commands))
        {
            game.doCommand(cmd);
        }
        assertEquals("Quadrant mismatch", "[1,5  * , 2,5  * , 3,1  * , 5,4 +K+, 6,1 <0>, 8,3  * , 8,7  * ]", game.getQuadrant().toString());
        assertEquals("Indicator 0 mismatch", 3000, (int) game.getIndicators().get(0));
        String qLoc = game.getShipPosition()[0].toString().replace("java.awt.Point","");
        assertEquals("Quadrant point incorrect", "[x=2,y=6]",qLoc);
        String sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=6,y=1]",sLoc);
    }

    @Test
    public void testCompassDirections()
    {
        // test upper / lower case compass directions for nav and imp
        StartrekGame3 game = new StartrekGame3(0);

        game.doCommand("nav n .2");
        String sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=4,y=3]",sLoc);

        game.doCommand("nav w .1");
        sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=4,y=2]",sLoc);

        game.doCommand("nav S .2");        
        sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=6,y=2]",sLoc);

        game.doCommand("nav E .1");        
        sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=6,y=3]",sLoc);

        game.doCommand("imp ne");        
        sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=4,y=5]",sLoc);

        game.doCommand("imp SW");        
        sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=6,y=3]",sLoc);

        game.doCommand("imp SE");        
        sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=8,y=5]",sLoc);

        game.doCommand("imp nw");        
        sLoc = game.getShipPosition()[1].toString().replace("java.awt.Point","");
        assertEquals("Sector point incorrect", "[x=6,y=3]",sLoc);

    }

    @Test
    public void testCommandList()
    {
        StartrekGame3 game = new StartrekGame3(0);
        game.doCommand("X");
        String[] logOut = game.getLog().split("\n");  
        assertEquals("Log expecting:Enter one of the following:, was:"+logOut[0], "Enter one of the following:", logOut[0]);
        assertEquals("Log expecting:  nav - To Set Course, was:"+logOut[1], "  nav - To Set Course", logOut[1]);
        assertEquals("Log expecting:  imp - Impulse engines, was:"+logOut[2], "  imp - Impulse engines", logOut[2]);
        assertEquals("Log expecting:  srs - Short Range Sensors, was:"+logOut[3], "  srs - Short Range Sensors", logOut[3]);
        assertEquals("Log expecting:  lrs - Long Range Sensors, was:"+logOut[4], "  lrs - Long Range Sensors", logOut[4]);
        assertEquals("Log expecting:  pha - Phasers, was:"+logOut[5], "  pha - Phasers", logOut[5]);
        assertEquals("Log expecting:  tor - Photon Torpedoes, was:"+logOut[6], "  tor - Photon Torpedoes", logOut[6]);
        assertEquals("Log expecting:  bea - Tractor Beam, was:"+logOut[7], "  bea - Tractor Beam", logOut[7]);
        assertEquals("Log expecting:  shi - Shield Control, was:"+logOut[8], "  shi - Shield Control", logOut[8]);
        assertEquals("Log expecting:  dam - Damage Control, was:"+logOut[9], "  dam - Damage Control", logOut[9]);
        assertEquals("Log expecting:  com - Library Computer, was:"+logOut[10], "  com - Library Computer", logOut[10]);
        assertEquals("Log expecting:  q - Resign Command, was:"+logOut[11], "  q - Resign Command", logOut[11]);
        assertEquals("Wrong log length, 12 != "+logOut.length, 12, logOut.length);    
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    private String readFile(File file) 
    {
        String out = "";
        
        try
        {
            Scanner scan = new Scanner(file);
            
            
            while (scan.hasNextLine())
            {
                out += scan.nextLine();
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return out;
    }
}
