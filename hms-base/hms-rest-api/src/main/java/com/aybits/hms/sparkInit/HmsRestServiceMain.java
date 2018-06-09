package com.aybits.hms.sparkInit;

import com.aybits.hms.common.HmsConfig;

public class HmsRestServiceMain {

    public static void main(String[] args) throws Exception{
        HmsRestServiceMain serviceMain = new HmsRestServiceMain();
        serviceMain.initConfiguration();
        serviceMain.startServicesInSpark(args);
    }

    void initConfiguration() throws Exception{
        System.out.println("Loading properties from hms-rest.properties file.");
        HmsConfig hmsConfig = new HmsConfig();
    }

    void startServicesInSpark(String[] args){
        HmsRestServiceStartup serviceStartup = new HmsRestServiceStartup();
        serviceStartup.start(args);
    }
}
