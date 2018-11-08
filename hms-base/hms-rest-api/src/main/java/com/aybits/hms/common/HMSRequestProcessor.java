package com.aybits.hms.common;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public interface HMSRequestProcessor {

    static Logger Log = Logger.getLogger(HMSRequestProcessor.class);

    public HMSResponse processRequest(HMSRequest hmsRequest);

    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException;





    default public HMSResponse populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
        Log.error(he.getHmsErrorInfo());
        return new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, he.getHmsErrorInfo().getErrorMessage(), he.getHmsErrorInfo().getErrorCode());
    }


    default public HMSResponse populateGenericErrorResponse(Exception e, String tokenId) {
        Log.error(e.getCause());
        return new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_SYSTEM_ERROR);
    }


    default public HMSResponse populateHMSResponse(String tokenId, String responseString) {
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


}


