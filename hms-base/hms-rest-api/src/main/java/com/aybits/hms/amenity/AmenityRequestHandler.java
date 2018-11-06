package com.aybits.hms.amenity;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.amenity.api.AmenityAPI;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import org.json.JSONObject;
import spark.Request;
import spark.Response;


public class AmenityRequestHandler implements HmsRequestHandler {

   AmenityAPI hmsapiProvider = new AmenityAPI();

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

        Log.info("Hotel request handler invoked");

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
                message = fetchAllAmenities(tokenId, data);
                break;
            case "/fetch":
                message = fetchAmenity(tokenId, data);
                break;
            case "add/hotel":
                message = addAmenity(tokenId, data);
                break;
            case "update/hotel":
                message = updateAmenity(tokenId,data);
                break;
            case "disable/hotel":
                message = disableAmenity(tokenId,data);
                break;
        }
        return message;
    }





    public HmsResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {
        return null;
    }

    private String addAmenity(String tokenId, String requestData) {
        Log.info("in setupAmenity");
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


    private String updateAmenity(String tokenId, String requestData) {
        Log.info("in setupAmenity");
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

    private String fetchAmenity(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchAmenity");
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
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchHotel");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }

    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */
    private String fetchAllAmenities(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchAllAmenities");
        HmsResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsapiProvider.fetchAll(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchAllAmenities");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }
    private String disableAmenity(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::disableAmenity");
        HmsResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsapiProvider.disable(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableAmenity");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


}
