package com.hms.learning;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class JTableCombo {
  public static void main(String[] argv) throws Exception {
	 JFrame f = new JFrame();
	 
	 f.setSize(500,500);
	 f.setLocation(100,100);
	 f.setLayout(new FlowLayout());
	 
    JTable table = new JTable();
    DefaultTableModel model = (DefaultTableModel) table.getModel();

    model.addColumn("A", new Object[] { "item1" });
    model.addColumn("B", new Object[] { "item2" });

    String[] values = new String[] { "1", "2", "3" };

    TableColumn col = table.getColumnModel().getColumn(1);
    col.setCellEditor(new MyComboBoxEditor(values));
    col.setCellRenderer(new MyComboBoxRenderer(values));
    f.add(table);
    f.setVisible(true);
  }
}


