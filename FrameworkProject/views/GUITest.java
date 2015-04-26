package views;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.*;

/**
 * The test class GUITest.
 *
 * @author  Mason Stevenson
 * @version 1.0
 */
public class GUITest
{
    /**
     * Default constructor for test class GUITest
     */
    public GUITest()
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
    public void testDefualt()
    {
        CalcController cont;
        GUI gui = new GUI();
        cont = gui.getController();
        String[] results;
        
        cont.append("2 + 2\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 4", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2.0 + 2\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 4.0", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2 - 2\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 0", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2 - .5\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 1.5", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2 * 3\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 6", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2.0 * 3\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 6.0", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2 / 2\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 1", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("24.0 / 2\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 12.0", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("6 / 4\n");
        cont.enter();
        assertEquals("Operation must evaluate correctly.", "Result: 1.5", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("% % %\n");
        cont.enter();
        assertEquals("Error not handled correctly.", 1, cont.getText().split("\n").length);
        cont.clear();
        
        cont.append("2 +\n");
        cont.enter();
        assertEquals("Error not handled correctly.", "Error: Invalid Operation.", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2 + 3 + 4\n");
        cont.enter();
        assertEquals("Error not handled correctly.", "Error: Invalid Operation.", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2 + -\n");
        cont.enter();
        assertEquals("Error not handled correctly.", "Error: Invalid Number Format.", cont.getText().split("\n")[2]);
        cont.clear();
        
        cont.append("2.0 + -\n");
        cont.enter();
        assertEquals("Error not handled correctly.", "Error: Invalid Number Format.", cont.getText().split("\n")[2]);
        cont.clear();
        
        gui.dispose();
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
}
