package com.aybits.hms.func.login.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.login.beans.LoginAttributes;
import com.aybits.hms.func.login.beans.LoginSession;
import com.aybits.hms.func.login.dao.LoginDAO;
import org.json.JSONObject;


public class LoginAPI implements HmsAPI {

    @Override
    public String process(JSONObject dataJSON) throws HMSRuntimeException {

        LoginSession loginSession = null;
        try {

            LoginAttributes loginAttributes = (LoginAttributes)HMSJSONParser.convertJSONToObject(dataJSON.toString(), LoginAttributes.class);
            validate(dataJSON);
            Boolean isLoginSuccessful = false;
            if(isLoginSuccessful){

                // TODO
                /** If Login is successful generate a new <CODE>{@link com.aybits.hms.func.login.beans.LoginSession}</CODE>
                 * for the user */
                loginSession = generateLoginSession(loginAttributes);
            }

        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_LOGIN_ATTRIBUTES,"Login details provided are invalid"));
        }finally{
            return loginSession.toString();
        }
    }

    @Override
    public void validate(JSONObject dataJSON){


        LoginAttributes loginAttributes = (LoginAttributes)HMSJSONParser.convertJSONToObject(dataJSON.toString(),LoginAttributes.class);

        String login = loginAttributes.getLoginId();
        String password = loginAttributes.getPassword();

        LoginDAO loginDAO = new LoginDAO();

        loginDAO.validateLogin(login,password).toString();

    }

    @Override
    public String fetch(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public Object init(JSONObject object) {
        return null;
    }



    private LoginSession generateLoginSession(LoginAttributes loginAttributes){
        HotelAPI hotelAPI = new HotelAPI();
        Hotel hotel = null;
        LoginSession loginSession = new LoginSession();
        try {
            hotel = hotelAPI.fetchHotelByEmployeeId(loginAttributes.getLoginId());
            loginSession.setCurrentTimeStamp(System.currentTimeMillis());
            loginSession.setExpiryTimeStamp(System.currentTimeMillis()+(30*60*1000));
            loginSession.setHotel(hotel);

        }catch(HMSRuntimeException he){
            loginSession =  null;
        }
        return loginSession;

    }



}
