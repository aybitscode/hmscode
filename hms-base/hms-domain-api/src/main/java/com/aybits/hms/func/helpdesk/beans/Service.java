package com.aybits.hms.func.helpdesk.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Service {

    @JsonProperty("service_id")
    private Integer serviceId;
    @JsonProperty("service_name")
    private String serviceName;
    //@JsonProperty("service_type")
   // private ServiceType serviceType;
    @JsonProperty("service_description")
    private String serviceDescription;
    @JsonProperty("service_availability")
    private Boolean isServiceAvailable;
    @JsonProperty("service_chargeable")
    private Boolean isServiceChargeable;
    @JsonProperty("service_charge")
    private Double serviceCharge;
    @JsonProperty("is_tax_applicable")
    private Boolean isTaxApplicable;
    @JsonProperty("tax_applicable")
    private Double appliedTax;


    public Service() {
    }


    public Service(Integer serviceId,
                   String serviceName,
                  // ServiceType serviceType,
                   String serviceDescription,
                   Boolean isServiceAvailable,
                   Boolean isServiceChargeable,
                   Double serviceCharge,
                   Boolean isTaxApplicable,
                   Double appliedTax
                ) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        //this.serviceType = serviceType;
        this.serviceDescription = serviceDescription;
        this.isServiceAvailable = isServiceAvailable;
        this.isServiceChargeable = isServiceChargeable;
        this.isTaxApplicable = isTaxApplicable;
        this.serviceCharge = serviceCharge;
        this.appliedTax = appliedTax;
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
    
    public Boolean getServiceChargeable() {
        return isServiceChargeable;
    }

    public void setServiceChargeable(Boolean serviceChargeable) {
        isServiceChargeable = serviceChargeable;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Boolean getTaxApplicable() {
        return isTaxApplicable;
    }

    public void setTaxApplicable(Boolean taxApplicable) {
        isTaxApplicable = taxApplicable;
    }

    public Double getAppliedTax() {
        return appliedTax;
    }

    public void setAppliedTax(Double appliedTax) {
        this.appliedTax = appliedTax;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
