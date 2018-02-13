package com.hms.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.hms.cache.ExpensesCache;
import com.hms.exception.HMSErrorCodes;
import com.hms.exception.HMSException;
import com.hms.model.Expenses;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;

public class ExpenseDbOperations {
	
	
	private static Connection connection = DBConnection.getDBConnection();
	
	
	@SuppressWarnings("finally")
	public Boolean getAllExpenses(ExpensesCache objCache) throws HMSException{
		if(connection == null){
			throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
		}
		Boolean cacheLoadStatus = false;
		
		if(objCache == null){
			return cacheLoadStatus;
		}
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//if(connection!=null && !connection.isClosed())
			if(connection != null)
			{
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(DatabaseConstants.GET_ALL_EXPENSES);
				stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
				rs = stmt.executeQuery();
				Expenses objExpense = null;
				while(rs.next()){
					objExpense = populateExpenses(rs);
					System.out.println("\nPopulating Expenses["+objExpense.getRequestId()+"] in ExpeseCache");
					objCache.addExpense(objExpense);
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
			if(!objCache.getAllExpenses().isEmpty()){
				cacheLoadStatus = true;
			}
			return cacheLoadStatus;
		}
	}		
	
	
	private Expenses populateExpenses(ResultSet rs) throws HMSException,SQLException{
		Expenses objExpense = new Expenses(
				rs.getString("REQUESTID"),
				rs.getString("DEPARTMENT"),
				rs.getString("PAIDTO"),
				rs.getString("DESCRIPTION"),
				rs.getString("AMOUNT"),
				rs.getTimestamp("CREATEDDATE"),
				rs.getString("EMPLOYEEID")
		);
		return objExpense;
		
	}
	
	public Boolean addExpense(Expenses objExpense){
		PreparedStatement pst;
		Boolean additionStatus = false;
		int i  = 0;
		try {
			
			pst = connection.prepareStatement(DatabaseConstants.ADD_EXPENSE);
			pst.setString(++i, generateRequestId());
			pst.setString(++i, objExpense.getDepartment());
			pst.setString(++i, objExpense.getPaidTo());
			pst.setString(++i, objExpense.getDescription());
			pst.setString(++i, objExpense.getAmount());
			pst.setTimestamp(++i, objExpense.getCreatedDate());
			pst.setString(++i, objExpense.getCreatedBy());			
			int s=pst.executeUpdate();
			connection.commit();
			if(s>0){
				System.out.println("from true section");
				additionStatus = true;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return additionStatus;
	}
	
	public Boolean updateExpenses(Expenses objExpense) throws HMSException
	{
		PreparedStatement pst;
		Boolean updateStatus = false;
		int i  = 1;
		try {
			
			pst = connection.prepareStatement(DatabaseConstants.UPDATE_EXPENSE);
			pst.setString(++i, objExpense.getDepartment());
			pst.setString(++i, objExpense.getPaidTo());
			pst.setString(++i, objExpense.getDescription());
			pst.setString(++i, objExpense.getAmount());
			pst.setString(++i, generateRequestId());

			int s=pst.executeUpdate();
			if(s>0){
				updateStatus = true;
				System.out.println("Expenses Record updated successfully");
			}
				
		} catch (SQLException e) {
			
			throw new HMSException("Expenses Update Operation failed");
			
		}finally{
			try {
				connection.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new HMSException("Expenses Update Operation failed");
			}
		}
		return updateStatus;
	}	
	

	
	public String generateRequestId(){

		String prefix = "EX";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = connection.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_EXPENSES);
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
	

}
