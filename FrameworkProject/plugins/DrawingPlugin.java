package plugins;

import views.ControlPanel;
import views.Display;
import views.Numpad;
import model.CalcController;
import model.Calculator;
import model.CalculatorPlugin;

/**
 * Allows the user to draw a circle or a graph drawing to the draw view in the
 * calculator gui.
 * 
 * @author MasonS
 * @version 1.0
 */
public class DrawingPlugin extends CalculatorPlugin
{
    /**
     * Constructor for DrawingPlugin.
     * 
     * @param newController A controller for this plugin's parent gui.
     */
    public DrawingPlugin(CalcController newController)
    {
        super(newController);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Numpad getNumpad()
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlPanel getControlPanel()
    {
        return new DrawingPanel(getController());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator getCalculator()
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unload()
    {
        getController().switchDisplay(Display.kTextView);
    }

}
