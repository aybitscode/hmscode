package com.aybits.hms.func.common.api;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.common.cache.HMSCache;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public interface HMSAPI {

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

}
