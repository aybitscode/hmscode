package com.aybits.hms.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.aybits.hms.common.HmsRequestHandler;
import spark.Request;
import spark.Response;

public class LoginRequestHandler implements HmsRequestHandler {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Login request handler invoked");

        return "{'login_status':'success'}";
    }
}
