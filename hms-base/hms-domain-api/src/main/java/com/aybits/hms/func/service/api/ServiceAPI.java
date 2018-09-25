package com.aybits.hms.func.service.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.service.beans.Service;
import com.aybits.hms.func.service.dao.ServiceDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class ServiceAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(ServiceAPI.class);
    ServiceDAO.ServiceCache serviceCache = new ServiceDAO.ServiceCache();
    ServiceDAO serviceDAO = new ServiceDAO();

    public Boolean addService(Service[] services) throws HMSException {
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
                        throw new HMSException(HMSErrorCodes.SERVICE_ADDITION_FAILED, "Adding service details failed");
                    }
                }

            }
        }
        return isServiceAdded;
    }

    public Boolean updateService(Service service)throws HMSException{
        return true;
    }

    public Service fetchServiceByHotel(String hotelId)throws HMSException{
        return null;
    }

    public List<Service> fetchAllFacilities()throws HMSException{
        return null;
    }

}
