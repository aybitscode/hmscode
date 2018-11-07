package com.aybits.hms.feature;

import com.aybits.hms.amenity.AmenityRequestHandler;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class FeatureRequestHandler implements GenericRequestHandler {

    static Logger Log = Logger.getLogger(FeatureRequestHandler.class);
    GenericRequestHandler hmsRequestHandler = null;
    private String[] entities = {HMSJSONConstants.FACILITIES,HMSJSONConstants.AMENITIES,HMSJSONConstants.SERVICES};

    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public void validateRequestData(JSONObject dataJson) throws HMSRuntimeException {
        //return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {


        Log.info("Feature request handler invoked");

        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());
        String data = components.getData();
        String tokenId = components.getTokenId();
        String errorResponse = null;

        JSONObject dataJSON = null;

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

        Map<String,String> messages = new HashMap<>();
        String message = null;

        for(String entity:entities) {
            try {
                switch (entity) {
                    case HMSJSONConstants.FACILITIES:
                        message = setupFacilities(request, response);
                        break;
                    case HMSJSONConstants.AMENITIES:
                        message = setupAmenities(request, response);
                        break;
                    case HMSJSONConstants.SERVICES:
                        message = setupServices(request, response);
                        break;
                }
            } catch (HMSRuntimeException hrex) {
                message = populateHMSErrorResponse(hrex, tokenId);
            } finally {
                messages.put(entity,message);
            }
        }

        message = populateHMSResponse(tokenId,messages);
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

    private String setupFacilities(Request request,Response response){
        hmsRequestHandler = new FacilityRequestHandler();
        return hmsRequestHandler.handleRequest(request,response);
    }

    private String setupAmenities(Request request,Response response){
        hmsRequestHandler = new AmenityRequestHandler();
        return hmsRequestHandler.handleRequest(request,response);
    }

    private String setupServices(Request request,Response response){
        hmsRequestHandler = new ServiceRequestHandler();
        return hmsRequestHandler.handleRequest(request,response);
    }



    public String populateHMSResponse(String tokenId,Map messages){

            return null;
    }

    public String populateHMSErrorResponse(HMSRuntimeException hrex){
        return null;
    }

}
