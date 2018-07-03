package com.aybits.hms.func.common.cache;

import com.aybits.hms.func.customer.cache.CustomerCache;
import com.aybits.hms.func.employee.cache.EmployeeCache;
import com.aybits.hms.func.facility.cache.FacilityCache;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.identificationparam.cache.IdentificationParamCache;
import com.aybits.hms.func.room.cache.RoomCache;
import com.aybits.hms.func.room.cache.RoomCategoryCache;
import com.aybits.hms.func.voucher.cache.VoucherCache;

import java.util.HashMap;

public class HMSCache {

    private static Boolean isHMSCacheLoaded = false;
    private HashMap<String,Object> hmsCache = new HashMap<>();

    public static Boolean initHMSCache(){
        Boolean isCustomerCacheLoaded = CustomerCache.initCache();
        Boolean isEmployeeCacheLoaded = EmployeeCache.initCache();
        Boolean isHotelCacheLoaded    = HotelCache.initCache();
        Boolean isRoomCacheLoaded     = RoomCache.initCache();
        Boolean isRoomCategoryCacheLoaded  = RoomCategoryCache.initCache();
        Boolean isIdentityParamCacheLoaded = IdentificationParamCache.initCache();
        Boolean isFacilityCacheLoaded = FacilityCache.initCache();
        Boolean isVoucherCacheLoaded = VoucherCache.initCache();

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

        return isHMSCacheLoaded;
    }

    public static void reloadHMSCache(){
        initHMSCache();
    }

    public static Boolean isHMSCacheLoaded(){
        return isHMSCacheLoaded;
    }


}
