package com.aybits.hms.customer;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.customer.api.CustomerAPI;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.login.LoginRequestHandler;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

import java.util.List;

public class CustomerRequestHandler implements HmsRequestHandler {
    static Logger Log = Logger.getLogger(CustomerRequestHandler.class);

    @Override
    public ValidationResult validateRequestData(Request request, Response response) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        return result;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Customer request handler invoked");

        ValidationResult result = validateRequest(request, response);
        if (result != null) {
            return result.getMessage();
        }

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
        Log.info("adding new customer");
        try {
            String jsonString = request.body().toString();
            CustomerAPI customerAPI = new CustomerAPI();
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = customerAPI.addCustomer(customer);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(customer, result));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while adding customer", false));
        }
    }

    private String updateCustomer(Request request) {
        Log.info("updating customer");
        try {
            String jsonString = request.body().toString();
            CustomerAPI customerAPI = new CustomerAPI();
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = customerAPI.updateCustomer(customer);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(customer, result));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while updating customer", false));
        }
    }

    private String getCustomer(Request request) {
        Log.info("get customer");
        try {
            String jsonString = request.body().toString();
            CustomerAPI customerAPI = new CustomerAPI();
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            Customer result = customerAPI.getCustomerById(customer.getCustomerId());
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting customer data", false));
        }
    }

    private String getAllCustomers(Request request) {
        Log.info("get all customers");
        try {
            CustomerAPI customerAPI = new CustomerAPI();
            List<Customer> result = customerAPI.getAllCustomers();
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting all customer data", false));
        }
    }

    private HmsResponse getHmsResponse(Object responseData, boolean result) {
        HmsResponse response = null;
      /*  if (result) {
            response = new HmsResponse(200, "SUCCESS", responseData, null, null, "SUCCESS");
        } else {
            response = new HmsResponse(200, "FAILED", responseData, null, null, "FAILED");
        }*/
        return response;
    }
}
