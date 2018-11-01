package com.aybits.hms.func.common.api;


import com.aybits.hms.arch.exception.HMSException;

public abstract class HMSAPIProviderImpl implements HMSAPIProvider {
    @Override
    public Object init(Object object) throws HMSException {
        return null;
    }

    public Object process(Object object) throws HMSException {
        return null;
    }

    @Override
    public Object validate(Object object) throws HMSException {
        return null;
    }
}
