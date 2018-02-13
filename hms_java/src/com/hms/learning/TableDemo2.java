package com.hms.learning;
import javax.swing.*;
import java.awt.*;
 
public class TableDemo2 extends JPanel {
    private boolean DEBUG = false;
 
    public TableDemo2() {
        super(new GridLayout(1,0));
 
        JTable table = new JTable(5, 4);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
 
        Color light_blue = new Color (205, 235, 255);
        scrollPane.setBackground(light_blue);
        scrollPane.getViewport().setBackground(light_blue);
        table.getTableHeader().setBackground( new Color(50, 197, 210));
    }
 
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("TableDemo2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        TableDemo2 newContentPane = new TableDemo2();
        frame.setContentPane(newContentPane);
 
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {   
                createAndShowGUI();   
            }   
        });   
    }   
}