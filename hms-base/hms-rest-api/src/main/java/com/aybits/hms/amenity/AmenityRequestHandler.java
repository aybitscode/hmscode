package com.aybits.hms.amenity;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import spark.Request;
import spark.Response;

public class AmenityRequestHandler implements HmsRequestHandler {

    @Override
    public ValidationResult validateRequest(Request request, Response response) {
        return null;
    }

    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public ValidationResult validateRequestData(Request request, Response response) {
        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        return null;
    }

    @Override
    public HmsResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {
        return null;
    }
}
