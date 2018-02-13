package com.hms.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentUtilDate {

  public static void main(String[] args) {
//    java.util.Date date = new java.util.Date();     //util current date
	 // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a"); //util current date with date and time am/pm
	  Date date = new Date();
	//System.out.println(dateFormat.format(date));
	  SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
    System.out.println("Util current date is "+timeFormat.format(date));
	  
	  
	  //Splitting current date and time in util format
//	  try {
//	        DateFormat f = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
//	        Date d = f.parse("18-09-2011 11:16:12 AM");
//	        DateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
//	        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
//	        System.out.println("Date: " + date.format(d));
//	        System.out.println("Time: " + time.format(d));
//	    } catch (ParseException e) {
//	        e.printStackTrace();
//	    }
  }

}