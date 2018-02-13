package com.hms.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentSqlDate {  
    public static void main(String[] args) {  
 //       long millis=System.currentTimeMillis();  
  //      java.sql.Date date=new java.sql.Date(millis);  
  //      System.out.println("Sql Date is "+date);
//        
//        java.util.Date udate = new java.util.Date(date.getTime());
//        System.out.println("Util date is "+udate);
//        
//        
//        java.sql.Date sqlDate = new java.sql.Date(udate.getTime());
//        System.out.println("util date converted to sql date "+sqlDate);
    	
//    	Date dateBefore30Days = DateUtils.addDays(new Date(),-1);
//    	System.out.println("dateBefore30Days is "+dateBefore30Days);
        
        //method 1
    	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        //method 2 - via Date
        Date date = new Date();
        System.out.println(new Timestamp(date.getTime()));

        //return number of milliseconds since January 1, 1970, 00:00:00 GMT
        System.out.println(timestamp.getTime());

        //format timestamp
        System.out.println(sdf.format(timestamp));
        
        
        
    }  
} 