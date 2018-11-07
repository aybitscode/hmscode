package com.aybits.hms.init;


import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.arch.util.HmsConfig;
import com.aybits.hms.billing.BillingRequestHandler;
import com.aybits.hms.booking.BookingRequestHandler;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.customer.CustomerRequestHandler;
import com.aybits.hms.feature.FeatureRequestHandler;
import com.aybits.hms.hotel.HotelRequestHandler;
import com.aybits.hms.invoice.InvoiceRequestHandler;
import com.aybits.hms.login.LoginRequestHandler;
import com.aybits.hms.room.RoomCategoryRequestHandler;
import com.aybits.hms.room.RoomRequestHandler;
import com.aybits.hms.setup.HMSRequestHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static spark.Spark.get;
import static spark.Spark.post;

public class HmsRestHttpService {
    static Logger Log = Logger.getLogger(HmsRestHttpService.class);
    private static final String API_PREFIX = "/api";
    private static final String CONTENT_TYPE = "application/json";

    @Autowired
    HMSRequestHandler HMSRequestHandler;
    

    public void registerHttpAPIs() {

        String appName = HmsConfig.getRestProperty("app.name");
        String version = HmsConfig.getRestProperty("app.version");

        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/do/*", HMSRequestHandler);
    }

    private static void registerPostApi(String apiPath, GenericRequestHandler requestHandler) {
        Log.info("Registering post api for "+apiPath);
        post(apiPath, CONTENT_TYPE, (request, response) -> {
            return requestHandler.handleRequest(request, response);
        });
    }

    private static void registerGetApi(String apiPath, GenericRequestHandler requestHandler) {
        Log.info("Registering get api for "+apiPath);
        get(apiPath, CONTENT_TYPE, (request, response) -> {
            return requestHandler.handleRequest(request, response);
        });
    }
}
