package com.aybits.hms.billing;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.HMSRequestHandler;
import org.json.JSONObject;

import spark.Request;
import spark.Response;

public class BillingRequestHandler implements HMSRequestHandler {


    @Override
    public String getActionString(Request request) {
        System.out.println("Hello");
        return null;
    }


    public void validateRequestData(JSONObject jsonObject) {
        System.out.println("Hello");
        // return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Hello");
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
