package com.aybits.hms.func.common.beans;

import com.aybits.hms.api.func.common.util.HMSAPIServiceConstants;

public enum HMSServiceAction {

    ADD(HMSAPIServiceConstants.INSERT),
    UPDATE(HMSAPIServiceConstants.UPDATE),
    DELETE(HMSAPIServiceConstants.DELETE),
    FETCH(HMSAPIServiceConstants.FETCH),
    FETCH_ALL(HMSAPIServiceConstants.FETCH_ALL),
    DELETE_ALL(HMSAPIServiceConstants.DELETE_ALL),
    UPSERT(HMSAPIServiceConstants.UPSERT);

    private String type;

    HMSServiceAction(String type){
        this.type=type;
    }
}
