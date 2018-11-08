package com.aybits.hms.feature;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.*;
import com.aybits.hms.func.common.api.HMSAPI;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;

public class AmenityRequestProcessor implements HMSRequestProcessor {

    @Autowired
    HMSAPI hmsAPI;
    @Override
    public HMSResponse processRequest(HMSRequest hmsRequest) {
        return null;
    }


    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {
        hmsAPI.validate(dataJSON);
    }
}

