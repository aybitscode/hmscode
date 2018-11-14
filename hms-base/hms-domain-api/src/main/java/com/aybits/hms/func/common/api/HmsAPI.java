package com.aybits.hms.func.common.api;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.cache.HMSCache;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public interface HmsAPI {

    static Logger Log = Logger.getLogger(HmsAPI.class);

    default void init(){
        HMSCache hmsCache = new HMSCache();
        hmsCache.initHMSCache();
    }
    Object init(JSONObject object) throws HMSRuntimeException;

    String process(JSONObject object) throws HMSRuntimeException;

    void validate(JSONObject object) throws HMSRuntimeException;

    String fetch(JSONObject json) throws HMSRuntimeException;

    String fetchAll(JSONObject json) throws HMSRuntimeException;

    String update(JSONObject json) throws HMSRuntimeException;

    String disable(JSONObject json) throws HMSRuntimeException;

    String delete(JSONObject json) throws HMSRuntimeException;



    default String createHMSAPIResponse(String status,String message,JSONObject data){
        Log.info("hmsAPI.createHMSAPIResponse - Creating response string for exiting hmsAPI");

        HMSAPIResponse hmsapiResponse = new HMSAPIResponse();
        hmsapiResponse.setResponseData(data.toString());
        hmsapiResponse.setMessage(message);
        hmsapiResponse.setStatus(status);

        return HMSJSONParser.convertObjectToJSON(hmsapiResponse);

    }

}
