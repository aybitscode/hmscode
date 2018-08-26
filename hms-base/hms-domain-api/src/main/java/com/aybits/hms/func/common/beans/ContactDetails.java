package com.aybits.hms.func.common.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactDetails {

    @JsonProperty("primary_email")
    private String primaryEmail;

    @JsonProperty("primary_phone")
    private String primaryPhone;

    @JsonProperty("secondary_email")
    private String secondaryEmail;

    @JsonProperty("secondary_phone")
    private String secondaryPhone;

    @JsonProperty("fax_number")
    private String faxNumber;

    @JsonProperty("mobile_number1")
    private String primaryMobileNumber;

    @JsonProperty("mobile_number2")
    private String secondaryMobileNumber;



    public ContactDetails(){

    }

    public ContactDetails(String primaryEmail, String primaryPhone,
                          String secondaryEmail, String secondaryPhone,
                          String faxNumber, String primaryMobileNumber,
                          String secondaryMobileNumber) {
        this.primaryEmail = primaryEmail;
        this.primaryPhone = primaryPhone;
        this.secondaryEmail = secondaryEmail;
        this.secondaryPhone = secondaryPhone;
        this.faxNumber = faxNumber;
        this.primaryMobileNumber = primaryMobileNumber;
        this.secondaryMobileNumber = secondaryMobileNumber;
    }

    public ContactDetails(String primaryEmail, String primaryPhone,
                          String secondaryEmail, String secondaryPhone, String primaryMobileNumber,
                          String secondaryMobileNumber) {
        this.primaryEmail = primaryEmail;
        this.primaryPhone = primaryPhone;
        this.secondaryEmail = secondaryEmail;
        this.secondaryPhone = secondaryPhone;
        this.primaryMobileNumber = primaryMobileNumber;
        this.secondaryMobileNumber = secondaryMobileNumber;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }


    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPrimaryMobileNumber() {
        return primaryMobileNumber;
    }

    public void setPrimaryMobileNumber(String primaryMobileNumber) {
        this.primaryMobileNumber = primaryMobileNumber;
    }


    public String getSecondaryMobileNumber() {
        return secondaryMobileNumber;
    }

    public void setSecondaryMobileNumber(String secondaryMobileNumber) {
        this.secondaryMobileNumber = secondaryMobileNumber;
    }

}
