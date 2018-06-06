package com.aybits.hms.api.func.common.beans;

public enum IdentifierType {

    VOTER_ID(1),
    AADHAR_CARD(2),
    PAN_CARD(3),
    DRIVING_LICENSE(4),
    RATION_CARD(5),
    PASSPORT(6),
    BANK_STATEMENT(7);

    private int userIdentifierType;

    IdentifierType(int userIdentifierType){
        this.userIdentifierType = userIdentifierType;
    }

    public int getUserIdentifierTypeAsInt() {
        return userIdentifierType;
    }

    public String getUserIdentifierTypeAsString() {
        return String.valueOf(userIdentifierType);
    }

    public static IdentifierType convertIntToUserIdentifierType(int iUserIdentifierType) {
        for (IdentifierType identifierType : IdentifierType.values()) {
            if (identifierType.getUserIdentifierTypeAsInt() == iUserIdentifierType) {
                return identifierType;
            }
        }
        return null;
    }

    public static IdentifierType convertStringToUserIdentifierType(String inputUserIdentifierType) {
        for (IdentifierType identifierType : IdentifierType.values()) {
            if (identifierType.getUserIdentifierTypeAsString().equals(inputUserIdentifierType)) {
                return identifierType;
            }
        }
        return null;
    }

    public static int convertUserIdentifierTypeToInt(IdentifierType inputIdentifierType) {
        for (IdentifierType identifierType : IdentifierType.values()) {
            if (identifierType.getUserIdentifierTypeAsInt() == inputIdentifierType.getUserIdentifierTypeAsInt()) {
                return identifierType.getUserIdentifierTypeAsInt();
            }
        }
        return -1;
    }

    public static String convertUserIdentifierTypeToString(IdentifierType inputIdentifierType) {
        for (IdentifierType identifierType : IdentifierType.values()) {
            if (identifierType.getUserIdentifierTypeAsInt() == inputIdentifierType.getUserIdentifierTypeAsInt()) {
                return identifierType.getUserIdentifierTypeAsString();
            }
        }
        return null;
    }

}
