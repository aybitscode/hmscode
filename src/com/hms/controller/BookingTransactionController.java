package com.hms.controller;

import java.text.ParseException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.services.BookingTransactionService;

public class BookingTransactionController {

	BookingTransactionService booking_transaction_service;
	
	public BookingTransactionController()
	{
		booking_transaction_service = new BookingTransactionService();
	}
	
	public BookingTransactionController(DefaultTableModel tableModel, JTable table)
	{
		booking_transaction_service = new BookingTransactionService(tableModel, table);
		
	}
	public void retrieveAll()
	{
		booking_transaction_service.retrieveAll();
	}
	public void retrieveAll(String query, String parameter)
	{
		booking_transaction_service.retrieveAll(query, parameter);
	}
	public void retrieve(String query, String param)
	{
		booking_transaction_service.retrieve(query, param);
	}
	public void updateService(String query1, String query2, String param) throws ParseException
	{
		booking_transaction_service.updateService(query1, query2, param);
	}
	public int updateService(String query1, List<String> list1, List<String> list2, String param) throws ParseException
	{
		return booking_transaction_service.updateService(query1, list1, list2, param);
	}
}