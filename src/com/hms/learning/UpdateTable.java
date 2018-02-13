package com.hms.learning;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UpdateTable {
  public static void main(String[] argv) throws Exception {
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    model.addColumn("Col1");
    model.addRow(new Object[] { "r1" });
    model.addRow(new Object[] { "r2" });
    model.addRow(new Object[] { "r3" });
    
    Vector data = model.getDataVector();
    Vector row = (Vector) data.elementAt(1);
    // Overwrite the first row with the copy
    Vector firstRow = (Vector) data.elementAt(0);
    for (int i = 0; i < row.size(); i++) {
      firstRow.set(i, row.get(i));
    }
    
    JFrame f = new JFrame();
    f.setSize(300, 300);
    f.add(new JScrollPane(table));
    f.setVisible(true);
  }
}