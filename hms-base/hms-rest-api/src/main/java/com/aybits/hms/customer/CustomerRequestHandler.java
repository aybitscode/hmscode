package com.aybits.hms.customer;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.customer.api.CustomerAPI;
import com.aybits.hms.func.customer.beans.Customer;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;

public class CustomerRequestHandler implements HMSRequestHandler {
    static Logger Log = Logger.getLogger(CustomerRequestHandler.class);

    @Override
    public void validateRequestData(JSONObject jsonObject) throws HMSRuntimeException {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Customer request handler invoked");

        ValidationResult result = validateRequest(request);
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

    @Override
    public String populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
        return null;
    }

    @Override
    public String populateGenericErrorResponse(Exception e, String tokenId) {
        return null;
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

    private HMSResponse getHmsResponse(Object responseData, boolean result) {
        HMSResponse response = null;
      /*  if (result) {
            response = new HMSAPIResponse(200, "SUCCESS", responseData, null, null, "SUCCESS");
        } else {
            response = new HMSAPIResponse(200, "FAILED", responseData, null, null, "FAILED");
        }*/
        return response;
    }
}
