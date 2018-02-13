package com.hms.controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.CheckIn;
import com.hms.services.CheckInService;

public class CheckInController {
	public CheckIn obj_checkIn;
	CheckInService checkIn_service;

	public CheckInController(DefaultTableModel tableModel, JTable table)
	{
		checkIn_service = new CheckInService(tableModel, table);
	}
	
	public CheckInController(CheckIn obj_checkIn)
	{
		this.obj_checkIn = obj_checkIn;
		checkIn_service = new CheckInService(obj_checkIn);
	}
	public int submitCheckIn()
	{
		return checkIn_service.submitService();
	}
	public void retrieveAll(String query)
	{
		  checkIn_service.retrieveAll(query);
	}
	
	public void retrieve(String query, String parameter)
	{
		  checkIn_service.retrieveCheckInTransaction(query, parameter);
	}
	public List<String> retrieveBookingIDs(String query, String param)
	{
		return checkIn_service.retrieveBookingIDs(query, param);
	}
}
