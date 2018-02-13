package com.hms.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDifferenceCalculator {
	public static long calculateDifference(Date startDate, Date endDate)
	{
		long diffDays = 0;

		try {
			//in milliseconds
			long diff = endDate.getTime() - startDate.getTime();
//			long diffSeconds = diff / 1000 % 60;
//			long diffMinutes = diff / (60 * 1000) % 60;
//			long diffHours = diff / (60 * 60 * 1000) % 24;
			diffDays = diff / (24 * 60 * 60 * 1000);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return diffDays;
	}
	public static boolean validateCheckinTime(String checkin_time)
	{
		boolean b = false;
		Date selected_date = null;
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
		Date current_date = new Date();
		try {
			selected_date = timeFormat.parse(checkin_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int diffDays = (int) calculateDatePeriod(current_date, selected_date);
		
		if(diffDays == 0)
		{
			int hours = (int) calculateCheckinHours(current_date, selected_date);
			if(hours >= 0)
				b = true;
		}
		else 
			b = true;
		
		return b;
		
	}
	public static long calculateDatePeriod(Date startDate, Date endDate)
	{
		long diffDays = 0;

		try {
			long diff = endDate.getTime() - startDate.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return diffDays;
		
	}
	public static long calculateCheckinHours(Date startDate, Date endDate)
	{
		long diffHours = 0;
		try {
			//in milliseconds
			long diff = endDate.getTime() - startDate.getTime();
			diffHours = diff / (60 * 60 * 1000) % 24;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffHours;
	}
	public static long calculateCheckoutHours(Date startDate, Date endDate)
	{
		System.out.println("checkin Date is"+startDate);
		System.out.println("stardate is"+endDate);
		long diffDays = 0, diffHours = 0;
		try {
			//in milliseconds
			long diff = endDate.getTime() - startDate.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);
			diffHours = diff / (60 * 60 * 1000) % 24;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(diffDays > 0)
			diffHours += diffDays * 24;

		return diffHours;
	}
	public static String calculateTimePeriod(Date startDate, Date endDate)
	{	
		long diffDays = 0, diffHours = 0, diffMinutes = 0;
		try {
			//in milliseconds
			long diff = endDate.getTime() - startDate.getTime();
			diffMinutes = diff / (60 * 1000) % 60;
			diffHours = diff / (60 * 60 * 1000) % 24;
			diffDays = diff / (24 * 60 * 60 * 1000);		

		} catch (Exception e) {
			e.printStackTrace();
		}
		if(diffDays > 0)
		{
			System.out.println("diff days are sib"+diffDays);
			return ""+diffDays;
			
		}
		else if(diffDays == 0 && diffHours > 0)
		{
			String time = null;
			if(diffMinutes == 0)
			{
				if(diffHours > 0)
					time =  ""+diffHours+" Hours";
				else
					time = "";
			}
			else if(diffMinutes > 0)
			{
				if(diffHours > 0)
					time = ""+diffHours+" Hours"+diffMinutes+" Minutes";					
				else 
					time = "";
			}
			return time;
		}
		else if(diffDays == 0 && diffHours == 0)
		{
			return "1";
		}
		else
		return "";
	}	
	public static long validateBookingDate(Date bookingDate)
	{
		Date currentDate = new Date();
		long diffDays = 0;

		try {
			long diff = bookingDate.getTime() - currentDate.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return diffDays;
	}
	public static long validateLimit(Date param)
	{
		Date currentDate = new Date();
		long diffDays = 0;

		try {
			//in milliseconds
			long diff = param.getTime() - currentDate.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return diffDays;
	}

	public static long validateCheckoutDate(Date checkOutDate, Date bookingDate)
	{
		DateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
		long diffDays = 0;

		try {
			//in milliseconds
			long diff = checkOutDate.getTime() - bookingDate.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);


		} catch (Exception e) {
			e.printStackTrace();
		}
		String s1 =date_format.format(bookingDate);
		String s2 = date_format.format(checkOutDate); 
		if(diffDays == 0)
		{
			if(!s1.equals(s2))
				diffDays = -1;
		}
		return diffDays;
	}
	public static boolean validateCheckoutTime(Date checkinTime, Date checkoutTime)
	{
		boolean b = false;
	    long diff = checkoutTime.getTime() - checkinTime.getTime();
	    long diffHours = diff / (60 * 60 * 1000);	    
	    if(diffHours < 3)
	    	b = true;
	    
		return b;
		
	}

 
}

