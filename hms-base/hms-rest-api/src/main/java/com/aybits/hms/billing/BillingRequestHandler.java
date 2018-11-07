package com.aybits.hms.billing;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.HMSResponse;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class BillingRequestHandler implements GenericRequestHandler {


    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {
        //return null;
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
