package com.aybits.hms.arch.util;

public class HMSJsonRequestComponents {
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getEntity() {
        return entity;
    }
    public void setEntity(String entity) {
        this.entity = entity;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getTokenId() {
        return tokenId;
    }
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
    private String data;
    private String entity;
    private String operation;
    private String tokenId;
}
