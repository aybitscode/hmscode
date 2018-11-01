package com.aybits.hms.func.facility.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.dao.FacilityCache;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

public class FacilityAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(FacilityAPI.class);
    FacilityCache facilityCache = new FacilityCache();

    private Boolean addFacility(Facility facility) throws HMSException{
        String hotelId = facility.getHotelId();
        Boolean isFacilityAdded = false;
        try {
            if (facility.getFacilityId() != null && facility.getFacilityId().equals(HMSAPIConstants.TO_BE_GENERATED )) {

                    String facilityId =  facilityCache.addFacility(facility);
                    if (facilityId == null) {
                        throw new NullPointerException("Facility Addition failed for Hotel["+hotelId+"]");
                    }
                    isFacilityAdded = true;
            }
        } catch (Exception e) {
            log.info("Exception occurred while adding facility::" + facility.getFacilityId());
            throw new HMSException(HMSErrorCodes.FACILITY_ADDITION_FAILED, "Adding facility details failed for hotel["+hotelId+"]");
        }finally{
            return isFacilityAdded;
        }
    }

    public Boolean addFacilities(Facility[] facilities) throws HMSException {

        Boolean areFacilitiesAdded = true;
        try{
            if(facilities != null) {
                for (Facility facility : facilities) {
                    areFacilitiesAdded &= addFacility(facility);
                }
            }
        }catch(HMSException he){
            log.info("Adding facility details failed");
            throw new HMSException(HMSErrorCodes.FACILITY_ADDITION_FAILED, "Exception occurred while adding facility");
        }finally {
            return areFacilitiesAdded;
        }
    }


    public Boolean updateFacility(Facility facility)throws HMSException{
        Boolean isFacilityUpdated = false;
        return isFacilityUpdated;
    }

    public Facility getFacility(String hotelId, String facilityId)throws HMSException{
        Facility facility = facilityCache.getFacility(hotelId,facilityId);
        return facility;
    }

    public List<Facility> getAllFacilities(String hotelId)throws HMSException{
        List<Facility> facilitiesList = null;
        facilitiesList = facilityCache.getAllFacilities(hotelId);
        return facilitiesList;
    }

    public List<Facility> getAvailableFacilities(String hotelId,Boolean isFacilityAvailable) throws HMSException{
        List<Facility> availableFacilitiesList = null;

        availableFacilitiesList = facilityCache.getAllFacilities(hotelId);

        return availableFacilitiesList;
    }

    public List<Facility> getChargeableFacilities(String hotelId,String chargeable) throws HMSException{
        List<Facility> chargeableFacilitiesList = null;
        try{
            Boolean isChargeable = Boolean.parseBoolean(chargeable);
            chargeableFacilitiesList = facilityCache.getAllChargeableFacilities(hotelId,isChargeable);
        }catch(HMSException he){
            throw new HMSException("Exception while getting chargeable facilities for Hotel["+hotelId+"]",he);
        }catch(Exception e){
            throw new HMSException("Exception while getting chargeable facilities for Hotel["+hotelId+"]",e);
        }

        return chargeableFacilitiesList;
    }

    @Override
    public String process(JSONObject object) throws HMSException {
        return null;
    }

    @Override
    public String fetch(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSException {
        return null;
    }
}
