package com.aybits.hms.setup;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.hotel.HotelRequestHandler;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

public class SetupRequestHandler implements HmsRequestHandler {

    static Logger Log = Logger.getLogger(SetupRequestHandler.class);



    @Override
    public ValidationResult validateRequestData(Request request) throws HMSException {



        return null;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Setup Request handler invoked");

        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());

        ValidationResult result = null;
        try {
            result = validateRequestData(request);
        }catch(HMSException he){

        }
        if (result != null) {
            return result.getMessage();
        }

        String entity = components.getEntity();

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

        HotelRequestHandler hotelRequestHandler = new HotelRequestHandler();
        return hotelRequestHandler.handleRequest(request,response);

    }

    private String setupFeatures(Request request,Response response){

        return null;
    }

    private String setupRoomCategory(Request request,Response response){
        return null;
    }

    private String setupEmployeeDetails(Request request,Response response){
        return null;
    }



}
