package com.aybits.hms.setup;

import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.feature.FeatureRequestHandler;
import com.aybits.hms.hotel.HotelRequestHandler;
import com.aybits.hms.room.RoomCategoryRequestHandler;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class SetupRequestHandler implements HMSRequestHandler {

    static Logger Log = Logger.getLogger(SetupRequestHandler.class);
    HMSRequestHandler hmsRequestHandler = null;



    public ValidationResult validateRequestData(JSONObject dataJSON) throws HMSException {
        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Setup Request handler invoked");

        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());


        String operation = components.getOperation();
        String entity = components.getEntity();
        String data = components.getData();
        String action = operation + "/" + entity;
        String tokenId = components.getTokenId();

        JSONObject dataJSON = null;

        ValidationResult result = null;
        try {
            dataJSON = new JSONObject(data);
            result = validateRequestData(dataJSON);
        }catch(HMSException he){

        }catch(JSONException je){

        }
        if (result != null) {
            return result.getMessage();
        }


        String message = "";

        switch (entity) {
            case "hotel":
                message = setupHotel(request,response);
                break;
            case "features":
                message = setupFeatures(request,response);
                break;
            case "room-category":
                message = setupRoomCategory(request,response);
                break;
            case "employee":
                message = setupEmployeeDetails(request,response);
                break;
        }
        return message;
    }



    private String setupHotel(Request request,Response response){
        hmsRequestHandler = new HotelRequestHandler();
        return hmsRequestHandler.handleRequest(request,response);

    }

    private String setupFeatures(Request request,Response response){
        hmsRequestHandler = new FeatureRequestHandler();
        return hmsRequestHandler.handleRequest(request,response);
    }

    private String setupRoomCategory(Request request,Response response){
        hmsRequestHandler = new RoomCategoryRequestHandler();
        return hmsRequestHandler.handleRequest(request,response);

    }

    private String setupEmployeeDetails(Request request,Response response){
        hmsRequestHandler = new EmployeeRequestHandler();
        return hmsRequestHandler.handleRequest(request,response);
    }



}
