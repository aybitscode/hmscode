package com.aybits.hms.func.hotel.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("hotel")
public class Hotel {


    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("hotel_attributes")
    private HotelAttributes hotelAttributes;
    @JsonProperty("hotel_registration_data")
    private HotelRegistrationData hotelRegistrationData;
    @JsonProperty("hotel_status")
    private Status hotelStatus;
    public Hotel() {
    }

    public Hotel(String hotelId,
                 HotelAttributes hotelAttributes,
                 HotelRegistrationData hotelRegistrationData,
                 Status hotelStatus) {
        this.hotelId = hotelId;
        this.hotelAttributes = hotelAttributes;
        this.hotelRegistrationData = hotelRegistrationData;
        this.hotelStatus = hotelStatus;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public HotelAttributes getHotelAttributes() {
        return hotelAttributes;
    }

    public void setHotelAttributes(HotelAttributes hotelAttributes) {
        this.hotelAttributes = hotelAttributes;
    }

    public HotelRegistrationData getHotelRegistrationData() {
        return hotelRegistrationData;
    }

    public void setHotelRegistrationData(HotelRegistrationData hotelRegistrationData) {
        this.hotelRegistrationData = hotelRegistrationData;
    }


    public Status getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(Status hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }

}
