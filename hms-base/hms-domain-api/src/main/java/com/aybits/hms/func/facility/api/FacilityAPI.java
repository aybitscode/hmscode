package com.aybits.hms.func.facility.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.cache.FacilityCache;
import com.aybits.hms.func.facility.dao.FacilityDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class FacilityAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(FacilityAPI.class);
    FacilityCache facilityCache = new FacilityCache();
    FacilityDAO facilityDAO = new FacilityDAO();

    public Boolean addFacility(Facility[] facilities) throws HMSException {
        if (facility.getFacilityId() != null && facility.getFacilityId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
            try {
                facilityCache.addFacility(facility);
                if(facility == null){
                    throw new NullPointerException();
                }
                return true;
            }
            catch (Exception e) {
                log.info("Exception occured while adding facility::"+facility.getFacilityId());
                throw new HMSException(HMSErrorCodes.FACILITY_ADDITION_FAILED, "Adding facility details failed");
            }
        }
        return false;
    }

    public Boolean updateFacility(Facility facility)throws HMSException{
        return true;
    }

    public Facility fetchFacilityByHotel(String hotelId)throws HMSException{
        return null;
    }

    public List<Facility> fetchAllFacilities()throws HMSException{
        return null;
    }

}
