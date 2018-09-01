package com.aybits.hms.func.amenity.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.amenity.cache.AmenityCache;
import com.aybits.hms.func.amenity.dao.AmenityDAO;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.facility.beans.Facility;
import org.apache.log4j.Logger;

import java.util.List;

public class AmenityAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(AmenityAPI.class);
    AmenityCache facilityCache = new AmenityCache();
    AmenityDAO facilityDAO = new AmenityDAO();

  /*  public Boolean addFacility(Facility[] facilities) throws HMSException {
        Boolean isFacilityAdded = false;
        if(facilities != null){
            for(Facility facility:facilities){

                if (facility.getFacilityId() != null && facility.getFacilityId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
                    try {
                        facilityCache.addFacility(facility);
                        if (facility == null) {
                            throw new NullPointerException();
                        }
                        isFacilityAdded = true;
                    } catch (Exception e) {
                        log.info("Exception occured while adding facility::" + facility.getFacilityId());
                        throw new HMSException(HMSErrorCodes.FACILITY_ADDITION_FAILED, "Adding facility details failed");
                    }
                }

            }
        }
        return isFacilityAdded;
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
*/
}
