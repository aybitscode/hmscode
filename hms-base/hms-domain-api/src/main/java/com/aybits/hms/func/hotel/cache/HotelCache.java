package com.aybits.hms.func.hotel.cache;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.dao.HotelDAO;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class HotelCache {

    static Logger log = Logger.getLogger(HotelCache.class);
    private static final ConcurrentHashMap<String, Hotel> hotelConcurrentHashMap = new ConcurrentHashMap<String,Hotel>();
    private static final HashSet<String> hotelIds = new HashSet<>();
    private HotelDAO hotelDAO = new HotelDAO();

    public Boolean initCache(){

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
                    //LOG Cache Initialization failed
                  //  throw new HMSException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all hotel details failed");
                }finally{
                    if(!hotelConcurrentHashMap.keySet().isEmpty()){
                        isHotelCacheInitialized = true;
                    }
                }
        }


        return isHotelCacheInitialized;
    }

    public void addHotel(Hotel hotel) throws HMSException {
        Boolean isHotelAdditionSuccessful = hotelDAO.addHotel(hotel);
        if(isHotelAdditionSuccessful){
            if (hotelConcurrentHashMap.get(hotel.getHotelId()) == null) {
                hotelIds.add(hotel.getHotelId());
                hotelConcurrentHashMap.put(hotel.getHotelId(), hotel);
            }
        }

    }

    public void updateHotel(Hotel hotel) throws HMSException {
        Boolean isHotelUpdateSuccessful = hotelDAO.updateHotel(hotel);
        if(isHotelUpdateSuccessful) {
            String hotelId = hotel.getHotelId();
            if (hotelIds.contains(hotel.getHotelId())) {
                hotelConcurrentHashMap.remove(hotelId);
            }
            hotelConcurrentHashMap.put(hotelId, hotel);
        }
    }


    public Hotel fetchHotelById(String hotelId) throws HMSException {
        try{
            Hotel hotel = hotelConcurrentHashMap.get(hotelId);
            if (hotel != null)
                return hotel;
            else{
                hotel = hotelDAO.fetchHotelByHotelId(hotelId);
                hotel = Objects.requireNonNull(hotel);
                hotelConcurrentHashMap.put(hotelId,hotel);
                return hotel;
            }
        }catch(NullPointerException npe){
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "No Hotel Present for the given hotelId["+hotelId+"]");
        }


    }


    public  List<Hotel> fetchAllHotels() throws HMSException {

        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels.addAll(hotelConcurrentHashMap.values());
        if(hotels.isEmpty()){
            initCache();
            hotels.addAll(hotelConcurrentHashMap.values());
        }
        return hotels;
    }

    public List<String> fetchAllHotelIds() {
        ArrayList<String> hotelIds = new ArrayList<>();
        hotelIds.addAll(hotelConcurrentHashMap.keySet());
        return hotelIds;
    }

}
