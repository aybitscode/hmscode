package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.RoomCategory;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.RoomCategoryEntry;
import com.hotelmanagement.MainPage;

public class RoomCategoryService {
	private RoomCategory rc;
	Statement stmt;
	ResultSet rk;
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public RoomCategoryService()
	{
		
	}
	public RoomCategoryService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	
	public RoomCategoryService(RoomCategory rc)
	{
		this.rc = rc;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.SELECT_ALL_ROOM_CATEGORY);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateRoomCategory(String query)
	{
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		int cl = 1;
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.ALL_ROOM_CATEGORY_ID, ""+RoomCategoryEntry.combo_search.getSelectedItem()))
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
					pst.setString(cl, ""+RoomCategoryEntry.combo_search.getSelectedItem());
					int s = pst.executeUpdate();
					if(s>0)
					{
						RoomCategoryEntry.sbm_consignCom.db.remove(RoomCategoryEntry.combo_search.getSelectedItem());
						RoomCategoryEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAllRoomCategories(DatabaseConstants.SELECT_ALL_ROOM_CATEGORY);
						RoomCategoryEntry.combo_search.setSelectedItem("");
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

	
	public int submitService()
	{
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement("insert into roomcategory(roomCategoryID, roomCategoryName, roomCategoryDesc, roomCapacityName) "+ "values(?,?,?,?)");
			pst.setString(1, rc.getCategory_ID());
			pst.setString(2, rc.getCategory_Name());
			pst.setString(3, rc.getCategory_Desc());
			pst.setString(4, rc.getCategory_Beds());
//			pst.setString(5, rc.getRoom_capacity_ID());
//			pst.setString(6, rc.getRoom_facilities_ID());
			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public void retrieveAllRoomCategories(String query)
	{
		RoomCategoryEntry.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 0; 
		try {
		
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rk=stmt.executeQuery(query);
			RoomCategory obj_roomCategory = new RoomCategory();

			while(rk.next())
			{
				
				//obj_roomCategory.setSlno(rk.getString(1));
				obj_roomCategory.setCategory_ID(rk.getString(1));
				obj_roomCategory.setCategory_Name(rk.getString(2));
				obj_roomCategory.setCategory_Desc(rk.getString(3));
				obj_roomCategory.setCategory_Beds(rk.getString(4));
//				obj_roomCategory.setRoom_capacity_ID(rk.getString(6));
//				obj_roomCategory.setRoom_facilities_ID(rk.getString(7));
				 
				
				
				tableModel.addRow(new Object[]{
						obj_roomCategory.getCategory_ID(),
						obj_roomCategory.getCategory_Name(),
						obj_roomCategory.getCategory_Desc(),
						obj_roomCategory.getCategory_Beds(),
//						obj_roomCategory.getRoom_capacity_ID(),
//						obj_roomCategory.getRoom_facilities_ID()
						});
				slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoomCategoryEntry.lblRows.setText("");
		RoomCategoryEntry.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieveRoomCategory(String query, String parameter)
	{

		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			RoomCategoryEntry.btnSubmit.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			RoomCategoryEntry.btnSubmit.setVisible(true);
		else
			RoomCategoryEntry.btnSubmit.setVisible(false);

		
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
						rk.getString(3),	
						rk.getString(4)

				});			
				slno++;
			}
			RoomCategoryEntry.lblRows.setText("");
			RoomCategoryEntry.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void updateService(int id, RoomCategory rc)
	{
		
		try {
			PreparedStatement pst = con.prepareStatement("update roomcategory set roomCategoryName=?, roomCategoryDesc=?, roomCapacityName=?, roomCapacityID=?, roomFacilitiesID=?  where roomCategoryID=?");
			pst.setString(1, rc.getCategory_Name());
			pst.setString(2, rc.getCategory_Desc());
			pst.setString(3, rc.getCategory_Beds());
			pst.setString(4, rc.getRoom_capacity_ID());
			pst.setString(5, rc.getRoom_facilities_ID());
			pst.setInt(6, id);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public void deleteService(int id)
	{
		try {
			PreparedStatement pst=con.prepareStatement("delete from roomCategory where roomCategoryID=?");
			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public RoomCategory getRoomCategoryFirst()
	{
		RoomCategory lrc = new RoomCategory();
	
		try {

			rk.first();
			lrc.setCategory_ID(rk.getString(1));
			lrc.setCategory_Name(rk.getString(2));
			lrc.setCategory_Desc(rk.getString(3));
			lrc.setCategory_Beds(rk.getString(4));
			lrc.setRoom_capacity_ID(rk.getString(5));
			lrc.setRoom_facilities_ID(rk.getString(6));	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lrc;
	}
	public RoomCategory getRoomCategoryLast()
	{
		RoomCategory lrc = new RoomCategory();
	 
		try {
			 
			rk.last();
			lrc.setCategory_ID(rk.getString(1));
			lrc.setCategory_Name(rk.getString(2));
			lrc.setCategory_Desc(rk.getString(3));
			lrc.setCategory_Beds(rk.getString(4));
			lrc.setRoom_capacity_ID(rk.getString(5));
			lrc.setRoom_facilities_ID(rk.getString(6));	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lrc;
	}
	
	public RoomCategory getRoomCategoryPrevious()
	{
		RoomCategory lrc = new RoomCategory();
	 
		try {
	 
			rk.previous();
			lrc.setCategory_ID(rk.getString(1));
			lrc.setCategory_Name(rk.getString(2));
			lrc.setCategory_Desc(rk.getString(3));
			lrc.setCategory_Beds(rk.getString(4));
			lrc.setRoom_capacity_ID(rk.getString(5));
			lrc.setRoom_facilities_ID(rk.getString(6));	
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No Data Found", "Alert !", JOptionPane.ERROR_MESSAGE);

			 
		}
		
		return lrc;
	}
	
	public RoomCategory getRoomCategoryNext()
	{
		RoomCategory lrc = new RoomCategory();
	 
		try {
			rk.next();
			System.out.println("the next is"+rk.getString(1));
			lrc.setCategory_ID(rk.getString(1));
			lrc.setCategory_Name(rk.getString(2));
			lrc.setCategory_Desc(rk.getString(3));
			lrc.setCategory_Beds(rk.getString(4));
			lrc.setRoom_capacity_ID(rk.getString(5));
			lrc.setRoom_facilities_ID(rk.getString(6));	
			
		} catch (SQLException e) {
			getRoomCategoryPrevious();
			JOptionPane.showMessageDialog(null, "No Data Found", "Alert !", JOptionPane.ERROR_MESSAGE);
		}
		
		return lrc;
	}	

}
