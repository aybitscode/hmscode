package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Booking;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.view.BookingCancel;
import com.hms.view.BookingCancel;
import com.hms.view.BookingTransactions;

public class BookingCancelService {
		private Booking obj_booking = new Booking();
		Connection con = DBConnection.getDBConnection();
		DefaultTableModel tableModel;
		JTable table;
		public BookingCancelService()
		{
			
		}
		public BookingCancelService(Booking obj_booking)
		{
			this.obj_booking = obj_booking;
		}
		public BookingCancelService(DefaultTableModel tableModel, JTable table)
		{
			this.tableModel = tableModel;
			this.table = table;
		}
		public Booking retrieveBookings(String bookingID, String roomNumber)
		{
			Booking obj_booking = new Booking(); 
			try {
				PreparedStatement pst = con.prepareStatement(DatabaseConstants.CANCEL_BOOKING);
				pst.setString(1, bookingID);
				pst.setString(2, roomNumber);
				ResultSet rk=pst.executeQuery();
				
				PreparedStatement pst2 = con.prepareStatement("select couponDiscount from coupons where couponName = ?");
				if(rk.next())
				{
					obj_booking.setBooking_Id(rk.getString(1));
					obj_booking.setCustomerName(rk.getString(2)+" "+rk.getString(3));
					obj_booking.setBooking_customer_mobile(rk.getString(4));
					obj_booking.setBookingDate(rk.getTimestamp(5));
					obj_booking.setBookedDate(rk.getTimestamp(6));
					obj_booking.setCheckoutDate(rk.getTimestamp(7));
					obj_booking.setBooking_room_category(rk.getString(8));
					obj_booking.setBooking_room_door_number(rk.getString(9));
					obj_booking.setBooking_total_nights(rk.getString(10));
					obj_booking.setRoomCost(rk.getString(11));
					obj_booking.setFacilitiesCost(rk.getString(12));
												
					pst2.setString(1, obj_booking.getCouponName());
					ResultSet rc = pst2.executeQuery();
					if(rc.next())
						obj_booking.setDiscount(rc.getString(1));
	 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return obj_booking;
		}
		public void retrieveAll(String query, String parameter)
		{

//			BookingCancel.btnSubmit.setVisible(false);
			
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
							rk.getString(4),
							rk.getString(5),
							rk.getString(6),
							rk.getString(7),
							rk.getString(8),
							rk.getString(9)
						});
					slno++;
	 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			BookingCancel.lblRows.setText("");
//			BookingCancel.lblRows.setText("No. of Rows: "+slno);
		}

		public int submitService(String bookingId, String roomNumber)
		{
			int result = 0;
			
			try {
				PreparedStatement pstBook=con.prepareStatement("update booking set status = ? where bookingID = ? and roomDoorNumber = ?");
				pstBook.setString(1, Constants.CANCEL);
				pstBook.setString(2, bookingId);
				pstBook.setString(3, roomNumber);
				
				int s= pstBook.executeUpdate();
				if(s>0)
				{
					con.commit();
					JOptionPane.showMessageDialog(null, "Booking cancelled successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					result = 1;
				}
				else
					  JOptionPane.showMessageDialog(null, "Please check the details", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;

			

		}
		public void retrieve(String query, String parameter)
		{
//			String s2 = "ADMIN";
//			if(MainPage.user_role == null)
//				BookingTransactions.btnSubmit.setVisible(false);
//			else if(MainPage.user_role.equals(s2))
//				BookingTransactions.btnSubmit.setVisible(true);
//			else
//				BookingTransactions.btnSubmit.setVisible(false);

			
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
							rk.getString(4),
							rk.getString(5),
							rk.getString(6),
							rk.getString(7),
							rk.getString(8),
							rk.getString(9)
						});		
					slno++;
				}
				BookingTransactions.lblRows.setText("");
				BookingTransactions.lblRows.setText("No. of Rows: "+slno);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		

}
