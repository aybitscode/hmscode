package com.aybits.hms.arch.util;

import java.util.UUID;

public class HMSRandomAPI {

    private static String generateRandomString(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

    private String generateRandomSalt(){

        String randomUUIDString = generateRandomString();
        String[] strs = randomUUIDString.split("-");
        return strs[1] + strs[2];


    }

    public String generatePrimaryKey(String keyPrefix,String keySuffix,Boolean isRandomSaltRequired){

        String randomSalt = null;
        String primaryKey = null;
        if(isRandomSaltRequired){
            randomSalt = generateRandomSalt();
            primaryKey = keyPrefix+randomSalt;
        }else{
            Integer intKeySuffix = 6857093+Integer.parseInt(keySuffix);
            primaryKey = keyPrefix+intKeySuffix.toString();
        }
        return primaryKey;
    }



    public String generateJsonWebToken(){
        String randomUUIDString = generateRandomString();
        return randomUUIDString.split("-")[4];
    }

}
