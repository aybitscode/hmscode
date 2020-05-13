package com.aybits.hms.func.customer.dao;

public class CustomerDBQueries {
	
	static final String GET_ALL_CUSTOMERS = "Select * from hms_customer where hotel_id = ?";
	static final String GET_CUSTOMER_BY_PHONE = "Select * from hms_customer where mobile=?";
	static final String GET_CUSTOMER_BY_ID = "Select * from hms_customer where customer_id=?";
	static final String ADD_CUSTOMER = "insert into hms_customer(customer_id,corporate_id,customer_full_name,email,mobile," +
															 "home_address,identification_param_id, payment_param,hotel_id," +
															 "customer_status,date_created) "+
			                                                 "values(?,?,?,?,?,?,?,?,?,?,?)";
	static final String UPDATE_CUSTOMER = "update hms_customer set customer_full_name = ?, home_address = ?, identification_param_id, payment_param," +
			"							   email = ?, mobile = ? where customer_id = ? and hms_hotel_id=?";


	static final String FETCH_NEXT_CUSTOMER_ID_SEQUENCE  = "(select count(customer_id)+1 as NEXT_CUSTOMER_ID_VAL from hms_customer)";
}
