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
    HotelRequestHandler hotelRequestHandler;

    @Autowired
    SetupRequestHandler setupRequestHandler;

    @Autowired
    CustomerRequestHandler customerRequestHandler;

    @Autowired
    BookingRequestHandler bookingRequestHandler;

    @Autowired
    EmployeeRequestHandler employeeRequestHandler;

    @Autowired
    InvoiceRequestHandler invoiceRequestHandler;

    @Autowired
    RoomCategoryRequestHandler roomCategoryRequestHandler;

    @Autowired
    AmenityRequestHandler amenitiesRequestHandler;

    @Autowired
    FacilityRequestHandler facilitiesRequestHandler;

    @Autowired
    RoomRequestHandler roomRequestHandler;

    @Autowired
    LoginRequestHandler loginRequestHandler;

    @Autowired
    BillingRequestHandler billingRequestHandler;



    public void registerHttpAPIs() {

        String appName = HmsConfig.getRestProperty("app.name");
        String version = HmsConfig.getRestProperty("app.version");

        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/setup/*", setupRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/login/*", loginRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/booking/*", bookingRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/employee/*",employeeRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/room/*", roomRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/hotel/*", hotelRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/customer/*", customerRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/facility/*",facilitiesRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/amenity/*", amenitiesRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/room-category/*", roomCategoryRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/billing/*",billingRequestHandler);
        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/invoice/*", invoiceRequestHandler);

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
