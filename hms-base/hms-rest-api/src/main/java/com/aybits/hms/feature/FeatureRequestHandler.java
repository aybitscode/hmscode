package com.aybits.hms.feature;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.HMSRequestHandler;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class FeatureRequestHandler implements HMSRequestHandler {


    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public void validateRequestData(JSONObject dataJson) throws HMSRuntimeException {
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
    public String populateHMSErrorResponse(HMSRuntimeException he) {
        return null;
    }
}
