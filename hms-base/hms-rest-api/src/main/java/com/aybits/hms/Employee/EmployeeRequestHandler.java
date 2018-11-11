package com.aybits.hms.Employee;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HMSErrorResponse;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.employee.api.EmployeeAPI;
import com.aybits.hms.func.employee.beans.Employee;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

import java.util.List;

public abstract class EmployeeRequestHandler implements HMSRequestHandler {
    static Logger Log = Logger.getLogger(EmployeeRequestHandler.class);
    public ValidationResult validateRequestData(HMSJsonRequestComponents components) throws HMSException {
        return null;
    }

    @Override
    public ValidationResult validateRequestData(Request request) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        return result;
    }

    public String handleRequest(Request request, Response response) {
        Log.info("Employee request handler invoked");

        ValidationResult result = validateRequest(request);
        if (result != null) {
            return result.getMessage();
        }

        String action = request.pathInfo().split("/")[2];
        String message = "";
        switch (action) {
            case "fetch-all":
                message = getAllEmployees(request);
                break;
            case "fetch-by-phone":
                message = getEmployeeByPhone(request);
                break;
            case "add":
                message = addEmployee(request);
                break;
            case "update":
                message = updateEmployee(request);
                break;
            /*case "getEmployeeNameByMobile":
                message = getEmployeeNameByMobile(request);
                break;*/
            case "fetch-id":
                message = getEmployeeId(request);
                break;
            case "fetch-by-id":
                message = getEmployeeById(request);
                break;
        }
        return message;
    }

    public String populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
        Log.error(he.getHmsErrorInfo());
        HMSErrorResponse hmsErrorResponse = new HMSErrorResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, he.getHmsErrorInfo().getErrorMessage(), he.getHmsErrorInfo().getErrorCode());
        return HMSJSONParser.convertObjectToJSON(hmsErrorResponse);
    }

    @Override
    public String populateGenericErrorResponse(Exception e, String tokenId) {
        Log.error(e.getCause());
        HMSErrorResponse hmsErrorResponse = new HMSErrorResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_SYSTEM_ERROR);
        return HMSJSONParser.convertObjectToJSON(hmsErrorResponse);
    }


    private String addEmployee(Request request) {
        Log.info("in adding new employee");
        try {
            String jsonString = request.body().toString();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = employeeAPI.addEmployee(employee);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(employee, result));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while adding employee", false));
        }
    }

    private String updateEmployee(Request request) {
        Log.info("in updating employee");
        try {
            String jsonString = request.body().toString();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            boolean result = employeeAPI.updateEmployee(employee);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(employee, result));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while updating employee", false));
        }
    }

    private String getEmployeeById(Request request) {
        Log.info("in getEmployeeById");
        try {
            String jsonString = request.body().toString();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            Employee result = employeeAPI.getEmployeeById(employee.getEmpId());
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting employee data bi ID", false));
        }
    }

    private String getEmployeeByPhone(Request request) {
        Log.info("in getEmployeeByPhone");
        try {
            String jsonString = request.body().toString();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            Employee result = employeeAPI.getEmployeeByPhone(employee.getContactDetails().getPrimaryPhone());
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting employee data bi phone", false));
        }
    }

    private String getEmployeeId(Request request) {
        Log.info("in  getEmployeeId");
        try {
            String jsonString = request.body().toString();
            EmployeeAPI employeeAPI = new EmployeeAPI();
            Employee employee = (Employee) HMSJSONParser.convertJSONToObject(jsonString, Customer.class);
            String result = employeeAPI.getEmployeeId(employee.getContactDetails().getPrimaryPhone());
            employee.setEmpId(result);
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(employee, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting employee data bi ID", false));
        }
    }


    private String getAllEmployees(Request request) {
        Log.info("in get all employees");
        try {
            EmployeeAPI employeeAPI = new EmployeeAPI();
            List<Employee> result = employeeAPI.getAllEmployees();
            return HMSJSONParser.convertObjectToJSON(getHmsResponse(result, true));
        } catch (Exception e) {
            return HMSJSONParser.convertObjectToJSON(getHmsResponse("Error while getting all employee data", false));
        }
    }

    private HMSResponse getHmsResponse(Object responseData, boolean result) {
        HMSResponse response = null;
       /* if (result) {
            response = new HMSAPIResponse(200, "SUCCESS", responseData, null, null, "SUCCESS");
        } else {
            response = new HMSAPIResponse(200, "FAILED", responseData, null, null, "FAILED");
        }*/
        return response;
    }
}
