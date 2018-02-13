package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.services.BookedTransactionService;

public class BookedTransactionController {

	BookedTransactionService booked_transaction_service;
	
	public BookedTransactionController(DefaultTableModel tableModel, JTable table)
	{
		booked_transaction_service = new BookedTransactionService(tableModel, table);
		
	}
	public void retrieveAllBookingsStatus(String query, String status)
	{
		  try {
			booked_transaction_service.retrieveAllBookingsStatus(query, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void retrieveBooking(String query, String parameter)
	{
		booked_transaction_service.retrieveBooking(query, parameter);
	}
	
}
