package com.aybits.hms.invoice;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.HMSRequest;
import com.aybits.hms.common.HMSRequestProcessor;
import com.aybits.hms.common.HMSResponse;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class InvoiceRequestProcessor implements HMSRequestProcessor {


    @Override
    public HMSResponse processRequest(HMSRequest hmsRequest) {
        return null;
    }

    @Override
    public void validateRequestData(JSONObject jsonObject) throws HMSRuntimeException {
        System.out.println("Hello");
        // return null;
    }


}

