package com.aybits.hms.arch.dbman;

import com.aybits.hms.arch.util.HMSConstants;

public class DatabaseConstants {


	//Table roomfacilites & roompricefacilities
	public static String FACILITIES_PRICE = "select t1.roomFacilitiesName, roomFacilitiesPrice from roomprice_facilities t1 inner join roomfacilities t2 on t1.roomFacilitiesName = t2.roomFacilitiesName where roomPriceID = ?";

	//Table roomprice & roomprice_facilities
	public static String SELECT_ROOMPRICE = "select roomPriceName, roomPrice, roomCategoryID, seasonName, totalCapacity, roomFacilitiesName from roomprice t1 inner join roomprice_facilities t2 on t1.roomPriceID = t2.roomPriceID";	
	
	//Table roomprice_facilities
	public static String INSERT_ROOMPRICE_FACILITIES = "insert into roomprice_facilities(roomPriceID, roomFacilitiesName) "+ "values(?,?)";
	
	//Table roomcategory & roomprice
	public static String COMBO_ROOM_PRICE_CATEGORY = "select t1.roomCategoryID FROM roomcategory t1 left join roomprice t2 on t2.roomCategoryID = t1.roomCategoryID where t2.roomCategoryID IS NULL";
	
	//Table roomprice 
	public static String ALL_PRICE_COLS = "select roomPriceID, roomCategoryID, roomPrice from roomprice";
	public static String ALL_ROOM_PRICE_CATEGORY = "select roomPriceID, roomCategoryID, roomPrice from roomprice where roomCategoryID = ?";
	public static String ROOM_PRICE_CATEGORY = "select roomCategoryID from roomprice";
	public static String TABLE_ROOM_PRICE_COLS = "select roomCategoryID, roomPrice from roomprice";
	public static String INSERT_ROOMPRICE = "insert into roomPrice(roomPriceID, roomCategoryID, roomPrice) "+ "values(?,?,?)";
	public static String UPDATE_ROOM_PRICE = "update roomprice set  roomCategoryID = ?, roomPrice = ? where roomCategoryID = ?";
	public static String ROOMPRICE_ID_PRICE = "select  roomPriceID, roomPrice from roomprice where roomCategoryID = ?";
	
	
	//Table roomfacilities
	public static String SELECT_ALL_ROOM_FACILITIES = "select * from roomfacilities";
	public static String TABLE_ROOM_FACILITES_COLS = "select roomFacilitiesName, roomFacilitiesDesc, roomFacilitiesPrice from roomFacilities";
	public static String TABLE_ROOM_FACILITES_NAME = "select roomFacilitiesID, roomFacilitiesName, roomFacilitiesDesc, roomFacilitiesPrice from roomFacilities where roomFacilitiesName = ?";
	public static String ROOM_FACILITIES_NAME = "select roomFacilitiesName from roomfacilities";
	public static String UPDATE_FACILITIES = "update roomfacilities  set roomFacilitiesName = ?, roomFacilitiesDesc = ?, roomFacilitiesPrice = ? where roomFacilitiesID = ?";
	
	//Table coupons
	public static String SELECT_ALL_COUPONS = "select * from coupons";
	public static String INSERT_COUPON ="insert into coupons(couponID, couponName, couponDiscount, couponType) "+ "values(?,?,?,?)";
	public static String TABLE_COUPON_COLS = "select couponName, couponDiscount, couponType from coupons";
	public static String TABLE_COUPON_NAME = "select couponID, couponName, couponDiscount, couponType from coupons where couponName = ?";
	public static String UPDATE_COUPONS = "update coupons set couponName = ?, couponDiscount = ?, couponType = ? where couponID = ?";
	public static String COUPON_TYPE = "select couponName from coupons where couponType = ?";
	public static String COUPON_DISCOUNT = "select couponDiscount from coupons where couponName = ?";
	public static String COUPON_NAME = "select couponName from coupons";
	public static String SEASONAL_COUPONS_LEFT = "select t1.couponName FROM coupons t1 left join seasons t2 on t2.couponName = t1.couponName where t2.couponName IS NULL and t1.couponType = ?";
	
	
	
