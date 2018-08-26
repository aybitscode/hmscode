package com.aybits.hms.func.amenity.cache;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.amenity.dao.AmenityDAO;
import com.aybits.hms.func.facility.beans.Facility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AmenityCache {
    private static ConcurrentHashMap<String, Facility> facilityCache = new ConcurrentHashMap<>();
    private static HashSet<String> facilityIds = new HashSet<>();
    private AmenityDAO facilityDAO = new AmenityDAO();

    public Boolean initCache(String facilityId){
        Boolean isFacilityCacheInitialized = false;
        if(facilityCache.isEmpty()){
            List<Facility> facilities = null;
            try {
                facilities = facilityDAO.fetchAllFacilities();
                if(!facilities.isEmpty()) {
                    for (Facility facility : facilities) {
                        facilityIds.add(facility.getFacilityId());
                        facilityCache.put(facility.getFacilityId(), facility);
                    }
                }

            }catch(Exception e){
                //LOG Cache Initialization failed
                //  throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all hotel details failed");
            }finally{
                if(!facilityCache.keySet().isEmpty()){
                    isFacilityCacheInitialized = true;
                }
            }
        }

        return isFacilityCacheInitialized;
    }

    public String addFacility(Facility facility) throws HMSException
    {
        Boolean isFacilityAdditionSuccessful = facilityDAO.addFacility(facility);
        if(isFacilityAdditionSuccessful){
            if (facilityCache.get(facility.getFacilityId()) == null) {
                facilityIds.add(facility.getFacilityId());
                facilityCache.put(facility.getFacilityId(), facility);
            }
        }
        return facility.getFacilityId();
    }

    public void updateFacility(Facility facility) {

        String facilityId = facility.getFacilityId();
        facilityCache.remove(facilityId);
        facilityCache.put(facilityId, facility);
    }
    
    public Facility getFacilityById(String facilityId) {
        Facility facility = facilityCache.get(facilityId);
        if (facility != null)
            return facility;
        else
            return null;
    }

    public List<Facility> getAllFacilities() {
        ArrayList<Facility> facilitys = new ArrayList<>();
        facilitys.addAll(facilityCache.values());
        return facilitys;
    }

    public List<String> getAllFacilityIds() {
        ArrayList<String> facilityIds = new ArrayList<>();
        facilityIds.addAll(facilityCache.keySet());
        return facilityIds;
    }

    public ConcurrentHashMap<String,Facility> getFacilityCache(){
        return facilityCache;
    }

}
