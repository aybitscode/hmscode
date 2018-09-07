package com.aybits.hms.func.facility.cache;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.common.cache.HMSCache;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.dao.FacilityDAO;
import com.aybits.hms.func.facility.dao.FacilitySelectDAO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FacilityCache {

    private static ConcurrentHashMap<String, Facility> facilityCache = new ConcurrentHashMap<>();

    private static HashSet<String> facilityIds = new HashSet<>();
    private FacilitySelectDAO facilitySelectDAO = new FacilitySelectDAO();
    private FacilityDAO facilityDAO = new FacilityDAO();
    private HMSCache hmsCache = HMSCache.getHmsCache();

    public Boolean initCache(String hotelId) throws HMSException{
        Boolean isCacheInitialized = false;
        if(facilityCache.isEmpty()){
            List<Facility> facilities = null;
            try {
                facilities = facilitySelectDAO.getAllFacilities(hotelId);
                if(!facilities.isEmpty()) {
                    for (Facility facility : facilities) {
                        facilityIds.add(facility.getFacilityId());
                        facilityCache.put(facility.getHotelId()+"~"+facility.getFacilityId(), facility);
                    }
                }

            }catch(HMSException e){
                //LOG Cache Initialization failed
                  throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Loading Hotel Facilities Cache failed");
            }finally{
                if(!facilityCache.keySet().isEmpty()){
                    isCacheInitialized = true;
                }
            }
        }

        return isCacheInitialized;
    }

    public String addFacility(Facility facility) throws HMSException
    {
        Facility facilityFromCache = this.getFacility(facility.getHotelId(),facility.getFacilityId());

        if(facilityFromCache == null){
            Boolean isFacilityAdditionSuccessful = facilityDAO.addFacility(facility);
            if(isFacilityAdditionSuccessful){
                if (facilityCache.get(facility.getFacilityId()) == null) {
                    facilityIds.add(facility.getFacilityId());
                    facilityCache.put(facility.getFacilityId(), facility);
                }
                return facility.getFacilityId();
            }else{
               return null;
            }
        }
    }

    public void updateFacility(Facility facility) {

        String facilityId = facility.getFacilityId();
        facilityCache.remove(facilityId);
        facilityCache.put(facilityId, facility);
    }
    
    public Facility getFacility(String hotelId, String facilityId) throws HMSException{

        String  key = HMSUtilAPI.getKey(hotelId,facilityId);
        Facility facility = facilityCache.get(key);
        if (facility == null){
            facility = facilitySelectDAO.getFacility(hotelId,facilityId);
        }

        return facility;
    }

    public List<Facility> getAllFacilities(String hotelId) {
        ArrayList<Facility> facilities = new ArrayList<>();
        facilities.addAll(facilityCache.values());
        return facilities;
    }


    public ConcurrentHashMap<String,Facility> getFacilityCache(){
        return facilityCache;
    }



}
