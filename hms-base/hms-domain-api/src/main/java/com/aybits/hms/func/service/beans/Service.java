package com.aybits.hms.func.service.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("service")
public class Service {

    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("service_id")
    private String serviceId;
    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("service_description")
    private String serviceDescription;
    @JsonProperty("is_available")
    private Boolean isAvailable;
    @JsonProperty("is_chargeable")
    private Boolean isChargeable;
    @JsonProperty("service_type")
    private ServiceType serviceType;
    @JsonProperty("service_charge")
    private Double serviceCharges;


    public Service() {
    }

    public Service(String hotelId,String serviceId, String serviceName, String serviceDescription,
                   Boolean isAvailable, Boolean isChargeable, ServiceType serviceType,
                   Double serviceCharges) {
        this.hotelId = hotelId;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.isAvailable = isAvailable;
        this.isChargeable = isChargeable;
        this.serviceType = serviceType;
        this.serviceCharges = serviceCharges;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }


    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean serviceAvailable) {
        isAvailable = serviceAvailable;
    }

    public Boolean isChargeable() {
        return isChargeable;
    }

    public void setIsChargeable(Boolean serviceChargeable) {
        isChargeable = serviceChargeable;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Double getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Double serviceCharges) {
        this.serviceCharges = serviceCharges;
    }




    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
