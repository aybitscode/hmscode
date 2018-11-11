package com.aybits.hms.func.taxrules.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.aybits.hms.func.taxrules.beans.TaxRule;
import com.aybits.hms.func.taxrules.dao.TaxRuleCache;
import com.aybits.hms.func.taxrules.dao.TaxRuleSelectDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class TaxAPIHelper {


    TaxRuleCache taxCache = new TaxRuleCache();
    TaxRuleSelectDAO taxRuleSelectDAO = new TaxRuleSelectDAO();


    static Logger Log = Logger.getLogger(TaxAPIHelper.class);



    protected String addTaxRule(TaxRule taxRule) throws HMSRuntimeException {
        String taxRuleId = null;

        if(!isTaxRuleAlreadyPresent(taxRule)){
            if (taxRule.getHotelId() != null && taxRule.getTaxRuleId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
                try {
                    taxRuleId = taxCache.addTaxRule(taxRule);
                    if(taxRuleId == null){
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException npe) {
                    Log.info("Exception occurred while adding hotel::"+taxRule.getHotelId());
                    throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Null Pointer Exception:"+npe.getMessage()));
                }
            }

        }else{
            Log.info("Exception occurred while adding hotel::Hotel already exists");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_ALREADY_EXISTS, "Hotel already exists"));
        }

        return taxRuleId;
    }




    public List<TaxRule> fetchAllTaxRules(String hotelId) throws HMSRuntimeException{

        List<TaxRule> taxRules = null;
        try {
            taxRules = taxCache.fetchAllTaxRules(hotelId);

        }catch(HMSRuntimeException he){

        }finally{
            return taxRules;
        }

    }



    public Boolean isTaxRuleAlreadyPresent(TaxRule taxRuleFromUI)throws HMSRuntimeException{

        TaxRule taxRuleFromDB = null;

        Boolean isTaxRuleAlreadyPresent = false;
        String hotelId = taxRuleFromUI.getHotelId();
        String taxRuleName = taxRuleFromUI.getTaxRuleName();


        try{
            taxRuleFromDB = taxRuleSelectDAO.fetchTaxRuleByTaxRuleId(taxRuleFromUI.getHotelId(),taxRuleFromUI.getTaxRuleId());
            if(null != taxRuleFromDB){
                isTaxRuleAlreadyPresent = true;
            }
        }catch(HMSRuntimeException he){

        }finally{
            return isTaxRuleAlreadyPresent;
        }
    }



}
