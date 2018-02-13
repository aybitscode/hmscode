package com.hms.learning;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
class Example2
{
   public static void main(String[] args)
   {
       //Input date in String format
       String input = "15/02/2014 11:22 am";
       //Date/time pattern of input date
       DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
       //Date/time pattern of desired output date
       DateFormat outputformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
       Date date = null;
       String output = null;
       try{
          //Conversion of input String to date
    	  date= df.parse(input);
          //old date format to new date format
    	  System.out.println(new Timestamp(date.getTime()));
    	  output = outputformat.format(date);
    	  System.out.println(output);
    	}catch(ParseException pe){
    	    pe.printStackTrace();
    	 }
   }
}