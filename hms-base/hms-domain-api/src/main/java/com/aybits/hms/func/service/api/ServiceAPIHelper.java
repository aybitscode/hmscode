package com.aybits.hms.func.service.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.service.beans.Service;
import com.aybits.hms.func.service.dao.ServiceCache;
import com.aybits.hms.func.service.dao.ServiceDAO;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceAPIHelper implements HmsAPI {

    static Logger log = Logger.getLogger(ServiceAPIHelper.class);
    ServiceCache serviceCache = new ServiceCache();
    ServiceDAO serviceDAO = new ServiceDAO();

    public Map<String,String> addServices(List<Service> services) throws HMSRuntimeException {
        Boolean areServicesAdded = false;
        Map<String,String> servicesMap = new HashMap<String,String>();
        if(services != null){
            for(Service service:services){

                if (service.getServiceId() != null && service.getServiceId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
                    try {

                        areServicesAdded &= addService(service);
                        if(areServicesAdded){
                            servicesMap.put(service.getServiceId(),service.getServiceName());
                        }
                    } catch (Exception e) {
                        log.info("Exception occured while adding service::" + service.getServiceId());
                        throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.SERVICE_ADDITION_FAILED, "Adding service details failed"));
                    }
                }

            }
        }
        return servicesMap;
    }


    private Boolean addService(Service service) throws HMSRuntimeException {
        String hotelId = service.getHotelId();
        Boolean isServiceAdded = false;
        try {
            if (service.getServiceId() != null && service.getServiceId().equals(HMSAPIConstants.TO_BE_GENERATED )) {

                String serviceId =  serviceCache.addService(service);
                if (serviceId == null) {
                    throw new NullPointerException("Service Addition failed for Hotel["+hotelId+"]");
                }
                isServiceAdded = true;
            }
        } catch (HMSRuntimeException e) {
            log.info("Exception occurred while adding facility::" + service.getServiceId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.SERVICE_ADDITION_FAILED, "Adding service details failed for hotel["+hotelId+"]"));
        }finally{
            return isServiceAdded;
        }
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
    public Object init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {

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
