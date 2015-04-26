package model;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.*;

import views.GUI;

/**
 * Controls the calculator. Each public method in this class is usable by 
 * CalculatorPlugin objects.
 * 
 * @author MasonS
 * @version 1.0
 */
public class CalcController
{
    private Calculator calc;
    private GUI gui;
    
    /**
     * Constructor for CalcController.
     * @param newGui A reference to the gui this class controlls.
     */
    public CalcController(GUI newGui)
    {
        gui = newGui;
        calc = new DefaultCalculator();
    }
    
    /*
     * Constructor for CalcController.
     * @param newGui A reference to the gui this class controlls.
     * @param newCalc A calculator that overrides the default one.
     *
    public CalcController(GUI newGui, Calculator newCalc)
    {
        gui = newGui;
        calc = newCalc;
    }*/
    
    /**
     * Sets the calculator for this cal controller.
     * @param newCalc The custom calculator to set, or null for default.
     */
    public void setCalculator(Calculator newCalc)
    {
        //check for custom calc
        if (newCalc != null)
        {
            calc = newCalc;
        }
        
        //if no custom calc, use default
        else
        {
            calc = new DefaultCalculator();
        }
    }
    
    /**
     * Gets the most recent command from the text view, processes it, and 
     * prints the result to the screen.
     */
    public void enter()
    {
        String result;
        String textCommand = "";
        
        //get command from gui's text field
        textCommand = getCommand();
        
        //process command
        result = calc.processCommand(textCommand);
        
        //append result to gui's text field
        ///append(result + "\n");
        gui.getScreen().println(result, false);
    }
    
    /**
     * Adds a JMenuItem to the File menu.
     * @param item The item to add.
     * @param index The index of the item
     */
    public void addToFile(JMenuItem item, int index)
    {
        gui.getFileMenu().add(item, index);
    }
    
    /**
     * Adds a JMenuItem to the Edit menu.
     * @param item The item to add.
     * @param index The index of the item
     */
    public void addToEdit(JMenuItem item, int index)
    {
        gui.getEditMenu().add(item, index);
    }
    
    /**
     * Adds a JMenuItem to the Help menu.
     * @param item The item to add.
     * @param index The index of the item
     */
    public void addToHelp(JMenuItem item, int index)
    {
        gui.getHelpMenu().add(item, index);
    }
    
    /**
     * Returns all the text in the text view.
     * @return Returns all the text in the text view.
     */
    public String getText()
    {
        return gui.getScreen().getText();
    }
    
    /**
     * Clears the text view.
     */
    public void clear()
    {
        gui.getScreen().clear();
    }
    
    /**
     * Appends some text to the text view.
     * @param toAppend The text to append.
     */
    public void append(String toAppend)
    {
        gui.getScreen().print(toAppend, true);
    }
    
    /**
     * Sends a notification to the user.
     * @param notification The notification to send.
     */
    public void notify(String notification)
    {
        JOptionPane.showMessageDialog(null, notification);
    }
    
    /**
     * Switches the display.
     * @param view A command for which view. (text or draw)
     */
    public void switchDisplay(String view)
    {
        gui.switchDisplay(view);
    }
    
    /**
     * Updates the canvas.
     * @param drawing A JPanel containing a drawing for the canvas.
     */
    public void sendDrawingToCanvas(JPanel drawing)
    {
        gui.getCanvas().removeAll();
        gui.getCanvas().add(drawing);
        gui.getCanvas().updateUI();
    }
    
    /**
     * Removes all components from the canvas.
     */
    public void clearCanvas()
    {
        gui.getCanvas().removeAll();
        gui.getCanvas().updateUI();
    }
    
    /**
     * Tells the gui to exit.
     */
    public void exit()
    {
        gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
    }
    
    /**
     * Gets the newest command from the gui's text field.
     * @return returns the command
     */
    private String getCommand()
    {
        String[] log = gui.getScreen().getLog().split("\n");
        
        //command retrieved
        if (log.length > 0 && !log[0].equals(""))
        {
            gui.getScreen().println(false);
            return log[log.length - 1];
        }
        
        //no command
        else
        {
            return "";
        }
    }
}
