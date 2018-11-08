package com.aybits.hms.booking;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;

public class BookingRequestProcessor implements HMSRequestProcessor {

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {

    }

    @Override
    public HMSResponse processRequest(HMSRequest hmsRequest) {
        return null;
    }



}
