package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.services.BookingCashTransactionService;

public class BookingCashTransactionController {

	BookingCashTransactionService booking_cash_transaction_service;
	
	public BookingCashTransactionController(DefaultTableModel tableModel, JTable table)
	{
		booking_cash_transaction_service = new BookingCashTransactionService(tableModel, table);
		
	}

	public void retrieveAllTransactions(String query, String parameter)
	{
		booking_cash_transaction_service.retrieveAllTransactions(query, parameter);
	}
	public void retrieveTransaction(String query, String bookingID)
	{
		booking_cash_transaction_service.retrieveTransaction(query, bookingID);
	}
	
}
