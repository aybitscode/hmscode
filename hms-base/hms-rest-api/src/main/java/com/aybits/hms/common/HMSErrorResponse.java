package com.aybits.hms.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HMSErrorResponse {

    @JsonProperty("token_id")
    String tokenId;

    @JsonProperty("status")
    String status;

    @JsonProperty("error_message")
    String errorMessage;

    @JsonProperty("error_code")
    String errorCode;

    public String getTokenId() {
        return tokenId;
    }

    public HMSErrorResponse(){

    }
    public HMSErrorResponse(String tokenId, String status, String errorMessage, String errorCode) {
        this.tokenId = tokenId;
        this.status = status;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
