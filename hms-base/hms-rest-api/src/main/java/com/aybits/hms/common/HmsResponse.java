package com.aybits.hms.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("hms_response")
public class HmsResponse {

    @JsonProperty("token_id")
    String tokenId;

    @JsonProperty("status")
    String status;

    @JsonProperty("statusMessage")
    String statusMessage;

    @JsonProperty("data")
    Object responseData;

    public HmsResponse() {

    }

    public HmsResponse(String tokenId, String status, String statusMessage, Object responseData) {
        this.tokenId = tokenId;
        this.responseData = responseData;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
