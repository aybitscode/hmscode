package com.hms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInBetween {
	public static void main(String ar[]) throws ParseException
	{
		Date d = new Date();      // the date in question
        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
        Date a = dateformat3.parse("3/12/2017");
        Date b = dateformat3.parse("4/12/2017");

		System.out.println( a.compareTo(d) * d.compareTo(b) > 0);
	}

}
