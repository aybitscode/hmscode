package com.aybits.hms.func.amenities.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("amenity")
public class Amenity {

    @JsonProperty("amenity_id")
    private Integer amenityId;
    @JsonProperty("amenity_name")
    private String amenityName;
    @JsonProperty("amenity_description")
    private String amenityDescription;


    public Amenity() {
    }

    public Amenity(Integer amenityId, String amenityName, String amenityDescription) {
        this.amenityId = amenityId;
        this.amenityName = amenityName;
        this.amenityDescription = amenityDescription;
    }

    public Integer getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(Integer amenityId) {
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

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
