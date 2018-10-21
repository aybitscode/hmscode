package com.aybits.hms.sparkInit;


import com.aybits.hms.Employee.EmployeeRequestHandler;
import com.aybits.hms.amenity.AmenityRequestHandler;
import com.aybits.hms.billing.BillingRequestHandler;
import com.aybits.hms.booking.BookingRequestHandler;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.customer.CustomerRequestHandler;
import com.aybits.hms.facility.FacilityRequestHandler;
import com.aybits.hms.hotel.HotelRequestHandler;
import com.aybits.hms.invoice.InvoiceRequestHandler;
import com.aybits.hms.login.LoginRequestHandler;
import com.aybits.hms.room.RoomCategoryRequestHandler;
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

        registerPostApi("/login/*", new LoginRequestHandler());
        registerPostApi("/booking/*", new BookingRequestHandler());
        registerPostApi("/employee/*", new EmployeeRequestHandler());
        registerPostApi("/room/*", new RoomRequestHandler());
        registerPostApi("/hotel/*", new HotelRequestHandler());
        registerPostApi("/customer/*", new CustomerRequestHandler());
        registerPostApi("/facility/*",new FacilityRequestHandler());
        registerPostApi("/amenity/*", new AmenityRequestHandler());
        registerPostApi("/room-category/*", new RoomCategoryRequestHandler());
        registerPostApi("/billing/*",new BillingRequestHandler());
        registerPostApi("/invoice/*", new InvoiceRequestHandler());

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
