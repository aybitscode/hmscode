package com.aybits.hms.func.hotel.cache;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.dao.HotelDAO;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HotelCache {

    static Logger log = Logger.getLogger(HotelCache.class);
    private static final ConcurrentHashMap<String, Hotel> hotelConcurrentHashMap = new ConcurrentHashMap<String,Hotel>();
    private static final HashSet<String> hotelIds = new HashSet<>();
    private HotelDAO hotelDAO = new HotelDAO();

    public Boolean initCache() throws HMSException{

        Boolean isHotelCacheInitialized = false;
        if(hotelConcurrentHashMap.isEmpty()){
                List<Hotel> hotels = null;
                try {
                        hotels = hotelDAO.fetchAllHotels();
                        if(!hotels.isEmpty()) {
                            for (Hotel hotel : hotels) {
                                hotelIds.add(hotel.getHotelId());
                                hotelConcurrentHashMap.put(hotel.getHotelId(), hotel);
                            }
                        }

                }catch(HMSException e){
                    throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all hotel details failed");
                }finally{
                    if(!hotelConcurrentHashMap.keySet().isEmpty()){
                        isHotelCacheInitialized = true;
                    }
                }
        }


        return isHotelCacheInitialized;
    }

    public Boolean addHotel(Hotel hotel){
            Boolean isHotelAdditionSuccessful = false;
            if (hotelConcurrentHashMap.get(hotel.getHotelId()) == null) {
                hotelIds.add(hotel.getHotelId());
                hotelConcurrentHashMap.put(hotel.getHotelId(), hotel);
                isHotelAdditionSuccessful = true;
            }
            return isHotelAdditionSuccessful;
    }

    public Boolean deleteHotel(String hotelId){
        Boolean isHotelDeleted = false;

        if (hotelIds.contains(hotelId)) {
            hotelConcurrentHashMap.remove(hotelId);
            hotelIds.remove(hotelId);
            isHotelDeleted = true;
        }
        return isHotelDeleted;
    }

    public void updateHotel(Hotel hotel) {
        String hotelId = hotel.getHotelId();
        if(deleteHotel(hotelId)){
            addHotel(hotel);
        }


    }


    public Hotel fetchHotelById(String hotelId) {
        Hotel hotel = hotelConcurrentHashMap.get(hotelId);
        if (hotel != null)
            return hotel;
        else{
            return null;
        }

    }


    public  List<Hotel> fetchAllHotels() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels.addAll(hotelConcurrentHashMap.values());
        return hotels;
    }

    public List<String> fetchAllHotelIds() {
        ArrayList<String> hotelIds = new ArrayList<>();
        hotelIds.addAll(hotelConcurrentHashMap.keySet());
        return hotelIds;
    }

}
