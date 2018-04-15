package com.aybits.hms.api.func.common.beans;

import com.aybits.hms.api.func.common.util.HMSServiceConstants;

public enum HMSServiceModule {

    LOGIN(HMSServiceConstants.LOGIN),
    LOGOUT(HMSServiceConstants.LOGOUT),
    BOOKING(HMSServiceConstants.BOOKING),
    CHECKIN(HMSServiceConstants.CHECKIN),
    CHECKOUT(HMSServiceConstants.CHECKOUT),
    GENERATE_INVOICE(HMSServiceConstants.GENERATE_INVOICE),
    PRINT_INVOICE(HMSServiceConstants.PRINT_INVOICE);

    private String type;

    HMSServiceModule(String type){
        this.type=type;
    }


}
