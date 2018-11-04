package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.hotel.dao.HotelSelectDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class HotelAPIHelper {


    HotelCache hotelCache = new HotelCache();
    HotelSelectDAO hotelSelectDAO = new HotelSelectDAO();


    static Logger Log = Logger.getLogger(HotelAPIHelper.class);

    protected String addHotel(Hotel hotel) throws HMSRuntimeException {
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
                    Log.info("Exception occurred while adding hotel::"+hotel.getHotelId());
                    throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Null Pointer Exception:"+npe.getMessage()));
                }
            }

        }else{
            Log.info("Exception occurred while adding hotel::Hotel already exists");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_ALREADY_EXISTS, "Hotel already exists"));
        }

        return hotelId;
    }

    public Boolean addHotelRegistrationData(HotelRegistrationData hotelRegistrationData) throws HMSRuntimeException{

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
            Log.info("Exception occurred while adding registration data for hotel::"+hotelRegistrationData.getHotelId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_SETUP_FAILED, "Adding Hotel details failed"));
        }catch(HMSRuntimeException he){
            Log.info("Exception occurred while adding registration data for hotel::"+hotelRegistrationData.getHotelId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_SETUP_FAILED, "Adding Hotel Registration Details failed"));
        }

        return isAdditionSuccessful;
    }


    public List<Hotel> fetchAllHotels() throws HMSRuntimeException{

        List<Hotel> hotels = null;
        try {
            hotels = hotelCache.fetchAllHotels();
        }catch(HMSRuntimeException he){

        }finally{
            return hotels;
        }

    }

    public Hotel fetchHotelByHotelId(String hotelId) throws HMSRuntimeException{
        Hotel hotel = null;
        try{
            hotel = hotelCache.fetchHotelById(hotelId);
        }catch(HMSRuntimeException he){

        }finally {
            return hotel;
        }
    }


    public Hotel fetchHotelByEmployeeId(String employeeId) throws HMSRuntimeException{
        Hotel hotel = null;
        try{
            hotel = hotelSelectDAO.fetchHotelByEmployeeId(employeeId);
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Hotel Details not available for given emploeeId"));
        }finally{
            return hotel;
        }

    }

    public Boolean updateHotel(Hotel hotel) throws HMSRuntimeException {

        Boolean isHotelUpdateSuccessful = false;
        if (hotel.getHotelId() == HMSAPIConstants.TO_BE_GENERATED) try {
            isHotelUpdateSuccessful = hotelCache.updateHotel(hotel);
        } catch (HMSRuntimeException e) {
            Log.info("Exception occured while adding hotel::" + hotel.getHotelId());
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_UPDATE_FAILED, "Adding Hotel details failed"));
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
        }catch(HMSRuntimeException he){

        }finally{
            return isHotelAlreadyPresent;
        }
    }


}
