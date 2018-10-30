package com.aybits.hms.func.common.api;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.cache.HMSCache;
import org.json.JSONObject;

public interface HMSAPIProvider {

    default void init(){
        HMSCache hmsCache = new HMSCache();
        hmsCache.initHMSCache();
    }
    Object init(Object object) throws HMSException;

    String process(JSONObject object) throws HMSException;

    Object validate(Object object) throws HMSException;

    String fetch(JSONObject json) throws HMSException;

    String fetchAll(JSONObject json) throws HMSException;

    String update(JSONObject json) throws HMSException;

    String disable(JSONObject json) throws HMSException;

    String delete(JSONObject json) throws HMSException;

}
