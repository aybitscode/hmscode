package com.aybits.hms.facility;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.facility.api.FacilityAPI;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.beans.FacilityType;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Map;

public class FacilityRequestHandler implements HmsRequestHandler {

    HMSJsonRequestComponents components = null;
    FacilityAPI facilityAPI = new FacilityAPI();
    static Logger Log = Logger.getLogger(FacilityRequestHandler.class);

    @Override
    public ValidationResult validateRequest(Request request, Response response) {
        return null;
    }

    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public ValidationResult validateRequestData(Request request, Response response) {
        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Hotel request handler invoked");

        ValidationResult result = validateRequest(request, response);
        if (result != null) {
            //return result.getMessage();
        }

        components = HMSJSONParser.getHmsJsonRequestComponents(request.body());
        String action = getActionString(request);

        String message = "";

        try {
            switch (action) {
                case "fetch/all/facilities":
                    message = fetchAllFacilities(request);
                    break;
                case "fetch/facility":
                    message = fetchFacilityById(request);
                    break;
                case "add/facilities":
                    message = addFacilites(request);
                    break;
            }
        }catch(HMSException hmse){

        }
        return message;
    }

    private String fetchAllFacilities(Request request) throws HMSException {
        return null;
    }

    private String fetchFacilityById(Request request) throws HMSException{
        return null;
    }

    private String addFacilites(Request request) {
        Log.info("in addFacilites");
        try {
            String data = components.getData();
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
                boolean addFacilityStatus = facilityAPI.addFacilities(facilities);
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Facilities added succesfully",null));
            }else{
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", "no facilities to add", null));
            }
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }
    }





}
