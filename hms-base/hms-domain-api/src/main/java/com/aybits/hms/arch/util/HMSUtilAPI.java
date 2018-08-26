package com.aybits.hms.arch.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.util.Date;

public class HMSUtilAPI {


    public static Timestamp convertDateToTimestamp(Date date){

        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    public static Date convertTimestampToDate(Timestamp ts){
        Date date = new Date(ts.getTime());
        return date;
    }

    public static String generateSHA256Hash(String clearText){
        String sha256hex = DigestUtils.sha256Hex(clearText);
        return sha256hex;
    }


}
