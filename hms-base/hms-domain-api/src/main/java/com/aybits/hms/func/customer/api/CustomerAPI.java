package com.aybits.hms.func.customer.api;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.customer.dao.CustomerDAO;
import com.aybits.hms.func.customer.dao.CustomerSelectDAO;

public class CustomerAPI implements HmsAPI {

	CustomerSelectDAO customerSelectDAO = new CustomerSelectDAO();

	public CustomerAPI(){
		
	}
	
	public List<Customer> getAllCustomers(){
		
	//	List<Customer> customerList = HMSCache.custCache.getAllCustomers();
		List<Customer> allCustomers = new ArrayList<Customer>();
		try {
        allCustomers = customerSelectDAO.getAllCustomers();
		} catch (HMSRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allCustomers;
	}
	
	public Customer getCustomerByPhone(String mobilePhone){
		//Customer customer = HMSCache.custCache.getCustomerByMobile(mobilePhone);
		Customer customer = null;

        try {
            customer = customerSelectDAO.getCustomerByPhone(mobilePhone);
        } catch (HMSRuntimeException e) {
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
	
	public Boolean updateCustomer(Customer customer) throws HMSRuntimeException{
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
		customer = customerSelectDAO.getCustomerById(customerId);
				
		return customer;
	}


	@Override
	public Object init(JSONObject object) throws HMSRuntimeException {
		return null;
	}

	@Override
	public String process(JSONObject object) throws HMSRuntimeException {
		return null;
	}

	@Override
	public void validate(JSONObject object) throws HMSRuntimeException {

	}

	@Override
	public String fetch(JSONObject json) throws HMSRuntimeException {
		return null;
	}

	@Override
	public String fetchAll(JSONObject json) throws HMSRuntimeException {
		return null;
	}

	@Override
	public String update(JSONObject json) throws HMSRuntimeException {
		return null;
	}

	@Override
	public String disable(JSONObject json) throws HMSRuntimeException {
		return null;
	}

	@Override
	public String delete(JSONObject json) throws HMSRuntimeException {
		return null;
	}
}
