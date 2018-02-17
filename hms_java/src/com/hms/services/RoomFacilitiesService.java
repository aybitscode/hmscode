package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.RoomFacilities;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.RoomFacilitiesEntry;
import com.hotelmanagement.MainPage;

public class RoomFacilitiesService {
	private RoomFacilities obj_roomFacilities;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public RoomFacilitiesService()
	{
		
	}
	public RoomFacilitiesService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public RoomFacilitiesService(RoomFacilities obj_roomFacilities)
	{
		this.obj_roomFacilities = obj_roomFacilities;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.SELECT_ALL_ROOM_FACILITIES);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String generateFacilitiesId(){

		String prefix = "RFC";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_ROOM_FACILITIES);
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
			pst = con.prepareStatement("insert into roomFacilities(roomFacilitiesID, roomFacilitiesName, roomFacilitiesDesc, roomFacilitiesPrice) "+ "values(?,?,?,?)");
			pst.setString(1, obj_roomFacilities.getRoom_facilities_ID());
			pst.setString(2, obj_roomFacilities.getRoom_facilities_name());
			pst.setString(3, obj_roomFacilities.getRoom_facilities_desc());
			pst.setString(4, obj_roomFacilities.getRoom_facilities_price());
			


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
		RoomFacilitiesEntry.btnSubmit.setVisible(false);
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
						rk.getString(3)
					});
					slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoomFacilitiesEntry.lblRows.setText("");
		RoomFacilitiesEntry.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieve(String query, String parameter)
	{
		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			RoomFacilitiesEntry.btnSubmit.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			RoomFacilitiesEntry.btnSubmit.setVisible(true);
		else
			RoomFacilitiesEntry.btnSubmit.setVisible(false);

		
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
						rk.getString(3)

				});			
				slno++;
			}
			RoomFacilitiesEntry.lblRows.setText("");
			RoomFacilitiesEntry.lblRows.setText("No. of Rows: "+slno);
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
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.ALL_ROOM_CATEGORY_ID, ""+RoomFacilitiesEntry.combo_search.getSelectedItem()))
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
						RoomFacilitiesEntry.sbm_consignCom.db.remove(RoomFacilitiesEntry.combo_search.getSelectedItem());
						RoomFacilitiesEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAll(query2);
						RoomFacilitiesEntry.combo_search.setSelectedItem("");
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
	public RoomFacilities populateForm(String param) {
		// TODO Auto-generated method stub
		RoomFacilities obj = new RoomFacilities();
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.TABLE_ROOM_FACILITES_NAME);
			pst.setString(1, param);
			ResultSet rk = pst.executeQuery();
			if(rk.next())
			{					
					obj.setRoom_facilities_ID(rk.getString(1));
					obj.setRoom_facilities_name(rk.getString(2));
					obj.setRoom_facilities_desc(rk.getString(3));
					obj.setRoom_facilities_price(rk.getString(4));
 
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public int updateForm(RoomFacilities objRF) {
		// TODO Auto-generated method stub
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(DatabaseConstants.UPDATE_FACILITIES);			
			pst.setString(1, objRF.getRoom_facilities_name());
			pst.setString(2, objRF.getRoom_facilities_desc());
			pst.setString(3, objRF.getRoom_facilities_price());
			pst.setString(4, objRF.getRoom_facilities_ID());
			


			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}	


	


}
