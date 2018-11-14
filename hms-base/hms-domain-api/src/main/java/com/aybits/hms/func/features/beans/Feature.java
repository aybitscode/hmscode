package com.aybits.hms.func.features.beans;

import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.service.beans.Service;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Feature {

    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("facilities")
    private List<Facility> facilityList;
    @JsonProperty("amenities")
    private List<Amenity> amenityList;
    @JsonProperty("services")
    private List<Service> serviceList;

    public Feature(){}

    public Feature(String hotelId, List<Facility> facilityList, List<Amenity> amenityList, List<Service> serviceList) {
        this.hotelId = hotelId;
        this.facilityList = facilityList;
        this.amenityList = amenityList;
        this.serviceList = serviceList;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public List<Facility> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<Facility> facilityList) {
        this.facilityList = facilityList;
    }

    public List<Amenity> getAmenityList() {
        return amenityList;
    }

    public void setAmenityList(List<Amenity> amenityList) {
        this.amenityList = amenityList;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
