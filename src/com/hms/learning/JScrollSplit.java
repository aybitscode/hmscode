package com.hms.learning;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class JScrollSplit extends JFrame {

  public JScrollSplit() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(400, 400);
    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    split.setDividerLocation(200);
    add(split);

    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());
   // panel1.add(new JLabel("top panel"), BorderLayout.NORTH);
    
//    JPanel myDrawPanel1 = new JPanel();
//    myDrawPanel1.setPreferredSize(new Dimension(300, 300));
//    myDrawPanel1.add(new JLabel("draw panel here!"));
//    panel1.add(new JScrollPane(myDrawPanel1), BorderLayout.NORTH);

    
    JPanel myDrawPanel = new JPanel();
    myDrawPanel.setPreferredSize(new Dimension(300, 300));
    myDrawPanel.add(new JLabel("draw panel here!"));
    panel1.add(new JScrollPane(myDrawPanel), BorderLayout.CENTER);

    split.setTopComponent(panel1);

    JPanel panel2 = new JPanel();
    panel2.add(new JLabel("bottom panel"));
    panel2.setPreferredSize(new Dimension(300, 300));
    split.setBottomComponent(new JScrollPane(panel2));
    setVisible(true);
  }
  public static void main(String[] args) {
    new JScrollSplit();
  }
}