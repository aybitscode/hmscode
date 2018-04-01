package com.aybits.hms.api.func.customer.beans;

import com.aybits.hms.api.func.common.beans.UserAddress;
import com.aybits.hms.api.func.common.beans.ContactDetails;
import com.aybits.hms.api.func.common.beans.UserAddress;
import com.aybits.hms.api.func.common.beans.UserIdentifier;

public class Customer {

    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private UserAddress customerAddress;
    private ContactDetails contactDetails;
    private UserIdentifier userIdentifier;


    public Customer(){

    }


    public Customer(String customerId, String firstName, String middleName, String lastName,
                    UserAddress customerAddress, ContactDetails contactDetails,
                    UserIdentifier userIdentifier) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.customerAddress = customerAddress;
        this.contactDetails = contactDetails;
        this.userIdentifier = userIdentifier;
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

    public UserAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(UserAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public UserIdentifier getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(UserIdentifier userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
}
