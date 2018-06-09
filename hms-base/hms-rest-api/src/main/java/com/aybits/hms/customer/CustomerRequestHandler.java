package com.aybits.hms.customer;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.func.customer.api.CustomerService;
import com.aybits.hms.func.customer.beans.Customer;
import jdk.nashorn.internal.parser.JSONParser;
import spark.Request;
import spark.Response;

import java.util.List;

public class CustomerRequestHandler implements HmsRequestHandler {
    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Customer request handler invoked");

        String action = request.pathInfo().split("/")[2];
        String message = "";
        switch (action) {
            case "add":
                message = addCustomer(request);
                break;
            case "update":
                message = updateCustomer(request);
                break;
            case "get":
                message = getCustomer(request);
                break;
            case "getAll":
                message = getAllCustomers(request);
                break;
        }
        return message;
    }

    private String addCustomer(Request request) {
        System.out.println("adding new customer");
        try {
            String jsonString = request.body().toString();
            CustomerService customerService = new CustomerService();
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = customerService.addCustomer(customer);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(customer, result));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while adding customer", false));
        }
    }

    private String updateCustomer(Request request) {
        System.out.println("updating customer");
        try {
            String jsonString = request.body().toString();
            CustomerService customerService = new CustomerService();
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = customerService.updateCustomer(customer);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(customer, result));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while updating customer", false));
        }
    }

    private String getCustomer(Request request) {
        System.out.println("get customer");
        try {
            String jsonString = request.body().toString();
            CustomerService customerService = new CustomerService();
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            Customer result = customerService.getCustomerById(customer.getCustomerId());
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting customer data", false));
        }
    }

    private String getAllCustomers(Request request) {
        System.out.println("get all customers");
        try {
            CustomerService customerService = new CustomerService();
            List<Customer> result = customerService.getAllCustomers();
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting all customer data", false));
        }
    }

    private HmsResponse getHmsResponse(Object responseData, boolean result) {
        HmsResponse response;
        if (result) {
            response = new HmsResponse(200, "SUCCESS", responseData);
        } else {
            response = new HmsResponse(401, "FAILED", responseData);
        }
        return response;
    }
}
