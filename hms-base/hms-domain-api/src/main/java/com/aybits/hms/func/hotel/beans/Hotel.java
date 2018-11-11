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
    @JsonProperty("hotel_status")
    private Status hotelStatus;
    public Hotel() {
    }

    public Hotel(String hotelId,
                 HotelAttributes hotelAttributes,
                 Status hotelStatus){
        this.hotelId = hotelId;
        this.hotelAttributes = hotelAttributes;
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
