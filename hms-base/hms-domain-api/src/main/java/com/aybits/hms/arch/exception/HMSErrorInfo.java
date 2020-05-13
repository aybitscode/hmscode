package com.aybits.hms.arch.exception;

public class HMSErrorInfo {

    private HMSError hmsError;
    private Object[] values;

    protected HMSErrorInfo(HMSError error, Object... values){
        this.hmsError = error;
        this.values = values;
    }

    public String getErrorCode(){
        return this.getErrorCode();
    }


    public String getErrorMessage(){
        if(values == null)
            return hmsError.getErrorMessage();
        else{
            return String.format(hmsError.getErrorMessage(),values);
        }
    }

    public static HMSErrorInfo getNewErrorInfo(HMSError hmsError,Object... values){
        return new HMSErrorInfo(hmsError,values);
    }

}
