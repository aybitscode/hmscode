package com.aybits.hms.func.features.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.common.api.HMSAPIValidator;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.features.beans.Feature;
import com.aybits.hms.func.service.beans.Service;

import java.util.List;

public class FeatureAPIValidator {



    public void validateFeature(Feature feature) throws HMSRuntimeException{

        if(HMSAPIValidator.isBlankString(feature.getHotelId())){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_HOTEL_SETUP_DATA,"Invalid Hotel ID provided or hotel-id is null"));
        }

        List<Facility> facilities = feature.getFacilityList();
        validateFacilities(facilities);

        List<Amenity> amenities = feature.getAmenityList();
        validateAmenities(amenities);

        List<Service> services = feature.getServiceList();
        validateServices(services);

    }




    private void validateFacilities(List<Facility> facilities) throws HMSRuntimeException {

        if(null == facilities || facilities.isEmpty()){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FACILIY_DETAILS,"No Facility Details are provided for Hotel"));
        }

        for(Facility facility:facilities) {
            if (HMSAPIValidator.isBlankString(facility.getFacilityId())) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FACILITY_ID, "Invalid Facility ID provided or facility-id is null"));
            }

            if (HMSAPIValidator.isBlankString(facility.getFacilityName())) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FACILITY_NAME, "Invalid Facility Name provided or facility name is null"));
            }

            if (facility.getFacilityId().length() > HMSAPIConstants.HMS_ID_LENGTH) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_LENGTH_CHECK_FAILED, "Hotel ID length is more than the allowed size"));
            }

            if (facility.getFacilityCharges() < 0) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FACILITY_CHARGES, "Facility Charges for facility[" + facility.getFacilityName() + "] should not be less than zero"));
            }

            if (null == facility.isFacilityAvailable()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FACILITY_AVAILABILE_VALUE, "Invalid Facility Availability Indicator provided for facility[" + facility.getFacilityName() + "]"));
            }

            if (null == facility.getFacilityType()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FACILITY_TYPE, "Invalid Facility Type for facility[" + facility.getFacilityName() + "]"));
            }

            if (null == facility.isChargeable()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_FACILITY_AVAILABILE_VALUE, "Invalid Facility Availability Indicator provided for facility[" + facility.getFacilityName() + "]"));
            }


        }

    }


    private void validateAmenities(List<Amenity> amenities) throws HMSRuntimeException {

        if(null == amenities || amenities.isEmpty()){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_DETAILS,"No Amenity Details are provided for Hotel"));
        }

        for(Amenity amenity:amenities) {
            if (HMSAPIValidator.isBlankString(amenity.getAmenityId())) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_ID, "Invalid Amenity ID provided or Amenity-id is null"));
            }

            if (HMSAPIValidator.isBlankString(amenity.getAmenityName())) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_NAME, "Invalid Amenity Name provided or Amenity name is null"));
            }

            if (amenity.getAmenityId().length() > HMSAPIConstants.HMS_ID_LENGTH) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_LENGTH_CHECK_FAILED, "Hotel ID length is more than the allowed size"));
            }

            if (amenity.getAmenityCharges() < 0) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_CHARGES, "Amenity Charges for Amenity[" + amenity.getAmenityName() + "] should not be less than zero"));
            }

            if (null == amenity.isAvailable()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_AVAILABILE_VALUE, "Invalid Amenity Availability Indicator provided for Amenity[" + amenity.getAmenityName() + "]"));
            }

            if (null == amenity.getAmenityType()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_TYPE, "Invalid Amenity Type for Amenity[" + amenity.getAmenityName() + "]"));
            }

            if (null == amenity.isChargeable()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_AVAILABILE_VALUE, "Invalid Amenity Availability Indicator provided for Amenity[" + amenity.getAmenityName() + "]"));
            }

        }
    }

    private void validateServices(List<Service> services) throws HMSRuntimeException{


        if(null == services || services.isEmpty()){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_DETAILS,"No Service Details are provided for Hotel"));
        }

        for(Service service:services) {
            if (HMSAPIValidator.isBlankString(service.getServiceId())) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_ID, "Invalid Service ID provided or Service-id is null"));
            }

            if (HMSAPIValidator.isBlankString(service.getServiceName())) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_NAME, "Invalid Service Name provided or Service name is null"));
            }

            if (service.getServiceId().length() > HMSAPIConstants.HMS_ID_LENGTH) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_LENGTH_CHECK_FAILED, "Service ID length is more than the allowed size"));
            }

            if (service.getServiceCharges() < 0) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_CHARGES, "Service Charges for Service[" + service.getServiceName() + "] should not be less than zero"));
            }

            if (null == service.isAvailable()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_AVAILABILE_VALUE, "Invalid Service Availability Indicator provided for Service[" + service.getServiceName() + "]"));
            }

            if (null == service.getServiceType()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_TYPE, "Invalid Service Type for Service[" + service.getServiceName() + "]"));
            }

            if (null == service.isChargeable()) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_AMENITY_AVAILABILE_VALUE, "Invalid Service Availability Indicator provided for Service[" + service.getServiceName() + "]"));
            }

        }

    }


}
