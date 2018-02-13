package com.hms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SplitDate {
	public static String returnDate(java.sql.Timestamp timeStamp)
	{
		String str_date = null;
		String stringStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeStamp);
  	  try {    
	      Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringStamp);
	      str_date = new SimpleDateFormat("yyyy-MM-dd").format(date);
	      System.out.println("split date is"+str_date);
	      } 
	    catch (ParseException e) {
	    //Handle exception here
	     e.printStackTrace();
	     }
	return str_date;
	}
 
}
