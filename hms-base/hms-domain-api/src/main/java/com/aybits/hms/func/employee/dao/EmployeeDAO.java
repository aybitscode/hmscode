package com.aybits.hms.func.employee.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.dbman.DatabaseConstants;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
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

    @SuppressWarnings("finally")
    public Boolean getAllEmployees(EmployeeCache employeeCache) throws HMSRuntimeException{
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Boolean cacheLoadStatus = false;

        if(employeeCache == null){
            return cacheLoadStatus;
        }


        try {
            connection = DBCPConnection.getDBConnection();
            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(EmployeeDBQueries.GET_ALL_EMPLOYEES);
                stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                Employee employee = null;
                while(rs.next()){
                    employee = populateEmployee(rs);
                    Log.info("\nPopulating employee["+employee.getEmpId()+"] in EmployeeCache");
                    //employeeCache.addEmployee(employee);
                }
            }else{
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE,"No DB Connections are available"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } finally{
           DBCPConnection.closeDBConnection(rs ,stmt, connection);
            return cacheLoadStatus;
        }
    }

    @SuppressWarnings("finally")
    public static List<Employee> getAllEmployees() throws HMSRuntimeException{


        List<Employee> employees = new ArrayList<Employee>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBCPConnection.getDBConnection();
            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(EmployeeDBQueries.GET_ALL_EMPLOYEES);
                stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                Employee employee = null;
                while(rs.next()){
                    employee = populateEmployee(rs);
                    Log.info("\nPopulating employee["+employee.getEmpId()+"] in EmployeeCache");
                    employees.add(employee);
                }
            }else{
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE,"No DB Connections are available"));
            }
        } catch (SQLException e){
            //TODO - throw cache specific errorCode,message
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        }finally{
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return employees;
        }
    }


    @SuppressWarnings("finally")
    public static Employee getEmployeeByPhone(String mobilePhone) throws HMSRuntimeException{
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Employee employee = new Employee();

        try {
            connection = DBCPConnection.getDBConnection();
            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(EmployeeDBQueries.GET_EMPLOYEE_BY_PHONE);
                stmt.setString(1,mobilePhone);
                stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                while(rs.next()){
                    employee = populateEmployee(rs);
                    Log.info("\nPopulating employee["+employee.getEmpId()+"] in Employee Object");
                }
            }else{
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE,"No DB Connections are available"));
            }
        }catch (SQLException e){
            //TODO - throw cache specific errorCode,message
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        }finally{
           DBCPConnection.closeDBConnection(rs, stmt, connection);

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
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean additionStatus = false;
        int i  = 0;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);

            stmt = connection.prepareStatement(EmployeeDBQueries.ADD_EMPLOYEE);
            stmt.setString(++i, generateEmployeeId());
            stmt.setString(++i, employee.getEmpFullName());

            stmt.setString(++i, employee.getContactDetails().getPrimaryEmail());
            stmt.setString(++i, employee.getContactDetails().getPrimaryPhone());
            stmt.setString(++i, employee.getEmployeeAddress().toString());
            stmt.setString(++i, employee.getIdentificationParam().getIdentifierValue().toString());
            stmt.setInt(++i, employee.getHotelId());
            stmt.setString(++i,employee.getEmployeeStatus().getStatusAsString());
            stmt.setDate(++i, new Date(employee.getDateCreated().getTime()));


            int s=stmt.executeUpdate();
            connection.commit();
            if(s>0){
                additionStatus = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        return additionStatus;
    }

    public Boolean updateEmployee(Employee employee) throws HMSRuntimeException
    {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean updateStatus = false;
        int i  = 1;
        try {

            stmt = connection.prepareStatement(EmployeeDBQueries.UPDATE_EMPLOYEE);

            stmt.setString(i++, employee.getEmpFullName());
            stmt.setString(i++, employee.getEmployeeAddress().getCity());
            stmt.setString(i++, employee.getContactDetails().getPrimaryEmail());
            stmt.setString(i++, employee.getContactDetails().getPrimaryPhone());
            stmt.setString(i++, employee.getEmployeeAddress().toString());
            stmt.setString(i++, employee.getEmpId());
            int s=stmt.executeUpdate();
            if(s>0){
                updateStatus = true;
                Log.info("Employee Record updated successfully");

                connection.commit();
            }

        } catch (SQLException e) {

            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));

        }
        finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        return updateStatus;
    }



    public String generateEmployeeId(){

        String prefix = "C";
        String serialNo = "0";

        int count = 0;

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPConnection.getDBConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DatabaseConstants.COUNT_EMPLOYEES);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }

        count += 1;

        serialNo = String.valueOf(count);

        serialNo = String.format("%3s", serialNo).replace(' ', '0');

        return prefix+serialNo;
    }

    public static Employee getEmployeeById(String employeeId) {
        if(employeeId == null || employeeId.equals("")){
            try {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_EMPLOYEE_ID,"Invalid Employee ID provided"));
            } catch (HMSRuntimeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                return null;
            }

        }

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Employee employee = new Employee();

        try {
            connection = DBCPConnection.getDBConnection();
            if(connection != null)
            {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(EmployeeDBQueries.GET_EMPLOYEE_BY_ID);
                stmt.setString(1,employeeId);
                stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                while(rs.next()){
                    employee = populateEmployee(rs);
                    Log.info("\nPopulating employee["+employee.getEmpId()+"] in Employee Object");
                }
            }else{
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE,"No DB Connections are available"));
            }
        } catch (SQLException e){
            //TODO - throw cache specific errorCode,message
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        }finally{
           DBCPConnection.closeDBConnection(rs, stmt ,connection);
            return employee;
        }
    }


}
