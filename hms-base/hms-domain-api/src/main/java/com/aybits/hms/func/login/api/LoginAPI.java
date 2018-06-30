package com.aybits.hms.func.login.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.login.beans.LoginAttributes;
import com.aybits.hms.func.login.beans.LoginSession;
import com.aybits.hms.func.login.dao.LoginDAO;


public class LoginAPI implements HMSAPIProvider {



    @Override
    public Object process(Object object) throws HMSException {

        LoginSession loginSession = null;
        try {
            LoginAttributes loginAttributes = (LoginAttributes)object;

            if(validate(loginAttributes)){

                // TODO
                /** If Login is successful generate a new <CODE>{@link com.aybits.hms.func.login.beans.LoginSession}</CODE>
                 * for the user */
                loginSession = generateLoginSession(loginAttributes);

            }

        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_LOGIN_ATTRIBUTES,"Login details provided are invalid");
        }finally{
            return loginSession;
        }
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

    private LoginSession generateLoginSession(LoginAttributes loginAttributes){
        String loginId = loginAttributes.getLoginId();
        HotelAPI hotelAPI = new HotelAPI();
        Hotel hotel = hotelAPI.fetchHotelDetails(loginId);
        LoginSession loginSession = new LoginSession();
        loginSession.setCurrentTimeStamp(System.currentTimeMillis());
        loginSession.setExpiryTimeStamp(System.currentTimeMillis()+(30*60*1000));
        loginSession.setHotel(hotel);

        return loginSession;

    }



}
