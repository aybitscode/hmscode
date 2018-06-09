package com.aybits.hms.api.func.customer.beans;

import com.aybits.hms.api.func.common.beans.ContactDetails;
import com.aybits.hms.api.func.common.beans.HMSAddress;
import com.aybits.hms.api.func.common.beans.IdentificationParams;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Customer {

    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("corporate_id")
    private String corporateId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("address")
    private HMSAddress customerAddress;
    @JsonProperty("contact_details")
    private ContactDetails contactDetails;
    @JsonProperty("identification_params")
    private IdentificationParams identificationParams;
    @JsonProperty("customer_status")
    private CustomerStatus customerStatus;
    @JsonProperty("date_modified")
    private Date dateModified;
    @JsonProperty("date_created")
    private Date dateCreated;
    @JsonProperty("date_deleted")
    private Date dateDeleted;




    public Customer(){

    }


    public Customer(String customerId, String firstName, String middleName, String lastName,
                    HMSAddress customerAddress, ContactDetails contactDetails,
                    IdentificationParams identificationParams) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.customerAddress = customerAddress;
        this.contactDetails = contactDetails;
        this.identificationParams = identificationParams;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public HMSAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(HMSAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public IdentificationParams getIdentificationParams() {
        return identificationParams;
    }

    public void setIdentificationParams(IdentificationParams identificationParams) {
        this.identificationParams = identificationParams;
    }


    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }
}
