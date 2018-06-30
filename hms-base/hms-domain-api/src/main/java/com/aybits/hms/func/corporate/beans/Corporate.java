package com.aybits.hms.func.corporate.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.HMSAddress;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Corporate {


    @JsonProperty("corporate_id")
    private String corporateId;
    @JsonProperty("corporate_name")
    private String corporateName;
    @JsonProperty("address")
    private HMSAddress corporateAddress;
    @JsonProperty("contact_details")
    private ContactDetails corporateContactDetails;
    @JsonProperty("contact_person")
    private String corporateContactPerson;


    public Corporate() {
    }

    public Corporate(String corporateId, String corporateName,
                     HMSAddress corporateAddress,
                     ContactDetails corporateContactDetails,
                     String corporateContactPerson) {
        this.corporateId = corporateId;
        this.corporateName = corporateName;
        this.corporateAddress = corporateAddress;
        this.corporateContactDetails = corporateContactDetails;
        this.corporateContactPerson = corporateContactPerson;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public HMSAddress getCorporateAddress() {
        return corporateAddress;
    }

    public void setCorporateAddress(HMSAddress corporateAddress) {
        this.corporateAddress = corporateAddress;
    }

    public ContactDetails getCorporateContactDetails() {
        return corporateContactDetails;
    }

    public void setCorporateContactDetails(ContactDetails corporateContactDetails) {
        this.corporateContactDetails = corporateContactDetails;
    }

    public String getCorporateContactPerson() {
        return corporateContactPerson;
    }

    public void setCorporateContactPerson(String corporateContactPerson) {
        this.corporateContactPerson = corporateContactPerson;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
