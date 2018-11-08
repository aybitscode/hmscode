package com.aybits.hms.login;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.*;
import com.aybits.hms.func.common.api.HMSAPI;
import com.aybits.hms.func.login.api.LoginAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginRequestProcessor implements HMSRequestProcessor {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    LoginAPI loginAPI;

    @Override
    public void validateRequestData(JSONObject dataJson) throws HMSRuntimeException{
        loginAPI.validate(dataJson);
    }

    @Override
    public HMSResponse processRequest(HMSRequest request) {
        System.out.println("Login request handler invoked");
        return null;
    }
}
