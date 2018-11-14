package com.aybits.hms.facility;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;

import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;


public class FacilityRequestHandler implements HMSRequestHandler {

    static Logger Log = Logger.getLogger(FacilityRequestHandler.class);

    HmsAPI hmsAPI = null;


    /**
     * @param dataJSON
     * @return ValidationResult
     */

    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {
        hmsAPI.validate(dataJSON);
    }


    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    public String handleRequest(Request request, Response response) {

        Log.info("Fascility request handler invoked");

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

        try {
            switch (action) {
                case "/fetch/all":
                    message = fetchAllFacilities(tokenId, data);
                    break;
                case "/fetch":
                    message = fetchFacility(tokenId, data);
                    break;
                case "add/hotel":
                    message = addFacility(tokenId, data);
                    break;
                case "update/hotel":
                    message = updateFacility(tokenId, data);
                    break;
                case "disable/hotel":
                    message = disableFacility(tokenId, data);
                    break;
            }
        }catch(HMSRuntimeException hrex){
            message = populateHMSErrorResponse(hrex, tokenId);
        }finally {
            return message;
        }
    }

    /**
     * @param tokenId
     * @param requestData
     * @return
     */
    private String addFacility(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::addFacility");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hmsAPI.process(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::addFacility");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return
     */
    private String updateFacility(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::updateFacility");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hmsAPI.update(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Entry]::updateFacility");
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
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsAPI.fetch(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            Log.info("requestToken:" + tokenId + ",Exception occured in fetchHotel" + e.getMessage());
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


    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */
    private String disableFacility(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::disableFacility");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsAPI.disable(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableFacility");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }

}



