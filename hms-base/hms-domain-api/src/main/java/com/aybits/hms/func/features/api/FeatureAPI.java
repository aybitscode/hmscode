package com.aybits.hms.func.features.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import com.aybits.hms.func.features.beans.Feature;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class FeatureAPI implements HmsAPI {

    static Logger Log = Logger.getLogger(FeatureAPI.class);

    FeatureAPIHelper featureAPIHelper = new FeatureAPIHelper();
    FeatureAPIValidator featureAPIValidator  = new FeatureAPIValidator();

    @Override
    public void init() {

    }

    @Override
    public Object init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String process(JSONObject data) throws HMSRuntimeException {

        Log.info("[Entry]::featureAPI.process");
        String status = null;
        String message = null;
        String response = null;
        JSONObject responseJson = null;
        Feature feature = null;
        try {
            feature = (Feature) HMSJSONParser.convertJSONToObject(data.getJSONObject(HMSJSONConstants.FEATURE).toString(), Feature.class);
            response = featureAPIHelper.addFeature(feature);
            responseJson = new JSONObject(response);
            status = HMSAPIServiceConstants.HMS_RESPONSE_SUCCESS;
            message = "Features for Hotel["+feature.getHotelId()+"] created successfully";
            Log.info(message);
        }catch (HMSRuntimeException hrex) {
            throw hrex;
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FEATURE_DETAILS,"Features setup failed due to :"+e.getMessage()));
        }finally{
            Log.info("[Exit]::featureAPI.process");
            return createHMSAPIResponse(status,message,responseJson);
        }
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {
        Feature feature = null;
        try {
            feature = (Feature) HMSJSONParser.convertJSONToObject(object.getJSONObject(HMSJSONConstants.FEATURE).toString(), Feature.class);
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FEATURE_DETAILS,"Invalid Feature details provided"));
        }
        featureAPIValidator.validateFeature(feature);
    }

    @Override
    public String fetch(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSRuntimeException {
        return null;
    }
}
