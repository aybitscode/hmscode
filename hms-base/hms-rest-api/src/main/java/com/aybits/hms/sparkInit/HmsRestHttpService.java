package com.aybits.hms.sparkInit;


import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.amenity.AmenityRequestHandler;
import com.aybits.hms.arch.util.HmsConfig;
import com.aybits.hms.billing.BillingRequestHandler;
import com.aybits.hms.booking.BookingRequestHandler;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.customer.CustomerRequestHandler;
import com.aybits.hms.facility.FacilityRequestHandler;
import com.aybits.hms.hotel.HotelRequestHandler;
import com.aybits.hms.invoice.InvoiceRequestHandler;
import com.aybits.hms.login.LoginRequestHandler;
import com.aybits.hms.room.RoomCategoryRequestHandler;
import com.aybits.hms.room.RoomRequestHandler;
import com.aybits.hms.setup.SetupRequestHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static spark.Spark.get;
import static spark.Spark.post;

public class HmsRestHttpService {
    static Logger Log = Logger.getLogger(HmsRestHttpService.class);
    private static final String API_PREFIX = "/api";
    private static final String CONTENT_TYPE = "application/json";

    @Autowired
    SetupRequestHandler setupRequestHandler;

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
    FacilityRequestHandler facilityRequestHandler;

    @Autowired
    AmenityRequestHandler amenityRequestHandler;

    @Autowired
    RoomCategoryRequestHandler roomCategoryRequestHandler;

    @Autowired
    BillingRequestHandler billingRequestHandler;

    @Autowired
    InvoiceRequestHandler invoiceRequestHandler;

    public void registerHttpAPIs() {

        String appName = HmsConfig.getRestProperty("app.name");
        String version = HmsConfig.getRestProperty("app.version");

        registerPostApi(appName+"/"+version+API_PREFIX+"/setup/*", setupRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/login/*", loginRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/booking/*", bookingRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/employee/*", employeeRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/room/*", roomRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/hotel/*", hotelRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/customer/*", customerRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/facility/*",facilityRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/amenity/*", amenityRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/room-category/*", roomCategoryRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/billing/*",billingRequestHandler);
        registerPostApi(appName+"/"+version+API_PREFIX+"/invoice/*", invoiceRequestHandler);

    }

    private static void registerPostApi(String apiPath, HMSRequestHandler requestHandler) {
        Log.info("Registering post api for "+apiPath);
        post(apiPath, CONTENT_TYPE, (request, response) -> {
            return requestHandler.handleRequest(request, response);
        });
    }

    private static void registerGetApi(String apiPath, HMSRequestHandler requestHandler) {
        Log.info("Registering get api for "+apiPath);
        get(apiPath, CONTENT_TYPE, (request, response) -> {
            return requestHandler.handleRequest(request, response);
        });
    }
}
