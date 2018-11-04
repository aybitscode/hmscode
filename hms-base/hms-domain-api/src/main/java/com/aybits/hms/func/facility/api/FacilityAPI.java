package com.aybits.hms.func.facility.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.dao.FacilityCache;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

public class FacilityAPI implements HMSAPIProvider {

    static Logger log = Logger.getLogger(FacilityAPI.class);
    FacilityCache facilityCache = new FacilityCache();

    private Boolean addFacility(Facility facility) throws HMSRuntimeException{
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
        } catch (HMSRuntimeException e) {
            log.info("Exception occurred while adding facility::" + facility.getFacilityId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.FACILITY_ADDITION_FAILED, "Adding facility details failed for hotel["+hotelId+"]"));
        }finally{
            return isFacilityAdded;
        }
    }

    public Boolean addFacilities(Facility[] facilities) throws HMSRuntimeException {

        Boolean areFacilitiesAdded = true;
        try{
            if(facilities != null) {
                for (Facility facility : facilities) {
                    areFacilitiesAdded &= addFacility(facility);
                }
            }
        }catch(HMSRuntimeException he){
            log.info("Adding facility details failed");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.FACILITY_ADDITION_FAILED, "Exception occurred while adding facility"));
        }finally {
            return areFacilitiesAdded;
        }
    }


    public Boolean updateFacility(Facility facility)throws HMSRuntimeException{
        Boolean isFacilityUpdated = false;
        return isFacilityUpdated;
    }

    public Facility getFacility(String hotelId, String facilityId)throws HMSRuntimeException{
        Facility facility = facilityCache.getFacility(hotelId,facilityId);
        return facility;
    }

    public List<Facility> getAllFacilities(String hotelId)throws HMSRuntimeException{
        List<Facility> facilitiesList = null;
        facilitiesList = facilityCache.getAllFacilities(hotelId);
        return facilitiesList;
    }

    public List<Facility> getAvailableFacilities(String hotelId,Boolean isFacilityAvailable) throws HMSRuntimeException{
        List<Facility> availableFacilitiesList = null;

        availableFacilitiesList = facilityCache.getAllFacilities(hotelId);

        return availableFacilitiesList;
    }

    public List<Facility> getChargeableFacilities(String hotelId,String chargeable) throws HMSRuntimeException{
        List<Facility> chargeableFacilitiesList = null;
        try{
            Boolean isChargeable = Boolean.parseBoolean(chargeable);
            chargeableFacilitiesList = facilityCache.getAllChargeableFacilities(hotelId,isChargeable);
        }catch(HMSRuntimeException he){
           // throw new HMSRuntimeException("Exception while getting chargeable facilities for Hotel["+hotelId+"]",he);
        }catch(Exception e){
          //  throw new HMSRuntimeException("Exception while getting chargeable facilities for Hotel["+hotelId+"]",e);
        }

        return chargeableFacilitiesList;
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
    public Object validate(JSONObject object) throws HMSRuntimeException {
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
