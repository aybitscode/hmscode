package com.aybits.hms.api.func.login.services;

import com.aybits.hms.api.arch.exception.HMSErrorCodes;
import com.aybits.hms.api.arch.exception.HMSException;
import com.aybits.hms.api.func.common.beans.HMSServiceAction;
import com.aybits.hms.api.func.common.beans.HMSServiceModule;
import com.aybits.hms.api.func.common.services.HMSService;
import com.aybits.hms.api.func.login.beans.LoginAttributes;
import com.aybits.hms.api.func.login.dao.LoginDAO;

public class LoginService implements HMSService {

    @Override
    public Object process(Object object, HMSServiceModule hmsServiceModule, HMSServiceAction hmsServiceAction) throws HMSException {

        try {
            LoginAttributes loginAttributes = (LoginAttributes) object;
            String login = loginAttributes.getLoginId();
            String password = loginAttributes.getPassword();

            return validateLogin(login,password);

        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_LOGIN_ATTRIBUTES,"Login details provided are invalid");
        }
    }



    private Boolean validateLogin(String login,String password){

        LoginDAO loginDAO = new LoginDAO();

        return loginDAO.validateLogin(login,password);

    }

    @Override
    public Object init(Object object, HMSServiceModule hmsServiceModule, HMSServiceAction hmsServiceAction) {
        return null;
    }
}
