package com.aybits.hms.hotel;

import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.amenity.api.AmenityAPI;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.beans.AmenityType;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.facility.api.FacilityAPI;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.beans.FacilityType;
import com.aybits.hms.func.helpdesk.beans.Service;
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelAttributes;
import com.aybits.hms.func.services.api.ServicesAPI;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HotelRequestHandler implements HmsRequestHandler {
    static Logger Log = Logger.getLogger(EmployeeRequestHandler.class);
    HotelAPI hotelAPI = new HotelAPI();
    FacilityAPI facilityAPI = new FacilityAPI();
    AmenityAPI amenityAPI = new AmenityAPI();
    ServicesAPI servicesAPI = new ServicesAPI();
    HMSJsonRequestComponents components = null;

    @Override
    public ValidationResult validateRequestData(Request request, Response response) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        return result;
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

        switch (action) {
            case "/fetch/all":
                message = fetchAllHotels(request);
                break;
            case "/fetch/hotel-id":
                message = fetchHotelByHotelId(request);
                break;
            case "/fetch/emp-id":
                message = fetchHotelByEmployeeId(request);
                break;
            case "/setup/hotel":
                message = addHotel(request);
                break;
            case "/setup/category":
                message = addRoomCategory(request);
                break;
            case "/setup/facilities":
                message = addFacilites(request);
                message = addAmenities(request);
                message = addServices(request);
                break;
            case "/setup/tax-rules":
                message = addTaxRules(request);
                break;
        }
        return message;
    }

    private String addHotel(Request request) {
        Log.info("in addHotel");
        try {
            Hotel hotel = (Hotel) HMSJSONParser.convertJSONToObject(components.getData(), Hotel.class);
            String hotelId = hotelAPI.addHotel(hotel);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Hotel successfully added", hotelId));
        } catch (Exception e) {
            e.printStackTrace();
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }
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
                facility.setIsFacilityAvailable(Boolean.valueOf(facilityMap.get("is_available").toString()));
                facility.setChargeable(Boolean.valueOf(facilityMap.get("is_chargeable").toString()));
                facility.setFacilityType(FacilityType.valueOf(facilityMap.get("facility_type").toString()));
                //facility.setFacilityPrice(Double.valueOf(facilityMap.get("facility_price").toString()));
                facilities[i] = facility;
            }

            if(facilities != null && facilities.length > 0) {
                boolean addFacilityStatus = facilityAPI.addFacility(facilities);
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Facilities added succesfully",null));
            }else{
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", "no facilities to add", null));
            }
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }
    }

    private String addAmenities(Request request) {
        Log.info("in addAmenities");
        try {
            String data = components.getData();
            Gson gson = new Gson();
            Object myTypes = (Object)gson.fromJson(data, Object.class);
            List amenitiesList = ((List)((LinkedTreeMap)myTypes).get("facilities"));

            Amenity[] facilities = new Amenity[amenitiesList.size()];

            for(int i = 0; i < amenitiesList.size(); i++){
                Map amenityMap = (Map)amenitiesList.get(i);
                Amenity amenity = new Amenity();
                //amenity.setHotelId(amenityMap.get("hotel_id").toString());
                //amenity.setAmenityId(Integer.valueOf(amenityMap.get("amenity_id").toString()));
                amenity.setAmenityName(amenityMap.get("amenity_id").toString());
                amenity.setAmenityDescription(amenityMap.get("amenity_description").toString());
                //amenity.setAmenityAvailable(Boolean.valueOf(amenityMap.get("is_available").toString()));
                //amenity.setAmenityChargeable(Boolean.valueOf(amenityMap.get("is_chargeable").toString()));
                amenity.setAmenityType(AmenityType.valueOf(amenityMap.get("amenity_type").toString()));
                //amenity.setAmenityPrice(Double.valueOf(amenityMap.get("amenity_charges").toString()));
                facilities[i] = amenity;
            }

            if(facilities != null && facilities.length > 0) {
                //boolean addFacilityStatus = amenityAPI.addFacility(facilities);
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Facilities added succesfully",null));
            }else{
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", "no facilities to add", null));
            }
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }
    }

    private String addServices(Request request) {
        Log.info("in addServices");
        try {
            String data = components.getData();
            Gson gson = new Gson();
            Object myTypes = (Object)gson.fromJson(data, Object.class);
            List servicesList = ((List)((LinkedTreeMap)myTypes).get("services"));

            Service[] services = new Service[servicesList.size()];

            for(int i = 0; i < servicesList.size(); i++){
                Map servicesMap = (Map)servicesList.get(i);
                Service service = new Service();
                service.setHotelId(servicesMap.get("hotel_id").toString());
                service.setServiceId(Integer.valueOf(servicesMap.get("facility_id").toString()));
                service.setServiceName(servicesMap.get("facility_name").toString());
                service.setServiceDescription(servicesMap.get("facility_description").toString());
                service.setServiceAvailable(Boolean.valueOf(servicesMap.get("is_available").toString()));
                service.setServiceChargeable(Boolean.valueOf(servicesMap.get("is_chargeable").toString()));
                //service.setServiceType(ServicesType.valueOf(servicesMap.get("facility_type").toString()));
                service.setServiceCharge(Double.valueOf(servicesMap.get("facility_price").toString()));
                services[i] = service;
            }

            if(services != null && services.length > 0) {
                //boolean addFacilityStatus = servicesAPI.addFacility(services);
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Services added succesfully",null));
            }else{
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", "no services to add", null));
            }
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }
    }

    private String fetchHotelByHotelId(Request request) {
        Log.info("in fetchHotelByHotelId");
        try {
            String jsonString = request.body().toString();
            Hotel hotel = (Hotel) HMSJSONParser.convertJSONToObject(jsonString, Hotel.class);
            String hotelId = hotel.getHotelId();
            hotel = hotelAPI.fetchHotelByHotelId(hotelId);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Hotel successfully added", hotelId));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }
    }

    private String fetchHotelByEmployeeId(Request request) {
        Log.info("in fetchHotelByEmployeeId");
        try {
            String jsonString = request.body().toString();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Employee.class);
            Hotel result = hotelAPI.fetchHotelByEmployeeId(employee.getEmpId());
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Hotel successfully added", null));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }
    }

    private String fetchAllHotels(Request request) {
        Log.info("in fetchAllHotels");
        try {
            List<Hotel> result = hotelAPI.fetchAllHotels();
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "hotels retrieved succsfully", null));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }
    }



    private String addRoomCategory(Request request) {

        // TODO Create RoomCategory
        return null;
    }

    private String addEmployeeDetails(Request request) {
        // TODO Create Employee Details

        return null;
    }

    private String addTaxRules(Request request) {
        // TODO Create Tax Rules

        return null;
    }

    private HmsResponse getHmsResponse(String tokenID, String status, String statusMessage, String hotelId) {
        String hotelJson = null;
        if(hotelId != null){
            Hotel hotel = new Hotel();
            hotel.setHotelId(hotelId);
            HotelAttributes a= new HotelAttributes();
            a.setHotelName("test hotel");
            hotel.setHotelAttributes(a);
            hotelJson = HMSJSONParser.convertObjectToJSON(hotel);
        }
        return new HmsResponse(tokenID, status, statusMessage, hotelJson);
    }
}
