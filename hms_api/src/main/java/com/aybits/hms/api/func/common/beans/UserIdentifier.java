package com.aybits.hms.api.func.common.beans;

public class UserIdentifier {

    private Byte[] userIdentifer;
    private UserIdentifierType userIdentifierType;

    public Byte[] getUserIdentifer() {
        return userIdentifer;
    }

    public void setUserIdentifer(Byte[] userIdentifer) {
        this.userIdentifer = userIdentifer;
    }

    public UserIdentifierType getUserIdentifierType() {
        return userIdentifierType;
    }

    public void setUserIdentifierType(UserIdentifierType userIdentifierType) {
        this.userIdentifierType = userIdentifierType;
    }
}
