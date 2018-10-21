package com.aybits.hms.sparkInit;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.util.HmsConfig;

public class HmsRestServiceMain {

    public static void main(String[] args) throws Exception{
        HmsRestServiceMain serviceMain = new HmsRestServiceMain();
        serviceMain.initConfiguration();
        serviceMain.startServicesInSpark(args);
    }

    void initConfiguration() throws Exception{
        System.out.println("Loading HMS Configuration properties.");
        HmsConfig hmsConfig = new HmsConfig();
        DBCPConnection initDBCP = new DBCPConnection();
    }

    void startServicesInSpark(String[] args){
        HmsRestServiceStartup serviceStartup = new HmsRestServiceStartup();
        serviceStartup.start(args);
    }
}
