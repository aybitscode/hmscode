package com.aybits.hms.func.customer.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.customer.cache.CustomerCache;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class CustomerDAO {

    static Logger Log = Logger.getLogger(CustomerDAO.class);
    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();
    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();


    @SuppressWarnings("finally")
    public Boolean getAllCustomers(CustomerCache customerCache) throws HMSException {
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
                throw new HMSException(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (HMSException e) {
            //TODO - throw cache specific errorCode,message
            throw new HMSException("");
        } finally {
            DBCPConnection.getDBConnection();
            if (!customerCache.getAllCustomers().isEmpty()) {
                cacheLoadStatus = true;
            }
            return cacheLoadStatus;
        }
    }

    @SuppressWarnings("finally")
    public List<Customer> getAllCustomers() throws HMSException {
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
            throw new HMSException("");
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return customers;
        }
    }


    @SuppressWarnings("finally")
    public Customer getCustomerByPhone(String mobilePhone) throws HMSException {

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
            throw new HMSException("");
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return customer;
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

    public Boolean addCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean additionStatus = false;
        int i = 0;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);

            stmt = connection.prepareStatement(CustomerDBQueries.ADD_CUSTOMER);
            String keyPrefix = "CUST";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey("HOTEL_ID", "HMS_HOTEL");
            stmt.setString(++i, hmsRandomAPI.generatePrimaryKey(keyPrefix, keySuffix));
            stmt.setString(++i, customer.getCustomerFullName());
            stmt.setString(++i, customer.getContactDetails().getPrimaryEmail());
            stmt.setString(++i, customer.getContactDetails().getPrimaryPhone());
            stmt.setString(++i, customer.getCustomerAddress().toString());
            stmt.setString(++i, customer.getIdentificationParam().getIdentifierValue().toString());
            stmt.setString(++i, customer.getPaymentParams().getPaymentType().getPaymentTypeAsString());
            stmt.setInt(++i, customer.getHotelId());
            stmt.setString(++i, customer.getStatus().getStatusAsString());
            stmt.setDate(++i, new Date(customer.getDateCreated().getTime()));


            int s = stmt.executeUpdate();
            connection.commit();
            if (s > 0) {
                additionStatus = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        return additionStatus;
    }

    public Boolean updateCustomer(Customer customer) throws HMSException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean updateStatus = false;
        int i = 1;
        try {
            connection = DBCPConnection.getDBConnection();
            stmt = connection.prepareStatement(CustomerDBQueries.UPDATE_CUSTOMER);

            stmt.setString(i++, customer.getCustomerFullName());
            stmt.setString(i++, customer.getCustomerAddress().getCity());
            stmt.setString(i++, customer.getContactDetails().getPrimaryEmail());
            stmt.setString(i++, customer.getContactDetails().getPrimaryPhone());
            stmt.setString(i++, customer.getCustomerAddress().toString());
            stmt.setString(i++, customer.getCustomerId());
            stmt.setInt(i++, customer.getHotelId());
            int s = stmt.executeUpdate();
            if (s > 0) {
                updateStatus = true;
                Log.info("Customer Record updated successfully");

                connection.commit();
            }

        } catch (SQLException e) {

            throw new HMSException("Customer Update Operation failed");

        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        return updateStatus;
    }


    public Customer getCustomerById(String customerId) {

        if (customerId == null || customerId.equals("")) {
            try {
                throw new HMSException(HMSErrorCodes.INVALID_CUSTOMER_ID);
            } catch (HMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                return null;
            }

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
                throw new HMSException(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE);
            }
        } catch (Exception e) {
            //TODO - throw cache specific errorCode,message
            e.printStackTrace();
            throw new HMSException("");
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
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return hotelIdSeq;
        }

    }


}
