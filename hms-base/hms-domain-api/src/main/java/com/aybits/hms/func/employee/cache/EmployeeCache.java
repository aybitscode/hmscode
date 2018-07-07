package com.aybits.hms.func.employee.cache;

import com.aybits.hms.func.employee.beans.Employee;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeeCache {

    private static ConcurrentHashMap<String, Employee> employeeCache = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Employee> employeeMobileCache = new ConcurrentHashMap<>();
    private HashSet<String> employeeIds = new HashSet<>();

    public Boolean initCache(){
        return false;
    }

    public void addEmployee(Employee employee) {
        if (employeeCache.get(String.valueOf(employee.getEmpId())) == null) {
            employeeIds.add(employee.getEmpId());
            employeeCache.put(employee.getEmpId(), employee);
            addToEmployeeMobileCache(employee);
        }
    }

    public void updateEmployee(Employee employee) {
        String employeePhone = employee.getContactDetails().getPrimaryPhone();
        String employeeId = employee.getEmpId();
        employeeCache.remove(employeeId);
        employeeMobileCache.remove(employeePhone);
        employeeCache.put(employeeId, employee);
        employeeMobileCache.put(employee.getContactDetails().getPrimaryPhone(),employee);
    }

    private void addToEmployeeMobileCache(Employee employee) {
        employeeMobileCache.put(employee.getContactDetails().getPrimaryPhone(),employee);
    }

    public Employee getEmployeeById(String employeeId) {
        Employee employee = employeeCache.get(employeeId);
        if (employee != null)
            return employee;
        else
            return null;
    }

    public Employee getEmployeeByMobile(String mobileNumber) {
        Employee employee = employeeMobileCache.get(mobileNumber);
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

    public static ConcurrentHashMap<String,Employee> getEmployeeCache(){
        return employeeCache;
    }
}
