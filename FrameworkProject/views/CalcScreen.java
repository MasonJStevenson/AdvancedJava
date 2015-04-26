package views;

import javax.swing.JTextPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.awt.Color;

/**
 * A custom textpane for the calculator gui.
 * @author MasonS
 * @version 1.0
 */
public class CalcScreen extends JTextPane
{
    private String log;
    
    /**
     * Constructor for CalcScreen.
     */
    public CalcScreen()
    {
        log = "";
    }
    
    /**
     * Prints some text to the calculator screen.
     * @param text The text to print.
     * @param useLog If useLog is true, the string is appended to the log.
     */
    public void print(String text, boolean useLog)
    {
        this.setText(this.getText() + text);
        
        //check useLog flag
        if (useLog)
        {
            log += text;
        }
    }
    
    /**
     * Prints a newline character to the calculator screen.
     * @param useLog If useLog is true, the string is appended to the log.
     */
    public void println(boolean useLog)
    {
        this.setText(this.getText() + "\n");
        
        //check useLog flag
        if (useLog)
        {
            log += "\n";
        }
    }
    
    
    /**
     * Prints some text to the calculator screen, along with a newline 
     * character.
     * @param text The text to print.
     * @param useLog If useLog is true, the string is appended to the log.
     */
    public void println(String text, boolean useLog)
    {
        this.setText(this.getText() + text + "\n");
        
        //check useLog flag
        if (useLog)
        {
            log += (text + "\n");
        }
    }
    
    /**
     * Clears the calculator screen (text view).
     */
    public void clear()
    {
        this.setText("");
        log = "";
    }
    
    /**
     * Returns and flushes the log.
     * @return Returns the log.
     */
    public String getLog()
    {
        String temp = log;
        log = "";
        return temp;
    }
}
