package com.aybits.hms.feature;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.ValidationResult;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class AmenityRequestHandler implements GenericRequestHandler {

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {

    }

    @Override
    public String handleRequest(Request request, Response response) {
        return null;
    }

    @Override
    public HMSResponse populateHMSResponse(String tokenId, String responseString) {
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
