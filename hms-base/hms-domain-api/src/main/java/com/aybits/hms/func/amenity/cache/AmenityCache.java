package com.aybits.hms.func.amenity.cache;

import com.aybits.hms.arch.exception.HMSException;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AmenityCache {/*
<<<<<<< HEAD
    private static ConcurrentHashMap<String, Amenity> amenityCache = new ConcurrentHashMap<>();
    private static HashSet<String> amenityIds = new HashSet<>();
    private AmenityDAO amenityDAO = new AmenityDAO();

    public Boolean initCache(String amenityId){
        Boolean isAmenityCacheInitialized = false;
        if(amenityCache.isEmpty()){
            List<Amenity> facilities = null;
            try {
                facilities = amenityDAO.fetchAllAmenities();
                if(!facilities.isEmpty()) {
                    for (Amenity amenity : facilities) {
                        amenityIds.add(amenity.getAmenityId());
                        amenityCache.put(amenity.getAmenityId(), amenity);
=======
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
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0
                    }
                }

            }catch(Exception e){
                //LOG Cache Initialization failed
                //  throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all hotel details failed");
            }finally{
<<<<<<< HEAD
                if(!amenityCache.keySet().isEmpty()){
                    isAmenityCacheInitialized = true;
=======
                if(!facilityCache.keySet().isEmpty()){
                    isFacilityCacheInitialized = true;
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0
                }
            }
        }

<<<<<<< HEAD
        return isAmenityCacheInitialized;
    }

    public String addAmenity(Amenity amenity) throws HMSException
    {
        Boolean isAmenityAdditionSuccessful = amenityDAO.addAmenity(amenity);
        if(isAmenityAdditionSuccessful){
            if (amenityCache.get(amenity.getAmenityId()) == null) {
                amenityIds.add(amenity.getAmenityId());
                amenityCache.put(amenity.getAmenityId(), amenity);
            }
        }
        return amenity.getAmenityId();
    }

    public void updateAmenity(Amenity amenity) {

        String amenityId = amenity.getAmenityId();
        amenityCache.remove(amenityId);
        amenityCache.put(amenityId, amenity);
    }
    
    public Amenity getAmenityById(String amenityId) {
        Amenity amenity = amenityCache.get(amenityId);
        if (amenity != null)
            return amenity;
=======
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
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0
        else
            return null;
    }

<<<<<<< HEAD
    public List<Amenity> getAllAmenities() {
        ArrayList<Amenity> amenitys = new ArrayList<>();
        amenitys.addAll(amenityCache.values());
        return amenitys;
    }

    public List<String> getAllAmenityIds() {
        ArrayList<String> amenityIds = new ArrayList<>();
        amenityIds.addAll(amenityCache.keySet());
        return amenityIds;
    }

    public ConcurrentHashMap<String,Amenity> getAmenityCache(){
        return amenityCache;
=======
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
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0
    }*/

}
