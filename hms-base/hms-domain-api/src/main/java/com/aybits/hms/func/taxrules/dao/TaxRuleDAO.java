package com.aybits.hms.func.taxrules.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aybits.hms.arch.exception.HMSErrorInfo;
import org.apache.log4j.Logger;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.taxrules.beans.TaxRule;

public class TaxRuleDAO {

	 Connection connection = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
	    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();

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

	    protected TaxRule getTaxRuleByHotelId(String hotelId) throws HMSRuntimeException{

	    	TaxRule taxRule = new TaxRule();
	    	
	        try {
	            connection = DBCPConnection.getDBConnection();
	            connection.setAutoCommit(false);
	            stmt = connection.prepareStatement(TaxRuleDBQueries.FETCH_ALL_TAXRULES);
	            stmt.setString(1, hotelId);
	            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
	            rs = stmt.executeQuery();

	            taxRule = populateTaxRule(rs);

	            if (null != taxRule) {
	                Log.info("\nPopulating TaxRule[" + taxRule.getHotelId() + "," + taxRule.getTaxRuleId()+ "]");


	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
	        } catch (NullPointerException npe) {
	            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
	        } finally {
	            DBCPConnection.closeDBConnection(null, stmt, connection);
	            return taxRule;
	        }

	    }
	    
	    private TaxRule populateTaxRule(ResultSet rs) throws SQLException {

	        if (rs.next() == false) {
	            System.out.println("ResultSet is empty in Java");
	            return null;
	        } else {
	            String taxRuleId = rs.getString("taxrule_id");
	            String hotelId = rs.getString("hotel_id");
	            String taxRuleName = rs.getString("taxrule_name");
	            String taxRuleDescription = rs.getString("taxrule_description");
	            Double taxLowerBound = rs.getDouble("tax_lower_bound");
	            Double taxUpperBound = rs.getDouble("tax_upper_bound");
	            Double cgst = rs.getDouble("tax_cgst");
	            Double sgst = rs.getDouble("tax_sgst");
	            Double gst = rs.getDouble("tax_gst");
	            String taxCategory = rs.getString("tax_category");

	            
	            return new  TaxRule(hotelId, taxRuleId, taxRuleName, taxLowerBound, taxUpperBound, cgst, sgst, gst, taxCategory, taxRuleDescription); 
	        }
	    }
}
