package com.aybits.hms.feature;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class FeatureRequestHandler implements HmsRequestHandler {


    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public ValidationResult validateRequestData(JSONObject dataJson) {
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
