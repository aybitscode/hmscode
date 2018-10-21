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
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelAttributes;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.aybits.hms.func.room.api.RoomCategoryAPI;
import com.aybits.hms.func.service.api.ServiceAPI;
import com.aybits.hms.func.service.beans.Service;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Map;

public class HotelRequestHandler implements HmsRequestHandler {
    static Logger Log = Logger.getLogger(EmployeeRequestHandler.class);
    HotelAPI hotelAPI = new HotelAPI();
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
                message = setupHotel(request);
                break;
            case "/setup/facilities":

                message = addAmenities(request);
                message = addServices(request);
                break;
            case "/setup/tax-rules":
                message = addTaxRules(request);
                break;
        }
        return message;
    }

    private String setupHotel(Request request) {
        Log.info("in setupHotel");
        Boolean isSetupSuccessful = false;
        String hotelId = null;
        try {
            JSONObject jsonObject = new JSONObject(components.getData());
            JSONObject hotelJSON = jsonObject.getJSONObject("hotel");
            JSONObject hotelRegistrationJSON = jsonObject.getJSONObject("hotel_registration_data");
            HotelRegistrationData hotelRegistrationData = (HotelRegistrationData) HMSJSONParser.convertJSONToObject(hotelRegistrationJSON.toString(),HotelRegistrationData.class);
            Hotel hotel = (Hotel) HMSJSONParser.convertJSONToObject(hotelJSON.toString(), Hotel.class);

            hotelId = hotelAPI.addHotel(hotel);
            hotelRegistrationData.setHotelId(hotelId);
            hotelAPI.addHotelRegistrationData(hotelRegistrationData);

            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Hotel successfully added", hotelId));
        } catch (Exception e) {
            e.printStackTrace();
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "FAILED", e.getMessage(), null));
        }finally{
            return hotelId;
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
                //boolean addFacilityStatus = amenityAPI.addNewFacility(facilities);
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
                service.setServiceId(servicesMap.get("facility_id").toString());
                service.setServiceName(servicesMap.get("facility_name").toString());
                service.setServiceDescription(servicesMap.get("facility_description").toString());
                service.setIsAvailable(Boolean.valueOf(servicesMap.get("is_available").toString()));
                service.setIsChargeable(Boolean.valueOf(servicesMap.get("is_chargeable").toString()));
                //service.setServiceType(ServiceType.valueOf(servicesMap.get("facility_type").toString()));
                service.setServiceCharge(Double.valueOf(servicesMap.get("facility_price").toString()));
                services[i] = service;
            }

            if(services != null && services.length > 0) {
                //boolean addFacilityStatus = servicesAPI.addNewFacility(services);
                return HMSJSONParser.convertObjectToJSON(getHmsResponse(null, "SUCCESS", "Service added succesfully",null));
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




    private String addEmployeeDetails(Request request) {
        // TODO Create Employee Details

        return null;
    }

    private String addTaxRules(Request request) {
        // TODO Create Tax Rules

        return null;
    }


    @Override
    public HmsResponse getHmsResponse(String tokenID, String status, String statusMessage, Object hotelId) {
        String hotelJson = null;
        if(hotelId != null){
            Hotel hotel = new Hotel();
            String hotelIdStr = (String)hotelId;
            hotel.setHotelId(hotelIdStr);
            HotelAttributes a= new HotelAttributes();
            a.setHotelName("test hotel");
            hotel.setHotelAttributes(a);
            hotelJson = HMSJSONParser.convertObjectToJSON(hotel);
        }
        return new HmsResponse(tokenID, status, statusMessage, hotelJson);
    }
}
