package com.aybits.hms.common;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.arch.util.HmsConfig;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;


public interface   HMSRequestHandler

{
    Logger Log = Logger.getLogger(HMSRequestHandler.class);


    default public ValidationResult validateRequest(Request request) {
        ValidationResult requestValidationResult = null;
        HMSJsonRequestComponents components = null;
        //Todo do base request validation result here
        Log.info("doing base structure validation");
        String action = getActionString(request);

        if (action == null) {
            requestValidationResult.setCode(001);
            requestValidationResult.setMessage("{'status':'"+HMSAPIServiceConstants.HMS_RESPONSE_FAILURE+"','message':'Invalid request URL.'}");
        }


        components = HMSJSONParser.getHmsJsonRequestComponents(request.body());

        if (components == null) {
            requestValidationResult.setCode(002);
            requestValidationResult.setMessage("{'status':'"+HMSAPIServiceConstants.HMS_RESPONSE_FAILURE+"','message':'Invalid request body.'}");
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


    void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException;

    public String handleRequest(Request request, Response response);


    default public HMSResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {

        return new HMSResponse(tokenID, status, statusMessage, responseData);
    }

    default public HMSResponse populateHmsResponse(String tokenId, String responseString) {
        JSONObject responseJSON = null;
        String status = null;
        String message = null;
        String responseData =
                null;
        HMSResponse hmsResponse = null;
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
            return new HMSResponse(tokenId, status, message, responseData);
        }

    }


    default public String populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
        Log.error(he.getHmsErrorInfo());
        HMSErrorResponse hmsErrorResponse = new HMSErrorResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, he.getHmsErrorInfo().getErrorMessage(), he.getHmsErrorInfo().getErrorCode());
        return HMSJSONParser.convertObjectToJSON(hmsErrorResponse);
    }


    default public String populateGenericErrorResponse(Exception e, String tokenId) {
        Log.error(e.getCause());
        HMSErrorResponse hmsErrorResponse = new HMSErrorResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_SYSTEM_ERROR);
        return HMSJSONParser.convertObjectToJSON(hmsErrorResponse);
    }
}
