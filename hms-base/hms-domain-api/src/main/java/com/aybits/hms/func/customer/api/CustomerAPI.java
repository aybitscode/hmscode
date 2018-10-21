package com.aybits.hms.func.customer.api;


import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.customer.dao.CustomerDAO;

import java.util.ArrayList;
import java.util.List;

public class CustomerAPI extends HMSAPIProviderImpl {

	CustomerDAO customerDAO = new CustomerDAO();

	public CustomerAPI(){
		
	}
	
	public List<Customer> getAllCustomers(){
		
	//	List<Customer> customerList = HMSCache.custCache.getAllCustomers();
		List<Customer> allCustomers = new ArrayList<Customer>();
		try {
        allCustomers = customerDAO.getAllCustomers();
		} catch (HMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allCustomers;
	}
	
	public Customer getCustomerByPhone(String mobilePhone){
		//Customer customer = HMSCache.custCache.getCustomerByMobile(mobilePhone);
		Customer customer = null;

        try {
            customer = customerDAO.getCustomerByPhone(mobilePhone);
        } catch (HMSException e) {
            e.printStackTrace();
        }

        return customer;
	}
	
	public Boolean addCustomer(Customer customer){
		CustomerDAO dbOps = new CustomerDAO();
		Boolean isCustomerAdditionSuccessful = dbOps.addCustomer(customer);
		/*CustomerCache customerCache = new CustomerCache();
		if(isCustomerAdditionSuccessful){
			customerCache.addCustomer(customer);
		}*/
		return isCustomerAdditionSuccessful;
	}
	
	public Boolean updateCustomer(Customer customer) throws HMSException{
		CustomerDAO dbOps = new CustomerDAO();
		Boolean isCustomerUpdateSuccessful = dbOps.updateCustomer(customer);
		/*if(isCustomerUpdateSuccessful){
			CustomerCache customerCache = new CustomerCache();
			customerCache.updateCustomer(customer);
			System.out.println("Customer Cache update successful");
		}*/
		return isCustomerUpdateSuccessful;
	}
	
	

	public String getCustomerNameByMobile(String mobilePhone){
		Customer customer = getCustomerByPhone(mobilePhone);
		return customer.getCustomerFullName();
	}

	public String getCustomerId(String mobilePhone) {
		Customer customer = getCustomerByPhone(mobilePhone);
		return customer.getCustomerId();
	}

	public Customer getCustomerById(String customerId) {
		//Customer customer = HMSCache.custCache.getCustomerById(customerId);
		Customer customer = null;
		customer = customerDAO.getCustomerById(customerId);
				
		return customer;
	}

	
	
	

}
