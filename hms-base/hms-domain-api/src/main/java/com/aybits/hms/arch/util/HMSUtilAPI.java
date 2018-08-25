package com.aybits.hms.arch.util;

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


}
