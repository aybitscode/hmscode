package com.aybits.hms.func.customer.cache;

import com.aybits.hms.func.customer.beans.Customer;

import java.util.*;

public class CustomerCache {
	private HashMap<String, Customer> customerCache = new HashMap<>();
	private Map<String, Customer> customerMobileCache = new HashMap<>();
	private HashSet<String> customerIds = new HashSet<>();

	public void addCustomer(Customer customer) {
		if (customerCache.get(String.valueOf(customer.getCustomerId())) == null) {
			customerIds.add(customer.getCustomerId());
			customerCache.put(customer.getCustomerId(), customer);
			addToCustomerMobileCache(customer);
		}
	}

	public void updateCustomer(Customer customer) {
		String customerPhone = customer.getContactDetails().getPrimaryPhone();
		String customerId = customer.getCustomerId();
		customerCache.remove(customerId);
		customerMobileCache.remove(customerPhone);
		customerCache.put(customerId, customer);
		customerMobileCache.put(customer.getContactDetails().getPrimaryPhone(),customer);
	}

	private void addToCustomerMobileCache(Customer customer) {
		customerMobileCache.put(customer.getContactDetails().getPrimaryPhone(),customer);
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
