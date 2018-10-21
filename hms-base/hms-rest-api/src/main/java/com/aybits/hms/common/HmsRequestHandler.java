package com.aybits.hms.common;

import com.aybits.hms.arch.util.HMSJSONParser;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

public interface HmsRequestHandler {
    Logger Log = Logger.getLogger(HmsRequestHandler.class);

    default ValidationResult validateRequest(Request request, Response response) {
        //Todo do base request validation result here
        Log.info("doing base structure validation");
        ValidationResult result = null;
        String action = getActionString(request);

        if (action == null) {
            result.setCode(001);
            result.setMessage("{'status':'error','message':'Invalid request URL.'}");
        }

        if(HMSJSONParser.getHmsJsonRequestComponents(request.body()) == null){
            result.setCode(002);
            result.setMessage("{'status':'error','message':'Invalid request body.'}");
        }

        //Todo if the base validation has no errors then do data validations
        if (result == null) {
            Log.info("doing data validations");
            result = validateRequestData(request, response);
        }

        return result;
    }

    default String getActionString(Request request){
        String action = null;
        try {
            String str = request.pathInfo();
            int secondIndex = str.indexOf("/", str.indexOf("/") + 1);
            action = str.substring(secondIndex, str.length());
        }catch (Exception e){
            Log.error(e.getMessage());
        }
        return action;
    }

    ValidationResult validateRequestData(Request request, Response response);

    String handleRequest(Request request, Response response);


    default HmsResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {

        return new HmsResponse(tokenID, status, statusMessage, responseData);
    }
}
