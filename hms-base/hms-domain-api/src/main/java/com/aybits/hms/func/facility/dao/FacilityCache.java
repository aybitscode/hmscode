package com.aybits.hms.func.facility.dao;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.facility.beans.Facility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FacilityCache {

    private static ConcurrentHashMap<String, HashMap<String, Facility>> hotelFacilityCache = new ConcurrentHashMap<>();
    private HashMap<String, Facility> facilityHashMap = new HashMap<>();

    private FacilitySelectDAO facilitySelectDAO = new FacilitySelectDAO();
    private FacilityDAO facilityDAO = new FacilityDAO();

    public Boolean initCache(String hotelId) throws HMSRuntimeException {

        Boolean isCacheInitialized = initializeHotelFacilityCache(hotelId, false);

        return isCacheInitialized;
    }


    private Boolean initializeHotelFacilityCache(String hotelId, Boolean reload) throws HMSRuntimeException {

        Boolean isCacheInitialized = false;
        if (reload) {
            hotelFacilityCache = new ConcurrentHashMap<>();
        }
        if (hotelFacilityCache.isEmpty()) {
            try {
                facilityHashMap = facilitySelectDAO.getFacilitiesMapByHotelId(hotelId);
                if (!facilityHashMap.isEmpty()) {
                    hotelFacilityCache.put(hotelId, facilityHashMap);
                }
            } catch (HMSRuntimeException e) {
                //LOG Cache Initialization failed
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE, "Loading Hotel Facilities Cache failed"));
            } finally {
                if (!hotelFacilityCache.keySet().isEmpty()) {
                    isCacheInitialized = true;
                }
            }
        }
        return isCacheInitialized;
    }

    public String addFacility(Facility facility) throws HMSRuntimeException {
        String hotelId = facility.getHotelId();
        String facilityId = facility.getFacilityId();

        Facility facilityFromCache = this.getFacility(hotelId, facilityId);

        if (facilityFromCache == null) {
            Boolean isFacilityAdditionSuccessful = facilityDAO.addFacility(facility);
            if (isFacilityAdditionSuccessful) {
                updateFacility(facility);
            }
        }
        return facilityId;
    }

    private void updateFacility(Facility facility) {
        String hotelId = facility.getHotelId();
        String facilityId = facility.getFacilityId();
        HashMap<String, Facility> updatedFacilityMap = new HashMap<>();
        facilityHashMap = hotelFacilityCache.get(hotelId);
        updatedFacilityMap.putAll(facilityHashMap);
        updatedFacilityMap.put(facilityId, facility);
        hotelFacilityCache.remove(hotelId);
        hotelFacilityCache.put(hotelId, updatedFacilityMap);
    }

    public Facility getFacility(String hotelId, String facilityId) throws HMSRuntimeException {

        facilityHashMap = hotelFacilityCache.get(hotelId);
        Facility facility = facilityHashMap.get(facilityId);
        if (facility == null) {
            facility = facilitySelectDAO.getFacility(hotelId, facilityId);
            updateFacility(facility);
        }
        return facility;
    }

    public List<Facility> getAllFacilities(String hotelId) throws HMSRuntimeException{
        ArrayList<Facility> facilities = new ArrayList<>();
        facilityHashMap = hotelFacilityCache.get(hotelId);

        if (facilityHashMap.keySet().isEmpty()) {
            reloadHotelFacilitiesCache(hotelId);
        }

        facilities.addAll(facilityHashMap.values());
        return facilities;
    }

    public List<Facility> getAllAvailableFacilities(String hotelId, Boolean isAvailable) throws HMSRuntimeException{
        List<Facility> availableFacilities = new ArrayList<Facility>();
        facilityHashMap = hotelFacilityCache.get(hotelId);

        if (facilityHashMap.keySet().isEmpty()) {
            reloadHotelFacilitiesCache(hotelId);
        }

        for (Facility facility : facilityHashMap.values()) {
            if (facility.isAvailable()) {
                availableFacilities.add(facility);
            }
        }
        return availableFacilities;
    }

    public List<Facility> getAllChargeableFacilities(String hotelId, Boolean isChargeable) throws HMSRuntimeException {
        List<Facility> availableFacilities = new ArrayList<Facility>();
        facilityHashMap = hotelFacilityCache.get(hotelId);

        if (facilityHashMap.keySet().isEmpty()) {
            reloadHotelFacilitiesCache(hotelId);
        }

        for (Facility facility : facilityHashMap.values()) {
                if (facility.isChargeable()) {
                    availableFacilities.add(facility);
                }
        }

        return availableFacilities;
    }


    public Boolean reloadHotelFacilitiesCache(String hotelId) throws HMSRuntimeException {

        Boolean reloadCache = true;
        Boolean isCacheInitialized = initializeHotelFacilityCache(hotelId, reloadCache);
        return isCacheInitialized;

    }


    public ConcurrentHashMap<String, HashMap<String, Facility>> getFacilityCache() {
        return hotelFacilityCache;
    }


}
