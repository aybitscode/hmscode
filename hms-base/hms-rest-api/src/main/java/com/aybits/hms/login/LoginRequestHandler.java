package com.aybits.hms.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.aybits.hms.common.HmsRequestHandler;
import org.apache.log4j.Logger;


import spark.Request;
import spark.Response;

public class LoginRequestHandler implements HmsRequestHandler {

    static Logger Log = Logger.getLogger(LoginRequestHandler.class);
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Login request handler invoked");


        return "{'login_status':'success'}";
        //return "{id: undefined, fullName: \"Demo\", email: \"demo@demo.com\", token: \"fake-jwt-token\"}";
    }
}
