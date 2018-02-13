package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.RoomCategory;
import com.hms.services.CustomerService;
import com.hms.services.RoomCategoryService;

public class RoomCategoryController {
	public RoomCategory obj_roomCategory;
	RoomCategoryService room_category_service;
	
	public RoomCategoryController(RoomCategory obj_roomCategory)
	{
		this.obj_roomCategory = obj_roomCategory;
		room_category_service = new RoomCategoryService(obj_roomCategory);
	}
	public RoomCategoryController(DefaultTableModel tableModel, JTable table)
	{
		room_category_service = new RoomCategoryService(tableModel, table);
	}
	public int submitRoom()
	{
		return room_category_service.submitService();
	}
	public void retrieveAllRoomCategories(String query)
	{
		room_category_service.retrieveAllRoomCategories(query);
	}
	public void retrieveRoomCategory(String query, String param)
	{
		room_category_service.retrieveRoomCategory(query, param);
	}
	public void updateRoomCategory(String query)
	{
		room_category_service.updateRoomCategory(query);
	}
	
	public void updateCategory(int id, RoomCategory obj_roomCategory)
	{
		room_category_service.updateService(id, obj_roomCategory);
	}	
	public void deleteCategory(int id)
	{
		room_category_service.deleteService(id);
	}
	
	public void getFirst()
	{
		
		obj_roomCategory =  room_category_service.getRoomCategoryFirst();
	}
	
	public void getLast()
	{
		
		obj_roomCategory = room_category_service.getRoomCategoryLast();
	}
	public void getNext()
	{
		
		obj_roomCategory = room_category_service.getRoomCategoryNext();
 
	}
	public void getPrevious()
	{
		
		obj_roomCategory = room_category_service.getRoomCategoryPrevious();
	}	

}
