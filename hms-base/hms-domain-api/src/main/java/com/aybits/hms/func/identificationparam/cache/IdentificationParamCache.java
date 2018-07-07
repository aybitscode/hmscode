package com.aybits.hms.func.identificationparam.cache;

import com.aybits.hms.func.identificationparam.beans.IdentificationParam;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.List;

public class IdentificationParamCache {



    private static ConcurrentHashMap<String, IdentificationParam> identificationParamCache = new ConcurrentHashMap<>();
    private static HashSet<String> identificationParamIds = new HashSet<>();

    public Boolean initCache(){
        return false;
    }

    public void addIdentificationParam(IdentificationParam identificationParam) {
        if (identificationParamCache.get(String.valueOf(identificationParam.getIdentificationParamId())) == null) {
            identificationParamIds.add(identificationParam.getIdentificationParamId());
            identificationParamCache.put(identificationParam.getIdentificationParamId(), identificationParam);
        }
    }

    public void updateIdentificationParam(IdentificationParam identificationParam) {
        String identificationParamId = identificationParam.getIdentificationParamId();
        identificationParamCache.remove(identificationParamId);
        identificationParamCache.put(identificationParamId, identificationParam);
    }


    public IdentificationParam getIdentificationParamById(String identificationParamId) {
        IdentificationParam identificationParam = identificationParamCache.get(identificationParamId);
        if (identificationParam != null)
            return identificationParam;
        else
            return null;
    }


    public List<IdentificationParam> getAllIdentificationParams() {
        ArrayList<IdentificationParam> identificationParams = new ArrayList<>();
        identificationParams.addAll(identificationParamCache.values());
        return identificationParams;
    }

    public List<String> getAllIdentificationParamIds() {
        ArrayList<String> identificationParamIds = new ArrayList<>();
        identificationParamIds.addAll(identificationParamCache.keySet());
        return identificationParamIds;
    }

    public static ConcurrentHashMap<String,IdentificationParam> getIdentificationParamCache(){
        return identificationParamCache;
    }



}
