package com.hms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToUtilDate {
	public static Date getDate_MMDDYYYY(String param)
	{
		//String testDate = "29-Apr-2010,13:00:14 PM";
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
			date = formatter.parse(param);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDate_YYYYMMDD(String param)
	{
		//String testDate = "29-Apr-2010,13:00:14 PM";
		DateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd");
		Date date = null;
		try {
			date = formatter.parse(param);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
