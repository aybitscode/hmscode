package com.aybits.hms.func.common.api;


import com.aybits.hms.arch.exception.HMSRuntimeException;
import org.json.JSONObject;

public abstract class HmsAPIImpl implements HmsAPI {
    @Override
    public Object init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
    }
}
