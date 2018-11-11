package com.aybits.hms.func.taxrules.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.taxrules.beans.TaxRule;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxRuleSelectDAO {


    static Logger Log = Logger.getLogger(TaxRuleSelectDAO.class);
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public TaxRule fetchTaxRuleByTaxRuleId(String hotelId,String taxRuleId) throws HMSRuntimeException {
        TaxRule taxRule = new TaxRule();

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(TaxRuleDBQueries.FETCH_TAXRULE_BY_TAXRULE_ID);

            stmt.setString(1, hotelId);
            stmt.setString(1, taxRuleId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            taxRule = populateTaxRule(rs);
            Log.info("\nPopulating TaxRule[" + taxRule.getTaxRuleId() + "] in TaxRule Object");

        } catch (Exception npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return taxRule;
        }
    }


    public TaxRule fetchTaxRuleByTaxRuleName(String hotelId,String taxRuleName) throws HMSRuntimeException {
        TaxRule taxRule = new TaxRule();

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(TaxRuleDBQueries.FETCH_TAXRULE_BY_TAXRULE_NAME);

            stmt.setString(1, hotelId);
            stmt.setString(1, taxRuleName);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            taxRule = populateTaxRule(rs);
            Log.info("\nPopulating TaxRule[" + taxRule.getTaxRuleId() + "] in TaxRule Object");

        } catch (Exception npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return taxRule;
        }
    }


    public List<TaxRule> fetchAllTaxRules() throws HMSRuntimeException {
        List<TaxRule> TaxRules = new ArrayList<TaxRule>();
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(TaxRuleDBQueries.FETCH_ALL_TAXRULES);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            while (rs.next()) {
                TaxRule TaxRule = populateTaxRule(rs);
                TaxRules.add(TaxRule);
            }

        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return TaxRules;
        }
    }

    private TaxRule populateTaxRule(ResultSet rs) throws SQLException {

        if (rs.next() == false) {
            System.out.println("ResultSet is empty in Java");
            return null;
        } else {

            do {
                String hotelId = rs.getString("hotel_id");
                String taxRuleId = rs.getString("taxrule_id");
                String taxRuleName = rs.getString("taxrule_name");
                String taxRuleDescription = rs.getString("taxrule_description");
                Double taxLowerBound = rs.getDouble("tax_lower_bound");
                Double taxUpperBound = rs.getDouble("tax_upper_bound");
                Double cgst = rs.getDouble("tax_cgst");
                Double sgst = rs.getDouble("tax_sgst");
                Double gst = rs.getDouble("tax_gst");
                String taxCategory = rs.getString("tax_category");
                return new  TaxRule(hotelId, taxRuleId, taxRuleName, taxLowerBound, taxUpperBound, cgst, sgst, gst, taxCategory, taxRuleDescription);
            } while (rs.next());
        }
    }
}