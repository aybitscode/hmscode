package com.aybits.hms.func.employee.dao;

public class EmployeeDBQueries {

    static final String GET_ALL_EMPLOYEES = "Select * from hms_employee";
    static final String GET_EMPLOYEE_BY_PHONE = "Select * from hms_employee where mobile=?";
    static final String GET_EMPLOYEE_BY_ID = "Select * from hms_employee where employee_id=?";
    static final String ADD_EMPLOYEE = "insert into hms_employee(employee_id,corporate_id,first_name,middle_name,last_name,email,mobile," +
            "home_address,identification_param_id, payment_param,hms_hotel_id," +
            "employee_status,date_created) "+
            "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    static final String UPDATE_EMPLOYEE = "update hms_employee set first_name = ?,last_name = ?, home_address = ?, identification_param_id, payment_param," +
            "							   email = ?, mobile = ? where employee_id = ?";
}
