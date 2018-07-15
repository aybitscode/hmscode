package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.hotel.dao.HotelDAO;

import java.util.List;

public class HotelAPI extends HMSAPIProviderImpl {

    HotelCache hotelCache = new HotelCache();
    HotelDAO hotelDAO = new HotelDAO();



    public Boolean addHotel(Hotel hotel) throws HMSException {
        Boolean isHotelAdditionSuccessful = false;
        if (hotel.getHotelId() == HMSAPIConstants.TO_BE_GENERATED) {
            try {

                isHotelAdditionSuccessful = hotelDAO.addHotel(hotel);
                if(isHotelAdditionSuccessful) {
               //     hotel;
                }
            } catch (HMSException e) {
                log.info("Exception occured while adding hotel");
                throw new HMSException(HMSErrorCodes.HOTEL_ADDITION_FAILED, "Adding Hotel details failed");
            }
        }
        return isHotelAdditionSuccessful;
    }
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
