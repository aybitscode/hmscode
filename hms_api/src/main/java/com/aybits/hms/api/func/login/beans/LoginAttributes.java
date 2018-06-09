package com.aybits.hms.api.func.login.beans;

<<<<<<< HEAD
public class LoginAttributes {

    private String login;
    private String password;
    private String passwordType;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
=======

import com.fasterxml.jackson.annotation.JsonProperty;

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
>>>>>>> adil_develop
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

<<<<<<< HEAD
    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
=======
    public PasswordType getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(PasswordType passwordType) {
>>>>>>> adil_develop
        this.passwordType = passwordType;
    }
}
