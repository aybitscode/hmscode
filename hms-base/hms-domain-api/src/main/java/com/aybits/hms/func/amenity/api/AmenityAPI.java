package com.aybits.hms.func.amenity.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.dao.AmenityCache;
import com.aybits.hms.func.amenity.dao.AmenityDAO;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class AmenityAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(AmenityAPI.class);
    AmenityCache amenityCache = new AmenityCache();
    AmenityDAO amenityDAO = new AmenityDAO();


    private Boolean addAmenity(Amenity amenity) throws HMSException {
        String hotelId = amenity.getHotelId();
        Boolean isAmenityAdded = false;
        try {
            if (amenity.getAmenityId() != null && amenity.getAmenityId().equals(HMSAPIConstants.TO_BE_GENERATED )) {

                String amenityId =  amenityCache.addAmenity(amenity);
                if (amenityId == null) {
                    throw new NullPointerException("Amenity Addition failed for Hotel["+hotelId+"]");
                }
                isAmenityAdded = true;
            }
        } catch (Exception e) {
            log.info("Exception occurred while adding amenity::" + amenity.getAmenityId());
            throw new HMSException(HMSErrorCodes.AMENITY_ADDITION_FAILED, "Adding amenity details failed for hotel["+hotelId+"]");
        }finally{
            return isAmenityAdded;
        }
    }

    public Boolean addAmenities(Amenity[] amenities) throws HMSException {

        Boolean areAmenitiesAdded = true;
        try{
            if(amenities != null) {
                for (Amenity amenity : amenities) {
                    areAmenitiesAdded &= addAmenity(amenity);
                }
            }
        }catch(HMSException he){
            log.info("Adding amenity details failed");
            throw new HMSException(HMSErrorCodes.AMENITY_ADDITION_FAILED, "Exception occurred while adding amenity");
        }finally {
            return areAmenitiesAdded;
        }
    }


    public Boolean updateAmenity(Amenity amenity)throws HMSException{
        Boolean isAmenityUpdated = false;
        return isAmenityUpdated;
    }

    public Amenity getAmenity(String hotelId, String amenityId)throws HMSException{
        Amenity amenity = amenityCache.getAmenity(hotelId,amenityId);
        return amenity;
    }

    public List<Amenity> getAllAmenities(String hotelId)throws HMSException{
        List<Amenity> amenitiesList = null;
        amenitiesList = amenityCache.getAllAmenities(hotelId);
        return amenitiesList;
    }

    public List<Amenity> getAvailableAmenities(String hotelId,Boolean isAmenityAvailable) throws HMSException{
        List<Amenity> availableAmenitiesList = null;

        availableAmenitiesList = amenityCache.getAllAmenities(hotelId);

        return availableAmenitiesList;
    }

    public List<Amenity> getChargeableAmenities(String hotelId,String chargeable) throws HMSException{
        List<Amenity> chargeableAmenitiesList = null;
        try{
            Boolean isChargeable = Boolean.parseBoolean(chargeable);
            chargeableAmenitiesList = amenityCache.getAllChargeableAmenities(hotelId,isChargeable);
        }catch(HMSException he){
            throw new HMSException("Exception while getting chargeable amenities for Hotel["+hotelId+"]",he);
        }catch(Exception e){
            throw new HMSException("Exception while getting chargeable amenities for Hotel["+hotelId+"]",e);
        }

        return chargeableAmenitiesList;
    }



}
