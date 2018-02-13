package com.hms.learning;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TestEntryPoint {

    private JButton btn1 = new JButton("Test");
    private JButton button1 = new JButton("Toggle");

    public TestEntryPoint() {

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                button1.setEnabled(!button1.isEnabled());
                UIManager.put("Button.disabledText", Color.red);

            }
        });
        
        JFrame f = new JFrame("ButtonTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(2, 2));
        f.add(button1);
        f.add(btn1);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TestEntryPoint();
            }
        });
    }
}