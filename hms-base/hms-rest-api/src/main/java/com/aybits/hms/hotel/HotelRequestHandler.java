package com.aybits.hms.hotel;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.hotel.api.HotelAPI;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class HotelRequestHandler implements HMSRequestHandler {
    static Logger Log = Logger.getLogger(HotelRequestHandler.class);

    HMSAPIProvider hmsapiProvider = new HotelAPI();



    /**
     *
     * @param dataJSON
     * @return ValidationResult
     */
    @Override
    public ValidationResult validateRequestData(JSONObject dataJSON) throws HMSException {
        return (ValidationResult)hmsapiProvider.validate(dataJSON);
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

        ValidationResult validationResult = validateRequest(request);
        if(validationResult != null){
            return validationResult.getMessage();
        }
        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());
        String operation = components.getOperation();
        String entity = components.getEntity();
        String data = components.getData();
        String action = operation + "/" + entity;
        String tokenId = components.getTokenId();

        JSONObject dataJSON = null;

        ValidationResult result = null;
        try {
            dataJSON = new JSONObject(data);
            result = validateRequestData(dataJSON);
        }catch(HMSException he){

        }catch(JSONException je){

        }
        if (result != null) {
            return result.getMessage();
        }



        String message = "";


        switch (action) {
            case "/fetch/all":
                message = fetchAllHotels(tokenId, data);
                break;
            case "/fetch":
                message = fetchHotel(tokenId, data);
                break;
            case "add/hotel":
                message = addHotel(tokenId, data);
                break;
            case "update/hotel":
                message = updateHotel(tokenId,data);
                break;
            case "disable/hotel":
                message = disableHotel(tokenId,data);
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
    private String addHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::addHotel");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hmsapiProvider.process(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::addHotel");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


    /**
     *
     * @param tokenId
     * @param requestData
     * @return
     */
    private String updateHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hmsapiProvider.update(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */

    private String fetchHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchHotel");
        HMSResponse hmsResponse = null;
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
    private String fetchAllHotels(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchAllHotels");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsapiProvider.fetchAll(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
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
    private String disableHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::disableHotel");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hmsapiProvider.disable(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSException e) {
            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableHotel");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }



}
