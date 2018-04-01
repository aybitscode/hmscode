package com.aybits.hms.api.func.customer.dao;

public class CustomerDBQueries {
	
	static final String GET_ALL_CUSTOMERS = "Select * from CUSTOMERS";
	static final String GET_CUSTOMER_BY_PHONE = "Select * from customers where mobile=?";
	static final String GET_CUSTOMER_BY_ID = "Select * from customers where customerid=?";
	static final String ADD_CUSTOMER = "insert into customers(customerId,firstName, lastName, gender, city, email, mobile, addrs) "+ "values(?,?,?,?,?,?,?,?)";
	static final String UPDATE_CUSTOMER = "update customers set firstName = ?,lastName = ?, city = ?, email = ?, mobile = ?, addrs = ? where customerID = ?";

}
