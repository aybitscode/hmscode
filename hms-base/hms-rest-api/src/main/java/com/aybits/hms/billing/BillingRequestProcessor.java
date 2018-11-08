package com.aybits.hms.billing;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.HMSRequest;
import com.aybits.hms.common.HMSRequestProcessor;
import com.aybits.hms.common.HMSResponse;
import org.json.JSONObject;

public class BillingRequestProcessor implements HMSRequestProcessor {


    @Override
    public HMSResponse processRequest(HMSRequest hmsRequest) {
        return null;
    }

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {
        //return null;
    }
}
