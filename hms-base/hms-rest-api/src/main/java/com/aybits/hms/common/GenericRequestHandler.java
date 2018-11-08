package com.aybits.hms.common;

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

public interface GenericRequestHandler {
    Logger Log = Logger.getLogger(GenericRequestHandler.class);


    default public HMSResponse getHmsResponse(String tokenID, String status, String statusMessage, Object responseData) {

        return new HMSResponse(tokenID, status, statusMessage, responseData);
    }

    public String handleRequest(Request request,Response response);

}
