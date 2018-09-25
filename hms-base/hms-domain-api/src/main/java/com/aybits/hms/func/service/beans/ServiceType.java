package com.aybits.hms.func.service.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ServiceType {


    HOTEL_SERVICE(1),
    ROOM_SERVICE(2);

    @JsonProperty("service_type")
    private final int serviceType;

    ServiceType(int serviceType){
        this.serviceType=serviceType;
    }

    public int getServiceTypeAsInt() {
        return serviceType;
    }

    public String getServiceTypeAsString() {
        return String.valueOf(serviceType);
    }

    public static ServiceType convertIntToServiceType(int iServiceType) {
        for (ServiceType serviceType : ServiceType.values()) {
            if (serviceType.getServiceTypeAsInt() == iServiceType) {
                return serviceType;
            }
        }
        return null;
    }

    public static ServiceType convertStringToServiceType(String inputServiceType) {
        for (ServiceType serviceType : ServiceType.values()) {
            if (serviceType.getServiceTypeAsString().equals(inputServiceType)) {
                return serviceType;
            }
        }
        return null;
    }

    public static int convertServiceTypeToInt(ServiceType inputServiceType) {
        for (ServiceType serviceType : ServiceType.values()) {
            if (serviceType.getServiceTypeAsInt() == inputServiceType.getServiceTypeAsInt()) {
                return serviceType.getServiceTypeAsInt();
            }
        }
        return -1;
    }

    public static String convertServiceTypeToString(ServiceType inputServiceType) {
        for (ServiceType serviceType : ServiceType.values()) {
            if (serviceType.getServiceTypeAsInt() == inputServiceType.getServiceTypeAsInt()) {
                return serviceType.getServiceTypeAsString();
            }
        }
        return null;
    }


}