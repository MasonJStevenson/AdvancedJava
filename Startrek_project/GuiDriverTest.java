import java.util.*;
import java.io.*;

/**
 * Write a description of class GuiDriverTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuiDriverTest
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class GuiDriverTest
     */
    public GuiDriverTest()
    {
    }
    
    public static void main(String[] args)
    {
        StartrekGame3 model = new StartrekGame3(0);
        //DefaultGameModel model = new DefaultGameModel(0);
        StartrekGUI gui =  new StartrekGUI(model);
        
        //String[] files = {"testGalaxyLimit.txt"};
        //String[] files = {"testLibComp1.txt"};
        //String[] files = {"testLibComp2.txt"};
        //String[] files = {"testLibComp3.txt"};
        //String[] files = {"testLibComp5.txt"};
        //String[] files = {"testManeuverEnergy.txt"};
        //String[] files = {"testNewQuadrantAfterLimit.txt"};
        //String[] files = {"testNoNewQuadrant.txt"};
        String[] files = {"testOutOfTime.txt"};
        //String[] files = {"testOutOfTimeEnteringQuadrant.txt"};
        //String[] files = {"testPhaserControl.txt"};
        //String[] files = {"testQuitgame.txt"};
        //String[] files = {"testRepairCrew.txt"};
        //String[] files = {"testShieldControl.txt"};
        //String[] files = {"testStart.txt"};
        //String[] files = {"testStranded_1.txt"};
        //String[] files = {"testSub1.txt"};
        //String[] files = {"testSub2.txt"};
        //String[] files = {"testTorpedoMiss.txt"};
        //String[] files = {"testUndockProperly.txt"};
        //String[] files = {"testUnkillableKlingon.txt"};
        
        //String[] files = {"testZZZMJS.txt"};
        String prefex = "command_scripts/";
        
        model.addObserver(gui);
        gui.setVisible(true);
        gui.update(null, null);
        
        /*
        model.doCommand("nav 5 1");
        model.doCommand("nav 1 1");
        model.doCommand("nav 5 1");
        model.doCommand("nav 1 1");
        model.doCommand("nav 5 1");
        model.doCommand("nav 1 1");
        model.doCommand("nav 5 1");
        model.doCommand("nav 1 1");
        model.doCommand("nav 5 1");
        model.doCommand("nav 1 1");*/
        
        /*
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
        model.doCommand("tor 2");*/
        
        /*
        try
        {
            for (String fileName : files)
            {
                Scanner scanner = new Scanner(new File(prefex + fileName));
            
                while (scanner.hasNextLine())
                {
                    model.doCommand(scanner.nextLine());
                }
            }
        }
        
        catch (Exception e) 
        {
            
        }*/
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
