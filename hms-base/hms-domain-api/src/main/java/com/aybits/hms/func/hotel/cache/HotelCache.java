package com.aybits.hms.func.hotel.cache;

import com.aybits.hms.func.hotel.beans.Hotel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HotelCache {

    private static ConcurrentHashMap<String, Hotel> hotelCache = new ConcurrentHashMap<>();
    private static HashSet<String> hotelIds = new HashSet<>();

    public static Boolean initCache(){
        return false;
    }
    public void addHotel(Hotel hotel) {
        if (hotelCache.get(String.valueOf(hotel.getHotelId())) == null) {
            hotelIds.add(hotel.getHotelId());
            hotelCache.put(hotel.getHotelId(), hotel);
        }
    }

    public void updateHotel(Hotel hotel) {
        String hotelId = hotel.getHotelId();
        hotelCache.remove(hotelId);
        hotelCache.put(hotelId, hotel);
    }


    public Hotel getHotelById(String hotelId) {
        Hotel hotel = hotelCache.get(hotelId);
        if (hotel != null)
            return hotel;
        else
            return null;
    }


    public List<Hotel> getAllHotels() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels.addAll(hotelCache.values());
        return hotels;
    }

    public List<String> getAllHotelIds() {
        ArrayList<String> hotelIds = new ArrayList<>();
        hotelIds.addAll(hotelCache.keySet());
        return hotelIds;
    }

    public static ConcurrentHashMap<String,Hotel> getHotelCache(){
        return hotelCache;
    }
}
