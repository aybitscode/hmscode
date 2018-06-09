package com.aybits.hms.func.room.beans;

import com.aybits.hms.func.amenities.beans.Amenity;
import com.aybits.hms.func.facilities.beans.Facility;
import com.aybits.hms.func.services.beans.Service;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RoomCategory {

    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
    private String bedCount;
    private RoomPrice roomPrice;
    private RoomCapacity roomCapacity;
    private Integer adultOccupancy;
    private Integer childOccupancy;
    @JsonProperty("room_facilites")
    private List<Facility> facilities;
    @JsonProperty("room_amenities")
    private List<Amenity> roomAmenities;
    @JsonProperty("room_facilities")
    private List<Facility> roomFacilities;
    @JsonProperty("room_services")
    private List<Service> roomService;

    public RoomCategory() {
    }

    public RoomCategory(Integer categoryId, String categoryName, String categoryDescription,
                        String bedCount, RoomPrice roomPrice, RoomCapacity roomCapacity,
                        Integer adultOccupancy, Integer childOccupancy,
                        List<Facility> facilities, List<Amenity> roomAmenities, List<Facility> roomFacilities,
                        List<Service> roomService) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.bedCount = bedCount;
        this.roomPrice = roomPrice;
        this.roomCapacity = roomCapacity;
        this.adultOccupancy = adultOccupancy;
        this.childOccupancy = childOccupancy;
        this.facilities = facilities;
        this.roomAmenities = roomAmenities;
        this.roomFacilities = roomFacilities;
        this.roomService = roomService;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getBedCount() {
        return bedCount;
    }

    public void setBedCount(String bedCount) {
        this.bedCount = bedCount;
    }

    public RoomPrice getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(RoomPrice roomPrice) {
        this.roomPrice = roomPrice;
    }

    public RoomCapacity getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(RoomCapacity roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public Integer getAdultOccupancy() {
        return adultOccupancy;
    }

    public void setAdultOccupancy(Integer adultOccupancy) {
        this.adultOccupancy = adultOccupancy;
    }

    public Integer getChildOccupancy() {
        return childOccupancy;
    }

    public void setChildOccupancy(Integer childOccupancy) {
        this.childOccupancy = childOccupancy;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<Amenity> getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(List<Amenity> roomAmenities) {
        this.roomAmenities = roomAmenities;
    }

    public List<Facility> getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(List<Facility> roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public List<Service> getRoomService() {
        return roomService;
    }

    public void setRoomService(List<Service> roomService) {
        this.roomService = roomService;
    }
}