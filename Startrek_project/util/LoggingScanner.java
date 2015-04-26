package util;
import java.util.*;

/** A wrapper around java.util.Scanner that records the user inputs
 *  and can report them.
 */
public final class LoggingScanner 
{
    private Scanner scan;            // let's wrap this
    private StringBuilder logBuffer;  // our buffer
    private boolean doReport;        // should we display the results
    
    /** Construct this scanner from the given input source,
     *  and indicate whether a report is desired or not.
     *  During production report is usually false.
     *  
     *  @param source the source
     *  @param report the report
     */
    public LoggingScanner(java.io.InputStream source, boolean report)
    {
        scan = new Scanner(source);
        logBuffer = new StringBuilder();
        doReport = report;
    }
    /** 
     * Return the next line of input and log it to the buffer.
     * @return returns the nextLine.
     */
    public String nextLine()
    {
        String lineIn = scan.nextLine();
        logBuffer.append(lineIn);
        logBuffer.append("\n");
        return lineIn;
    }
    /** 
     * Check if more input is available.
     * 
     * @return returns true or false.
     */
    public boolean hasNextLine()
    {
        return scan.hasNextLine();
    }
    /** Return a printable representation of the buffer,
     *   if doReport is true.
     *   
     *   @return returns result.
     */
    public String toString()
    {
        String result = "";
        
        //if logs are turned on
        if (doReport)
        {
            result = logBuffer.toString();
        }
        return result;
    }
}