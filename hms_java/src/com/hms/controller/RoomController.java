package com.hms.controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Room;
import com.hms.services.RoomService;

public class RoomController {
	public Room obj_room;
	RoomService roomService;
	public RoomController() {
		// TODO Auto-generated constructor stub
		roomService = new RoomService();
	}
	public String generateRoomID(){
		return roomService.generateRoomID();
	}
	public int roomCount()
	{
		return roomService.getTotalRooms();
	}
	public List<String> roomCategoryIDList(String query)
	{
		return roomService.roomCategoryIDList(query);
	}
	public RoomController(DefaultTableModel tableModel, JTable table)
	{
		roomService = new RoomService(tableModel, table);
	}
	public RoomController(Room obj_room)
	{
		this.obj_room = obj_room;
		roomService = new RoomService(obj_room);
	}

	public int submitRoom()
	{
		return roomService.submitService();
	}
	public void retrieveAll(String query)
	{
		 roomService.retrieveAllRooms(query);
	}
	public void retrieve(String query, String param)
	{
		 roomService.retrieveRoom(query, param);
	}
	public void updateRoom(String query, String param)
	{
		roomService.updateRoom(query, param);
	}
	public Room populateForm(String param) {
		// TODO Auto-generated method stub
		return roomService.populateForm(param);
	}
	public int updateForm(Room obj_room) {
		// TODO Auto-generated method stub
		return roomService.updateForm(obj_room);
	}

}