package com.hms.model;

public class Expenses implements Comparable<Expenses>{
	private String requestId;
	private String department;
	private String paidTo;
	private String description;
	private String amount;
	private java.sql.Timestamp createdDate;
	private String createdBy;
	
	public Expenses()
	{
		
	}
	
	public Expenses(String requestId, String department, String paidTo, String description, String amount, java.sql.Timestamp createdDate, String createdBy)
	{
		this.requestId = requestId;
		this.department = department;
		this.paidTo = paidTo;
		this.description = description;
		this.amount = amount;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
				
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPaidTo() {
		return paidTo;
	}
	public void setPaidTo(String paidTo) {
		this.paidTo = paidTo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.sql.Timestamp createdDate) {
		this.createdDate = createdDate;
	}
 
	
 

	@Override
	public int compareTo(Expenses arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
