package com.hms.services;

import java.util.List;

import com.hms.cache.HMSCache;
import com.hms.dbutil.ExpenseDbOperations;
import com.hms.exception.HMSException;
import com.hms.model.Expenses;
import com.hms.model.Expenses;

public class ExpenseService {

	public ExpenseService(){
		
	}
	
	public List<Expenses> getExpenseList(){
		List<Expenses> expenseList = HMSCache.expenseCache.getAllExpenses();
		return expenseList;
	}
	
//	public Expenses getExpensesByPhone(String mobilePhone){
//		Expenses Expenses = HMSCache.custCache.getExpensesByMobile(mobilePhone);
//		return Expenses;
//	}
	
	public Boolean addExpenses(Expenses objExpense){
		ExpenseDbOperations dbOps = new ExpenseDbOperations();
		Boolean isExpensesAdditionSuccessful = dbOps.addExpense(objExpense);
		if(isExpensesAdditionSuccessful){
			HMSCache.expenseCache.addExpense(objExpense);
		}
		return isExpensesAdditionSuccessful;
	}
	
	public Boolean updateExpenses(Expenses objExpense) throws HMSException{
		ExpenseDbOperations dbOps = new ExpenseDbOperations();
		Boolean isExpensesUpdateSuccessful = dbOps.updateExpenses(objExpense);
		if(isExpensesUpdateSuccessful){
			HMSCache.expenseCache.updateExpense(objExpense);
			System.out.println("Expenses Cache update successful");
		}
		return isExpensesUpdateSuccessful;
	}
	
	

//	public String getExpensesNameByMobile(String mobilePhone){
//		Expenses Expenses = getExpensesByPhone(mobilePhone);
//		return Expenses.getFirst_name()+" "+Expenses.getLast_name();
//	}

//	public String getExpensesId(String mobilePhone) {
//		Expenses Expenses = getExpensesByPhone(mobilePhone);
//		return Expenses.getExpensesID();
//	}

	public Expenses getExpenseById(String requestId) {
		Expenses objExpense = HMSCache.expenseCache.getExpenseById(requestId);
		return objExpense;
	}

	
	
	

}
