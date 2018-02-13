package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hms.model.ReportDetails;
import com.hms.util.DBConnection;

public class BookingDetailsService {
	
	Connection con = DBConnection.getDBConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");

	public BookingDetailsService()
	{
		
	}
		
	public ReportDetails retrieve(String query1, String query2, String query3, String query4, String parameter)
	{
		ReportDetails obj_rpt = new ReportDetails();
		List<String> bookingList = new ArrayList<>();
		try {
			PreparedStatement pst1 = con.prepareStatement(query1);
			pst1.setString(1, parameter);
			ResultSet rk=pst1.executeQuery();
			
			PreparedStatement pst2 = con.prepareStatement(query2);
			PreparedStatement pst3 = con.prepareStatement(query3);
			PreparedStatement pst4 = con.prepareStatement(query4);
		
			if(rk.next())
			{
				obj_rpt.setBookingID(rk.getString(1));
				obj_rpt.setBookedDate(sdf.format(rk.getTimestamp(2)));
				obj_rpt.setCheckinDate(sdf1.format(rk.getTimestamp(3)));
				obj_rpt.setCheckoutDate(sdf.format(rk.getTimestamp(4)));
				obj_rpt.setRoomType(rk.getString(5));
				obj_rpt.setRoomNo(rk.getString(6));
				obj_rpt.setMobileNumber(rk.getString(7));
				obj_rpt.setRoomCost(rk.getString(8));
				obj_rpt.setFacilitiesCost(rk.getString(9));
				obj_rpt.setDays(rk.getString(10));
				obj_rpt.setGrossTotal(rk.getString(11));
				obj_rpt.setCouponName(rk.getString(12));
				obj_rpt.setStatus(rk.getString(13));
				obj_rpt.setPaymentMode(rk.getString(14));
				obj_rpt.setInvoiceID(rk.getString(15));
				obj_rpt.setAdults(rk.getString(16));
				obj_rpt.setChilds(rk.getString(17));
				obj_rpt.setAdvancePaid(rk.getString(18));	
				
 
			}
			pst2.setString(1, obj_rpt.getMobileNumber());
			ResultSet rm = pst2.executeQuery();
			if(rm.next())
			{
				obj_rpt.setCustomerName(rm.getString(1)+" "+rm.getString(2));
				obj_rpt.setAddress(rm.getString(3));
			}
			
			pst3.setString(1, obj_rpt.getCouponName());
			ResultSet rc = pst3.executeQuery();
			if(rc.next())
				obj_rpt.setDiscount(rc.getString(1));
			
			if(obj_rpt.getInvoiceID()!=null)
			{
				pst4.setString(1, obj_rpt.getInvoiceID());
				System.out.println("invoice id is "+obj_rpt.getInvoiceID());
				ResultSet rb = pst4.executeQuery();
				if(rb.next())
				{
					obj_rpt.setBookingCGST(rb.getString(1));
					obj_rpt.setBookingSGST(rb.getString(2));
					obj_rpt.setServiceCGST(rb.getString(3));
					obj_rpt.setServiceSGST(rb.getString(4));
					obj_rpt.setExtraBedCharges(rb.getString(5));
				}
			}
			else
			{
				obj_rpt.setBookingCGST("-");
				obj_rpt.setBookingSGST("-");
				obj_rpt.setServiceCGST("-");
				obj_rpt.setServiceSGST("-");
				obj_rpt.setExtraBedCharges("-");
				obj_rpt.setInvoiceID("-");
				obj_rpt.setPaymentMode("-");
				obj_rpt.setGrossTotal("-");
				obj_rpt.setDiscount("-");
				obj_rpt.setBalance("-");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj_rpt;
	}

	public ReportDetails retrieveBookedCancel(String query1, String query2, String parameter)
	{
		ReportDetails obj_rpt = new ReportDetails();
		
		try {
			PreparedStatement pst1 = con.prepareStatement(query1);
			pst1.setString(1, parameter);
			ResultSet rk=pst1.executeQuery();
			
			PreparedStatement pst2 = con.prepareStatement(query2);
		
			if(rk.next())
			{
				obj_rpt.setBookingID(rk.getString(1));
				obj_rpt.setBookedDate(sdf.format(rk.getTimestamp(2)));
				obj_rpt.setCheckoutDate(sdf.format(rk.getTimestamp(3)));
				obj_rpt.setRoomType(rk.getString(4));
				obj_rpt.setRoomNo(rk.getString(5));
				obj_rpt.setMobileNumber(rk.getString(6));
				obj_rpt.setRoomCost(rk.getString(7));
				obj_rpt.setFacilitiesCost(rk.getString(8));
				obj_rpt.setDays(rk.getString(9));
				obj_rpt.setStatus(rk.getString(10));
				
				pst2.setString(1, obj_rpt.getMobileNumber());
				ResultSet rm = pst2.executeQuery();
				if(rm.next())
					obj_rpt.setCustomerName(rm.getString(1)+" "+rm.getString(2));		
				
				
				
 
			}
			obj_rpt.setBookingCGST("-");
			obj_rpt.setBookingSGST("-");
			obj_rpt.setServiceCGST("-");
			obj_rpt.setServiceSGST("-");
			obj_rpt.setExtraBedCharges("-");
			obj_rpt.setInvoiceID("-");
			obj_rpt.setPaymentMode("-");
			obj_rpt.setGrossTotal("-");
			obj_rpt.setDiscount("-");
			obj_rpt.setBalance("-");
			obj_rpt.setAdults("-");
			obj_rpt.setChilds("-");
			obj_rpt.setAdvancePaid("-");
			obj_rpt.setBalance("-");
			obj_rpt.setCheckinDate("-");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj_rpt;
	}

	
}
