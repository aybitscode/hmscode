package com.aybits.hms.func.amenity.cache;

import com.aybits.hms.arch.exception.HMSException;

<<<<<<< HEAD
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.dao.AmenityDAO;

import com.aybits.hms.func.amenity.dao.AmenityDAO;
import com.aybits.hms.func.facility.beans.Facility;

=======
>>>>>>> ae5dad5d87e8db07071616eabafc3819e5249428

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

<<<<<<< HEAD
public class AmenityCache {

=======
public class AmenityCache {/*
<<<<<<< HEAD
>>>>>>> ae5dad5d87e8db07071616eabafc3819e5249428
    private static ConcurrentHashMap<String, Amenity> amenityCache = new ConcurrentHashMap<>();
    private static HashSet<String> amenityIds = new HashSet<>();
    private AmenityDAO amenityDAO = new AmenityDAO();



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

<<<<<<< HEAD
=======
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
>>>>>>> ae5dad5d87e8db07071616eabafc3819e5249428

}
