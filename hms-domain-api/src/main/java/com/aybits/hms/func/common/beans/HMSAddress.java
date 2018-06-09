package com.aybits.hms.func.common.beans;


import com.fasterxml.jackson.annotation.JsonProperty;

public class HMSAddress {

    @JsonProperty("building_no")
    private String buildingNo;

    @JsonProperty("apartment_name")
    private String apartmentName;

    @JsonProperty("street")
    private String street;

    @JsonProperty("landmark")
    private String landmark;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("city")
    private String city;

    @JsonProperty("pin_code")
    private String pinCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("geo_location")
    private String geoLocation;


    public HMSAddress() {
    }
    public HMSAddress(String buildingNo, String apartmentName, String street, String landmark, String locality, String city, String pinCode, String country, String geoLocation) {
        this.buildingNo = buildingNo;
        this.apartmentName = apartmentName;
        this.street = street;
        this.landmark = landmark;
        this.locality = locality;
        this.city = city;
        this.pinCode = pinCode;
        this.country = country;
        this.geoLocation = geoLocation;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }
}