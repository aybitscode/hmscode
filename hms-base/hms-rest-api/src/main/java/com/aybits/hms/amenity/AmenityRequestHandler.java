package com.aybits.hms.amenity;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.ValidationResult;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class AmenityRequestHandler implements HMSRequestHandler {


    @Override
    public String getActionString(Request request) {
        System.out.println("Hello");
        return null;
    }

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {
      //  return null;
    }


    public ValidationResult validateRequestData(Request request) {
        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Hello");
        return null;
    }

    @Override
    public String populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
        return null;
    }

    @Override
    public String populateGenericErrorResponse(Exception e, String tokenId) {
        return null;
    }
}
