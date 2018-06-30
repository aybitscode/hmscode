package com.aybits.hms.sparkInit;


import com.aybits.hms.bookings.BookingRequestHandler;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.customer.CustomerRequestHandler;
import com.aybits.hms.login.LoginRequestHandler;
import org.apache.log4j.Logger;


import static spark.Spark.get;
import static spark.Spark.post;

public class HmsRestHttpService {
    static Logger Log = Logger.getLogger(HmsRestHttpService.class);
    private static final String API_PREFIX = "";
    private static final String CALLBACK_API_PREFIX = "";
    private static final String CONTENT_TYPE = "application/json";

    public void registerHttpAPIs() {
        registerPostApi("/login", new LoginRequestHandler());

        registerPostApi("/booking", new BookingRequestHandler());
    }

    private static void registerPostApi(String apiPath, HmsRequestHandler requestHandler) {
        Log.info("Registering post api for "+apiPath);
        post(apiPath, CONTENT_TYPE, (request, response) -> {
            return requestHandler.handleRequest(request, response);
        });
    }

    private static void registerGetApi(String apiPath, HmsRequestHandler requestHandler) {
        Log.info("Registering get api for "+apiPath);
        get(apiPath, CONTENT_TYPE, (request, response) -> {
            return requestHandler.handleRequest(request, response);
        });
    }
}
