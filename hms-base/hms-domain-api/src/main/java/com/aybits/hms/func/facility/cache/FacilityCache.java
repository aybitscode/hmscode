package com.aybits.hms.func.facility.cache;

import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.voucher.beans.Voucher;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FacilityCache {
    private static ConcurrentHashMap<String, Facility> facilityCache = new ConcurrentHashMap<>();
    private static HashSet<String> facilityIds = new HashSet<>();

    public Boolean initCache(String hotelId){
        return false;
    }
    public void addFacility(Facility facility) {
        if (facilityCache.get(String.valueOf(facility.getFacilityId())) == null) {
            facilityIds.add(facility.getFacilityId());
            facilityCache.put(facility.getFacilityId(), facility);
        }
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
