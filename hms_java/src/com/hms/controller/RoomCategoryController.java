package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.RoomCategory;
import com.hms.services.CustomerService;
import com.hms.services.RoomCategoryService;

public class RoomCategoryController {
	public RoomCategory objRoomCategory;
	RoomCategoryService roomCategoryService;
	
	
	public RoomCategoryController()
	{
		roomCategoryService = new RoomCategoryService();
	}
	public RoomCategoryController(RoomCategory obj_roomCategory)
	{
		this.objRoomCategory = obj_roomCategory;
		roomCategoryService = new RoomCategoryService(obj_roomCategory);
	}
	public RoomCategoryController(DefaultTableModel tableModel, JTable table)
	{
		roomCategoryService = new RoomCategoryService(tableModel, table);
	}
	public int submitRoom()
	{
		return roomCategoryService.submitService();
	}
	public void retrieveAllRoomCategories(String query)
	{
		roomCategoryService.retrieveAllRoomCategories(query);
	}
	public void retrieveRoomCategory(String query, String param)
	{
		roomCategoryService.retrieveRoomCategory(query, param);
	}
	public void updateRoomCategory(String query)
	{
		roomCategoryService.updateRoomCategory(query);
	}
	
	public void updateCategory(int id, RoomCategory obj_roomCategory)
	{
		roomCategoryService.updateService(id, obj_roomCategory);
	}	
	public void deleteCategory(int id)
	{
		roomCategoryService.deleteService(id);
	}
	
	public void getFirst()
	{
		
		objRoomCategory =  roomCategoryService.getRoomCategoryFirst();
	}
	
	public void getLast()
	{
		
		objRoomCategory = roomCategoryService.getRoomCategoryLast();
	}
	public void getNext()
	{
		
		objRoomCategory = roomCategoryService.getRoomCategoryNext();
 
	}
	public void getPrevious()
	{
		
		objRoomCategory = roomCategoryService.getRoomCategoryPrevious();
	}
	public RoomCategory populateForm(String roomCategoryId) {
		// TODO Auto-generated method stub
		return roomCategoryService.populateForm(roomCategoryId);
	}	

}
