package com.aybits.hms.sparkInit;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.util.HmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HmsRestServiceMain {

    @Autowired
    HmsConfig hmsConfig;

    @Autowired
    DBCPConnection initDBCP;

    @Autowired
    HmsRestServiceStartup serviceStartup;

    public static void main(String[] args) throws Exception{
        System.out.println("Loading Spring and HMS Configuration properties.");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-hms-rest.xml");
        HmsRestServiceMain serviceMain = (HmsRestServiceMain)context.getBean("hmsServiceMain");
        serviceMain.startServicesInSpark(args);
    }

    void startServicesInSpark(String[] args){
        serviceStartup.start(args);
    }
}
