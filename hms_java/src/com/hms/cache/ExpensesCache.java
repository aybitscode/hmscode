package com.hms.cache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.hms.model.Customer;
import com.hms.model.Expenses;

public class ExpensesCache {
	private HashMap<String, Expenses> ExpensesCache = new HashMap<>();
	private Map<String, Expenses> customerMobileCache = new HashMap<>();
	private HashSet<String> expenseIds = new HashSet<>();

	public void addExpense(Expenses objExpense) {
		if (ExpensesCache.get(String.valueOf(objExpense.getRequestId())) == null) {
			expenseIds.add(objExpense.getRequestId());
			ExpensesCache.put(objExpense.getRequestId(), objExpense);
			//addToCustomerMobileCache(objExpense);
		}
	}

	public void updateExpense(Expenses objExpense) {
		//String customerPhone = objExpense.getPhone_number();
		String requestId = objExpense.getRequestId();
		ExpensesCache.remove(requestId);
		//customerMobileCache.remove(customerPhone);
		ExpensesCache.put(requestId, objExpense);
		//customerMobileCache.put(customer.getPhone_number(),objExpense);
	}

//	private void addToCustomerMobileCache(Expenses objExpense) {
//		customerMobileCache.put(objExpense.getPhone_number(),customer);
//	}

	public Expenses getExpenseById(String requestId) {
		Expenses objExpense = ExpensesCache.get(requestId);
		if ( objExpense != null)
			return  objExpense;
		else
			return null;
	}
	
//	public Customer getCustomerByMobile(String mobileNumber) {
//		Customer customer = customerMobileCache.get(mobileNumber);
//		if (customer != null)
//			return customer;
//		else
//			return null;
//	}

	
	public List<Expenses> getAllExpenses() {
		ArrayList<Expenses> objExpenses = new ArrayList<>();
		objExpenses.addAll(ExpensesCache.values());
		return objExpenses;
	}

	public List<String> getAllCustomerIds() {
		ArrayList<String> customerIds = new ArrayList<>();
		customerIds.addAll(ExpensesCache.keySet());
		return customerIds;
	}

}
