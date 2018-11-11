package com.aybits.hms.Employee;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.employee.api.EmployeeAPI;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.hotel.api.HotelAPI;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;

public class EmployeeRequestHandler implements HMSRequestHandler {
    static Logger Log = Logger.getLogger(EmployeeRequestHandler.class);
    
    HmsAPI hmsAPI = new EmployeeAPI();
    
    @Override
    public void validateRequestData(JSONObject dataJSON) {
      hmsAPI.validate(dataJSON);
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Employee request handler invoked");
        ValidationResult validationResult = validateRequest(request);
        if (validationResult != null) {
            return validationResult.getMessage();
        }
        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());
        String operation = components.getOperation();
        String entity = components.getEntity();
        String data = components.getData();
        String action = operation + "/" + entity;
        String tokenId = components.getTokenId();
        String errorResponse = null;

        JSONObject dataJSON = null;

        ValidationResult result = null;
        try {
            dataJSON = new JSONObject(data);
            validateRequestData(dataJSON);
        } catch (HMSRuntimeException hrex) {
            errorResponse = populateHMSErrorResponse(hrex, tokenId);
        } catch (JSONException jex) {
            errorResponse = populateGenericErrorResponse(jex, tokenId);
        }
        if (errorResponse != null) {
            return errorResponse;
        }


        String message = "";

        switch (action) {
            case "fetch-all":
                message = getAllEmployees(tokenId,data);
                break;
            case "fetch-by-phone":
                message = getEmployeeByPhone(tokenId,data);
                break;
            case "add/employee":
                message = addEmployee(tokenId,data);
                break;
            case "update/employee":
                message = updateEmployee(tokenId,data);
                break;
            /*case "getEmployeeNameByMobile":
                message = getEmployeeNameByMobile(request);
                break;*/
            case "fetch-id":
                message = getEmployeeId(tokenId,data);
                break;
            case "fetch-by-id":
                message = getEmployeeById(tokenId,data);
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

    private String addEmployee(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::addEmployee");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hmsAPI.process(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::addEmployee");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }

    private String updateEmployee(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::updateEmployee");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hmsAPI.update(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }

    private String getEmployeeById(String tokenId, String requestData) {
        Log.info("in getEmployeeById");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsAPI.fetch(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            Log.info("requestToken:" + tokenId + ",Exception occured in getEmployeeById" + e.getMessage());
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchHotel");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }

    private String getEmployeeByPhone(String tokenId, String requestData) {
    	Log.info("requestToken:" + tokenId + ",[Entry]::fetchHotel");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsAPI.fetch(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            Log.info("requestToken:" + tokenId + ",Exception occured in getEmployeeByPhone" + e.getMessage());
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchHotel");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }

    private String getEmployeeId(String tokenId, String requestData) {
    	Log.info("requestToken:" + tokenId + ",[Entry]::getEmployeeId");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsAPI.fetch(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            Log.info("requestToken:" + tokenId + ",Exception occured in getEmployeeId" + e.getMessage());
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::getEmployeeId");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


    private String getAllEmployees(String tokenId, String requestData) {
    	Log.info("requestToken:" + tokenId + ",[Entry]::fetchAllHotels");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsAPI.fetchAll(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchAllHotels");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }

    private HMSResponse getHmsResponse(Object responseData, boolean result) {
        HMSResponse response = null;
       /* if (result) {
            response = new HMSAPIResponse(200, "SUCCESS", responseData, null, null, "SUCCESS");
        } else {
            response = new HMSAPIResponse(200, "FAILED", responseData, null, null, "FAILED");
        }*/
        return response;
    }
}
