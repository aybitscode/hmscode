package sparkInit;

<<<<<<< HEAD
import com.aybits.hms.arch.util.HmsConfig;
import org.apache.log4j.Logger;
=======
import common.HmsConfig;
>>>>>>> adil_develop

import static spark.Spark.port;
import static spark.Spark.options;
import static spark.Spark.before;


public class HmsRestServiceStartup {
    Logger log = Logger.getLogger(HmsRestServiceStartup.class);
    HmsRestHttpService httpService = new HmsRestHttpService();

    public void start(String[] args) {
        startSparkServer();
        httpService.registerHttpAPIs();
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
<<<<<<< HEAD
            int port = Integer.parseInt(HmsConfig.getRestProperty("HMS_PORT"));
            log.info("starting spark server on port "+port);
=======
            int port = Integer.parseInt(HmsConfig.getProperty("HMS_PORT"));
            System.out.println("starting spark server on port "+port);
>>>>>>> adil_develop
            port(port);
            enableCORS("*", "*", "*");
        }catch (Exception e){
            log.info("Error while starting spark server.");
        }
    }

    private void awaitSparkServerInitialization() {
        log.info("Spark APIs started.");
    }
}
