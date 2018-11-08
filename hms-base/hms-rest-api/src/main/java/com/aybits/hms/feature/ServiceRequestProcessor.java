package com.aybits.hms.feature;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.*;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class ServiceRequestProcessor implements HMSRequestProcessor {


    @Override
    public HMSResponse processRequest(HMSRequest hmsRequest) {
        return null;
    }

    @Override
    public void validateRequestData(JSONObject data) throws HMSRuntimeException {

    }
}
