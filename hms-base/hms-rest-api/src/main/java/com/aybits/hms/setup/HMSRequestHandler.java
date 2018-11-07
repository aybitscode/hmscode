package com.aybits.hms.setup;

import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.billing.BillingRequestHandler;
import com.aybits.hms.booking.BookingRequestHandler;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.customer.CustomerRequestHandler;
import com.aybits.hms.feature.FeatureRequestHandler;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.hotel.HotelRequestHandler;
import com.aybits.hms.invoice.InvoiceRequestHandler;
import com.aybits.hms.login.LoginRequestHandler;
import com.aybits.hms.room.RoomCategoryRequestHandler;
import com.aybits.hms.room.RoomRequestHandler;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;

public class HMSRequestHandler implements GenericRequestHandler {

    static Logger Log = Logger.getLogger(HMSRequestHandler.class);


    @Autowired
    LoginRequestHandler loginRequestHandler;

    @Autowired
    BookingRequestHandler bookingRequestHandler;

    @Autowired
    EmployeeRequestHandler employeeRequestHandler;

    @Autowired
    RoomRequestHandler roomRequestHandler;

    @Autowired
    HotelRequestHandler hotelRequestHandler;

    @Autowired
    CustomerRequestHandler customerRequestHandler;

    @Autowired
    RoomCategoryRequestHandler roomCategoryRequestHandler;

    @Autowired
    FeatureRequestHandler featureRequestHandler;

    @Autowired
    BillingRequestHandler billingRequestHandler;

    @Autowired
    InvoiceRequestHandler invoiceRequestHandler;



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
        GenericRequestHandler requestHandler = null;

        switch (entity) {
            case "login":
                requestHandler = loginRequestHandler;
                break;
            case "hotel":
                requestHandler = hotelRequestHandler;
                break;
            case "features":
                requestHandler = featureRequestHandler;
                break;
            case "room-category":
                requestHandler = roomCategoryRequestHandler;
                break;
            case "employee":
                requestHandler = employeeRequestHandler;
                break;
            case "room":
                requestHandler = roomRequestHandler;
                break;
            case "booking":
                requestHandler = bookingRequestHandler;
                break;
            case "billing":
                requestHandler = billingRequestHandler;
                break;
            case "invoice":
                requestHandler = invoiceRequestHandler;
                break;
        }
        message = requestHandler.handleRequest(request, response);
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

    private ValidationResult validateRequest(Request request) {
        ValidationResult requestValidationResult = null;
        HMSJsonRequestComponents components = null;
        //Todo do base request validation result here
        Log.info("doing base structure validation");
        String action = getActionString(request);

        if (action == null) {
            requestValidationResult.setCode(001);
            requestValidationResult.setMessage("{'status':'"+ HMSAPIServiceConstants.HMS_RESPONSE_FAILURE+"','message':'Invalid request URL.'}");
        }


        components = HMSJSONParser.getHmsJsonRequestComponents(request.body());

        if (components == null) {
            requestValidationResult.setCode(002);
            requestValidationResult.setMessage("{'status':'"+HMSAPIServiceConstants.HMS_RESPONSE_FAILURE+"','message':'Invalid request body.'}");
        }

        return requestValidationResult;
    }

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {

    }



}
