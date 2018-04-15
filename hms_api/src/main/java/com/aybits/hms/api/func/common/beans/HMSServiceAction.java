package com.aybits.hms.api.func.common.beans;

import com.aybits.hms.api.func.common.util.HMSServiceConstants;

public enum HMSServiceAction {

    ADD(HMSServiceConstants.INSERT),
    UPDATE(HMSServiceConstants.UPDATE),
    DELETE(HMSServiceConstants.DELETE),
    FETCH(HMSServiceConstants.FETCH),
    FETCH_ALL(HMSServiceConstants.FETCH_ALL),
    DELETE_ALL(HMSServiceConstants.DELETE_ALL),
    UPSERT(HMSServiceConstants.UPSERT);

    private String type;

    HMSServiceAction(String type){
        this.type=type;
    }
}
