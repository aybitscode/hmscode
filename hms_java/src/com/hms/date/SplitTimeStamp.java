package com.hms.date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SplitTimeStamp {

    public static void main(String[] args) {
    	  try {    
    	      String originalString = "2017-12-21 21:00:54.706000";
    	      Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(originalString);
    	      String newstr = new SimpleDateFormat("MM/dd/yyyy, H:mm:ss").format(date);
    	      String str_date = new SimpleDateFormat("MM/dd/yyyy").format(date);
    	      String str_time = new SimpleDateFormat("H:mm:ss").format(date);
    	       System.out.println("\n"+newstr+"\n");
    	       System.out.println("date is"+str_date);
    	       System.out.println("time is"+str_time);
    	      } 
    	    catch (ParseException e) {
    	    //Handle exception here
    	     e.printStackTrace();
    	     }
    }
}