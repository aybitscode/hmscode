package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.hotel.dao.HotelDAO;

import java.util.List;

public class HotelAPI extends HMSAPIProviderImpl {

    HotelCache hotelCache = new HotelCache();
    HotelDAO hotelDAO = new HotelDAO();


    public List<Hotel> fetchAllHotels()  {
        List<Hotel> hotels = null;
        try {
            hotels = hotelCache.fetchAllHotels();
        }catch(HMSException he){

        }finally{
            return hotels;
        }

    }

    public Hotel fetchHotelByHotelId(String hotelId){
        Hotel hotel = null;
        try{
            hotel = hotelCache.fetchHotelById(hotelId);
        }catch(HMSException he){

        }finally {
            return hotel;
        }
    }


    public Hotel fetchHotelByEmployeeId(String employeeId){
        Hotel hotel = null;
        try{
            hotel = hotelDAO.fetchHotelByEmployeeId(employeeId);
        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Hotel Details not available for given emploeeId");
        }finally{
            return hotel;
        }

    }


}
