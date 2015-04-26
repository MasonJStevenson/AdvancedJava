/*import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import junit.framework.TestCase;

public class InstructorTests
  extends TestCase
{
  private StartrekGame3 game;
  private final String kFilename = "testcommands.txt";
  private String[] commands = new String[0];
  private boolean success;
  
  public void setUp()
  {
    this.success = false;
  }
  
  public void tearDown()
  {
    if (!this.success) {
      saveCommands();
    }
  }
  
  private void saveCommands()
  {
    try
    {
      PrintWriter writer = null;
      writer = new PrintWriter(new FileWriter("testcommands.txt"));
      writer.println(Arrays.asList(this.commands));
      writer.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void testStart()
  {
    this.game = new StartrekGame3(0);
    this.commands = new String[0];
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,3 <0>, 7,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 3000, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 34, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=5]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 1,2 mismatch 0!=" + this.game.getGalaxy()[1][2], 0, this.game.getGalaxy()[1][2]);
    assertEquals("Galaxy 1,3 mismatch 0!=" + this.game.getGalaxy()[1][3], 0, this.game.getGalaxy()[1][3]);
    assertEquals("Galaxy 1,4 mismatch 0!=" + this.game.getGalaxy()[1][4], 0, this.game.getGalaxy()[1][4]);
    assertEquals("Galaxy 1,5 mismatch 0!=" + this.game.getGalaxy()[1][5], 0, this.game.getGalaxy()[1][5]);
    assertEquals("Galaxy 1,6 mismatch 0!=" + this.game.getGalaxy()[1][6], 0, this.game.getGalaxy()[1][6]);
    assertEquals("Galaxy 1,7 mismatch 0!=" + this.game.getGalaxy()[1][7], 0, this.game.getGalaxy()[1][7]);
    assertEquals("Galaxy 1,8 mismatch 0!=" + this.game.getGalaxy()[1][8], 0, this.game.getGalaxy()[1][8]);
    assertEquals("Galaxy 2,1 mismatch 0!=" + this.game.getGalaxy()[2][1], 0, this.game.getGalaxy()[2][1]);
    assertEquals("Galaxy 2,2 mismatch 0!=" + this.game.getGalaxy()[2][2], 0, this.game.getGalaxy()[2][2]);
    assertEquals("Galaxy 2,3 mismatch 0!=" + this.game.getGalaxy()[2][3], 0, this.game.getGalaxy()[2][3]);
    assertEquals("Galaxy 2,4 mismatch 0!=" + this.game.getGalaxy()[2][4], 0, this.game.getGalaxy()[2][4]);
    assertEquals("Galaxy 2,5 mismatch 6!=" + this.game.getGalaxy()[2][5], 6, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 0!=" + this.game.getGalaxy()[2][6], 0, this.game.getGalaxy()[2][6]);
    assertEquals("Galaxy 2,7 mismatch 0!=" + this.game.getGalaxy()[2][7], 0, this.game.getGalaxy()[2][7]);
    assertEquals("Galaxy 2,8 mismatch 0!=" + this.game.getGalaxy()[2][8], 0, this.game.getGalaxy()[2][8]);
    assertEquals("Galaxy 3,1 mismatch 0!=" + this.game.getGalaxy()[3][1], 0, this.game.getGalaxy()[3][1]);
    assertEquals("Galaxy 3,2 mismatch 0!=" + this.game.getGalaxy()[3][2], 0, this.game.getGalaxy()[3][2]);
    assertEquals("Galaxy 3,3 mismatch 0!=" + this.game.getGalaxy()[3][3], 0, this.game.getGalaxy()[3][3]);
    assertEquals("Galaxy 3,4 mismatch 0!=" + this.game.getGalaxy()[3][4], 0, this.game.getGalaxy()[3][4]);
    assertEquals("Galaxy 3,5 mismatch 0!=" + this.game.getGalaxy()[3][5], 0, this.game.getGalaxy()[3][5]);
    assertEquals("Galaxy 3,6 mismatch 0!=" + this.game.getGalaxy()[3][6], 0, this.game.getGalaxy()[3][6]);
    assertEquals("Galaxy 3,7 mismatch 0!=" + this.game.getGalaxy()[3][7], 0, this.game.getGalaxy()[3][7]);
    assertEquals("Galaxy 3,8 mismatch 0!=" + this.game.getGalaxy()[3][8], 0, this.game.getGalaxy()[3][8]);
    assertEquals("Galaxy 4,1 mismatch 0!=" + this.game.getGalaxy()[4][1], 0, this.game.getGalaxy()[4][1]);
    assertEquals("Galaxy 4,2 mismatch 0!=" + this.game.getGalaxy()[4][2], 0, this.game.getGalaxy()[4][2]);
    assertEquals("Galaxy 4,3 mismatch 0!=" + this.game.getGalaxy()[4][3], 0, this.game.getGalaxy()[4][3]);
    assertEquals("Galaxy 4,4 mismatch 0!=" + this.game.getGalaxy()[4][4], 0, this.game.getGalaxy()[4][4]);
    assertEquals("Galaxy 4,5 mismatch 0!=" + this.game.getGalaxy()[4][5], 0, this.game.getGalaxy()[4][5]);
    assertEquals("Galaxy 4,6 mismatch 0!=" + this.game.getGalaxy()[4][6], 0, this.game.getGalaxy()[4][6]);
    assertEquals("Galaxy 4,7 mismatch 0!=" + this.game.getGalaxy()[4][7], 0, this.game.getGalaxy()[4][7]);
    assertEquals("Galaxy 4,8 mismatch 0!=" + this.game.getGalaxy()[4][8], 0, this.game.getGalaxy()[4][8]);
    assertEquals("Galaxy 5,1 mismatch 0!=" + this.game.getGalaxy()[5][1], 0, this.game.getGalaxy()[5][1]);
    assertEquals("Galaxy 5,2 mismatch 0!=" + this.game.getGalaxy()[5][2], 0, this.game.getGalaxy()[5][2]);
    assertEquals("Galaxy 5,3 mismatch 0!=" + this.game.getGalaxy()[5][3], 0, this.game.getGalaxy()[5][3]);
    assertEquals("Galaxy 5,4 mismatch 0!=" + this.game.getGalaxy()[5][4], 0, this.game.getGalaxy()[5][4]);
    assertEquals("Galaxy 5,5 mismatch 0!=" + this.game.getGalaxy()[5][5], 0, this.game.getGalaxy()[5][5]);
    assertEquals("Galaxy 5,6 mismatch 0!=" + this.game.getGalaxy()[5][6], 0, this.game.getGalaxy()[5][6]);
    assertEquals("Galaxy 5,7 mismatch 0!=" + this.game.getGalaxy()[5][7], 0, this.game.getGalaxy()[5][7]);
    assertEquals("Galaxy 5,8 mismatch 0!=" + this.game.getGalaxy()[5][8], 0, this.game.getGalaxy()[5][8]);
    assertEquals("Galaxy 6,1 mismatch 0!=" + this.game.getGalaxy()[6][1], 0, this.game.getGalaxy()[6][1]);
    assertEquals("Galaxy 6,2 mismatch 0!=" + this.game.getGalaxy()[6][2], 0, this.game.getGalaxy()[6][2]);
    assertEquals("Galaxy 6,3 mismatch 0!=" + this.game.getGalaxy()[6][3], 0, this.game.getGalaxy()[6][3]);
    assertEquals("Galaxy 6,4 mismatch 0!=" + this.game.getGalaxy()[6][4], 0, this.game.getGalaxy()[6][4]);
    assertEquals("Galaxy 6,5 mismatch 0!=" + this.game.getGalaxy()[6][5], 0, this.game.getGalaxy()[6][5]);
    assertEquals("Galaxy 6,6 mismatch 0!=" + this.game.getGalaxy()[6][6], 0, this.game.getGalaxy()[6][6]);
    assertEquals("Galaxy 6,7 mismatch 0!=" + this.game.getGalaxy()[6][7], 0, this.game.getGalaxy()[6][7]);
    assertEquals("Galaxy 6,8 mismatch 0!=" + this.game.getGalaxy()[6][8], 0, this.game.getGalaxy()[6][8]);
    assertEquals("Galaxy 7,1 mismatch 0!=" + this.game.getGalaxy()[7][1], 0, this.game.getGalaxy()[7][1]);
    assertEquals("Galaxy 7,2 mismatch 0!=" + this.game.getGalaxy()[7][2], 0, this.game.getGalaxy()[7][2]);
    assertEquals("Galaxy 7,3 mismatch 0!=" + this.game.getGalaxy()[7][3], 0, this.game.getGalaxy()[7][3]);
    assertEquals("Galaxy 7,4 mismatch 0!=" + this.game.getGalaxy()[7][4], 0, this.game.getGalaxy()[7][4]);
    assertEquals("Galaxy 7,5 mismatch 0!=" + this.game.getGalaxy()[7][5], 0, this.game.getGalaxy()[7][5]);
    assertEquals("Galaxy 7,6 mismatch 0!=" + this.game.getGalaxy()[7][6], 0, this.game.getGalaxy()[7][6]);
    assertEquals("Galaxy 7,7 mismatch 0!=" + this.game.getGalaxy()[7][7], 0, this.game.getGalaxy()[7][7]);
    assertEquals("Galaxy 7,8 mismatch 0!=" + this.game.getGalaxy()[7][8], 0, this.game.getGalaxy()[7][8]);
    assertEquals("Galaxy 8,1 mismatch 0!=" + this.game.getGalaxy()[8][1], 0, this.game.getGalaxy()[8][1]);
    assertEquals("Galaxy 8,2 mismatch 0!=" + this.game.getGalaxy()[8][2], 0, this.game.getGalaxy()[8][2]);
    assertEquals("Galaxy 8,3 mismatch 0!=" + this.game.getGalaxy()[8][3], 0, this.game.getGalaxy()[8][3]);
    assertEquals("Galaxy 8,4 mismatch 0!=" + this.game.getGalaxy()[8][4], 0, this.game.getGalaxy()[8][4]);
    assertEquals("Galaxy 8,5 mismatch 0!=" + this.game.getGalaxy()[8][5], 0, this.game.getGalaxy()[8][5]);
    assertEquals("Galaxy 8,6 mismatch 0!=" + this.game.getGalaxy()[8][6], 0, this.game.getGalaxy()[8][6]);
    assertEquals("Galaxy 8,7 mismatch 0!=" + this.game.getGalaxy()[8][7], 0, this.game.getGalaxy()[8][7]);
    assertEquals("Galaxy 8,8 mismatch 0!=" + this.game.getGalaxy()[8][8], 0, this.game.getGalaxy()[8][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Your orders are as follows:, was:" + logOut[0], "Your orders are as follows:", logOut[0]);
    assertEquals("Log expecting:   Destroy the 15 Klingon warships which have invaded, was:" + logOut[1], "   Destroy the 15 Klingon warships which have invaded", logOut[1]);
    assertEquals("Log expecting:the galaxy before they can attack Federation Headquarters, was:" + logOut[2], "the galaxy before they can attack Federation Headquarters", logOut[2]);
    assertEquals("Log expecting:on stardate 2134. This gives you 34 days. There are, was:" + logOut[3], "on stardate 2134. This gives you 34 days. There are", logOut[3]);
    assertEquals("Log expecting: 2 starbases in the galaxy for resupplying your ship., was:" + logOut[4], " 2 starbases in the galaxy for resupplying your ship.", logOut[4]);
    assertEquals("Log expecting:Your mission begins with your starship located, was:" + logOut[5], "Your mission begins with your starship located", logOut[5]);
    assertEquals("Log expecting:in the galactic quadrant Deneb., was:" + logOut[6], "in the galactic quadrant Deneb.", logOut[6]);
    assertEquals("Wrong log length, 7 != " + logOut.length, 7, logOut.length);
    this.success = true;
  }
  
  public void testCourseControl()
  {
    this.game = new StartrekGame(0);
    String[] commands = { "nav a", "nav -1", "nav 10", "nav 1 10", "nav 1 8", "lrs", "nav 5 .125", "nav 5 1", "nav 5 .5", "nav 0 .2", "nav 9 .1", "nav 3 .1", "nav 3 .1" };
    for (String cmd : Arrays.asList(commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,5  * , 2,6  * , 3,6  * , 4,4  * , 4,7 <0>, 6,3  * , 7,5  * , 7,8  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2746, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 29, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "3.5", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "3.8", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.1", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=7]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=4,y=7]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 1,2 mismatch 0!=" + this.game.getGalaxy()[1][2], 0, this.game.getGalaxy()[1][2]);
    assertEquals("Galaxy 1,3 mismatch 0!=" + this.game.getGalaxy()[1][3], 0, this.game.getGalaxy()[1][3]);
    assertEquals("Galaxy 1,4 mismatch 0!=" + this.game.getGalaxy()[1][4], 0, this.game.getGalaxy()[1][4]);
    assertEquals("Galaxy 1,5 mismatch 0!=" + this.game.getGalaxy()[1][5], 0, this.game.getGalaxy()[1][5]);
    assertEquals("Galaxy 1,6 mismatch 0!=" + this.game.getGalaxy()[1][6], 0, this.game.getGalaxy()[1][6]);
    assertEquals("Galaxy 1,7 mismatch 1!=" + this.game.getGalaxy()[1][7], 1, this.game.getGalaxy()[1][7]);
    assertEquals("Galaxy 1,8 mismatch 101!=" + this.game.getGalaxy()[1][8], 101, this.game.getGalaxy()[1][8]);
    assertEquals("Galaxy 2,1 mismatch 0!=" + this.game.getGalaxy()[2][1], 0, this.game.getGalaxy()[2][1]);
    assertEquals("Galaxy 2,2 mismatch 0!=" + this.game.getGalaxy()[2][2], 0, this.game.getGalaxy()[2][2]);
    assertEquals("Galaxy 2,3 mismatch 0!=" + this.game.getGalaxy()[2][3], 0, this.game.getGalaxy()[2][3]);
    assertEquals("Galaxy 2,4 mismatch 0!=" + this.game.getGalaxy()[2][4], 0, this.game.getGalaxy()[2][4]);
    assertEquals("Galaxy 2,5 mismatch 6!=" + this.game.getGalaxy()[2][5], 6, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 0!=" + this.game.getGalaxy()[2][6], 0, this.game.getGalaxy()[2][6]);
    assertEquals("Galaxy 2,7 mismatch 7!=" + this.game.getGalaxy()[2][7], 7, this.game.getGalaxy()[2][7]);
    assertEquals("Galaxy 2,8 mismatch 6!=" + this.game.getGalaxy()[2][8], 6, this.game.getGalaxy()[2][8]);
    assertEquals("Galaxy 3,1 mismatch 0!=" + this.game.getGalaxy()[3][1], 0, this.game.getGalaxy()[3][1]);
    assertEquals("Galaxy 3,2 mismatch 0!=" + this.game.getGalaxy()[3][2], 0, this.game.getGalaxy()[3][2]);
    assertEquals("Galaxy 3,3 mismatch 0!=" + this.game.getGalaxy()[3][3], 0, this.game.getGalaxy()[3][3]);
    assertEquals("Galaxy 3,4 mismatch 0!=" + this.game.getGalaxy()[3][4], 0, this.game.getGalaxy()[3][4]);
    assertEquals("Galaxy 3,5 mismatch 0!=" + this.game.getGalaxy()[3][5], 0, this.game.getGalaxy()[3][5]);
    assertEquals("Galaxy 3,6 mismatch 0!=" + this.game.getGalaxy()[3][6], 0, this.game.getGalaxy()[3][6]);
    assertEquals("Galaxy 3,7 mismatch 3!=" + this.game.getGalaxy()[3][7], 3, this.game.getGalaxy()[3][7]);
    assertEquals("Galaxy 3,8 mismatch 115!=" + this.game.getGalaxy()[3][8], 115, this.game.getGalaxy()[3][8]);
    assertEquals("Galaxy 4,1 mismatch 0!=" + this.game.getGalaxy()[4][1], 0, this.game.getGalaxy()[4][1]);
    assertEquals("Galaxy 4,2 mismatch 0!=" + this.game.getGalaxy()[4][2], 0, this.game.getGalaxy()[4][2]);
    assertEquals("Galaxy 4,3 mismatch 0!=" + this.game.getGalaxy()[4][3], 0, this.game.getGalaxy()[4][3]);
    assertEquals("Galaxy 4,4 mismatch 0!=" + this.game.getGalaxy()[4][4], 0, this.game.getGalaxy()[4][4]);
    assertEquals("Galaxy 4,5 mismatch 0!=" + this.game.getGalaxy()[4][5], 0, this.game.getGalaxy()[4][5]);
    assertEquals("Galaxy 4,6 mismatch 0!=" + this.game.getGalaxy()[4][6], 0, this.game.getGalaxy()[4][6]);
    assertEquals("Galaxy 4,7 mismatch 0!=" + this.game.getGalaxy()[4][7], 0, this.game.getGalaxy()[4][7]);
    assertEquals("Galaxy 4,8 mismatch 0!=" + this.game.getGalaxy()[4][8], 0, this.game.getGalaxy()[4][8]);
    assertEquals("Galaxy 5,1 mismatch 0!=" + this.game.getGalaxy()[5][1], 0, this.game.getGalaxy()[5][1]);
    assertEquals("Galaxy 5,2 mismatch 0!=" + this.game.getGalaxy()[5][2], 0, this.game.getGalaxy()[5][2]);
    assertEquals("Galaxy 5,3 mismatch 0!=" + this.game.getGalaxy()[5][3], 0, this.game.getGalaxy()[5][3]);
    assertEquals("Galaxy 5,4 mismatch 0!=" + this.game.getGalaxy()[5][4], 0, this.game.getGalaxy()[5][4]);
    assertEquals("Galaxy 5,5 mismatch 0!=" + this.game.getGalaxy()[5][5], 0, this.game.getGalaxy()[5][5]);
    assertEquals("Galaxy 5,6 mismatch 0!=" + this.game.getGalaxy()[5][6], 0, this.game.getGalaxy()[5][6]);
    assertEquals("Galaxy 5,7 mismatch 0!=" + this.game.getGalaxy()[5][7], 0, this.game.getGalaxy()[5][7]);
    assertEquals("Galaxy 5,8 mismatch 0!=" + this.game.getGalaxy()[5][8], 0, this.game.getGalaxy()[5][8]);
    assertEquals("Galaxy 6,1 mismatch 0!=" + this.game.getGalaxy()[6][1], 0, this.game.getGalaxy()[6][1]);
    assertEquals("Galaxy 6,2 mismatch 0!=" + this.game.getGalaxy()[6][2], 0, this.game.getGalaxy()[6][2]);
    assertEquals("Galaxy 6,3 mismatch 0!=" + this.game.getGalaxy()[6][3], 0, this.game.getGalaxy()[6][3]);
    assertEquals("Galaxy 6,4 mismatch 0!=" + this.game.getGalaxy()[6][4], 0, this.game.getGalaxy()[6][4]);
    assertEquals("Galaxy 6,5 mismatch 0!=" + this.game.getGalaxy()[6][5], 0, this.game.getGalaxy()[6][5]);
    assertEquals("Galaxy 6,6 mismatch 0!=" + this.game.getGalaxy()[6][6], 0, this.game.getGalaxy()[6][6]);
    assertEquals("Galaxy 6,7 mismatch 0!=" + this.game.getGalaxy()[6][7], 0, this.game.getGalaxy()[6][7]);
    assertEquals("Galaxy 6,8 mismatch 0!=" + this.game.getGalaxy()[6][8], 0, this.game.getGalaxy()[6][8]);
    assertEquals("Galaxy 7,1 mismatch 0!=" + this.game.getGalaxy()[7][1], 0, this.game.getGalaxy()[7][1]);
    assertEquals("Galaxy 7,2 mismatch 0!=" + this.game.getGalaxy()[7][2], 0, this.game.getGalaxy()[7][2]);
    assertEquals("Galaxy 7,3 mismatch 0!=" + this.game.getGalaxy()[7][3], 0, this.game.getGalaxy()[7][3]);
    assertEquals("Galaxy 7,4 mismatch 0!=" + this.game.getGalaxy()[7][4], 0, this.game.getGalaxy()[7][4]);
    assertEquals("Galaxy 7,5 mismatch 0!=" + this.game.getGalaxy()[7][5], 0, this.game.getGalaxy()[7][5]);
    assertEquals("Galaxy 7,6 mismatch 0!=" + this.game.getGalaxy()[7][6], 0, this.game.getGalaxy()[7][6]);
    assertEquals("Galaxy 7,7 mismatch 0!=" + this.game.getGalaxy()[7][7], 0, this.game.getGalaxy()[7][7]);
    assertEquals("Galaxy 7,8 mismatch 0!=" + this.game.getGalaxy()[7][8], 0, this.game.getGalaxy()[7][8]);
    assertEquals("Galaxy 8,1 mismatch 0!=" + this.game.getGalaxy()[8][1], 0, this.game.getGalaxy()[8][1]);
    assertEquals("Galaxy 8,2 mismatch 0!=" + this.game.getGalaxy()[8][2], 0, this.game.getGalaxy()[8][2]);
    assertEquals("Galaxy 8,3 mismatch 0!=" + this.game.getGalaxy()[8][3], 0, this.game.getGalaxy()[8][3]);
    assertEquals("Galaxy 8,4 mismatch 0!=" + this.game.getGalaxy()[8][4], 0, this.game.getGalaxy()[8][4]);
    assertEquals("Galaxy 8,5 mismatch 0!=" + this.game.getGalaxy()[8][5], 0, this.game.getGalaxy()[8][5]);
    assertEquals("Galaxy 8,6 mismatch 0!=" + this.game.getGalaxy()[8][6], 0, this.game.getGalaxy()[8][6]);
    assertEquals("Galaxy 8,7 mismatch 0!=" + this.game.getGalaxy()[8][7], 0, this.game.getGalaxy()[8][7]);
    assertEquals("Galaxy 8,8 mismatch 0!=" + this.game.getGalaxy()[8][8], 0, this.game.getGalaxy()[8][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Damage Control report:, was:" + logOut[0], "Damage Control report:", logOut[0]);
    assertEquals("Log expecting:    Library-Computer repair completed, was:" + logOut[1], "    Library-Computer repair completed", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testDamageControl()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 300", "nav 1 1", "pha 300", "dam", "nav 1 .4", "pha 200", "nav 3 1", "shi 400", "pha 300", "pha 100", "pha 10", "lrs", "srs", "nav 1 2", "pha 100", "dam", "pha 100", "pha 22" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[3,6  * , 6,3 <0>]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 1449, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 247, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 12, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 28, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "-0.2", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=1,y=8]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 1,2 mismatch 0!=" + this.game.getGalaxy()[1][2], 0, this.game.getGalaxy()[1][2]);
    assertEquals("Galaxy 1,3 mismatch 0!=" + this.game.getGalaxy()[1][3], 0, this.game.getGalaxy()[1][3]);
    assertEquals("Galaxy 1,4 mismatch 0!=" + this.game.getGalaxy()[1][4], 0, this.game.getGalaxy()[1][4]);
    assertEquals("Galaxy 1,5 mismatch 3!=" + this.game.getGalaxy()[1][5], 3, this.game.getGalaxy()[1][5]);
    assertEquals("Galaxy 1,6 mismatch 3!=" + this.game.getGalaxy()[1][6], 3, this.game.getGalaxy()[1][6]);
    assertEquals("Galaxy 1,7 mismatch 1!=" + this.game.getGalaxy()[1][7], 1, this.game.getGalaxy()[1][7]);
    assertEquals("Galaxy 1,8 mismatch 1!=" + this.game.getGalaxy()[1][8], 1, this.game.getGalaxy()[1][8]);
    assertEquals("Galaxy 2,1 mismatch 0!=" + this.game.getGalaxy()[2][1], 0, this.game.getGalaxy()[2][1]);
    assertEquals("Galaxy 2,2 mismatch 0!=" + this.game.getGalaxy()[2][2], 0, this.game.getGalaxy()[2][2]);
    assertEquals("Galaxy 2,3 mismatch 0!=" + this.game.getGalaxy()[2][3], 0, this.game.getGalaxy()[2][3]);
    assertEquals("Galaxy 2,4 mismatch 0!=" + this.game.getGalaxy()[2][4], 0, this.game.getGalaxy()[2][4]);
    assertEquals("Galaxy 2,5 mismatch 6!=" + this.game.getGalaxy()[2][5], 6, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 5!=" + this.game.getGalaxy()[2][6], 5, this.game.getGalaxy()[2][6]);
    assertEquals("Galaxy 2,7 mismatch 7!=" + this.game.getGalaxy()[2][7], 7, this.game.getGalaxy()[2][7]);
    assertEquals("Galaxy 2,8 mismatch 0!=" + this.game.getGalaxy()[2][8], 0, this.game.getGalaxy()[2][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:7 unit hit on Klingon at sector 7, 8, was:" + logOut[0], "7 unit hit on Klingon at sector 7, 8", logOut[0]);
    assertEquals("Log expecting:*** Klingon Destroyed ***, was:" + logOut[1], "*** Klingon Destroyed ***", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testDeathByKlingons()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "nav 1 1", "nav 3 .1" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,5  * , 2,5  * , 3,1  * , 4,1  * , 6,3 <0>, 8,3  * , 8,7 +K+]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2853, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", -111, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 32, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=6]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 2,5 mismatch 6!=" + this.game.getGalaxy()[2][5], 6, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 105!=" + this.game.getGalaxy()[2][6], 105, this.game.getGalaxy()[2][6]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:111 unit hit on Enterprise from sector 8, 7, was:" + logOut[0], "111 unit hit on Enterprise from sector 8, 7", logOut[0]);
    assertEquals("Log expecting:, was:" + logOut[1], "", logOut[1]);
    assertEquals("Log expecting:The Enterprise has been destroyed. , was:" + logOut[2], "The Enterprise has been destroyed. ", logOut[2]);
    assertEquals("Log expecting:The Federation will be conquered., was:" + logOut[3], "The Federation will be conquered.", logOut[3]);
    assertEquals("Log expecting:It is stardate 2102., was:" + logOut[4], "It is stardate 2102.", logOut[4]);
    assertEquals("Log expecting:, was:" + logOut[5], "", logOut[5]);
    assertEquals("Log expecting:There were 15 Klingon Battlecruisers left at the, was:" + logOut[6], "There were 15 Klingon Battlecruisers left at the", logOut[6]);
    assertEquals("Log expecting: end of your mission., was:" + logOut[7], " end of your mission.", logOut[7]);
    assertEquals("Wrong log length, 8 != " + logOut.length, 8, logOut.length);
    
    this.success = true;
  }
  
  public void testDestroyStarbases()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 1300", "nav 7 1", "nav 1 3.7", "tor 6.6", "nav 7 2", "nav 5 4", "tor 5" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,2  * , 2,8  * , 3,6  * , 3,7  * , 4,1  * , 4,3  * , 6,8 <0>, 7,7  * , 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2658, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 1214, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 8, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 26, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.2", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=5,y=4]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=8]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 2,4 mismatch 0!=" + this.game.getGalaxy()[2][4], 0, this.game.getGalaxy()[2][4]);
    assertEquals("Galaxy 2,5 mismatch 6!=" + this.game.getGalaxy()[2][5], 6, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 0!=" + this.game.getGalaxy()[2][6], 0, this.game.getGalaxy()[2][6]);
    assertEquals("Galaxy 3,3 mismatch 0!=" + this.game.getGalaxy()[3][3], 0, this.game.getGalaxy()[3][3]);
    assertEquals("Galaxy 3,4 mismatch 0!=" + this.game.getGalaxy()[3][4], 0, this.game.getGalaxy()[3][4]);
    assertEquals("Galaxy 3,5 mismatch 4!=" + this.game.getGalaxy()[3][5], 4, this.game.getGalaxy()[3][5]);
    assertEquals("Galaxy 3,6 mismatch 0!=" + this.game.getGalaxy()[3][6], 0, this.game.getGalaxy()[3][6]);
    assertEquals("Galaxy 3,7 mismatch 0!=" + this.game.getGalaxy()[3][7], 0, this.game.getGalaxy()[3][7]);
    assertEquals("Galaxy 3,8 mismatch 105!=" + this.game.getGalaxy()[3][8], 105, this.game.getGalaxy()[3][8]);
    assertEquals("Galaxy 4,1 mismatch 0!=" + this.game.getGalaxy()[4][1], 0, this.game.getGalaxy()[4][1]);
    assertEquals("Galaxy 4,2 mismatch 0!=" + this.game.getGalaxy()[4][2], 0, this.game.getGalaxy()[4][2]);
    assertEquals("Galaxy 5,4 mismatch 8!=" + this.game.getGalaxy()[5][4], 8, this.game.getGalaxy()[5][4]);
    assertEquals("Galaxy 5,5 mismatch 0!=" + this.game.getGalaxy()[5][5], 0, this.game.getGalaxy()[5][5]);
    assertEquals("Galaxy 5,6 mismatch 0!=" + this.game.getGalaxy()[5][6], 0, this.game.getGalaxy()[5][6]);
    assertEquals("Galaxy 5,7 mismatch 0!=" + this.game.getGalaxy()[5][7], 0, this.game.getGalaxy()[5][7]);
    assertEquals("Galaxy 5,8 mismatch 3!=" + this.game.getGalaxy()[5][8], 3, this.game.getGalaxy()[5][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Torpedo Track:, was:" + logOut[0], "Torpedo Track:", logOut[0]);
    assertEquals("Log expecting:    6, 7, was:" + logOut[1], "    6, 7", logOut[1]);
    assertEquals("Log expecting:    6, 6, was:" + logOut[2], "    6, 6", logOut[2]);
    assertEquals("Log expecting:*** Starbase Destroyed ***, was:" + logOut[3], "*** Starbase Destroyed ***", logOut[3]);
    assertEquals("Log expecting:That does it, Captain!!  , was:" + logOut[4], "That does it, Captain!!  ", logOut[4]);
    assertEquals("Log expecting:You are hereby relieved of command, was:" + logOut[5], "You are hereby relieved of command", logOut[5]);
    assertEquals("Log expecting:and sentenced to 99 stardates of hardlabor on Cygnus 12!!, was:" + logOut[6], "and sentenced to 99 stardates of hardlabor on Cygnus 12!!", logOut[6]);
    assertEquals("Log expecting:There were 15 Klingon Battlecruisers left at the, was:" + logOut[7], "There were 15 Klingon Battlecruisers left at the", logOut[7]);
    assertEquals("Log expecting: end of your mission., was:" + logOut[8], " end of your mission.", logOut[8]);
    assertEquals("Wrong log length, 9 != " + logOut.length, 9, logOut.length);
    
    this.success = true;
  }
  
  public void testExpendsTorpedoes()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "tor 5", "tor 5", "tor 5", "tor 5", "tor 5", "tor 5", "tor 5", "tor 5", "tor 5", "tor 5", "tor 5" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,3 <0>, 7,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2980, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 0, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 34, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=5]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:All photon torpedoes expended, was:" + logOut[0], "All photon torpedoes expended", logOut[0]);
    assertEquals("Wrong log length, 1 != " + logOut.length, 1, logOut.length);
    
    this.success = true;
  }
  
  public void testPhaserControl()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 1000", "nav 1 1", "srs", "pha -1", "pha a", "pha 2001", "pha 300", "srs", "pha 500" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,5  * , 2,5  * , 3,1  * , 4,1  * , 4,2 +K+, 6,3 <0>, 8,3  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2585, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 921, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 32, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "-0.4", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=6]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Phasers Inoperative, was:" + logOut[0], "Phasers Inoperative", logOut[0]);
    assertEquals("Wrong log length, 1 != " + logOut.length, 1, logOut.length);
    
    this.success = true;
  }
  
  public void testQuitGame()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "lrs", "q", "shi 1", "q" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,3 <0>, 7,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 3000, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 34, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=5]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 1,2 mismatch 0!=" + this.game.getGalaxy()[1][2], 0, this.game.getGalaxy()[1][2]);
    assertEquals("Galaxy 1,3 mismatch 0!=" + this.game.getGalaxy()[1][3], 0, this.game.getGalaxy()[1][3]);
    assertEquals("Galaxy 1,4 mismatch 1!=" + this.game.getGalaxy()[1][4], 1, this.game.getGalaxy()[1][4]);
    assertEquals("Galaxy 1,5 mismatch 3!=" + this.game.getGalaxy()[1][5], 3, this.game.getGalaxy()[1][5]);
    assertEquals("Galaxy 1,6 mismatch 103!=" + this.game.getGalaxy()[1][6], 103, this.game.getGalaxy()[1][6]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:, was:" + logOut[0], "", logOut[0]);
    assertEquals("Wrong log length, 1 != " + logOut.length, 1, logOut.length);
    
    this.success = true;
  }
  
  public void testUndockProperly()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "nav 7 1", "nav 1 3.5", "shi 1000", "nav 7 .1", "nav 5 5", "shi 1000", "nav 5 .1" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,8 +K+, 2,4  * , 3,7  * , 6,3  * , 6,4  * , 7,1  * , 7,6 <0>, 7,8  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2794, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 905, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 28, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "3.5", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "-0.2", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=3,y=3]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=7,y=6]", sLoc);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:95 unit hit on Enterprise from sector 1, 8, was:" + logOut[0], "95 unit hit on Enterprise from sector 1, 8", logOut[0]);
    assertEquals("Log expecting:    <Shields down to 905 units>, was:" + logOut[1], "    <Shields down to 905 units>", logOut[1]);
    assertEquals("Log expecting:Damage Control reports, was:" + logOut[2], "Damage Control reports", logOut[2]);
    assertEquals("Log expecting:   'Damage Control' damaged by hit, was:" + logOut[3], "   'Damage Control' damaged by hit", logOut[3]);
    assertEquals("Wrong log length, 4 != " + logOut.length, 4, logOut.length);
    
    this.success = true;
  }
  
  public void testDamageInflict()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "nav 5 .1", "nav 7 .1", "nav 3 .1", "nav 7 .1", "nav 3 .1", "nav 7 .1", "nav 3 .1", "nav 7 .1", "nav 3 .1" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,2 <0>, 7,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2901, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 33, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "-1.6", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "2.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=5]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=2]", sLoc);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Damage Control report:, was:" + logOut[0], "Damage Control report:", logOut[0]);
    assertEquals("Log expecting:    Photon Tubes damaged, was:" + logOut[1], "    Photon Tubes damaged", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testDamageHeal()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "nav 5 .1", "nav 7 .1", "nav 3 .1", "nav 7 .1", "nav 3 .1", "nav 7 .1", "nav 3 .1" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,2 <0>, 7,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2923, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 33, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "2.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=5]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=2]", sLoc);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Damage Control report:, was:" + logOut[0], "Damage Control report:", logOut[0]);
    assertEquals("Log expecting:    Library-Computer state of repair improved, was:" + logOut[1], "    Library-Computer state of repair improved", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testDirDistCalc()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "com 4 1 2 3 4" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,3 <0>, 7,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:  DIRECTION = 8.00, was:" + logOut[0], "  DIRECTION = 8.00", logOut[0]);
    assertEquals("Log expecting:  DISTANCE = 2.00, was:" + logOut[1], "  DISTANCE = 2.00", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testSub2()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "com 4 1 2 3 4", "com 4 1 2 4 3" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,3 <0>, 7,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=5]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:  DIRECTION = 7.33, was:" + logOut[0], "  DIRECTION = 7.33", logOut[0]);
    assertEquals("Log expecting:  DISTANCE = 3.00, was:" + logOut[1], "  DISTANCE = 3.00", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testSub1()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "com 4 8 7 6 5", "com 4 8 7 1 4" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,3  * , 3,1  * , 4,4  * , 5,6  * , 6,3 <0>, 7,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:  DIRECTION = 3.43, was:" + logOut[0], "  DIRECTION = 3.43", logOut[0]);
    assertEquals("Log expecting:  DISTANCE = 7.00, was:" + logOut[1], "  DISTANCE = 7.00", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testRepairCrew()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "nav 7 3", "shi 500", "pha 500", "shi", "pha 500", "nav 1 .2", "nav 7 1", "pha 300", "dam", "pha 400", "nav 4 1", "nav 8 .1", "dam" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,2  * , 2,8  * , 3,6  * , 4,1  * , 5,1  * , 5,5  * , 6,3  * , 7,6 <0>, 7,7 >!<, 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 3000, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 12, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 27, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "3.8", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.DOCKED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=5,y=4]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=7,y=6]", sLoc);
    assertEquals("Galaxy 5,3 mismatch 0!=" + this.game.getGalaxy()[5][3], 0, this.game.getGalaxy()[5][3]);
    assertEquals("Galaxy 5,4 mismatch 18!=" + this.game.getGalaxy()[5][4], 18, this.game.getGalaxy()[5][4]);
    assertEquals("Galaxy 5,5 mismatch 8!=" + this.game.getGalaxy()[5][5], 8, this.game.getGalaxy()[5][5]);
    assertEquals("Galaxy 5,6 mismatch 0!=" + this.game.getGalaxy()[5][6], 0, this.game.getGalaxy()[5][6]);
    assertEquals("Galaxy 5,7 mismatch 0!=" + this.game.getGalaxy()[5][7], 0, this.game.getGalaxy()[5][7]);
    assertEquals("Galaxy 6,4 mismatch 0!=" + this.game.getGalaxy()[6][4], 0, this.game.getGalaxy()[6][4]);
    assertEquals("Galaxy 6,5 mismatch 3!=" + this.game.getGalaxy()[6][5], 3, this.game.getGalaxy()[6][5]);
    assertEquals("Galaxy 6,6 mismatch 0!=" + this.game.getGalaxy()[6][6], 0, this.game.getGalaxy()[6][6]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Repairs completed, 0.1112 stardates elapsed., was:" + logOut[0], "Repairs completed, 0.1112 stardates elapsed.", logOut[0]);
    assertEquals("Wrong log length, 1 != " + logOut.length, 1, logOut.length);
    
    this.success = true;
  }
  
  public void testLibComp1()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 500", "nav 1 1", "pha 500", "nav 1 2", "lrs", "nav 7 1", "com 1" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,5 >!<, 3,6  * , 3,7  * , 4,1  * , 5,5 +K+, 6,3 <0>, 6,6  * , 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2376, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 500, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 14, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 28, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "-4.1", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=3,y=8]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    assertEquals("Galaxy 1,7 mismatch 1!=" + this.game.getGalaxy()[1][7], 1, this.game.getGalaxy()[1][7]);
    assertEquals("Galaxy 1,8 mismatch 101!=" + this.game.getGalaxy()[1][8], 101, this.game.getGalaxy()[1][8]);
    assertEquals("Galaxy 2,1 mismatch 0!=" + this.game.getGalaxy()[2][1], 0, this.game.getGalaxy()[2][1]);
    assertEquals("Galaxy 3,7 mismatch 3!=" + this.game.getGalaxy()[3][7], 3, this.game.getGalaxy()[3][7]);
    assertEquals("Galaxy 3,8 mismatch 115!=" + this.game.getGalaxy()[3][8], 115, this.game.getGalaxy()[3][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:   Status Report:, was:" + logOut[0], "   Status Report:", logOut[0]);
    assertEquals("Log expecting:Klingons Left: 14, was:" + logOut[1], "Klingons Left: 14", logOut[1]);
    assertEquals("Log expecting:Mission must be completed in 28.0 stardates, was:" + logOut[2], "Mission must be completed in 28.0 stardates", logOut[2]);
    assertEquals("Log expecting:The Federation is maintaining 2 starbases in the galaxy, was:" + logOut[3], "The Federation is maintaining 2 starbases in the galaxy", logOut[3]);
    assertEquals("Wrong log length, 4 != " + logOut.length, 4, logOut.length);
    
    this.success = true;
  }
  
  public void testLibComp2()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 800", "nav 7 1", "nav 1 4", "lrs", "com 2" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[3,2  * , 4,3  * , 4,6  * , 5,3 +K+, 6,3  * , 6,4  * , 6,8 <0>, 8,7 >!<]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2880, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 800, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 30, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=3,y=8]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=8]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:From Enterprise to Klingon battlecruiser:, was:" + logOut[0], "From Enterprise to Klingon battlecruiser:", logOut[0]);
    assertEquals("Log expecting:, was:" + logOut[1], "", logOut[1]);
    assertEquals("Log expecting:  DIRECTION = 4.80, was:" + logOut[2], "  DIRECTION = 4.80", logOut[2]);
    assertEquals("Log expecting:  DISTANCE = 5.00, was:" + logOut[3], "  DISTANCE = 5.00", logOut[3]);
    assertEquals("Wrong log length, 4 != " + logOut.length, 4, logOut.length);
    
    this.success = true;
  }
  
  public void testLibComp3()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 500", "nav 7 1", "nav 1 4", "com 3" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[3,2  * , 4,3  * , 4,6  * , 5,3 +K+, 6,3  * , 6,4  * , 6,8 <0>, 8,7 >!<]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2880, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 500, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 30, ((Integer)this.game.getIndicators().get(4)).intValue());
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:  DIRECTION = 6.50, was:" + logOut[0], "  DIRECTION = 6.50", logOut[0]);
    assertEquals("Log expecting:  DISTANCE = 2.00, was:" + logOut[1], "  DISTANCE = 2.00", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testLibComp5()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "com 5" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:                   The Galaxy, was:" + logOut[0], "                   The Galaxy", logOut[0]);
    assertEquals("Log expecting:    1     2     3     4     5     6     7     8, was:" + logOut[1], "    1     2     3     4     5     6     7     8", logOut[1]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[2], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[2]);
    assertEquals("Log expecting:1         Antares                  Sirius, was:" + logOut[3], "1         Antares                  Sirius", logOut[3]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[4], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[4]);
    assertEquals("Log expecting:2          Rigel                    Deneb, was:" + logOut[5], "2          Rigel                    Deneb", logOut[5]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[6], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[6]);
    assertEquals("Log expecting:3         Procyon                  Capella, was:" + logOut[7], "3         Procyon                  Capella", logOut[7]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[8], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[8]);
    assertEquals("Log expecting:4          Vega                Betelgeuse, was:" + logOut[9], "4          Vega                Betelgeuse", logOut[9]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[10], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[10]);
    assertEquals("Log expecting:5         Canopus                 Aldebaran, was:" + logOut[11], "5         Canopus                 Aldebaran", logOut[11]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[12], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[12]);
    assertEquals("Log expecting:6         Altair                 Regulus, was:" + logOut[13], "6         Altair                 Regulus", logOut[13]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[14], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[14]);
    assertEquals("Log expecting:7       Sagittarius               Arcturus, was:" + logOut[15], "7       Sagittarius               Arcturus", logOut[15]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[16], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[16]);
    assertEquals("Log expecting:8         Pollux                  Spica, was:" + logOut[17], "8         Pollux                  Spica", logOut[17]);
    assertEquals("Log expecting:  ----- ----- ----- ----- ----- ----- ----- -----, was:" + logOut[18], "  ----- ----- ----- ----- ----- ----- ----- -----", logOut[18]);
    assertEquals("Wrong log length, 19 != " + logOut.length, 19, logOut.length);
    
    this.success = true;
  }
  
  public void testOutOfTimeEnteringQuadrant()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 500", "lrs", "nav 1 1", "pha 500", "nav 3 1", "pha 400", "pha 200", "lrs", "nav 7 1", "nav 1 1", "lrs", "nav 3 1", "nav 1 1", "pha 450", "nav 40", "pha 40", "nav 7 2", "tor 3", "nav 3 .5", "dam", "nav 5 6", "lrs", "shi 500", "bad", "srs", "com", "com 1", "nav 3 .6", "com 2", "tor 7", "lrs", "nav 4 1", "com 2", "tor 7.6", "nav 8 2", "com 2", "tor 3.5", "shi 500", "tor 3.3", "nav 7 2", "nav 7 .2", "nav 7 .2", "nav 7 .2", "nav 7 .2", "nav 7 .2", "nav 7 .2", "nav 7 .4", "nav 7 .2", "tor 7", "lrs", "nav 1 1", "nav 1 .2", "nav 1 .2", "nav 1 .2", "com 3", "nav 1 .2", "dam", "nav 1 3", "lrs", "shi 600", "nav 5 1", "nav 7 2", "lrs", "nav 4 .5" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,2 +K+, 1,4  * , 2,2  * , 5,7 <0>, 7,4  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2830, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 600, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 7, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 0, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.1", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "-1.3", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "3.5", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "2.2", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=6,y=5]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=5,y=7]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 5!=" + this.game.getGalaxy()[1][1], 5, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 1,2 mismatch 3!=" + this.game.getGalaxy()[1][2], 3, this.game.getGalaxy()[1][2]);
    assertEquals("Galaxy 1,3 mismatch 8!=" + this.game.getGalaxy()[1][3], 8, this.game.getGalaxy()[1][3]);
    assertEquals("Galaxy 1,4 mismatch 1!=" + this.game.getGalaxy()[1][4], 1, this.game.getGalaxy()[1][4]);
    assertEquals("Galaxy 1,5 mismatch 3!=" + this.game.getGalaxy()[1][5], 3, this.game.getGalaxy()[1][5]);
    assertEquals("Galaxy 1,6 mismatch 3!=" + this.game.getGalaxy()[1][6], 3, this.game.getGalaxy()[1][6]);
    assertEquals("Galaxy 1,7 mismatch 1!=" + this.game.getGalaxy()[1][7], 1, this.game.getGalaxy()[1][7]);
    assertEquals("Galaxy 1,8 mismatch 1!=" + this.game.getGalaxy()[1][8], 1, this.game.getGalaxy()[1][8]);
    assertEquals("Galaxy 2,1 mismatch 1!=" + this.game.getGalaxy()[2][1], 1, this.game.getGalaxy()[2][1]);
    assertEquals("Galaxy 2,2 mismatch 3!=" + this.game.getGalaxy()[2][2], 3, this.game.getGalaxy()[2][2]);
    assertEquals("Galaxy 2,3 mismatch 7!=" + this.game.getGalaxy()[2][3], 7, this.game.getGalaxy()[2][3]);
    assertEquals("Galaxy 2,4 mismatch 6!=" + this.game.getGalaxy()[2][4], 6, this.game.getGalaxy()[2][4]);
    assertEquals("Galaxy 2,5 mismatch 6!=" + this.game.getGalaxy()[2][5], 6, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 5!=" + this.game.getGalaxy()[2][6], 5, this.game.getGalaxy()[2][6]);
    assertEquals("Galaxy 2,7 mismatch 7!=" + this.game.getGalaxy()[2][7], 7, this.game.getGalaxy()[2][7]);
    assertEquals("Galaxy 2,8 mismatch 6!=" + this.game.getGalaxy()[2][8], 6, this.game.getGalaxy()[2][8]);
    assertEquals("Galaxy 3,1 mismatch 2!=" + this.game.getGalaxy()[3][1], 2, this.game.getGalaxy()[3][1]);
    assertEquals("Galaxy 3,2 mismatch 2!=" + this.game.getGalaxy()[3][2], 2, this.game.getGalaxy()[3][2]);
    assertEquals("Galaxy 3,3 mismatch 6!=" + this.game.getGalaxy()[3][3], 6, this.game.getGalaxy()[3][3]);
    assertEquals("Galaxy 3,4 mismatch 1!=" + this.game.getGalaxy()[3][4], 1, this.game.getGalaxy()[3][4]);
    assertEquals("Galaxy 3,5 mismatch 4!=" + this.game.getGalaxy()[3][5], 4, this.game.getGalaxy()[3][5]);
    assertEquals("Galaxy 3,6 mismatch 6!=" + this.game.getGalaxy()[3][6], 6, this.game.getGalaxy()[3][6]);
    assertEquals("Galaxy 3,7 mismatch 3!=" + this.game.getGalaxy()[3][7], 3, this.game.getGalaxy()[3][7]);
    assertEquals("Galaxy 3,8 mismatch 15!=" + this.game.getGalaxy()[3][8], 15, this.game.getGalaxy()[3][8]);
    assertEquals("Galaxy 4,1 mismatch 8!=" + this.game.getGalaxy()[4][1], 8, this.game.getGalaxy()[4][1]);
    assertEquals("Galaxy 4,2 mismatch 5!=" + this.game.getGalaxy()[4][2], 5, this.game.getGalaxy()[4][2]);
    assertEquals("Galaxy 4,3 mismatch 2!=" + this.game.getGalaxy()[4][3], 2, this.game.getGalaxy()[4][3]);
    assertEquals("Galaxy 4,4 mismatch 2!=" + this.game.getGalaxy()[4][4], 2, this.game.getGalaxy()[4][4]);
    assertEquals("Galaxy 4,5 mismatch 0!=" + this.game.getGalaxy()[4][5], 0, this.game.getGalaxy()[4][5]);
    assertEquals("Galaxy 4,6 mismatch 3!=" + this.game.getGalaxy()[4][6], 3, this.game.getGalaxy()[4][6]);
    assertEquals("Galaxy 4,7 mismatch 6!=" + this.game.getGalaxy()[4][7], 6, this.game.getGalaxy()[4][7]);
    assertEquals("Galaxy 4,8 mismatch 1!=" + this.game.getGalaxy()[4][8], 1, this.game.getGalaxy()[4][8]);
    assertEquals("Galaxy 5,1 mismatch 0!=" + this.game.getGalaxy()[5][1], 0, this.game.getGalaxy()[5][1]);
    assertEquals("Galaxy 5,2 mismatch 5!=" + this.game.getGalaxy()[5][2], 5, this.game.getGalaxy()[5][2]);
    assertEquals("Galaxy 5,3 mismatch 7!=" + this.game.getGalaxy()[5][3], 7, this.game.getGalaxy()[5][3]);
    assertEquals("Galaxy 5,4 mismatch 18!=" + this.game.getGalaxy()[5][4], 18, this.game.getGalaxy()[5][4]);
    assertEquals("Galaxy 5,5 mismatch 0!=" + this.game.getGalaxy()[5][5], 0, this.game.getGalaxy()[5][5]);
    assertEquals("Galaxy 5,6 mismatch 2!=" + this.game.getGalaxy()[5][6], 2, this.game.getGalaxy()[5][6]);
    assertEquals("Galaxy 5,7 mismatch 6!=" + this.game.getGalaxy()[5][7], 6, this.game.getGalaxy()[5][7]);
    assertEquals("Galaxy 5,8 mismatch 3!=" + this.game.getGalaxy()[5][8], 3, this.game.getGalaxy()[5][8]);
    assertEquals("Galaxy 6,1 mismatch 0!=" + this.game.getGalaxy()[6][1], 0, this.game.getGalaxy()[6][1]);
    assertEquals("Galaxy 6,2 mismatch 1!=" + this.game.getGalaxy()[6][2], 1, this.game.getGalaxy()[6][2]);
    assertEquals("Galaxy 6,3 mismatch 5!=" + this.game.getGalaxy()[6][3], 5, this.game.getGalaxy()[6][3]);
    assertEquals("Galaxy 6,4 mismatch 5!=" + this.game.getGalaxy()[6][4], 5, this.game.getGalaxy()[6][4]);
    assertEquals("Galaxy 6,5 mismatch 103!=" + this.game.getGalaxy()[6][5], 103, this.game.getGalaxy()[6][5]);
    assertEquals("Galaxy 6,6 mismatch 7!=" + this.game.getGalaxy()[6][6], 7, this.game.getGalaxy()[6][6]);
    assertEquals("Galaxy 6,7 mismatch 1!=" + this.game.getGalaxy()[6][7], 1, this.game.getGalaxy()[6][7]);
    assertEquals("Galaxy 6,8 mismatch 1!=" + this.game.getGalaxy()[6][8], 1, this.game.getGalaxy()[6][8]);
    assertEquals("Galaxy 7,1 mismatch 0!=" + this.game.getGalaxy()[7][1], 0, this.game.getGalaxy()[7][1]);
    assertEquals("Galaxy 7,2 mismatch 0!=" + this.game.getGalaxy()[7][2], 0, this.game.getGalaxy()[7][2]);
    assertEquals("Galaxy 7,3 mismatch 0!=" + this.game.getGalaxy()[7][3], 0, this.game.getGalaxy()[7][3]);
    assertEquals("Galaxy 7,4 mismatch 0!=" + this.game.getGalaxy()[7][4], 0, this.game.getGalaxy()[7][4]);
    assertEquals("Galaxy 7,5 mismatch 1!=" + this.game.getGalaxy()[7][5], 1, this.game.getGalaxy()[7][5]);
    assertEquals("Galaxy 7,6 mismatch 2!=" + this.game.getGalaxy()[7][6], 2, this.game.getGalaxy()[7][6]);
    assertEquals("Galaxy 7,7 mismatch 1!=" + this.game.getGalaxy()[7][7], 1, this.game.getGalaxy()[7][7]);
    assertEquals("Galaxy 7,8 mismatch 0!=" + this.game.getGalaxy()[7][8], 0, this.game.getGalaxy()[7][8]);
    assertEquals("Galaxy 8,1 mismatch 0!=" + this.game.getGalaxy()[8][1], 0, this.game.getGalaxy()[8][1]);
    assertEquals("Galaxy 8,2 mismatch 0!=" + this.game.getGalaxy()[8][2], 0, this.game.getGalaxy()[8][2]);
    assertEquals("Galaxy 8,3 mismatch 0!=" + this.game.getGalaxy()[8][3], 0, this.game.getGalaxy()[8][3]);
    assertEquals("Galaxy 8,4 mismatch 0!=" + this.game.getGalaxy()[8][4], 0, this.game.getGalaxy()[8][4]);
    assertEquals("Galaxy 8,5 mismatch 108!=" + this.game.getGalaxy()[8][5], 108, this.game.getGalaxy()[8][5]);
    assertEquals("Galaxy 8,6 mismatch 8!=" + this.game.getGalaxy()[8][6], 8, this.game.getGalaxy()[8][6]);
    assertEquals("Galaxy 8,7 mismatch 4!=" + this.game.getGalaxy()[8][7], 4, this.game.getGalaxy()[8][7]);
    assertEquals("Galaxy 8,8 mismatch 0!=" + this.game.getGalaxy()[8][8], 0, this.game.getGalaxy()[8][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Now entering Regulus quadrant..., was:" + logOut[0], "Now entering Regulus quadrant...", logOut[0]);
    assertEquals("Log expecting:Combat Area  Condition Red, was:" + logOut[1], "Combat Area  Condition Red", logOut[1]);
    assertEquals("Log expecting:You ran out of time to complete your mission., was:" + logOut[2], "You ran out of time to complete your mission.", logOut[2]);
    assertEquals("Log expecting:It is stardate 2134., was:" + logOut[3], "It is stardate 2134.", logOut[3]);
    assertEquals("Log expecting:, was:" + logOut[4], "", logOut[4]);
    assertEquals("Log expecting:There were 7 Klingon Battlecruisers left at the, was:" + logOut[5], "There were 7 Klingon Battlecruisers left at the", logOut[5]);
    assertEquals("Log expecting: end of your mission., was:" + logOut[6], " end of your mission.", logOut[6]);
    assertEquals("Wrong log length, 7 != " + logOut.length, 7, logOut.length);
    
    this.success = true;
  }
  
  public void testShieldControl()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi -1", "shi 4000", "shi bad input", "shi 400", "nav 1 1", "pha 50" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,5  * , 2,5  * , 3,1  * , 4,1  * , 4,2 +K+, 6,3 <0>, 8,3  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2698, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 184, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 32, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "-1.4", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Sensors show no damage to enemy at 4, 2, was:" + logOut[0], "Sensors show no damage to enemy at 4, 2", logOut[0]);
    assertEquals("Log expecting:216 unit hit on Enterprise from sector 4, 2, was:" + logOut[1], "216 unit hit on Enterprise from sector 4, 2", logOut[1]);
    assertEquals("Log expecting:    <Shields down to 184 units>, was:" + logOut[2], "    <Shields down to 184 units>", logOut[2]);
    assertEquals("Log expecting:Damage Control reports, was:" + logOut[3], "Damage Control reports", logOut[3]);
    assertEquals("Log expecting:   'Phaser Control' damaged by hit, was:" + logOut[4], "   'Phaser Control' damaged by hit", logOut[4]);
    assertEquals("Wrong log length, 5 != " + logOut.length, 5, logOut.length);
    
    this.success = true;
  }
  
  public void testNewQuadrantAfterLimit()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "nav 7 1", "nav 1 4", "lrs" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[3,2  * , 4,3  * , 4,6  * , 5,3 +K+, 6,3  * , 6,4  * , 6,8 <0>, 8,7 >!<]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2880, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 30, ((Integer)this.game.getIndicators().get(4)).intValue());
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting::   7 :   6 : *** :, was:" + logOut[0], ":   7 :   6 : *** :", logOut[0]);
    assertEquals("Log expecting::   3 : 115 : *** :, was:" + logOut[1], ":   3 : 115 : *** :", logOut[1]);
    assertEquals("Log expecting::   6 :   1 : *** :, was:" + logOut[2], ":   6 :   1 : *** :", logOut[2]);
    assertEquals("Wrong log length, 3 != " + logOut.length, 3, logOut.length);
    
    this.success = true;
  }
  
  public void testGalaxyLimit()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "nav 7 1", "nav 1 4" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:LT. Uhura reports:, was:" + logOut[0], "LT. Uhura reports:", logOut[0]);
    assertEquals("Log expecting:  Message from Starfleet Command:, was:" + logOut[1], "  Message from Starfleet Command:", logOut[1]);
    assertEquals("Log expecting:  Permission to attempt crossing of galactic perimeter, was:" + logOut[2], "  Permission to attempt crossing of galactic perimeter", logOut[2]);
    assertEquals("Log expecting:  is hereby *denied*. Shut down your engines., was:" + logOut[3], "  is hereby *denied*. Shut down your engines.", logOut[3]);
    assertEquals("Log expecting:Chief Engineer Scott reports:, was:" + logOut[4], "Chief Engineer Scott reports:", logOut[4]);
    assertEquals("Log expecting:  Warp Engines shut down at sector 6, 8 of quadrant 3, 8., was:" + logOut[5], "  Warp Engines shut down at sector 6, 8 of quadrant 3, 8.", logOut[5]);
    assertEquals("Log expecting:Now entering Capella quadrant..., was:" + logOut[6], "Now entering Capella quadrant...", logOut[6]);
    assertEquals("Log expecting:Combat Area  Condition Red, was:" + logOut[7], "Combat Area  Condition Red", logOut[7]);
    assertEquals("Log expecting:Shields Dangerously Low, was:" + logOut[8], "Shields Dangerously Low", logOut[8]);
    assertEquals("Wrong log length, 9 != " + logOut.length, 9, logOut.length);
    
    this.success = true;
  }
  
  public void testUnkillableKlingon()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 1111", "nav 1 1", "pha 1", "nav 1 .1", "nav 3 .1", "nav 1 .1", "nav 1 .1", "nav 5 .1", "pha 77" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,5  * , 2,5  * , 3,1  * , 4,1  * , 5,4 <0>, 8,3  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2474, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 754, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 14, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 31, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "3.5", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "2.9", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=6]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=5,y=4]", sLoc);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:32 unit hit on Klingon at sector 1, 3, was:" + logOut[0], "32 unit hit on Klingon at sector 1, 3", logOut[0]);
    assertEquals("Log expecting:*** Klingon Destroyed ***, was:" + logOut[1], "*** Klingon Destroyed ***", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testManeuverEnergy()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 1500", "nav 7 3", "pha 1400", "nav 1 1" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[3,6  * , 6,3 <0>, 7,8  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 1496, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 1496, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 13, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 30, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Alert level incorrect", AlertLevel.YELLOW, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=5,y=6]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Now entering Aldebaran quadrant..., was:" + logOut[0], "Now entering Aldebaran quadrant...", logOut[0]);
    assertEquals("Log expecting:Shield Control supplies energy to complete maneuver., was:" + logOut[1], "Shield Control supplies energy to complete maneuver.", logOut[1]);
    assertEquals("Wrong log length, 2 != " + logOut.length, 2, logOut.length);
    
    this.success = true;
  }
  
  public void testTorpedoMiss()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "tor 1" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Torpedo Track:, was:" + logOut[0], "Torpedo Track:", logOut[0]);
    assertEquals("Log expecting:    6, 4, was:" + logOut[1], "    6, 4", logOut[1]);
    assertEquals("Log expecting:    6, 5, was:" + logOut[2], "    6, 5", logOut[2]);
    assertEquals("Log expecting:    6, 6, was:" + logOut[3], "    6, 6", logOut[3]);
    assertEquals("Log expecting:    6, 7, was:" + logOut[4], "    6, 7", logOut[4]);
    assertEquals("Log expecting:    6, 8, was:" + logOut[5], "    6, 8", logOut[5]);
    assertEquals("Log expecting:Torpedo Missed, was:" + logOut[6], "Torpedo Missed", logOut[6]);
    assertEquals("Wrong log length, 7 != " + logOut.length, 7, logOut.length);
    
    this.success = true;
  }
  
  public void testOutOfTime()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 1000", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5", "nav 5 .5", "nav 1 .5" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,5  * , 3,6  * , 5,2  * , 5,7  * , 6,2  * , 6,3 <0>, 6,8  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2244, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 1000, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 0, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "1.2", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.3", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "3.2", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "3.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "2.8", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "2.2", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=2,y=4]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=6,y=3]", sLoc);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:, was:" + logOut[0], "", logOut[0]);
    assertEquals("Wrong log length, 1 != " + logOut.length, 1, logOut.length);
    
    this.success = true;
  }
  
  public void testExceedQuadrantLimits()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "shi 900", "lrs", "nav 1 1", "nav 1 4", "lrs", "nav 5 .1", "nav 7 7", "lrs", "nav 3 .1", "nav 5 8", "lrs", "nav 3 8" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,1 <0>, 2,8  * , 4,1  * , 5,5  * , 6,3  * , 7,1 +K+, 8,6  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2319, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 789, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 24, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "-1.7", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=1,y=1]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=1,y=1]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 105!=" + this.game.getGalaxy()[1][1], 105, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 1,2 mismatch 0!=" + this.game.getGalaxy()[1][2], 0, this.game.getGalaxy()[1][2]);
    assertEquals("Galaxy 1,3 mismatch 0!=" + this.game.getGalaxy()[1][3], 0, this.game.getGalaxy()[1][3]);
    assertEquals("Galaxy 1,4 mismatch 1!=" + this.game.getGalaxy()[1][4], 1, this.game.getGalaxy()[1][4]);
    assertEquals("Galaxy 1,5 mismatch 3!=" + this.game.getGalaxy()[1][5], 3, this.game.getGalaxy()[1][5]);
    assertEquals("Galaxy 1,6 mismatch 103!=" + this.game.getGalaxy()[1][6], 103, this.game.getGalaxy()[1][6]);
    assertEquals("Galaxy 1,7 mismatch 1!=" + this.game.getGalaxy()[1][7], 1, this.game.getGalaxy()[1][7]);
    assertEquals("Galaxy 1,8 mismatch 101!=" + this.game.getGalaxy()[1][8], 101, this.game.getGalaxy()[1][8]);
    assertEquals("Galaxy 2,1 mismatch 0!=" + this.game.getGalaxy()[2][1], 0, this.game.getGalaxy()[2][1]);
    assertEquals("Galaxy 2,2 mismatch 0!=" + this.game.getGalaxy()[2][2], 0, this.game.getGalaxy()[2][2]);
    assertEquals("Galaxy 2,3 mismatch 0!=" + this.game.getGalaxy()[2][3], 0, this.game.getGalaxy()[2][3]);
    assertEquals("Galaxy 2,4 mismatch 6!=" + this.game.getGalaxy()[2][4], 6, this.game.getGalaxy()[2][4]);
    assertEquals("Galaxy 2,5 mismatch 6!=" + this.game.getGalaxy()[2][5], 6, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 105!=" + this.game.getGalaxy()[2][6], 105, this.game.getGalaxy()[2][6]);
    assertEquals("Galaxy 2,7 mismatch 7!=" + this.game.getGalaxy()[2][7], 7, this.game.getGalaxy()[2][7]);
    assertEquals("Galaxy 2,8 mismatch 6!=" + this.game.getGalaxy()[2][8], 6, this.game.getGalaxy()[2][8]);
    assertEquals("Galaxy 3,1 mismatch 0!=" + this.game.getGalaxy()[3][1], 0, this.game.getGalaxy()[3][1]);
    assertEquals("Galaxy 3,2 mismatch 0!=" + this.game.getGalaxy()[3][2], 0, this.game.getGalaxy()[3][2]);
    assertEquals("Galaxy 3,3 mismatch 0!=" + this.game.getGalaxy()[3][3], 0, this.game.getGalaxy()[3][3]);
    assertEquals("Galaxy 3,4 mismatch 1!=" + this.game.getGalaxy()[3][4], 1, this.game.getGalaxy()[3][4]);
    assertEquals("Galaxy 3,5 mismatch 4!=" + this.game.getGalaxy()[3][5], 4, this.game.getGalaxy()[3][5]);
    assertEquals("Galaxy 3,6 mismatch 6!=" + this.game.getGalaxy()[3][6], 6, this.game.getGalaxy()[3][6]);
    assertEquals("Galaxy 3,7 mismatch 3!=" + this.game.getGalaxy()[3][7], 3, this.game.getGalaxy()[3][7]);
    assertEquals("Galaxy 3,8 mismatch 115!=" + this.game.getGalaxy()[3][8], 115, this.game.getGalaxy()[3][8]);
    assertEquals("Galaxy 4,1 mismatch 0!=" + this.game.getGalaxy()[4][1], 0, this.game.getGalaxy()[4][1]);
    assertEquals("Galaxy 4,2 mismatch 0!=" + this.game.getGalaxy()[4][2], 0, this.game.getGalaxy()[4][2]);
    assertEquals("Galaxy 4,3 mismatch 0!=" + this.game.getGalaxy()[4][3], 0, this.game.getGalaxy()[4][3]);
    assertEquals("Galaxy 4,4 mismatch 0!=" + this.game.getGalaxy()[4][4], 0, this.game.getGalaxy()[4][4]);
    assertEquals("Galaxy 4,5 mismatch 0!=" + this.game.getGalaxy()[4][5], 0, this.game.getGalaxy()[4][5]);
    assertEquals("Galaxy 4,6 mismatch 0!=" + this.game.getGalaxy()[4][6], 0, this.game.getGalaxy()[4][6]);
    assertEquals("Galaxy 4,7 mismatch 0!=" + this.game.getGalaxy()[4][7], 0, this.game.getGalaxy()[4][7]);
    assertEquals("Galaxy 4,8 mismatch 0!=" + this.game.getGalaxy()[4][8], 0, this.game.getGalaxy()[4][8]);
    assertEquals("Galaxy 5,1 mismatch 0!=" + this.game.getGalaxy()[5][1], 0, this.game.getGalaxy()[5][1]);
    assertEquals("Galaxy 5,2 mismatch 0!=" + this.game.getGalaxy()[5][2], 0, this.game.getGalaxy()[5][2]);
    assertEquals("Galaxy 5,3 mismatch 0!=" + this.game.getGalaxy()[5][3], 0, this.game.getGalaxy()[5][3]);
    assertEquals("Galaxy 5,4 mismatch 0!=" + this.game.getGalaxy()[5][4], 0, this.game.getGalaxy()[5][4]);
    assertEquals("Galaxy 5,5 mismatch 0!=" + this.game.getGalaxy()[5][5], 0, this.game.getGalaxy()[5][5]);
    assertEquals("Galaxy 5,6 mismatch 0!=" + this.game.getGalaxy()[5][6], 0, this.game.getGalaxy()[5][6]);
    assertEquals("Galaxy 5,7 mismatch 0!=" + this.game.getGalaxy()[5][7], 0, this.game.getGalaxy()[5][7]);
    assertEquals("Galaxy 5,8 mismatch 0!=" + this.game.getGalaxy()[5][8], 0, this.game.getGalaxy()[5][8]);
    assertEquals("Galaxy 6,1 mismatch 0!=" + this.game.getGalaxy()[6][1], 0, this.game.getGalaxy()[6][1]);
    assertEquals("Galaxy 6,2 mismatch 0!=" + this.game.getGalaxy()[6][2], 0, this.game.getGalaxy()[6][2]);
    assertEquals("Galaxy 6,3 mismatch 0!=" + this.game.getGalaxy()[6][3], 0, this.game.getGalaxy()[6][3]);
    assertEquals("Galaxy 6,4 mismatch 0!=" + this.game.getGalaxy()[6][4], 0, this.game.getGalaxy()[6][4]);
    assertEquals("Galaxy 6,5 mismatch 0!=" + this.game.getGalaxy()[6][5], 0, this.game.getGalaxy()[6][5]);
    assertEquals("Galaxy 6,6 mismatch 0!=" + this.game.getGalaxy()[6][6], 0, this.game.getGalaxy()[6][6]);
    assertEquals("Galaxy 6,7 mismatch 0!=" + this.game.getGalaxy()[6][7], 0, this.game.getGalaxy()[6][7]);
    assertEquals("Galaxy 6,8 mismatch 0!=" + this.game.getGalaxy()[6][8], 0, this.game.getGalaxy()[6][8]);
    assertEquals("Galaxy 7,1 mismatch 108!=" + this.game.getGalaxy()[7][1], 108, this.game.getGalaxy()[7][1]);
    assertEquals("Galaxy 7,2 mismatch 6!=" + this.game.getGalaxy()[7][2], 6, this.game.getGalaxy()[7][2]);
    assertEquals("Galaxy 7,3 mismatch 0!=" + this.game.getGalaxy()[7][3], 0, this.game.getGalaxy()[7][3]);
    assertEquals("Galaxy 7,4 mismatch 0!=" + this.game.getGalaxy()[7][4], 0, this.game.getGalaxy()[7][4]);
    assertEquals("Galaxy 7,5 mismatch 0!=" + this.game.getGalaxy()[7][5], 0, this.game.getGalaxy()[7][5]);
    assertEquals("Galaxy 7,6 mismatch 0!=" + this.game.getGalaxy()[7][6], 0, this.game.getGalaxy()[7][6]);
    assertEquals("Galaxy 7,7 mismatch 1!=" + this.game.getGalaxy()[7][7], 1, this.game.getGalaxy()[7][7]);
    assertEquals("Galaxy 7,8 mismatch 7!=" + this.game.getGalaxy()[7][8], 7, this.game.getGalaxy()[7][8]);
    assertEquals("Galaxy 8,1 mismatch 3!=" + this.game.getGalaxy()[8][1], 3, this.game.getGalaxy()[8][1]);
    assertEquals("Galaxy 8,2 mismatch 103!=" + this.game.getGalaxy()[8][2], 103, this.game.getGalaxy()[8][2]);
    assertEquals("Galaxy 8,3 mismatch 0!=" + this.game.getGalaxy()[8][3], 0, this.game.getGalaxy()[8][3]);
    assertEquals("Galaxy 8,4 mismatch 0!=" + this.game.getGalaxy()[8][4], 0, this.game.getGalaxy()[8][4]);
    assertEquals("Galaxy 8,5 mismatch 0!=" + this.game.getGalaxy()[8][5], 0, this.game.getGalaxy()[8][5]);
    assertEquals("Galaxy 8,6 mismatch 0!=" + this.game.getGalaxy()[8][6], 0, this.game.getGalaxy()[8][6]);
    assertEquals("Galaxy 8,7 mismatch 4!=" + this.game.getGalaxy()[8][7], 4, this.game.getGalaxy()[8][7]);
    assertEquals("Galaxy 8,8 mismatch 4!=" + this.game.getGalaxy()[8][8], 4, this.game.getGalaxy()[8][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:LT. Uhura reports:, was:" + logOut[0], "LT. Uhura reports:", logOut[0]);
    assertEquals("Log expecting:  Message from Starfleet Command:, was:" + logOut[1], "  Message from Starfleet Command:", logOut[1]);
    assertEquals("Log expecting:  Permission to attempt crossing of galactic perimeter, was:" + logOut[2], "  Permission to attempt crossing of galactic perimeter", logOut[2]);
    assertEquals("Log expecting:  is hereby *denied*. Shut down your engines., was:" + logOut[3], "  is hereby *denied*. Shut down your engines.", logOut[3]);
    assertEquals("Log expecting:Chief Engineer Scott reports:, was:" + logOut[4], "Chief Engineer Scott reports:", logOut[4]);
    assertEquals("Log expecting:  Warp Engines shut down at sector 1, 1 of quadrant 1, 1., was:" + logOut[5], "  Warp Engines shut down at sector 1, 1 of quadrant 1, 1.", logOut[5]);
    assertEquals("Log expecting:Now entering Antares quadrant..., was:" + logOut[6], "Now entering Antares quadrant...", logOut[6]);
    assertEquals("Log expecting:Combat Area  Condition Red, was:" + logOut[7], "Combat Area  Condition Red", logOut[7]);
    assertEquals("Wrong log length, 8 != " + logOut.length, 8, logOut.length);
    
    this.success = true;
  }
  
  public void testWon398()
  {
    this.game = new StartrekGame(398);
    this.commands = new String[] { "shi 1000", "nav 1 .1", "nav 3 4", "tor 8", "nav 3 2", "pha 500", "pha 200 ", "nav 5 7 ", "lrs ", "nav 1 1 ", "pha 600 ", "pha 100 ", "nav 5 .2 ", "nav 7 5 ", "tor 7 ", "nav 3 2 ", "nav 3 .1 ", "nav 1 1", "nav 1 .4 ", "pha 300 ", "tor  7.3 ", "nav 7 .2 ", "nav 1 1 ", "tor 1 ", "lrs ", "nav 1 1 ", "shi 300 ", "nav 1 1 ", "nav 1 .1 ", "nav 7 3 ", "shi 500 ", "nav 7 .3 ", "nav 5 .2 ", "com ", "nav 5 .1", "nav 1 .1", "tor 2" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[1,8  * , 2,4  * , 3,2  * , 4,4 <0>, 5,8  * , 7,1  * , 7,2 >!<, 8,7  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 549, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 352, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 5, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 0, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 7, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "-1.6", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "3.7", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=7,y=5]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=4,y=4]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 2!=" + this.game.getGalaxy()[1][1], 2, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 1,2 mismatch 6!=" + this.game.getGalaxy()[1][2], 6, this.game.getGalaxy()[1][2]);
    assertEquals("Galaxy 1,3 mismatch 0!=" + this.game.getGalaxy()[1][3], 0, this.game.getGalaxy()[1][3]);
    assertEquals("Galaxy 1,4 mismatch 0!=" + this.game.getGalaxy()[1][4], 0, this.game.getGalaxy()[1][4]);
    assertEquals("Galaxy 1,5 mismatch 0!=" + this.game.getGalaxy()[1][5], 0, this.game.getGalaxy()[1][5]);
    assertEquals("Galaxy 1,6 mismatch 0!=" + this.game.getGalaxy()[1][6], 0, this.game.getGalaxy()[1][6]);
    assertEquals("Galaxy 1,7 mismatch 0!=" + this.game.getGalaxy()[1][7], 0, this.game.getGalaxy()[1][7]);
    assertEquals("Galaxy 1,8 mismatch 7!=" + this.game.getGalaxy()[1][8], 7, this.game.getGalaxy()[1][8]);
    assertEquals("Galaxy 2,1 mismatch 4!=" + this.game.getGalaxy()[2][1], 4, this.game.getGalaxy()[2][1]);
    assertEquals("Galaxy 2,2 mismatch 3!=" + this.game.getGalaxy()[2][2], 3, this.game.getGalaxy()[2][2]);
    assertEquals("Galaxy 2,3 mismatch 0!=" + this.game.getGalaxy()[2][3], 0, this.game.getGalaxy()[2][3]);
    assertEquals("Galaxy 2,4 mismatch 0!=" + this.game.getGalaxy()[2][4], 0, this.game.getGalaxy()[2][4]);
    assertEquals("Galaxy 2,5 mismatch 0!=" + this.game.getGalaxy()[2][5], 0, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 0!=" + this.game.getGalaxy()[2][6], 0, this.game.getGalaxy()[2][6]);
    assertEquals("Galaxy 2,7 mismatch 0!=" + this.game.getGalaxy()[2][7], 0, this.game.getGalaxy()[2][7]);
    assertEquals("Galaxy 2,8 mismatch 0!=" + this.game.getGalaxy()[2][8], 0, this.game.getGalaxy()[2][8]);
    assertEquals("Galaxy 3,1 mismatch 0!=" + this.game.getGalaxy()[3][1], 0, this.game.getGalaxy()[3][1]);
    assertEquals("Galaxy 3,2 mismatch 0!=" + this.game.getGalaxy()[3][2], 0, this.game.getGalaxy()[3][2]);
    assertEquals("Galaxy 3,3 mismatch 7!=" + this.game.getGalaxy()[3][3], 7, this.game.getGalaxy()[3][3]);
    assertEquals("Galaxy 3,4 mismatch 4!=" + this.game.getGalaxy()[3][4], 4, this.game.getGalaxy()[3][4]);
    assertEquals("Galaxy 3,5 mismatch 1!=" + this.game.getGalaxy()[3][5], 1, this.game.getGalaxy()[3][5]);
    assertEquals("Galaxy 3,6 mismatch 0!=" + this.game.getGalaxy()[3][6], 0, this.game.getGalaxy()[3][6]);
    assertEquals("Galaxy 3,7 mismatch 0!=" + this.game.getGalaxy()[3][7], 0, this.game.getGalaxy()[3][7]);
    assertEquals("Galaxy 3,8 mismatch 2!=" + this.game.getGalaxy()[3][8], 2, this.game.getGalaxy()[3][8]);
    assertEquals("Galaxy 4,1 mismatch 0!=" + this.game.getGalaxy()[4][1], 0, this.game.getGalaxy()[4][1]);
    assertEquals("Galaxy 4,2 mismatch 4!=" + this.game.getGalaxy()[4][2], 4, this.game.getGalaxy()[4][2]);
    assertEquals("Galaxy 4,3 mismatch 7!=" + this.game.getGalaxy()[4][3], 7, this.game.getGalaxy()[4][3]);
    assertEquals("Galaxy 4,4 mismatch 1!=" + this.game.getGalaxy()[4][4], 1, this.game.getGalaxy()[4][4]);
    assertEquals("Galaxy 4,5 mismatch 7!=" + this.game.getGalaxy()[4][5], 7, this.game.getGalaxy()[4][5]);
    assertEquals("Galaxy 4,6 mismatch 0!=" + this.game.getGalaxy()[4][6], 0, this.game.getGalaxy()[4][6]);
    assertEquals("Galaxy 4,7 mismatch 0!=" + this.game.getGalaxy()[4][7], 0, this.game.getGalaxy()[4][7]);
    assertEquals("Galaxy 4,8 mismatch 0!=" + this.game.getGalaxy()[4][8], 0, this.game.getGalaxy()[4][8]);
    assertEquals("Galaxy 5,1 mismatch 0!=" + this.game.getGalaxy()[5][1], 0, this.game.getGalaxy()[5][1]);
    assertEquals("Galaxy 5,2 mismatch 0!=" + this.game.getGalaxy()[5][2], 0, this.game.getGalaxy()[5][2]);
    assertEquals("Galaxy 5,3 mismatch 5!=" + this.game.getGalaxy()[5][3], 5, this.game.getGalaxy()[5][3]);
    assertEquals("Galaxy 5,4 mismatch 7!=" + this.game.getGalaxy()[5][4], 7, this.game.getGalaxy()[5][4]);
    assertEquals("Galaxy 5,5 mismatch 3!=" + this.game.getGalaxy()[5][5], 3, this.game.getGalaxy()[5][5]);
    assertEquals("Galaxy 5,6 mismatch 0!=" + this.game.getGalaxy()[5][6], 0, this.game.getGalaxy()[5][6]);
    assertEquals("Galaxy 5,7 mismatch 0!=" + this.game.getGalaxy()[5][7], 0, this.game.getGalaxy()[5][7]);
    assertEquals("Galaxy 5,8 mismatch 0!=" + this.game.getGalaxy()[5][8], 0, this.game.getGalaxy()[5][8]);
    assertEquals("Galaxy 6,1 mismatch 0!=" + this.game.getGalaxy()[6][1], 0, this.game.getGalaxy()[6][1]);
    assertEquals("Galaxy 6,2 mismatch 3!=" + this.game.getGalaxy()[6][2], 3, this.game.getGalaxy()[6][2]);
    assertEquals("Galaxy 6,3 mismatch 0!=" + this.game.getGalaxy()[6][3], 0, this.game.getGalaxy()[6][3]);
    assertEquals("Galaxy 6,4 mismatch 0!=" + this.game.getGalaxy()[6][4], 0, this.game.getGalaxy()[6][4]);
    assertEquals("Galaxy 6,5 mismatch 0!=" + this.game.getGalaxy()[6][5], 0, this.game.getGalaxy()[6][5]);
    assertEquals("Galaxy 6,6 mismatch 0!=" + this.game.getGalaxy()[6][6], 0, this.game.getGalaxy()[6][6]);
    assertEquals("Galaxy 6,7 mismatch 0!=" + this.game.getGalaxy()[6][7], 0, this.game.getGalaxy()[6][7]);
    assertEquals("Galaxy 6,8 mismatch 0!=" + this.game.getGalaxy()[6][8], 0, this.game.getGalaxy()[6][8]);
    assertEquals("Galaxy 7,1 mismatch 0!=" + this.game.getGalaxy()[7][1], 0, this.game.getGalaxy()[7][1]);
    assertEquals("Galaxy 7,2 mismatch 0!=" + this.game.getGalaxy()[7][2], 0, this.game.getGalaxy()[7][2]);
    assertEquals("Galaxy 7,3 mismatch 0!=" + this.game.getGalaxy()[7][3], 0, this.game.getGalaxy()[7][3]);
    assertEquals("Galaxy 7,4 mismatch 0!=" + this.game.getGalaxy()[7][4], 0, this.game.getGalaxy()[7][4]);
    assertEquals("Galaxy 7,5 mismatch 16!=" + this.game.getGalaxy()[7][5], 16, this.game.getGalaxy()[7][5]);
    assertEquals("Galaxy 7,6 mismatch 0!=" + this.game.getGalaxy()[7][6], 0, this.game.getGalaxy()[7][6]);
    assertEquals("Galaxy 7,7 mismatch 0!=" + this.game.getGalaxy()[7][7], 0, this.game.getGalaxy()[7][7]);
    assertEquals("Galaxy 7,8 mismatch 6!=" + this.game.getGalaxy()[7][8], 6, this.game.getGalaxy()[7][8]);
    assertEquals("Galaxy 8,1 mismatch 0!=" + this.game.getGalaxy()[8][1], 0, this.game.getGalaxy()[8][1]);
    assertEquals("Galaxy 8,2 mismatch 0!=" + this.game.getGalaxy()[8][2], 0, this.game.getGalaxy()[8][2]);
    assertEquals("Galaxy 8,3 mismatch 0!=" + this.game.getGalaxy()[8][3], 0, this.game.getGalaxy()[8][3]);
    assertEquals("Galaxy 8,4 mismatch 0!=" + this.game.getGalaxy()[8][4], 0, this.game.getGalaxy()[8][4]);
    assertEquals("Galaxy 8,5 mismatch 0!=" + this.game.getGalaxy()[8][5], 0, this.game.getGalaxy()[8][5]);
    assertEquals("Galaxy 8,6 mismatch 0!=" + this.game.getGalaxy()[8][6], 0, this.game.getGalaxy()[8][6]);
    assertEquals("Galaxy 8,7 mismatch 0!=" + this.game.getGalaxy()[8][7], 0, this.game.getGalaxy()[8][7]);
    assertEquals("Galaxy 8,8 mismatch 0!=" + this.game.getGalaxy()[8][8], 0, this.game.getGalaxy()[8][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:Torpedo Track:, was:" + logOut[0], "Torpedo Track:", logOut[0]);
    assertEquals("Log expecting:    3, 5, was:" + logOut[1], "    3, 5", logOut[1]);
    assertEquals("Log expecting:    2, 6, was:" + logOut[2], "    2, 6", logOut[2]);
    assertEquals("Log expecting:    1, 7, was:" + logOut[3], "    1, 7", logOut[3]);
    assertEquals("Log expecting:*** Klingon Destroyed ***, was:" + logOut[4], "*** Klingon Destroyed ***", logOut[4]);
    assertEquals("Log expecting:Congratulations, Captain!  The last Klingon Battle Cruiser, was:" + logOut[5], "Congratulations, Captain!  The last Klingon Battle Cruiser", logOut[5]);
    assertEquals("Log expecting:menacing the Federation has been destroyed., was:" + logOut[6], "menacing the Federation has been destroyed.", logOut[6]);
    assertEquals("Log expecting:Your efficiency rating is 106.00, was:" + logOut[7], "Your efficiency rating is 106.00", logOut[7]);
    assertEquals("Wrong log length, 8 != " + logOut.length, 8, logOut.length);
    
    this.success = true;
  }
  
  public void testNoNewQuadrant()
  {
    this.game = new StartrekGame(0);
    this.commands = new String[] { "nav 1 5", "lrs", "nav 7 6", "nav 7 1" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[3,2  * , 4,3  * , 4,6  * , 6,4  * , 8,8 <0>]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 2748, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 15, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 28, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "-3.9", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.GREEN, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=8,y=8]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=8,y=8]", sLoc);
    assertEquals("Galaxy 1,1 mismatch 0!=" + this.game.getGalaxy()[1][1], 0, this.game.getGalaxy()[1][1]);
    assertEquals("Galaxy 1,2 mismatch 0!=" + this.game.getGalaxy()[1][2], 0, this.game.getGalaxy()[1][2]);
    assertEquals("Galaxy 1,3 mismatch 0!=" + this.game.getGalaxy()[1][3], 0, this.game.getGalaxy()[1][3]);
    assertEquals("Galaxy 1,4 mismatch 0!=" + this.game.getGalaxy()[1][4], 0, this.game.getGalaxy()[1][4]);
    assertEquals("Galaxy 1,5 mismatch 0!=" + this.game.getGalaxy()[1][5], 0, this.game.getGalaxy()[1][5]);
    assertEquals("Galaxy 1,6 mismatch 0!=" + this.game.getGalaxy()[1][6], 0, this.game.getGalaxy()[1][6]);
    assertEquals("Galaxy 1,7 mismatch 1!=" + this.game.getGalaxy()[1][7], 1, this.game.getGalaxy()[1][7]);
    assertEquals("Galaxy 1,8 mismatch 101!=" + this.game.getGalaxy()[1][8], 101, this.game.getGalaxy()[1][8]);
    assertEquals("Galaxy 2,1 mismatch 0!=" + this.game.getGalaxy()[2][1], 0, this.game.getGalaxy()[2][1]);
    assertEquals("Galaxy 2,2 mismatch 0!=" + this.game.getGalaxy()[2][2], 0, this.game.getGalaxy()[2][2]);
    assertEquals("Galaxy 2,3 mismatch 0!=" + this.game.getGalaxy()[2][3], 0, this.game.getGalaxy()[2][3]);
    assertEquals("Galaxy 2,4 mismatch 0!=" + this.game.getGalaxy()[2][4], 0, this.game.getGalaxy()[2][4]);
    assertEquals("Galaxy 2,5 mismatch 6!=" + this.game.getGalaxy()[2][5], 6, this.game.getGalaxy()[2][5]);
    assertEquals("Galaxy 2,6 mismatch 0!=" + this.game.getGalaxy()[2][6], 0, this.game.getGalaxy()[2][6]);
    assertEquals("Galaxy 2,7 mismatch 7!=" + this.game.getGalaxy()[2][7], 7, this.game.getGalaxy()[2][7]);
    assertEquals("Galaxy 2,8 mismatch 6!=" + this.game.getGalaxy()[2][8], 6, this.game.getGalaxy()[2][8]);
    assertEquals("Galaxy 3,1 mismatch 0!=" + this.game.getGalaxy()[3][1], 0, this.game.getGalaxy()[3][1]);
    assertEquals("Galaxy 3,2 mismatch 0!=" + this.game.getGalaxy()[3][2], 0, this.game.getGalaxy()[3][2]);
    assertEquals("Galaxy 3,3 mismatch 0!=" + this.game.getGalaxy()[3][3], 0, this.game.getGalaxy()[3][3]);
    assertEquals("Galaxy 3,4 mismatch 0!=" + this.game.getGalaxy()[3][4], 0, this.game.getGalaxy()[3][4]);
    assertEquals("Galaxy 3,5 mismatch 0!=" + this.game.getGalaxy()[3][5], 0, this.game.getGalaxy()[3][5]);
    assertEquals("Galaxy 3,6 mismatch 0!=" + this.game.getGalaxy()[3][6], 0, this.game.getGalaxy()[3][6]);
    assertEquals("Galaxy 3,7 mismatch 3!=" + this.game.getGalaxy()[3][7], 3, this.game.getGalaxy()[3][7]);
    assertEquals("Galaxy 3,8 mismatch 115!=" + this.game.getGalaxy()[3][8], 115, this.game.getGalaxy()[3][8]);
    assertEquals("Galaxy 4,1 mismatch 0!=" + this.game.getGalaxy()[4][1], 0, this.game.getGalaxy()[4][1]);
    assertEquals("Galaxy 4,2 mismatch 0!=" + this.game.getGalaxy()[4][2], 0, this.game.getGalaxy()[4][2]);
    assertEquals("Galaxy 4,3 mismatch 0!=" + this.game.getGalaxy()[4][3], 0, this.game.getGalaxy()[4][3]);
    assertEquals("Galaxy 4,4 mismatch 0!=" + this.game.getGalaxy()[4][4], 0, this.game.getGalaxy()[4][4]);
    assertEquals("Galaxy 4,5 mismatch 0!=" + this.game.getGalaxy()[4][5], 0, this.game.getGalaxy()[4][5]);
    assertEquals("Galaxy 4,6 mismatch 0!=" + this.game.getGalaxy()[4][6], 0, this.game.getGalaxy()[4][6]);
    assertEquals("Galaxy 4,7 mismatch 0!=" + this.game.getGalaxy()[4][7], 0, this.game.getGalaxy()[4][7]);
    assertEquals("Galaxy 4,8 mismatch 0!=" + this.game.getGalaxy()[4][8], 0, this.game.getGalaxy()[4][8]);
    assertEquals("Galaxy 5,1 mismatch 0!=" + this.game.getGalaxy()[5][1], 0, this.game.getGalaxy()[5][1]);
    assertEquals("Galaxy 5,2 mismatch 0!=" + this.game.getGalaxy()[5][2], 0, this.game.getGalaxy()[5][2]);
    assertEquals("Galaxy 5,3 mismatch 0!=" + this.game.getGalaxy()[5][3], 0, this.game.getGalaxy()[5][3]);
    assertEquals("Galaxy 5,4 mismatch 0!=" + this.game.getGalaxy()[5][4], 0, this.game.getGalaxy()[5][4]);
    assertEquals("Galaxy 5,5 mismatch 0!=" + this.game.getGalaxy()[5][5], 0, this.game.getGalaxy()[5][5]);
    assertEquals("Galaxy 5,6 mismatch 0!=" + this.game.getGalaxy()[5][6], 0, this.game.getGalaxy()[5][6]);
    assertEquals("Galaxy 5,7 mismatch 0!=" + this.game.getGalaxy()[5][7], 0, this.game.getGalaxy()[5][7]);
    assertEquals("Galaxy 5,8 mismatch 0!=" + this.game.getGalaxy()[5][8], 0, this.game.getGalaxy()[5][8]);
    assertEquals("Galaxy 6,1 mismatch 0!=" + this.game.getGalaxy()[6][1], 0, this.game.getGalaxy()[6][1]);
    assertEquals("Galaxy 6,2 mismatch 0!=" + this.game.getGalaxy()[6][2], 0, this.game.getGalaxy()[6][2]);
    assertEquals("Galaxy 6,3 mismatch 0!=" + this.game.getGalaxy()[6][3], 0, this.game.getGalaxy()[6][3]);
    assertEquals("Galaxy 6,4 mismatch 0!=" + this.game.getGalaxy()[6][4], 0, this.game.getGalaxy()[6][4]);
    assertEquals("Galaxy 6,5 mismatch 0!=" + this.game.getGalaxy()[6][5], 0, this.game.getGalaxy()[6][5]);
    assertEquals("Galaxy 6,6 mismatch 0!=" + this.game.getGalaxy()[6][6], 0, this.game.getGalaxy()[6][6]);
    assertEquals("Galaxy 6,7 mismatch 0!=" + this.game.getGalaxy()[6][7], 0, this.game.getGalaxy()[6][7]);
    assertEquals("Galaxy 6,8 mismatch 0!=" + this.game.getGalaxy()[6][8], 0, this.game.getGalaxy()[6][8]);
    assertEquals("Galaxy 7,1 mismatch 0!=" + this.game.getGalaxy()[7][1], 0, this.game.getGalaxy()[7][1]);
    assertEquals("Galaxy 7,2 mismatch 0!=" + this.game.getGalaxy()[7][2], 0, this.game.getGalaxy()[7][2]);
    assertEquals("Galaxy 7,3 mismatch 0!=" + this.game.getGalaxy()[7][3], 0, this.game.getGalaxy()[7][3]);
    assertEquals("Galaxy 7,4 mismatch 0!=" + this.game.getGalaxy()[7][4], 0, this.game.getGalaxy()[7][4]);
    assertEquals("Galaxy 7,5 mismatch 0!=" + this.game.getGalaxy()[7][5], 0, this.game.getGalaxy()[7][5]);
    assertEquals("Galaxy 7,6 mismatch 0!=" + this.game.getGalaxy()[7][6], 0, this.game.getGalaxy()[7][6]);
    assertEquals("Galaxy 7,7 mismatch 0!=" + this.game.getGalaxy()[7][7], 0, this.game.getGalaxy()[7][7]);
    assertEquals("Galaxy 7,8 mismatch 0!=" + this.game.getGalaxy()[7][8], 0, this.game.getGalaxy()[7][8]);
    assertEquals("Galaxy 8,1 mismatch 0!=" + this.game.getGalaxy()[8][1], 0, this.game.getGalaxy()[8][1]);
    assertEquals("Galaxy 8,2 mismatch 0!=" + this.game.getGalaxy()[8][2], 0, this.game.getGalaxy()[8][2]);
    assertEquals("Galaxy 8,3 mismatch 0!=" + this.game.getGalaxy()[8][3], 0, this.game.getGalaxy()[8][3]);
    assertEquals("Galaxy 8,4 mismatch 0!=" + this.game.getGalaxy()[8][4], 0, this.game.getGalaxy()[8][4]);
    assertEquals("Galaxy 8,5 mismatch 0!=" + this.game.getGalaxy()[8][5], 0, this.game.getGalaxy()[8][5]);
    assertEquals("Galaxy 8,6 mismatch 0!=" + this.game.getGalaxy()[8][6], 0, this.game.getGalaxy()[8][6]);
    assertEquals("Galaxy 8,7 mismatch 0!=" + this.game.getGalaxy()[8][7], 0, this.game.getGalaxy()[8][7]);
    assertEquals("Galaxy 8,8 mismatch 4!=" + this.game.getGalaxy()[8][8], 4, this.game.getGalaxy()[8][8]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:LT. Uhura reports:, was:" + logOut[0], "LT. Uhura reports:", logOut[0]);
    assertEquals("Log expecting:  Message from Starfleet Command:, was:" + logOut[1], "  Message from Starfleet Command:", logOut[1]);
    assertEquals("Log expecting:  Permission to attempt crossing of galactic perimeter, was:" + logOut[2], "  Permission to attempt crossing of galactic perimeter", logOut[2]);
    assertEquals("Log expecting:  is hereby *denied*. Shut down your engines., was:" + logOut[3], "  is hereby *denied*. Shut down your engines.", logOut[3]);
    assertEquals("Log expecting:Chief Engineer Scott reports:, was:" + logOut[4], "Chief Engineer Scott reports:", logOut[4]);
    assertEquals("Log expecting:  Warp Engines shut down at sector 8, 8 of quadrant 8, 8., was:" + logOut[5], "  Warp Engines shut down at sector 8, 8 of quadrant 8, 8.", logOut[5]);
    assertEquals("Wrong log length, 6 != " + logOut.length, 6, logOut.length);
    
    this.success = true;
  }
  
  public void testStranded1()
  {
    this.game = new StartrekGame(1);
    this.commands = new String[] { "pha 3000" };
    for (String cmd : Arrays.asList(this.commands)) {
      this.game.doCommand(cmd);
    }
    assertEquals("Quadrant mismatch", "[2,1 <0>, 2,7  * ]", this.game.getQuadrant().toString());
    assertEquals("Indicator 0 mismatch", 0, ((Integer)this.game.getIndicators().get(0)).intValue());
    assertEquals("Indicator 1 mismatch", 0, ((Integer)this.game.getIndicators().get(1)).intValue());
    assertEquals("Indicator 2 mismatch", 10, ((Integer)this.game.getIndicators().get(2)).intValue());
    assertEquals("Indicator 3 mismatch", 20, ((Integer)this.game.getIndicators().get(3)).intValue());
    assertEquals("Indicator 4 mismatch", 34, ((Integer)this.game.getIndicators().get(4)).intValue());
    assertEquals("Device 0 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(0) }));
    assertEquals("Device 1 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(1) }));
    assertEquals("Device 2 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(2) }));
    assertEquals("Device 3 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(3) }));
    assertEquals("Device 4 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(4) }));
    assertEquals("Device 5 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(5) }));
    assertEquals("Device 6 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(6) }));
    assertEquals("Device 7 status incorrect", "0.0", String.format("%3.1f", new Object[] { this.game.getDeviceStatus().get(7) }));
    assertEquals("Alert level incorrect", AlertLevel.RED, this.game.getAlertLevel());
    String qLoc = this.game.getShipPosition()[0].toString().replace("java.awt.Point", "");
    assertEquals("Quadrant point incorrect", "[x=4,y=4]", qLoc);
    String sLoc = this.game.getShipPosition()[1].toString().replace("java.awt.Point", "");
    assertEquals("Sector point incorrect", "[x=2,y=1]", sLoc);
    assertEquals("Galaxy 4,4 mismatch 1!=" + this.game.getGalaxy()[4][4], 1, this.game.getGalaxy()[4][4]);
    String[] logOut = this.game.getLog().split("\n");
    assertEquals("Log expecting:2050 unit hit on Klingon at sector 3, 6, was:" + logOut[0], "2050 unit hit on Klingon at sector 3, 6", logOut[0]);
    assertEquals("Log expecting:*** Klingon Destroyed ***, was:" + logOut[1], "*** Klingon Destroyed ***", logOut[1]);
    assertEquals("Log expecting:2030 unit hit on Klingon at sector 3, 3, was:" + logOut[2], "2030 unit hit on Klingon at sector 3, 3", logOut[2]);
    assertEquals("Log expecting:*** Klingon Destroyed ***, was:" + logOut[3], "*** Klingon Destroyed ***", logOut[3]);
    assertEquals("Log expecting:** Fatal Error **   , was:" + logOut[4], "** Fatal Error **   ", logOut[4]);
    assertEquals("Log expecting:You've just stranded your ship in space., was:" + logOut[5], "You've just stranded your ship in space.", logOut[5]);
    assertEquals("Log expecting:You have insufficient maneuvering energy,, was:" + logOut[6], "You have insufficient maneuvering energy,", logOut[6]);
    assertEquals("Log expecting:and Shield Control is presently, was:" + logOut[7], "and Shield Control is presently", logOut[7]);
    assertEquals("Log expecting:incapable of cross circuiting to engine room!!, was:" + logOut[8], "incapable of cross circuiting to engine room!!", logOut[8]);
    assertEquals("Log expecting:It is stardate 2600., was:" + logOut[9], "It is stardate 2600.", logOut[9]);
    assertEquals("Log expecting:, was:" + logOut[10], "", logOut[10]);
    assertEquals("Log expecting:There were 20 Klingon Battlecruisers left at the, was:" + logOut[11], "There were 20 Klingon Battlecruisers left at the", logOut[11]);
    assertEquals("Log expecting: end of your mission., was:" + logOut[12], " end of your mission.", logOut[12]);
    assertEquals("Wrong log length, 13 != " + logOut.length, 13, logOut.length);
    
    this.success = true;
  }
}*/
