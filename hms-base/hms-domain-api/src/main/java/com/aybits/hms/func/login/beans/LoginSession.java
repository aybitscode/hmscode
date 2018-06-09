package com.aybits.hms.func.login.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("login_session")
public class LoginSession{

        @JsonProperty("session_id")
        private String sessionId;
        @JsonProperty("expiry_time_in_millis")
        private Long expiryTimeInMillis;
        @JsonProperty("current_time_in_millis")
        private Long currentTimeStamp;

        public String getSessionId() {
                return sessionId;
        }

        public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
        }

        public Long getExpiryTimeInMillis() {
                return expiryTimeInMillis;
        }

        public void setExpiryTimeInMillis(Long expiryTimeInMillis) {
                this.expiryTimeInMillis = expiryTimeInMillis;
        }

        public Long getCurrentTimeInMillis() {
                return currentTimeStamp;
        }

        public void setCurrentTimeInMillis(Long currentTimeStamp) {
                this.currentTimeStamp = currentTimeStamp;
        }
}
