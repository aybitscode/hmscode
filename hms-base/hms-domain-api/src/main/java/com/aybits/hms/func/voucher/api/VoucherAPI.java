package com.aybits.hms.func.voucher.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.voucher.beans.Voucher;
import com.aybits.hms.func.voucher.dao.VoucherDAO;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

public class VoucherAPI implements HmsAPI {


    static Logger log = Logger.getLogger(VoucherAPI.class);
    VoucherDAO.VoucherCache voucherCache = new VoucherDAO.VoucherCache();
    VoucherDAO voucherDAO = new VoucherDAO();



    private Boolean addVoucher(Voucher voucher) throws HMSRuntimeException {
        Boolean isVoucherAdditionSuccessful = false;
        if (voucher.getVoucherId() != null && voucher.getVoucherId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
            try {
                isVoucherAdditionSuccessful = voucherCache.addVoucher(voucher);
            } catch (HMSRuntimeException e) {
                log.info("Exception occured while adding voucher::"+voucher.getVoucherId());
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.VOUCHER_ADDITION_FAILED, "Adding voucher details failed"));
            }
        }
        return isVoucherAdditionSuccessful;
    }
    public List<Voucher> fetchAllVouchers(String hotelId)  {

        List<Voucher> vouchers = null;
        try {
            vouchers = voucherCache.fetchAllVouchers(hotelId);
        }catch(HMSRuntimeException he){

        }finally{
            return vouchers;
        }

    }

    public Voucher fetchVoucherByVoucherId(String hotelId,String voucherId){
        Voucher voucher = null;
        try{
            voucher = voucherCache.fetchVoucherById(hotelId,voucherId);
        }catch(HMSRuntimeException he){

        }finally {
            return voucher;
        }
    }


    public Voucher fetchVoucherById(String hotelId,String voucherId){
        Voucher voucher = null;
        try{
            voucher = voucherCache.fetchVoucherById(hotelId,voucherId);
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Voucher Details not available for given emploeeId"));
        }finally{
            return voucher;
        }

    }

    public Boolean upsertVoucher(Voucher voucher) throws HMSRuntimeException{

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

    private Boolean updateVoucher(Voucher voucher) throws HMSRuntimeException {

        Boolean isVoucherUpdateSuccessful = false;
        if (voucher.getVoucherId() == HMSAPIConstants.TO_BE_GENERATED) try {
            isVoucherUpdateSuccessful = voucherCache.updateVoucher(voucher);
        } catch (HMSRuntimeException e) {
            log.info("Exception occured while adding voucher::" + voucher.getVoucherId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_UPDATE_FAILED, "Adding Voucher details failed"));
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


    @Override
    public Object init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {
    }

    @Override
    public String fetch(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSRuntimeException {
        return null;
    }
}
