package com.aybits.hms.feature;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.HMSRequest;
import com.aybits.hms.common.HMSRequestProcessor;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.func.common.api.HMSAPI;
import com.aybits.hms.func.facility.api.FacilityAPI;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.beans.FacilityType;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;

import java.util.List;
import java.util.Map;

public class FacilityRequestProcessor implements HMSRequestProcessor {


    @Autowired
    HMSAPI hmsAPI;
    static Logger Log = Logger.getLogger(FacilityRequestProcessor.class);



    @Override
    public HMSResponse processRequest(HMSRequest request) {
        Log.info("Facility request handler invoked");


        String operation = request.getOperation();
        String entity = request.getEntity();
        String data = request.getData();
        String action = operation + "/" + entity;
        String tokenId = request.getTokenId();
        HMSResponse hmsResponse = null;
        JSONObject dataJSON = null;


        try {
            dataJSON = new JSONObject(data);
            validateRequestData(dataJSON);
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex, tokenId);
        }catch(Exception ex){
            hmsResponse = populateGenericErrorResponse(ex,tokenId);
        }finally{
            if (hmsResponse != null) {
                return hmsResponse;
            }
        }


        try {
            switch (action) {
                case "fetch/all/facilities":
                    hmsResponse = fetchAllFacilities(request);
                    break;
                case "fetch/facility":
                    hmsResponse = fetchFacilityById(request);
                    break;
                case "add/facilities":
                    hmsResponse = addFacilites(request);
                    break;
                case "disable/facility":
                    hmsResponse = disableFacility(request);
                    break;
            }
        }catch(Exception ex){
            hmsResponse = populateGenericErrorResponse(ex,tokenId);
        } finally{
            return hmsResponse;
        }
    }

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {
        hmsAPI.validate(dataJSON);
    }


    private HMSResponse fetchAllFacilities(HMSRequest request) throws HMSRuntimeException {
        return null;
    }

    private HMSResponse fetchFacilityById(HMSRequest request) throws HMSRuntimeException{
        return null;
    }

    private HMSResponse addFacilites(HMSRequest request) {
        Log.info("in addFacilites");
        try {
            String data = request.getData();
            Gson gson = new Gson();
            Object myTypes = (Object)gson.fromJson(data, Object.class);
            List facilitiesList = ((List)((LinkedTreeMap)myTypes).get("facilities"));

            Facility[] facilities = new Facility[facilitiesList.size()];

            for(int i = 0; i < facilitiesList.size(); i++){
                Map facilityMap = (Map)facilitiesList.get(i);
                Facility facility = new Facility();
                facility.setHotelId(facilityMap.get("hotel_id").toString());
                facility.setFacilityId(facilityMap.get("facility_id").toString());
                facility.setFacilityName(facilityMap.get("facility_name").toString());
                facility.setFacilityDescription(facilityMap.get("facility_description").toString());
                facility.setIsAvailable(Boolean.valueOf(facilityMap.get("is_available").toString()));
                facility.setChargeable(Boolean.valueOf(facilityMap.get("is_chargeable").toString()));
                facility.setFacilityType(FacilityType.valueOf(facilityMap.get("facility_type").toString()));
                //facility.setFacilityPrice(Double.valueOf(facilityMap.get("facility_price").toString()));
                facilities[i] = facility;
            }

            if(facilities != null && facilities.length > 0) {
                //boolean addFacilityStatus = facilityAPI.addFacilities(facilities);
                //return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Facilities added succesfully",null));
                return null;
            }else{
               // return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", "no facilities to add", null));
                return null;
            }
        } catch (Exception e) {
           // return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
            return null;
        }
    }


    private HMSResponse disableFacility(HMSRequest request){
        return null;
    }





}
