package com.hms.controller;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.exception.HMSException;
import com.hms.model.Customer;
import com.hms.services.CustomerService;
import com.hms.view.CustomerView;
import com.hms.view.ViewAllCustomers;

public class CustomerController {
	
	CustomerView customerView = new CustomerView();
	CustomerService customerService = new CustomerService();
 
	public CustomerController()
	{
		customerView = new CustomerView();
	}
	
	public CustomerController(DefaultTableModel tableModel, JTable table)
	{
		customerView = new CustomerView(tableModel, table);
	}
 
	public Boolean addCustomer(Customer customer)
	{
		return customerService.addCustomer(customer);
	}
	
	public Boolean updateCustomer(Customer customer) throws HMSException{
		return customerService.updateCustomer(customer);
	}
	public void populateCustomerList()
	{
		customerView.populateCustomerListInModel();
	}
	public void populateCustomerList(ViewAllCustomers obj)
	{
		customerView.populateCustomerListInModel(obj);
	}
	
	public String getCustomerName(String mobilePhone)
	{
		return customerService.getCustomerNameByMobile(mobilePhone);
		
	}
	
	public void populateCustomer(String mobilePhone){
		customerView.addCustomerToModel(mobilePhone);
	}
	
	public Customer populateCustomerForm(String mobilePhone){
		return customerView.addCustomerToForm(mobilePhone);
	}
	
	public String getCustomerId(String mobilePhone){
		return customerService.getCustomerId(mobilePhone);
	}
 
	public Boolean isCustomerDataModified(Customer customerFromTableModel,Customer customerFromCache) {
		
		
		Boolean isCustomerDataModified = false;
		Integer check = customerFromCache.compareTo(customerFromTableModel);
		// TODO Auto-generated method stub
		if(check < 0){
			isCustomerDataModified =  true;
		}
		return isCustomerDataModified;
	}
	
	
	public Customer getCustomerFromModel(DefaultTableModel tableModel){
		Customer customer = new Customer();
		String customerId = this.getCustomerId(""+tableModel.getValueAt(0,6));
		customer.setCustomerID(customerId);
		customer.setFirst_name(""+tableModel.getValueAt(0,0));
		customer.setLast_name(""+tableModel.getValueAt(0,1));
		customer.setGender(""+tableModel.getValueAt(0,2));
		customer.setAddress(""+tableModel.getValueAt(0,3));
		customer.setCity(""+tableModel.getValueAt(0,4));
		customer.setEmail(""+tableModel.getValueAt(0, 5));		
		customer.setPhone_number(""+tableModel.getValueAt(0, 6));
		
		
		return customer;
	}
	
	public Customer getCustomerByPhone(String mobilePhone){
		return customerService.getCustomerByPhone(mobilePhone);
	}
	
	public Customer getCustomerById(String customerId){
		return customerService.getCustomerById(customerId);
	}
}
