package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Room;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.RoomEntry;
import com.hotelmanagement.MainPage;

public class RoomService {
	private Room obj_room;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public RoomService()
	{
		
	}
	public RoomService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public RoomService(Room obj_room)
	{
		this.obj_room = obj_room;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.SELECT_ALL_ROOMS);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getTotalRooms()
	{
		int count = 0;
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.COUNT_ROOMS);
        	ResultSet rs = pst.executeQuery();
        	rs.next();
        	count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public List<String> roomCategoryIDList(String query)
	{
		List<String> categoryList = new ArrayList<String>();
		categoryList.add("");
		try
		{
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				categoryList.add(rs.getString(1));	
			}
			rs.first();

		}catch(Exception e){
		}
		return categoryList;
	}
	
	public String generateRoomID(){

		String prefix = "R";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_ROOMS);
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
			pst = con.prepareStatement(DatabaseConstants.INSERT_ROOM);
			pst.setString(1, obj_room.getRoom_ID());
			pst.setString(2, obj_room.getRoomDoorNumber());
			pst.setString(3, obj_room.getRoomCategoryID());
//			pst.setString(4, obj_room.getRoom_count());
//			pst.setString(4, obj_room.getRoom_status());

			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public void retrieveAllRooms(String query)
	{
			RoomEntry.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 0; 
		try {
		
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rk=stmt.executeQuery(query);
			while(rk.next())
			{
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2)			
				});
				slno++;
 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoomEntry.lblRows.setText("");
		RoomEntry.lblRows.setText("No. of Rows: "+slno);
	}

	public void retrieveRoom(String query, String parameter)
	{
		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			RoomEntry.btnSubmit.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			RoomEntry.btnSubmit.setVisible(true);
		else
			RoomEntry.btnSubmit.setVisible(false);

		
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
			while(rk.next())
			{					
				
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),	
						rk.getString(3)

				});			
				slno++;
			}
			RoomEntry.lblRows.setText("");
			RoomEntry.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateRoom(String query, String param)
	{
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		int cl = 1;
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.ALL_ROOM_CATEGORY_ID, ""+RoomEntry.combo_search.getSelectedItem()))
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
							cl++;
							
						}
					pst.setString(cl, param);
					int s = pst.executeUpdate();
					if(s>0)
					{
						RoomEntry.sbm_consignCom.db.remove(RoomEntry.combo_search.getSelectedItem());
						RoomEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAllRooms(DatabaseConstants.TABLE_ROOM_COLS);
						RoomEntry.combo_search.setSelectedItem("");
					}
					else
						JOptionPane.showMessageDialog(null, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
							
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Foreign Key constraint / Duplicate room number",  "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}


	}
	public Room populateForm(String param) {
	 Room obj = new Room();
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.TABLE_ROOM_COLS_DNO);
			pst.setString(1, param);
			ResultSet rk = pst.executeQuery();
			while(rk.next())
			{					
				 
						obj.setRoom_ID(rk.getString(1));
						obj.setRoomDoorNumber(rk.getString(2));	
						obj.setRoomCategoryID(rk.getString(3));

			 
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public int updateForm(Room obj_room2) {
		// TODO Auto-generated method stub
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(DatabaseConstants.UPDATE_ROOM);		
			pst.setString(1, obj_room.getRoomDoorNumber());
			pst.setString(2, obj_room.getRoomCategoryID());
			pst.setString(3, obj_room.getRoom_ID());

			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}	
}
