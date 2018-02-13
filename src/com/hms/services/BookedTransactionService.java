package com.hms.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.util.DBConnection;
import com.hms.view.BookedTransactions;
import com.hms.view.BookingCancelTransactions;
import com.hotelmanagement.MainPage;

public class BookedTransactionService {
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public BookedTransactionService()
	{
		
	}
	public BookedTransactionService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}

 
	public void retrieveAllBookingsStatus(String query, String status) throws Exception
	{
		BookedTransactions.btnSave.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		} 
		int slno = 0;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, status);
			ResultSet rk = pst.executeQuery();
			while(rk.next())
			{
			
				
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getDate(2),
						rk.getDate(3),
						rk.getDate(4),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7),
						rk.getString(8),
						rk.getString(9),
						rk.getString(10),
						rk.getString(11),
						rk.getString(12)	,
						rk.getString(13),
						rk.getString(14),
						rk.getString(15),
						rk.getString(16),
						rk.getString(17),
						rk.getString(18)
				});
				slno++;
 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//con.close();
		}
		BookedTransactions.lblRows.setText("");
		BookedTransactions.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieveBooking(String query, String parameter)
	{

		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			BookedTransactions.btnSave.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			BookedTransactions.btnSave.setVisible(true);
		else
			BookedTransactions.btnSave.setVisible(false);

		
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
						rk.getString(4),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7),
						rk.getString(8),
						rk.getString(9),
						rk.getString(10),
						rk.getString(11),
						rk.getString(12),
						rk.getString(13),
						rk.getString(14),
						rk.getString(15),
						rk.getString(16),
						rk.getString(17),
						rk.getString(18)


				});
				slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BookedTransactions.lblRows.setText("");
		BookedTransactions.lblRows.setText("No. of Rows: "+slno);
	}

	
}
