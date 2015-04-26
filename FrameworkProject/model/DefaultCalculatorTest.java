package model;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DefaultCalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DefaultCalculatorTest
{
    /**
     * Default constructor for test class DefaultCalculatorTest
     */
    public DefaultCalculatorTest()
    {
        DefaultCalculator calc = new DefaultCalculator();
        
        assertEquals("Operation must evaluate correctly.", "Result: 4", calc.processCommand("2 + 2"));
        assertEquals("Operation must evaluate correctly.", "Result: 4.0", calc.processCommand("2.0 + 2"));
        
        assertEquals("Operation must evaluate correctly.", "Result: 0", calc.processCommand("2 - 2"));
        assertEquals("Operation must evaluate correctly.", "Result: 1.5", calc.processCommand("2 - .5"));
        
        assertEquals("Operation must evaluate correctly.", "Result: 6", calc.processCommand("2 * 3"));
        assertEquals("Operation must evaluate correctly.", "Result: 6.0", calc.processCommand("2.0 * 3"));
        
        assertEquals("Operation must evaluate correctly.", "Result: 1", calc.processCommand("2 / 2"));
        assertEquals("Operation must evaluate correctly.", "Result: 12.0", calc.processCommand("24.0 / 2"));
        assertEquals("Operation must evaluate correctly.", "Result: 1.5", calc.processCommand("6 / 4"));
        
        assertEquals("Error not handled correctly.", "", calc.processCommand("% % %"));
        assertEquals("Error not handled correctly.", "Error: Invalid Operation.", calc.processCommand("2 +"));
        assertEquals("Error not handled correctly.", "Error: Invalid Operation.", calc.processCommand("2 + 2 + 3 + 4"));
        assertEquals("Error not handled correctly.", "Error: Invalid Number Format.", calc.processCommand("2 + -"));
        assertEquals("Error not handled correctly.", "Error: Invalid Number Format.", calc.processCommand("2.0 + -"));
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
    public void testCalc()
    {
        
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
