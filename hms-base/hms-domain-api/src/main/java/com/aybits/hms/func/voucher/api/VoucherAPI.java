package com.aybits.hms.func.voucher.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.voucher.beans.Voucher;
import com.aybits.hms.func.voucher.cache.VoucherCache;
import com.aybits.hms.func.voucher.dao.VoucherDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class VoucherAPI extends HMSAPIProviderImpl {


    static Logger log = Logger.getLogger(VoucherAPI.class);
    VoucherCache voucherCache = new VoucherCache();
    VoucherDAO voucherDAO = new VoucherDAO();



    private Boolean addVoucher(Voucher voucher) throws HMSException {
        Boolean isVoucherAdditionSuccessful = false;
        if (voucher.getVoucherId() != null && voucher.getVoucherId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
            try {
                isVoucherAdditionSuccessful = voucherCache.addVoucher(voucher);
            } catch (HMSException e) {
                log.info("Exception occured while adding voucher::"+voucher.getVoucherId());
                throw new HMSException(HMSErrorCodes.HOTEL_ADDITION_FAILED, "Adding voucher details failed");
            }
        }
        return isVoucherAdditionSuccessful;
    }
    public List<Voucher> fetchAllVouchers(String hotelId)  {

        List<Voucher> vouchers = null;
        try {
            vouchers = voucherCache.fetchAllVouchers(hotelId);
        }catch(HMSException he){

        }finally{
            return vouchers;
        }

    }

    public Voucher fetchVoucherByVoucherId(String hotelId,String voucherId){
        Voucher voucher = null;
        try{
            voucher = voucherCache.fetchVoucherById(hotelId,voucherId);
        }catch(HMSException he){

        }finally {
            return voucher;
        }
    }


    public Voucher fetchVoucherById(String hotelId,String voucherId){
        Voucher voucher = null;
        try{
            voucher = voucherCache.fetchVoucherById(hotelId,voucherId);
        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Voucher Details not available for given emploeeId");
        }finally{
            return voucher;
        }

    }

    public Boolean upsertVoucher(Voucher voucher) throws HMSException{

        String voucherId = voucher.getVoucherId();
        String hotelId = voucher.getHotelId();
        Boolean isVoucherPresent = voucherCache.isVoucherPresent(hotelId,voucherId);
        Boolean isOperationSuccessful = false;
        if(isVoucherPresent){
            //update Voucher Details
            isOperationSuccessful = updateVoucher(voucher);
        }else{
            isOperationSuccessful = addVoucher(voucher);
            //add voucher Details

        }
        return isOperationSuccessful;
    }

    private Boolean updateVoucher(Voucher voucher) throws HMSException {

        Boolean isVoucherUpdateSuccessful = false;
        if (voucher.getVoucherId() == HMSAPIConstants.TO_BE_GENERATED) try {
            isVoucherUpdateSuccessful = voucherCache.updateVoucher(voucher);
        } catch (HMSException e) {
            log.info("Exception occured while adding voucher::" + voucher.getVoucherId());
            throw new HMSException(HMSErrorCodes.HOTEL_UPDATE_FAILED, "Adding Voucher details failed");
        }
        return isVoucherUpdateSuccessful;

    }



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
