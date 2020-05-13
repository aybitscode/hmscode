package com.aybits.hms.func.login.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("login_attributes")
public class LoginAttributes {


    @JsonProperty("login_id")
    private String loginId;
    @JsonProperty("password")
    private String password;
    @JsonProperty("password_type")
    private PasswordType passwordType;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PasswordType  getPasswordType() {
        return passwordType;
    }


    public void setPasswordType(PasswordType passwordType) {
        this.passwordType = passwordType;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
