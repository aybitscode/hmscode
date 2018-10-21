package com.aybits.hms.func.login.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.customer.dao.CustomerDAO;
import com.aybits.hms.func.login.beans.LoginSession;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginDAO {

    static Logger Log = Logger.getLogger(CustomerDAO.class);

    private static final String GET_EMPLOYEE_BY_ID = "select password from hms_employee where login_id = ?";

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public Boolean insertLoginSession(){
            return null;
    }

    public Boolean deleteLoginSession(){
        return null;
    }

    public LoginSession fetchLoginSession(String login){

        return null;

    }

    public List<LoginSession> fetchAllLoginSessions(){
        return null;
    }

    public Boolean validateLogin(String login,String enteredPassword){

        Boolean isLoginSuccessful = false;
        String dbPassword = null;
         try{
            dbPassword = getEmployeePassword(login);
            if(null == dbPassword || "".equals(dbPassword)){
                isLoginSuccessful = false;
            }

            if(dbPassword.equals(enteredPassword)){
                isLoginSuccessful = true;
            }

         }catch(HMSException he){
             isLoginSuccessful = false;
         }finally{
             return isLoginSuccessful;
         }


    }


    private String getEmployeePassword(String loginId) throws HMSException{

        String password = null;

        if(connection == null){
            throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
        }

        Customer customer = new Customer();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBCPConnection.getDBConnection();

            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
            stmt.setString(1, loginId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            password = rs.getString("PASSWORD");


        }catch (Exception e){
            //TODO - throw cache specific errorCode,message
            throw new HMSException("");
        }finally{
           DBCPConnection.closeDBConnection(rs, stmt, connection);
            return password;
        }
    }

}
