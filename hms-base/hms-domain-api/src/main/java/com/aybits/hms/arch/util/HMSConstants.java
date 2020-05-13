package com.aybits.hms.arch.util;


public class HMSConstants {
	public final static String SUBMIT = "Submit";
	public final static String UPDATE = "Update";
	
	

	
	public final static int TOTAL_ROOMS = 100;
	public final static int BOOKING_MONTHS_LIMIT = 1;
	public final static int BOOKING_DAYS_LIMIT = 30;
	
	public final static String GST_BOOKING = "BOOKING";
	public final static String GST_ROOM_SERVICE = "ROOM_SERVICE";
	public final static String CGST = "CGST";
	public final static String SGST = "SGST";
	public final static String  PREFIX_TAX_LESS = "IWT";
	public final static String  PREFIX_CASH = "INC";
	public final static String  PREFIX_CARD = "INO";
	public final static String FREE = "AVAILABLE";
	public final static String BOOKED = "BOOKED";
	public final static String CHECKIN = "CHECKED IN";	
	public final static String CHECKOUT = "CHECKED OUT";
	public final static String SERVICE = "SERVICE";
	public final static String MAINTENANCE = "MAINTENANCE";
	public final static String CANCEL = "CANCELED";
	//public final static String GENERAL = "GENERAL";
	public final static String NOT_DEFINED = "INFINITE";
	public final static String NO_TAX = "NO TAX";
	public final static int CHECKOUT_TIME_LIMIT = 1;
	public final static String CASH = "CASH";
	public final static String CARD = "CARD";
	public final static String ADMIN = "ADMIN";
	public final static String USER = "USER";
	public final static String COUPON_TYPE_GENERAL = "GENERAL";
	public final static String COUPON_TYPE_SEASONAL = "SEASONAL";
	public final static String NO_FACILITIES = "NONE";
	public final static String MODE_ON = "ON";
	public final static String MODE_OFF = "OFF";
	//public final static String NULL ="NULL";
	public final static String GST = "5%";
	public static double GST_VALUE = 0.05;
	
	//Class BookingCancel
	public final static String bookingCancelTransactions[]={ "BOOKING ID", "FIRST NAME", "LAST NAME","MOBILE NUMBER", "BOOKING DATE", "CHECK-IN DATE", "CHECK-OUT DATE",  "DAYS", "B00KING_STATUS"};
	public final static String[] bookingCancelTipStr = {"Booking Id", "Customer first name", "Customer last name", "Customer Mobile Number", "Booking Date", "Check-in Date", "Check-out Date", "Total Days", "Status"};
	
	public final static String expenseCols[] = {"PAID TO", "DEPARTMENT", "DESCRIPTION", "AMOUNT"};
	public final static String[] expenseTipStr = {"Paid To", "Department", "Description", "Amount Paid"};
	
	//Class BookingTransactions
	public final static String bookingTransactionColumnNames[]={ "BOOKING ID", "FIRST NAME", "LAST NAME","MOBILE NUMBER", "BOOKING DATE", "CHECK-IN DATE", "CHECK-OUT DATE",  "DAYS",  "GROSS TOTAL", "B00KING_STATUS", "PAYMENT MODE"};
	public final static String[] bTransactionsTipStr = {"Booking Id", "Customer first name", "Customer last name", "Customer Mobile Number", "Booking Date", "Check-in Date", "Check-out Date", "Total Days", "Gross Total", "Status", "Payment Mode"};
	
	//Class CheckInEntry
	public final static String checkInEntryNames[]={"BOOKING ID", "ROOM NUMBER", "NAME", "MOBILE NUMBER", "CHECKIN DATE", "ADULTS", "CHILDREN", "ADVANCE AMOUNT"};
	public final static String[] checkInEntryTipStr = {"Booking ID", "Room Number", "Customer's Name", "Mobile Number", "CheckIn date", "Total Adults", "Total Children", "Advance Amount"};
	
	//Class RoomCategoryEntry
	public final static String[] toolTipStr = {"Room Category", "Room Category Type", "Room Category Description", "Room Capacity"};
	public final static String roomCategoyColNames[]={"ROOM CATEGORY","ROOM TYPE","ROOM CATEGORY DESC","ROOM CAPACITY"};
	
	//Class RoomCapacityEntry
	public final static String roomCapacityColNames[]={"NAME","ADULT CAPACITY","CHILDREN CAPACITY", "TOTAL CAPACITY"};	
	public final static String[] roomCapacityTipStr = {"Room Capacity Name", "Room Adult Capacity", "Room Child Capacity", "Total Capacity"};
	
