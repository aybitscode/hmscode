package com.hms.model;


public class CheckIn {
	private String slno;
	private String checkInID;
	private java.sql.Timestamp checkinDate;
	private String bookingID;
	private String advanceAmt;
	private String totalAdults;
	private String totalChilds;
	private String roomDoorNumber;
	public String getSlno() {
		return slno;
	}
	public void setSlno(String slno) {
		this.slno = slno;
	}
	public String getCheckInID() {
		return checkInID;
	}
	public void setCheckInID(String checkInID) {
		this.checkInID = checkInID;
	}

	public String getBookingID() {
		return bookingID;
	}
	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}
//	public String getCustomerID() {
//		return customerID;
//	}
//	public void setCustomerID(String customerID) {
//		this.customerID = customerID;
//	}
	public String getAdvanceAmt() {
		return advanceAmt;
	}
	public void setAdvanceAmt(String advanceAmt) {
		this.advanceAmt = advanceAmt;
	}
	public String getTotalAdults() {
		return totalAdults;
	}
	public void setTotalAdults(String totalAdults) {
		this.totalAdults = totalAdults;
	}
	public String getTotalChilds() {
		return totalChilds;
	}
	public void setTotalChilds(String totalChilds) {
		this.totalChilds = totalChilds;
	}
	public java.sql.Timestamp getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(java.sql.Timestamp checkinDate) {
		this.checkinDate = checkinDate;
	}
	public String getRoomDoorNumber() {
		return roomDoorNumber;
	}
	public void setRoomDoorNumber(String roomDoorNumber) {
		this.roomDoorNumber = roomDoorNumber;
	}



}
