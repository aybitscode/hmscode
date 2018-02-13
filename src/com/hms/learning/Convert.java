package com.hms.learning;
import java.text.SimpleDateFormat;
 
public class Convert {
 
    public static void main(String[] args) {
        try {
            String now = new SimpleDateFormat("hh:mm aa").format(new java.util.Date().getTime());
            System.out.println("time in 12 hour format : " + now);
            SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa");
            SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
            String time24 = outFormat.format(inFormat.parse(now));
            System.out.println("time in 24 hour format : " + time24);
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }
}