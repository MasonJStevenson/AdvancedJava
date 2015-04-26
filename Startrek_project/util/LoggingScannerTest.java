package util;

import java.io.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LoggingScannerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LoggingScannerTest
{
    /**
     * Default constructor for test class LoggingScannerTest
     */
    public LoggingScannerTest()
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
    public void testScanner()
    {
        String str = "Testing my multi-line string\n"
            + "To see if the scanner will work\n"
            + "My bet is that it does\n"
            + "If not, we will try something else\n";
 
        // convert String into InputStream
        InputStream input = new ByteArrayInputStream(str.getBytes());
        
        LoggingScanner logScan = new LoggingScanner(input, true);
        
        while (logScan.hasNextLine())
        {
            logScan.nextLine();
        }
        
        assertEquals("log must equal input", str, logScan.toString());
        //System.out.println(logScan.toString());
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
