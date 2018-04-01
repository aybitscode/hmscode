package com.aybits.hms.api.func.customer.services;


import java.util.ArrayList;
import java.util.List;
import com.aybits.hms.api.func.customer.cache.CustomerCache;
import com.aybits.hms.api.func.customer.dao.CustomerDAO;
import com.aybits.hms.api.arch.exception.HMSException;
import com.aybits.hms.api.func.customer.beans.Customer;

public class CustomerService {

	public CustomerService(){
		
	}
	
	public List<Customer> getCustomerList(){
		
	//	List<Customer> customerList = HMSCache.custCache.getAllCustomers();
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			customerList = CustomerDAO.getAllCustomers();
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
			customer = CustomerDAO.getCustomerByPhone(mobilePhone);
		} catch (HMSException e) {

		}
		return customer;
	}
	
	public Boolean addCustomer(Customer customer){
		CustomerDAO dbOps = new CustomerDAO();
		Boolean isCustomerAdditionSuccessful = dbOps.addCustomer(customer);
		;CustomerCache customerCache = new CustomerCache();
		if(isCustomerAdditionSuccessful){
			customerCache.addCustomer(customer);
		}
		return isCustomerAdditionSuccessful;
	}
	
	public Boolean updateCustomer(Customer customer) throws HMSException{
		CustomerDAO dbOps = new CustomerDAO();
		Boolean isCustomerUpdateSuccessful = dbOps.updateCustomer(customer);
		if(isCustomerUpdateSuccessful){
			CustomerCache customerCache = new CustomerCache();
			customerCache.updateCustomer(customer);
			System.out.println("Customer Cache update successful");
		}
		return isCustomerUpdateSuccessful;
	}
	
	

	public String getCustomerNameByMobile(String mobilePhone){
		Customer customer = getCustomerByPhone(mobilePhone);
		return customer.getFirstName()+" "+customer.getLastName();
	}

	public String getCustomerId(String mobilePhone) {
		Customer customer = getCustomerByPhone(mobilePhone);
		return customer.getCustomerId();
	}

	public Customer getCustomerById(String customerId) {
		//Customer customer = HMSCache.custCache.getCustomerById(customerId);
		Customer customer = null;
		customer = CustomerDAO.getCustomerById(customerId);
				
		return customer;
	}

	
	
	

}
