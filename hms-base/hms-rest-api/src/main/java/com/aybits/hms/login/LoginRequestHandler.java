package com.aybits.hms.login;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.ValidationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aybits.hms.common.GenericRequestHandler;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class LoginRequestHandler implements GenericRequestHandler {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void validateRequestData(JSONObject dataJson) {
        ValidationResult result = new ValidationResult();
        result.setCode(100);
        result.setMessage("In Valida Request");
        //return result;
    }

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Login request handler invoked");

        ValidationResult result = validateRequest(request);
        if (result != null) {
            return result.getMessage();
        }

        return "{'login_status':'success'}";
        //return "{id: undefined, fullName: \"Demo\", email: \"demo@demo.com\", token: \"fake-jwt-token\"}";
    }

    @Override
    public String populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
        return null;
    }

    @Override
    public String populateGenericErrorResponse(Exception e, String tokenId) {
        return null;
    }
}
