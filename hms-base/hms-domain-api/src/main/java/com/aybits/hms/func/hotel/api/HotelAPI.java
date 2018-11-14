package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.api.HMSAPIResponse;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class HotelAPI implements HmsAPI {

    static Logger Log = Logger.getLogger(HotelAPI.class);

    HotelAPIHelper hotelAPIHelper = new HotelAPIHelper();
    HotelAPIValidator hotelAPIValidator = new HotelAPIValidator();


    public String process(JSONObject data) throws HMSRuntimeException{
        Log.info("[Entry]::hotelAPI.process");
        String status = null;
        String message = null;
        String hotelId = null;
        JSONObject responseData = new JSONObject();

        Hotel hotel = null;
        HotelRegistrationData hotelRegistrationData = null;
        try {
            hotel = (Hotel) HMSJSONParser.convertJSONToObject(data.getJSONObject(HMSJSONConstants.HOTEL).toString(), Hotel.class);
            hotelRegistrationData = (HotelRegistrationData) HMSJSONParser.convertJSONToObject(data.getJSONObject(HMSJSONConstants.HOTEL_REGISTRATION_DATA).toString(), HotelRegistrationData.class);

            hotelId = hotelAPIHelper.addHotel(hotel);
            responseData.put(HMSJSONConstants.HOTEL_ID,data);
            hotelRegistrationData.setHotelId(hotelId);
            hotelAPIHelper.addHotelRegistrationData(hotelRegistrationData);
            status = HMSAPIServiceConstants.HMS_RESPONSE_SUCCESS;
            message = "Hotel with ["+hotelId+"] created successfully";
            Log.info(message);
        }catch (HMSRuntimeException e) {
            throw e;
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HOTEL_SETUP_FAILED,"Hotel setup failed due to :"+e.getMessage()));
        }finally{
            Log.info("[Exit]::hotelAPI.process");
            return createHMSAPIResponse(status,message,responseData);
        }
    }




    @Override
    public String init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {

        Hotel hotel = null;
        HotelRegistrationData hotelRegistrationData = null;
        try {
            hotel = (Hotel) HMSJSONParser.convertJSONToObject(object.getJSONObject(HMSJSONConstants.HOTEL).toString(), Hotel.class);
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_SETUP_DATA,"Invalid Hotel setup details provided"));
        }

        try{
           hotelRegistrationData = (HotelRegistrationData) HMSJSONParser.convertJSONToObject(object.getJSONObject(HMSJSONConstants.HOTEL_REGISTRATION_DATA).toString(), HotelRegistrationData.class);
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Hotel Registration Data provided"));
        }

        hotelAPIValidator.validateHotel(hotel);
        hotelAPIValidator.validateHotelRegistrationData(hotelRegistrationData);

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
