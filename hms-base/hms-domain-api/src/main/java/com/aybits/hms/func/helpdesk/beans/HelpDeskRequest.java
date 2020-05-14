package com.aybits.hms.func.helpdesk.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

@JsonRootName("help_desk_request")
public class HelpDeskRequest {

    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("service_id")
    private String serviceId;
    @JsonProperty("request_admin")
    private String requestAdminId;
    @JsonProperty("request_description")
    private String requestDescription;
    @JsonProperty("request_creation_date")
    private Date requestCreationDate;
    @JsonProperty("request_status")
    private HelpDeskRequestStatus requestStatus;
    @JsonProperty("request_expiry_date")
    private Date requestExpiryDate;
    @JsonProperty("allocated_completion_time")
    private Long allocatedTime;
    @JsonProperty("is_valid_request")
    private Boolean isValidRequest;


    public HelpDeskRequest(String requestId, String serviceId, String requestAdminId, String requestDescription,
                           Date requestCreationDate, HelpDeskRequestStatus requestStatus, Date requestExpiryDate,
                           Long allocatedTime, Boolean isValidRequest) {
        this.requestId = requestId;
        this.serviceId = serviceId;
        this.requestAdminId = requestAdminId;
        this.requestDescription = requestDescription;
        this.requestCreationDate = requestCreationDate;
        this.requestStatus = requestStatus;
        this.requestExpiryDate = requestExpiryDate;
        this.allocatedTime = allocatedTime;
        this.isValidRequest = isValidRequest;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRequestAdminId() {
        return requestAdminId;
    }

    public void setRequestAdminId(String requestAdminId) {
        this.requestAdminId = requestAdminId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public Date getRequestCreationDate() {
        return requestCreationDate;
    }

    public void setRequestCreationDate(Date requestCreationDate) {
        this.requestCreationDate = requestCreationDate;
    }

    public HelpDeskRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(HelpDeskRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getRequestExpiryDate() {
        return requestExpiryDate;
    }

    public void setRequestExpiryDate(Date requestExpiryDate) {
        this.requestExpiryDate = requestExpiryDate;
    }

    public Long getAllocatedTime() {
        return allocatedTime;
    }

    public void setAllocatedTime(Long allocatedTime) {
        this.allocatedTime = allocatedTime;
    }

    public Boolean getValidRequest() {
        return isValidRequest;
    }

    public void setValidRequest(Boolean validRequest) {
        isValidRequest = validRequest;
    }

    public HelpDeskRequest() {
    }



    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
