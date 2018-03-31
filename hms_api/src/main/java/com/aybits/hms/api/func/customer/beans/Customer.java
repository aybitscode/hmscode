package com.aybits.hms.api.func.customer.beans;

public class Customer {

    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private CustomerAddress customerAddress;
    private ContactDetails contactDetails;
    private CustomerIdentifiers customerIdentifiers;


    public Customer(){

    }


    public Customer(String customerId, String firstName, String middleName, String lastName,
                    CustomerAddress customerAddress, ContactDetails contactDetails,
                    CustomerIdentifiers customerIdentifiers) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.customerAddress = customerAddress;
        this.contactDetails = contactDetails;
        this.customerIdentifiers = customerIdentifiers;
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

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public CustomerIdentifiers getCustomerIdentifiers() {
        return customerIdentifiers;
    }

    public void setCustomerIdentifiers(CustomerIdentifiers customerIdentifiers) {
        this.customerIdentifiers = customerIdentifiers;
    }
}
