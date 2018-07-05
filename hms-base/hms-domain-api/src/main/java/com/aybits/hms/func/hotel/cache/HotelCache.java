package com.aybits.hms.func.hotel.cache;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.dao.HotelDAO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HotelCache {

    private static volatile ConcurrentHashMap<String, Hotel> hotelConcurrentHashMap = null;
    private static volatile HashSet<String> hotelIds = new HashSet<>();
    private static HotelDAO hotelDAO = new HotelDAO();

    public Boolean initCache() throws HMSException{

        Boolean isHotelCacheInitialized = false;
        if(hotelConcurrentHashMap == null){
                List<Hotel> hotels = null;
                try {
                    synchronized (HotelCache.class) {
                        hotels = hotelDAO.fetchAllHotels();
                        if(!hotels.isEmpty()) {
                            for (Hotel hotel : hotels) {
                                addHotel(hotel);
                            }
                        }
                    }
                }catch(HMSException e){
                    throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all hotel details failed");
                }finally{
                    if(hotelConcurrentHashMap != null && !hotelConcurrentHashMap.keySet().isEmpty()){
                        isHotelCacheInitialized = true;
                    }
                }
        }


        return isHotelCacheInitialized;
    }
    public void addHotel(Hotel hotel) {
        if (hotelConcurrentHashMap.get(hotel.getHotelId()) == null) {
            hotelIds.add(hotel.getHotelId());
            hotelConcurrentHashMap.put(hotel.getHotelId(), hotel);
        }
    }

    public void updateHotel(Hotel hotel) {
        String hotelId = hotel.getHotelId();
        if(hotelIds.contains(hotel.getHotelId())) {
            hotelConcurrentHashMap.remove(hotelId);
        }
        hotelConcurrentHashMap.put(hotelId, hotel);
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

    public static ConcurrentHashMap<String,Hotel> getHotelConcurrentHashMap(){
        return hotelConcurrentHashMap;
    }
}
