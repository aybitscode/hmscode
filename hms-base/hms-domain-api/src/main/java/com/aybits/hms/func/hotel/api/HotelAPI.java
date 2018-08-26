package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.hotel.dao.HotelDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class HotelAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(HotelAPI.class);
    HotelCache hotelCache = new HotelCache();
    HotelDAO hotelDAO = new HotelDAO();



    public String addHotel(Hotel hotel) throws HMSException {
        String hotelId = null;

        if(isHotelAlreadyPresent(hotel)){
            log.info("Exception occured while adding hotel::Hotel already exists");
            throw new HMSException(HMSErrorCodes.HOTEL_ALREADY_EXISTS, "Hotel already exists");
        }
        if (hotel.getHotelId() != null && hotel.getHotelId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
            try {
                hotelId = hotelCache.addHotel(hotel);
                if(hotelId == null){
                    throw new NullPointerException();
                }
            } 
            catch (NullPointerException | HMSException e) {
                log.info("Exception occured while adding hotel::"+hotel.getHotelId());
                throw new HMSException(HMSErrorCodes.HOTEL_ADDITION_FAILED, "Adding Hotel details failed");
            }
        }
        return hotelId;
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

    public Boolean updateHotel(Hotel hotel) throws HMSException {

        Boolean isHotelUpdateSuccessful = false;
        if (hotel.getHotelId() == HMSAPIConstants.TO_BE_GENERATED) try {
            isHotelUpdateSuccessful = hotelCache.updateHotel(hotel);
        } catch (HMSException e) {
            log.info("Exception occured while adding hotel::" + hotel.getHotelId());
            throw new HMSException(HMSErrorCodes.HOTEL_UPDATE_FAILED, "Adding Hotel details failed");
        }
        return isHotelUpdateSuccessful;

    }

    public Boolean isHotelAlreadyPresent(Hotel hotelFromUI){


        Boolean isHotelAlreadyPresent = false;
        String primaryEmail = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryEmail();
        String primaryPhone = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryPhone();
        String primaryMobileNumber = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryMobileNumber();

        Hotel hotelFromDB = hotelDAO.fetchHotelByContactDetails(primaryEmail,primaryPhone,primaryMobileNumber);

        String clearTextFromUI = primaryEmail+primaryPhone+primaryMobileNumber;

        String uiHash = HMSUtilAPI.generateSHA256Hash(clearTextFromUI);

        primaryEmail = hotelFromDB.getHotelAttributes().getHotelContactDetails().getPrimaryEmail();
        primaryPhone = hotelFromDB.getHotelAttributes().getHotelContactDetails().getPrimaryPhone();
        primaryMobileNumber = hotelFromDB.getHotelAttributes().getHotelContactDetails().getPrimaryMobileNumber();

        String clearTextFromDB = primaryEmail+primaryPhone+primaryMobileNumber;

        String dbHash = HMSUtilAPI.generateSHA256Hash(clearTextFromDB);

        if(uiHash.equals(dbHash)){
            isHotelAlreadyPresent = true;
        }

        return isHotelAlreadyPresent;

    }


}
