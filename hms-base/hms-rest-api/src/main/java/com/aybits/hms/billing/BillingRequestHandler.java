package com.aybits.hms.billing;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class BillingRequestHandler implements HmsRequestHandler {


    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public ValidationResult validateRequestData(JSONObject dataJSON) {
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
