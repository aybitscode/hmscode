package com.aybits.hms.func.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.employee.cache.EmployeeCache;

public class EmployeeSelectDAO {

	static Logger Log = Logger.getLogger(EmployeeSelectDAO.class);
	 Connection connection = null;
     PreparedStatement stmt = null;
     ResultSet rs = null;
	
    @SuppressWarnings("finally")
    public Boolean getAllEmployees(EmployeeCache employeeCache) throws HMSRuntimeException{
       

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
        }catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage()));
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
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage()));
        }finally{
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return employees;
        }
    }


    @SuppressWarnings("finally")
    public  Employee getEmployeeByPhone(String mobilePhone) throws HMSRuntimeException{
        
        Employee employee = null;

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
        }catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage()));
        }finally{
           DBCPConnection.closeDBConnection(rs, stmt, connection);

            return employee;
        }
    }
    
    public Employee getEmployeeById(String employeeId) throws HMSRuntimeException {
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
        }catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage()));
        }finally{
           DBCPConnection.closeDBConnection(rs, stmt ,connection);
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

        String hmsHotelId = rs.getString("HMS_HOTEL_ID");
        Integer employeeStatusInt = rs.getInt("EMPLOYEE_STATUS");

        employee.setHotelId(hmsHotelId);
        //employee.setIdentificationParams()


        Status employeeStatus = Status.convertIntToStatus(employeeStatusInt);
        employee.setEmployeeStatus(employeeStatus);

        return employee;

    }

}
