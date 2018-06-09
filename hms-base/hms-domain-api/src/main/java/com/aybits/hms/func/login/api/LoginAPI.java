package com.aybits.hms.func.login.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.login.beans.LoginAttributes;
import com.aybits.hms.func.login.beans.LoginSession;
import com.aybits.hms.func.login.dao.LoginDAO;

public class LoginAPI implements HMSAPIProvider {

    @Override
    public Object process(Object object) throws HMSException {

        try {
            LoginAttributes loginAttributes = (LoginAttributes)object;

            Integer sessionTimeoutInMinutes = 60;

            if(validate(loginAttributes)){
               LoginSession loginSession = new LoginSession();
               loginSession.setCurrentTimeInMillis(System.currentTimeMillis());
               loginSession.setExpiryTime(System.currentTimeMillis()+ (sessionTimeoutInMinutes*60*1000));
            }else{
                throw new HMSException(HMSErrorCodes.INVALID_LOGIN_ATTRIBUTES,"Login details provided are invalid");
            }

        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_LOGIN_ATTRIBUTES,"Login details provided are invalid");
        }

        return null;
    }


    @Override
    public Boolean validate(Object object){

        LoginAttributes loginAttributes = (LoginAttributes)object;

        String login = loginAttributes.getLoginId();
        String password = loginAttributes.getPassword();

        LoginDAO loginDAO = new LoginDAO();

        return loginDAO.validateLogin(login,password);

    }

    @Override
    public Object init(Object object) {
        return null;
    }
}
