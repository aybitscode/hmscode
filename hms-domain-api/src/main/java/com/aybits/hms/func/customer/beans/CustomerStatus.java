package com.aybits.hms.func.customer.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CustomerStatus {

    ACTIVE(1),
    DISABLED(-1),
    BLOCKED(-2),
    DELETED(-3);

    @JsonProperty("customer_status")
    private final int customerStatus;

    CustomerStatus(int customerStatus){
        this.customerStatus=customerStatus;
    }

    public int getCustomerStatusAsInt() {
        return customerStatus;
    }

    public String getCustomerStatusAsString() {
        return String.valueOf(customerStatus);
    }

    public static CustomerStatus convertIntToCustomerStatus(int iCustomerStatus) {
        for (CustomerStatus customerStatus : CustomerStatus.values()) {
            if (customerStatus.getCustomerStatusAsInt() == iCustomerStatus) {
                return customerStatus;
            }
        }
        return null;
    }

    public static CustomerStatus convertStringToCustomerStatus(String inputCustomerStatus) {
        for (CustomerStatus CustomerStatus : CustomerStatus.values()) {
            if (CustomerStatus.getCustomerStatusAsString().equals(inputCustomerStatus)) {
                return CustomerStatus;
            }
        }
        return null;
    }

    public static int convertCustomerStatusToInt(CustomerStatus inputCustomerStatus) {
        for (CustomerStatus CustomerStatus : CustomerStatus.values()) {
            if (CustomerStatus.getCustomerStatusAsInt() == inputCustomerStatus.getCustomerStatusAsInt()) {
                return CustomerStatus.getCustomerStatusAsInt();
            }
        }
        return -1;
    }

    public static String convertCustomerStatusToString(CustomerStatus inputCustomerStatus) {
        for (CustomerStatus CustomerStatus : CustomerStatus.values()) {
            if (CustomerStatus.getCustomerStatusAsInt() == inputCustomerStatus.getCustomerStatusAsInt()) {
                return CustomerStatus.getCustomerStatusAsString();
            }
        }
        return null;
    }
}
