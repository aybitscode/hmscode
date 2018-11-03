package com.aybits.hms.func.login.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.login.beans.LoginAttributes;
import com.aybits.hms.func.login.beans.LoginSession;
import com.aybits.hms.func.login.dao.LoginDAO;
import org.json.JSONObject;


public class LoginAPI implements HMSAPIProvider {

    @Override
    public String process(JSONObject dataJSON) throws HMSException {

        LoginSession loginSession = null;
        try {

            LoginAttributes loginAttributes = (LoginAttributes)HMSJSONParser.convertJSONToObject(dataJSON.toString(), LoginAttributes.class);
            String loginStr = (String)validate(dataJSON);
            Boolean isLoginSuccessful = Boolean.parseBoolean(loginStr);
            if(isLoginSuccessful){

                // TODO
                /** If Login is successful generate a new <CODE>{@link com.aybits.hms.func.login.beans.LoginSession}</CODE>
                 * for the user */
                loginSession = generateLoginSession(loginAttributes);
            }

        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_LOGIN_ATTRIBUTES,"Login details provided are invalid");
        }finally{
            return loginSession.toString();
        }
    }

    @Override
    public Object validate(JSONObject dataJSON){


        LoginAttributes loginAttributes = (LoginAttributes)HMSJSONParser.convertJSONToObject(dataJSON.toString(),LoginAttributes.class);

        String login = loginAttributes.getLoginId();
        String password = loginAttributes.getPassword();

        LoginDAO loginDAO = new LoginDAO();

        return loginDAO.validateLogin(login,password).toString();

    }

    @Override
    public String fetch(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSException {
        return null;
    }

    @Override
    public Object init(JSONObject object) {
        return null;
    }



    private LoginSession generateLoginSession(LoginAttributes loginAttributes){
        HotelAPI hotelAPI = new HotelAPI();
        Hotel hotel = hotelAPI.fetchHotelByEmployeeId(loginAttributes.getLoginId());
        LoginSession loginSession = new LoginSession();
        loginSession.setCurrentTimeStamp(System.currentTimeMillis());
        loginSession.setExpiryTimeStamp(System.currentTimeMillis()+(30*60*1000));
        loginSession.setHotel(hotel);

        return loginSession;

    }



}
