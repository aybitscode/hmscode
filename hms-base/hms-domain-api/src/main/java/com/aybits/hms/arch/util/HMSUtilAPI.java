package com.aybits.hms.arch.util;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIValidator;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;

public class HMSUtilAPI {


    public static Logger Log = Logger.getLogger(HMSCommonDAO.class);

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


    public static Timestamp getCurrentTimestamp() throws HMSException {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
    }

    public static Boolean getBooleanValueFromString(String strValue){
        Boolean booleanValue = false;
        if(HMSAPIValidator.isBlankString(strValue)){
            return booleanValue;
        }

        if(!strValue.equals("0")){
            booleanValue = true;
        }

        return booleanValue;
    }

    public static Integer getIntegerValueFromBoolean(Boolean boolValue){

        Integer intValue = 0;
        if(boolValue){
            intValue = 1;
        }
        return intValue;
    }

    public static String getKey(String str1,String str2){
        return str1+"~"+str2;
    }

    public static String[] getKeyElements(String key){
        String [] elements = null;
        elements = key.split("~");
        return elements;
    }

}
