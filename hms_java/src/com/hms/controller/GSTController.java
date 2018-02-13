package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.GST;
import com.hms.services.GSTService;

public class GSTController {
	public GST obj_gst;
	GSTService gst_service;
	
	public GSTController()
	{
		gst_service = new GSTService();
	}
	public GSTController(GST obj_gst)
	{
		this.obj_gst = obj_gst;
		gst_service = new GSTService(obj_gst);
	}
	public GSTController(DefaultTableModel tableModel, JTable table) {
		// TODO Auto-generated constructor stub
		gst_service = new GSTService(tableModel, table);
	}

	public int submitService(String query)
	{
		return gst_service.submitService(query);
	}
	public String retrieveGSTName(String query)
	{
		return gst_service.retrieveGSTName(query);
	}
	public String generateID() {
		// TODO Auto-generated method stub
		return gst_service.generateGSTId();
	}
	public void retrieveAll(String query)
	{
		gst_service.retrieveAll(query);
	}
	public void retrieve(String query, String param)
	{
		gst_service.retrieve(query, param);
	}
	public void updateService(String query1, String query2, String param)
	{
		gst_service.updateService(query1, query2, param);
	}
}