package com.aybits.hms.api.func.login.beans;

<<<<<<< HEAD
public class LoginSession{

        private String sessionId;
        private Long expiryTime;
        private Long currentTimeStamp;


        public Boolean isActive(){
            return true;
        }

=======

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginSession{

        @JsonProperty("session_id")
        private String sessionId;
        @JsonProperty("expiry_time")
        private Long expiryTime;
        @JsonProperty("current_timestamp")
        private Long currentTimeStamp;

        public String getSessionId() {
                return sessionId;
        }

        public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
        }

        public Long getExpiryTime() {
                return expiryTime;
        }

        public void setExpiryTime(Long expiryTime) {
                this.expiryTime = expiryTime;
        }

        public Long getCurrentTimeStamp() {
                return currentTimeStamp;
        }

        public void setCurrentTimeStamp(Long currentTimeStamp) {
                this.currentTimeStamp = currentTimeStamp;
        }
>>>>>>> adil_develop
}
