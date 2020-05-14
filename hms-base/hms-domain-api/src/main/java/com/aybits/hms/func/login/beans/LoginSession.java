package com.aybits.hms.func.login.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("login_session")
public class LoginSession{

        @JsonProperty("session_id")
        private String sessionId;
        @JsonProperty("expiry_time_in_millis")
        private Long expiryTimeStamp;
        @JsonProperty("current_time_in_millis")
        private Long currentTimeStamp;
        @JsonProperty("hotel")
        private Hotel hotel;

        public Long getCurrentTimeStamp() {
                return currentTimeStamp;
        }

        public void setCurrentTimeStamp(Long currentTimeStamp) {
                this.currentTimeStamp = currentTimeStamp;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        public String getSessionId() {
                return sessionId;
        }

        public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
        }

        public Long getExpiryTimeStamp() {
                return expiryTimeStamp;
        }

        public void setExpiryTimeStamp(Long expiryTimeInMillis) {
                this.expiryTimeStamp = expiryTimeInMillis;
        }




        @Override
        public String toString(){
                return HMSJSONParser.convertObjectToJSON(this);
        }
}
