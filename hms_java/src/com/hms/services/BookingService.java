package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Booking;
import com.hms.model.Room;
import com.hms.util.BigDecimalType;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.view.SingleLeftPanel;
public class BookingService {
	private Booking obj_booking;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	
	Connection con = DBConnection.getDBConnection();
	public BookingService()
	{
		
	}
	public BookingService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public BookingService(Booking obj_booking)
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
	public String generateID()
	{
        String serialNo = "1";
        GregorianCalendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMM");
            //TODO: Query DB to get serial number! Just use date between and count the rows to get a valid SerialNumber.
            DateFormat convertDate = new SimpleDateFormat("yy-MM-dd");
            GregorianCalendar gc = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 1);
            GregorianCalendar gc2 = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), gc.getActualMaximum(Calendar.DAY_OF_MONTH));
            java.util.Date monthStrartDate = new java.util.Date(gc.getTime().getTime());
            java.util.Date monthEndDate = new java.util.Date(gc2.getTime().getTime());

            int count = 0;
            
            
            count += 1;

            serialNo = String.valueOf(count);

            serialNo = String.format("%4s", serialNo).replace(' ', '0');

        return format.format(date)+serialNo;
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
	public String generateCashLessBookingID(){

		String prefix = "D";
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
	public int submitService()
	{
		PreparedStatement pst;
	
		int s = 0;
//		PreparedStatement pst1;
//		double discount = 0;
//		double finalAmount = 0;
		try {
			pst = con.prepareStatement(DatabaseConstants.INSERT_BOOKING);
//			
//			if(obj_booking.getCouponName()!=null)
//			{
//			pst1 = con.prepareStatement("select couponDiscount from coupons where couponName = ?");
//			pst1.setString(1, obj_booking.getCouponName());
//			ResultSet rcs = pst1.executeQuery();
//			if(rcs.next())
//				discount = Double.parseDouble(rcs.getString(1));
//			finalAmount = Double.parseDouble(obj_booking.getGross_amount())-discount;
//			}
//			else
//			{
//				discount = 0;
//				finalAmount = Integer.parseInt(obj_booking.getGross_amount());
//			
//			}
			
			pst.setString(1, obj_booking.getBooking_Id());
			pst.setTimestamp(2, obj_booking.getBookingDate());
			pst.setTimestamp(3, obj_booking.getBookedDate());
			pst.setTimestamp(4, obj_booking.getCheckoutDate());
			pst.setString(5, obj_booking.getBooking_room_category());
			pst.setString(6, obj_booking.getBooking_room_door_number());
			pst.setString(7, obj_booking.getBooking_customer_mobile());
			pst.setString(8, obj_booking.getRoomCost());
			pst.setString(9, obj_booking.getFacilitiesCost());
			pst.setString(10, obj_booking.getBooking_total_nights());			
//			pst.setString(13, obj_booking.getNet_amount());
//			pst.setString(14, obj_booking.getGstValue());
//			pst.setString(15, obj_booking.getGross_amount());
			//pst.setString(11, obj_booking.getCouponName());
//			pst.setString(17, obj_booking.getGross_amount());
			pst.setString(11, obj_booking.getStatus());
			

			
			
//			PreparedStatement pstUpdate=con.prepareStatement("update rooms set roomStatus = ? where roomDoorNumber = ?");
//			pstUpdate.setString(1, Constants.BOOKED);
//			pstUpdate.setString(2,obj_booking.getBooking_room_door_number());
			
			s=pst.executeUpdate();
			if(s>0)
			{
				con.commit();
				
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public int submitMultiService(List<Room> listRooms, java.sql.Timestamp booked_date, java.sql.Timestamp checkout_date, String days, String mobile)
	{
		PreparedStatement pst;
		int result = 1;
		try {
			con.setAutoCommit(false);
			pst = con.prepareStatement(DatabaseConstants.INSERT_BOOKING);
			long millis=System.currentTimeMillis();  	
			java.sql.Timestamp currentDate = new java.sql.Timestamp(millis);					
			SingleLeftPanel.BOOKING_ID = generateBookingId();	
			
			for(Room room: listRooms)
			{
					pst.setString(1, SingleLeftPanel.BOOKING_ID);
					pst.setTimestamp(2, currentDate);
					pst.setTimestamp(3, booked_date);
					pst.setTimestamp(4, checkout_date);
					pst.setString(5, room.getRoomCategoryID());
					pst.setString(6, room.getRoomDoorNumber());
					pst.setString(7, mobile);
					pst.setString(8, room.getRoomCost());
					pst.setString(9, room.getFacilitiesCost());
					pst.setString(10, days);			
					pst.setString(11, Constants.BOOKED);
					pst.addBatch();
					
			}
			int s[] = pst.executeBatch();
			con.commit();	
			con.setAutoCommit(true);
			for(int i=0;i<s.length;i++)
			{
				System.out.println("hey sibbu");
				if(s[i]<0)
				{
					result = 0;
					System.out.println("from inside less than 0"+s[i]);
				}
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Object[][] retrieveBookingsOld()
	{
		int rows = 0;
		Object[][] data = null;
		
		try {
			
			while(rk.next())
			{
				rows++;			
			}
			 rk=stmt.executeQuery("select * from Booking");
			 data=new Object[rows][14];
			for(int i=0;i<rows;i++)
			{
				rk.next();
				for(int j=0;j<14;j++)
					{		
						data[i][j]=rk.getString(j+1);

					}
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			if(rows==0)
				JOptionPane.showMessageDialog(null,"No transactions performed","Message",JOptionPane.INFORMATION_MESSAGE);
		}
		return data;

	}	
	
	public void retrieveBookings()
	{

		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
 
		try {
			
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rk=stmt.executeQuery("select * from booking");
			Booking obj_booking = new Booking();
			while(rk.next())
			{
//				obj_booking.setBooking_Id(rk.getString(1));
//				obj_booking.setBooking_Date(rk.getDate(2));
//				obj_booking.setBooking_arrival_date(rk.getDate(3));
//				obj_booking.setBooking_departure_date(rk.getDate(4));
//				obj_booking.setBooking_room_category(rk.getString(5));
//				obj_booking.setBooking_room_door_number(rk.getString(6));
//				obj_booking.setBooking_customer_mobile(rk.getString(7));
//				obj_booking.setBooking_total_adults(rk.getString(8));
//				obj_booking.setBooking_total_childs(rk.getString(9));
//				obj_booking.setRoomCost(rk.getString(10));
//				obj_booking.setFacilitiesCost(rk.getString(11));
//				obj_booking.setBooking_total_nights(rk.getString(12));
//				obj_booking.setBooking_total_cost(rk.getString(13));
//				obj_booking.setGstValue(rk.getString(14));
//				obj_booking.setGross_amount(rk.getString(15));
//				obj_booking.setCouponName(rk.getString(16));
//				obj_booking.setFinal_amount(rk.getString(17));
//				obj_booking.setStatus(rk.getString(18));

				
				
				
				tableModel.addRow(new Object[]{
//						obj_booking.getBooking_Id(),
//						obj_booking.getBooking_Date(),
//						obj_booking.getBooking_arrival_date(),
//						obj_booking.getBooking_departure_date(),
//						obj_booking.getBooking_room_category(),
//						obj_booking.getBooking_room_door_number(),
//						obj_booking.getBooking_customer_mobile(),
//						//obj_booking.getBooking_status(),
//						obj_booking.getBooking_total_nights(),
//						obj_booking.getBooking_total_rooms(),
//						obj_booking.getBooking_total_adults(),
//						obj_booking.getBooking_total_childs(),
//						obj_booking.getBooking_total_cost()
						rk.getString(1),
						rk.getString(2),
						rk.getString(3),
						rk.getString(4),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7),
						rk.getString(8),
						rk.getString(9),
						rk.getString(10),
						rk.getString(11),
						rk.getString(12),
						rk.getString(13),
						rk.getString(14),
						rk.getString(15),
						rk.getString(16),
						rk.getString(17),
						rk.getString(18)
				});
 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int checkRoomAvailability(java.sql.Timestamp checkout_date, String roomDoorNumber){
		int count = 0;
		try {			
			PreparedStatement pst = con.prepareStatement("select bookingID from booking where ? between bookedDate and checkoutDate and status = '"+Constants.BOOKED+"' and roomDoorNumber = ?");
			pst.setTimestamp(1, checkout_date);
			pst.setString(2, roomDoorNumber);
			ResultSet rs = pst.executeQuery();
			System.out.println("from else sib"+checkout_date);
			while(rs.next())
			{
				System.out.println("\nbooking ids are"+rs.getString(1));
				count++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;
	
	}
	public List<Room> singleFreeRooms(String query, Timestamp checkinDate, Timestamp checkoutDate, String status)
	{
		List<String> bookedRooms = new ArrayList<String>();
		List<String> freeRooms = new ArrayList<String>();
		List<Room> rooms = new ArrayList<>();
		
		try {			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setTimestamp(1, checkinDate);
			pst.setTimestamp(2, checkoutDate);
			pst.setTimestamp(3, checkinDate);
			pst.setTimestamp(4, checkoutDate);
			pst.setString(5, status);
			ResultSet rs = pst.executeQuery();	
			while(rs.next())
			{
				bookedRooms.add(rs.getString(1));
				System.out.println("booking id is"+rs.getString(2));
			}
			System.out.println("from free rooms");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HashSet<String> hashset = new HashSet<String>();
		hashset.addAll(bookedRooms);
		bookedRooms.clear();
		bookedRooms.addAll(hashset);

		for(String room : bookedRooms)
			System.out.println("booked rooms is"+room);

		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.ROOM_DOOR_NUMBER);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				freeRooms.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		freeRooms.removeAll(bookedRooms);

		for(String roomNo : freeRooms)
		{
			Room room = new Room();
		
			try {
				PreparedStatement pstRooms = con.prepareStatement(DatabaseConstants.MULTIPLE_ROOM_DETAILS);
				PreparedStatement pstRoomPrice=con.prepareStatement(DatabaseConstants.ROOMPRICE_ID_PRICE);
				PreparedStatement pstFacilities = con.prepareStatement(DatabaseConstants.FACILITIES_PRICE);
				
				pstRooms.setString(1, roomNo);
				ResultSet rsRooms = pstRooms.executeQuery();
				if(rsRooms.next())
				{
					room.setRoom_ID(rsRooms.getString(1));
					room.setRoomCategoryID(rsRooms.getString(2));
					room.setRoomDoorNumber(roomNo);
					pstRoomPrice.setString(1, room.getRoomCategoryID());
					ResultSet rp = pstRoomPrice.executeQuery();
					if(rp.next())
					{
						String facilities = "";
						double totalCost = 0;
						String roomPriceID = rp.getString(1);
						room.setRoomCost(rp.getString(2));
						
						pstFacilities.setString(1, roomPriceID);
						ResultSet rf = pstFacilities.executeQuery();
						while(rf.next())
						{
							facilities = facilities+rf.getString(1)+", ";
							totalCost += Double.parseDouble(rf.getString(2));
						}
						room.setFacilitiesCost(""+BigDecimalType.round(totalCost));
						room.setRoomFacilities(facilities);
						
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rooms.add(room);
		}
		
		for(Room room : rooms)
		{
			System.out.println("room no"+room.getRoomDoorNumber());
			System.out.println("room price"+room.getRoomCost());
		}
		
		return rooms;
		
	}
	
	
	//public List<Room> multipleFreeRooms(String query, Timestamp checkinDate, Timestamp checkoutDate, String status)	
	public List<Room> multipleFreeRooms(String query, String query1, String checkinDate, String checkoutDate, String status)
	{
		List<String> bookedRooms = new ArrayList<String>();
		List<String> checkedInRooms = new ArrayList<String>();
		List<String> freeRooms = new ArrayList<String>();
		List<Room> rooms = new ArrayList<>(); 
		
		try {			
			PreparedStatement pstBooked = con.prepareStatement(query);
			PreparedStatement pstCheckedIn = con.prepareStatement(query1);
//			pst.setTimestamp(1, checkinDate);
//			pst.setTimestamp(2, checkinDate);
//			pst.setTimestamp(3, checkoutDate);
//			pst.setTimestamp(4, checkoutDate);
			//pst.setString(5, status);
			pstBooked.setString(1, checkinDate);
			pstBooked.setString(2, checkinDate);
			pstBooked.setString(3, checkoutDate);
			pstBooked.setString(4, checkoutDate);
			ResultSet rs = pstBooked.executeQuery();	
			while(rs.next())
			{
				bookedRooms.add(rs.getString(1));
				System.out.println("booking id is"+rs.getString(2));
				System.out.println("Checkin datei is"+checkinDate);
				System.out.println("Checkout datei is"+checkoutDate);
			}
			
			

			pstCheckedIn.setString(1, checkinDate);
			pstCheckedIn.setString(2, checkinDate);
			pstCheckedIn.setString(3, checkoutDate);
			pstCheckedIn.setString(4, checkoutDate);
			ResultSet rsCheckedIn = pstCheckedIn.executeQuery();
			while(rsCheckedIn.next())
			{
				checkedInRooms.add(rsCheckedIn.getString(1));
			}
			
			System.out.println("from free rooms");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HashSet<String> hashset = new HashSet<String>();
		hashset.addAll(bookedRooms);
		bookedRooms.clear();
		bookedRooms.addAll(hashset);
		
		
		HashSet<String> checkHashset = new HashSet<String>();
		checkHashset.addAll(checkedInRooms);
		checkedInRooms.clear();
		checkedInRooms.addAll(checkHashset);

		for(String room : bookedRooms)
			System.out.println("booked room is"+room);
		
		for(String room : checkedInRooms)
			System.out.println("checked in room is"+room);

		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.ROOM_DOOR_NUMBER);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				freeRooms.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		freeRooms.removeAll(bookedRooms);
		freeRooms.removeAll(checkedInRooms);

		for(String roomNo : freeRooms)
		{
			Room room = new Room();
		
			try {
				PreparedStatement pstRooms = con.prepareStatement(DatabaseConstants.MULTIPLE_ROOM_DETAILS);
				PreparedStatement pstRoomPrice=con.prepareStatement(DatabaseConstants.ROOMPRICE_ID_PRICE);
				PreparedStatement pstFacilities = con.prepareStatement(DatabaseConstants.FACILITIES_PRICE);
				
				pstRooms.setString(1, roomNo);
				ResultSet rsRooms = pstRooms.executeQuery();
				if(rsRooms.next())
				{
					room.setRoom_ID(rsRooms.getString(1));
					room.setRoomCategoryID(rsRooms.getString(2));
					room.setRoomDoorNumber(roomNo);
					pstRoomPrice.setString(1, room.getRoomCategoryID());
					ResultSet rp = pstRoomPrice.executeQuery();
					if(rp.next())
					{
						String facilities = "";
						double totalCost = 0;
						String roomPriceID = rp.getString(1);
						room.setRoomCost(rp.getString(2));
						
						pstFacilities.setString(1, roomPriceID);
						ResultSet rf = pstFacilities.executeQuery();
						while(rf.next())
						{
							facilities = facilities+rf.getString(1)+", ";
							totalCost += Double.parseDouble(rf.getString(2));
						}
						room.setFacilitiesCost(""+BigDecimalType.round(totalCost));
						room.setRoomFacilities(facilities);
						
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rooms.add(room);
		}
		
		for(Room room : rooms)
		{
			System.out.println("room no"+room.getRoomDoorNumber());
			System.out.println("room price"+room.getRoomCost());
		}
		
		return rooms;
		
	}
	
	
	

}
