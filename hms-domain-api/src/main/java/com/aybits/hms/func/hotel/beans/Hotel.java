package com.aybits.hms.func.hotel.beans;

import com.aybits.hms.api.func.facilities.beans.Facility;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Hotel {


    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("hotel_features")
    private HotelFeatures hotelFeatures;
    @JsonProperty("hotel_registration_no")
    private String registrationNo;
    @JsonProperty("hotel_gst_no")
    private String gstNo;
    @JsonProperty("tax_config_id")
    private String taxConfigId;
    @JsonProperty("hotel_facilities")
    private List<Facility> hotelFacilities;

    public Hotel() {
    }

    public Hotel(String hotelId,
                 HotelFeatures hotelAttributes,
                 String registrationNo,
                 String gstNo,
                 String taxConfigId,
                 List<Facility> hotelFacilities) {
        this.hotelId = hotelId;
        this.hotelFeatures = hotelAttributes;
        this.registrationNo = registrationNo;
        this.gstNo = gstNo;
        this.taxConfigId = taxConfigId;
        this.hotelFacilities = hotelFacilities;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getTaxConfigId() {
        return taxConfigId;
    }

    public void setTaxConfigId(String taxConfigId) {
        this.taxConfigId = taxConfigId;
    }

    public HotelFeatures getHotelFeatures() {
        return hotelFeatures;
    }

    public void setHotelFeatures(HotelFeatures hotelFeatures) {
        this.hotelFeatures = hotelFeatures;
    }

    public List<Facility> getHotelFacilities() {
        return hotelFacilities;
    }

    public void setHotelFacilities(List<Facility> hotelFacilities) {
        this.hotelFacilities = hotelFacilities;
    }

}
