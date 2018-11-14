package com.aybits.hms.func.facility.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.dao.FacilityCache;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilityAPIHelper {


    static Logger log = Logger.getLogger(FacilityAPIHelper.class);
    FacilityCache facilityCache = new FacilityCache();

    private Boolean addFacility(Facility facility) throws HMSRuntimeException {
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
        } catch (HMSRuntimeException hrex) {
            log.info("Exception occurred while adding facility::" + facility.getFacilityId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.FACILITY_ADDITION_FAILED, "Exception occured while adding facility details for hotel["+hotelId+"]:"+hrex.getMessage()));
        }catch(Exception ex){
            log.info("Exception occurred while adding facility::" + facility.getFacilityId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Exception ocured while adding facility details for hotel["+hotelId+"]:"+ex.getMessage()));
        }finally{

            return isFacilityAdded;
        }
    }

    public Map<String,String> addFacilities(List<Facility> facilities) throws HMSRuntimeException {

        Boolean areFacilitiesAdded = true;
        Map<String,String> facilitiesMap = new HashMap<String,String>();
        String hotelId = null;
        try{
            if(facilities != null) {
                for (Facility facility : facilities) {
                    areFacilitiesAdded &= addFacility(facility);
                    if(areFacilitiesAdded){
                        facilitiesMap.put(facility.getFacilityId(),facility.getFacilityName());
                        hotelId = facility.getHotelId();
                    }
                }
            }
        }catch(HMSRuntimeException he){
            log.info("Adding facility details failed");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.FACILITY_ADDITION_FAILED, "Exception occurred while adding facilities for hotel["+hotelId+"]"));
        }catch(Exception ex){
            log.info("Exception occurred while adding facilities::");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Exception ocured while adding facility details for hotel["+hotelId+"]:"+ex.getMessage()));
        }finally {
            return facilitiesMap;
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

}
