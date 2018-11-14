package com.aybits.hms.func.features.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.amenity.api.AmenityAPIHelper;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import com.aybits.hms.func.facility.api.FacilityAPIHelper;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.features.beans.Feature;
import com.aybits.hms.func.service.api.ServiceAPIHelper;
import com.aybits.hms.func.service.beans.Service;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FeatureAPIHelper {

    static Logger Log = Logger.getLogger(FeatureAPIHelper.class);
    FacilityAPIHelper facilityAPIHelper = new FacilityAPIHelper();
    ServiceAPIHelper serviceAPIHelper = new ServiceAPIHelper();
    AmenityAPIHelper amenityAPIHelper = new AmenityAPIHelper();

    public String addFeature(Feature feature) throws HMSRuntimeException {

        JSONObject featureJSON = new JSONObject();

        try {
            featureJSON.put(HMSJSONConstants.HOTEL_ID,feature.getHotelId());

            List<Facility> facilities = feature.getFacilityList();
            List<Service> services = feature.getServiceList();
            List<Amenity> amenities = feature.getAmenityList();



            String[] entityConstants = {HMSJSONConstants.FACILITY_ID,HMSJSONConstants.FACILITY_NAME};
            Map<String,String> entityMap = facilityAPIHelper.addFacilities(facilities);
            JSONArray componentsArray = populateFeatureComponents(entityMap,entityConstants);
            featureJSON.put(HMSJSONConstants.FACILITIES,componentsArray);

            entityConstants = new String[]{HMSJSONConstants.AMENITY_ID,HMSJSONConstants.AMENITY_NAME};
            entityMap = amenityAPIHelper.addAmenities(amenities);
            componentsArray = populateFeatureComponents(entityMap,entityConstants);
            featureJSON.put(HMSJSONConstants.AMENITIES,componentsArray);

            entityConstants = new String[]{HMSJSONConstants.SERVICE_ID,HMSJSONConstants.SERVICE_NAME};
            entityMap = serviceAPIHelper.addServices(services);
            componentsArray = populateFeatureComponents(entityMap,entityConstants);
            featureJSON.put(HMSJSONConstants.SERVICES,componentsArray);


        }catch(HMSRuntimeException hrex){
            Log.info("Exception occurred while adding features to the hotel::" + feature.getHotelId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.FEATURE_ADDITION_FAILED, "Adding feature details failed for hotel["+feature.getHotelId()+"]"));
        }catch(Exception ex){
            Log.info("Exception occurred while adding features to the hotel::" + feature.getHotelId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Exception occured while Adding feature details to hotel["+feature.getHotelId()+"]::"+ex.getMessage()));
        }finally{
            return featureJSON.toString();
        }
    }

    private JSONArray populateFeatureComponents(Map<String,String> entityMap,String[] entityConstants) throws JSONException {

        Set<String> keys = entityMap.keySet();
        JSONArray entitiesArray = new JSONArray();
        for(String entityId:keys){
            String entityName = entityMap.get(entityId);
            JSONObject entityJSON = new JSONObject();
            entityJSON.put(entityConstants[0],entityId);
            entityJSON.put(entityConstants[1],entityName);
            entitiesArray.put(entityJSON);
        }
        return entitiesArray;
    }






}
