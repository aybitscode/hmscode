package com.hms.learning;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JLabelFontForeground {
  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame();
    frame.setTitle("JLabel Test");
    frame.setLayout(new FlowLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel label = new JLabel("First Name");
    label.setFont(new Font("Courier New", Font.ITALIC, 12));
    label.setForeground(Color.GRAY);

    frame.add(label);
    frame.pack();
    frame.setVisible(true);
  }
}