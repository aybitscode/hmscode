package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Employee;
import com.hms.services.EmployeeService;
import com.hms.services.SeasonService;


public class EmployeeController {
	EmployeeService employee_service;
 
	
	public EmployeeController(Employee obj_employee)
	{
		employee_service = new EmployeeService(obj_employee);
	}
	public EmployeeController(DefaultTableModel tableModel, JTable table) {
		// TODO Auto-generated constructor stub
		employee_service = new EmployeeService(tableModel, table);
	}
	public int submitCustomer()
	{
		return employee_service.submitService();
	}
	public void retrieveAll(String query)
	{
		employee_service.retrieveAll(query);
	}
	public void retrieve(String query, String param)
	{
		employee_service.retrieve(query, param);
	}
	public void updateService(String query1, String query2, String param)
	{
		employee_service.updateService(query1, query2, param);
	}
	public int  deleteService(String query, String param){
		return employee_service.deleteService(query, param);
	}
 

}
