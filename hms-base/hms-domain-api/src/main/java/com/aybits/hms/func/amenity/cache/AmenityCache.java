package com.aybits.hms.func.amenity.cache;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.dao.AmenityDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class AmenityCache {



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

    public List<String> getAllAmenityIds() {
        ArrayList<String> amenityIds = new ArrayList<>();
        amenityIds.addAll(amenityCache.keySet());
        return amenityIds;
    }

    public ConcurrentHashMap<String,Amenity> getAmenityCache(){
        return amenityCache;
    }

}
