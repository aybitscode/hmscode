package com.aybits.hms.func.employee.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.dbman.DatabaseConstants;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.employee.beans.Employee;
import com.aybits.hms.func.employee.cache.EmployeeCache;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	static Logger Log = Logger.getLogger(EmployeeDAO.class);
	
	//added by nouman
	private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
	private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();
	
	//public void addEmployee(Employee employee){ //added by nouman
    public String addEmployee(Employee employee){
        
    	Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String employeeId = null;
        //Boolean additionStatus = false;
        int i  = 0;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);

            stmt = connection.prepareStatement(EmployeeDBQueries.ADD_EMPLOYEE);
            //added by nouman
            String keyPrefix = "HEMP";
            String columns[] = {"hotel_id","employee_id"};
            String table =  "hms_employee";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey(columns,table);
            Boolean isRandomSaltRequired = false;
            employeeId = hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix,isRandomSaltRequired);
            
            stmt.setString(++i, generateEmployeeId());
            stmt.setString(++i, employee.getEmpFullName());

            stmt.setString(++i, employee.getContactDetails().getPrimaryEmail());
            stmt.setString(++i, employee.getContactDetails().getPrimaryPhone());
            stmt.setString(++i, employee.getEmployeeAddress().toString());
            stmt.setString(++i, employee.getIdentificationParam().getIdentifierValue().toString());
            stmt.setString(++i, employee.getHotelId());
            stmt.setString(++i,employee.getEmployeeStatus().getStatusAsString());
            stmt.setDate(++i, new Date(employee.getDateCreated().getTime()));


            int s=stmt.executeUpdate();
            connection.commit();
            Log.info("\nPopulating Employee [" + employee.getEmpId() + "] in Employee Object");
          /*  if(s>0){
                additionStatus = true;
            }
*/
        } catch (SQLException e) {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        	throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        }
        finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        //return additionStatus;
        return employeeId;
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



}
