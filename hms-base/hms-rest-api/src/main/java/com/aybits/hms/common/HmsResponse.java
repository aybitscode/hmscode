package com.aybits.hms.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("hms_response")
public class HmsResponse {

    @JsonProperty("response_code")
    int responseCode;

    @JsonProperty("response_message")
    String responseMessage;

    @JsonProperty("response_data")
    Object responseData;

    public HmsResponse() {

    }

    public HmsResponse(int responseCode, String responseMessage, Object responseData) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

}
