package com.aybits.hms.feature;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.features.api.FeatureAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;


public class FeatureRequestHandler implements HMSRequestHandler {

    @Autowired
    FeatureAPI featureAPI;

    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {
        featureAPI.validate(dataJSON);
    }

    @Override
    public String handleRequest(Request request, Response response) {


        Log.info("Hotel request handler invoked");

        ValidationResult validationResult = validateRequest(request);
        if (validationResult != null) {
            return validationResult.getMessage();
        }
        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());
        String operation = components.getOperation();
        String entity = components.getEntity();
        String data = components.getData();
        String action = operation + "/" + entity;
        String tokenId = components.getTokenId();
        String errorResponse = null;

        JSONObject dataJSON = null;

        ValidationResult result = null;
        try {
            dataJSON = new JSONObject(data);
            validateRequestData(dataJSON);
        } catch (HMSRuntimeException hrex) {
            errorResponse = populateHMSErrorResponse(hrex, tokenId);
        } catch (JSONException jex) {
            errorResponse = populateGenericErrorResponse(jex, tokenId);
        }
        if (errorResponse != null) {
            return errorResponse;
        }


        String message = "";

        try {
            switch (action) {
                case "/fetch":
                    message = fetchFeatures(tokenId, data);
                    break;
                case "add/features":
                    message = addFeatures(tokenId, data);
                    break;
                case "update/features":
                    message = updateFeatures(tokenId, data);
                    break;
                case "disable/features":
                    message = disableFeatures(tokenId, data);
                    break;
                case "enable/features":
                    message = enableFeatures(tokenId,data);
            }
        }catch(HMSRuntimeException hrex){
            message = populateHMSErrorResponse(hrex, tokenId);
        }finally {
            return message;
        }
    }

    private String fetchFeatures(String tokenId, String data) {
        return null;
    }

    private String addFeatures(String tokenId, String data) {
        Log.info("requestToken:" + tokenId + ",[Entry]::addHotel");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(data);
            String responseStr = featureAPI.process(inputJSON);
            hmsResponse = populateHmsResponse(tokenId, responseStr);
        } catch (HMSRuntimeException e) {
            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::addFeature");
            return HMSJSONParser.convertObjectToJSON(hmsResponse);
        }
    }

    private String updateFeatures(String tokenId, String data) {
        return null;
    }

    private String disableFeatures(String tokenId, String data) {
        return null;
    }

    private String enableFeatures(String tokenId, String data) {
        return null;
    }

    @Override
    public HMSResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {
        return null;
    }

    @Override
    public HMSResponse populateHmsResponse(String tokenId, String responseString) {
        return null;
    }

    @Override
    public String populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
        return null;
    }

    @Override
    public String populateGenericErrorResponse(Exception e, String tokenId) {
        return null;
    }


}
