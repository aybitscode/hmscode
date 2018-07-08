package com.aybits.hms.func.employee.api;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.employee.dao.EmployeeDAO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAPI extends HMSAPIProviderImpl {

    public EmployeeAPI(){

    }

    public List<Employee> getAllEmployees(){

        //	List<Employee> employeeList = HMSCache.custCache.getAllEmployees();
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            allEmployees = EmployeeDAO.getAllEmployees();
        } catch (HMSException e) {
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
        } catch (HMSException e) {
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

    public Boolean updateEmployee(Employee employee) throws HMSException{
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
        return employee.getFirstName()+" "+employee.getLastName();
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
}
