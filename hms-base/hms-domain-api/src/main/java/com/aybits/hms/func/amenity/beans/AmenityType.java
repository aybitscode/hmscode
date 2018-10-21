package com.aybits.hms.func.amenity.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AmenityType {


    HOTEL_AMENITY(1),
    ROOM_AMENITY(2);

    @JsonProperty("facility_type")
    private final int amenityType;

    AmenityType(int amenityType){
        this.amenityType=amenityType;
    }

    public int getAmenityTypeAsInt() {
        return amenityType;
    }

    public String getAmenityTypeAsString() {
        return String.valueOf(amenityType);
    }

    public static AmenityType convertIntToAmenityType(int iAmenityType) {
        for (AmenityType amenityType : AmenityType.values()) {
            if (amenityType.getAmenityTypeAsInt() == iAmenityType) {
                return amenityType;
            }
        }
        return null;
    }

    public static AmenityType convertStringToAmenityType(String inputAmenityType) {
        for (AmenityType amenityType : AmenityType.values()) {
            if (amenityType.getAmenityTypeAsString().equals(inputAmenityType)) {
                return amenityType;
            }
        }
        return null;
    }

    public static int convertAmenityTypeToInt(AmenityType inputAmenityType) {
        for (AmenityType amenityType : AmenityType.values()) {
            if (amenityType.getAmenityTypeAsInt() == inputAmenityType.getAmenityTypeAsInt()) {
                return amenityType.getAmenityTypeAsInt();
            }
        }
        return -1;
    }

    public static String convertAmenityTypeToString(AmenityType inputAmenityType) {
        for (AmenityType amenityType : AmenityType.values()) {
            if (amenityType.getAmenityTypeAsInt() == inputAmenityType.getAmenityTypeAsInt()) {
                return amenityType.getAmenityTypeAsString();
            }
        }
        return null;
    }


}
