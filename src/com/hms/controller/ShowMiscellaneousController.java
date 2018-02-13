package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.services.ShowMiscellaneousService;

public class ShowMiscellaneousController {
	private ShowMiscellaneousService mis_service;
	
	public ShowMiscellaneousController(DefaultTableModel tableModel, JTable table)
	{
		mis_service = new ShowMiscellaneousService(tableModel, table);
	}
	public void retriveAll(String query, String param)
	{
		mis_service.retrieveServices(query, param);
	}

}
