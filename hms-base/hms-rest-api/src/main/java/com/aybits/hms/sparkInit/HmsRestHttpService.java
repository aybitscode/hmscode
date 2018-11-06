package com.aybits.hms.sparkInit;


import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.amenity.AmenityRequestHandler;
import com.aybits.hms.arch.util.HmsConfig;
import com.aybits.hms.billing.BillingRequestHandler;
import com.aybits.hms.booking.BookingRequestHandler;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.customer.CustomerRequestHandler;
import com.aybits.hms.feature.FacilityRequestHandler;
import com.aybits.hms.hotel.HotelRequestHandler;
import com.aybits.hms.invoice.InvoiceRequestHandler;
import com.aybits.hms.login.LoginRequestHandler;
import com.aybits.hms.room.RoomCategoryRequestHandler;
import com.aybits.hms.room.RoomRequestHandler;
import com.aybits.hms.setup.SetupRequestHandler;
import org.apache.log4j.Logger;

import static spark.Spark.get;
import static spark.Spark.post;

public class HmsRestHttpService {
    static Logger Log = Logger.getLogger(HmsRestHttpService.class);
    private static final String API_PREFIX = "/api";
    private static final String CONTENT_TYPE = "application/json";

    public void registerHttpAPIs() {

        String appName = HmsConfig.getRestProperty("app.name");
        String version = HmsConfig.getRestProperty("app.version");

        registerPostApi(appName+"/"+version+API_PREFIX+"/setup/*", new SetupRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/login/*", new LoginRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/booking/*", new BookingRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/employee/*", new EmployeeRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/room/*", new RoomRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/hotel/*", new HotelRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/customer/*", new CustomerRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/facility/*",new FacilityRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/amenity/*", new AmenityRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/room-category/*", new RoomCategoryRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/billing/*",new BillingRequestHandler());
        registerPostApi(appName+"/"+version+API_PREFIX+"/invoice/*", new InvoiceRequestHandler());

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
