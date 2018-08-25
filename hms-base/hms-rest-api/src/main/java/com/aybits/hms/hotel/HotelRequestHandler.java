package com.aybits.hms.hotel;

import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.beans.Hotel;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

import java.util.List;

public class HotelRequestHandler implements HmsRequestHandler {
    static Logger Log = Logger.getLogger(EmployeeRequestHandler.class);
    HotelAPI hotelAPI = new HotelAPI();

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
            case "/setup/tax-rules":
                message = addTaxRules(request);

        }
        return message;
    }

    private String addFacilites(Request request) {
        return null;
    }


    private String fetchHotelByHotelId(Request request) {
        Log.info("in fetchHotelByHotelId");
        try {
            String jsonString = request.body().toString();
            Hotel hotel = (Hotel) HMSJSONParser.convertJSONToObject(jsonString, Hotel.class);
            String hotelId = hotel.getHotelId();
            hotel = hotelAPI.fetchHotelByHotelId(hotelId);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(hotel, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while fetchHotelByHotelId", false));
        }
    }

    private String fetchHotelByEmployeeId(Request request) {
        Log.info("in fetchHotelByEmployeeId");
        try {
            String jsonString = request.body().toString();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Employee.class);
            Hotel result = hotelAPI.fetchHotelByEmployeeId(employee.getEmpId());
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while fetchHotelByEmployeeId", false));
        }
    }

    private String fetchAllHotels(Request request) {
        Log.info("in fetchAllHotels");
        try {
            List<Hotel> result = hotelAPI.fetchAllHotels();
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while fetchAllHotels", false));
        }
    }

    private String upsertHotel(String hotelJsonString) {
        Log.info("in upsertHotel");
        try {
            Hotel hotel = (Hotel) HMSJSONParser.convertJSONToObject(hotelJsonString, Hotel.class);
            Boolean result = hotelAPI.upsertHotel(hotel);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));


        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while fetchAllHotels", false));
        }
    }

    private String addHotel(Request request) {

        // TODO Create Hotel

        upsertHotel(request.body());


        return null;
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

    private HmsResponse getHmsResponse(Object responseData, boolean result) {
        HmsResponse response;
        if (result) {
            response = new HmsResponse(200, "SUCCESS", responseData);
        } else {
            response = new HmsResponse(401, "FAILED", responseData);
        }
        return response;
    }
}
