package com.aybits.hms.func.facility.cache;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.dao.FacilityDAO;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.voucher.beans.Voucher;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FacilityCache {
    private static ConcurrentHashMap<String, Facility> facilityCache = new ConcurrentHashMap<>();
    private static HashSet<String> facilityIds = new HashSet<>();
    private FacilityDAO facilityDAO = new FacilityDAO();

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

    public String addFacility(Facility[] facilities) throws HMSException
    {
        Boolean isFacilityAdditionSuccessful = facilityDAO.addFacility(facilities);
        if(isFacilityAdditionSuccessful){
            for(Facility facility : facilities){
                if (facilityCache.get(facility.getFacilityId()) == null) {
                    facilityIds.add(facility.getFacilityId());
                    facilityCache.put(facility.getFacilityId(), facility);
                }
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
