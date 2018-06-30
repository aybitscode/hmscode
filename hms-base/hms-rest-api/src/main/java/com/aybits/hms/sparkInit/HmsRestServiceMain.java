package sparkInit;

import common.HmsConfig;

import java.io.FileReader;
import java.util.Properties;

public class HmsRestServiceMain {
<<<<<<< HEAD
    Logger Log = Logger.getLogger(HmsRestServiceMain.class);
=======
>>>>>>> adil_develop

    public static void main(String[] args) throws Exception{
        HmsRestServiceMain serviceMain = new HmsRestServiceMain();
        serviceMain.initConfiguration();
        serviceMain.startServicesInSpark(args);
    }

<<<<<<< HEAD
    void initConfiguration(){
        Log.info("Loading properties from hms-rest.properties file.");
=======
    void initConfiguration() throws Exception{
        System.out.println("Loading properties from hms-rest.properties file.");
>>>>>>> adil_develop
        HmsConfig hmsConfig = new HmsConfig();
    }

    void startServicesInSpark(String[] args){
        HmsRestServiceStartup serviceStartup = new HmsRestServiceStartup();
        serviceStartup.start(args);
    }
}
