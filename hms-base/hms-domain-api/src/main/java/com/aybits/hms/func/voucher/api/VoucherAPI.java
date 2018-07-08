package com.aybits.hms.func.voucher.api;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.voucher.beans.Voucher;

import java.util.List;

public class VoucherAPI extends HMSAPIProviderImpl {

    
    public Voucher getVoucherDetails(String voucherName){
        return null;
    }

    public List<Voucher> getAllVouchers(){
        return null;
    }

    public Boolean isValidVoucher(String voucherName){
        return null;
    }

    public Boolean isValidVoucher(String voucherId,String voucherName){
        return null;
    }

    public Voucher getVoucherDetails(String voucherId,String voucherName){
        return null;
    }




}
