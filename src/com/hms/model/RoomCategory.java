package com.hms.model;


public class RoomCategory {
	private String slno;
	private String category_ID;
	private String category_Name;
	private String category_Desc;
	private String category_Beds;
	private String room_capacity_ID;
	private String room_facilities_ID;
	public String getCategory_ID() {
		return category_ID;
	}
	public void setCategory_ID(String category_ID) {
		
		
		
		
		this.category_ID = category_ID;
	}
	public String getCategory_Name() {
		return category_Name;
	}
	public void setCategory_Name(String category_Name) {
		this.category_Name = category_Name;
	}
	public String getCategory_Desc() {
		return category_Desc;
	}
	public void setCategory_Desc(String category_Desc) {
		this.category_Desc = category_Desc;
	}
	public String getCategory_Beds() {
		return category_Beds;
	}
	public void setCategory_Beds(String category_Beds) {
		this.category_Beds = category_Beds;
	}
	public String getRoom_capacity_ID() {
		return room_capacity_ID;
	}
	public void setRoom_capacity_ID(String room_capacity_ID) {
		this.room_capacity_ID = room_capacity_ID;
	}
	public String getRoom_facilities_ID() {
		return room_facilities_ID;
	}
	public void setRoom_facilities_ID(String room_facilities_ID) {
		this.room_facilities_ID = room_facilities_ID;
	}
	public String getSlno() {
		return slno;
	}
	public void setSlno(String slno) {
		this.slno = slno;
	}

}
