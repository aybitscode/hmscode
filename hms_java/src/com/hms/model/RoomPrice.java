package com.hms.model;

public class RoomPrice {
	private String slno;
	private String room_price_ID;
	private String room_price;
	private String room_category_ID;
	private String facilities_ID;
	private String roomFacilitiesPrice;
	private String roomFacilities;
	public String getRoom_price_ID() {
		return room_price_ID;
	}
	public void setRoom_price_ID(String room_price_ID) {
		this.room_price_ID = room_price_ID;
	}
	public String getRoom_price() {
		return room_price;
	}
	public void setRoom_price(String room_price) {
		this.room_price = room_price;
	}
	public String getRoom_category_ID() {
		return room_category_ID;
	}
	public void setRoom_category_ID(String room_category_ID) {
		this.room_category_ID = room_category_ID;
	}

	public String getFacilities_ID() {
		return facilities_ID;
	}
	public void setFacilities_ID(String facilities_ID) {
		this.facilities_ID = facilities_ID;
	}
	public String getSlno() {
		return slno;
	}
	public void setSlno(String slno) {
		this.slno = slno;
	}
	public String getRoomFacilitiesPrice() {
		return roomFacilitiesPrice;
	}
	public void setRoomFacilitiesPrice(String roomFacilitiesPrice) {
		this.roomFacilitiesPrice = roomFacilitiesPrice;
	}
	public String getRoomFacilities() {
		return roomFacilities;
	}
	public void setRoomFacilities(String roomFacilities) {
		this.roomFacilities = roomFacilities;
	}

}