	//Table Hotel
	public static String INSERT_HOTEL = "insert into hotel(hotelID, hotelName, hotelDesc, hotelTotalRooms, hotelTotalFloors) "+ "values(?,?,?,?,?)";
	public static String SELECT_ALL_HOTEL = "select * from hotel";
	public static String TABLE_HOTEL_COLS = "select hotelName, hotelDesc, hotelTotalRooms, hotelTotalFloors from hotel";
	public static String TABLE_HOTEL_NAME = "select hotelName, hotelDesc, hotelTotalRooms, hotelTotalFloors from hotel where hotelName = ?";
	public static String HOTEL_NAME = "select hotelName from hotel";
	public static String HOTEL_ROOMS = "select hotelTotalRooms from hotel";
	public static String UPDATE_HOTEL = "update hotel set hotelName = ?, hotelDesc = ?, hotelTotalRooms = ?, hotelTotalFloors = ? where hotelName = ?";
	
	// Table GST
	public static String INSERT_GST = "insert into tax_rules(taxID, lowerbound, upperbound, cgst, sgst, taxpercentage, category, status) "+ "values(?,?,?,?,?,?,?,?)";
	public static String SELECT_ALL_GST = "select * from tax_rules";
	public static String TABLE_GST_COLS = "select taxID, lowerbound, upperbound, cgst, sgst, taxpercentage, category from tax_rules";
	public static String TABLE_TAX_TYPE = "select taxID, lowerbound, upperbound, cgst, sgst, taxpercentage, category from tax_rules where taxID = ?";
	public static String TAX_TYPE = "select taxID from tax_rules";
	public static String UPDATE_GST = "update tax_rules set  lowerbound = ?, upperbound = ?, cgst = ?, sgst = ?, taxpercentage = ?, category = ? where taxID = ?";
	public static String SELECT_ALL_TAX_CATEGORY = "select taxID, lowerbound, upperbound, cgst, sgst, taxpercentage from tax_rules where category = ?";
	

	
	//Table roomcapacity
	public static String SELECT_ALL_ROOM_CAPACITY = "select * from roomcapacity";
	public static String TABLE_ROOM_CAPACITY_COLS = "select roomCapacityName, roomCapacityAdults, roomCapacityChilds, totalCapacity from roomcapacity";
	public static String TOTAL_CAPACITY = "select totalCapacity from roomcapacity";
	public static String TABLE_ROOM_CAPACITY_NAME = "select roomCapacityName, roomCapacityAdults, roomCapacityChilds, totalCapacity from roomcapacity where roomCapacityName = ?";
	public static String ROOM_CAPACITY_NAME = "select roomCapacityName from roomcapacity";
	public static String UPDATE_ROOM_CAPACITY = "update roomcapacity set roomCapacityName = ?, roomCapacityAdults = ?, roomCapacityChilds = ?, totalCapacity = ? where roomCapacityName = ?";
	public static String CAPACITY_ADULTS_CHILDS = "select roomCapacityAdults, roomCapacityChilds from roomcategory r1 inner join roomcapacity r2 on r1.roomCapacityName = r2.roomCapacityName where roomCategoryID = ?";



	//Table season
	public static String INSERT_SEASON = "insert into seasons(seasonID, seasonName, dateStart, dateEnd, couponName) "+ "values(?,?,?,?,?)";
	public static String SELECT_ALL_SEASONS = "select * from seasons";
	public static String SEASONS_COUPONS = "select dateStart, dateEnd, s.couponName, couponDiscount from seasons s inner join coupons c on s.couponName = c.couponName";
	public static String TABLE_SEASON_COLS = "select seasonName, dateStart, dateEnd, couponName from seasons";
	public static String TABLE_SEASON_NAME = "select seasonID, seasonName, dateStart, dateEnd, couponName from seasons where seasonName = ?";
	public static String UPDATE_SEASON = "update seasons set seasonName = ?, dateStart = ?, dateEnd = ?, couponName = ? where seasonID = ?";
	public static String SEASON_NAME = "select seasonName from seasons";
	
