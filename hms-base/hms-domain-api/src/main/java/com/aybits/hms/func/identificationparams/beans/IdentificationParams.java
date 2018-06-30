package com.aybits.hms.func.identificationparams.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IdentificationParams {

    @JsonProperty("identifier_binary_data")
    private Byte[] identiferBinaryData;

    @JsonProperty("identifier_value")
    private Integer identifierValue;

    @JsonProperty("identifier_type")
    private IdentifierType identifierType;

    public Byte[] getIdentiferBinaryData() {
        return identiferBinaryData;
    }

    public void setIdentiferBinaryData(Byte[] identiferBinaryData) {
        this.identiferBinaryData = identiferBinaryData;
    }

    public IdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(IdentifierType identifierType) {
        this.identifierType = identifierType;
    }

    public Integer getIdentifierValue() {
        return identifierValue;
    }

    public void setIdentifierValue(Integer identifierValue) {
        this.identifierValue = identifierValue;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
