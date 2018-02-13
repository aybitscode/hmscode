package com.hms.controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.RoomCapacity;
import com.hms.services.RoomCapacityService;

public class RoomCapacityController {
	public RoomCapacity obj_roomCapacity;
	RoomCapacityService room_capacity_service;
	
	public RoomCapacityController()
	{
		room_capacity_service = new RoomCapacityService();
	}
	public RoomCapacityController(RoomCapacity obj_roomCapacity)
	{
		this.obj_roomCapacity = obj_roomCapacity;
		room_capacity_service = new RoomCapacityService(obj_roomCapacity);
	}
	public RoomCapacityController(DefaultTableModel tableModel, JTable table) {
		// TODO Auto-generated constructor stub
		room_capacity_service = new RoomCapacityService(tableModel, table);
	}
	
	public String generateID() {
		// TODO Auto-generated method stub
		return room_capacity_service.generateRoomCapacityID();
	}
	public int submitRoom()
	{
		return room_capacity_service.submitService();
	}
	public List<String> retrieveCapacityName(String query)
	{
		return room_capacity_service.retrieveCapacityName(query);
	}
	public void retrieveAll(String query)
	{
		room_capacity_service.retrieveAll(query);
	}
	public void retrieve(String query, String param)
	{
		room_capacity_service.retrieve(query, param);
	}
	public void updateService(String query1, String query2, String param)
	{
		room_capacity_service.updateService(query1, query2, param);
	}

}
