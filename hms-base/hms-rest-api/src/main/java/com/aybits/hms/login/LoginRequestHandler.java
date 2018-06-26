package login;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.HmsRequestHandler;
import spark.Request;
import spark.Response;

public class LoginRequestHandler implements HmsRequestHandler {
    ObjectMapper mapper = new ObjectMapper(); 

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Login request handler invoked");
       // request.body();

        return "{'login_status':'success'}";
        //return "{id: undefined, fullName: \"Demo\", email: \"demo@demo.com\", token: \"fake-jwt-token\"}";
    }
}
