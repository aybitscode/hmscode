package com.aybits.hms.func.login.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginSession{

        @JsonProperty("session_id")
        private String sessionId;
        @JsonProperty("expiry_time")
        private Long expiryTime;
        @JsonProperty("current_time_in_millis")
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

        public Long getCurrentTimeInMillis() {
                return currentTimeStamp;
        }

        public void setCurrentTimeInMillis(Long currentTimeStamp) {
                this.currentTimeStamp = currentTimeStamp;
        }
}