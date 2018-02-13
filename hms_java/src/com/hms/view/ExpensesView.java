package com.hms.view; 

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.services.ExpenseService;

public class ExpensesView {
	
	DefaultTableModel tableModel;
	JTable table;
	ExpenseService expenseService = new ExpenseService();
	
	public ExpensesView(){
		
	}
	
	public ExpensesView(DefaultTableModel tableModel, JTable table) {
		super();
		this.tableModel = tableModel;
		this.table = table;
	}
	
	public void populateExpensesListInModel(){
		List<com.hms.model.Expenses> objExpenseList = expenseService.getExpenseList();
		//ExpensesEntry.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		} 
		
		for(com.hms.model.Expenses objExpense : objExpenseList){
			System.out.println("from inside bros");
			tableModel.addRow(new Object[]{
					objExpense.getDepartment(),
					objExpense.getPaidTo(),
					objExpense.getDescription(),
					objExpense.getAmount()
				});
			numRows++;
		}
		ExpenseHistory.lblRows.setText("No. of Rows: "+numRows);
	}
	
//	public void addExpensesToModel(String mobilePhone)
//	{
//		Expenses Expenses = expenseService.getExpensesByPhone(mobilePhone);
//		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//			Expenses.btnSubmit.setVisible(false);
//		else if(MainPage.user_role.equals(s2))
//			Expenses.btnSubmit.setVisible(true);
//		else
//			Expenses.btnSubmit.setVisible(false);
//
//		
//		int numRows = tableModel.getRowCount();
//		for (int i=numRows;i>0;i--) {
//			tableModel.removeRow(i-1);
//			table.revalidate();
//		}
//
//		tableModel.addRow(new Object[]{
//				Expenses.getFirst_name(),
//				Expenses.getLast_name(),
//				Expenses.getGender(),
//				Expenses.getAddress(),
//				Expenses.getCity(),
//				Expenses.getEmail(),
//				Expenses.getPhone_number()
//			});
//		Expenses.lblRows.setText("");
//		Expenses.lblRows.setText("No. of Rows: "+1);
//		
//	}

	

}
