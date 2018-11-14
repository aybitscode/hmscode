package com.aybits.hms.func.amenity.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.dao.AmenityCache;
import com.aybits.hms.func.amenity.dao.AmenityDAO;
import com.aybits.hms.func.common.api.HmsAPI;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aybits.hms.arch.exception.HMSErrorCodes.AMENITY_ADDITION_FAILED;

public class AmenityAPIHelper implements HmsAPI {

    static Logger log = Logger.getLogger(AmenityAPIHelper.class);
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

    public Map<String,String> addAmenities(List<Amenity> amenities) throws HMSRuntimeException {

        Boolean areAmenitiesAdded = true;
        Map<String,String> amenitiesMap = new HashMap<String,String>();
        String hotelId = null;
        try{
            if(amenities != null) {
                for (Amenity amenity : amenities) {
                    areAmenitiesAdded &= addAmenity(amenity);
                    if(areAmenitiesAdded){
                        amenitiesMap.put(amenity.getAmenityId(),amenity.getAmenityName());
                        hotelId = amenity.getHotelId();
                    }
                }
            }
        }catch(HMSRuntimeException he){
            log.info("Adding amenitiy details failed");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.AMENITY_ADDITION_FAILED, "Exception occurred while adding amenities for hotel["+hotelId+"]"));
        }catch(Exception ex){
            log.info("Exception occurred while adding amenities:"+ex.getMessage());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Exception occurred while adding amenities for hotel["+hotelId+"]:"+ex.getMessage()));
        }finally {
            return amenitiesMap;
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
    public Object init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {

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
