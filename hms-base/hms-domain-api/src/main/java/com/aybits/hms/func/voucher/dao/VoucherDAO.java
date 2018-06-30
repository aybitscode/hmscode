package com.aybits.hms.func.voucher.dao;

import com.aybits.hms.func.voucher.beans.Voucher;
import com.aybits.hms.func.voucher.beans.VoucherStatus;

import java.util.List;

public class VoucherDAO {

    public Boolean addVoucher(Voucher voucher){
        return true;
    }

    public Boolean updateVoucher(Voucher voucher){
        return true;
    }

    public Voucher fetchVoucher(Integer voucherId){
        return null;
    }

    public List<Voucher> fetchAllVouchers(){
        return null;
    }

    public Boolean updateVoucherPrice(Integer voucherId,Double newVoucherprice){
        return true;
    }

    public Boolean updateVoucherStatus(Integer voucherId,VoucherStatus voucherStatus){
        return true;
    }


}
