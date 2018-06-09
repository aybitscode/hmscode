package com.aybits.hms.func.customer.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.HMSAddress;
import com.aybits.hms.func.common.beans.IdentificationParams;
import com.aybits.hms.func.payment.beans.PaymentParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;
@JsonRootName("customer")
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
    @JsonProperty("customer_address")
    private HMSAddress customerAddress;
    @JsonProperty("contact_details")
    private ContactDetails contactDetails;
    @JsonProperty("identification_params")
    private IdentificationParams identificationParams;
    @JsonProperty("payment_params")
    private PaymentParams paymentParams;
    @JsonProperty("customer_status")
    private CustomerStatus customerStatus;
    @JsonProperty("date_modified")
    private Date dateModified;
    @JsonProperty("date_created")
    private Date dateCreated;
    @JsonProperty("date_deleted")
    private Date dateDeleted;
    @JsonProperty("hms_hotel_id")
    private Integer hotelId;




    public Customer(){

    }


    public Customer(String customerId, String firstName, String middleName, String lastName,

                    HMSAddress customerAddress, ContactDetails contactDetails,
                    IdentificationParams identificationParams, PaymentParams paymentParams, Integer hotelId) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.customerAddress = customerAddress;
        this.contactDetails = contactDetails;
        this.identificationParams = identificationParams;
        this.paymentParams = paymentParams;
        this.hotelId = hotelId;

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

    public PaymentParams getPaymentParams() {
        return paymentParams;
    }

    public void setPaymentParams(PaymentParams paymentParams) {
        this.paymentParams = paymentParams;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON(this);
    }


}
