package com.aybits.hms.sparkInit;

import com.aybits.hms.arch.util.HmsConfig;
import org.apache.log4j.Logger;

import static spark.Spark.port;

public class HmsRestServiceStartup {
    Logger log = Logger.getLogger(HmsRestServiceStartup.class);
    HmsRestHttpService httpService = new HmsRestHttpService();

    public void start(String[] args) {
        startSparkServer();
        httpService.registerHttpAPIs();
        awaitSparkServerInitialization();
    }

    void startSparkServer() {
        try {
            int port = Integer.parseInt(HmsConfig.getRestProperty("HMS_PORT"));
            log.info("starting spark server on port "+port);
            port(port);
        }catch (Exception e){
            log.info("Error while starting spark server.");
        }
    }

    private void awaitSparkServerInitialization() {
        log.info("Spark APIs started.");
    }
}
