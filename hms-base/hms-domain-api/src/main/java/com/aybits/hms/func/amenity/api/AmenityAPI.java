package com.aybits.hms.func.amenity.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.dao.AmenityCache;
import com.aybits.hms.func.amenity.dao.AmenityDAO;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

import static com.aybits.hms.arch.exception.HMSErrorCodes.AMENITY_ADDITION_FAILED;

public class AmenityAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(AmenityAPI.class);
    AmenityCache amenityCache = new AmenityCache();
    AmenityDAO amenityDAO = new AmenityDAO();


    private Boolean addAmenity(Amenity amenity) throws HMSRuntimeException {
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
        } catch (HMSRuntimeException e) {
            log.info("Exception occurred while adding amenity::" + amenity.getAmenityId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(AMENITY_ADDITION_FAILED, "Adding amenity details failed for hotel["+hotelId+"]"));
        }finally{
            return isAmenityAdded;
        }
    }

    public Boolean addAmenities(Amenity[] amenities) throws HMSRuntimeException {

        Boolean areAmenitiesAdded = true;
        try{
            if(amenities != null) {
                for (Amenity amenity : amenities) {
                    areAmenitiesAdded &= addAmenity(amenity);
                }
            }
        }catch(HMSRuntimeException he){
            log.info("Adding amenity details failed");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(AMENITY_ADDITION_FAILED, "Exception occurred while adding amenity"));
        }finally {
            return areAmenitiesAdded;
        }
    }


    public Boolean updateAmenity(Amenity amenity)throws HMSRuntimeException{
        Boolean isAmenityUpdated = false;
        return isAmenityUpdated;
    }

    public Amenity getAmenity(String hotelId, String amenityId)throws HMSRuntimeException{
        Amenity amenity = amenityCache.getAmenity(hotelId,amenityId);
        return amenity;
    }

    public List<Amenity> getAllAmenities(String hotelId)throws HMSRuntimeException{
        List<Amenity> amenitiesList = null;
        amenitiesList = amenityCache.getAllAmenities(hotelId);
        return amenitiesList;
    }

    public List<Amenity> getAvailableAmenities(String hotelId,Boolean isAmenityAvailable) throws HMSRuntimeException{
        List<Amenity> availableAmenitiesList = null;

        availableAmenitiesList = amenityCache.getAllAmenities(hotelId);

        return availableAmenitiesList;
    }

    public List<Amenity> getChargeableAmenities(String hotelId,String chargeable) throws HMSRuntimeException{
        List<Amenity> chargeableAmenitiesList = null;
        try{
            Boolean isChargeable = Boolean.parseBoolean(chargeable);
            chargeableAmenitiesList = amenityCache.getAllChargeableAmenities(hotelId,isChargeable);
        }catch(HMSRuntimeException he){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION,"Exception while getting chargeable amenities for Hotel["+hotelId+"]"));
        }

        return chargeableAmenitiesList;
    }


    @Override
    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String fetch(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSRuntimeException {
        return null;
    }
}
