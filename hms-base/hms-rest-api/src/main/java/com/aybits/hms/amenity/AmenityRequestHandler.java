package com.aybits.hms.amenity;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class AmenityRequestHandler implements HmsRequestHandler {


    @Override
    public String getActionString(Request request) {
        System.out.println("Hello");
        return null;
    }

    @Override
    public ValidationResult validateRequestData(Request request) {
        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Hello");
        return null;
    }
}
