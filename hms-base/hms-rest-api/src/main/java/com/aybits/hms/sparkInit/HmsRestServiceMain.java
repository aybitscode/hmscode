package sparkInit;

import com.aybits.hms.sparkInit.HmsRestServiceStartup;
import common.HmsConfig;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.util.Properties;

public class HmsRestServiceMain {

    Logger Log = Logger.getLogger(HmsRestServiceMain.class);


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
