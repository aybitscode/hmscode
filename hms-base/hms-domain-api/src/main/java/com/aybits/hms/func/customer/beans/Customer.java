package com.aybits.hms.func.customer.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.identificationparam.beans.IdentificationParam;
import com.aybits.hms.func.common.beans.Status;
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
    @JsonProperty("customer_full_name")
    private String customerFullName;
    @JsonProperty("customer_address")
    private Address customerAddress;
    @JsonProperty("contact_details")
    private ContactDetails contactDetails;
    @JsonProperty("identification_params")
    private IdentificationParam identificationParam;
    @JsonProperty("payment_params")
    private PaymentParams paymentParams;
    @JsonProperty("customer_status")
    private Status customerStatus;
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


    public Customer(String customerId, String customerFullName,
                    Status customerStatus,
                    Address customerAddress, ContactDetails contactDetails,
                    IdentificationParam identificationParam, PaymentParams paymentParams, Integer hotelId) {

        this.customerId = customerId;
        this.customerFullName = customerFullName;
        this.customerAddress = customerAddress;
        this.contactDetails = contactDetails;
        this.identificationParam = identificationParam;
        this.paymentParams = paymentParams;
        this.hotelId = hotelId;
        this.customerStatus = customerStatus;

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public IdentificationParam getIdentificationParam() {
        return identificationParam;
    }

    public void setIdentificationParam(IdentificationParam identificationParam) {
        this.identificationParam = identificationParam;
    }


    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public Status getStatus() {
        return customerStatus;
    }

    public void setStatus(Status customerStatus) {
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
