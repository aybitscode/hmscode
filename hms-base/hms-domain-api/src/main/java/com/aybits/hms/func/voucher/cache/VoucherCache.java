package com.aybits.hms.func.voucher.cache;

import com.aybits.hms.func.voucher.beans.Voucher;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.List;

public class VoucherCache {


    private static ConcurrentHashMap<String, Voucher> voucherCache = new ConcurrentHashMap<>();
    private static HashSet<String> voucherIds = new HashSet<>();

    public Boolean initCache(){
        return false;
    }

    public void addVoucher(Voucher voucher) {
        if (voucherCache.get(String.valueOf(voucher.getVoucherId())) == null) {
            voucherIds.add(voucher.getVoucherId());
            voucherCache.put(voucher.getVoucherId(), voucher);
        }
    }

    public void updateVoucher(Voucher voucher) {
        String voucherId = voucher.getVoucherId();
        voucherCache.remove(voucherId);
        voucherCache.put(voucherId, voucher);
    }


    public Voucher getVoucherById(String voucherId) {
        Voucher voucher = voucherCache.get(voucherId);
        if (voucher != null)
            return voucher;
        else
            return null;
    }


    public List<Voucher> getAllVouchers() {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        vouchers.addAll(voucherCache.values());
        return vouchers;
    }

    public List<String> getAllVoucherIds() {
        ArrayList<String> voucherIds = new ArrayList<>();
        voucherIds.addAll(voucherCache.keySet());
        return voucherIds;
    }

    public static ConcurrentHashMap<String,Voucher> getVoucherCache(){
        return voucherCache;
    }

}
