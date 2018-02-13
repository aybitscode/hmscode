package com.hms.learning;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SSCCE extends JPanel
{
    public SSCCE()
    {
        setLayout( new BorderLayout() );

        JLabel tabbedPane = new JLabel();
        add(tabbedPane);

        AnimatedIcon icon = new AnimatedIcon(tabbedPane, 250, 3);
        ImageIcon duke = new ImageIcon(SSCCE.class.getResource("/images/loading.gif"));
        icon.addIcon( duke );
//
//        for (int angle = 30; angle < 360; angle += 30)
//        {
//            icon.addIcon( new RotatedIcon(duke, angle) );
//        }

        tabbedPane.setIcon(icon);
        icon.start();
    }


    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("SSCCE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SSCCE());
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
       // EventQueue.invokeLater( () -> createAndShowGUI() );

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });

    }
}
