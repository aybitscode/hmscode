package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Hotel;
import com.hms.services.HotelService;

public class HotelController {
	public Hotel obj_hotel;
	HotelService hotel_service;
	
	public HotelController()
	{
		hotel_service = new HotelService();
	}
	public HotelController(Hotel obj_hotel)
	{
		this.obj_hotel = obj_hotel;
		hotel_service = new HotelService(obj_hotel);
	}
	public HotelController(DefaultTableModel tableModel, JTable table) {
		// TODO Auto-generated constructor stub
		hotel_service = new HotelService(tableModel, table);
	}
	public int totalRooms(String query)
	{
		return hotel_service.retrieveTotalRooms(query);
	}
	public int submitRoom(String query)
	{
		return hotel_service.submitService(query);
	}
	public String retrieveHotelName(String query)
	{
		return hotel_service.retrieveHotelName(query);
	}
	public String generateID() {
		// TODO Auto-generated method stub
		return hotel_service.generateHotelId();
	}
	public void retrieveAll(String query)
	{
		hotel_service.retrieveAll(query);
	}
	public void retrieve(String query, String param)
	{
		hotel_service.retrieve(query, param);
	}
	public void updateService(String query1, String query2, String param)
	{
		hotel_service.updateService(query1, query2, param);
	}
}