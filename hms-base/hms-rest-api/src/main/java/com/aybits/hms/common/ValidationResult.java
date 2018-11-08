package com.aybits.hms.common;

public class ValidationResult {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private int code;
    private String message;
    private HMSRequest hmsRequest;

    public HMSRequest getHmsRequest() {
        return hmsRequest;
    }

    public void setHmsRequest(HMSRequest hmsRequest) {
        this.hmsRequest = hmsRequest;
    }


}
