package com.hms.controller;

import java.sql.Timestamp;
import java.util.List;

import com.hms.model.Booking;
import com.hms.model.Room;
import com.hms.services.BookingService;

public class BookingController {
	public Booking obj_booking;
	BookingService booking_service;
	
	public BookingController()
	{
		booking_service = new BookingService();
	}
	public BookingController(Booking obj_booking)
	{
		this.obj_booking = obj_booking;
		booking_service = new BookingService(obj_booking);
	}
	public int submitBooking()
	{
		return booking_service.submitService();
	}
	public int submitMultipleBooking(List<Room> listRooms, Timestamp booked_date, Timestamp checkout_date, String days, String mobile)
	{
		return booking_service.submitMultiService(listRooms, booked_date, checkout_date, days, mobile);
	}
	public void retrieveBookings()
	{
		  booking_service.retrieveBookings();
	}
	public int checkRoomAvailability(java.sql.Timestamp checkout_date, String roomDoorNumber)
	{
		return booking_service.checkRoomAvailability(checkout_date, roomDoorNumber);
	}
	public List<Room> multipleFreeRooms(String query, String query1, String checkinDate, String checkoutDate, String status)
	{
		return booking_service.multipleFreeRooms(query, query1, checkinDate, checkoutDate, status);
	}
}
