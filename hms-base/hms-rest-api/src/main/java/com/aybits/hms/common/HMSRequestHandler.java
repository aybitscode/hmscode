package com.aybits.hms.common;

import com.aybits.hms.Employee.EmployeeRequestProcessor;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.arch.util.HmsConfig;
import com.aybits.hms.billing.BillingRequestProcessor;
import com.aybits.hms.booking.BookingRequestProcessor;
import com.aybits.hms.customer.CustomerRequestProcessor;
import com.aybits.hms.feature.FeatureRequestProcessor;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.hotel.HotelRequestProcessor;
import com.aybits.hms.invoice.InvoiceRequestProcessor;
import com.aybits.hms.login.LoginRequestProcessor;
import com.aybits.hms.room.RoomCategoryRequestProcessor;
import com.aybits.hms.room.RoomRequestProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;

public class HMSRequestHandler implements GenericRequestHandler {

    static Logger Log = Logger.getLogger(HMSRequestHandler.class);


    @Autowired
    LoginRequestProcessor loginRequestProcessor;

    @Autowired
    BookingRequestProcessor bookingRequestProcessor;

    @Autowired
    EmployeeRequestProcessor employeeRequestProcessor;

    @Autowired
    RoomRequestProcessor roomRequestProcessor;

    @Autowired
    HotelRequestProcessor hotelRequestProcessor;

    @Autowired
    CustomerRequestProcessor customerRequestProcessor;

    @Autowired
    RoomCategoryRequestProcessor roomCategoryRequestProcessor;

    @Autowired
    FeatureRequestProcessor featureRequestProcessor;

    @Autowired
    BillingRequestProcessor billingRequestProcessor;

    @Autowired
    InvoiceRequestProcessor invoiceRequestProcessor;



    public String handleRequest(Request request, Response response) {
        Log.info("Setup Request handler invoked");

        ValidationResult validationResult = validateRequest(request);

        if(validationResult.getMessage() != null){
            return validationResult.getMessage();
        }


        HMSRequest hmsRequest = validationResult.getHmsRequest();
        String entity = hmsRequest.getEntity();
        String message = null;
        HMSRequestProcessor requestProcessor = null;

        switch (entity) {
            case "login":
                requestProcessor = loginRequestProcessor;
                break;
            case "hotel":
                requestProcessor = hotelRequestProcessor;
                break;
            case "features":
                requestProcessor = featureRequestProcessor;
                break;
            case "room-category":
                requestProcessor = roomCategoryRequestProcessor;
                break;
            case "employee":
                requestProcessor = employeeRequestProcessor;
                break;
            case "room":
                requestProcessor = roomRequestProcessor;
                break;
            case "booking":
                requestProcessor = bookingRequestProcessor;
                break;
            case "billing":
                requestProcessor = billingRequestProcessor;
                break;
            case "invoice":
                requestProcessor = invoiceRequestProcessor;
                break;
            case "customer":
                requestProcessor = customerRequestProcessor;
        }
        HMSResponse hmsResponse = requestProcessor.processRequest(hmsRequest);
        return HMSJSONParser.convertObjectToJSON(hmsResponse);
    }



    private ValidationResult validateRequest(Request request){
        ValidationResult requestValidationResult = null;
        HMSJsonRequestComponents components = null;
        //Todo do base request validation result here
        Log.info("doing base structure validation");
        String action = getActionString(request);

        if (action == null) {
            requestValidationResult.setCode(001);
            requestValidationResult.setMessage("{'status':'"+ HMSAPIServiceConstants.HMS_RESPONSE_FAILURE+"','message':'Invalid request URL.'}");
        }

        String hmsRequestJSON = request.body();
        HMSRequest hmsRequest = (HMSRequest)HMSJSONParser.convertJSONToObject(hmsRequestJSON,HMSRequest.class);

        if (hmsRequest == null) {
            requestValidationResult.setCode(002);
            requestValidationResult.setMessage("{'status':'"+HMSAPIServiceConstants.HMS_RESPONSE_FAILURE+"','message':'Invalid request body.'}");
        }else {
            requestValidationResult.setHmsRequest(hmsRequest);
        }
        return requestValidationResult;
    }



    private String getActionString(Request request) {
        String action = null;
        try {
            String str = request.pathInfo();
            String appName = HmsConfig.getRestProperty("app.name");
            String appVersion = HmsConfig.getRestProperty("app.version");
            String appPrefix = "api";
            StringBuffer sb = new StringBuffer();
            sb.append("/");
            sb.append(appName);
            sb.append("/");
            sb.append(appVersion);
            sb.append("/");
            sb.append(appPrefix);
            sb.append("/");
            String url = sb.toString();

            action = str.substring(url.length());

        } catch (Exception e) {
            Log.error(e.getMessage());
        }
        return action;
    }
    





}
