package com.aybits.hms.func.employee.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.employee.dao.EmployeeDAO;
import com.aybits.hms.func.hotel.beans.Hotel;

public class EmployeeCache {

    private static ConcurrentHashMap<String,Employee> employeeCache = new ConcurrentHashMap<String,Employee>();
    private static ConcurrentHashMap employeeMobileCache = new ConcurrentHashMap();
    private HashSet<String> employeeIds = new HashSet<>();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    
    public Boolean initCache(String hotelId){
        return false;
    }

    /*public void addEmployee(Employee employee) {
        if (employeeCache.get(String.valueOf(employee.getEmpId())) == null) {
            employeeIds.add(employee.getEmpId());
            employeeCache.put(employee.getEmpId(), employee);
            addToEmployeeMobileCache(employee);
        }
    }*/
    
    public String addEmployee(Employee employee)  throws HMSRuntimeException {
    	String employeeId= employeeDAO.addEmployee(employee);
        if (employeeCache.get(String.valueOf(employee.getEmpId())) == null) {
            employeeIds.add(employee.getEmpId());
            employeeCache.put(employee.getEmpId(), employee);
            addToEmployeeMobileCache(employee);
        }
        return String.valueOf(employeeId);
    }
    
    /*public void updateEmployee(Employee employee) {
        String employeePhone = employee.getContactDetails().getPrimaryPhone();
        String employeeId = employee.getEmpId();
        employeeCache.remove(employeeId);
        employeeMobileCache.remove(employeePhone);
        employeeCache.put(employeeId, employee);
        employeeMobileCache.put(employee.getContactDetails().getPrimaryPhone(),employee);
    }
*/
    public Boolean updateEmployee(Employee employee)  throws HMSRuntimeException {
        Boolean isEmployeeUpdateSuccessful = employeeDAO.updateEmployee(employee);
        String employeePhone = employee.getContactDetails().getPrimaryPhone();
        if(isEmployeeUpdateSuccessful) {
            String employeeId = employee.getEmpId();
            if (employeeIds.contains(employee.getEmpId())) {
            	 employeeCache.remove(employeeId);
                 employeeMobileCache.remove(employeePhone);
            }
            employeeCache.put(employeeId, employee);
            employeeMobileCache.put(employee.getContactDetails().getPrimaryPhone(),employee);
        }
        return isEmployeeUpdateSuccessful;
    }

    private void addToEmployeeMobileCache(Employee employee) {
        employeeMobileCache.put(employee.getContactDetails().getPrimaryPhone(),employee);
    }

    public Employee getEmployeeById(String employeeId) {
        Employee employee = (Employee)employeeCache.get(employeeId);
        if (employee != null)
            return employee;
        else
            return null;
    }

    public Employee getEmployeeByMobile(String mobileNumber) {
        Employee employee = (Employee)employeeMobileCache.get(mobileNumber);
        if (employee != null)
            return employee;
        else
            return null;
    }


    public List<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.addAll(employeeCache.values());
        return employees;
    }

    public List<String> getAllEmployeeIds() {
        ArrayList<String> employeeIds = new ArrayList<>();
        employeeIds.addAll(employeeCache.keySet());
        return employeeIds;
    }

    public ConcurrentHashMap<String,Employee> getEmployeeCache(){
        return employeeCache;
    }
    
    
    
}
