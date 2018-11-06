package com.aybits.hms.func.service.dao;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.service.beans.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceCache {

    private static ConcurrentHashMap<String, HashMap<String, Service>> hotelServiceCache = new ConcurrentHashMap<>();
    private HashMap<String, Service> serviceHashMap = new HashMap<>();

    private ServiceSelectDAO serviceSelectDAO = new ServiceSelectDAO();
    private ServiceDAO serviceDAO = new ServiceDAO();

    public Boolean initCache(String hotelId) throws HMSRuntimeException {

        Boolean isCacheInitialized = initializeHotelServiceCache(hotelId, false);

        return isCacheInitialized;
    }


    private Boolean initializeHotelServiceCache(String hotelId, Boolean reload) throws HMSRuntimeException {

        Boolean isCacheInitialized = false;
        if (reload) {
            hotelServiceCache = new ConcurrentHashMap<>();
        }
        if (hotelServiceCache.isEmpty()) {
            try {
                serviceHashMap = serviceSelectDAO.getServicesMapByHotelId(hotelId);
                if (!serviceHashMap.isEmpty()) {
                    hotelServiceCache.put(hotelId, serviceHashMap);
                }
            } catch (HMSRuntimeException e) {
                //LOG Cache Initialization failed
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE, "Loading Hotel Services Cache failed"));
            } finally {
                if (!hotelServiceCache.keySet().isEmpty()) {
                    isCacheInitialized = true;
                }
            }
        }
        return isCacheInitialized;
    }

    public String addService(Service service) throws HMSRuntimeException {
        String hotelId = service.getHotelId();
        String serviceId = service.getServiceId();

        Service serviceFromCache = this.getService(hotelId, serviceId);

        if (serviceFromCache == null) {
            Boolean isServiceAdditionSuccessful = serviceDAO.addService(service);
            if (isServiceAdditionSuccessful) {
                updateService(service);
            }
        }
        return serviceId;
    }

    private void updateService(Service service) {
        String hotelId = service.getHotelId();
        String serviceId = service.getServiceId();
        HashMap<String, Service> updatedServiceMap = new HashMap<>();
        serviceHashMap = hotelServiceCache.get(hotelId);
        updatedServiceMap.putAll(serviceHashMap);
        updatedServiceMap.put(serviceId, service);
        hotelServiceCache.remove(hotelId);
        hotelServiceCache.put(hotelId, updatedServiceMap);
    }

    public Service getService(String hotelId, String serviceId) throws HMSRuntimeException {

        serviceHashMap = hotelServiceCache.get(hotelId);
        Service service = serviceHashMap.get(serviceId);
        if (service == null) {
            service = serviceSelectDAO.getService(hotelId, serviceId);
            updateService(service);
        }
        return service;
    }

    public List<Service> getAllServices(String hotelId) throws HMSRuntimeException{
        ArrayList<Service> amenities = new ArrayList<>();
        serviceHashMap = hotelServiceCache.get(hotelId);

        if (serviceHashMap.keySet().isEmpty()) {
            reloadHotelServicesCache(hotelId);
        }

        amenities.addAll(serviceHashMap.values());
        return amenities;
    }

    public List<Service> getAllAvailableServices(String hotelId, Boolean isAvailable) throws HMSRuntimeException{
        List<Service> availableServices = new ArrayList<Service>();
        serviceHashMap = hotelServiceCache.get(hotelId);

        if (serviceHashMap.keySet().isEmpty()) {
            reloadHotelServicesCache(hotelId);
        }

        for (Service service : serviceHashMap.values()) {
            if (service.isAvailable()) {
                availableServices.add(service);
            }
        }
        return availableServices;
    }

    public List<Service> getAllChargeableServices(String hotelId, Boolean isChargeable) throws HMSRuntimeException {
        List<Service> availableServices = new ArrayList<Service>();
        serviceHashMap = hotelServiceCache.get(hotelId);

        if (serviceHashMap.keySet().isEmpty()) {
            reloadHotelServicesCache(hotelId);
        }

        for (Service service : serviceHashMap.values()) {
            if (service.isChargeable()) {
                availableServices.add(service);
            }
        }

        return availableServices;
    }


    public Boolean reloadHotelServicesCache(String hotelId) throws HMSRuntimeException {

        Boolean reloadCache = true;
        Boolean isCacheInitialized = initializeHotelServiceCache(hotelId, reloadCache);
        return isCacheInitialized;

    }


    public ConcurrentHashMap<String, HashMap<String, Service>> getServiceCache() {
        return hotelServiceCache;
    }


}
