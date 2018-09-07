package com.aybits.hms.func.common.cache;

import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.amenity.cache.AmenityCache;
import com.aybits.hms.func.customer.cache.CustomerCache;
import com.aybits.hms.func.employee.cache.EmployeeCache;
import com.aybits.hms.func.facility.cache.FacilityCache;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.identificationparam.cache.IdentificationParamCache;
import com.aybits.hms.func.room.cache.RoomCache;
import com.aybits.hms.func.room.cache.RoomCategoryCache;
import com.aybits.hms.func.services.cache.ServicesCache;
import com.aybits.hms.func.voucher.cache.VoucherCache;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HMSCache {

    private static Boolean isHMSCacheLoaded = false;



    static Logger log = Logger.getLogger(VoucherCache.class);

    private static HMSCache hmsCache;
    private static HotelCache hotelCache;
    private static EmployeeCache employeeCache;
    private static CustomerCache customerCache;
    private static RoomCategoryCache roomCategoryCache;
    private static RoomCache roomCache;
    private static VoucherCache voucherCache;
    private static FacilityCache facilityCache;
    private static AmenityCache amenityCache;
    private static ServicesCache servicesCache;
    /**
     * This API Loads the HMS Cache during application startup
     * @return
     */
    public static Boolean initHMSCache(){
        hmsCache = new HMSCache();
        HotelCache hotelCache = new HotelCache();
        Boolean isHotelCacheLoaded   = hotelCache.initCache();
        hmsCache.setHotelCache(hotelCache);
        if(isHotelCacheLoaded){
            for(String hotelId:hotelCache.fetchAllHotelIds()) {
                EmployeeCache employeeCache = new EmployeeCache();
                Boolean isEmployeeCacheLoaded = employeeCache.initCache(hotelId);
                if(isEmployeeCacheLoaded){
                    hmsCache.setEmployeeCache(employeeCache);
                }

                RoomCache roomCache = new RoomCache();
                Boolean isRoomCacheLoaded = roomCache.initCache(hotelId);
                if(isRoomCacheLoaded){
                    hmsCache.setRoomCache(roomCache);
                }

                Boolean isRoomCategoryCacheLoaded = new RoomCategoryCache().initCache(hotelId);
                RoomCategoryCache roomCategoryCache = new RoomCategoryCache();
                if(isRoomCategoryCacheLoaded){
                    hmsCache.setRoomCategoryCache(roomCategoryCache);
                }

               /* Boolean isIdentityParamCacheLoaded = new IdentificationParamCache().initCache(hotelId);
                IdentificationParamCache identificationParamCache = new IdentificationParamCache();
                if(isIdentityParamCacheLoaded){
                    hmsCache.setI
                }*/

                FacilityCache facilityCache = new FacilityCache();
                Boolean isFacilityCacheLoaded = new FacilityCache().initCache(hotelId);
                if(isFacilityCacheLoaded){
                    hmsCache.setFacilityCache(facilityCache);
                }

                VoucherCache voucherCache = new VoucherCache();
                Boolean isVoucherCacheLoaded = voucherCache.initCache(hotelId);
                if(isVoucherCacheLoaded){
                    hmsCache.setVoucherCache(voucherCache);
                }

                ServicesCache servicesCache = new ServicesCache();
                Boolean isServicesCacheLoaded = servicesCache.initCache(hotelId);
                if(isServicesCacheLoaded){
                    hmsCache.setVoucherCache(voucherCache);
                }

                CustomerCache customerCache = new CustomerCache();
                Boolean isCustomerCacheLoaded = customerCache.initCache(hotelId);
                if(isCustomerCacheLoaded){
                    hmsCache.setCustomerCache(customerCache);
                }


                if(isCustomerCacheLoaded &&
                        isEmployeeCacheLoaded &&
                        isHotelCacheLoaded &&
                        isRoomCacheLoaded &&
                        isRoomCategoryCacheLoaded &&
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
       return isHMSCacheLoaded;
    }

    public HotelCache getHotelCache() {
        return hotelCache;
    }

    private void setHotelCache(HotelCache hotelCache) {
        HMSCache.hotelCache = hotelCache;
    }

    public EmployeeCache getEmployeeCache() {
        return employeeCache;
    }

    private void setEmployeeCache(EmployeeCache employeeCache) {
        HMSCache.employeeCache = employeeCache;
    }

    public CustomerCache getCustomerCache() {
        return customerCache;
    }

    private void setCustomerCache(CustomerCache customerCache) {
        HMSCache.customerCache = customerCache;
    }

    public RoomCategoryCache getRoomCategoryCache() {
        return roomCategoryCache;
    }

    private void setRoomCategoryCache(RoomCategoryCache roomCategoryCache) {
        HMSCache.roomCategoryCache = roomCategoryCache;
    }

    public RoomCache getRoomCache() {
        return roomCache;
    }

    private void setRoomCache(RoomCache roomCache) {
        HMSCache.roomCache = roomCache;
    }

    public VoucherCache getVoucherCache() {
        return voucherCache;
    }

    private void setVoucherCache(VoucherCache voucherCache) {
        HMSCache.voucherCache = voucherCache;
    }

    public FacilityCache getFacilityCache() {
        return facilityCache;
    }

    private void setFacilityCache(FacilityCache facilityCache) {
        HMSCache.facilityCache = facilityCache;
    }

    public AmenityCache getAmenityCache() {
        return amenityCache;
    }

    private void setAmenityCache(AmenityCache amenityCache) {
        HMSCache.amenityCache = amenityCache;
    }

    public ServicesCache getServicesCache() {
        return servicesCache;
    }

    private void setServicesCache(ServicesCache servicesCache) {
        HMSCache.servicesCache = servicesCache;
    }
}
