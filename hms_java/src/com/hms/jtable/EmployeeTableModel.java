package com.hms.jtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
 

 
public class EmployeeTableModel extends AbstractTableModel
{
    private final List<Employee> employeeList;
    public static int storeIndex;
     
    private final String[] columnNames = new String[] {
            "Id", "Name", "Hourly Rate", "Part Time", "Update"
    };
    private final Class[] columnClass = new Class[] {
        Integer.class, String.class, Double.class, Boolean.class, JButton.class
    };
 
    public EmployeeTableModel(List<Employee> employeeList)
    {
        this.employeeList = employeeList;
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return employeeList.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Employee row = employeeList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
        	storeIndex = rowIndex;
        	System.out.println(row.getName()+"row no is"+rowIndex);
            return row.getName();
        }
        else if(2 == columnIndex) {
            return row.getHourlyRate();
        }
        else if(3 == columnIndex) {
            return row.isPartTime();
        }
        else if(4 == columnIndex) {
            return row.getButton();
        }
        return null;
    }
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
 
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Employee row = employeeList.get(rowIndex);
        if(0 == columnIndex) {
            row.setId((Integer) aValue);
        }
        else if(1 == columnIndex) {
            row.setName((String) aValue);
        }
        else if(2 == columnIndex) {
            row.setHourlyRate((Double) aValue);
        }
        else if(3 == columnIndex) {
            row.setPartTime((Boolean) aValue);
        }
        else if(3 == columnIndex) {
            row.setButton((JButton) aValue);
        }
    }
 
}