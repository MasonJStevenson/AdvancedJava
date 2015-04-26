package views;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import model.CalcController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Default control panel view that shows up when no plugin is loaded or if a 
 * plugin does not add control panel functionality.
 * 
 * @author MasonS
 * @version 1.0
 */
public class DefaultControlPanel extends ControlPanel
{
    /**
     * Constructor for DefaultControlPanel.
     * @param newController A controller for this panel's parent gui.
     */
    public DefaultControlPanel(CalcController newController)
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        super(newController);

        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setMaximumSize(new Dimension(32767, 200));
        add(verticalStrut);

        JButton btnNewButton = new JButton("/");
        btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append(" / ");
            }
        });
        btnNewButton.setMaximumSize(new Dimension(50, 50));
        btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(btnNewButton);

        Component verticalStrut1 = Box.createVerticalStrut(20);
        add(verticalStrut1);

        JButton btnX = new JButton("X");
        btnX.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append(" * ");
            }
        });
        btnX.setMaximumSize(new Dimension(50, 50));
        btnX.setAlignmentX(0.5f);
        add(btnX);

        Component verticalStrut2 = Box.createVerticalStrut(20);
        add(verticalStrut2);

        JButton button1 = new JButton("-");
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append(" - ");
            }
        });
        button1.setMaximumSize(new Dimension(50, 50));
        button1.setAlignmentX(0.5f);
        add(button1);

        Component verticalStrut3 = Box.createVerticalStrut(20);
        add(verticalStrut3);

        JButton button2 = new JButton("+");
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append(" + ");
            }
        });
        button2.setMaximumSize(new Dimension(50, 50));
        button2.setAlignmentX(0.5f);
        add(button2);

        Component verticalStrut4 = Box.createVerticalStrut(20);
        add(verticalStrut4);

        JButton btnEnter = new JButton("Enter");
        btnEnter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().enter();
            }
        });
        btnEnter.setMaximumSize(new Dimension(75, 30));
        btnEnter.setAlignmentX(0.5f);
        add(btnEnter);

        Component verticalStrut5 = Box.createVerticalStrut(20);
        add(verticalStrut5);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().clear();
            }
        });
        btnClear.setMaximumSize(new Dimension(75, 30));
        btnClear.setAlignmentX(0.5f);
        add(btnClear);
    }
    
    private void initOperatorButtons()
    {
        
    }
    
    private void initActionButtons()
    {
        
    }
}