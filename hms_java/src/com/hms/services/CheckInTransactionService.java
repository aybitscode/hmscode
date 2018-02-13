package com.hms.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.CheckIn;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;

public class CheckInTransactionService {
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public CheckInTransactionService()
	{
		
	}
	public CheckInTransactionService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
 
 
	public void retrieveBookings()
	{
 
 
		try {
		
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rk=stmt.executeQuery(DatabaseConstants.TABLE_CHECK_IN_COLS);
			CheckIn obj_checkin = new CheckIn();
			int slno = 1;
			while(rk.next())
			{
				
				//obj_checkin.setSlno(rk.getString(1));
				//obj_checkin.setCheckInID(rk.getString(1));
				obj_checkin.setBookingID(rk.getString(1));
				obj_checkin.setCheckinDate(rk.getTimestamp(2));

				//obj_checkin.setCustomerID(rk.getString(5));
				obj_checkin.setAdvanceAmt(rk.getString(3));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("the checkin date is"+sdf.format(obj_checkin.getCheckinDate()));
				
tableModel.addRow(new Object[]{
						//slno++,
						//obj_checkin.getCheckInID(),
						obj_checkin.getBookingID(),
						sdf.format(obj_checkin.getCheckinDate()),
						
						//obj_checkin.getCustomerID(),
						obj_checkin.getAdvanceAmt()
						});
 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
