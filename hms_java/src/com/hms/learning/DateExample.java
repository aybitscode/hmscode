package com.hms.learning;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateExample {

    private static final DateFormat dateFormat = new SimpleDateFormat("hh:mm a");

    public static void main(String[] args) {

    	Calendar c1 = Calendar.getInstance();
        System.out.println(dateFormat.format(c1.getTime()));

        // convert date to calendar
        Calendar c = Calendar.getInstance();
 //       c.setTime(currentDate);

//        // manipulate date
//        c.add(Calendar.YEAR, 1);
//        c.add(Calendar.MONTH, 1);
//        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 1);
//        c.add(Calendar.MINUTE, 1);
//        c.add(Calendar.SECOND, 1);

        // convert calendar to date
        //Date currentDatePlusOne = c.getTime();

        System.out.println(dateFormat.format(c.getTime()));

    }

}