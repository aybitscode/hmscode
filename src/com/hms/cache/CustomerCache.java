package com.hms.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.hms.model.Customer;

public class CustomerCache {
	private HashMap<String, Customer> customerCache = new HashMap<>();
	private Map<String, Customer> customerMobileCache = new HashMap<>();
	private HashSet<String> customerIds = new HashSet<>();

	public void addCustomer(Customer customer) {
		if (customerCache.get(String.valueOf(customer.getCustomerID())) == null) {
			customerIds.add(customer.getCustomerID());
			customerCache.put(customer.getCustomerID(), customer);
			addToCustomerMobileCache(customer);
		}
	}

	public void updateCustomer(Customer customer) {
		String customerPhone = customer.getPhone_number();
		String customerId = customer.getCustomerID();
		customerCache.remove(customerId);
		customerMobileCache.remove(customerPhone);
		customerCache.put(customerId, customer);
		customerMobileCache.put(customer.getPhone_number(),customer);
	}

	private void addToCustomerMobileCache(Customer customer) {
		customerMobileCache.put(customer.getPhone_number(),customer);
	}

	public Customer getCustomerById(String customerId) {
		Customer customer = customerCache.get(customerId);
		if (customer != null)
			return customer;
		else
			return null;
	}
	
	public Customer getCustomerByMobile(String mobileNumber) {
		Customer customer = customerMobileCache.get(mobileNumber);
		if (customer != null)
			return customer;
		else
			return null;
	}

	
	public List<Customer> getAllCustomers() {
		ArrayList<Customer> customers = new ArrayList<>();
		customers.addAll(customerCache.values());
		return customers;
	}

	public List<String> getAllCustomerIds() {
		ArrayList<String> customerIds = new ArrayList<>();
		customerIds.addAll(customerCache.keySet());
		return customerIds;
	}

}
