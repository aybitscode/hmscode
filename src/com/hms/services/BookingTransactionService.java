package com.hms.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.BookingTransactions;
import com.hotelmanagement.MainPage;

public class BookingTransactionService {
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public BookingTransactionService()
	{
		
	}
	public BookingTransactionService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}

 
	public void retrieveAll()
	{
		
		String query;
		if(MainPage.user_role.equals(Constants.ADMIN)&&MainPage.user_mode.equals(Constants.MODE_OFF))
			query = DatabaseConstants.TABLE_BOOKING_COLS;
		else
			query = DatabaseConstants.TABLE_BOOKING_COLS_USER;
		
		//BookingTransactions.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 0; 
		try {
		
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rk = pst.executeQuery();
			String gross_total = null;
			String status = null;
			while(rk.next())
			{
				if(rk.getString(9)==null)
					gross_total = "-";
				else
					gross_total = rk.getString(9);				
				if(rk.getString(11)==null)
					status = "-";
				else
					status = rk.getString(11);
				
				
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),
						rk.getString(3),
						rk.getString(4),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7),
						rk.getString(8),
						gross_total,
						rk.getString(10),
						status
					});
					slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookingTransactions.lblRows.setText("");
		BookingTransactions.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieveAll(String query, String parameter)
	{

			//BookingTransactions.btnSave.setVisible(false);
		
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
			String gross_total = null;
			String status = null;
			while(rk.next())
			{
				if(rk.getString(9)==null)
					gross_total = "-";
				else
					gross_total = rk.getString(9);				
				if(rk.getString(11)==null)
					status = "-";
				else
					status = rk.getString(11);
				
				
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),
						rk.getString(3),
						rk.getString(4),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7),
						rk.getString(8),
						gross_total,
						rk.getString(10),
						status
					});
				slno++;
 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookingTransactions.lblRows.setText("");
		BookingTransactions.lblRows.setText("No. of Rows: "+slno);
	}

	
	public void retrieve(String query, String parameter)
	{
//		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//			BookingTransactions.btnSubmit.setVisible(false);
//		else if(MainPage.user_role.equals(s2))
//			BookingTransactions.btnSubmit.setVisible(true);
//		else
//			BookingTransactions.btnSubmit.setVisible(false);

		
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
			String gross_total = null;
			String status = null;
			if(rk.next())
			{
				if(rk.getString(9)==null)
					gross_total = "-";
				else
					gross_total = rk.getString(9);				
				if(rk.getString(11)==null)
					status = "-";
				else
					status = rk.getString(11);
				
				
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),
						rk.getString(3),
						rk.getString(4),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7),
						rk.getString(8),
						gross_total,
						rk.getString(10),
						status
					});		
				slno++;
			}
			BookingTransactions.lblRows.setText("");
			BookingTransactions.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateService(String query, String query2, String param) throws ParseException
	{
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		int cl = 1;
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.ALL_ROOM_CATEGORY_ID, ""+BookingTransactions.combo_search.getSelectedItem()))
				JOptionPane.showMessageDialog(null, "There are no changes",  "Error", JOptionPane.ERROR_MESSAGE);
			else
			{
				
				Connection con = DBConnection.getDBConnection();
				try {
					PreparedStatement pst=con.prepareStatement(query);
					for(int i=0;i<rowCount;i++)
						for(int j=0;j<colCount;j++)
						{
							pst.setString(cl, ""+tableModel.getValueAt(i, j));
//							if(cl==2||cl==3||cl==4)
//							{
//							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//							java.util.Date date = sdf1.parse(""+tableModel.getValueAt(i, j));
//							java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//							pst.setDate(cl, sqlDate);
//							}
							cl++;
							System.out.println("count is"+cl);
							
						}
					pst.setString(cl, param);
					int s = pst.executeUpdate();
					if(s>0)
					{
						BookingTransactions.sbm_consignCom.db.remove(BookingTransactions.combo_search.getSelectedItem());
						BookingTransactions.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAll();
						BookingTransactions.combo_search.setSelectedItem("");
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

	public int updateService(String query, List<String> list1, List<String> list2, String param) throws ParseException
	{
		int cl = 1;
		int s = 0;
			if(RowComparator.equalLists(list1, list2))
				JOptionPane.showMessageDialog(null, "There are no changes",  "Error", JOptionPane.ERROR_MESSAGE);
			else
			{
				
				Connection con = DBConnection.getDBConnection();
				try {
					PreparedStatement pst=con.prepareStatement(query);
					  for (String string : list2) {
						  pst.setString(cl, string);
						  System.out.println("the clis "+cl);
						  cl++;
					    }
					  System.out.println("the clis "+cl);
					  pst.setString(cl, param);
					
					 s = pst.executeUpdate();
							
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			return s;


	}	


	

	
}
