package com.aybits.hms.func.identificationparam.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("identification_param")
public class IdentificationParam {

    @JsonProperty("identification_param_id")
    private String identificationParamId;
    @JsonProperty("identifier_binary_data")
    private Byte[] identiferBinaryData;
    @JsonProperty("identifier_value")
    private Integer identifierValue;
    @JsonProperty("identifier_type")
    private IdentifierType identifierType;


    public IdentificationParam(){

    }
    
    public IdentificationParam(String identificationParamId, Byte[] identiferBinaryData,
                               Integer identifierValue, IdentifierType identifierType) {
        this.identificationParamId = identificationParamId;
        this.identiferBinaryData = identiferBinaryData;
        this.identifierValue = identifierValue;
        this.identifierType = identifierType;
    }


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

    public String getIdentificationParamId() {
        return identificationParamId;
    }

    public void setIdentificationParamId(String identificationParamId) {
        this.identificationParamId = identificationParamId;
    }


    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
