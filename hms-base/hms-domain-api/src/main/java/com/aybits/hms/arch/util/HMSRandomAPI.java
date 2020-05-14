package com.aybits.hms.arch.util;

import java.util.UUID;

public class HMSRandomAPI {

    private static String generateRandomString(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

    public static String generatePrimaryKeyForDB(){

        String randomUUIDString = generateRandomString();
        String[] strs = randomUUIDString.split("-");
        return strs[1] + strs[2];


    }

    public static String generateJsonWebToken(){
        String randomUUIDString = generateRandomString();
        return randomUUIDString.split("-")[4];
    }

}
