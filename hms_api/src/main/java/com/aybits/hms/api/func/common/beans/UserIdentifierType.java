package com.aybits.hms.api.func.common.beans;

public enum UserIdentifierType {

    VOTER_ID(1),
    AADHAR_CARD(2),
    PAN_CARD(3),
    DRIVING_LICENSE(4),
    RATION_CARD(5),
    PASSPORT(6),
    BANK_STATEMENT(7);

    private int value;

    UserIdentifierType(int value){
        this.value=value;
    }



}
