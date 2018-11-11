package com.aybits.hms.func.hotel.cache;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.aybits.hms.func.hotel.dao.HotelDAO;
import com.aybits.hms.func.hotel.dao.HotelSelectDAO;
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
    private HotelSelectDAO hotelSelectDAO = new HotelSelectDAO();
    private HotelDAO hotelDAO = new HotelDAO();



    public Boolean initCache(){

        Boolean isHotelCacheInitialized = false;
        if(hotelConcurrentHashMap.isEmpty()){
                List<Hotel> hotels = null;
                try {
                        hotels = hotelSelectDAO.fetchAllHotels();
                        if(!hotels.isEmpty()) {
                            for (Hotel hotel : hotels) {
                                hotelIds.add(hotel.getHotelId());
                                hotelConcurrentHashMap.put(hotel.getHotelId(), hotel);
                            }
                        }

                }catch(HMSRuntimeException e){
                    //LOG Cache Initialization failed
                  //  throw new HMSRuntimeException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all hotel details failed");
                }finally{
                    if(!hotelConcurrentHashMap.keySet().isEmpty()){
                        isHotelCacheInitialized = true;
                    }
                }
        }


        return isHotelCacheInitialized;
    }

    public String addHotel(Hotel hotel) throws HMSRuntimeException {
        String hotelId = hotelDAO.addHotel(hotel);
        if(hotelId != null){
            hotel.setHotelId(hotelId);
            if (hotelConcurrentHashMap.get(hotel.getHotelId()) == null) {
                hotelIds.add(hotel.getHotelId());
                hotelConcurrentHashMap.put(hotel.getHotelId(), hotel);
            }
        }
        return String.valueOf(hotelId);
    }

    public Boolean updateHotel(Hotel hotel) throws HMSRuntimeException {
        Boolean isHotelUpdateSuccessful = hotelDAO.updateHotel(hotel);
        if(isHotelUpdateSuccessful) {
            String hotelId = hotel.getHotelId();
            if (hotelIds.contains(hotel.getHotelId())) {
                hotelConcurrentHashMap.remove(hotelId);
            }
            hotelConcurrentHashMap.put(hotelId, hotel);
        }
        return isHotelUpdateSuccessful;
    }

    public String addHotelRegistrationData(HotelRegistrationData hotelRegistrationData) throws HMSRuntimeException {
        String hotelRegistrationId = hotelDAO.addHotelRegistrationData(hotelRegistrationData);
        if(hotelRegistrationId == null){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null"));
        }
        return String.valueOf(hotelRegistrationId);
    }

    public Hotel fetchHotelById(String hotelId) throws HMSRuntimeException {
        Hotel hotel = null;
        try{
            hotel = hotelConcurrentHashMap.get(hotelId);
            if (hotel == null){
                hotel = hotelSelectDAO.fetchHotelByHotelId(hotelId);
                hotel = Objects.requireNonNull(hotel);
                hotelConcurrentHashMap.put(hotelId,hotel);
            }
        }catch(NullPointerException npe){
             log.info("No Hotel Present for the given hotelId["+hotelId+"]");
        }finally{
            return hotel;
        }
    }

   /* public Hotel fetchHotelByEmployeeId(String employeeId) throws HMSRuntimeException {
        Hotel hotel = null;
        try{
            hotel = hotelConcurrentHashMap.get(hotelId);
            if (hotel == null){
                hotel = hotelSelectDAO.fetchHotelByEmployeeId(employeeId);
                hotel = Objects.requireNonNull(hotel);
                hotelConcurrentHashMap.put(hotelId,hotel);
            }
        }catch(NullPointerException npe){
            log.info("No Hotel Present for the given hotelId["+hotelId+"]");
        }finally{
            return hotel;
        }
    }

*/



    public  List<Hotel> fetchAllHotels() throws HMSRuntimeException {

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


    public Boolean isHotelPresent(String hotelId){
        Boolean isHotelPresent = false;
        Hotel hotel = null;
        try {
            hotel = fetchHotelById(hotelId);
            if(hotel != null && hotel.getHotelId() != null){
                isHotelPresent = true;
            }
        } catch (HMSRuntimeException e) {
            log.error("Exception occured while checking if Hotel["+hotel.getHotelId()+"] is present in the system");
        }
        return isHotelPresent;

    }

}
