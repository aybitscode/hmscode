package com.aybits.hms.func.employee.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIValidator;
import com.aybits.hms.func.employee.beans.Employee;


public class EmployeeAPIValidator {
	
	HMSAPIValidator hmsapiValidator = new HMSAPIValidator();

    protected void validateEmployee(Employee employee) throws HMSRuntimeException {

        if(HMSAPIValidator.isBlankString(employee.getEmpId())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_EMPLOYEE_ID,"Invalid Employee ID provided or employee-id is null"));
        }
        //what is this length //added by nouman
        if(employee.getEmpId().length() > HMSAPIConstants.HMS_ID_LENGTH){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_LENGTH_CHECK_FAILED,"Employee ID length is more than the allowed size"));
        }

        hmsapiValidator.validateContactDetails(employee.getContactDetails());

        hmsapiValidator.validateAddress(employee.getEmployeeAddress());

    }

    

}
