package com.aybits.hms.func.amenity.dao;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.amenity.beans.Amenity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AmenityCache {

    private static ConcurrentHashMap<String, HashMap<String, Amenity>> hotelAmenityCache = new ConcurrentHashMap<>();
    private HashMap<String, Amenity> amenityHashMap = new HashMap<>();

    private AmenitySelectDAO amenitySelectDAO = new AmenitySelectDAO();
    private AmenityDAO amenityDAO = new AmenityDAO();

    public Boolean initCache(String hotelId) throws HMSException {

        Boolean isCacheInitialized = initializeHotelAmenityCache(hotelId, false);

        return isCacheInitialized;
    }


    private Boolean initializeHotelAmenityCache(String hotelId, Boolean reload) throws HMSException {

        Boolean isCacheInitialized = false;
        if (reload) {
            hotelAmenityCache = new ConcurrentHashMap<>();
        }
        if (hotelAmenityCache.isEmpty()) {
            try {
                amenityHashMap = amenitySelectDAO.getAmenitiesMapByHotelId(hotelId);
                if (!amenityHashMap.isEmpty()) {
                    hotelAmenityCache.put(hotelId, amenityHashMap);
                }
            } catch (HMSException e) {
                //LOG Cache Initialization failed
                throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE, "Loading Hotel Amenities Cache failed");
            } finally {
                if (!hotelAmenityCache.keySet().isEmpty()) {
                    isCacheInitialized = true;
                }
            }
        }
        return isCacheInitialized;
    }

    public String addAmenity(Amenity amenity) throws HMSException {
        String hotelId = amenity.getHotelId();
        String amenityId = amenity.getAmenityId();

        Amenity amenityFromCache = getAmenity(hotelId, amenityId);

        if (amenityFromCache == null) {
            Boolean isAmenityAdditionSuccessful = amenityDAO.addAmenity(amenity);
            if (isAmenityAdditionSuccessful) {
                updateAmenity(amenity);
            }
        }
        return amenityId;
    }

    private void updateAmenity(Amenity amenity) {
        String hotelId = amenity.getHotelId();
        String amenityId = amenity.getAmenityId();
        HashMap<String, Amenity> updatedAmenityMap = new HashMap<>();
        amenityHashMap = hotelAmenityCache.get(hotelId);
        updatedAmenityMap.putAll(amenityHashMap);
        updatedAmenityMap.put(amenityId, amenity);
        hotelAmenityCache.remove(hotelId);
        hotelAmenityCache.put(hotelId, updatedAmenityMap);
    }

    public Amenity getAmenity(String hotelId, String amenityId) throws HMSException {

        amenityHashMap = hotelAmenityCache.get(hotelId);
        Amenity amenity = amenityHashMap.get(amenityId);
        if (amenity == null) {
            amenity = amenitySelectDAO.getAmenity(hotelId, amenityId);
            updateAmenity(amenity);
        }
        return amenity;
    }

    public List<Amenity> getAllAmenities(String hotelId) throws HMSException{
        ArrayList<Amenity> amenities = new ArrayList<>();
        amenityHashMap = hotelAmenityCache.get(hotelId);

        if (amenityHashMap.keySet().isEmpty()) {
            reloadHotelAmenitiesCache(hotelId);
        }

        amenities.addAll(amenityHashMap.values());
        return amenities;
    }

    public List<Amenity> getAllAvailableAmenities(String hotelId, Boolean isAvailable) throws HMSException{
        List<Amenity> availableAmenities = new ArrayList<Amenity>();
        amenityHashMap = hotelAmenityCache.get(hotelId);

        if (amenityHashMap.keySet().isEmpty()) {
            reloadHotelAmenitiesCache(hotelId);
        }

        for (Amenity amenity : amenityHashMap.values()) {
            if (amenity.isAvailable()) {
                availableAmenities.add(amenity);
            }
        }
        return availableAmenities;
    }

    public List<Amenity> getAllChargeableAmenities(String hotelId, Boolean isChargeable) throws HMSException {
        List<Amenity> availableAmenities = new ArrayList<Amenity>();
        amenityHashMap = hotelAmenityCache.get(hotelId);

        if (amenityHashMap.keySet().isEmpty()) {
            reloadHotelAmenitiesCache(hotelId);
        }

        for (Amenity amenity : amenityHashMap.values()) {
            if (amenity.isChargeable()) {
                availableAmenities.add(amenity);
            }
        }

        return availableAmenities;
    }


    public Boolean reloadHotelAmenitiesCache(String hotelId) throws HMSException {

        Boolean reloadCache = true;
        Boolean isCacheInitialized = initializeHotelAmenityCache(hotelId, reloadCache);
        return isCacheInitialized;

    }


    public ConcurrentHashMap<String, HashMap<String, Amenity>> getAmenityCache() {
        return hotelAmenityCache;
    }


}
