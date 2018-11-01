package com.aybits.hms.amenity;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import spark.Request;
import spark.Response;

public class AmenityRequestHandler implements HmsRequestHandler {



    public ValidationResult validateRequest(Request request) {
        return null;
    }


    public String getActionString(Request request) {
        return null;
    }


    public ValidationResult validateRequestData(Request request) {
        return null;
    }



    public String handleRequest(Request request, Response response) {
        return null;
    }


    public HmsResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {
        return null;
    }
}
