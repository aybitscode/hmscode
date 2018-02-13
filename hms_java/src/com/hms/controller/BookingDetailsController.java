package com.hms.controller;

import com.hms.model.Booking;
import com.hms.model.ReportDetails;
import com.hms.services.BookingDetailsService;

public class BookingDetailsController {

	BookingDetailsService booking_service;
	
	public BookingDetailsController()
	{
		booking_service = new BookingDetailsService();
	}
	public ReportDetails retrieve(String query1, String query2, String query3, String query4, String param)
	{
		return booking_service.retrieve(query1, query2, query3, query4, param);		
	}
	
	public ReportDetails retrieveBookedCancel(String query1, String query2, String param)
	{
		return booking_service.retrieveBookedCancel(query1,query2, param);		
	}

}