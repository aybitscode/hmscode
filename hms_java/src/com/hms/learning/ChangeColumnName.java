package com.hms.learning;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ChangeColumnName implements ActionListener{
  JTable table;
  public static void main(String arg[]){
  new ChangeColumnName();
  }

  public ChangeColumnName(){
  JFrame frame = new JFrame("Changing Column Name!");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  JPanel panel = new JPanel();
  panel.setLayout(new FlowLayout());
  String data[][] = {{"Vinod","MCA","Computer"},
   {"Deepak","PGDCA","History"},
   {"Ranjan","M.SC.","Biology"},
   {"Radha","BCA","Computer"}};
  String col[] = {"Name","Course","Subject"};
  DefaultTableModel model = new DefaultTableModel(data,col);
  table = new JTable(model);
  
  JTableHeader header = table.getTableHeader();
  header.setBackground(Color.yellow);
  JScrollPane pane = new JScrollPane(table);
  panel.add(pane);
  JButton btn = new JButton("Change");
  btn.addActionListener(this);
  panel.add(btn);
  frame.add(panel);
  frame.setUndecorated(true);
  frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
  frame.setSize(500,150);
  frame.setVisible(true);
  }

  public void ChangeName(JTable table, int col_index, String col_name){
  table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
  table.getColumn("Course").setHeaderValue("My Course Bro");
  }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	ChangeName(table,2,"Paper");
	table.updateUI();
}
}