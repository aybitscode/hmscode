package com.hms.date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToTimeStamp{
  public static Timestamp convertStringToTimestamp(String str_date) {
    try {
      DateFormat formatter;
      formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date date = (Date) formatter.parse(str_date);
      java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

      return timeStampDate;
    } catch (ParseException e) {
      System.out.println("Exception :" + e);
      return null;
    }
  }
  public static void main(String r[])
  {
	  StringToTimeStamp st = new StringToTimeStamp();
	  System.out.println(st.convertStringToTimestamp("2017-01-09"));
  }
}