	//Table Employee
	public static String SELECT_EMP_USER_PASS = "select employeeID, username, password, role, mode from employees where username = ?";
	public static final String INSERT_EMPLOYEE= "insert into employees(employeeID, firstName, lastName, userName, password, email, mobile, birthDate, gender, role, mode) "+ "values(?,?,?,?,?,?,?,?,?,?,?)";
	public static String ALL_EMPLOYEES = "select * from employees";
//	public static String TABLE_EMPLOYEES = "select firstName, lastName, username, password, email, mobile, birthDate, gender from employees where role = '"+HMSConstants.USER+"'";
	public static String TABLE_EMPLOYEES = "select firstName, lastName, username, password, email, mobile, birthDate, gender from employees";
	public static String TABLE_EMPLOYEE_MOBILE = "select firstName, lastName, username, password, email, mobile, birthDate, gender from employees where mobile = ?";
	public static String UPDATE_EMPLOYEE = "update employees set firstName = ?, lastName = ?, username = ?, password = ?, email = ?, mobile = ?, birthDate = ?, gender = ? where mobile = ?";
	//public static String TABLE_MOBILE = "select mobile from employees where role = '"+HMSConstants.USER+"'";
	public static String TABLE_MOBILE = "select mobile from employees";
	public static String DELETE_EMPLOYEE= "delete from employees where mobile = ?";
	
	
	//Table room
	public static String INSERT_ROOM = "insert into rooms(roomID, roomDoorNumber, roomCategoryID) "+ "values(?,?,?)";
	public static String TABLE_ROOM_COLS = "select roomDoorNumber, roomCategoryID from rooms";
	public static String TABLE_ROOM_COLS_DNO = "select roomID, roomDoorNumber, roomCategoryID from rooms  where roomDoorNumber = ?";
	public static String UPDATE_ROOM = "update rooms set roomDoorNumber = ?, roomCategoryID = ? where roomID = ?";
	public static String SELECT_ALL_ROOMS = "select * from rooms";
	public static String ROOM_DOOR_NO = "select roomDoorNumber from rooms where roomID = ?";
	public static String ROOMCATEGORY_ROOMID = "select roomCategoryID from rooms where roomID = ?";
	public static String ROOM_DOOR_NUMBER = "select roomDoorNumber from rooms";
	public static String SELECT_ROOM_DETAILS = "select roomID, roomDoorNumber, roomCategoryID from rooms order by roomCategoryID";
	public static String MULTIPLE_ROOM_DETAILS = "select roomID, roomCategoryID from rooms where roomDoorNumber = ?";

	
	
	//Table roomcategory
	public static String SELECT_ALL_ROOM_CATEGORY = "select * from roomcategory";
	public static String ALL_ROOM_CATEGORY_ID = "select roomCategoryID, roomCategoryName, roomCategoryDesc, roomCapacityName from roomcategory where roomCategoryID = ?";
	public static String SELECT_ROOM_CATEGORY_ID = "select roomCategoryID from roomcategory";
	public static String ROOMCATEGORY_ID = "select roomCategoryID from roomcategory";
	public static String UPDATE_ROOM_CATEGORY = "update roomcategory set roomCategoryID = ?, roomcategoryName = ?, roomCategoryDesc = ?, roomCapacityName = ? where roomCategoryID = ?";


	
	
