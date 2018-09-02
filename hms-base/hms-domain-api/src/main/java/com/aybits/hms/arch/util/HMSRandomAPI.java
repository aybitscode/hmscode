package com.aybits.hms.arch.util;

import java.util.UUID;

public class HMSRandomAPI {

    private static String generateRandomString(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

    private String generatePrimaryKeyForDB(){

        String randomUUIDString = generateRandomString();
        String[] strs = randomUUIDString.split("-");
        return strs[1] + strs[2];


    }

    public String generatePrimaryKey(String keyPrefix,String keySuffix){

        String randomSalt = generatePrimaryKeyForDB();
        String primaryKey = keyPrefix+randomSalt+"_"+keySuffix;
        return primaryKey;
    }

    public static String generateJsonWebToken(){
        String randomUUIDString = generateRandomString();
        return randomUUIDString.split("-")[4];
    }

}
