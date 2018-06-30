package com.aybits.hms.func.common.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {

    ACTIVE(1),
    DISABLED(-1),
    BLOCKED(-2),
    DELETED(-3);

    @JsonProperty("status")
    private final int status;

    Status(int status){
        this.status = status;
    }

    public int getStatusAsInt() {
        return status;
    }

    public String getStatusAsString() {
        return String.valueOf(status);
    }

    public static Status convertIntToStatus(int iStatus) {
        for (Status Status : Status.values()) {
            if (Status.getStatusAsInt() == iStatus) {
                return Status;
            }
        }
        return null;
    }

    public static Status convertStringToStatus(String inputStatus) {
        for (Status Status : Status.values()) {
            if (Status.getStatusAsString().equals(inputStatus)) {
                return Status;
            }
        }
        return null;
    }

    public static int convertStatusToInt(Status inputStatus) {
        for (Status Status : Status.values()) {
            if (Status.getStatusAsInt() == inputStatus.getStatusAsInt()) {
                return Status.getStatusAsInt();
            }
        }
        return -1;
    }

    public static String convertStatusToString(Status inputStatus) {
        for (Status Status : Status.values()) {
            if (Status.getStatusAsInt() == inputStatus.getStatusAsInt()) {
                return Status.getStatusAsString();
            }
        }
        return null;
    }
}
