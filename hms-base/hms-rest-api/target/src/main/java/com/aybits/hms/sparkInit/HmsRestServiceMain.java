package com.aybits.hms.sparkInit;

import com.aybits.hms.arch.util.HmsConfig;
import org.apache.log4j.Logger;

public class HmsRestServiceMain {
    Logger Log = Logger.getLogger(HmsRestServiceMain.class);
    public static void main(String[] args) throws Exception{
        HmsRestServiceMain serviceMain = new HmsRestServiceMain();
        serviceMain.initConfiguration();
        serviceMain.startServicesInSpark(args);
    }

    void initConfiguration(){
        Log.info("Loading properties from hms-rest.properties file.");
        HmsConfig hmsConfig = new HmsConfig();
    }

    void startServicesInSpark(String[] args){
        HmsRestServiceStartup serviceStartup = new HmsRestServiceStartup();
        serviceStartup.start(args);
    }
}
