package com.aybits.hms.feature;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.ValidationResult;
import spark.Request;
import spark.Response;


public class FeatureRequestHandler implements HMSRequestHandler {


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
    public HMSResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {
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
