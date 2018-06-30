package com.aybits.hms.func.login.dao;

import com.aybits.hms.arch.dbman.DBConnection;
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

    private static Connection connection = DBConnection.getDBConnection();
    private static final String GET_EMPLOYEE_BY_ID = "select password from hms_employee where login_id = ?";

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

            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
                stmt.setString(1,loginId);
                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                password = rs.getString("PASSWORD");

            }else{
                throw new HMSException(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (HMSException e){
            //TODO - throw cache specific errorCode,message
            throw new HMSException("");
        }finally{
            try {
                if(rs != null)
                    rs.close();
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return password;
        }
    }

}
