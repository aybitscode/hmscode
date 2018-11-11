package com.aybits.hms.func.taxrules.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.aybits.hms.func.taxrules.beans.TaxRule;
import com.aybits.hms.func.taxrules.dao.TaxRuleCache;
import com.aybits.hms.func.taxrules.dao.TaxRuleDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class TaxApiHelper {


    TaxRuleCache taxCache = new TaxRuleCache();
    TaxRuleDAO taxRuleSelectDAO = new TaxRuleDAO();


    static Logger Log = Logger.getLogger(TaxApiHelper.class);



    protected String addTaxRule(TaxRule taxRule) throws HMSRuntimeException {
        String taxRuleId = null;

        if(!isHotelAlreadyPresent(taxRule)){
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

    public Boolean addHotelRegistrationData(HotelRegistrationData hotelRegistrationData) throws HMSRuntimeException{

        Boolean isAdditionSuccessful = false;
        try {
            String hotelRegistrationId = taxCache.addHotelRegistrationData(hotelRegistrationData);
            if(hotelRegistrationId == null){
                throw new NullPointerException();
            }else{
                isAdditionSuccessful = true;
            }
        }
        catch (NullPointerException npe) {
            Log.info("Exception occurred while adding registration data for hotel::"+hotelRegistrationData.getHotelId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_SETUP_FAILED, "Adding Hotel details failed"));
        }catch(HMSRuntimeException he){
            Log.info("Exception occurred while adding registration data for hotel::"+hotelRegistrationData.getHotelId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_SETUP_FAILED, "Adding Hotel Registration Details failed"));
        }

        return isAdditionSuccessful;
    }


    public List<TaxRule> fetchAllTaxRules() throws HMSRuntimeException{

        List<TaxRule> taxRules = null;
        try {
            taxRules = taxCache.fetchAllTaxRules();

        }catch(HMSRuntimeException he){

        }finally{
            return taxRules;
        }

    }

    public Hotel fetchTaxByHotellId(String hotelId) throws HMSRuntimeException{
        Hotel hotel = null;
        try{
            hotel = taxCache.fetchTaxRuleByHotellId();

        }catch(HMSRuntimeException he){

        }finally {
            return hotel;
        }
    }




    public Boolean isHotelAlreadyPresent(Hotel hotelFromUI){

        Hotel hotelFromDB = null;

        Boolean isHotelAlreadyPresent = false;
        String primaryEmail = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryEmail();
        String primaryPhone = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryPhone();
        String primaryMobileNumber = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryMobileNumber();

        try{
            hotelFromDB = hotelSelectDAO.fetchHotelByContactDetails(primaryEmail,primaryPhone,primaryMobileNumber);
            if(null != hotelFromDB){
                isHotelAlreadyPresent = true;
            }
        }catch(HMSRuntimeException he){

        }finally{
            return isHotelAlreadyPresent;
        }
    }



}
