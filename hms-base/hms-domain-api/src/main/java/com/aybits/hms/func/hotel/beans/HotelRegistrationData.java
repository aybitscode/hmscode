package com.aybits.hms.func.hotel.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("hotel_registration_data")
class HotelRegistrationData {

    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("hotel_reg_id")
    private String hotelRegistrationId;
    @JsonProperty("building_permit_id")
    private String buildingPermitId;
    @JsonProperty("fire_safety_permit_id")
    private String fireSafetyPermitId;
    @JsonProperty("police_license_id")
    private String policeLicenseId;
    @JsonProperty("health_trade_license_id")
    private String healthTradeLicenseId;
    @JsonProperty("liquor_license_id")
    private String liquorLicenseId;
    @JsonProperty("fssai_license_id")
    private String fssaiLicenseId;
    @JsonProperty("gst_no")
    private String gstNumber;
    @JsonProperty("esi_reg_id")
    private String esiRegistrationId;
    @JsonProperty("epf_reg_id")
    private String pfRegistrationId;

    public HotelRegistrationData(String hotelId, String hotelRegistrationId, String buildingPermitId, String fireSafetyPermitId, String policeLicenseId, String healthTradeLicenseId, String liquorLicenseId, String fssaiLicenseId, String gstNumber, String esiRegistrationId, String pfRegistrationId) {
        this.hotelId = hotelId;
        this.hotelRegistrationId = hotelRegistrationId;
        this.buildingPermitId = buildingPermitId;
        this.fireSafetyPermitId = fireSafetyPermitId;
        this.policeLicenseId = policeLicenseId;
        this.healthTradeLicenseId = healthTradeLicenseId;
        this.liquorLicenseId = liquorLicenseId;
        this.fssaiLicenseId = fssaiLicenseId;
        this.gstNumber = gstNumber;
        this.esiRegistrationId = esiRegistrationId;
        this.pfRegistrationId = pfRegistrationId;
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

    public String getBuildingPermitId() {
        return buildingPermitId;
    }

    public void setBuildingPermitId(String buildingPermitId) {
        this.buildingPermitId = buildingPermitId;
    }

    public String getFireSafetyPermitId() {
        return fireSafetyPermitId;
    }

    public void setFireSafetyPermitId(String fireSafetyPermitId) {
        this.fireSafetyPermitId = fireSafetyPermitId;
    }

    public String getPoliceLicenseId() {
        return policeLicenseId;
    }

    public void setPoliceLicenseId(String policeLicenseId) {
        this.policeLicenseId = policeLicenseId;
    }

    public String getHealthTradeLicenseId() {
        return healthTradeLicenseId;
    }

    public void setHealthTradeLicenseId(String healthTradeLicenseId) {
        this.healthTradeLicenseId = healthTradeLicenseId;
    }

    public String getLiquorLicenseId() {
        return liquorLicenseId;
    }

    public void setLiquorLicenseId(String liquorLicenseId) {
        this.liquorLicenseId = liquorLicenseId;
    }

    public String getFssaiLicenseId() {
        return fssaiLicenseId;
    }

    public void setFssaiLicenseId(String fssaiLicenseId) {
        this.fssaiLicenseId = fssaiLicenseId;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getEsiRegistrationId() {
        return esiRegistrationId;
    }

    public void setEsiRegistrationId(String esiRegistrationId) {
        this.esiRegistrationId = esiRegistrationId;
    }

    public String getPfRegistrationId() {
        return pfRegistrationId;
    }

    public void setPfRegistrationId(String pfRegistrationId) {
        this.pfRegistrationId = pfRegistrationId;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
