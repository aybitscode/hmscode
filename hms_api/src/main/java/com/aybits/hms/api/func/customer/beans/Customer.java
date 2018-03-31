package com.aybits.hms.api.func.customer.beans;

public class Customer {

    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private CustomerAddress address;
    private ContactDetails contactDetails;
    private LegalIdentifiers legalIdentifiers;


    public Customer(){

    }


    public Customer(String customerId, String firstName, String middleName, String lastName,
                    CustomerAddress address, ContactDetails contactDetails,
                    LegalIdentifiers legalIdentifiers) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.contactDetails = contactDetails;
        this.legalIdentifiers = legalIdentifiers;
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

    public CustomerAddress getAddress() {
        return address;
    }

    public void setAddress(CustomerAddress address) {
        this.address = address;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public LegalIdentifiers getLegalIdentifiers() {
        return legalIdentifiers;
    }

    public void setLegalIdentifiers(LegalIdentifiers legalIdentifiers) {
        this.legalIdentifiers = legalIdentifiers;
    }
}
