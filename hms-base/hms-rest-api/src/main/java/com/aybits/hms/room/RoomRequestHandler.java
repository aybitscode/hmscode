package com.aybits.hms.room;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.room.api.RoomAPI;
import com.aybits.hms.func.room.beans.Room;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;

public class RoomRequestHandler implements HmsRequestHandler {
    static Logger Log = Logger.getLogger(RoomRequestHandler.class);

    @Override
    public ValidationResult validateRequestData(JSONObject dataJSON) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        return result;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Room request handler invoked");

        ValidationResult result = validateRequest(request);
        if (result != null) {
            return result.getMessage();
        }

        String action = request.pathInfo().split("/")[2];
        String message = "";
        switch (action) {
            case "fetch-rooms-by-hotel-id":
                message = fetchRoomsByHotel(request);
                break;
            case "fetch-com.aybits.hms.room-details":
                message = fetchRoomDetails(request);
                break;
        }
        return message;
    }

    private String fetchRoomsByHotel(Request request) {
        Log.info("in fetchRoomsByHotel");
        try {
            String jsonString = request.body().toString();
            RoomAPI roomAPI = new RoomAPI();
            Room room = (Room) HMSJSONParser.convertJSONToObject(jsonString, Room.class);
            List<Room> result = roomAPI.fetchRoomsByHotel(room.getHotelId());
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting Rooms by hotel id", false));
        }
    }

    private String fetchRoomDetails(Request request) {
        Log.info("in fetchRoomDetails");
        try {
            String jsonString = request.body().toString();
            RoomAPI roomAPI = new RoomAPI();
            Room room = (Room) HMSJSONParser.convertJSONToObject(jsonString, Room.class);
            Room result = roomAPI.fetchRoomDetails(room.getRoomId());
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting Room by com.aybits.hms.room id", false));
        }
    }

    private HmsResponse getHmsResponse(Object responseData, boolean result) {
        HmsResponse response = null;
        /*if (result) {
            response = new HmsResponse(200, "SUCCESS", responseData, null, "SUCCESS");
        } else {
            response = new HmsResponse(200, "FAILED", responseData, null, "FAILED");
        }*/
        return response;
    }
}
