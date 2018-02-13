package com.hms.model;

public class CheckOut {
	private String checkOutID;
	private java.sql.Timestamp checkOutDate;
	private String bookingID;
	public String getCheckOutID() {
		return checkOutID;
	}
	public void setCheckOutID(String checkOutID) {
		this.checkOutID = checkOutID;
	}
	public String getBookingID() {
		return bookingID;
	}
	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}
	public java.sql.Timestamp getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(java.sql.Timestamp checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

}
