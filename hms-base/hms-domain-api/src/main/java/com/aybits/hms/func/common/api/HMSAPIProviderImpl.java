package com.aybits.hms.func.common.api;


import com.aybits.hms.arch.exception.HMSException;
import org.json.JSONObject;

public abstract class HMSAPIProviderImpl implements HMSAPIProvider {
    @Override
    public Object init(JSONObject object) throws HMSException {
        return null;
    }

    public String process(JSONObject object) throws HMSException {
        return null;
    }

    @Override
    public Object validate(JSONObject object) throws HMSException {
        return null;
    }
}
