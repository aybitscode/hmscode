package com.aybits.hms.func.taxrules.dao;

import java.util.concurrent.ConcurrentHashMap;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.taxrules.beans.TaxRule;


public class TaxRuleCache {
	private static ConcurrentHashMap<String, TaxRule> hotelTaxRuleCache = new ConcurrentHashMap<>();
	
	private TaxRuleDAO taxRuleDAO = new TaxRuleDAO();
	private TaxRule taxRule = new TaxRule();
	
    public Boolean initCache(String hotelId) throws HMSRuntimeException {

        Boolean isCacheInitialized = initializeHotelTaxRuleCache(hotelId, false);

        return isCacheInitialized;
    }
    
    private Boolean initializeHotelTaxRuleCache(String hotelId, Boolean reload) throws HMSRuntimeException {

        Boolean isCacheInitialized = false;
        if (reload) {
            hotelTaxRuleCache = new ConcurrentHashMap<>();
        }
        if (hotelTaxRuleCache.isEmpty()) {
            try {
            	taxRule = taxRuleDAO.getTaxRuleByHotelId(hotelId);
                
                if (taxRule!=null) {
                    hotelTaxRuleCache.put(hotelId, taxRule);
                }
            } catch (HMSRuntimeException e) {
                //LOG Cache Initialization failed
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE, "Loading Hotel Amenities Cache failed"));
            } finally {
                if (!hotelTaxRuleCache.keySet().isEmpty()) {
                    isCacheInitialized = true;
                }
            }
        }
        return isCacheInitialized;
    }

}
