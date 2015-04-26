package plugins;

import model.CalcController;
import views.ControlPanel;
import views.Display;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

/**
 * Selecting buttons on this control panel draws shapes to the draw view in a
 * calculator gui.
 * 
 * @author MasonS
 * @version 1.0
 */
public class DrawingPanel extends ControlPanel
{

    /**
     * Constructor for DrawingPanel.
     * @param newController A controller for this panel's parent gui.
     */
    public DrawingPanel(CalcController newController)
    {
        super(newController);
        getController().switchDisplay(Display.kDrawView);

        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setMaximumSize(new Dimension(32767, 200));
        verticalStrut.setPreferredSize(new Dimension(0, 200));
        add(verticalStrut);

        JButton circleButton = new JButton("Draw Circle");
        circleButton.addActionListener(new DrawAction(this));
        circleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(circleButton);

        Component verticalStrut1 = Box.createVerticalStrut(20);
        add(verticalStrut1);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().clearCanvas();
            }
        });

        JButton graphButton = new JButton("Draw Graph");
        graphButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                getController().sendDrawingToCanvas(new GraphObject());
            }
        });
        graphButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(graphButton);

        Component verticalStrut2 = Box.createVerticalStrut(20);
        add(verticalStrut2);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(clearButton);
        // TODO Auto-generated constructor stub
    }

    /**
     * Action fired from draw button.
     */
    private class DrawAction implements ActionListener
    {
        private DrawingPanel panel;

        public DrawAction(DrawingPanel newPanel)
        {
            panel = newPanel;
        }

        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            panel.getController().sendDrawingToCanvas(new CircleObject());
        }

    }

    /**
     * A drawing of a circle.
     */
    private class CircleObject extends JPanel
    {
        private static final int kXLoc = 150;
        private static final int kYLoc = 200;
        private static final int kSize = 200;
        
        public CircleObject()
        {
            this.setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics gfx)
        {
            super.paintComponent(gfx);
            Graphics2D gfx2d = (Graphics2D) gfx;
            gfx2d.setColor(Color.BLACK);
            gfx2d.fillOval(kXLoc, kYLoc, kSize, kSize);
        }
    }

    /**
     * A drawing of a simple line graph.
     */
    private class GraphObject extends JPanel
    {
        //private static final int kTwenty = 20;
        //private static final int kFourHun = 400;
        //private static final int kFourTwenty = 420;
        
        public GraphObject()
        {
            this.setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics gfx)
        {
            super.paintComponent(gfx);
            Graphics2D gfx2d = (Graphics2D) gfx;
            gfx2d.setColor(Color.BLACK);
            // gfx2d.fillOval(5, 5, 200, 200);
            gfx2d.drawLine(20, 20, 20, 400);
            gfx2d.drawLine(20, 400, 420, 400);
            gfx2d.setColor(Color.RED);
            gfx2d.drawLine(40, 40, 340, 390);
        }
    }

}
