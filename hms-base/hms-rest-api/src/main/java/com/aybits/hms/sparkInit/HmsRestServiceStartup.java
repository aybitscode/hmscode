package com.aybits.hms.sparkInit;

import com.aybits.hms.arch.util.HmsConfig;

import static spark.Spark.port;

public class HmsRestServiceStartup {
    HmsRestHttpService httpService = new HmsRestHttpService();

    public void start(String[] args) {
        startSparkServer();
        httpService.registerHttpAPIs();
        awaitSparkServerInitialization();
    }

    void startSparkServer() {
        try {
            int port = Integer.parseInt(HmsConfig.getRestProperty("HMS_PORT"));
            System.out.println("starting spark server on port "+port);
            port(port);
        }catch (Exception e){
            System.out.println("Error while starting spark server.");
        }
    }

    private void awaitSparkServerInitialization() {
        System.out.println("Spark APIs started.");
    }
}
