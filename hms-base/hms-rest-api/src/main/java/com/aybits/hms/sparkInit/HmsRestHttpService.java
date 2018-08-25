package com.aybits.hms.sparkInit;


import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.bookings.BookingRequestHandler;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.customer.CustomerRequestHandler;
import com.aybits.hms.hotel.HotelRequestHandler;
import com.aybits.hms.login.LoginRequestHandler;
import com.aybits.hms.room.RoomRequestHandler;
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
        registerPostApi("/quick-booking", new BookingRequestHandler());
        registerPostApi("/employee/fetch-all", new EmployeeRequestHandler());
        registerPostApi("/employee/fetch-by-phone", new EmployeeRequestHandler());
        registerPostApi("/employee/add", new EmployeeRequestHandler());
        registerPostApi("/employee/update", new EmployeeRequestHandler());
        registerPostApi("/employee/fetch-by-id", new EmployeeRequestHandler());

        registerPostApi("/room/fetch-by-hotel-id", new RoomRequestHandler());
        registerPostApi("/room/fetch", new RoomRequestHandler());


        registerPostApi("/hotel/fetch-all", new HotelRequestHandler());
        registerPostApi("/hotel/fetch-by-hotel-id", new HotelRequestHandler());
        registerPostApi("/hotel/fetch-by-emp-id", new HotelRequestHandler());
        registerPostApi("/hotel/setup", new HotelRequestHandler());

        registerPostApi("/customer/add", new CustomerRequestHandler());
        registerPostApi("/customer/update", new CustomerRequestHandler());
        registerPostApi("/customer/fetch-by-id", new CustomerRequestHandler());
        registerPostApi("/customer/fetch", new CustomerRequestHandler());
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
