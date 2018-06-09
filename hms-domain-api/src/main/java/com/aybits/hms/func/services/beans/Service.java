package com.aybits.hms.func.services.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Service {

    @JsonProperty("service_id")
    private Integer serviceId;
    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("service_type")
    private ServiceType serviceType;
    @JsonProperty("service_description")
    private String serviceDescription;
    @JsonProperty("service_availability")
    private Boolean isServiceAvailable;

    public Service() {
    }


    public Service(Integer serviceId,
                   String serviceName,
                   ServiceType serviceType,
                   String serviceDescription,
                   Boolean isServiceAvailable) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.serviceDescription = serviceDescription;
        this.isServiceAvailable = isServiceAvailable;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Boolean getServiceAvailable() {
        return isServiceAvailable;
    }

    public void setServiceAvailable(Boolean serviceAvailable) {
        isServiceAvailable = serviceAvailable;
    }
}
