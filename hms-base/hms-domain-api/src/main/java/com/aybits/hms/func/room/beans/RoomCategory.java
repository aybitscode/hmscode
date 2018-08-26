package com.aybits.hms.func.room.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.helpdesk.beans.Service;
import com.aybits.hms.func.voucher.beans.Voucher;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("room_category")
public class RoomCategory {
    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("room_category_id")
    private String categoryId;
    @JsonProperty("room_category_name")
    private String categoryName;
    @JsonProperty("room_category_desc")
    private String categoryDescription;
    @JsonProperty("bed_count")
    private String bedCount;
    @JsonProperty("category_room_count")
    private Integer roomCount;
    @JsonProperty("room_category_price")
    private RoomPrice roomPrice;
    @JsonProperty("room_category_capacity")
    private RoomCapacity roomCapacity;
    @JsonProperty("adult_occupancy")
    private Integer adultOccupancy;
    @JsonProperty("child_occupancy")
    private Integer childOccupancy;
    @JsonProperty("room_category_amenities")
    private List<Amenity> categoryAmenities;
    @JsonProperty("room_category_facilities")
    private List<Facility> categoryFacilities;
    @JsonProperty("room_category_services")
    private List<Service> categoryServices;
    @JsonProperty("room_category_vouchers")
    private List<Voucher> categoryVouchers;
    @JsonProperty("is_category_available")
    private Boolean isCategoryAvailable;

    public RoomCategory() {
    }

    public RoomCategory(String hotelId,String categoryId,
                        String categoryName, String categoryDescription,
                        String bedCount, Integer roomCount,
                        RoomPrice roomPrice, RoomCapacity roomCapacity,
                        Integer adultOccupancy, Integer childOccupancy,
                        List<Amenity> categoryAmenities, List<Facility> categoryFacilities,
                        List<Service> categoryServices, List<Voucher> categoryVouchers,
                        Boolean isCategoryAvailable) {
        this.hotelId = hotelId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.bedCount = bedCount;
        this.roomCount = roomCount;
        this.roomPrice = roomPrice;
        this.roomCapacity = roomCapacity;
        this.adultOccupancy = adultOccupancy;
        this.childOccupancy = childOccupancy;
        this.categoryAmenities = categoryAmenities;
        this.categoryFacilities = categoryFacilities;
        this.categoryServices = categoryServices;
        this.categoryVouchers = categoryVouchers;
        this.isCategoryAvailable = isCategoryAvailable;
    }


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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

    public List<Amenity> getCategoryAmenities() {
        return categoryAmenities;
    }

    public void setCategoryAmenities(List<Amenity> categoryAmenities) {
        this.categoryAmenities = categoryAmenities;
    }

    public List<Facility> getCategoryFacilities() {
        return categoryFacilities;
    }

    public void setCategoryFacilities(List<Facility> categoryFacilities) {
        this.categoryFacilities = categoryFacilities;
    }

    public List<Service> getCategoryServices() {
        return categoryServices;
    }

    public void setCategoryServices(List<Service> categoryServices) {
        this.categoryServices = categoryServices;
    }

    public List<Voucher> getCategoryVouchers() {
        return categoryVouchers;
    }

    public void setCategoryVouchers(List<Voucher> categoryVouchers) {
        this.categoryVouchers = categoryVouchers;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Boolean getCategoryAvailable() {
        return isCategoryAvailable;
    }

    public void setCategoryAvailable(Boolean categoryAvailable) {
        isCategoryAvailable = categoryAvailable;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
