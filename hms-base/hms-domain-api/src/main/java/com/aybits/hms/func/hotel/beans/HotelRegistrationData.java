package com.aybits.hms.func.hotel.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;



@JsonRootName("hotel_registration_data")
public class HotelRegistrationData {



    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("hotel_reg_no")
    private String hotelRegistrationId;
    @JsonProperty("building_permit_no")
    private String buildingPermitNo;
    @JsonProperty("fire_safety_permit_no")
    private String fireSafetyPermitNo;
    @JsonProperty("police_license_no")
    private String policeLicenseNo;
    @JsonProperty("health_trade_license_no")
    private String healthTradeLicenseNo;
    @JsonProperty("liquor_license_no")
    private String liquorLicenseNo;
    @JsonProperty("fssai_license_no")
    private String fssaiLicenseNo;
    @JsonProperty("gst_no")
    private String gstNo;
    @JsonProperty("esi_reg_no")
    private String esiRegistrationNo;
    @JsonProperty("epf_reg_no")
    private String pfRegistrationNo;

    public HotelRegistrationData(){

    }

    public HotelRegistrationData(String hotelId,String hotelRegistrationId, String buildingPermitNo, String fireSafetyPermitNo, String policeLicenseNo, String healthTradeLicenseNo, String liquorLicenseNo, String fssaiLicenseNo, String gstNo, String esiRegistrationNo, String pfRegistrationNo) {
        this.hotelRegistrationId = hotelRegistrationId;
        this.buildingPermitNo = buildingPermitNo;
        this.fireSafetyPermitNo = fireSafetyPermitNo;
        this.policeLicenseNo = policeLicenseNo;
        this.healthTradeLicenseNo = healthTradeLicenseNo;
        this.liquorLicenseNo = liquorLicenseNo;
        this.fssaiLicenseNo = fssaiLicenseNo;
        this.gstNo = gstNo;
        this.esiRegistrationNo = esiRegistrationNo;
        this.pfRegistrationNo = pfRegistrationNo;
        this.hotelId = hotelId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }


    public String getHotelRegistrationId() {
        return hotelRegistrationId;
    }

    public void setHotelRegistrationId(String hotelRegistrationId) {
        this.hotelRegistrationId = hotelRegistrationId;
    }

    public String getBuildingPermitNo() {
        return buildingPermitNo;
    }

    public void setBuildingPermitNo(String buildingPermitNo) {
        this.buildingPermitNo = buildingPermitNo;
    }

    public String getFireSafetyPermitNo() {
        return fireSafetyPermitNo;
    }

    public void setFireSafetyPermitNo(String fireSafetyPermitNo) {
        this.fireSafetyPermitNo = fireSafetyPermitNo;
    }

    public String getPoliceLicenseNo() {
        return policeLicenseNo;
    }

    public void setPoliceLicenseNo(String policeLicenseNo) {
        this.policeLicenseNo = policeLicenseNo;
    }

    public String getHealthTradeLicenseNo() {
        return healthTradeLicenseNo;
    }

    public void setHealthTradeLicenseNo(String healthTradeLicenseNo) {
        this.healthTradeLicenseNo = healthTradeLicenseNo;
    }

    public String getLiquorLicenseNo() {
        return liquorLicenseNo;
    }

    public void setLiquorLicenseNo(String liquorLicenseNo) {
        this.liquorLicenseNo = liquorLicenseNo;
    }

    public String getFssaiLicenseNo() {
        return fssaiLicenseNo;
    }

    public void setFssaiLicenseNo(String fssaiLicenseNo) {
        this.fssaiLicenseNo = fssaiLicenseNo;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getEsiRegistrationNo() {
        return esiRegistrationNo;
    }

    public void setEsiRegistrationNo(String esiRegistrationNo) {
        this.esiRegistrationNo = esiRegistrationNo;
    }

    public String getPfRegistrationNo() {
        return pfRegistrationNo;
    }

    public void setPfRegistrationNo(String pfRegistrationNo) {
        this.pfRegistrationNo = pfRegistrationNo;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
