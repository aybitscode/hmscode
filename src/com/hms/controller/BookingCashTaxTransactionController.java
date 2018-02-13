//package com.hms.controller;
//
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import com.hms.services.BookingCashTaxTransactionService;
//
//public class BookingCashTaxTransactionController {
//
//	BookingCashTaxTransactionService booking_cash_tax_transaction_service;
//	
//	public BookingCashTaxTransactionController(DefaultTableModel tableModel, JTable table)
//	{
//		booking_cash_tax_transaction_service = new BookingCashTaxTransactionService(tableModel, table);
//		
//	}
//
//	public void retrieveAllTransactions(String query, String parameter)
//	{
//		booking_cash_tax_transaction_service.retrieveAllTransactions(query, parameter);
//	}
//	public void retrieveTransaction(String query, String bookingID)
//	{
//		booking_cash_tax_transaction_service.retrieveTransaction(query, bookingID);
//	}
//	
//}
