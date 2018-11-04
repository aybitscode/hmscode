package com.aybits.hms.invoice;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.HMSRequestHandler;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class InvoiceRequestHandler implements HMSRequestHandler {


    @Override
    public String getActionString(Request request) {
        System.out.println("Hello");
        return null;
    }

    @Override
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
    public String populateHMSErrorResponse(HMSRuntimeException he) {
        return null;
    }
}
