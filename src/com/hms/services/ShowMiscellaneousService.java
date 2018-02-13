package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.util.DBConnection;
import com.hms.view.BookingDetails;
import com.hms.view.CustomerEntry;
import com.hotelmanagement.MainPage;

public class ShowMiscellaneousService {
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public ShowMiscellaneousService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public void retrieveServices(String query, String parameter)
	{
		double service_cost = 0;
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, parameter);
			ResultSet rk = pst.executeQuery();
			while(rk.next())
			{			
				
						tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),	
						rk.getString(3)
					});	
			service_cost += Double.parseDouble(rk.getString(3));						
			}
			BookingDetails.total_service_cost = service_cost;
			System.out.println("serivce cost is"+service_cost);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
