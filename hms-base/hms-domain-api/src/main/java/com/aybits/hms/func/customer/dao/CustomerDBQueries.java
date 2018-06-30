package com.aybits.hms.func.customer.dao;

public class CustomerDBQueries {
	
	static final String GET_ALL_CUSTOMERS = "Select * from CUSTOMERS";
	static final String GET_CUSTOMER_BY_PHONE = "Select * from customers where mobile=?";
	static final String GET_CUSTOMER_BY_ID = "Select * from customers where customer_id=?";
	static final String ADD_CUSTOMER = "insert into hms_customer(customer_id,corporate_id,first_name,middle_name,last_name,email,mobile," +
															 "home_address,identification_param_id, payment_param,hms_hotel_id," +
															 "customer_status,date_created) "+
			                                                 "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	static final String UPDATE_CUSTOMER = "update hms_customer set first_name = ?,last_name = ?, home_address = ?, identification_param_id, payment_param," +
			"							   email = ?, mobile = ? where customer_id = ?";

}
