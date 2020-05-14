package com.aybits.hms.func.helpdesk.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum HelpDeskRequestStatus {
    INVALID(-1),
    CREATED(1),
    ASSIGNED(2),
    QUEUED(3),
    COMPLETED(4);

    @JsonProperty("request_status")
    private final int requestStatus;

    HelpDeskRequestStatus(int requestStatus){
        this.requestStatus=requestStatus;
    }

    public int getRequestStatusAsInt() {
        return requestStatus;
    }

    public String getRequestStatusAsString() {
        return String.valueOf(requestStatus);
    }

    public static HelpDeskRequestStatus convertIntToRequestStatus(int iRequestStatus) {
        for (HelpDeskRequestStatus helpDeskRequestStatus : HelpDeskRequestStatus.values()) {
            if (helpDeskRequestStatus.getRequestStatusAsInt() == iRequestStatus) {
                return helpDeskRequestStatus;
            }
        }
        return null;
    }

    public static HelpDeskRequestStatus convertStringToRequestStatus(String inputRequestStatus) {
        for (HelpDeskRequestStatus helpDeskRequestStatus : HelpDeskRequestStatus.values()) {
            if (helpDeskRequestStatus.getRequestStatusAsString().equals(inputRequestStatus)) {
                return helpDeskRequestStatus;
            }
        }
        return null;
    }

    public static int convertRequestStatusToInt(HelpDeskRequestStatus inputHelpDeskRequestStatus) {
        for (HelpDeskRequestStatus helpDeskRequestStatus : HelpDeskRequestStatus.values()) {
            if (helpDeskRequestStatus.getRequestStatusAsInt() == inputHelpDeskRequestStatus.getRequestStatusAsInt()) {
                return helpDeskRequestStatus.getRequestStatusAsInt();
            }
        }
        return -1;
    }

    public static String convertRequestStatusToString(HelpDeskRequestStatus inputHelpDeskRequestStatus) {
        for (HelpDeskRequestStatus helpDeskRequestStatus : HelpDeskRequestStatus.values()) {
            if (helpDeskRequestStatus.getRequestStatusAsInt() == inputHelpDeskRequestStatus.getRequestStatusAsInt()) {
                return helpDeskRequestStatus.getRequestStatusAsString();
            }
        }
        return null;
    }
}
