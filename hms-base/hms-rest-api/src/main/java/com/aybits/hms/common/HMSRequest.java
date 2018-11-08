package com.aybits.hms.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HMSRequest {

    @JsonProperty("token_id")
    String tokenId;

    @JsonProperty("entity")
    String entity;

    @JsonProperty("operation")
    String operation;

    @JsonProperty("data")
    String data;

    public HMSRequest(){}

    public HMSRequest(String tokenId, String entity, String operation, String data) {
        this.tokenId = tokenId;
        this.entity = entity;
        this.operation = operation;
        this.data = data;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
