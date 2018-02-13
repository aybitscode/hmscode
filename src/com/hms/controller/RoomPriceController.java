package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.RoomPrice;
import com.hms.services.CustomerService;
import com.hms.services.RoomPriceService;

public class RoomPriceController {
	public RoomPrice obj_RoomPrice;
	RoomPriceService room_price_service;
	
	public RoomPriceController(RoomPrice obj_RoomPrice)
	{
		this.obj_RoomPrice = obj_RoomPrice;
		room_price_service = new RoomPriceService(obj_RoomPrice);
	}
	public RoomPriceController(DefaultTableModel tableModel, JTable table)
	{
		room_price_service = new RoomPriceService(tableModel, table);
	}
	public int submitRoom()
	{
		return room_price_service.submitService();
	}
	public void retrieveRoomPrices()
	{
		room_price_service.retrieveRoomsPrices();
	}
	public void retrieveRoomPrice(String query, String roomPriceName)
	{
		room_price_service.retrieveRoomsPrice(query, roomPriceName);
	}
	public int  submitFacilities(Object[] listFacilites, RoomPrice rp)
	{
		int s = room_price_service.submitFacilities(listFacilites, rp);
		return s;
	}
	public int  submitNoFacilities(String param, RoomPrice rp)
	{
		int s = room_price_service.submitNoFacilities(param, rp);
		return s;
	}
	public String generateID()
	{
		String s = room_price_service.generateRoomPriceID();
		return s;
	}

}
