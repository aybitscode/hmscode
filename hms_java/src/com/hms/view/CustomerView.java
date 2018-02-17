package com.hms.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Customer;
import com.hms.services.CustomerService;

public class CustomerView {
	
	DefaultTableModel tableModel;
	JTable table;
	CustomerService customerService = new CustomerService();
	
	public CustomerView(){
		
	}
	
	public CustomerView(DefaultTableModel tableModel, JTable table) {
		super();
		this.tableModel = tableModel;
		this.table = table;
	}
	
	public void populateCustomerListInModel(){
		List<Customer> customers = customerService.getCustomerList();
		//CustomerEntry.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		} 
		numRows = 0;
		for(Customer customer : customers){
			tableModel.addRow(new Object[]{
					customer.getFirst_name(),
					customer.getLast_name(),
					customer.getGender(),
					customer.getAddress(),
					customer.getCity(),
					customer.getEmail(),
					customer.getPhone_number()
				});
			numRows++;
			
		} 
		CustomersReport.lblRows.setText("No. of Rows: "+numRows);
	}
	
	public void populateCustomerListInModel(ViewAllCustomers obj){
		List<Customer> customers = customerService.getCustomerList();
		//CustomerEntry.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		} 
		numRows = 0;
		for(Customer customer : customers){
			tableModel.addRow(new Object[]{
					customer.getFirst_name(),
					customer.getLast_name(),
					customer.getGender(),
					customer.getAddress(),
					customer.getCity(),
					customer.getEmail(),
					customer.getPhone_number()
				});
			numRows++;
			
		} 
		obj.lblRows.setText("No. of Rows: "+numRows);
	}
	
	public void addCustomerToModel(String mobilePhone)
	{
		Customer customer = customerService.getCustomerByPhone(mobilePhone);
		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//			CustomerEntry.btnSubmit.setVisible(false);
//		else if(MainPage.user_role.equals(s2))
//			CustomerEntry.btnSubmit.setVisible(true);
//		else
//			CustomerEntry.btnSubmit.setVisible(false);

		
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}

 		tableModel.addRow(new Object[]{
				customer.getFirst_name(),
				customer.getLast_name(),
				customer.getGender(),
				customer.getAddress(),
				customer.getCity(),
				customer.getEmail(),
				customer.getPhone_number()
			});
//		CustomerEntry.lblRows.setText("");
//		CustomerEntry.lblRows.setText("No. of Rows: "+1);
		
	}
	
	public Customer addCustomerToForm(String mobilePhone)
	{
		Customer customer = customerService.getCustomerByPhone(mobilePhone);
		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//			CustomerEntry.btnSubmit.setVisible(false);
//		else if(MainPage.user_role.equals(s2))
//			CustomerEntry.btnSubmit.setVisible(true);
//		else
//			CustomerEntry.btnSubmit.setVisible(false);
 
		return customer;
	}

	

}

