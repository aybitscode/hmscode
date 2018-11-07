package com.aybits.hms.room;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.room.api.RoomCategoryAPI;
import com.aybits.hms.func.room.beans.RoomCategory;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class RoomCategoryRequestHandler implements GenericRequestHandler {

    static Logger Log = Logger.getLogger(RoomCategoryRequestHandler.class);

    HMSJsonRequestComponents components = null;

    RoomCategoryAPI roomCategoryAPI = new RoomCategoryAPI();


    @Override
    public String getActionString(Request request) {
        return null;
    }

    @Override
    public void validateRequestData(JSONObject dataJson) throws HMSRuntimeException {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
       // return result;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Hotel request handler invoked");



        components = HMSJSONParser.getHmsJsonRequestComponents(request.body());
        String action = getActionString(request);

        String message = "";

        switch (action) {
            case "/fetch/all/room-categories":
                message = fetchAllRoomCategories(request);
                break;
            case "/fetch/room-category":
                message = fetchRoomCategoryById(request);
                break;
            case "/setup/room-category":
                message = setupRoomCategory(request);
                break;
        }
        return message;
    }

    @Override
    public String populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
        return null;
    }

    @Override
    public String populateGenericErrorResponse(Exception e, String tokenId) {
        return null;
    }

    private String fetchAllRoomCategories(Request request){
        return null;
    }

    private String fetchRoomCategoryById(Request request){
        return null;
    }

    private String setupRoomCategory(Request request){
        Log.info("[Enter]::setupRoomCateogry");
        Boolean isRoomCategoryAdded = false;
        String response = null;
        try {
            JSONObject jsonObject = new JSONObject(components.getData());
            JSONObject roomCategoryJSON = jsonObject.getJSONObject("room_category");
            RoomCategory roomCategory = (RoomCategory) HMSJSONParser.convertJSONToObject(roomCategoryJSON.toString(), RoomCategory.class);

            validateRequest(roomCategory);

            isRoomCategoryAdded = roomCategoryAPI.addRoomCategory(roomCategory);

            response =  HMSJSONParser.convertObjectToJSON(getHmsResponse("ABCD12345", "SUCCESS", "Room-Category successfully added", isRoomCategoryAdded));
        } catch (HMSException e) {
            response = HMSJSONParser.convertObjectToJSON(getHmsResponse("ABCD12345", "FAILED", e.getMessage(), null));
        }finally{
            Log.info("[Exit]::setupRoomCateogry");
            return response;
        }
    }


    public String validateRequest(RoomCategory roomCategory) throws HMSException{
        return null;
    }


}
