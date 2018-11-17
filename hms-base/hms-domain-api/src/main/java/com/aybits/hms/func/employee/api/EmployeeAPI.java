package com.aybits.hms.func.employee.api;

import java.util.ArrayList;
import java.util.List;

import com.aybits.hms.func.common.api.HMSAPIResponse;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.employee.dao.EmployeeDAO;
import com.aybits.hms.func.employee.dao.EmployeeSelectDAO;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;

public class EmployeeAPI implements HmsAPI {
	
	EmployeeAPIValidator empAPIValidator = new EmployeeAPIValidator();
    static Logger Log = Logger.getLogger(EmployeeAPI.class);

    EmployeeAPIHelper employeeAPIHelper = new EmployeeAPIHelper();

    public EmployeeAPI(){

    }

    public List<Employee> getAllEmployees(){

        //	List<Employee> employeeList = HMSCache.custCache.getAllEmployees();
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            allEmployees = EmployeeSelectDAO.getAllEmployees();
        } catch (HMSRuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allEmployees;
    }

    public Employee getEmployeeByPhone(String mobilePhone){
        //Employee employee = HMSCache.custCache.getEmployeeByMobile(mobilePhone);
        Employee employee = null;
        EmployeeSelectDAO employeeSelectDAO = null;
        try {
            employee = employeeSelectDAO.getEmployeeByPhone(mobilePhone);
        } catch (HMSRuntimeException e) {
            e.printStackTrace();
        }

        return employee;
    }

    /*public Boolean addEmployee(Employee employee){
        EmployeeDAO dbOps = new EmployeeDAO();
        Boolean isEmployeeAdditionSuccessful = dbOps.addEmployee(employee);
		/*EmployeeCache employeeCache = new EmployeeCache();
		if(isEmployeeAdditionSuccessful){
			employeeCache.addEmployee(employee);
		}*/
      //  return isEmployeeAdditionSuccessful;
    //}
  //added by nouman
    public String addEmployee(Employee employee){
        EmployeeDAO dbOps = new EmployeeDAO();
        
        String employeeid = dbOps.addEmployee(employee);
		/*EmployeeCache employeeCache = new EmployeeCache();
		if(isEmployeeAdditionSuccessful){
			employeeCache.addEmployee(employee);
		}*/
        return employeeid;
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
        EmployeeSelectDAO employeeSelectDAO = null;
        employee = employeeSelectDAO.getEmployeeById(employeeId);

        return employee;
    }

    @Override
    public Object init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String process(JSONObject data) throws HMSRuntimeException {
        Log.info("[Entry]::employeeAPI.process");
        String status = null;
        String message = null;
        String empId = null;
        JSONObject responseData = new JSONObject();
        
        Employee employee = null;
        try {
            employee = (Employee) HMSJSONParser.convertJSONToObject(data.getJSONObject(HMSJSONConstants.EMPLOYEE).toString(), Employee.class);
            empId = employeeAPIHelper.addEmployee(employee);
            responseData.put(HMSJSONConstants.EMPLOYEE_ID,data);
            status = HMSAPIServiceConstants.HMS_RESPONSE_SUCCESS;
            message = "Employee with ["+empId+"] created successfully";
            Log.info(message);
        }catch (HMSRuntimeException e) {
            throw e;
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_EMPLOYEE_DETAILS,"Invalid Employee details provided :"+e.getMessage()));
        }finally{
            Log.info("[Exit]::employeeAPI.process");
            return createHMSAPIResponse(status,message,responseData);
        }
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {
    	
    	 Employee employee = null;
         try {
             employee = (Employee) HMSJSONParser.convertJSONToObject(object.getJSONObject(HMSJSONConstants.EMPLOYEE).toString(), Employee.class);
         }catch(Exception e){
             throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_EMPLOYEE_DETAILS,"Invalid Employee details provided"));
         }
         empAPIValidator.validateEmployee(employee);

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