	//Table customers
	public static String ALL_CUSTOMER_MOBILE = "select firstName, lastName, gender, city, email, mobile, addrs from customers where mobile = ?";
	public static String TABLE_CUSTOMER_COLS = "select firstName, lastName, gender, city, email, mobile, addrs from customers";
	public static String CUSTOMER_MOBILE = "select mobile from customers";
	public static String UPDATE_CUSTOMER  = "update customers set firstName = ? , lastName = ?, gender = ?, city = ?, email = ?, mobile = ?, addrs = ? where mobile = ?";
	public static String SELECT_ALL_CUSTOMERS = "select * from customers";
	public static String CUSTOMER_NAMES = "select firstName, lastName from customers where mobile = ?";
	public static String CUSTOMER_NAME_ADDRS = "select firstName, lastName, addrs from customers where mobile = ?";
	//Table Billing
	public static String BILLING_DETAILS = "select booking_cgst, booking_sgst, service_cgst, service_sgst, extraBedCharges from billing where invoiceID = ?";
	//Table quickbooking
	public static String INSERT_BOOKING = "insert into quickbooking(bookingID, bookingDate, bookedDate, checkoutDate, roomCategoryID, roomDoorNumber, mobile, bookingRoomCost, facilitiesCost, bookingTotalNights, status) "+ "values(?,?,?,?,?,?,?,?,?,?,?)";
	public static String ALL_BOOKING_ID = "select * from quickbooking where bookingID = ?";
	public static String BOOKING_CANCEL = "select bookingID, bookedDate, checkoutDate, roomCategoryID, roomDoorNumber, mobile, bookingRoomCost, facilitiesCost, bookingTotalNights, status from quickbooking where bookingID = ?";
	public static String ALL_BOOKING_CHECKIN_ADVANCE = "select b1.bookingID, bookedDate, checkInDate, checkoutDate, roomCategoryID, roomDoorNumber, mobile, bookingRoomCost, facilitiesCost, bookingTotalNights, grossAmount, couponName, status, paymentMode, invoiceID,  totalAdults, totalChilds, advanceAmt from quickbooking b1 inner join checkin c1 on b1.bookingID = c1.bookingID where b1.bookingID = ?";
	public static String ALL_BOOKING_PAYMENTMODE = "select * from quickbooking where paymentMode = ?";
	//public static String ALL_BOOKING_PAYMENTMODE_IT = "select * from quickbooking where paymentMode = ? and invoiceID like 'IT%'";
	public static String CREATE_TABLE = "SELECT create_time FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'hotel' AND table_name = 'quickbooking'";
	//public static String ALL_BOOKING_PAYMENTMODE_IN = "select * from quickbooking where paymentMode = ? and invoiceID like 'IN%'";
	public static String SELECT_ALL_BOOKING = "select * from quickbooking";
	public static String CHECKOUT_BOOKING_ID = "select bookingID from quickbooking where status = ?";
	public static String BOOKING_ID = "select bookingID from quickbooking";
	public static String BOOKING_ID_CANCEL = "select bookingID from quickbooking where status = 'CANCELED'";
	public static String BOOKING_ID_USER = "select bookingID from quickbooking where invoiceID is null or invoiceID not like '"+ HMSConstants.PREFIX_TAX_LESS+"%'";
	public static String SELECT_CANCELLED_BOOKING = "select bookingID from quickbooking where status = ?";
	public static String ALL_BOOKING_STATUS = "select * from quickbooking where status = ?";
	public static String BOOKING_ID_BOOKED = "select bookingID from quickbooking where status = ?";
	public static String TABLE_BOOKING_COLS = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, grossAmount, status, paymentMode from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile order by bookingID desc";
	public static String TABLE_BOOKING_COLS_USER = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, grossAmount, status, paymentMode from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where invoiceID is null or invoiceID not like '"+ HMSConstants.PREFIX_TAX_LESS+"%' order by bookingID desc";
	public static String TABLE_BOOKING_ID = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, grossAmount, status, paymentMode from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where bookingID = ?";
	public static String TABLE_BOOKING_ID_CANCEL = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, status from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where bookingID = ?";
	public static String TABLE_BOOKING_MODE = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, grossAmount, status, paymentMode from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where paymentMode = ? order by bookingID desc";
	public static String TABLE_BOOKING_STATUS = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, grossAmount, status, paymentMode from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where status = ? order by bookingID desc";
	public static String TABLE_BOOKING_CANCEL = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, status from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where status = ? order by bookingID desc";
	public static String TABLE_BOOKING_STATUS_USER = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, grossAmount, status, paymentMode from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where status = ? and invoiceID not like '"+ HMSConstants.PREFIX_TAX_LESS+"%' order by bookingID desc";
	public static String TABLE_BOOKING_MODE_CASH_TAX = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, grossAmount, status, paymentMode from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where paymentMode = ? and invoiceID like '"+ HMSConstants.PREFIX_CASH+"%' order by bookingID desc";
	public static String TABLE_BOOKING_MODE_TAX_LESS = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, bookingTotalNights, grossAmount, status, paymentMode from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where paymentMode = ? and invoiceID like '"+ HMSConstants.PREFIX_TAX_LESS+"%' order by bookingID desc";
	public static String UPDATE_TABLE_BOOKING = "update quickbooking set " +
			"bookingID = ?, " +
			"mobile = ?, "+
			"bookingDate = ?, " +
			"roomDoorNumber = ?, "+
			"bookingRoomCost = ?, "+
			"facilitiesCost = ?, "+
			"bookingTotalNights = ?, "+
			"netAmount = ?, "+
			"gstAmount = ?, "+
			"grossAmount = ?, "+
			"couponName = ?, "+
			"finalAmount = ?,"+
			"status = ? "+			
			"where bookingID = ?";
	public static String UPDATE_BOOKING = "update quickbooking set " +
			"bookingID = ?, " +
			"bookingDate = ?, " +
			"bookedDate = ?, " +
			"checkoutDate = ?, "+
			"roomCategoryID = ?, "+
			"roomDoorNumber = ?, "+
			"mobile = ?, "+
			"bookingRoomCost = ?, "+
			"facilitiesCost = ?, "+
			"bookingTotalNights = ?, "+
			"grossAmount = ?, "+
			"status = ?,"+
			"paymentMode = ?,"+
			"invoiceID = ?"+
			"where bookingID = ?";
	public static String SELECT_CHECKIN_BOOKING_ID = "select bookingID from quickbooking where status = '"+ HMSConstants.CHECKIN+"'";
	public static String UPDATE_BOOKING_STATUS = "update quickbooking set status = ? where bookingID = ? and roomDoorNumber = ?";
	public static String ROONO_BOOKING = "select roomDoorNumber from quickbooking where bookingID = ?";
	//public static String ROOMNO_BOOKED_CHECKEDIN = "select roomDoorNumber, status from quickbooking where bookedDate = ? and status = ?";
	public static String ROOMNO_BOOKED_CHECKEDIN = "select roomDoorNumber, bookedDate, checkoutDate, status from quickbooking where status = ?";
	public static String BOOKING_CHECKOUT = "select t1.bookingID, bookedDate, t2.checkinDate, checkoutDate, roomCategoryID, roomDoorNumber, mobile, bookingRoomCost, facilitiesCost, bookingTotalNights,advanceAmt from quickbooking t1 inner join checkin t2 on t1.bookingID = t2.bookingID where t1.bookingID = ?";
	public static String CANCEL_BOOKING = "select bookingID, firstName, lastName, b1.mobile, bookingDate, bookedDate, checkoutDate, roomCategoryID, roomDoorNumber, bookingTotalNights, bookingRoomCost, facilitiesCost from quickbooking b1 inner join customers c1 on b1.mobile = c1.mobile where bookingID = ? and roomDoorNumber = ?";
	public static String CURRENT_BOOKED_DATE = "select bookingID, bookedDate, roomCategoryID, roomDoorNumber, mobile from quickbooking where bookedDate between DATE_ADD(current_date() , INTERVAL '00:00:01' HOUR_SECOND) and DATE_ADD(current_date() , INTERVAL '23:59:59' HOUR_SECOND) and status ='"+ HMSConstants.BOOKED+"'";
	public static String CURRENT_CHECKOUT_DATE = "select bookingID, checkoutDate, roomCategoryID, roomDoorNumber, mobile from quickbooking where checkoutDate between DATE_ADD(current_date() , INTERVAL '00:00:01' HOUR_SECOND) and DATE_ADD(current_date() , INTERVAL '23:59:59' HOUR_SECOND) and status = '"+ HMSConstants.CHECKIN+"'";
	public static String CURRENT_CANCEL = "select bookingID, checkoutDate, roomCategoryID, roomDoorNumber, mobile from quickbooking where checkoutDate <= current_timestamp() and status = '"+ HMSConstants.BOOKED+"'";
	//Multiple RoomBooking
	//public static final String MULTIPLE_FREE_ROOMS = "SELECT roomDoorNumber, bookingID FROM hotel.quickbooking where bookedDate between ? and ? and checkoutDate between ? and ? and  status =?";
	//public static final String MULTIPLE_FREE_ROOMS = "select roomDoorNumber, bookingID from quickbooking where status = '"+HMSConstants.BOOKED+"'"+" and bookingID not in ( select bookingID from quickbooking where bookedDate < ? and checkoutDate < ? and status = '"+HMSConstants.BOOKED+"'"+" or bookedDate > ? and checkoutDate > ? and status = '"+HMSConstants.BOOKED+"')";
	public static final String SELECTED_DATE_ROOMS_BOOKED = "select roomDoorNumber, bookingID from quickbooking where status = '"+ HMSConstants.BOOKED+"'"+" and bookingID not in ( select bookingID from quickbooking where bookedDate < ? and checkoutDate < ? and status = '"+ HMSConstants.BOOKED+"'"+" or bookedDate > ? and checkoutDate > ? and status = '"+ HMSConstants.BOOKED+"')";
	public static final String SELECTED_DATE_ROOMS_CHECKEDIN = "select roomDoorNumber, bookingID from quickbooking where status = '"+ HMSConstants.CHECKIN+"'"+" and bookingID not in ( select bookingID from quickbooking where bookedDate < ? and checkoutDate < ? and status = '"+ HMSConstants.CHECKIN+"'"+" or bookedDate > ? and checkoutDate > ? and status = '"+ HMSConstants.CHECKIN+"')";
	
