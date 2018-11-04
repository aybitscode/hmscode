package com.aybits.hms.func.service.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.service.beans.Service;
import com.aybits.hms.func.service.dao.ServiceCache;
import com.aybits.hms.func.service.dao.ServiceDAO;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

public class ServiceAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(ServiceAPI.class);
    ServiceCache serviceCache = new ServiceCache();
    ServiceDAO serviceDAO = new ServiceDAO();

    public Boolean addService(Service[] services) throws HMSRuntimeException {
        Boolean isServiceAdded = false;
        if(services != null){
            for(Service service:services){

                if (service.getServiceId() != null && service.getServiceId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
                    try {
                        serviceCache.addService(service);
                        if (service == null) {
                            throw new NullPointerException();
                        }
                        isServiceAdded = true;
                    } catch (Exception e) {
                        log.info("Exception occured while adding service::" + service.getServiceId());
                        throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.SERVICE_ADDITION_FAILED, "Adding service details failed"));
                    }
                }

            }
        }
        return isServiceAdded;
    }

    public Boolean updateService(Service service)throws HMSRuntimeException{
        return true;
    }

    public Service fetchServiceByHotel(String hotelId)throws HMSRuntimeException{
        return null;
    }

    public List<Service> fetchAllFacilities()throws HMSRuntimeException{
        return null;
    }

    @Override
    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
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
