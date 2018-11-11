package com.aybits.hms.func.employee.api;

import java.util.List;

import org.apache.log4j.Logger;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.employee.cache.EmployeeCache;
import com.aybits.hms.func.employee.dao.EmployeeSelectDAO;

public class EmployeeAPIHelper {

	EmployeeCache employeeCache = new EmployeeCache();
    EmployeeSelectDAO employeeSelectDAO = new EmployeeSelectDAO();


   static Logger Log = Logger.getLogger(EmployeeAPIHelper.class);

    protected String addEmployee(Employee employee) throws HMSRuntimeException {
        String employeeId = null;

        if(!isEmployeeAlreadyPresent(employee)){
            if (employee.getEmpId() != null && employee.getEmpId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
                try {
                    employeeId = employeeCache.addEmployee(employee);
                    if(employeeId == null){
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException npe) {
                    Log.info("Exception occurred while adding employee::"+employee.getEmpId());
                    throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Null Pointer Exception:"+npe.getMessage()));
                }
            }

        }else{
            Log.info("Exception occurred while adding employee::Employee already exists");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_ALREADY_EXISTS, "Employee already exists"));
        }

        return employeeId;
    }
    
    
    
    public List<Employee> fetchAllEmployees() throws HMSRuntimeException{

        List<Employee> employees = null;
        try {
            employees = employeeCache.getAllEmployees();
            		
        }catch(HMSRuntimeException he){

        }finally{
            return employees;
        }

    }

    public Employee getEmployeeByPhone(String mobilePhone) throws HMSRuntimeException{
        Employee employee = null;
        try{
            employee = employeeSelectDAO.getEmployeeByPhone(mobilePhone);
            		
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Employee Details not available for given emploeeId"));
        }finally{
            return employee;
        }

    }

    public Boolean updateEmployee(Employee employee) throws HMSRuntimeException {

        Boolean isEmployeeUpdateSuccessful = false;
        if (employee.getEmpId() == HMSAPIConstants.TO_BE_GENERATED) try {
            isEmployeeUpdateSuccessful = employeeCache.updateEmployee(employee);
            		
        } catch (HMSRuntimeException e) {
            Log.info("Exception occured while adding employee::" + employee.getEmpId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_UPDATE_FAILED, "Adding Employee details failed"));
        }
        return isEmployeeUpdateSuccessful;

    }
    
    public Boolean isEmployeeAlreadyPresent(Employee employeeFromUI){

        Employee employeeFromDB = null;

        Boolean isEmployeeAlreadyPresent = false;
        String primaryMobileNumber = employeeFromUI.getContactDetails().getPrimaryMobileNumber();
        		

        try{
            employeeFromDB = employeeSelectDAO.getEmployeeByPhone(primaryMobileNumber);
            if(null != employeeFromDB){
                isEmployeeAlreadyPresent = true;
            }
        }catch(HMSRuntimeException he){

        }finally{
            return isEmployeeAlreadyPresent;
        }
    }
}
