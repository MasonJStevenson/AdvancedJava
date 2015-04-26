package views;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;

import model.CalcController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

/**
 * Default numpad view for the calculator gui. Shows up with no plugin has been
 * loaded or when a loaded plugin does not add numpad functionality.
 * @author MasonS
 * @version 1.0
 */
public class DefaultNumpad extends Numpad
{
    /**
     * Constructor for the default numpad.
     * @param newController A controller for the parent gui.
     */
    public DefaultNumpad(CalcController newController)
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        super(newController);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 50, 50, 50 };
        gridBagLayout.rowHeights = new int[] { 50, 50, 50, 50 };
        gridBagLayout.columnWeights = new double[] { 0.0 };
        gridBagLayout.rowWeights = new double[] { 0.0 };
        setLayout(gridBagLayout);

        
        initButtonRowOne();
        initButtonRowTwo();
        initButtonRowThree();
        initButtonRowFour();
    }
    
    private void initButtonRowOne()
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        JButton seven = new JButton("7");
        seven.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("7");
            }
        });
        seven.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcSeven = new GridBagConstraints();
        gbcSeven.insets = new Insets(0, 0, 5, 20);
        gbcSeven.fill = GridBagConstraints.BOTH;
        gbcSeven.gridx = 0;
        gbcSeven.gridy = 0;
        add(seven, gbcSeven);

        JButton eight = new JButton("8");
        eight.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("8");
            }
        });
        eight.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcEight = new GridBagConstraints();
        gbcEight.insets = new Insets(0, 0, 5, 20);
        gbcEight.gridx = 1;
        gbcEight.gridy = 0;
        add(eight, gbcEight);

        JButton nine = new JButton("9");
        nine.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("9");
            }
        });
        nine.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcNine = new GridBagConstraints();
        gbcNine.insets = new Insets(0, 0, 5, 0);
        gbcNine.gridx = 2;
        gbcNine.gridy = 0;
        add(nine, gbcNine);
    }
    
    private void initButtonRowTwo()
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        JButton four = new JButton("4");
        four.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("4");
            }
        });
        four.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcFour = new GridBagConstraints();
        gbcFour.insets = new Insets(0, 0, 5, 20);
        gbcFour.gridx = 0;
        gbcFour.gridy = 1;
        add(four, gbcFour);

        JButton five = new JButton("5");
        five.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("5");
            }
        });
        five.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcFive = new GridBagConstraints();
        gbcFive.insets = new Insets(0, 0, 5, 20);
        gbcFive.gridx = 1;
        gbcFive.gridy = 1;
        add(five, gbcFive);

        JButton six = new JButton("6");
        six.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("6");
            }
        });
        six.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcSix = new GridBagConstraints();
        gbcSix.insets = new Insets(0, 0, 5, 0);
        gbcSix.gridx = 2;
        gbcSix.gridy = 1;
        add(six, gbcSix);
    }
    
    private void initButtonRowThree()
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        JButton one = new JButton("1");
        one.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("1");
            }
        });
        one.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcOne = new GridBagConstraints();
        gbcOne.insets = new Insets(0, 0, 5, 20);
        gbcOne.gridx = 0;
        gbcOne.gridy = 2;
        add(one, gbcOne);

        JButton two = new JButton("2");
        two.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("2");
            }
        });
        two.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcTwo = new GridBagConstraints();
        gbcTwo.insets = new Insets(0, 0, 5, 20);
        gbcTwo.gridx = 1;
        gbcTwo.gridy = 2;
        add(two, gbcTwo);

        JButton three = new JButton("3");
        three.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("3");
            }
        });
        three.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcThree = new GridBagConstraints();
        gbcThree.insets = new Insets(0, 0, 5, 0);
        gbcThree.gridx = 2;
        gbcThree.gridy = 2;
        add(three, gbcThree);
    }
    
    private void initButtonRowFour()
    {
        //AUTO GENERATED BY WINDOWBUILDER PRO FOR ECLIPSE
        JButton zero = new JButton("0");
        zero.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("0");
            }
        });
        zero.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcZero = new GridBagConstraints();
        gbcZero.insets = new Insets(0, 0, 0, 20);
        gbcZero.gridx = 0;
        gbcZero.gridy = 3;
        add(zero, gbcZero);

        JButton decimal = new JButton(".");
        decimal.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append(".");
            }
        });
        decimal.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcDecimal = new GridBagConstraints();
        gbcDecimal.insets = new Insets(0, 0, 0, 20);
        gbcDecimal.gridx = 1;
        gbcDecimal.gridy = 3;
        add(decimal, gbcDecimal);

        JButton negation = new JButton("(-)");
        negation.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().append("-");
            }
        });
        negation.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbcNegation = new GridBagConstraints();
        gbcNegation.gridx = 2;
        gbcNegation.gridy = 3;
        add(negation, gbcNegation);
    }
}
