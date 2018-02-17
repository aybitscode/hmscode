package com.hms.services;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Hotel;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.HotelEntry;
import com.hotelmanagement.MainPage;

public class HotelService {
	private Hotel obj_hotel;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public HotelService()
	{
		
	}
	public HotelService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public HotelService(Hotel obj_hotel)
	{
		this.obj_hotel = obj_hotel;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.SELECT_ALL_HOTEL);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String generateHotelId(){

		String prefix = "H";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_HOTEL);
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
	
	public int submitService(String query)
	{
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, obj_hotel.getHotel_ID());
			pst.setString(2, obj_hotel.getHotel_name());
			pst.setString(3, obj_hotel.getHotel_desc());
			pst.setString(4, obj_hotel.getHotel_total_rooms());
			pst.setString(5, obj_hotel.getHotel_total_floors());

			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
 	
	public String retrieveHotelName(String query)
	{
		String hotelName = null;
		try {
		
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rk = pst.executeQuery();
			if(rk.next())
			{
				hotelName = rk.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotelName;
	}
	
	
	public int retrieveTotalRooms(String query)
	{
		int  totalRooms = 0;
		try {
		
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rk = pst.executeQuery();
			if(rk.next())
			{
				totalRooms = Integer.parseInt(rk.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalRooms;
	}
	public void retrieveAll(String query)
	{
		HotelEntry.btnSubmit.setVisible(false);
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
						rk.getString(3),
						rk.getString(4)
					});
					slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HotelEntry.lblRows.setText("");
		HotelEntry.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieve(String query, String parameter)
	{
		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			HotelEntry.btnSubmit.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			HotelEntry.btnSubmit.setVisible(true);
		else
			HotelEntry.btnSubmit.setVisible(false);

		
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
						rk.getString(4)

				});			
				slno++;
			}
			HotelEntry.lblRows.setText("");
			HotelEntry.lblRows.setText("No. of Rows: "+slno);
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
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.TABLE_HOTEL_NAME, ""+HotelEntry.combo_search.getSelectedItem()))
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
						HotelEntry.sbm_consignCom.db.remove(HotelEntry.combo_search.getSelectedItem());
						HotelEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAll(query2);
						HotelEntry.combo_search.setSelectedItem("");
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
