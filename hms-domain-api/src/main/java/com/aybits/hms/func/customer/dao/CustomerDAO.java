package com.aybits.hms.func.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aybits.hms.api.arch.util.HMSJSONParser;
import com.aybits.hms.api.func.common.beans.ContactDetails;
import com.aybits.hms.api.func.common.beans.HMSAddress;
import com.aybits.hms.api.func.customer.beans.CustomerStatus;
import com.aybits.hms.api.func.customer.cache.CustomerCache;
import com.aybits.hms.api.arch.exception.HMSErrorCodes;
import com.aybits.hms.api.arch.exception.HMSException;
import com.aybits.hms.api.func.customer.beans.Customer;
import com.aybits.hms.api.arch.dbman.DBConnection;
import com.aybits.hms.api.arch.dbman.DatabaseConstants;
import com.aybits.hms.func.customer.beans.Customer;
import com.aybits.hms.func.customer.beans.CustomerStatus;
import com.aybits.hms.func.customer.cache.CustomerCache;

public class CustomerDAO {
	


	private static Connection connection = DBConnection.getDBConnection();
	
	
	@SuppressWarnings("finally")
	public Boolean getAllCustomers(CustomerCache customerCache) throws HMSException{
		if(connection == null){
			throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
		}
		Boolean cacheLoadStatus = false;
		
		if(customerCache == null){
			return cacheLoadStatus;
		}
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//if(connection!=null && !connection.isClosed())
			if(connection != null)
			{
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(CustomerDBQueries.GET_ALL_CUSTOMERS);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				Customer customer = null;
				while(rs.next()){
					customer = populateCustomer(rs);
					System.out.println("\nPopulating customer["+customer.getCustomerId()+"] in CustomerCache");
					customerCache.addCustomer(customer);
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
			if(!customerCache.getAllCustomers().isEmpty()){
				cacheLoadStatus = true;
			}
			return cacheLoadStatus;
		}
	}		
	
	@SuppressWarnings("finally")
	public static List<Customer> getAllCustomers() throws HMSException{
		if(connection == null){
			throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
		}
		
		
		
		List<Customer> customers = new ArrayList<Customer>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//if(connection!=null && !connection.isClosed())
			if(connection != null)
			{
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(CustomerDBQueries.GET_ALL_CUSTOMERS);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				Customer customer = null;
				while(rs.next()){
					customer = populateCustomer(rs);
					System.out.println("\nPopulating customer["+customer.getCustomerId()+"] in CustomerCache");
					customers.add(customer);
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
			
			return customers;
		}
	}	
	
	
	@SuppressWarnings("finally")
	public static Customer getCustomerByPhone(String mobilePhone) throws HMSException{
		if(connection == null){
			throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
		}
		
		Customer customer = new Customer();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//if(connection!=null && !connection.isClosed())
			if(connection != null)
			{
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(CustomerDBQueries.GET_CUSTOMER_BY_PHONE);
				stmt.setString(1,mobilePhone);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				while(rs.next()){
					customer = populateCustomer(rs);
					System.out.println("\nPopulating customer["+customer.getCustomerId()+"] in Customer Object");
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
			
			return customer;
		}
	}	
	
	
	private static Customer populateCustomer(ResultSet rs) throws SQLException{
		        Customer customer = new Customer();
				customer.setCustomerId(rs.getString("CUSTOMER_ID"));
				customer.setCorporateId(rs.getString("CORPORATE_ID"));
				customer.setFirstName(rs.getString("FIRST_NAME"));
				customer.setLastName(rs.getString("LAST_NAME"));
				customer.setMiddleName(rs.getString("MIDDLE_NAME"));

				ContactDetails cd = new ContactDetails();
				cd.setPrimaryEmail(rs.getString("EMAIL"));
				cd.setPrimaryPhone(rs.getString("MOBILE"));
				customer.setContactDetails(cd);

		        String addressJSON = rs.getString("HOME_ADDRESS");
                HMSAddress customerAddress = (HMSAddress)HMSJSONParser.convertJSONToObject(addressJSON,HMSAddress.class);
                customer.setCustomerAddress(customerAddress);

                Integer identificationParamId = rs.getInt("IDENTIFICATION_PARAM_ID");
                Integer paymentParamId = rs.getInt("PAYMENT_PARAM");
                Integer hmsHotelId = rs.getInt("HMS_HOTEL_ID");
                Integer customerStatusInt = rs.getInt("CUSTOMER_STATUS");



                CustomerStatus customerStatus = CustomerStatus.convertIntToCustomerStatus(customerStatusInt);
                customer.setCustomerStatus(customerStatus);




		return customer;
		
	}
	
	public Boolean addCustomer(Customer customer){
		PreparedStatement pst;
		Boolean additionStatus = false;
		int i  = 0;
		try {
			
			pst = connection.prepareStatement(CustomerDBQueries.ADD_CUSTOMER);
			pst.setString(++i, generateCustomerId());
			pst.setString(++i, customer.getFirstName());
			pst.setString(++i, customer.getLastName());

			int s=pst.executeUpdate();
			 connection.commit();
			if(s>0){
				additionStatus = true;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		return additionStatus;
	}
	
	public Boolean updateCustomer(Customer customer) throws HMSException
	{
		PreparedStatement pst;
		Boolean updateStatus = false;
		int i  = 1;
		try {
			
			pst = connection.prepareStatement(CustomerDBQueries.UPDATE_CUSTOMER);

			pst.setString(i++, customer.getFirstName());
			pst.setString(i++, customer.getLastName());
			pst.setString(i++, customer.getCustomerAddress().getCity());
			pst.setString(i++, customer.getContactDetails().getPrimaryEmail());
			pst.setString(i++, customer.getContactDetails().getPrimaryPhone());
			pst.setString(i++, customer.getCustomerAddress().toString());
			pst.setString(i++, customer.getCustomerId());
			int s=pst.executeUpdate();
			if(s>0){
				updateStatus = true;
				System.out.println("Customer Record updated successfully");

				connection.commit();
			}
				
		} catch (SQLException e) {
			
			throw new HMSException("Customer Update Operation failed");
			
		} 
		return updateStatus;
	}	
	

	
	public String generateCustomerId(){

		String prefix = "C";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = connection.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_CUSTOMERS);
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

	public static Customer getCustomerById(String customerId) {
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
		
		if(customerId == null || customerId.equals("")){
			try {
				throw new HMSException(HMSErrorCodes.INVALID_CUSTOMER_ID);
			} catch (HMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				return null;
			}
			
		}
		
		Customer customer = new Customer();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//if(connection!=null && !connection.isClosed())
			if(connection != null)
			{
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(CustomerDBQueries.GET_CUSTOMER_BY_ID);
				stmt.setString(1,customerId);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				while(rs.next()){
					customer = populateCustomer(rs);
					System.out.println("\nPopulating customer["+customer.getCustomerId()+"] in Customer Object");
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
			
			return customer;
		}
	}
	

}
