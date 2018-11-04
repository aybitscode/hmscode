package com.aybits.hms.feature;

import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.ValidationResult;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class FeatureRequestHandler implements HMSRequestHandler {


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
    public HMSResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {
        return null;
    }
}
