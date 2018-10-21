package com.aybits.hms.invoice;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.ValidationResult;
import spark.Request;
import spark.Response;

public class InvoiceRequestHandler implements HmsRequestHandler {
    @Override
    public ValidationResult validateRequest(Request request, Response response) {
        return null;
    }

    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public ValidationResult validateRequestData(Request request, Response response) {
        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        return null;
    }
}
