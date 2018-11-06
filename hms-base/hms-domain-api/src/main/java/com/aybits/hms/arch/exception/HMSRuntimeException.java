package com.aybits.hms.arch.exception;

public class HMSRuntimeException extends RuntimeException {

    private HMSErrorInfo hmsErrorInfo;

    public HMSRuntimeException(HMSErrorInfo hmsErrorInfo) {
        this.hmsErrorInfo = hmsErrorInfo;
    }

    public HMSRuntimeException(String message, HMSErrorInfo hmsErrorInfo) {
        super(getFormattedMessage(hmsErrorInfo));
        this.hmsErrorInfo = hmsErrorInfo;
    }

    public HMSRuntimeException(String message, Throwable cause, HMSErrorInfo hmsErrorInfo) {
        super(getFormattedMessage(hmsErrorInfo), cause);
        this.hmsErrorInfo = hmsErrorInfo;
    }

    public HMSRuntimeException(Throwable cause, HMSErrorInfo hmsErrorInfo) {
        super(cause);
        this.hmsErrorInfo = hmsErrorInfo;
    }

    public HMSRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HMSErrorInfo hmsErrorInfo) {
        super(getFormattedMessage(hmsErrorInfo), cause, enableSuppression, writableStackTrace);
        this.hmsErrorInfo = hmsErrorInfo;
    }

    public HMSErrorInfo getHmsErrorInfo(){
        return hmsErrorInfo;
    }

    private static String getFormattedMessage(HMSErrorInfo hmsErrorInfo){
        return hmsErrorInfo.getErrorMessage();
    }
}