	//Checkin Table 
	public static String ALL_CHECKIN_ID = "select b1.bookingID, roomDoorNumber, firstName, lastName, b1.mobile, checkInDate, totalAdults, totalChilds, advanceAmt from quickbooking b1 inner join customers ct on b1.mobile = ct.mobile inner join checkin c1 on b1.bookingID = c1.bookingID where b1.bookingId = ?";
	public static String TABLE_CHECK_IN_COLS = "select b1.bookingID, roomDoorNumber, firstName, lastName, b1.mobile, checkInDate, totalAdults, totalChilds, advanceAmt from quickbooking b1 inner join customers ct on b1.mobile = ct.mobile inner join checkin c1 on b1.bookingID = c1.bookingID where status = '"+ HMSConstants.CHECKIN+"' order by checkInDate desc";
	public static String UPDATE_CHECKIN = "update checkin set bookingID = ?, checkInDate = ?, totalAdults = ?, totalChilds = ? advanceAmt = ? where bookingID = ?";
	public static String INSERT_CHECKIN = "insert into checkin(checkInID, checkInDate, bookingID, totalAdults, totalChilds, advanceAmt, employeeID) "+ "values(?,?,?,?,?,?,?)";
	
	public static String CHECKIN_ADULTS_CHILDS = "select totalAdults, totalChilds from checkin where bookingID = ?";
	
