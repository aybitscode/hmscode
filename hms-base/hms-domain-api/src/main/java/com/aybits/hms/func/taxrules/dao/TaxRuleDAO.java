package com.aybits.hms.func.taxrules.dao;

import static java.util.Objects.requireNonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.taxrules.beans.TaxRule;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxRuleDAO {

		Connection connection = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
	    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI(); 
	    private TaxRuleSelectDAO taxRuleSelectDAO = new TaxRuleSelectDAO();

	    static Logger Log = Logger.getLogger(TaxRuleDAO.class);
	    
	    public String addTaxRule(TaxRule taxRule) throws HMSRuntimeException {
	        boolean isTaxRuleAdded = false;
	        Connection connection  = null;
	        PreparedStatement ps = null;
			String taxRuleId = null;
			try {
	            connection = DBCPConnection.getDBConnection();
	            ps = connection.prepareStatement(TaxRuleDBQueries.INSERT_NEW_TAXRULE);


	            String keyPrefix = "HTXR";
	            String keySuffix = hmsCommonDAO.getNextPrimaryKey("hotel_id","taxrule_id","hms_taxrule");

	            taxRuleId = hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix,false);
	            taxRule.setTaxRuleId(taxRuleId);
	            connection.setAutoCommit(false);
	            ps.setString(1, taxRule.getTaxRuleId());
	            ps.setString(2, taxRule.getTaxRuleName());
	            ps.setDouble(3, taxRule.getLowerBound());
	            ps.setDouble(4, taxRule.getUpperBound());
	            ps.setDouble(5, taxRule.getCgst());
	            ps.setDouble(6, taxRule.getSgst());
	            ps.setDouble(7, taxRule.getGst());
	            ps.setString(8, taxRule.getTaxCategory());
	            ps.setString(9, taxRule.getTaxRuleDescription());
	            ps.setString(10, taxRule.getHotelId());
	            ps.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
	            int numRowsAffected = ps.executeUpdate();
	            if (numRowsAffected > 0)
	                isTaxRuleAdded = true;

	        } catch (SQLException e) {
	            Log.error("error occurred", e);
	            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occurred::" + e.getMessage()));
	        } catch (NullPointerException npe) {
	            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
	        } finally {
	            DBCPConnection.closeDBConnection(null, ps, connection);
	        }
	        return taxRuleId;
	    }
	    
	    
	    public Boolean updateTaxRule(Hotel hotel,TaxRule taxRule) throws HMSRuntimeException {
	    	Boolean updateStatus = false;
	    	TaxRule taxRuleFromDB = taxRuleSelectDAO.getTaxRuleById(hotel.getHotelId(), taxRule.getTaxRuleId());
	    			
	        int i  = 1;
	    	try {
	    		taxRuleFromDB = requireNonNull(taxRuleFromDB);
	    		connection = DBCPConnection.getDBConnection();
	            connection.setAutoCommit(false);
	        	stmt = connection.prepareStatement(TaxRuleDBQueries.UPDATE_EXISTING_TAXRULE);
	        	stmt.setString(i++, taxRule.getTaxRuleName());
	        	stmt.setDouble(i++, taxRule.getLowerBound());
	        	stmt.setDouble(i++, taxRule.getUpperBound());
	        	stmt.setDouble(i++, taxRule.getCgst());
	        	stmt.setDouble(i++, taxRule.getSgst());
	        	stmt.setString(i++, taxRule.getTaxCategory());
	        	stmt.setString(i++, taxRule.getTaxRuleDescription());
	            stmt.setString(i++, taxRuleFromDB.getTaxRuleId());
	            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
	            int s=stmt.executeUpdate();
	            if(s>0){
	                updateStatus = true;
	                Log.info("TaxRule Record updated successfully");
	                connection.commit();
	                Log.info("\nTaxRule[" + taxRule.getTaxRuleId()+ "] successfully updated");

	            }
	            } catch (SQLException sqle) {
	                // TODO Auto-generated catch block
	                //connection.rollback();
	                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
	            } catch (NullPointerException npe) {
	                //connection.rollback();
	                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage()));
	            }
	    	
	        finally {
	        	DBCPConnection.closeDBConnection(null, stmt, connection);
	        	}
	        return updateStatus;
	    }
	    

	    
}
