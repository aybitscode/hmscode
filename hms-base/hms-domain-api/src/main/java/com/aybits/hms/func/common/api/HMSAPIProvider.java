package com.aybits.hms.func.common.api;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.cache.HMSCache;

public interface HMSAPIProvider {

    public default void init(){
        HMSCache hmsCache = new HMSCache();
        hmsCache.initHMSCache();
    }
    public Object init(Object object) throws HMSException;

    public Object process(Object object) throws HMSException;

    public Object validate(Object object) throws HMSException;

}
