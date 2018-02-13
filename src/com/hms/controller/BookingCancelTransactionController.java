package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.services.BookingCancelTransactionService;

public class BookingCancelTransactionController {

	BookingCancelTransactionService booking_cancel_transaction_service;
	
	public BookingCancelTransactionController(DefaultTableModel tableModel, JTable table)
	{
		booking_cancel_transaction_service = new BookingCancelTransactionService(tableModel, table);
		
	}
	public void retrieveAllBookingsStatus(String query, String status)
	{
		  booking_cancel_transaction_service.retrieveAllBookingsStatus(query, status);
	}
	public void retrieveBooking(String query, String parameter)
	{
		booking_cancel_transaction_service.retrieveBooking(query, parameter);
	}
	
}
