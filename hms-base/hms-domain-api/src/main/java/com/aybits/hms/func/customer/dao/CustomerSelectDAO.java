package com.aybits.hms.func.customer.dao;

import static java.util.Objects.requireNonNull;

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
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.customer.cache.CustomerCache;

public class CustomerSelectDAO {
	
	 static Logger Log = Logger.getLogger(CustomerDAO.class);
	    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();
	    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
	    
    @SuppressWarnings("finally")
    public Boolean getAllCustomers(CustomerCache customerCache) throws HMSRuntimeException {
        Boolean cacheLoadStatus = false;

        if (customerCache == null) {
            return cacheLoadStatus;
        }

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBCPConnection.getDBConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(CustomerDBQueries.GET_ALL_CUSTOMERS);
                stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                Customer customer = null;
                while (rs.next()) {
                    customer = populateCustomer(rs);
                    Log.info("\nPopulating customer[" + customer.getCustomerId() + "] in CustomerCache");
                    //customerCache.addCustomer(customer);
                }
            } else {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE,"No DB Connections are available"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } finally {
            DBCPConnection.getDBConnection();
            if (!customerCache.getAllCustomers().isEmpty()) {
                cacheLoadStatus = true;
            }
            return cacheLoadStatus;
        }
    }

    @SuppressWarnings("finally")
    public List<Customer> getAllCustomers() throws HMSRuntimeException {
        List<Customer> customers = new ArrayList<Customer>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(CustomerDBQueries.GET_ALL_CUSTOMERS);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            Customer customer = null;
            while (rs.next()) {
                customer = populateCustomer(rs);
                Log.info("\nPopulating customer[" + customer.getCustomerId() + "] in CustomerCache");
                customers.add(customer);
            }
        } catch (Exception e) {
            //TODO - throw cache specific errorCode,message
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return customers;
        }
    }


    @SuppressWarnings("finally")
    public Customer getCustomerByPhone(String mobilePhone) throws HMSRuntimeException {

        Customer customer = new Customer();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(CustomerDBQueries.GET_CUSTOMER_BY_PHONE);
            stmt.setString(1, mobilePhone);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            while (rs.next()) {
                customer = populateCustomer(rs);
                Log.info("\nPopulating customer[" + customer.getCustomerId() + "] in Customer Object");

            }
        } catch (Exception e) {
            //TODO - throw cache specific errorCode,message
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return customer;
        }
    }
    
    public Customer getCustomerById(String customerId) throws HMSRuntimeException{

        if (customerId == null || customerId.equals("")) {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_CUSTOMER_ID,"Invalid Customer Id provided"));
        }

        Customer customer = new Customer();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //if(connection!=null && !connection.isClosed())
            if (connection != null) {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(CustomerDBQueries.GET_CUSTOMER_BY_ID);
                stmt.setString(1, customerId);
                stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    customer = populateCustomer(rs);
                    Log.info("\nPopulating customer[" + customer.getCustomerId() + "] in Customer Object");
                }
            } else {
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE,"DB SQL Connection error occured"));
            }
        } catch (SQLException e) {
            //TODO - throw cache specific errorCode,message
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);

            return customer;
        }
    }
    private String getNextCustomerId() {

        Customer customer = null;
        String hotelIdSeq = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBCPConnection.getDBConnection();
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(CustomerDBQueries.FETCH_NEXT_CUSTOMER_ID_SEQUENCE);

            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            rs = requireNonNull(rs);

            if (rs.next() == false) {
                System.out.println("ResultSet is empty in Java");
                return null;
            } else {
                hotelIdSeq = rs.getString("NEXT_CUSTOMER_ID_VAL");
            }


            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();


        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return hotelIdSeq;
        }

    }
    private Customer populateCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getString("CUSTOMER_ID"));
        customer.setCorporateId(rs.getString("CORPORATE_ID"));
        customer.setCustomerFullName(rs.getString("CUSTOMER_FULL_NAME"));

        ContactDetails cd = new ContactDetails();
        cd.setPrimaryEmail(rs.getString("EMAIL"));
        cd.setPrimaryPhone(rs.getString("MOBILE"));
        customer.setContactDetails(cd);

        String addressJSON = rs.getString("HOME_ADDRESS");
        Address customerAddress = (Address) HMSJSONParser.convertJSONToObject(addressJSON, Address.class);
        customer.setCustomerAddress(customerAddress);

        Integer identificationParamId = rs.getInt("IDENTIFICATION_PARAM_ID");
        Integer paymentParamId = rs.getInt("PAYMENT_PARAM");
        Integer hmsHotelId = rs.getInt("HMS_HOTEL_ID");
        Integer customerStatusInt = rs.getInt("CUSTOMER_STATUS");


        Status customerStatus = Status.convertIntToStatus(customerStatusInt);
        customer.setStatus(customerStatus);


        return customer;

    }


}
