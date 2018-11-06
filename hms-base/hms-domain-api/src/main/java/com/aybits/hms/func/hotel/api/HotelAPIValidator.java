package com.aybits.hms.func.hotel.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.common.api.HMSAPIValidator;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;

public class HotelAPIValidator {

    HMSAPIValidator hmsapiValidator = new HMSAPIValidator();

    protected void validateHotel(Hotel hotel) throws HMSRuntimeException {

        if(HMSAPIValidator.isBlankString(hotel.getHotelId())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_SETUP_DATA,"Invalid Hotel ID provided or hotel-id is null"));
        }

        if(hotel.getHotelId().length() > HMSAPIConstants.HMS_ID_LENGTH){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_LENGTH_CHECK_FAILED,"Hotel ID length is more than the allowed size"));
        }

        if(null == hotel.getHotelAttributes()){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_ATTRIBUTES,"Hotel Attributes are missing from the given hotel details"));
        }

        if(HMSAPIValidator.isBlankString(hotel.getHotelAttributes().getHotelName())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_NAME,"Hotel Name is missing from the hotel details"));
        }

        hmsapiValidator.validateContactDetails(hotel.getHotelAttributes().getHotelContactDetails());
        hmsapiValidator.validateAddress(hotel.getHotelAttributes().getHotelAddress());

    }

    protected void validateHotelRegistrationData(HotelRegistrationData hotelRegistrationData) throws HMSRuntimeException{

        if(null == hotelRegistrationData){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Hotel Registration Data is missing or null"));
        }

        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getHotelId())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Hotel ID provided or hotel-id is null"));
        }

        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getBuildingPermitNo())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Building Permit ID provided or Building Permit ID is null"));
        }

        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getEsiRegistrationNo())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid ESI Registration No provided or ESI Registration No is null"));
        }
        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getFireSafetyPermitNo())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Fire Safety Permit No provided or Firesafety Permit No is null"));
        }
        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getFssaiLicenseNo())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid FSSAI License No provided or FSSAI License No is null"));
        }
        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getGstNo())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid GST No provided or GST No is null"));
        }
        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getHotelRegistrationId())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Hotel Registration ID provided or Hotel Registration ID is null"));
        }
        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getPfRegistrationNo())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid PF Registration No provided or PF Registration No is null"));
        }
        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getPoliceLicenseNo())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Police License No provided or Police License No is null"));
        }
        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getHealthTradeLicenseNo())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Health Trade License No provided or Health Trade License No is null"));
        }
        if(HMSAPIValidator.isBlankString(hotelRegistrationData.getLiquorLicenseNo())) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_REGISTRATION_DATA,"Invalid Liquor License No provided or Liquor License No is null"));
        }
    }
}


