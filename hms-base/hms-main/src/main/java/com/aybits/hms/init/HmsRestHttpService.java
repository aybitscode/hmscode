package com.aybits.hms.init;


import com.aybits.hms.arch.util.HmsConfig;
import com.aybits.hms.common.GenericRequestHandler;
import com.aybits.hms.common.HMSRequestHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static spark.Spark.get;
import static spark.Spark.post;

public class HmsRestHttpService {
    static Logger Log = Logger.getLogger(HmsRestHttpService.class);
    private static final String API_PREFIX = "/api";
    private static final String CONTENT_TYPE = "application/json";

    @Autowired
    HMSRequestHandler hmsRequestHandler;
    

    public void registerHttpAPIs() {

        String appName = HmsConfig.getRestProperty("app.name");
        String version = HmsConfig.getRestProperty("app.version");

        registerPostApi(appName+"/"+API_PREFIX+"/"+version+"/do/*", hmsRequestHandler);
    }

    private static void registerPostApi(String apiPath, GenericRequestHandler requestHandler) {
        Log.info("Registering post api for "+apiPath);
        post(apiPath, CONTENT_TYPE, (request, response) -> {
            return requestHandler.handleRequest(request, response);
        });
    }

    private static void registerGetApi(String apiPath, GenericRequestHandler requestHandler) {
        Log.info("Registering get api for "+apiPath);
        get(apiPath, CONTENT_TYPE, (request, response) -> {
            return requestHandler.handleRequest(request, response);
        });
    }
}
