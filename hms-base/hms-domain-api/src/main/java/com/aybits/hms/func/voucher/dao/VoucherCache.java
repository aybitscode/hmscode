package com.aybits.hms.func.voucher.dao;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.voucher.beans.Voucher;

public class VoucherCache {




    static Logger log = Logger.getLogger(VoucherCache.class);
    private static final ConcurrentHashMap voucherConcurrentHashMap = new ConcurrentHashMap();
    private static final HashSet<String> voucherIds = new HashSet<>();
    private VoucherDAO voucherDAO = new VoucherDAO();
    private VoucherSelectDAO voucherSelectDAO = new VoucherSelectDAO();

    public Boolean initCache(String hotelId){

        Boolean isVoucherCacheInitialized = false;
        if(voucherConcurrentHashMap.isEmpty()){
            List<Voucher> vouchers = null;
            try {
                vouchers = voucherSelectDAO.fetchAllVouchers(hotelId);
                if(!vouchers.isEmpty()) {
                    for (Voucher voucher : vouchers) {
                        voucherIds.add(voucher.getVoucherId());
                        voucherConcurrentHashMap.put(voucher.getVoucherId(), voucher);
                    }
                }

            }catch(HMSException e){
                //LOG Cache Initialization failed
                //  throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all voucher details failed");
            }finally{
                if(!voucherConcurrentHashMap.keySet().isEmpty()){
                    isVoucherCacheInitialized = true;
                }
            }
        }


        return isVoucherCacheInitialized;
    }

    public Boolean addVoucher(Voucher voucher) throws HMSException {

        Boolean isVoucherAdditionSuccessful = voucherDAO.addVoucher(voucher);
        if(isVoucherAdditionSuccessful){
            if (voucherConcurrentHashMap.get(voucher.getVoucherId()) == null) {
                voucherIds.add(voucher.getVoucherId());
                voucherConcurrentHashMap.put(voucher.getVoucherId(), voucher);
            }
        }
        return isVoucherAdditionSuccessful;

    }

    public Boolean updateVoucher(Voucher voucher) throws HMSException {
        Boolean isVoucherUpdateSuccessful = voucherDAO.updateVoucher(voucher);
        if(isVoucherUpdateSuccessful) {
            String voucherId = voucher.getVoucherId();
            if (voucherIds.contains(voucher.getVoucherId())) {
                voucherConcurrentHashMap.remove(voucherId);
            }
            voucherConcurrentHashMap.put(voucherId, voucher);
        }
        return isVoucherUpdateSuccessful;
    }


    public Voucher fetchVoucherById(String hotelId,String voucherId) throws HMSException {
        Voucher voucher = null;
        try{
            voucher = (Voucher)voucherConcurrentHashMap.get(voucherId);
            if (voucher == null){
                voucher = voucherSelectDAO.fetchVoucher(hotelId,voucherId);
                voucher = requireNonNull(voucher);
                voucherConcurrentHashMap.put(voucherId,voucher);
            }
        }catch(NullPointerException npe){
            log.info("No Voucher Present for the given voucherId["+voucherId+"]");
        }finally{
            return voucher;
        }
    }


    public  List<Voucher> fetchAllVouchers(String hotelId) throws HMSException {

        ArrayList<Object> voucherObjs = new ArrayList<>();
        ArrayList<Voucher> vouchers = new ArrayList<>();

        voucherObjs.addAll(voucherConcurrentHashMap.values());
        if(voucherObjs.isEmpty()){
            initCache(hotelId);
            voucherObjs.addAll(voucherConcurrentHashMap.values());
        }
        for(Object obj:voucherObjs){
            Voucher voucher = (Voucher)obj;
            vouchers.add(voucher);
        }
        return vouchers;
    }

    public List<String> fetchAllVoucherIds() {
        ArrayList<String> voucherIds = new ArrayList<>();
        voucherIds.addAll(voucherConcurrentHashMap.keySet());
        return voucherIds;
    }


    public Boolean isVoucherPresent(String hotelId,String voucherId){
        Boolean isVoucherPresent = false;
        Voucher voucher = null;
        try {
            voucher = fetchVoucherById(hotelId,voucherId);
            if(voucher != null && voucher.getVoucherId() != null){
                isVoucherPresent = true;
            }
        } catch (HMSException e) {
            log.error("Exception occured while checking if Voucher["+voucher.getHotelId()+":"+voucher.getVoucherId()+"] is present in the system");
        }
        return isVoucherPresent;

    }

    public ConcurrentHashMap<String,Voucher> getVoucherCache(){
        return voucherConcurrentHashMap;
    }


}
