package com.aybits.hms.func.voucher.dao;

import static java.util.Objects.requireNonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.voucher.beans.Voucher;
import com.aybits.hms.func.voucher.beans.VoucherStatus;

public class VoucherSelectDAO {

	static Logger log = Logger.getLogger(VoucherSelectDAO.class);
	Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
	public Voucher fetchVoucher(String hotelId,String voucherId){
        Voucher voucher = null;

        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(VoucherDBQueries.FETCH_VOUCHER_BY_VOUCHER_ID);
            stmt.setString(1, hotelId);
            stmt.setString(2,voucherId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            voucher = populateVoucher(rs);

            if(null != voucher)
            	log.info("\nPopulating Voucher[" + voucher.getHotelId() +":"+voucher.getVoucherId()+"] in Voucher Object");

            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();


        }catch (Exception npe) {
           // throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return voucher;
        }
    }
	
	public List<Voucher> fetchAllVouchers(String hotelId)throws HMSException{
		List<Voucher> voucherList = null;
		Voucher voucher = null;
        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(VoucherDBQueries.FETCH_VOUCHERS_BY_HOTELID);
            stmt.setString(1, hotelId);
            
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            voucher = populateVoucher(rs);

            if(null != voucher)
            	log.info("\nPopulating Voucher[" + voucher.getHotelId() +":"+voucher.getVoucherId()+"] in Voucher Object");

            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();


        }catch (Exception npe) {
           // throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return voucherList;
        }
    }
	
	 private Voucher populateVoucher(ResultSet rs) throws SQLException{

	        if (rs.next() == false) {
	            System.out.println("ResultSet is empty");
	            return null;
	        } else {

	            do {
	                String hotelId = rs.getString("HOTEL_ID");
	                String voucherId = rs.getString("VOUCHER_ID");
	                String voucherName = rs.getString("HOTEL_NAME");
	                Timestamp ts = rs.getTimestamp("VOUCHER_START_DATE");
	                java.util.Date voucherStartDate = HMSUtilAPI.convertTimestampToDate(ts);
	                ts = rs.getTimestamp("VOUCHER_END_DATE");
	                java.util.Date voucherExpiryDate = HMSUtilAPI.convertTimestampToDate(ts);
	                String voucherDescription = rs.getString("VOUCHER_DESCRIPTION");
	                Integer iVoucherStatus = rs.getInt("VOUCHER_STATUS");
	                Double voucherDiscount = rs.getDouble("VOUCHER_DISCOUNT");
	                VoucherStatus voucherStatus = VoucherStatus.convertIntToVoucherStatus(iVoucherStatus);

	                return new Voucher(hotelId,voucherId, voucherName, voucherDescription,voucherStartDate,voucherExpiryDate,voucherDiscount,voucherStatus);

	            } while (rs.next());
	        }

	    }

	
}
