package com.aybits.hms.func.facility.api;

import com.aybits.hms.arch.exception.HMSException;
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

    public Boolean addFacility(Facility facility) throws HMSException {
        return true;
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
