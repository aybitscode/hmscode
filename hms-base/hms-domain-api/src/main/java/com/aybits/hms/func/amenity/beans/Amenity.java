package com.aybits.hms.func.amenity.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

@JsonRootName("amenity")
public class Amenity {

    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("amenity_id")
    private String amenityId;
    @JsonProperty("amenity_name")
    private String amenityName;
    @JsonProperty("amenity_description")
    private String amenityDescription;
    @JsonProperty("is_available")
    private Boolean isAvailable;
    @JsonProperty("is_chargeable")
    private Boolean isChargeable;
    @JsonProperty("amenity_type")
    private AmenityType amenityType;
    @JsonProperty("amenity_charges")
    private Double amenityCharges;



    public Amenity() {
    }

    public Amenity(String hotelId,String amenityId, String amenityName, String amenityDescription,
                   Boolean isAvailable, Boolean isChargeable, AmenityType amenityType,
                   Double amenityCharges) {
        this.hotelId = hotelId;
        this.amenityId = amenityId;
        this.amenityName = amenityName;
        this.amenityDescription = amenityDescription;
        this.isAvailable = isAvailable;
        this.isChargeable = isChargeable;
        this.amenityType = amenityType;
        this.amenityCharges = amenityCharges;
    }

    public String getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(String amenityId) {
        this.amenityId = amenityId;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public String getAmenityDescription() {
        return amenityDescription;
    }

    public void setAmenityDescription(String amenityDescription) {
        this.amenityDescription = amenityDescription;
    }


    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean amenityAvailable) {
        isAvailable = amenityAvailable;
    }

    public Boolean isChargeable() {
        return isChargeable;
    }

    public void setIsChargeable(Boolean amenityChargeable) {
        isChargeable = amenityChargeable;
    }

    public AmenityType getAmenityType() {
        return amenityType;
    }

    public void setAmenityType(AmenityType amenityType) {
        this.amenityType = amenityType;
    }

    public Double getAmenityCharges() {
        return amenityCharges;
    }

    public void setAmenityCharges(Double amenityCharges) {
        this.amenityCharges = amenityCharges;
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
