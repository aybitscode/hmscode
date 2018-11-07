package com.aybits.hms.setup;

import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.feature.FeatureRequestHandler;
import com.aybits.hms.hotel.HotelRequestHandler;
import com.aybits.hms.room.RoomCategoryRequestHandler;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;

public class SetupRequestHandler implements GenericRequestHandler {

    static Logger Log = Logger.getLogger(SetupRequestHandler.class);

    @Autowired
    HotelRequestHandler hotelRequestHandler;

    @Autowired
    FeatureRequestHandler featureRequestHandler;

    @Autowired
    RoomCategoryRequestHandler roomCategoryRequestHandler;

    @Autowired
    EmployeeRequestHandler employeeRequestHandler;


    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {
    }

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Setup Request handler invoked");

        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());


        String entity = components.getEntity();
        String data = components.getData();

        JSONObject dataJSON = null;

        ValidationResult result = null;
        try {
            dataJSON = new JSONObject(data);
            validateRequestData(dataJSON);
        } catch (HMSRuntimeException he) {

        } catch (JSONException je) {

        }
        if (result != null) {
            return result.getMessage();
        }


        String message = "";
        GenericRequestHandler hmsRequestHandler = null;

        switch (entity) {
            case "hotel":
                hmsRequestHandler = hotelRequestHandler;
                break;
            case "features":
                hmsRequestHandler = featureRequestHandler;
                break;
            case "room-category":
                hmsRequestHandler = roomCategoryRequestHandler;
                break;
            case "employee":
                hmsRequestHandler = employeeRequestHandler;
                break;
        }
        message = hmsRequestHandler.handleRequest(request, response);
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

}
