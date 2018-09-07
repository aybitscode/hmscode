package com.aybits.hms.func.common.api;

public class HMSAPIValidator {

    public static Boolean isEmptyOrNullString(String str){
        Boolean isEmpty = false;
        if(str == null || str.isEmpty()){
            isEmpty = true;
        }
        return isEmpty;
    }
}
