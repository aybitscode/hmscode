package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.api.HMSAPIValidator;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.api.HMSAPIResponse;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.aybits.hms.func.hotel.cache.HotelCache;
import com.aybits.hms.func.hotel.dao.HotelSelectDAO;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class HotelAPI implements HmsAPI {

    static Logger Log = Logger.getLogger(HotelAPI.class);
    HotelCache hotelCache = new HotelCache();
    HotelSelectDAO hotelSelectDAO = new HotelSelectDAO();
    HotelAPIHelper hotelAPIHelper = new HotelAPIHelper();
    HotelValidator hotelValidator = new HotelValidator();


    public String process(JSONObject data) throws HMSRuntimeException{
        Log.info("in addHotel");
        String status = null;
        String message = null;
        String hotelId = null;
        try {
            Hotel hotel = (Hotel) HMSJSONParser.convertJSONToObject(data.toString(), Hotel.class);
            hotelId = addHotel(hotel);
            status = HMSAPIServiceConstants.HMS_RESPONSE_SUCCESS;
            message = "Hotel with ["+hotelId+"] created successfully";
            Log.info(message);
        } catch (HMSRuntimeException e) {
            status = HMSAPIServiceConstants.HMS_RESPONSE_FAILURE;
            message = "Hotel creation failed:"+e.getMessage();
        }finally{
            return createHMSAPIResponse(status,message,hotelId);
        }
    }

    private String createHMSAPIResponse(String status,String message,String data){

        JSONObject json = null;
        HMSAPIResponse hmsapiResponse = new HMSAPIResponse();
        try {
            json = new JSONObject();
            json.put(HMSJSONConstants.HOTEL_ID,data);
        } catch (JSONException e) {
            status = HMSAPIServiceConstants.HMS_RESPONSE_FAILURE;
            message = "Hotel creation failed:"+e.getMessage();
        } finally{
            hmsapiResponse.setResponseData(json.toString());
            hmsapiResponse.setMessage(message);
            hmsapiResponse.setStatus(status);
            return HMSJSONParser.convertObjectToJSON(hmsapiResponse);
        }

    }

    private String addHotel(Hotel hotel) throws HMSRuntimeException {
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

    @Override
    public String init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {
        JSONObject hotelJSON = null;
        JSONObject hotelRegistrationDataJSON = null;
        try {
            hotelJSON = object.getJSONObject(HMSJSONConstants.HOTEL);
            hotelRegistrationDataJSON = object.getJSONObject(HMSJSONConstants.HOTEL_REGISTRATION_DATA);
        } catch (JSONException e) {
            Log.info("Exception occured while adding hotel::");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_SETUP_FAILED, "Adding Hotel details failed because of invalid hotel-setup-json:"+e.getMessage()));
        }

        Hotel hotel = null;
        HotelRegistrationData hotelRegistrationData = null;
        try {
            hotel = (Hotel) HMSJSONParser.convertJSONToObject(hotelJSON.toString(), Hotel.class);
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_SETUP_DATA,"Invalid Hotel setup details provided"));
        }

        try{
           hotelRegistrationData = (HotelRegistrationData) HMSJSONParser.convertJSONToObject(hotelRegistrationDataJSON.toString(), HotelRegistrationData.class);
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Hotel Registration Data provided"));
        }

        hotelValidator.validateHotel(hotel);
        hotelValidator.validateHotelRegistrationData(hotelRegistrationData);


    }



    @Override
    public String fetch(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSRuntimeException {
        return null;
    }
}
