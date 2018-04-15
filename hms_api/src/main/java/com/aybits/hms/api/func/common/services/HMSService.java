package com.aybits.hms.api.func.common.services;

import com.aybits.hms.api.arch.exception.HMSException;
import com.aybits.hms.api.func.common.beans.HMSServiceAction;
import com.aybits.hms.api.func.common.beans.HMSServiceModule;

public interface HMSService {

    public Object init(Object object,HMSServiceModule hmsServiceModule,HMSServiceAction hmsServiceAction) throws HMSException;

    public Object process(Object object,HMSServiceModule hmsServiceModule,HMSServiceAction hmsServiceAction) throws HMSException;

}
