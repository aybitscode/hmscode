package com.aybits.hms.room;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.room.api.RoomCategoryAPI;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

public class RoomCategoryRequestHandler implements HmsRequestHandler {

    static Logger Log = Logger.getLogger(RoomCategoryRequestHandler.class);

    HMSJsonRequestComponents components = null;

    RoomCategoryAPI roomCategoryAPI = new RoomCategoryAPI();


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

    private String fetchAllRoomCategories(Request request){
        return null;
    }

    private String fetchRoomCategoryById(Request request){
        return null;
    }

    private String setupRoomCategory(Request request){
        return null;
    }


}
