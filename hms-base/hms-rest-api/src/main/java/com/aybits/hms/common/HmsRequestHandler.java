package com.aybits.hms.common;

import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

public interface HmsRequestHandler {
    Logger Log = Logger.getLogger(HmsRequestHandler.class);

    default ValidationResult validateRequest(Request request, Response response) {
        Log.info("doing base structure validation");
        //Todo do base request validation result here
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");

        //Todo if the base validation has no errors then do data validations
        if (result == null) {
            Log.info("doing data validations");
            result = validateRequestData(request, response);
        }
        return result;
    }

    ValidationResult validateRequestData(Request request, Response response);

    String handleRequest(Request request, Response response);
}
