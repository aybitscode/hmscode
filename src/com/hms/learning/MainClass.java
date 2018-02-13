package com.hms.learning;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

public class MainClass {
  public static void main(String args[]) throws Exception {
    JFrame frame = new JFrame("UIDefaults Example");
    frame.setDefaultCloseOperation(3);
    Container c = frame.getContentPane();
    UIManager.put("ToggleButton.background", Color.blue);
    UIManager.put("Button.foreground", Color.white);
    Font f = new Font("Serif", Font.ITALIC, 24);
    UIManager.put("Button.font", f);

    JToggleButton north = new JToggleButton("North");
    JButton south = new JButton("South");
    JButton east = new JButton("East");
    JButton west = new JButton("West");
    JButton center = new JButton("Center");
    c.add(north, BorderLayout.NORTH);
    c.add(south, BorderLayout.SOUTH);
    c.add(east, BorderLayout.EAST);
    c.add(west, BorderLayout.WEST);
    c.add(center, BorderLayout.CENTER);
    frame.pack();
    frame.show();

  }

}
