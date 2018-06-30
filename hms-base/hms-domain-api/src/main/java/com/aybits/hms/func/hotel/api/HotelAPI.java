package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.dao.HotelDAO;

public class HotelAPI implements HMSAPIProvider {

    private HotelDAO hotelDAO = new HotelDAO();

    @Override
    public Object init(Object object) {
        return null;
    }
    @Override
    public Object process(Object object) {
        return null;
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

    public Hotel fetchHotelDetails(Integer hotelId){
        Hotel hotel = null;
        try{
            hotel = hotelDAO.fetchHotelByHotelId(hotelId);
        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Invalid Hotel ID");
        }finally{
            return hotel;
        }

    }


    @Override
    public Boolean validate(Object object) throws HMSException {
        return null;
    }



}
