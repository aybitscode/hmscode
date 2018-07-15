package com.aybits.hms.func.common.cache;

import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
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

    public Boolean initHMSCache(){
        Boolean isCustomerCacheLoaded = new CustomerCache().initCache();
        Boolean isEmployeeCacheLoaded = new EmployeeCache().initCache();
        Boolean isHotelCacheLoaded    = new HotelCache().initCache();
        Boolean isRoomCacheLoaded     = new RoomCache().initCache();
        Boolean isRoomCategoryCacheLoaded  = new RoomCategoryCache().initCache();
        Boolean isIdentityParamCacheLoaded = new IdentificationParamCache().initCache();
        Boolean isFacilityCacheLoaded = new FacilityCache().initCache();
        Boolean isVoucherCacheLoaded = new VoucherCache().initCache();

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

    public void reloadHMSCache(){
        initHMSCache();
    }

    public Boolean isHMSCacheLoaded(){
        return isHMSCacheLoaded;
    }


}
