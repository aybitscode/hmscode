package com.hms.model;


public class Booking {
	private String slno;
	private String booking_Id;
	private java.sql.Timestamp bookingDate;
	private java.sql.Timestamp bookedDate;
	private java.sql.Timestamp checkoutDate;
	private String booking_room_category;
	private String booking_room_door_number;
	private String booking_customer_mobile;
	private String booking_total_nights;
	private String booking_total_rooms;
	private String roomCost;
	private String facilitiesCost;
	private String gross_amount;
	private String status;
	private String couponName;
	private String customerName; 
	private String discount;
	private String payment_mode;
	private String invoiceID;
	private String misCost;
	
	
	
	public String getBooking_Id() {
		return booking_Id;
	}
	public void setBooking_Id(String booking_Id) {
		this.booking_Id = booking_Id;
	}

	public String getBooking_room_category() {
		return booking_room_category;
	}
	public void setBooking_room_category(String booking_room_category) {
		this.booking_room_category = booking_room_category;
	}
//	public String getBooking_room_id() {
//		return booking_room_id;
//	}
//	public void setBooking_room_id(String booking_room_id) {
//		this.booking_room_id = booking_room_id;
//	}
//	public String getBooking_customer_id() {
//		return booking_customer_id;
//	}
//	public void setBooking_customer_id(String booking_customer_id) {
//		this.booking_customer_id = booking_customer_id;
//	}
//	public String getBooking_status() {
//		return booking_status;
//	}
//	public void setBooking_status(String booking_status) {
//		this.booking_status = booking_status;
//	}
	public String getBooking_total_nights() {
		return booking_total_nights;
	}
	public void setBooking_total_nights(String booking_total_nights) {
		this.booking_total_nights = booking_total_nights;
	}
	public String getBooking_total_rooms() {
		return booking_total_rooms;
	}
	public void setBooking_total_rooms(String booking_total_rooms) {
		this.booking_total_rooms = booking_total_rooms;
	}


	public String getSlno() {
		return slno;
	}
	public void setSlno(String slno) {
		this.slno = slno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBooking_customer_mobile() {
		return booking_customer_mobile;
	}
	public void setBooking_customer_mobile(String booking_customer_mobile) {
		this.booking_customer_mobile = booking_customer_mobile;
	}
	public String getBooking_room_door_number() {
		return booking_room_door_number;
	}
	public void setBooking_room_door_number(String booking_room_door_number) {
		this.booking_room_door_number = booking_room_door_number;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getRoomCost() {
		return roomCost;
	}
	public void setRoomCost(String roomCost) {
		this.roomCost = roomCost;
	}
	public String getFacilitiesCost() {
		return facilitiesCost;
	}
	public void setFacilitiesCost(String facilitiesCost) {
		this.facilitiesCost = facilitiesCost;
	}

	public String getGross_amount() {
		return gross_amount;
	}
	public void setGross_amount(String gross_amount) {
		this.gross_amount = gross_amount;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	public String getMisCost() {
		return misCost;
	}
	public void setMisCost(String misCost) {
		this.misCost = misCost;
	}
	public java.sql.Timestamp getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(java.sql.Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}
	public java.sql.Timestamp getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(java.sql.Timestamp bookedDate) {
		this.bookedDate = bookedDate;
	}
	public java.sql.Timestamp getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(java.sql.Timestamp checkoutDate) {
		this.checkoutDate = checkoutDate;
	}


	
	

}