	//Class RoomFacilitiesEntry
	public final static String roomFaclitiesColNames[]={"ROOM FACILITIES NAME","ROOM FACILITIES DESC","PRICE"};
	public final static String[] roomFacilitiesTipStr = {"Room Facility Name", "Room Facility Description","Price"};
	
	//Class Miscellaneous Service
	public final static String miscellaneousColNames[]={"SERVICE NAME","SERVICE DESCRIPTION","PRICE"};
	public final static String[] miscellaneousTipStr = {"Service Name", "Service Description","Price"};
	
	//Class Hotel Details
	public final static String hotelColumnNames[]={"HOTEL NAME","DESCRIPTION","TOTAL ROOMS","TOTAL FLOORS"};
	public final static String[] hotelTipStr = {"Hotel Name", "Hotel Description", "Total Number of rooms", "Total Floors"};
	
	//Class GST Details
	public final static String gstColumnNames[]={"TAX ID", "TAX Rule","CGST (%)", "SGST (%)", "Total GST (%)","CATEGORY"};
	public final static String[] gstTipStr = {"TAX ID", "Tax rule with respect to upperbound & lower bound", "CGST tax percentage","SGST tax percentage", "Total GST", "Category on which tax is appled"};
	
	//Class CouponEntry
	public final static String  couponColumnNames[]={"COUPON NAME","DISCOUNT AMOUNT", "COUPON TYPE"};	
	public final static String[] couponTipStr = { "Coupon Name", "The Discount Amount","Coupon Type"};
	
	//Class RoomPriceEntry
	public final static String roomPriceEntryColNames[]={"ROOM CATEGORY", "PRICE",  "FACILITIES","FACILITIES PRICE"};
	public final static String[] roomPriceTipStr = {"Room Category", "Price of all rooms that are under this category",   "Room Facilities","Total cost of Facilities"};
	
	//Class RoomsEntry
	public final static String roomsEntryNames[]={"ROOM DOOR NUMBER","ROOM CATEGORY"};
	public final static String[] roomsEntryTipStr = {"Room Door Number", "Room Category"};
	
	//Class SeasonEntry
	public final static String seasonEntryNames[]={"SEASON NAME","DATE START","DATE END","COUPON"};
	public final static String[] seasonEntryTipStr = { "Season Name", "Season Start Date", "Season End Date","Coupon Name"};
	//Class Employee
	public final static String employeeEntryNames[]={"FIRST NAME","LAST NAME", "USER NAME", "PASSWORD", "EMAIL", "MOBILE", "BIRTH DATE", "GENDER"};
	public final static String[] employeeEntryTipStr = {"First Name", "Last Name", "Username", "Password", "Email", "Mobile", "Birth Date", "Gender"};
	
	//Class Customer
	public final static String customerColumnNames[]={"FIRST NAME","LAST NAME","GENDER","ADDRESS","CITY","EMAIL", "MOBILE"};
	public final static String[] customerTipStr = {"Customer First Name", "Customer Last Name", "Customer Gender", "City","Email","Mobile NUmber",  "Address" };
	
	//Class Booking Entry
	public final static String bookingEntryColNames[]={"SLNO","Booking ID","BOOKING DATE","ARRIVAL DATE","DEPARTURE DATE","ROOM CATEGORY ID", "ROOM ID", "CUSTOMER ID", "STATUS", "TOTAL NIGHTS", "TOTAL ROOMS", "TOTAL ADULTS", "TOTAL CHILDS", "TOTAL COST"};
	public final static String[] bookingTipStr = { "Serial Number", "Booking ID", "Booking Date", "Arrivate Date", "Departure Date", "Room Category ID", "Room ID", "Customer ID", "Status", "Total Nights", "Total Rooms", "Total Adults", "Total Childs", "Total Cost"  };
	

	
	//Class CheckOut
	public final static String checkOutEntryNames[]={"BOOKING ID", "ROOM NUMBER", "CUSTOMER NAME", "MOBILE NUMBER", "CHECKOUT DATE"};
	public final static String[] checkOutEntryTipStr = {"Booking ID", "Room Number", "Customer Full Name", "Mobile Number",  "CheckOut date"};
	
	public static final String FILE = "cmd /c dir C:\\HotelManagement\\oraexe\\app\\harness\\docs\\docs\\swing-layout-1.0.4-doc\\releaseNotes.txt /tc";
	//public static final String FILE = "cmd /c dir C:\\Users\\Nazar\\Desktop\\single.xls /tc";
	//Class CheckOutTransactions
	//public final static String checkOutTransacNames[]={"SLNO","BOOKING ID","CHECKIN DATE","CHECKOUT DATE", "ROOM NO", "ROOM CATEGORY", "ROOM COST", "FACILITIES COST", "TOTAL DAYS","ADVANCE AMOUNT","TOTAL AMOUNT"};
	
}
