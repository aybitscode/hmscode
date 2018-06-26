package com.aybits.hms.func.login.beans;

public enum PasswordType {

        PASSWORD(1),
        OTP(2),
        QNA(3);


        private int passwordType;

        PasswordType(int passwordType){
                this.passwordType=passwordType;
        }

}
