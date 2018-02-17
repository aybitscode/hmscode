package com.hms.services;

import java.util.ArrayList;
import java.util.List;
import com.hms.cache.HMSCache;
import com.hms.dbutil.HMSDbOperations;
import com.hms.exception.HMSException;
import com.hms.model.Customer;

public class CustomerService {

	public CustomerService(){
		
	}
	
	public List<Customer> getCustomerList(){
		
	//	List<Customer> customerList = HMSCache.custCache.getAllCustomers();
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			customerList = HMSDbOperations.getAllCustomers();
		} catch (HMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerList;
	}
	
	public Customer getCustomerByPhone(String mobilePhone){
		//Customer customer = HMSCache.custCache.getCustomerByMobile(mobilePhone);
		Customer customer = null;
		try {
			customer = HMSDbOperations.getCustomerByPhone(mobilePhone);
		} catch (HMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}
	
	public Boolean addCustomer(Customer customer){
		HMSDbOperations dbOps = new HMSDbOperations();
		Boolean isCustomerAdditionSuccessful = dbOps.addCustomer(customer);
		if(isCustomerAdditionSuccessful){
			HMSCache.custCache.addCustomer(customer);
		}
		return isCustomerAdditionSuccessful;
	}
	
	public Boolean updateCustomer(Customer customer) throws HMSException{
		HMSDbOperations dbOps = new HMSDbOperations();
		Boolean isCustomerUpdateSuccessful = dbOps.updateCustomer(customer);
		if(isCustomerUpdateSuccessful){
			HMSCache.custCache.updateCustomer(customer);
			System.out.println("Customer Cache update successful");
		}
		return isCustomerUpdateSuccessful;
	}
	
	

	public String getCustomerNameByMobile(String mobilePhone){
		Customer customer = getCustomerByPhone(mobilePhone);
		return customer.getFirst_name()+" "+customer.getLast_name();
	}

	public String getCustomerId(String mobilePhone) {
		Customer customer = getCustomerByPhone(mobilePhone);
		return customer.getCustomerID();
	}

	public Customer getCustomerById(String customerId) {
		//Customer customer = HMSCache.custCache.getCustomerById(customerId);
		Customer customer = null;
		customer = HMSDbOperations.getCustomerById(customerId);
				
		return customer;
	}

	
	
	

}
