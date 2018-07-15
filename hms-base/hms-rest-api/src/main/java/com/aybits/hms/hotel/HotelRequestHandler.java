package com.aybits.hms.hotel;

import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.beans.Hotel;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

import java.util.List;

public class HotelRequestHandler implements HmsRequestHandler {
    static Logger Log = Logger.getLogger(EmployeeRequestHandler.class);
    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Hotel request handler invoked");

        String action = request.pathInfo().split("/")[2];
        String message = "";
        switch (action) {
            case "fetch-all-hotels":
                message = fetchAllHotels(request);
                break;
            case "fetch-hotel-by-hotel-id":
                message = fetchHotelByHotelId(request);
                break;
            case "fetch-hotel-by-emp-id":
                message = fetchHotelByEmployeeId(request);
                break;
            case "setup-hotel":
                message = upsertHotel(request);
                break;
        }
        return message;
    }



    private String fetchHotelByHotelId(Request request) {
        Log.info("in fetchHotelByHotelId");
        try {
            String jsonString = request.body().toString();
            HotelAPI hotelAPI = new HotelAPI();
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
            HotelAPI hotelAPI = new HotelAPI();
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
            HotelAPI hotelAPI = new HotelAPI();
            List<Hotel> result = hotelAPI.fetchAllHotels();
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while fetchAllHotels", false));
        }
    }

    private String upsertHotel(Request request) {
        Log.info("in upsertHotel");
        try {
            HotelAPI hotelAPI = new HotelAPI();
            String jsonString = request.body().toString();
            Hotel hotel = (Hotel) HMSJSONParser.convertJSONToObject(jsonString, Hotel.class);
            Boolean result = hotelAPI.upsertHotel(hotel);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while fetchAllHotels", false));
        }
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
