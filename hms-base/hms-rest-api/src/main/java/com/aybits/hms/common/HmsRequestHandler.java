package com.aybits.hms.common;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.arch.util.HmsConfig;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public interface HmsRequestHandler {
    Logger Log = Logger.getLogger(HmsRequestHandler.class);


    default public ValidationResult validateRequest(Request request) {
        ValidationResult requestValidationResult = null;
        HMSJsonRequestComponents components = null;
        //Todo do base request validation result here
        Log.info("doing base structure validation");
        String action = getActionString(request);

        if (action == null) {
            requestValidationResult.setCode(001);
            requestValidationResult.setMessage("{'status':'error','message':'Invalid request URL.'}");
        }


        components = HMSJSONParser.getHmsJsonRequestComponents(request.body());

        if (components == null) {
            requestValidationResult.setCode(002);
            requestValidationResult.setMessage("{'status':'error','message':'Invalid request body.'}");
        }

        return requestValidationResult;
    }

    default public String getActionString(Request request) {
        String action = null;
        try {
            String str = request.pathInfo();
            String appName = HmsConfig.getRestProperty("app.name");
            String appVersion = HmsConfig.getRestProperty("app.version");
            String appPrefix = "api";
            StringBuffer sb = new StringBuffer();
            sb.append("/");
            sb.append(appName);
            sb.append("/");
            sb.append(appVersion);
            sb.append("/");
            sb.append(appPrefix);
            sb.append("/");
            String url = sb.toString();

            action = str.substring(url.length());

        } catch (Exception e) {
            Log.error(e.getMessage());
        }
        return action;
    }

    public ValidationResult validateRequestData(HMSJsonRequestComponents components) throws HMSException;

    ValidationResult validateRequestData(Request request) throws HMSException;

    public String handleRequest(Request request, Response response);


    default public HmsResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {

        return new HmsResponse(tokenID, status, statusMessage, responseData);
    }

    default public HmsResponse populateHmsResponse(String tokenId, String responseString) {
        JSONObject responseJSON = null;
        String status = null;
        String message = null;
        String responseData =
                null;
        HmsResponse hmsResponse = null;
        try {
            responseJSON = new JSONObject(responseString);
            status = responseJSON.getString("status");
            message = responseJSON.getString("message");
            responseData = responseJSON.getString("data");

        } catch (JSONException e) {
            status = HMSAPIServiceConstants.HMS_RESPONSE_FAILURE;
            message = e.getMessage();
            responseData = HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA;
        } finally {
            return new HmsResponse(tokenId, status, message, responseData);
        }

    }
}
