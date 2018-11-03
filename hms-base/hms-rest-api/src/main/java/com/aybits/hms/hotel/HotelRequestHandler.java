package com.aybits.hms.hotel;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.beans.AmenityType;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelAttributes;
import com.aybits.hms.func.service.beans.Service;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Map;

public class HotelRequestHandler implements HmsRequestHandler {
    static Logger Log = Logger.getLogger(HotelRequestHandler.class);

    HMSAPIProvider hmsapiProvider = new HotelAPI();



    /**
     *
     * @param dataJSON
     * @return
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
        Log.info("in setupHotel");
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
    private String updateHotel(String tokenId, String requestData) {
        Log.info("in setupHotel");
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

    private String fetchHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchHotel");
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
    private String fetchAllHotels(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchAllHotels");
        HmsResponse hmsResponse = null;
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
        HmsResponse hmsResponse = null;
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
