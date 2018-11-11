package com.aybits.hms.func.customer.dao;

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
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class CustomerDAO {

    static Logger Log = Logger.getLogger(CustomerDAO.class);
    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();
    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();



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
            stmt.setString(++i, hmsRandomAPI.generatePrimaryKey(keyPrefix, keySuffix,false));
            stmt.setString(++i, customer.getCustomerFullName());
            stmt.setString(++i, customer.getContactDetails().getPrimaryEmail());
            stmt.setString(++i, customer.getContactDetails().getPrimaryPhone());
            stmt.setString(++i, customer.getCustomerAddress().toString());
            stmt.setString(++i, customer.getIdentificationParam().getIdentifierValue().toString());
            stmt.setString(++i, customer.getPaymentParams().getPaymentType().getPaymentTypeAsString());
            stmt.setString(++i, customer.getHotelId());
            stmt.setString(++i, customer.getStatus().getStatusAsString());
            stmt.setDate(++i, new Date(customer.getDateCreated().getTime()));


            int s = stmt.executeUpdate();
            connection.commit();
            if (s > 0) {
                additionStatus = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        return additionStatus;
    }

    public Boolean updateCustomer(Customer customer) throws HMSRuntimeException {
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
            stmt.setString(i++, customer.getHotelId());
            int s = stmt.executeUpdate();
            if (s > 0) {
                updateStatus = true;
                Log.info("Customer Record updated successfully");

                connection.commit();
            }

        } catch (SQLException e) {

            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));

        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        return updateStatus;
    }


    

    


}
