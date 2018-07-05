package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.hotel.dao.HotelDAO;

import java.util.List;

public class HotelAPI implements HMSAPIProvider {

    HotelCache hotelCache = new HotelCache();
    HotelDAO hotelDAO = new HotelDAO();

    @Override
    public Object init(Object object) {
        return null;
    }
    @Override
    public Object process(Object object) {
        return null;
    }

    public List<Hotel> fetchAllHotels()  {
       return hotelCache.fetchAllHotels();

    }

    public Hotel fetchHotelByHotelId(String hotelId){
        return hotelCache.fetchHotelById(hotelId);
    }

    public Hotel fetchHotelDetails(String employeeId) {

        Hotel hotel = null;
        try{
            hotel = hotelDAO.fetchHotelByEmployeeId(employeeId);
        }catch(HMSException e){
            throw new HMSException(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Hotel does not exist for this employee");
        }finally{
            return hotel;
        }
    }

    public Hotel fetchHotelByEmployeeId(String employeeId){
        Hotel hotel = null;
        try{
            hotel = hotelDAO.fetchHotelByHotelId(employeeId);
        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Hotel Details not available for given emploeeId");
        }finally{
            return hotel;
        }

    }


    @Override
    public Boolean validate(Object object) throws HMSException {
        return null;
    }



}
