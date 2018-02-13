package com.hms.controller;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.exception.HMSException;
import com.hms.model.Expenses;
import com.hms.services.ExpenseService;
import com.hms.view.ExpensesView;

public class ExpenseController {
	
	ExpensesView objExpeseView = new ExpensesView();
	ExpenseService objExpenseService = new ExpenseService();
 
	public ExpenseController()
	{
		objExpeseView = new ExpensesView();
	}
	
	public ExpenseController(DefaultTableModel tableModel, JTable table)
	{
		objExpeseView = new ExpensesView(tableModel, table);
	}
 
	public Boolean addExpenses(Expenses objExpense)
	{
		return objExpenseService.addExpenses(objExpense);
	}
	
	public Boolean updateExpenses(Expenses objExpense) throws HMSException{
		return objExpenseService.updateExpenses(objExpense);
	}
	public void populateExpensesList()
	{
		objExpeseView.populateExpensesListInModel();
	}
//	
//	public String getExpensesName(String mobilePhone)
//	{
//		return objExpenseService.getExpensesNameByMobile(mobilePhone);
//		
//	}
//	
//	public void populateExpenses(String mobilePhone){
//		objExpeseView.addExpensesToModel(mobilePhone);
//	}
//	
//	public String getExpensesId(String mobilePhone){
//		return objExpenseService.getExpensesId(mobilePhone);
//	}
 
	public Boolean isExpensesDataModified(Expenses ExpensesFromTableModel,Expenses ExpensesFromCache) {
		
		
		Boolean isExpensesDataModified = false;
		Integer check = ExpensesFromCache.compareTo(ExpensesFromTableModel);
		// TODO Auto-generated method stub
		if(check < 0){
			isExpensesDataModified =  true;
		}
		return isExpensesDataModified;
	}
	
	
	public Expenses getExpensesFromModel(DefaultTableModel tableModel){
		Expenses objExpense = new Expenses();
		//String requestId = this.getExpenseById(""+tableModel.getValueAt(0,6));
		//objExpense.setRequestId(requestId);
		objExpense.setDepartment(""+tableModel.getValueAt(0,0));
		objExpense.setPaidTo(""+tableModel.getValueAt(0,1));
		objExpense.setDescription(""+tableModel.getValueAt(0,2));
		objExpense.setAmount(""+tableModel.getValueAt(0,3));
		
		
		return objExpense;
	}
	
//	public Expenses getExpensesByPhone(String mobilePhone){
//		return objExpenseService.getExpensesByPhone(mobilePhone);
//	}
	
	public Expenses getExpenseById(String requestId){
		return objExpenseService.getExpenseById(requestId);
	}
}
