package com.hms.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hms.cache.CustomerCache;
import com.hms.exception.HMSErrorCodes;
import com.hms.exception.HMSException;
import com.hms.model.Customer;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;

public class HMSDbOperations {
	
	
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
				stmt = connection.prepareStatement(HMSDBQueries.GET_ALL_CUSTOMERS);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				Customer customer = null;
				while(rs.next()){
					customer = populateCustomer(rs);
					System.out.println("\nPopulating customer["+customer.getCustomerID()+"] in CustomerCache");
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
				stmt = connection.prepareStatement(HMSDBQueries.GET_ALL_CUSTOMERS);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				Customer customer = null;
				while(rs.next()){
					customer = populateCustomer(rs);
					System.out.println("\nPopulating customer["+customer.getCustomerID()+"] in CustomerCache");
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
				stmt = connection.prepareStatement(HMSDBQueries.GET_CUSTOMER_BY_PHONE);
				stmt.setString(1,mobilePhone);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				while(rs.next()){
					customer = populateCustomer(rs);
					System.out.println("\nPopulating customer["+customer.getCustomerID()+"] in Customer Object");
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
	
	
	private static Customer populateCustomer(ResultSet rs) throws HMSException,SQLException{
		Customer customer = new Customer(
				rs.getString("CUSTOMERID"),
				rs.getString("FIRSTNAME"),
				rs.getString("LASTNAME"),
				rs.getString("GENDER"),
				rs.getString("CITY"),
				rs.getString("EMAIL"),
				rs.getString("MOBILE"),
				rs.getString("ADDRS")
		);
		return customer;
		
	}
	
	public Boolean addCustomer(Customer customer){
		PreparedStatement pst;
		Boolean additionStatus = false;
		int i  = 0;
		try {
			
			pst = connection.prepareStatement(HMSDBQueries.ADD_CUSTOMER);
			pst.setString(++i, generateCustomerId());
			pst.setString(++i, customer.getFirst_name());
			pst.setString(++i, customer.getLast_name());
			pst.setString(++i, customer.getGender());
			pst.setString(++i, customer.getCity());
			pst.setString(++i, customer.getEmail());
			pst.setString(++i, customer.getPhone_number());			
			pst.setString(++i, customer.getAddress());
			
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
			
			pst = connection.prepareStatement(HMSDBQueries.UPDATE_CUSTOMER);

			pst.setString(i++, customer.getFirst_name());
			pst.setString(i++, customer.getLast_name());
			pst.setString(i++, customer.getCity());
			pst.setString(i++, customer.getEmail());
			pst.setString(i++, customer.getPhone_number());			
			pst.setString(i++, customer.getAddress());
			pst.setString(i++, customer.getCustomerID());
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
				stmt = connection.prepareStatement(HMSDBQueries.GET_CUSTOMER_BY_ID);
				stmt.setString(1,customerId);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				while(rs.next()){
					customer = populateCustomer(rs);
					System.out.println("\nPopulating customer["+customer.getCustomerID()+"] in Customer Object");
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
