package com.aybits.hms.func.corporate.cache;

import com.aybits.hms.func.corporate.beans.Corporate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CorporateCache {

    private static ConcurrentHashMap<String, Corporate> corporateCache = new ConcurrentHashMap<>();
    private static HashSet<String> corporateIds = new HashSet<>();

    public static Boolean initCache(){
        return false;
    }
    public void addCorporate(Corporate corporate) {
        if (corporateCache.get(String.valueOf(corporate.getCorporateId())) == null) {
            corporateIds.add(corporate.getCorporateId());
            corporateCache.put(corporate.getCorporateId(), corporate);
        }
    }

    public void updateCorporate(Corporate corporate) {
        String corporateId = corporate.getCorporateId();
        corporateCache.remove(corporateId);
        corporateCache.put(corporateId, corporate);
    }


    public Corporate getCorporateById(String corporateId) {
        Corporate corporate = corporateCache.get(corporateId);
        if (corporate != null)
            return corporate;
        else
            return null;
    }


    public List<Corporate> getAllCorporates() {
        ArrayList<Corporate> corporates = new ArrayList<>();
        corporates.addAll(corporateCache.values());
        return corporates;
    }

    public List<String> getAllCorporateIds() {
        ArrayList<String> corporateIds = new ArrayList<>();
        corporateIds.addAll(corporateCache.keySet());
        return corporateIds;
    }

    public static ConcurrentHashMap<String,Corporate> getCorporateCache(){
        return corporateCache;
    }
}
