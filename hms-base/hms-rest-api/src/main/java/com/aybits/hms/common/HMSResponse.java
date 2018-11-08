package com.aybits.hms.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HMSResponse {

    @JsonProperty("token_id")
    String tokenId;

    @JsonProperty("status")
    String status;

    @JsonProperty("message")
    String message;

    @JsonProperty("data")
    Object responseData;

    @JsonProperty("error_message")
    String errorMessage;

    @JsonProperty("error_code")
    String errorCode;

    public HMSResponse() {

    }

    public HMSResponse(String tokenId, String status, String message, Object responseData) {
        this.tokenId = tokenId;
        this.responseData = responseData;
        this.status = status;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
