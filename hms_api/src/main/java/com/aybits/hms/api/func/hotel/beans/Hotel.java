package com.aybits.hms.api.func.hotel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hotel {


    @JsonProperty("hotel_id")
    private String hotelId;

    @JsonProperty("hotel_attributes")
    private HotelAttributes hotelAttributes;

    @JsonProperty("hotel_config")
    private HotelConfiguration hotelConfig;


    @JsonProperty("hotel_features")
    private HotelFeatures hotelFeatures;

    public HotelAttributes getHotelAttributes() {
        return hotelAttributes;
    }

    public void setHotelAttributes(HotelAttributes hotelAttributes) {
        this.hotelAttributes = hotelAttributes;
    }

    public HotelConfiguration getHotelConfig() {
        return hotelConfig;
    }

    public void setHotelConfig(HotelConfiguration hotelConfig) {
        this.hotelConfig = hotelConfig;
    }

    public HotelFeatures getHotelFeatures() {
        return hotelFeatures;
    }

    public void setHotelFeatures(HotelFeatures hotelFeatures) {
        this.hotelFeatures = hotelFeatures;
    }

}
