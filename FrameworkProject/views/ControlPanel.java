package views;

import javax.swing.JPanel;

import model.CalcController;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.border.LineBorder;

/**
 * Basic definition for a control panel view in the calculator gui.
 * @author MasonS
 * @version 1.0
 */
public abstract class ControlPanel extends JPanel
{
    private CalcController controller;
    
    /**
     * Constructor for ControlPanel.
     * 
     * @param newController A controller for the parent gui.
     */
    public ControlPanel(CalcController newController)
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        setBackground(new Color(105, 105, 105));
        controller = newController;
        setPreferredSize(new Dimension(194, 695));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    /**
     * Gets the current controller.
     * @return Returns the current controller.
     */
    public CalcController getController()
    {
        return controller;
    }
}
