package com.aybits.hms.booking;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.ValidationResult;
import spark.Request;
import spark.Response;

public class BookingRequestHandler implements HmsRequestHandler {

    @Override
    public ValidationResult validateRequestData(Request request, Response response) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        return result;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Booking request handler invoked");

        ValidationResult result = validateRequest(request, response);
        if (result != null) {
            return result.getMessage();
        }

        return null;
    }
}
