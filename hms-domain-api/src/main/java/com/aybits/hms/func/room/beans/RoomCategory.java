package com.aybits.hms.func.room.beans;

import com.aybits.hms.api.func.amenities.beans.Amenity;
import com.aybits.hms.api.func.facilities.beans.Facility;
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
    private List<>;


    


}
