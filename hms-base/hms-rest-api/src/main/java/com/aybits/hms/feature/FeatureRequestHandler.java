package com.aybits.hms.feature;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import spark.Request;
import spark.Response;

public class FeatureRequestHandler implements HmsRequestHandler {
    @Override
    public ValidationResult validateRequest(Request request) {
        return null;
    }

    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public ValidationResult validateRequestData(HMSJsonRequestComponents components) throws HMSException {
        return null;
    }

    @Override
    public ValidationResult validateRequestData(Request request) {
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
