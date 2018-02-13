package com.hms.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTimeStamp {
	public static Timestamp getSQLTimestamp(String dateString) throws ParseException {
	     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a"); 
	     Date date = dateFormat.parse(dateString);
	     long time = date.getTime();

	     return new Timestamp(time);
	}
 
}
