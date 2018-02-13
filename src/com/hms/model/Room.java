package com.hms.model;

public class Room {
	private String slno;
	private String room_ID;
	private String roomDoorNumber;
	private String 	roomCategoryID;
	private String roomCost;
	private String facilitiesCost;
	private String room_count;
	private String room_status;
	private String roomFacilities;
	public String getRoom_ID() {
		return room_ID;
	}
	public void setRoom_ID(String room_ID) {
		this.room_ID = room_ID;
	}

	
	public String getRoom_count() {
		return room_count;
	}
	public void setRoom_count(String room_count) {
		this.room_count = room_count;
	}
	public String getRoom_status() {
		return room_status;
	}
	public void setRoom_status(String room_status) {
		this.room_status = room_status;
	}
	public String getSlno() {
		return slno;
	}
	public void setSlno(String slno) {
		this.slno = slno;
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
	public String getRoomDoorNumber() {
		return roomDoorNumber;
	}
	public void setRoomDoorNumber(String roomDoorNumber) {
		this.roomDoorNumber = roomDoorNumber;
	}
	public String getRoomCategoryID() {
		return roomCategoryID;
	}
	public void setRoomCategoryID(String roomCategoryID) {
		this.roomCategoryID = roomCategoryID;
	}
	public String getRoomFacilities() {
		return roomFacilities;
	}
	public void setRoomFacilities(String roomFacilities) {
		this.roomFacilities = roomFacilities;
	}

}
