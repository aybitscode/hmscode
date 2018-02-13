package com.hotelmanagement;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class SwingSplitSample {
  public static void main(String args[]) {
    JFrame frame = new JFrame("JSplitPane Sample");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JSplitPane splitPane = new JSplitPane();
    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    frame.getContentPane().add(splitPane, BorderLayout.CENTER);
    frame.setSize(300, 200);
    frame.setVisible(true);
  }
}
    