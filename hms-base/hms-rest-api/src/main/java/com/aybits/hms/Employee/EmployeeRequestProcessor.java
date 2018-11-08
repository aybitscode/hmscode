package com.aybits.hms.Employee;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.*;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.employee.api.EmployeeAPI;
import com.aybits.hms.func.employee.beans.Employee;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

public class EmployeeRequestProcessor implements HMSRequestProcessor {
    static Logger Log = Logger.getLogger(EmployeeRequestProcessor.class);



    @Override
    public void validateRequestData(JSONObject dataJSON) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida HMSRequest");
        //return result;
    }

    @Override
    public HMSResponse processRequest(HMSRequest request) {
        Log.info("Employee request handler invoked");


        Log.info("Hotel request handler invoked");

        String operation = request.getOperation();
        String entity = request.getEntity();
        String data = request.getData();
        String action = operation + "/" + entity;
        String tokenId = request.getTokenId();
        HMSResponse hmsResponse = null;

        JSONObject dataJSON = null;

        try {
            dataJSON = new JSONObject(data);
            validateRequestData(dataJSON);
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex, tokenId);
        }catch(Exception ex){
            hmsResponse = populateGenericErrorResponse(ex,tokenId);
        }finally{
            if (hmsResponse != null) {
                return hmsResponse;
            }
        }

        switch (action) {
            case "fetch-all":
                hmsResponse = getAllEmployees(request);
                break;
            case "fetch-by-phone":
                hmsResponse = getEmployeeByPhone(request);
                break;
            case "add":
                hmsResponse = addEmployee(request);
                break;
            case "update":
                hmsResponse = updateEmployee(request);
                break;
            /*case "getEmployeeNameByMobile":
                message = getEmployeeNameByMobile(request);
                break;*/
            case "fetch-id":
                hmsResponse = getEmployeeId(request);
                break;
            case "fetch-by-id":
                hmsResponse = getEmployeeById(request);
                break;
        }
        return hmsResponse;
    }


    private HMSResponse addEmployee(HMSRequest request) {
        Log.info("in adding new employee");
        HMSResponse hmsResponse = null;
        String tokenId = request.getTokenId();
        try {
            String jsonString = request.getData();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = employeeAPI.addEmployee(employee);
            hmsResponse = populateHMSResponse(tokenId, "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        }  finally{
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return hmsResponse;
        }
    }

    private HMSResponse updateEmployee(HMSRequest request) {
        Log.info("in updating employee");
        HMSResponse hmsResponse = null;
        String tokenId = request.getTokenId();
        try {
            String jsonString = request.getData();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = employeeAPI.updateEmployee(employee);
            hmsResponse = populateHMSResponse(tokenId, "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        }  finally{
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return hmsResponse;
        }
    }

    private HMSResponse getEmployeeById(HMSRequest request) {
        Log.info("in getEmployeeById");
        HMSResponse hmsResponse = null;
        String tokenId = request.getTokenId();
        try {
            String jsonString = request.getData();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            Employee result = employeeAPI.getEmployeeById(employee.getEmpId());
            hmsResponse = populateHMSResponse(tokenId, "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        }  finally{
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return hmsResponse;
        }
    }

    private HMSResponse getEmployeeByPhone(HMSRequest request) {
        Log.info("in getEmployeeByPhone");
        HMSResponse hmsResponse = null;
        String tokenId = request.getTokenId();
        try {
            String jsonString = request.getData();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            Employee result = employeeAPI.getEmployeeByPhone(employee.getContactDetails().getPrimaryPhone());
            hmsResponse = populateHMSResponse(tokenId, "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        }  finally{
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return hmsResponse;
        }
    }

    private HMSResponse getEmployeeId(HMSRequest request) {
        Log.info("in  getEmployeeId");
        HMSResponse hmsResponse = null;
        String tokenId = request.getTokenId();
        try {
            String jsonString = request.getData();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            String result = employeeAPI.getEmployeeId(employee.getContactDetails().getPrimaryPhone());
            employee.setEmpId(result);
            hmsResponse = populateHMSResponse(tokenId, "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        }  finally{
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return hmsResponse;
        }
    }


    private HMSResponse getAllEmployees(HMSRequest request) {
        Log.info("in get all employees");
        HMSResponse hmsResponse = null;
        String tokenId = request.getTokenId();
        try {
            EmployeeAPI employeeAPI = new EmployeeAPI();
            List<Employee> result = employeeAPI.getAllEmployees();
            hmsResponse = populateHMSResponse(tokenId, "");
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        }  finally{
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return hmsResponse;
        }
    }

}
