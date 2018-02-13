package com.hms.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.CheckOut;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;

public class CheckOutTransactionService {
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public CheckOutTransactionService()
	{
		
	}
	public CheckOutTransactionService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
 
 
	public void retrieveBookings()
	{
 
 
		try {
		
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rk=stmt.executeQuery(DatabaseConstants.TABLE_CHECKOUT_COLS);
			CheckOut obj_checkOut = new CheckOut();
			int slno = 1;
			while(rk.next())
			{
				
//				//obj_checkOut.setSlno(rk.getString(1));
//				//obj_checkOut.setCheckOutID(rk.getString(1));
//				obj_checkOut.setBookingID(rk.getString(1));
//				obj_checkOut.setCheckInDate(rk.getString(2));
//				obj_checkOut.setCheckOutDate(rk.getString(3));
//				
//				//obj_checkOut.setCustomerID(rk.getString(6));
//				//obj_checkOut.setRoomDoorNumber(rk.getString(6));
//				obj_checkOut.setRoomDoorNumber(rk.getString(4));
//				obj_checkOut.setRoomCategoryID(rk.getString(5));
//				obj_checkOut.setRoomCost(rk.getString(6));
//				obj_checkOut.setRoomFacilitesCost(rk.getString(7));
//				obj_checkOut.setTotalDays(rk.getString(8));
//				obj_checkOut.setAdvanceAmt(rk.getString(9));
//				obj_checkOut.setTotalCost(rk.getString(10));
				
				
				
				
tableModel.addRow(new Object[]{
				slno++,
//				//obj_checkOut.getCheckOutID(),
//				obj_checkOut.getBookingID(),
//				obj_checkOut.getCheckInDate(),
//				obj_checkOut.getCheckOutDate(),				
//				//obj_checkOut.getCustomerID(),
//				//obj_checkOut.getRoomDoorNumber(),				
//				obj_checkOut.getRoomDoorNumber(),
//				obj_checkOut.getRoomCategoryID(),
//				obj_checkOut.getRoomCost(),
//				obj_checkOut.getRoomFacilitesCost(),
//				obj_checkOut.getTotalDays(),
//				obj_checkOut.getAdvanceAmt(),
//				obj_checkOut.getTotalCost()
				rk.getString(1),
				rk.getString(2)
						});
 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
