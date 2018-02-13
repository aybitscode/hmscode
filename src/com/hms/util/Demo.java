package com.hms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Demo{
	public static void main(String ar[])
	{
		 SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");

         Date date1 = null;
         Date date2 = null;
		date1 = new Date();
		date2 = new Date();
         
		 System.out.println(""+DateDifferenceCalculator.calculateDifference(date1, date2));
	}
}