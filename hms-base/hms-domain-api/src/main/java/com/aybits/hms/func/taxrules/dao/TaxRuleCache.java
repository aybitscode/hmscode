package com.aybits.hms.func.taxrules.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.taxrules.beans.TaxRule;


public class TaxRuleCache {
    private static ConcurrentHashMap<String, TaxRule> hotelTaxRuleCache = new ConcurrentHashMap<>();
    private static final HashSet<String> taxRuleIds = new HashSet<>();
    private TaxRuleDAO taxRuleDAO = new TaxRuleDAO();
    private TaxRuleSelectDAO taxRuleSelectDAO = new TaxRuleSelectDAO();
    private TaxRule taxRule = new TaxRule();
    static Logger log = Logger.getLogger(TaxRuleCache.class);

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
                taxRule = taxRuleSelectDAO.getTaxRuleByHotelId(hotelId);
                

                if (taxRule != null) {
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

    public String addTaxRule(TaxRule taxRule) throws HMSRuntimeException {
        String taxRulelId = taxRuleDAO.addTaxRule(taxRule);

        if (taxRulelId != null) {
            taxRule.setHotelId(taxRulelId);
            if (hotelTaxRuleCache.get(taxRule.getTaxRuleId()) == null) {
                taxRuleIds.add(taxRule.getHotelId());
                hotelTaxRuleCache.put(taxRule.getHotelId(), taxRule);
            }
        }
        return taxRulelId;
    }

    public TaxRule getTaxRuleById(String hotelId,String taxRuleId) throws HMSRuntimeException {
        TaxRule taxRule = null;
        try {
            taxRule = hotelTaxRuleCache.get(taxRuleId);

            if (taxRule == null) {
                taxRule = taxRuleSelectDAO.getTaxRuleByHotelId(hotelId);

                taxRule = Objects.requireNonNull(taxRule);
                hotelTaxRuleCache.put(taxRuleId, taxRule);
            }
        } catch (NullPointerException npe) {
            log.info("No Hotel Present for the given hotelId[" + taxRuleId + "]");
        } finally {
            return taxRule;
        }
    }


    public List<TaxRule> getTaxRulesByHotelId(String hotelId) throws HMSRuntimeException {
        TaxRule taxRule = null;
        /*try {
            taxRule = hotelTaxRuleCache.get(hotelId);

            if (taxRule == null) {
                taxRule = taxRuleDAO.getTaxRuleByHotelId(hotelId);

                taxRule = Objects.requireNonNull(taxRule);
                hotelTaxRuleCache.put(taxRuleId, taxRule);
            }
        } catch (NullPointerException npe) {
            log.info("No Hotel Present for the given hotelId[" + taxRuleId + "]");
        } finally {
            return taxRule;
        }*/
        return null;
    }





    public List<TaxRule> getAllTaxRules(String hotelId) throws HMSRuntimeException {

        ArrayList<TaxRule> taxRules = new ArrayList<>();
        taxRules.addAll(hotelTaxRuleCache.values());
        if (taxRules.isEmpty()) {
            initCache(hotelId);
            taxRules.addAll(hotelTaxRuleCache.values());
        }
        return taxRules;
    }

    public List<String> getAllTaxRuleIds() {
        ArrayList<String> TaxRulelIds = new ArrayList<>();
        TaxRulelIds.addAll(hotelTaxRuleCache.keySet());
        return TaxRulelIds;
    }


    public Boolean isTaxRulePresent(String hotelId,String taxRuleId) {
        Boolean isTaxPresent = false;
        TaxRule taxRule = null;
        try {
            taxRule = getTaxRuleById(hotelId,taxRuleId);
            if (taxRule != null && taxRule.getHotelId() != null) {
                isTaxPresent = true;
            }
        } catch (HMSRuntimeException e) {
            log.error("Exception occured while checking if TaxRule[" + taxRule.getHotelId() + "] is present in the system");
        }
        return isTaxPresent;


    }
}