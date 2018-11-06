package com.aybits.hms.func.common.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HMSAPIResponse {

    @JsonProperty("status")
    String status;

    @JsonProperty("message")
    String message;

    @JsonProperty("data")
    String responseData;

    public HMSAPIResponse() {

    }

    public HMSAPIResponse(String status, String message, String responseData) {
        this.responseData = responseData;
        this.status = status;
        this.message = message;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
