package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.RoomFacilities;
import com.hms.services.RoomFacilitiesService;

public class RoomFacilitiesController {
	public RoomFacilities objFacilities;
	RoomFacilitiesService objService;
	public RoomFacilitiesController() {
		// TODO Auto-generated constructor stub
		objService = new RoomFacilitiesService();
	}
	public RoomFacilitiesController(RoomFacilities obj_roomFacilities)
	{
		this.objFacilities = obj_roomFacilities;
		objService = new RoomFacilitiesService(obj_roomFacilities);
	}
	public RoomFacilitiesController(DefaultTableModel tableModel, JTable table) {
		// TODO Auto-generated constructor stub
		objService = new RoomFacilitiesService(tableModel, table);
	}

	public int submitFacility()
	{
		return objService.submitService();
	}
	public String generateID() {
		// TODO Auto-generated method stub
		return objService.generateFacilitiesId();
	}
	public void retrieveAll(String query)
	{
		objService.retrieveAll(query);
	}
	public void retrieve(String query, String param)
	{
		objService.retrieve(query, param);
	}
	public void updateService(String query1, String query2, String param)
	{
		objService.updateService(query1, query2, param);
	}
	public RoomFacilities populateForm(String param) {
		// TODO Auto-generated method stub
		return objService.populateForm(param);
	}
	public int updateForm(RoomFacilities objRF) {
		// TODO Auto-generated method stub
		return objService.updateForm(objRF);
	}

}