	//Checkout Table
	public static String ALL_CHECKOUT_ID = "select b1.bookingID, roomDoorNumber, firstName, lastName, b1.mobile, c1.checkOutDate from quickbooking b1 inner join customers ct on b1.mobile = ct.mobile inner join checkout c1 on b1.bookingID = c1.bookingID where c1.bookingID = ?";
	public static String TABLE_CHECKOUT_COLS = "select b1.bookingID, roomDoorNumber, firstName, lastName, b1.mobile, c1.checkOutDate from quickbooking b1 inner join customers ct on b1.mobile = ct.mobile inner join checkout c1 on b1.bookingID = c1.bookingID where status = '"+ HMSConstants.CHECKOUT+"' order by c1.checkOutDate desc";
	//public static String TABLE_CHECK_IN_COLS = "select b1.bookingID, roomDoorNumber, firstName, lastName, b1.mobile, checkInDate, totalAdults, totalChilds, advanceAmt from quickbooking b1 inner join customers ct on b1.mobile = ct.mobile inner join checkin c1 on b1.bookingID = c1.bookingID where status = '"+HMSConstants.CHECKIN+"'";
	public static String UPDATE_CHECKOUT = "update checkout set bookingID = ?, checkOutDate = ? where bookingID = ?";
	public static String SELECT_ALL_CHECK_OUT = "select * from checkout";	
	public static String TABLE_CHECKOUT_COLS_USER = "select c1.bookingID, roomDoorNumber, firstName, lastName, b1.mobile, c1.checkOutDate from checkout c1 inner join quickbooking b1 on c1.bookingID = b1.bookingID inner join customers ct on b1.mobile = ct.mobile where b1.invoiceID not like '"+ HMSConstants.PREFIX_TAX_LESS+"%' order by checkOutDate desc";
	public static String SELECT_CHECKOUT_BOOKING_ID = "select bookingID from checkout";
	public static String SELECT_CHECKOUT_BOOKING_ID_USER = "select c1.bookingID from checkout c1 inner join quickbooking b1 on c1.bookingID = b1.bookingID where b1.invoiceID not like '"+ HMSConstants.PREFIX_TAX_LESS+"%'";
	

				
	//Count On All Tables
	public static String COUNT_CUSTOMERS = "select count(*) from customers";
	public static String COUNT_ROOMS = "select count(*) from rooms";
	public static String COUNT_COUPONS = "select count(*) from coupons";
	public static String COUNT_CHECK_IN = "select count(*) from checkin";
	public static String COUNT_CHECK_OUT = "select count(*) from checkout";
	public static String COUNT_INVOICE_CASH = "select count(*) from quickbooking where invoiceID like 'INC%'";
	public static String COUNT_INVOICE_CARD = "select count(*) from quickbooking where invoiceID like 'INO%'";
	public static String COUNT_INVOICE_TAXL = "select count(*) from quickbooking where invoiceID like 'IWT%'";
	public static String COUNT_HOTEL = "select count(*) from hotel";
	public static String COUNT_GST = "select count(*) from tax_rules";
	public static String COUNT_ROOM_CAPACITY = "select count(*) from roomcapacity";
	public static String COUNT_ROOM_CATEGORY = "select count(*) from roomcategory";
	public static String COUNT_ROOM_FACILITIES = "select count(*) from roomfacilities";
	public static String COUNT_ROOM_PRICE = "select count(*) from roomprice";
	public static String COUNT_SEASONS = "select count(*) from seasons";
	public static String COUNT_BOOKING = "select count(*) from quickbooking";
	public static String COUNT_EMPLOYEES = "select count(*) from employees";
	
	
	//Table Miscellaneous
	public static String MIS_SERVICES = "select serviceName, serviceDesc, servicePrice from miscellaneous where bookingID = ?";
	
	
	//Table Expenses
	public static String COUNT_EXPENSES = "select count(*) from expenses";
	public static final String GET_ALL_EXPENSES = "Select * from EXPENSES";
	public static final String ADD_EXPENSE = "insert into expenses(requestId,department, paidTo, description, amount, createdDate, employeeId) "+ "values(?,?,?,?,?,?,?)";
	public static final String UPDATE_EXPENSE = "update expenses set department = ?, paidTo = ?, description = ?, amount = ? where requestId = ?";
	
		
	
	
	
}
