package com.aybits.hms.func.employee.api;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.api.HmsAPIImpl;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.employee.dao.EmployeeDAO;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAPI implements HmsAPI {

    public EmployeeAPI(){

    }

    public List<Employee> getAllEmployees(){

        //	List<Employee> employeeList = HMSCache.custCache.getAllEmployees();
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            allEmployees = EmployeeDAO.getAllEmployees();
        } catch (HMSRuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allEmployees;
    }

    public Employee getEmployeeByPhone(String mobilePhone){
        //Employee employee = HMSCache.custCache.getEmployeeByMobile(mobilePhone);
        Employee employee = null;

        try {
            employee = EmployeeDAO.getEmployeeByPhone(mobilePhone);
        } catch (HMSRuntimeException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public Boolean addEmployee(Employee employee){
        EmployeeDAO dbOps = new EmployeeDAO();
        Boolean isEmployeeAdditionSuccessful = dbOps.addEmployee(employee);
		/*EmployeeCache employeeCache = new EmployeeCache();
		if(isEmployeeAdditionSuccessful){
			employeeCache.addEmployee(employee);
		}*/
        return isEmployeeAdditionSuccessful;
    }

    public Boolean updateEmployee(Employee employee) throws HMSRuntimeException{
        EmployeeDAO dbOps = new EmployeeDAO();
        Boolean isEmployeeUpdateSuccessful = dbOps.updateEmployee(employee);
		/*if(isEmployeeUpdateSuccessful){
			EmployeeCache employeeCache = new EmployeeCache();
			employeeCache.updateEmployee(employee);
			System.out.println("Employee Cache update successful");
		}*/
        return isEmployeeUpdateSuccessful;
    }



    public String getEmployeeNameByMobile(String mobilePhone){
        Employee employee = getEmployeeByPhone(mobilePhone);
        return employee.getEmpFullName();
    }

    public String getEmployeeId(String mobilePhone) {
        Employee employee = getEmployeeByPhone(mobilePhone);
        return employee.getEmpId();
    }

    public Employee getEmployeeById(String employeeId) {
        //Employee employee = HMSCache.custCache.getEmployeeById(employeeId);
        Employee employee = null;
        employee = EmployeeDAO.getEmployeeById(employeeId);

        return employee;
    }

    @Override
    public Object init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {

    }

    @Override
    public String fetch(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSRuntimeException {
        return null;
    }
}
