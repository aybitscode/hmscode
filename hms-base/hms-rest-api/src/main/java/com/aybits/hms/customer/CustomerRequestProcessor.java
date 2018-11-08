package com.aybits.hms.customer;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HMSRequest;
import com.aybits.hms.common.HMSRequestProcessor;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.func.customer.api.CustomerAPI;
import com.aybits.hms.func.customer.beans.Customer;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerRequestProcessor implements HMSRequestProcessor {
    static Logger Log = Logger.getLogger(CustomerRequestProcessor.class);

    @Autowired
    CustomerAPI customerAPI;

    @Override
    public void validateRequestData(JSONObject data) throws HMSRuntimeException {
       customerAPI.validate(data);
    }

    @Override
    public HMSResponse processRequest(HMSRequest request) {
        Log.info("Customer request handler invoked");

        String action = request.getData();
        HMSResponse response = null;
        switch (action) {
            case "add":
                response = addCustomer(request);
                break;
            case "update":
                response = updateCustomer(request);
                break;
            case "get":
                response = getCustomer(request);
                break;
            case "getAll":
                response = getAllCustomers(request);
                break;
        }
        return response;
    }






    private HMSResponse addCustomer(HMSRequest request) {
        Log.info("adding new customer");
        String jsonString = request.getData();
        String tokenId = request.getTokenId();
        HMSResponse hmsResponse = null;
        try {
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = customerAPI.addCustomer(customer);
            hmsResponse =  populateHMSResponse("", "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableHotel");
            return hmsResponse;
        }
    }

    private HMSResponse updateCustomer(HMSRequest request) {
        Log.info("updating customer");
        String jsonString = request.getData();
        String tokenId = request.getTokenId();
        HMSResponse hmsResponse = null;
        try {
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = customerAPI.updateCustomer(customer);
            hmsResponse =  populateHMSResponse("", "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableHotel");
            return hmsResponse;
        }
    }

    private HMSResponse getCustomer(HMSRequest request) {
        Log.info("get customer");
        String jsonString = request.getData();
        String tokenId = request.getTokenId();
        HMSResponse hmsResponse = null;
        try {
            Customer customer = (Customer) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            Customer result = customerAPI.getCustomerById(customer.getCustomerId());
            hmsResponse =  populateHMSResponse("", "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableHotel");
            return hmsResponse;
        }
    }

    private HMSResponse getAllCustomers(HMSRequest request) {
        Log.info("get all customers");
        Log.info("get customer");
        String jsonString = request.getData();
        String tokenId = request.getTokenId();
        HMSResponse hmsResponse = null;
        try {
            CustomerAPI customerAPI = new CustomerAPI();
            List<Customer> result = customerAPI.getAllCustomers();
            hmsResponse =  populateHMSResponse("", "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableHotel");
            return hmsResponse;
        }
    }
}
