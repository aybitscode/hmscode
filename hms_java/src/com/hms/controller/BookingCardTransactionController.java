package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.services.BookingCardTransactionService;

public class BookingCardTransactionController {

	BookingCardTransactionService booking_card_transaction_service;
	
	public BookingCardTransactionController(DefaultTableModel tableModel, JTable table)
	{
		booking_card_transaction_service = new BookingCardTransactionService(tableModel, table);
		
	}

	public void retrieveAllTransactions(String query, String parameter)
	{
		booking_card_transaction_service.retrieveAllTransactions(query, parameter);
	}
	public void retrieveTransaction(String query, String bookingID)
	{
		booking_card_transaction_service.retrieveTransaction(query, bookingID);
	}
	
}
