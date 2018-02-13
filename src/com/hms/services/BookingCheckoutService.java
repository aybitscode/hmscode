package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Booking;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;

public class BookingCheckoutService {
	private Booking obj_booking;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	
	Connection con = DBConnection.getDBConnection();
	public BookingCheckoutService()
	{
		
	}
	public BookingCheckoutService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public BookingCheckoutService(Booking obj_booking)
	{
		this.obj_booking = obj_booking;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery("select * from booking");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String generateBookingId(){

		String prefix = "B";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_BOOKING);
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
//	
//	public Booking retrieveBookings(String bookingID)
//	{
//		Booking obj_booking = new Booking(); 
//		try {
//			PreparedStatement pst = con.prepareStatement("select bookingDate, bookingArrivalDate, bookingDepartureDate, roomCategoryID, roomDoorNumber, mobile, bookingRoomCost, facilitiesCost, bookingTotalNights, bookingTotalCost, gstAmount, grossAmount, couponName, finalAmount, advanceAmt from booking t1 inner join checkin t2 on t1.bookingID = t2.bookingID where t1.bookingID = ?");
//			pst.setString(1, bookingID);
//			ResultSet rk=pst.executeQuery();
//			
//			PreparedStatement pst1 = con.prepareStatement("select firstName, lastName from customers where mobile = ?");
//			PreparedStatement pst2 = con.prepareStatement("select couponDiscount from coupons where couponName = ?");
//			if(rk.next())
//			{
//				//obj_booking.setBooking_Id(rk.getString(1));
//				obj_booking.setBooking_Date(rk.getDate(1));
//				obj_booking.setBooking_arrival_date(rk.getDate(2));
//				obj_booking.setBooking_departure_date(rk.getDate(3));
//				obj_booking.setBooking_room_category(rk.getString(4));
//				obj_booking.setBooking_room_door_number(rk.getString(5));
//				obj_booking.setBooking_customer_mobile(rk.getString(6));
////				obj_booking.setBooking_total_adults(rk.getString(7));
////				obj_booking.setBooking_total_childs(rk.getString(8));
//				obj_booking.setRoomCost(rk.getString(7));
//				System.out.println("from database roomcost is"+obj_booking.getRoomCost());
//				obj_booking.setFacilitiesCost(rk.getString(8));
//				obj_booking.setBooking_total_nights(rk.getString(9));
//				obj_booking.setNet_amount(rk.getString(10));
//				obj_booking.setGstValue(rk.getString(11));
//				obj_booking.setGross_amount(rk.getString(12));
//				obj_booking.setCouponName(rk.getString(13));
//				obj_booking.setFinal_amount(rk.getString(14));
//				//obj_booking.setStatus(rk.getString(15));
//				obj_booking.setAdvance(rk.getString(15));
//				
//				pst1.setString(1, obj_booking.getBooking_customer_mobile());
//				ResultSet rm = pst1.executeQuery();
//				if(rm.next())
//					obj_booking.setCustomerName(rm.getString(1)+" "+rm.getString(2));
//				
//				pst2.setString(1, obj_booking.getCouponName());
//				ResultSet rc = pst2.executeQuery();
//				if(rc.next())
//					obj_booking.setDiscount(rc.getString(1));
// 
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return obj_booking;
//	}
//	
	
	

}
