package com.aybits.hms.Employee;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HmsRequestHandler;
import com.aybits.hms.common.HmsResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.employee.api.EmployeeAPI;
import com.aybits.hms.func.employee.beans.Employee;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

import java.util.List;

public class EmployeeRequestHandler extends HmsRequestHandler {
    static Logger Log = Logger.getLogger(EmployeeRequestHandler.class);

    @Override
    public ValidationResult validateRequestData(Request request) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        return result;
    }

    @Override
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

    private HmsResponse getHmsResponse(Object responseData, boolean result) {
        HmsResponse response = null;
       /* if (result) {
            response = new HmsResponse(200, "SUCCESS", responseData, null, null, "SUCCESS");
        } else {
            response = new HmsResponse(200, "FAILED", responseData, null, null, "FAILED");
        }*/
        return response;
    }
}
