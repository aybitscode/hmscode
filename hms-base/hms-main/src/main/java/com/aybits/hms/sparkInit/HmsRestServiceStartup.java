package com.aybits.hms.sparkInit;

import com.aybits.hms.arch.util.HmsConfig;
import org.springframework.beans.factory.annotation.Autowired;

import static spark.Spark.port;
import static spark.Spark.options;
import static spark.Spark.before;


public class HmsRestServiceStartup {

   // @Autowired
    HmsRestHttpService hmsRestHttpService = new HmsRestHttpService();

    public void start(String[] args) {
        startSparkServer();
        hmsRestHttpService.registerHttpAPIs();
        awaitSparkServerInitialization();
    }


    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }

    void startSparkServer() {
        try {
            int port = Integer.parseInt(HmsConfig.getRestProperty("app.port"));
            System.out.println("starting spark server on port "+port);
            port(port);
            enableCORS("*", "*", "*");
        }catch (Exception e){
            System.out.println("Error while starting spark server.");
        }
    }

    private void awaitSparkServerInitialization() {
        System.out.println("Spark APIs started.");
    }
}
