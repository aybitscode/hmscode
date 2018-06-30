package com.aybits.hms.func.helpdesk.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestStatus {
    INVALID(-1),
    CREATED(1),
    ASSIGNED(2),
    QUEUED(3),
    COMPLETED(4);

    @JsonProperty("request_status")
    private final int requestStatus;

    RequestStatus(int requestStatus){
        this.requestStatus=requestStatus;
    }

    public int getRequestStatusAsInt() {
        return requestStatus;
    }

    public String getRequestStatusAsString() {
        return String.valueOf(requestStatus);
    }

    public static RequestStatus convertIntToRequestStatus(int iRequestStatus) {
        for (RequestStatus requestStatus : RequestStatus.values()) {
            if (requestStatus.getRequestStatusAsInt() == iRequestStatus) {
                return requestStatus;
            }
        }
        return null;
    }

    public static RequestStatus convertStringToRequestStatus(String inputRequestStatus) {
        for (RequestStatus requestStatus : RequestStatus.values()) {
            if (requestStatus.getRequestStatusAsString().equals(inputRequestStatus)) {
                return requestStatus;
            }
        }
        return null;
    }

    public static int convertRequestStatusToInt(RequestStatus inputRequestStatus) {
        for (RequestStatus requestStatus : RequestStatus.values()) {
            if (requestStatus.getRequestStatusAsInt() == inputRequestStatus.getRequestStatusAsInt()) {
                return requestStatus.getRequestStatusAsInt();
            }
        }
        return -1;
    }

    public static String convertRequestStatusToString(RequestStatus inputRequestStatus) {
        for (RequestStatus requestStatus : RequestStatus.values()) {
            if (requestStatus.getRequestStatusAsInt() == inputRequestStatus.getRequestStatusAsInt()) {
                return requestStatus.getRequestStatusAsString();
            }
        }
        return null;
    }
}
