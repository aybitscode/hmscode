package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.CheckIn;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.view.CheckInEntry;
import com.hms.view.CheckInHistory;
import com.hotelmanagement.MainPage;

public class CheckInService {
	private CheckIn obj_checkIn;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
	
	public CheckInService()
	{
		
	}
	public CheckInService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public CheckInService(CheckIn obj_checkIn)
	{
		this.obj_checkIn = obj_checkIn;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.TABLE_CHECK_IN_COLS);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String generateCheckInId(){

		String prefix = "CHI";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_CHECK_IN);
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
	
	public int submitService()
	{
		PreparedStatement pst;
		int s = 0;
		try {   
			pst = con.prepareStatement(DatabaseConstants.INSERT_CHECKIN);
			pst.setString(1, obj_checkIn.getCheckInID());
			pst.setTimestamp(2, obj_checkIn.getCheckinDate());
			pst.setString(3, obj_checkIn.getBookingID());
			pst.setString(4, obj_checkIn.getTotalAdults());
			pst.setString(5, obj_checkIn.getTotalChilds());
			//pst.setString(4, obj_checkIn.getCustomerID());
			pst.setString(6, obj_checkIn.getAdvanceAmt());
			pst.setString(7, MainPage.userID);
									
			PreparedStatement pstBook=con.prepareStatement(DatabaseConstants.UPDATE_BOOKING_STATUS);
			pstBook.setString(1, Constants.CHECKIN);
			pstBook.setString(2,obj_checkIn.getBookingID());
			pstBook.setString(3, obj_checkIn.getRoomDoorNumber());
			
			s=pst.executeUpdate();
			
			if(s>0)
			{
				pstBook.execute();				
			}
			con.commit();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public String retrieveRoomDoorNumber(String query, String param)
	{
		String roomNumber = null;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, param);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				roomNumber = rs.getString(1);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomNumber;
		
	}
	public void retrieveAll(String query)
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
			ResultSet rk = pst.executeQuery();


			while(rk.next())
			{
				tableModel.addRow(new Object[]{
						rk.getString(1), 
						rk.getString(2),
						rk.getString(3)+" "+rk.getString(4),
						rk.getString(5),
						sdf.format(rk.getTimestamp(6)),
						rk.getString(7),
						rk.getString(8),
						rk.getString(9)
						
				});
				slno++;
			}
			CheckInHistory.lblRows.setText("");
			CheckInHistory.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void retrieveCheckInTransaction(String query, String parameter)
	{

//		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//			CheckInEntry.btnSubmit.setVisible(false);
//		else if(MainPage.user_role.equals(s2))
//			CheckInEntry.btnSubmit.setVisible(true);
//		else
//			CheckInEntry.btnSubmit.setVisible(false);

		
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
 
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, parameter);
			ResultSet rk = pst.executeQuery();
			int slno = 0;
			while(rk.next())
			{					
				
				tableModel.addRow(new Object[]{
						rk.getString(1), 
						rk.getString(2),
						rk.getString(3)+" "+rk.getString(4),
						rk.getString(5),
						sdf.format(rk.getTimestamp(6)),
						rk.getString(7),
						rk.getString(8),
						rk.getString(9)
						});
				slno++;
 
			}
//			CheckInEntry.lblRows.setText("");
//			CheckInEntry.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> retrieveBookingIDs(String query, String param)
	{
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, param);
			ResultSet rk = stmt.executeQuery();
			list.add("");
			while(rk.next())
			{		
			list.add(rk.getString(1));
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	

}
