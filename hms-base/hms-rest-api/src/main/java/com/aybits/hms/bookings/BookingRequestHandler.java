package com.aybits.hms.bookings;

import com.aybits.hms.common.HmsRequestHandler;
import spark.Request;
import spark.Response;

public class BookingRequestHandler implements HmsRequestHandler {
    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Booking request handler invoked");
        return null;
    }
}
