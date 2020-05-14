package com.aybits.hms.func.employee.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.dbman.DatabaseConstants;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.employee.cache.EmployeeCache;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    static Logger Log = Logger.getLogger(EmployeeDAO.class);

    private static Connection connection = DBConnection.getDBConnection();


    @SuppressWarnings("finally")
    public Boolean getAllEmployees(EmployeeCache employeeCache) throws HMSException{
        if(connection == null){
            throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
        }
        Boolean cacheLoadStatus = false;

        if(employeeCache == null){
            return cacheLoadStatus;
        }

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //if(connection!=null && !connection.isClosed())
            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(EmployeeDBQueries.GET_ALL_EMPLOYEES);
                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                Employee employee = null;
                while(rs.next()){
                    employee = populateEmployee(rs);
                    Log.info("\nPopulating employee["+employee.getEmpId()+"] in EmployeeCache");
                    //employeeCache.addEmployee(employee);
                }
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
                //DBConnection.closeDBConnection();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           /* if(!employeeCache.getAllEmployees().isEmpty()){
                cacheLoadStatus = true;
            }*/
            return cacheLoadStatus;
        }
    }

    @SuppressWarnings("finally")
    public static List<Employee> getAllEmployees() throws HMSException{
        if(connection == null){
            throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
        }



        List<Employee> employees = new ArrayList<Employee>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //if(connection!=null && !connection.isClosed())
            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(EmployeeDBQueries.GET_ALL_EMPLOYEES);
                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                Employee employee = null;
                while(rs.next()){
                    employee = populateEmployee(rs);
                    Log.info("\nPopulating employee["+employee.getEmpId()+"] in EmployeeCache");
                    employees.add(employee);
                }
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
                //DBConnection.closeDBConnection();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return employees;
        }
    }


    @SuppressWarnings("finally")
    public static Employee getEmployeeByPhone(String mobilePhone) throws HMSException{
        if(connection == null){
            throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
        }

        Employee employee = new Employee();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //if(connection!=null && !connection.isClosed())
            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(EmployeeDBQueries.GET_EMPLOYEE_BY_PHONE);
                stmt.setString(1,mobilePhone);
                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                while(rs.next()){
                    employee = populateEmployee(rs);
                    Log.info("\nPopulating employee["+employee.getEmpId()+"] in Employee Object");
                }
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
                //DBConnection.closeDBConnection();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return employee;
        }
    }


    private static Employee populateEmployee(ResultSet rs) throws SQLException{
        Employee employee = new Employee();
        employee.setEmpId(rs.getString("EMPLOYEE_ID"));
        employee.setEmpFullName(rs.getString("EMPLOYEE_FULL_NAME"));

        ContactDetails cd = new ContactDetails();
        cd.setPrimaryEmail(rs.getString("EMAIL"));
        cd.setPrimaryPhone(rs.getString("MOBILE"));
        employee.setContactDetails(cd);

        String addressJSON = rs.getString("HOME_ADDRESS");
        Address employeeAddress = (Address)HMSJSONParser.convertJSONToObject(addressJSON,Address.class);
        employee.setEmployeeAddress(employeeAddress);

        Integer identificationParamId = rs.getInt("IDENTIFICATION_PARAM_ID");

        Integer hmsHotelId = rs.getInt("HMS_HOTEL_ID");
        Integer employeeStatusInt = rs.getInt("EMPLOYEE_STATUS");

        employee.setHotelId(hmsHotelId);
        //employee.setIdentificationParams()


        Status employeeStatus = Status.convertIntToStatus(employeeStatusInt);
        employee.setEmployeeStatus(employeeStatus);




        return employee;

    }

    public Boolean addEmployee(Employee employee){
        PreparedStatement pst;
        Boolean additionStatus = false;
        int i  = 0;
        try {

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(EmployeeDBQueries.ADD_EMPLOYEE);
            pst.setString(++i, generateEmployeeId());
            pst.setString(++i, employee.getEmpFullName());

            pst.setString(++i, employee.getContactDetails().getPrimaryEmail());
            pst.setString(++i, employee.getContactDetails().getPrimaryPhone());
            pst.setString(++i, employee.getEmployeeAddress().toString());
            pst.setString(++i, employee.getIdentificationParam().getIdentifierValue().toString());
            pst.setInt(++i, employee.getHotelId());
            pst.setString(++i,employee.getEmployeeStatus().getStatusAsString());
            pst.setDate(++i, new java.sql.Date(employee.getDateCreated().getTime()));


            int s=pst.executeUpdate();
            connection.commit();
            if(s>0){
                additionStatus = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return additionStatus;
    }

    public Boolean updateEmployee(Employee employee) throws HMSException
    {
        PreparedStatement pst;
        Boolean updateStatus = false;
        int i  = 1;
        try {

            pst = connection.prepareStatement(EmployeeDBQueries.UPDATE_EMPLOYEE);

            pst.setString(i++, employee.getEmpFullName());
            pst.setString(i++, employee.getEmployeeAddress().getCity());
            pst.setString(i++, employee.getContactDetails().getPrimaryEmail());
            pst.setString(i++, employee.getContactDetails().getPrimaryPhone());
            pst.setString(i++, employee.getEmployeeAddress().toString());
            pst.setString(i++, employee.getEmpId());
            int s=pst.executeUpdate();
            if(s>0){
                updateStatus = true;
                Log.info("Employee Record updated successfully");

                connection.commit();
            }

        } catch (SQLException e) {

            throw new HMSException("Employee Update Operation failed");

        }
        return updateStatus;
    }



    public String generateEmployeeId(){

        String prefix = "C";
        String serialNo = "0";

        int count = 0;

        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(DatabaseConstants.COUNT_EMPLOYEES);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        count += 1;

        serialNo = String.valueOf(count);

        serialNo = String.format("%3s", serialNo).replace(' ', '0');

        return prefix+serialNo;
    }

    public static Employee getEmployeeById(String employeeId) {
        if(connection == null){
            try {
                throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
            } catch (HMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                return null;
            }
        }

        if(employeeId == null || employeeId.equals("")){
            try {
                throw new HMSException(HMSErrorCodes.INVALID_EMPLOYEE_ID);
            } catch (HMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                return null;
            }

        }

        Employee employee = new Employee();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //if(connection!=null && !connection.isClosed())
            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(EmployeeDBQueries.GET_EMPLOYEE_BY_ID);
                stmt.setString(1,employeeId);
                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                while(rs.next()){
                    employee = populateEmployee(rs);
                    Log.info("\nPopulating employee["+employee.getEmpId()+"] in Employee Object");
                }
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
                //DBConnection.closeDBConnection();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return employee;
        }
    }


}
