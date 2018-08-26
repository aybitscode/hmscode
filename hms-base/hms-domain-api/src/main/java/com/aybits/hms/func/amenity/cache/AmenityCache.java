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
                    }
                }

            }catch(Exception e){
                //LOG Cache Initialization failed
                //  throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all hotel details failed");
            }finally{
                if(!amenityCache.keySet().isEmpty()){
                    isAmenityCacheInitialized = true;
                }
            }
        }

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
        else
            return null;
    }

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
    }

}
