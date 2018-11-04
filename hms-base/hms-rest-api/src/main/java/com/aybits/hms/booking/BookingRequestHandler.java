package com.aybits.hms.booking;

import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.ValidationResult;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class BookingRequestHandler implements HMSRequestHandler {

    @Override
    public ValidationResult validateRequestData(JSONObject dataJSON) {
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
    public String getActionString(Request request) {
        return null;
    }


    @Override
    public HMSResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {
        return null;
    }
}
