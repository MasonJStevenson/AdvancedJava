package views;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextPane;

import java.awt.Color;

import javax.swing.UIManager;

import model.CalcController;
import model.Calculator;
import model.CalculatorPlugin;

import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.Box;

/**
 * Main gui frame for the calculator framework program.
 * 
 * @author MasonS
 * @version 1.0
 */
public class GUI extends JFrame
{
    private Display display;

    private JPanel numPanel;
    private CalcController controller;
    private JPanel controlPanel;
    private CalculatorPlugin currentPlugin;
    private JMenu mnFile;
    private JMenu mnEdit;
    private JMenu mnHelp;

    /**
     * Constructor for GUI.
     */
    public GUI()
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        try
        {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } 
        
        catch (Throwable e)
        {
            e.printStackTrace();
        }

        
        setResizable(false);
        setTitle("Calculator");
        setMinimumSize(new Dimension(700, 900));
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        buildPanels();
        buildMenuBar();

        currentPlugin = null;

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void buildPanels()
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        JPanel left = new JPanel();
        JPanel displayPanel = new JPanel();
        numPanel = new JPanel();
        JPanel right = new JPanel();
        JPanel layoutSelectionPanel = new JPanel();
        controlPanel = new JPanel();

        left.setBackground(Color.WHITE);
        getContentPane().add(left);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        displayPanel.setBackground(Color.WHITE);
        displayPanel.setPreferredSize(new Dimension(500, 600));
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.X_AXIS));
        display = new Display();
        displayPanel.add(display);
        left.add(displayPanel);

        numPanel.setBackground(Color.WHITE);
        numPanel.setPreferredSize(new Dimension(500, 300));
        left.add(numPanel);
        numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.X_AXIS));
        
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setPreferredSize(new Dimension(0, 5));
        left.add(verticalStrut);

        right.setBackground(Color.WHITE);
        getContentPane().add(right);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        layoutSelectionPanel.setBackground(Color.WHITE);
        layoutSelectionPanel.setPreferredSize(new Dimension(200, 200));
        layoutSelectionPanel.setLayout(new BoxLayout(layoutSelectionPanel,
                BoxLayout.X_AXIS));
        layoutSelectionPanel.add(new LayoutSelector(this));
        right.add(layoutSelectionPanel);

        controlPanel.setBackground(Color.WHITE);
        controlPanel.setPreferredSize(new Dimension(200, 700));
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        right.add(controlPanel);
        
        Component verticalStrut2 = Box.createVerticalStrut(20);
        verticalStrut2.setPreferredSize(new Dimension(0, 5));
        right.add(verticalStrut2);
    }
    
    private void buildMenuBar()
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmExit = new JMenuItem("Exit");
        
        mntmExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                closeWindow();
            }
        });
        
        mnFile.add(mntmExit);

        mnEdit = new JMenu("Edit");
        menuBar.add(mnEdit);

        mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
    }

    /**
     * Loads the proper JPanels and calculator logic from a plugin object.
     * @param plugin The plugin to load
     */
    public void loadPlugin(CalculatorPlugin plugin)
    {
        Numpad numpad;
        ControlPanel control;
        Calculator calc = plugin.getCalculator();

        // unload the old plugin
        if (currentPlugin != null)
        {
            currentPlugin.unload();
        }

        /*
        // load calculator
        if (calc != null)
        {
            controller = new CalcController(this, calc);
        }

        // default calculator
        else
        {
            controller = new CalcController(this);
        }*/

        numpad = plugin.getNumpad();
        control = plugin.getControlPanel();

        // load numpad
        if (numpad != null)
        {
            numPanel.removeAll();
            numPanel.add(numpad);
            numPanel.updateUI();
        }

        // default numpad
        else
        {
            numPanel.removeAll();
            numPanel.add(new DefaultNumpad(controller));
            numPanel.updateUI();
        }

        // load control panel
        if (control != null)
        {
            controlPanel.removeAll();
            controlPanel.add(control);
            controlPanel.updateUI();
        }

        // default control panel
        else
        {
            controlPanel.removeAll();
            controlPanel.add(new DefaultControlPanel(controller));
            controlPanel.updateUI();
        }

        currentPlugin = plugin;

    }

    /**
     * Loads the default JPanels and calculator logic.
     */
    public void loadDefault()
    {
        // unload the old plugin
        if (currentPlugin != null)
        {
            currentPlugin.unload();
        }

        controller = new CalcController(this);

        numPanel.removeAll();
        numPanel.add(new DefaultNumpad(controller));
        numPanel.updateUI();

        controlPanel.removeAll();
        controlPanel.add(new DefaultControlPanel(controller));
        controlPanel.updateUI();
    }
    
    /**
     * Gets the file menu.
     * @return Returns the file menu.
     */
    public JMenu getFileMenu()
    {
        return mnFile;
    }
    
    /**
     * Gets the edit menu.
     * @return Returns the edit menu.
     */
    public JMenu getEditMenu()
    {
        return mnEdit;
    }
    
    /**
     * Gets the help menu.
     * @return Returns the help menu.
     */
    public JMenu getHelpMenu()
    {
        return mnHelp;
    }

    /**
     * Returns the current screen.
     * @return Returns the current screen.
     */
    public CalcScreen getScreen()
    {
        return display.getScreen();
    }

    /**
     * Switches the display between drawing and text view.
     * @param view Either "draw" or "text".
     */
    public void switchDisplay(String view)
    {
        display.switchView(view);
    }

    /**
     * Returns the draw view.
     * @return Returns the draw view.
     */
    public JPanel getCanvas()
    {
        return display.getCanvas();
    }

    /**
     * Returns the controller.
     * @return Returns the controller.
     */
    public CalcController getController()
    {
        return controller;
    }

    private void closeWindow()
    {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
