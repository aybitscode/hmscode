package login;

import com.aybits.hms.sparkInit.HmsRestHttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
import com.aybits.hms.common.HmsRequestHandler;
import org.apache.log4j.Logger;
=======
import common.HmsRequestHandler;
>>>>>>> adil_develop
import spark.Request;
import spark.Response;

public class LoginRequestHandler implements HmsRequestHandler {
<<<<<<< HEAD
    static Logger Log = Logger.getLogger(LoginRequestHandler.class);
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String handleRequest(Request request, Response response) {
        Log.info("Login request handler invoked");
=======
    ObjectMapper mapper = new ObjectMapper(); 

    @Override
    public String handleRequest(Request request, Response response) {
        System.out.println("Login request handler invoked");
       // request.body();
>>>>>>> adil_develop

        return "{'login_status':'success'}";
        //return "{id: undefined, fullName: \"Demo\", email: \"demo@demo.com\", token: \"fake-jwt-token\"}";
    }
}
