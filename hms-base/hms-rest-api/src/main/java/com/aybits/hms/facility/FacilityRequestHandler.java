package com.aybits.hms.facility;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.facility.api.FacilityAPI;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.beans.FacilityType;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Map;


public class FacilityRequestHandler implements HmsRequestHandler {

    HMSJsonRequestComponents components = null;
    FacilityAPI hmsapiProvider = new FacilityAPI();
    static Logger Log = Logger.getLogger(FacilityRequestHandler.class);

    @Override
    public ValidationResult validateRequestData(HMSJsonRequestComponents components) throws HMSException {
        return null;
    }

    /**
     *
     * @param request
     * @return
     */
    @Override
    public ValidationResult validateRequestData(Request request) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        return result;
    }


    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String handleRequest(Request request, Response response) {

        Log.info("Facility request handler invoked");

        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());

        ValidationResult result = validateRequestData(request);
        if (result != null) {
            //return result.getMessage();
        }

        String operation = components.getOperation();
        String entity = components.getEntity();
        String data = components.getData();
        String action = operation + "/" + entity;
        String tokenId = components.getTokenId();


        String message = "";


        switch (action) {
            case "/fetch/all":
                message = fetchAllFacilities(tokenId, data);
                break;
            case "/fetch":
                message = fetchFacility(tokenId, data);
                break;
            case "add/hotel":
                message = addFascility(tokenId, data);
                break;
            case "update/hotel":
                message = updateFacility(tokenId,data);
                break;
            case "disable/hotel":
                message = disableFacility(tokenId,data);
                break;
        }
        return message;
    }


    /**
     *
     * @param tokenId
     * @param requestData
     * @return
     */

    /**
     *
     * @param tokenId
     * @param requestData
     * @return
     */
    private String addFascility(String tokenId, String requestData) {
        Log.info("in setup Facility");
        HmsResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hmsapiProvider.process(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = new HmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


    /**
     *
     * @param tokenId
     * @param requestData
     * @return
     */
    private String updateFacility(String tokenId, String requestData) {
        Log.info("in setup Facility");
        HmsResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hmsapiProvider.update(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = new HmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */

    private String fetchFacility(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchFacility");
        HmsResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsapiProvider.fetch(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            Log.info("requestToken:" + tokenId + ",Exception occured in fetchHotel" + e.getErrorMessage());
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchFacility");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */
    private String fetchAllFacilities(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchAllFacilities");
        HmsResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsapiProvider.fetchAll(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchAllFacilities");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */
    private String disableFacility(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::disableFacility");
        HmsResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsapiProvider.disable(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableFacility");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }



}
