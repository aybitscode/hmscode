package com.aybits.hms.booking;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import spark.Request;
import spark.Response;

public class BookingRequestHandler extends HmsRequestHandler {

    @Override
    public ValidationResult validateRequestData(Request request) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        return result;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Booking request handler invoked");

        ValidationResult result = validateRequest(request);
        if (result != null) {
            return result.getMessage();
        }

        return null;
    }

    @Override
    public ValidationResult validateRequest(Request request) {
        return null;
    }

    @Override
    public String getActionString(Request request) {
        return null;
    }


    @Override
    public HmsResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {
        return null;
    }
}
