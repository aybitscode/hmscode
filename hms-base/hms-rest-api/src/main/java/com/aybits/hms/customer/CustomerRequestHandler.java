package com.aybits.hms.customer;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.IdentificationParams;
import com.aybits.hms.func.common.beans.IdentifierType;
import com.aybits.hms.func.customer.api.CustomerService;
import com.aybits.hms.func.customer.beans.Customer;
import spark.Request;
import spark.Response;

import java.util.List;

public class CustomerRequestHandler implements HmsRequestHandler {
    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Customer request handler invoked");
        System.out.println("adding new customer");
        CustomerService customerService = new CustomerService();

        Customer customer = new Customer();
        customer.setFirstName("Mahaboob");
        customer.setMiddleName("Mahaboob");
        customer.setLastName("Mahaboob");
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setPrimaryEmail("kmb.noor@gmail.com");
        contactDetails.setPrimaryPhone("8861522542");
        customer.setContactDetails(contactDetails);
        IdentificationParams identificationParams = new IdentificationParams();
        identificationParams.setIdentifierType(IdentifierType.AADHAR_CARD);
        identificationParams.setIdentifierValue(88);
        customer.setIdentificationParams(identificationParams);

        customer.setCustomerId("1000");

        customerService.addCustomer(customer);

        List<Customer> list = customerService.getAllCustomers();
        System.out.println("customer list" + list);
        return null;
    }
}
