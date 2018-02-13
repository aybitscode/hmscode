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

import com.hms.model.RoomCapacity;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.RoomCapacityEntry;
import com.hotelmanagement.MainPage;

public class RoomCapacityService {
	private RoomCapacity obj_roomCapacity;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public RoomCapacityService()
	{
		
	}
	public RoomCapacityService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public RoomCapacityService(RoomCapacity obj_roomCapacity)
	{
		this.obj_roomCapacity = obj_roomCapacity;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.SELECT_ALL_ROOM_CAPACITY);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String generateRoomCapacityID(){

		String prefix = "RC";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_ROOM_CAPACITY);
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
			pst = con.prepareStatement("insert into roomcapacity(roomCapacityID, roomCapacityName, roomCapacityAdults, roomCapacityChilds, totalCapacity) "+ "values(?,?,?,?,?)");
			pst.setString(1, obj_roomCapacity.getRoom_capacity_ID());
			pst.setString(2, obj_roomCapacity.getRoom_capacity_name());
			pst.setString(3, obj_roomCapacity.getRoom_capacity_adults());
			pst.setString(4, obj_roomCapacity.getRoom_capacity_childs());
			pst.setString(5, Integer.toString(Integer.parseInt(obj_roomCapacity.getRoom_capacity_adults())+Integer.parseInt(obj_roomCapacity.getRoom_capacity_childs())));

			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public List<String> retrieveCapacityName(String query)
	{
		List<String> capacityList = new ArrayList<String>();
		capacityList.add("");
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				capacityList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return capacityList;
	}
	public void retrieveAll(String query)
	{
		RoomCapacityEntry.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 0;
		try {
		
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rk = pst.executeQuery();
			RoomCapacity obj_roomCapacity = new RoomCapacity();
			
			while(rk.next())
			{
				obj_roomCapacity.setRoom_capacity_name(rk.getString(1));
				obj_roomCapacity.setRoom_capacity_adults(rk.getString(2));
				obj_roomCapacity.setRoom_capacity_childs(rk.getString(3));
				obj_roomCapacity.setRoom_total_capacity(rk.getString(4));
				
				
				tableModel.addRow(new Object[]{
						obj_roomCapacity.getRoom_capacity_name(),
						obj_roomCapacity.getRoom_capacity_adults(),
						obj_roomCapacity.getRoom_capacity_childs(),
						obj_roomCapacity.getRoom_total_capacity()
					});
				slno++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoomCapacityEntry.lblRows.setText("");
		RoomCapacityEntry.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieve(String query, String parameter)
	{
		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			RoomCapacityEntry.btnSubmit.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			RoomCapacityEntry.btnSubmit.setVisible(true);
		else
			RoomCapacityEntry.btnSubmit.setVisible(false);

		
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
			RoomCapacityEntry.lblRows.setText("");
			RoomCapacityEntry.lblRows.setText("No. of Rows: "+slno);
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
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.ALL_ROOM_CATEGORY_ID, ""+RoomCapacityEntry.combo_search.getSelectedItem()))
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
						RoomCapacityEntry.sbm_consignCom.db.remove(RoomCapacityEntry.combo_search.getSelectedItem());
						RoomCapacityEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAll(query2);
						RoomCapacityEntry.combo_search.setSelectedItem("");
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

	
}
