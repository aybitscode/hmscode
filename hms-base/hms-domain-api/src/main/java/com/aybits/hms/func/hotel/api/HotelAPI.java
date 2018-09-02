package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.hotel.dao.HotelDAO;
import com.aybits.hms.func.hotel.dao.HotelSelectDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class HotelAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(HotelAPI.class);
    HotelCache hotelCache = new HotelCache();
    HotelSelectDAO hotelSelectDAO = new HotelSelectDAO();



    private String addHotel(Hotel hotel) throws HMSException {
        String hotelId = null;

        if(!isHotelAlreadyPresent(hotel)){
            if (hotel.getHotelId() != null && hotel.getHotelId().equals(HMSAPIConstants.TO_BE_GENERATED )) {
                try {
                    hotelId = hotelCache.addHotel(hotel);
                    if(hotelId == null){
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException npe) {
                    log.info("Exception occurred while adding hotel::"+hotel.getHotelId());
                    throw new HMSException(HMSErrorCodes.HOTEL_ADDITION_FAILED, "Adding Hotel details failed");
                }
            }

        }else{
            log.info("Exception occurred while adding hotel::Hotel already exists");
            throw new HMSException(HMSErrorCodes.HOTEL_ALREADY_EXISTS, "Hotel already exists");
        }

        return hotelId;
    }


    private Boolean addHotelRegistrationData(HotelRegistrationData hotelRegistrationData) throws HMSException{

       Boolean isAdditionSuccessful = false;
        try {
           String hotelRegistrationId = hotelCache.addHotelRegistrationData(hotelRegistrationData);
            if(hotelRegistrationId == null){
                throw new NullPointerException();
            }else{
                isAdditionSuccessful = true;
            }
        }
        catch (NullPointerException npe) {
            log.info("Exception occurred while adding registration data for hotel::"+hotelRegistrationData.getHotelId());
            throw new HMSException(HMSErrorCodes.HOTEL_ADDITION_FAILED, "Adding Hotel details failed");
        }catch(HMSException he){

        }

        return isAdditionSuccessful;
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
            hotel = hotelSelectDAO.fetchHotelByEmployeeId(employeeId);
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

        Hotel hotelFromDB = null;

        Boolean isHotelAlreadyPresent = false;
        String primaryEmail = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryEmail();
        String primaryPhone = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryPhone();
        String primaryMobileNumber = hotelFromUI.getHotelAttributes().getHotelContactDetails().getPrimaryMobileNumber();

        try{
            hotelFromDB = hotelSelectDAO.fetchHotelByContactDetails(primaryEmail,primaryPhone,primaryMobileNumber);
            if(null != hotelFromDB){
                isHotelAlreadyPresent = true;
            }
        }catch(HMSException he){

        }finally{
            return isHotelAlreadyPresent;
        }
    }

    public Boolean setupHotel(Hotel hotel,HotelRegistrationData hotelRegistrationData)throws HMSException{

        Boolean isSetupSuccessful = false;
        try {
            String hotelId = addHotel(hotel);
            hotelRegistrationData.setHotelId(hotelId);
            isSetupSuccessful = addHotelRegistrationData(hotelRegistrationData);

        }catch (NullPointerException npe) {
            log.info("Exception occurred while setting up hotel::"+hotel.getHotelAttributes().getHotelName());
            throw new HMSException(HMSErrorCodes.HOTEL_ADDITION_FAILED, "Adding Hotel details failed");
        }

        return isSetupSuccessful;


    }

}
