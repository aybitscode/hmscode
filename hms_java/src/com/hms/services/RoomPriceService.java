package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.RoomPrice;
import com.hms.util.BigDecimalType;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.view.RoomPriceEntry;
import com.hms.view.RoomPriceEntry;
import com.hotelmanagement.MainPage;

public class RoomPriceService {
	private RoomPrice rc;
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public RoomPriceService()
	{
		
	}
	public RoomPriceService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public RoomPriceService(RoomPrice rc)
	{
		this.rc = rc;		
	}
	
	public String generateRoomPriceID(){

		String prefix = "RP";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_ROOM_PRICE);
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
			pst = con.prepareStatement(DatabaseConstants.INSERT_ROOMPRICE);
			pst.setString(1, rc.getRoom_price_ID());
			pst.setString(2, rc.getRoom_category_ID());
			pst.setString(3, rc.getRoom_price());

			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public int submitFacilities(Object[] listFacilites, RoomPrice rp)
	{
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(DatabaseConstants.INSERT_ROOMPRICE_FACILITIES);
			
			for(int i=0;i<listFacilites.length;i++)
			{
			pst.setString(1, rp.getRoom_price_ID());
			pst.setString(2, ""+listFacilites[i]);
			s=pst.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public int submitNoFacilities(String param, RoomPrice rp)
	{
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(DatabaseConstants.INSERT_ROOMPRICE_FACILITIES);
			System.out.println(rp.getRoom_price_ID());
			pst.setString(1, rp.getRoom_price_ID());
			pst.setString(2, param);
			s=pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}	
	public void retrieveRoomsPrices()
	{
		RoomPriceEntry.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();

		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 0; 
		try {
		
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rk=stmt.executeQuery(DatabaseConstants.ALL_PRICE_COLS );
			RoomPrice obj_room_price = new RoomPrice();

			while(rk.next())
			{
				PreparedStatement pst;
				String facilities = "";
				double totalCost = 0;
				try {
					pst = con.prepareStatement(DatabaseConstants.FACILITIES_PRICE);
					pst.setString(1, rk.getString(1));
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						facilities = facilities+rs.getString(1)+", ";
						totalCost += Double.parseDouble(rs.getString(2));

					}
					
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				obj_room_price.setRoom_category_ID(rk.getString(2));				
				obj_room_price.setRoom_price(rk.getString(3));
				obj_room_price.setRoomFacilities(facilities);
				obj_room_price.setRoomFacilitiesPrice(""+BigDecimalType.roundDown(totalCost));
							
				
				tableModel.addRow(new Object[]{						
						obj_room_price.getRoom_category_ID(),
						obj_room_price.getRoom_price(),
						obj_room_price.getRoomFacilities(),
						obj_room_price.getRoomFacilitiesPrice()
						});
					slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoomPriceEntry.lblRows.setText("");
		RoomPriceEntry.lblRows.setText("No. of Rows: "+slno);
	}

	
	public void retrieveRoomsPrice(String query, String priceName)
	{
		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			RoomPriceEntry.btnSubmit.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			RoomPriceEntry.btnSubmit.setVisible(true);
		else
			RoomPriceEntry.btnSubmit.setVisible(false);
		
		int numRows = tableModel.getRowCount();

		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 1; 
		try {
			PreparedStatement pst1 = con.prepareStatement(query);
			pst1.setString(1, priceName);
			ResultSet rk = pst1.executeQuery();
			RoomPrice obj_room_price = new RoomPrice();
			if(rk.next())
			{
				PreparedStatement pst;
				String facilities = "";
				double totalCost = 0;
				try {
					pst = con.prepareStatement(DatabaseConstants.FACILITIES_PRICE);
					pst.setString(1, rk.getString(1));
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						facilities = facilities+rs.getString(1)+", ";
						totalCost += Double.parseDouble(rs.getString(2));

					}
					
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				obj_room_price.setRoom_category_ID(rk.getString(2));
				obj_room_price.setRoom_price(rk.getString(3));
				
				obj_room_price.setRoomFacilities(facilities);
				obj_room_price.setRoomFacilitiesPrice(""+BigDecimalType.roundDown(totalCost));
							
				tableModel.addRow(new Object[]{
						obj_room_price.getRoom_category_ID(),
						obj_room_price.getRoom_price(),
						obj_room_price.getRoomFacilities(),
						obj_room_price.getRoomFacilitiesPrice()
						});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoomPriceEntry.lblRows.setText("");
		RoomPriceEntry.lblRows.setText("No. of Rows: "+slno);
	}

	
	
}
