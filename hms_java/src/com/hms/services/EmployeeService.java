package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Employee;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.PasswordEncrypt;
import com.hms.util.RowComparator;
import com.hms.view.EmployeeEntry;
import com.hms.view.EmployeeEntry;
import com.hotelmanagement.MainPage;

public class EmployeeService {
	private Employee obj_employee;
	Statement stmt;
	ResultSet rk;
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public EmployeeService()
	{
		
	}
	public EmployeeService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	
	public String generateEmployeeId(){

		String prefix = "E";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_EMPLOYEES);
	        	resultSet.next();
	        	count = resultSet.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 
        	 count += 1;
        	 
        	 serialNo = String.valueOf(count);
        	
            serialNo = String.format("%3s", serialNo).replace(' ', '0');
		
		return prefix+serialNo;
	}
	
	public EmployeeService(Employee obj_employee)
	{
		this.obj_employee = obj_employee;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.ALL_EMPLOYEES);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int submitService()
	{
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(DatabaseConstants.INSERT_EMPLOYEE);
			pst.setString(1, generateEmployeeId());
			pst.setString(2, obj_employee.getFirst_name());
			pst.setString(3, obj_employee.getLast_name());
			pst.setString(4, obj_employee.getUserName());
			pst.setString(5, obj_employee.getPassword());
			pst.setString(6, obj_employee.getEmail());
			pst.setString(7, obj_employee.getMobile());			
			pst.setDate(8, obj_employee.getBirthDate());
			pst.setString(9, obj_employee.getGender());
			pst.setString(10, obj_employee.getRole());
			pst.setString(11, Constants.MODE_OFF);
			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}	
	public void retrieveAll(String query)
	{
		//EmployeeEntry.btnSubmit.setVisible(false);
		//EmployeeEntry.btnDelete.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 0; 
		try {
		
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rk = pst.executeQuery();
			while(rk.next())
			{
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),
						rk.getString(3),
						PasswordEncrypt.decrypt(rk.getString(4)),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7),
						rk.getString(8)
					});
					slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//EmployeeEntry.lblRows.setText("");
		//EmployeeEntry.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieve(String query, String parameter)
	{
//		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//		{
//			EmployeeEntry.btnSubmit.setVisible(false);
//			EmployeeEntry.btnDelete.setVisible(false);
//		}
//		else if(MainPage.user_role.equals(s2))
//		{
//			EmployeeEntry.btnSubmit.setVisible(true);
//			EmployeeEntry.btnDelete.setVisible(true);
//		}
//		else
//		{
//			EmployeeEntry.btnSubmit.setVisible(false);
//			EmployeeEntry.btnDelete.setVisible(false);
//		}

		
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
			int slno = 0;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, parameter);
			ResultSet rk = pst.executeQuery();
			if(rk.next())
			{					
				
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),
						rk.getString(3),
						PasswordEncrypt.decrypt(rk.getString(4)),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7),
						rk.getString(8)
				});			
				slno++;
			}
//			EmployeeEntry.lblRows.setText("");
//			EmployeeEntry.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateService(String query, String query2, String param)
	{
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		int cl = 1;
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.TABLE_EMPLOYEE_MOBILE, ""+EmployeeEntry.combo_search.getSelectedItem()))
				JOptionPane.showMessageDialog(null, "There are no changes",  "Error", JOptionPane.ERROR_MESSAGE);
			else
			{
				
				Connection con = DBConnection.getDBConnection();
				try {
					PreparedStatement pst=con.prepareStatement(query);
					for(int i=0;i<rowCount;i++)
						for(int j=0;j<colCount;j++)
						{
							if(j==3)							
								pst.setString(cl, PasswordEncrypt.encrypt(""+tableModel.getValueAt(i, j)));					
							else
								pst.setString(cl, ""+tableModel.getValueAt(i, j));
							cl++;
							
						}
					pst.setString(cl, param);
					int s = pst.executeUpdate();
					if(s>0)
					{
						EmployeeEntry.sbm_consignCom.db.remove(EmployeeEntry.combo_search.getSelectedItem());
						EmployeeEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 5));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAll(query2);
						EmployeeEntry.combo_search.setSelectedItem("");
					}
					else
						JOptionPane.showMessageDialog(null, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
							
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}


	}	
	
	public int deleteService(String query, String param)
	{
		int s = 0;
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, param);
			s = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}


	

}
