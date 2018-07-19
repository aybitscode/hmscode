package com.aybits.hms.func.facility.beans;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum FacilityType {

    HOTEL_FACILITY(1),
    ROOM_FACILITY(2);

    @JsonProperty("facility_type")
    private final int facilityType;

    FacilityType(int facilityType){
        this.facilityType=facilityType;
    }

    public int getFacilityTypeAsInt() {
        return facilityType;
    }

    public String getFacilityTypeAsString() {
        return String.valueOf(facilityType);
    }

    public static FacilityType convertIntToFacilityType(int iFacilityType) {
        for (FacilityType facilityType : FacilityType.values()) {
            if (facilityType.getFacilityTypeAsInt() == iFacilityType) {
                return facilityType;
            }
        }
        return null;
    }

    public static FacilityType convertStringToFacilityType(String inputFacilityType) {
        for (FacilityType facilityType : FacilityType.values()) {
            if (facilityType.getFacilityTypeAsString().equals(inputFacilityType)) {
                return facilityType;
            }
        }
        return null;
    }

    public static int convertFacilityTypeToInt(FacilityType inputFacilityType) {
        for (FacilityType facilityType : FacilityType.values()) {
            if (facilityType.getFacilityTypeAsInt() == inputFacilityType.getFacilityTypeAsInt()) {
                return facilityType.getFacilityTypeAsInt();
            }
        }
        return -1;
    }

    public static String convertFacilityTypeToString(FacilityType inputFacilityType) {
        for (FacilityType facilityType : FacilityType.values()) {
            if (facilityType.getFacilityTypeAsInt() == inputFacilityType.getFacilityTypeAsInt()) {
                return facilityType.getFacilityTypeAsString();
            }
        }
        return null;
    }


}
