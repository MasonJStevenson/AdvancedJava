package model;

import views.ControlPanel;
import views.Numpad;

/**
 * Defines the basic functions for a plugin for the calculator program.
 * 
 * Each plugin can provide a custom Numpad (custom JPanel), ControlPanel 
 * (custom JPanel), and Calculator (a string processor). The calculator 
 * framework obtains these items through the abstract getXXX methods shown 
 * below.
 * 
 * While all getXXX methods must be implemented by a child class, they can 
 * return null to retain the default setting.
 * 
 * Additionally, each plugin is provided with a CalcController, which allows
 * the plugin to interface with the Calculator gui. For more information on 
 * the available methods for CalcController, please refer to the CalcController
 * documentation.
 * 
 * Keep in mind that each plugin is re-constructed each time it is selected
 * with the layout selector. If you want to have some operation happen only 
 * once, use a static flag of some kind. The RomanPlugin class has an example 
 * of this.
 * 
 * @author MasonS
 * @version 1.0
 */
public abstract class CalculatorPlugin
{
    private CalcController controller;
        
    /**
     * Constructor for CalculatorPlugin.
     * @param newController A reference to the controller used by this plugin.
     */
    public CalculatorPlugin(CalcController newController)
    {
        controller = newController;
        controller.setCalculator(getCalculator());
    }
    
    /**
     * Sets the controller for this plugin.
     * @param newController The controller to set.
     */
    public void setController(CalcController newController)
    {
        controller = newController;
    }
    
    /**
     * Returns the controller for this plugin.
     * @return Returns the controller for this plugin.
     */
    public CalcController getController()
    {
        return controller;
    }
    
    /**
     * Alters the Calculator GUI's numpad.
     * 
     * If default numpad is desired, return null.
     * @return Returns a custom numpad, or null for default numpad.
     */
    public abstract Numpad getNumpad();
    
    /**
     * Alters the Calculator GUI's control panel.
     * 
     * If default control panel is desired, return null.
     * @return Returns a custom control panel, or null for default control 
     * panel.
     */
    public abstract ControlPanel getControlPanel();
    
    /**
     * Alters calculator behavior.
     * 
     * If default calculator is desired, return null.
     * @return Returns a custom calculator, or null for default calculator
     */
    public abstract Calculator getCalculator();
    
    /**
     * Reverts the GUI back to its original state.
     * @param controller
     */
    public abstract void unload();
}
