package com.aybits.hms.invoice;

import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.ValidationResult;
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
    public ValidationResult validateRequestData(JSONObject jsonObject) {
        System.out.println("Hello");
        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Hello");
        return null;
    }
}
