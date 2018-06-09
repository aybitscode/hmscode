package com.aybits.hms.func.common.beans;

import com.aybits.hms.api.func.common.util.HMSAPIServiceConstants;

public enum HMSServiceModule {

    LOGIN(HMSAPIServiceConstants.LOGIN),
    LOGOUT(HMSAPIServiceConstants.LOGOUT),
    BOOKING(HMSAPIServiceConstants.BOOKING),
    CHECKIN(HMSAPIServiceConstants.CHECKIN),
    CHECKOUT(HMSAPIServiceConstants.CHECKOUT),
    GENERATE_INVOICE(HMSAPIServiceConstants.GENERATE_INVOICE),
    PRINT_INVOICE(HMSAPIServiceConstants.PRINT_INVOICE);

    private String type;

    HMSServiceModule(String type){
        this.type=type;
    }


}
