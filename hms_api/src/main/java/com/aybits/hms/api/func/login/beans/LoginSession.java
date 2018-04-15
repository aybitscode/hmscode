package com.aybits.hms.api.func.login.beans;

public class LoginSession{

        private String sessionId;
        private Long expiryTime;
        private Long currentTimeStamp;


        public Boolean isActive(){
            return true;
        }

}
