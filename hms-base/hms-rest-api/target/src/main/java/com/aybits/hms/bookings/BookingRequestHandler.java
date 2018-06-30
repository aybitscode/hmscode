package com.aybits.hms.bookings;

import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.customer.CustomerRequestHandler;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

public class BookingRequestHandler implements HmsRequestHandler {
    static Logger Log = Logger.getLogger(BookingRequestHandler.class);
    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Booking request handler invoked");
        return null;
    }
}
