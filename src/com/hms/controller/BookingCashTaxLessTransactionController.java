//package com.hms.controller;
//
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import com.hms.services.BookingCashTaxLessTransactionService;
//
//public class BookingCashTaxLessTransactionController {
//
//	BookingCashTaxLessTransactionService booking_cash_tax_less_transaction_service;
//	
//	public BookingCashTaxLessTransactionController(DefaultTableModel tableModel, JTable table)
//	{
//		booking_cash_tax_less_transaction_service = new BookingCashTaxLessTransactionService(tableModel, table);
//		
//	}
//
//	public void retrieveAllTransactions(String query, String parameter)
//	{
//		booking_cash_tax_less_transaction_service.retrieveAllTransactions(query, parameter);
//	}
//	public void retrieveTransaction(String query, String bookingID)
//	{
//		booking_cash_tax_less_transaction_service.retrieveTransaction(query, bookingID);
//	}
//	
//}
