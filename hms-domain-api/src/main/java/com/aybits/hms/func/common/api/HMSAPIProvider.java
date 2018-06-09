package com.aybits.hms.func.common.api;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.beans.HMSServiceAction;
import com.aybits.hms.func.common.beans.HMSServiceModule;

public interface HMSAPIProvider {

    public Object init(Object object,HMSServiceModule hmsServiceModule,HMSServiceAction hmsServiceAction) throws HMSException;

    public Object process(Object object,HMSServiceModule hmsServiceModule,HMSServiceAction hmsServiceAction) throws HMSException;

}
