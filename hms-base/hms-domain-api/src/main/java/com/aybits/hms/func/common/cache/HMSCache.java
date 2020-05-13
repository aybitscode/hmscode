package com.aybits.hms.func.common.cache;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.amenity.dao.AmenityCache;
import com.aybits.hms.func.customer.cache.CustomerCache;
import com.aybits.hms.func.employee.cache.EmployeeCache;
import com.aybits.hms.func.facility.dao.FacilityCache;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.room.cache.RoomCache;
import com.aybits.hms.func.room.cache.RoomCategoryCache;
import com.aybits.hms.func.service.dao.ServiceCache;
import com.aybits.hms.func.service.dao.ServiceDAO;
import com.aybits.hms.func.voucher.dao.VoucherDAO;
import org.apache.log4j.Logger;

public class HMSCache {

    private static Boolean isHMSCacheLoaded = false;



    static Logger log = Logger.getLogger(VoucherDAO.VoucherCache.class);

    private static HMSCache hmsCache;
    private static HotelCache hotelCache;
    private static EmployeeCache employeeCache;
    private static CustomerCache customerCache;
    private static RoomCategoryCache roomCategoryCache;
    private static RoomCache roomCache;
    private static VoucherDAO.VoucherCache voucherCache;
    private static FacilityCache facilityCache;
    private static AmenityCache amenityCache;
    private static ServiceCache serviceCache;
    /**
     * This API Loads the HMS Cache during application startup
     * @return
     */
    public static Boolean initHMSCache(){

        try {
            hmsCache = new HMSCache();
            HotelCache hotelCache = new HotelCache();
            Boolean isHotelCacheLoaded = hotelCache.initCache();
            hmsCache.setHotelCache(hotelCache);
                if (isHotelCacheLoaded) {
                    for (String hotelId : hotelCache.fetchAllHotelIds()) {
                        EmployeeCache employeeCache = new EmployeeCache();
                        Boolean isEmployeeCacheLoaded = employeeCache.initCache(hotelId);
                        if (isEmployeeCacheLoaded) {
                            hmsCache.setEmployeeCache(employeeCache);
                        }

                        RoomCache roomCache = new RoomCache();
                        Boolean isRoomCacheLoaded = roomCache.initCache(hotelId);
                        if (isRoomCacheLoaded) {
                            hmsCache.setRoomCache(roomCache);
                        }

                        Boolean isRoomCategoryCacheLoaded = new RoomCategoryCache().initCache(hotelId);
                        RoomCategoryCache roomCategoryCache = new RoomCategoryCache();
                        if (isRoomCategoryCacheLoaded) {
                            hmsCache.setRoomCategoryCache(roomCategoryCache);
                        }

                        FacilityCache facilityCache = new FacilityCache();
                        Boolean isFacilityCacheLoaded = new FacilityCache().initCache(hotelId);
                        if (isFacilityCacheLoaded) {
                            hmsCache.setFacilityCache(facilityCache);
                        }

                        VoucherDAO.VoucherCache voucherCache = new VoucherDAO.VoucherCache();
                        Boolean isVoucherCacheLoaded = voucherCache.initCache(hotelId);
                        if (isVoucherCacheLoaded) {
                            hmsCache.setVoucherCache(voucherCache);
                        }

                        ServiceCache serviceCache = new ServiceCache();
                        Boolean isServicesCacheLoaded = serviceCache.initCache(hotelId);
                        if (isServicesCacheLoaded) {
                            hmsCache.setVoucherCache(voucherCache);
                        }

                        CustomerCache customerCache = new CustomerCache();
                        Boolean isCustomerCacheLoaded = customerCache.initCache(hotelId);
                        if (isCustomerCacheLoaded) {
                            hmsCache.setCustomerCache(customerCache);
                        }


                        if (isCustomerCacheLoaded &&
                                isEmployeeCacheLoaded &&
                                isHotelCacheLoaded &&
                                isRoomCacheLoaded &&
                                isRoomCategoryCacheLoaded &&
                                isFacilityCacheLoaded &&
                                isVoucherCacheLoaded) {

                            isHMSCacheLoaded = true;

                        }

                    }


                }
            }catch(HMSRuntimeException he){
                hmsCache = null;
                isHMSCacheLoaded = false;
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

    public VoucherDAO.VoucherCache getVoucherCache() {
        return voucherCache;
    }

    private void setVoucherCache(VoucherDAO.VoucherCache voucherCache) {
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

    public ServiceCache getServicesCache() {
        return serviceCache;
    }

    private void setServicesCache(ServiceCache serviceCache) {
        HMSCache.serviceCache = serviceCache;
    }

    public static HMSCache getHmsCache(){
        return hmsCache;
    }
}
