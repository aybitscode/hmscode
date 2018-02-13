package com.hms.learning;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class TestPanel extends JPanel {

   private static final int AREA_COUNT = 4;

   public TestPanel() {
      setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      setLayout(new GridLayout(0, 1, 0, 5));
      for (int i = 0; i < AREA_COUNT; i++) {
         JTextArea area = new JTextArea(5, 30);
         area.setLineWrap(true);
         area.setWrapStyleWord(true);
         JPanel wrapPanel = new JPanel(new BorderLayout());
         wrapPanel.add(new JLabel("JTextArea " + i), BorderLayout.PAGE_START);
         wrapPanel.add(new JScrollPane(area), BorderLayout.CENTER);
         add(wrapPanel);
      }
   }

   private static void createAndShowUI() {
      JFrame frame = new JFrame("TestPanel");
      frame.getContentPane().add(new TestPanel());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            createAndShowUI();
         }
      });
   }
}