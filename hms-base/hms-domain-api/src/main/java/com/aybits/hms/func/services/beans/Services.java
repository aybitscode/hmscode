package com.aybits.hms.func.services.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("amenity")
public class Services {

    @JsonProperty("amenity_id")
    private Integer amenityId;
    @JsonProperty("amenity_name")
    private String amenityName;
    @JsonProperty("amenity_description")
    private String amenityDescription;
    @JsonProperty("is_amenity_available")
    private Boolean isAmenityAvailable;
    @JsonProperty("is_amenity_chargeable")
    private Boolean isAmenityChargeable;
    @JsonProperty("amenity_type")
    private ServicesType servicesType;
    @JsonProperty("amenity_price")
    private Double amenityPrice;


    public Services() {
    }

    public Services(Integer amenityId, String amenityName, String amenityDescription,
                    Boolean isAmenityAvailable, Boolean isAmenityChargeable, ServicesType servicesType,
                    Double amenityPrice) {
        this.amenityId = amenityId;
        this.amenityName = amenityName;
        this.amenityDescription = amenityDescription;
        this.isAmenityAvailable = isAmenityAvailable;
        this.isAmenityChargeable = isAmenityChargeable;
        this.servicesType = servicesType;
        this.amenityPrice = amenityPrice;
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


    public Boolean getAmenityAvailable() {
        return isAmenityAvailable;
    }

    public void setAmenityAvailable(Boolean amenityAvailable) {
        isAmenityAvailable = amenityAvailable;
    }

    public Boolean getAmenityChargeable() {
        return isAmenityChargeable;
    }

    public void setAmenityChargeable(Boolean amenityChargeable) {
        isAmenityChargeable = amenityChargeable;
    }

    public ServicesType getServicesType() {
        return servicesType;
    }

    public void setServicesType(ServicesType servicesType) {
        this.servicesType = servicesType;
    }

    public Double getAmenityPrice() {
        return amenityPrice;
    }

    public void setAmenityPrice(Double amenityPrice) {
        this.amenityPrice = amenityPrice;
    }




    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
