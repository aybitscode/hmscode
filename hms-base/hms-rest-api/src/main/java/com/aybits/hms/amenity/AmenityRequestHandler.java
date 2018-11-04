package com.aybits.hms.amenity;

import com.aybits.hms.arch.exception.HMSException;
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
    public ValidationResult validateRequestData(JSONObject dataJSON) throws HMSException {
        return null;
    }


    public ValidationResult validateRequestData(Request request) {
        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Hello");
        return null;
    }
}
