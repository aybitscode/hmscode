package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Booking;
import com.hms.services.BookingCancelService;
import com.hms.services.BookingTransactionService;

public class BookingCancelController {
	private BookingCancelService objCancelService;
	
	public BookingCancelController()
	{
		objCancelService = new BookingCancelService();
	}
	public BookingCancelController(DefaultTableModel tableModel, JTable table)
	{
		objCancelService = new BookingCancelService(tableModel, table);
		
	}
	public BookingCancelController(Booking obj_booking)
	{
		objCancelService = new BookingCancelService(obj_booking);
	}
	public int submitCancelService(String bookingId, String roomNumber)
	{
		return objCancelService.submitService(bookingId, roomNumber);
		
	}
	public Booking retrieveBookingDetails(String bookingID, String roomNumber)
	{
		
		return objCancelService.retrieveBookings(bookingID, roomNumber);
	}
	public void retrieve(String query, String param)
	{
		objCancelService.retrieve(query, param);
	}
	public void retrieveAll(String query, String parameter)
	{
		objCancelService.retrieveAll(query, parameter);
	}
}
