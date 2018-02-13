package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hms.model.Welcome;
import com.hms.util.DBConnection;

public class WelcomeService {
	Connection con = DBConnection.getDBConnection();
	
	public WelcomeService(){
		
	}
	public List<Welcome> currentCheckins(String query, String query1)
	{
		List<Welcome> welList = new ArrayList<>();
		Welcome obj_wel = null;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			PreparedStatement pst1 = con.prepareStatement(query1);
			ResultSet rs1;
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				obj_wel = new Welcome(); 
				obj_wel.setBookingID(rs.getString(1));
				obj_wel.setBookedDate(rs.getString(2));
				obj_wel.setRoomType(rs.getString(3));
				obj_wel.setRoomNo(rs.getString(4));
				obj_wel.setMobileNo(rs.getString(5));
				
				
				pst1.setString(1, obj_wel.getMobileNo());
				rs1 = pst1.executeQuery();
				if(rs1.next())
				{
					obj_wel.setCustomerName(rs1.getString(1)+" "+ rs1.getString(2));
				}
				
				welList.add(obj_wel);
			} 
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return welList;
	}

}
