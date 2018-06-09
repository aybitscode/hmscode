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
        return HMSJSONParser.convertObjectToJSON(this);
    }
}
