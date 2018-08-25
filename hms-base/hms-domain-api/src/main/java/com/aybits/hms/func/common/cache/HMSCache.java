package com.aybits.hms.func.common.cache;

import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.customer.cache.CustomerCache;
import com.aybits.hms.func.employee.cache.EmployeeCache;
import com.aybits.hms.func.facility.cache.FacilityCache;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.identificationparam.cache.IdentificationParamCache;
import com.aybits.hms.func.room.cache.RoomCache;
import com.aybits.hms.func.room.cache.RoomCategoryCache;
import com.aybits.hms.func.voucher.cache.VoucherCache;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HMSCache {

    private HashMap<String,Object> hmsCache = new HashMap<>();
    private Boolean isHMSCacheLoaded = false;


    static Logger log = Logger.getLogger(VoucherCache.class);
    private static final ConcurrentHashMap<String, ConcurrentHashMap> hmsHotelDeepCache = new ConcurrentHashMap<String,ConcurrentHashMap>();

    public Boolean initHMSCache(){
        HotelCache hotelCache = new HotelCache();
        Boolean isHotelCacheLoaded    = hotelCache.initCache();
        if(isHotelCacheLoaded){
            for(String hotelId:hotelCache.fetchAllHotelIds()) {
                EmployeeCache employeeCache = new EmployeeCache();
                Boolean isEmployeeCacheLoaded = employeeCache.initCache(hotelId);
                if(isEmployeeCacheLoaded){
                    hmsHotelDeepCache.put(hotelId+"_"+HMSAPIConstants.EMPLOYEE_CACHE,employeeCache.getEmployeeCache());
                }

                RoomCache roomCache = new RoomCache();
                Boolean isRoomCacheLoaded = roomCache.initCache(hotelId);
                if(isRoomCacheLoaded){
                    hmsHotelDeepCache.put(hotelId+"_"+HMSAPIConstants.ROOM_CACHE,roomCache.getRoomCache());
                }

                Boolean isRoomCategoryCacheLoaded = new RoomCategoryCache().initCache(hotelId);
                RoomCategoryCache roomCategoryCache = new RoomCategoryCache();
                if(isRoomCategoryCacheLoaded){
                    hmsHotelDeepCache.put(hotelId+"_"+HMSAPIConstants.ROOM_CATEGORY_CACHE,roomCategoryCache.getRoomCategoryCache());
                }

                Boolean isIdentityParamCacheLoaded = new IdentificationParamCache().initCache(hotelId);
                IdentificationParamCache identificationParamCache = new IdentificationParamCache();
                if(isIdentityParamCacheLoaded){
                    hmsHotelDeepCache.put(hotelId+"_"+HMSAPIConstants.IDENTIFICATION_PARAM_CACHE,identificationParamCache.getIdentificationParamCache());
                }

                Boolean isFacilityCacheLoaded = new FacilityCache().initCache(hotelId);
                FacilityCache facilityCache = new FacilityCache();
                if(isFacilityCacheLoaded){
                    hmsHotelDeepCache.put(hotelId+"_"+HMSAPIConstants.FACILITY_CACHE,facilityCache.getFacilityCache());
                }

                Boolean isVoucherCacheLoaded = new VoucherCache().initCache(hotelId);
                VoucherCache voucherCache = new VoucherCache();
                if(isVoucherCacheLoaded){
                    hmsHotelDeepCache.put(hotelId+"_"+HMSAPIConstants.VOUCHER_CACHE,voucherCache.getVoucherCache());
                }

                Boolean isCustomerCacheLoaded = new CustomerCache().initCache(hotelId);
                CustomerCache customerCache = new CustomerCache();
                if(isCustomerCacheLoaded){
                    hmsHotelDeepCache.put(hotelId+"_"+HMSAPIConstants.CUSTOMER_CACHE,customerCache.getCustomerCache());
                }
                if(isCustomerCacheLoaded &&
                        isEmployeeCacheLoaded &&
                        isHotelCacheLoaded &&
                        isRoomCacheLoaded &&
                        isRoomCategoryCacheLoaded &&
                        isIdentityParamCacheLoaded &&
                        isFacilityCacheLoaded &&
                        isVoucherCacheLoaded ){

                    isHMSCacheLoaded = true;

                }

            }

        }




        return isHMSCacheLoaded;
    }

    public void reloadHMSCache(){
        initHMSCache();
    }

    public Boolean isHMSCacheLoaded() {
       if(!hmsHotelDeepCache.isEmpty()){
            isHMSCacheLoaded = true;
       }
       return isHMSCacheLoaded;
    }


}
