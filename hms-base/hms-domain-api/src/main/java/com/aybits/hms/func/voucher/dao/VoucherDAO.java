package com.aybits.hms.func.voucher.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.voucher.beans.Voucher;
import com.aybits.hms.func.voucher.beans.VoucherStatus;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class VoucherDAO {

    static Logger Log = Logger.getLogger(VoucherDAO.class);

    private Connection connection = DBConnection.getDBConnection();


    public Boolean addVoucher(Voucher voucher){


        Boolean isVoucherAdditionSuccessful = false;
        try (PreparedStatement ps = connection.prepareStatement(VoucherDBQueries.INSERT_NEW_VOUCHER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,voucher.getHotelId());
            ps.setString(2, voucher.getVoucherName());
            ps.setString(3, voucher.getVoucherDescription());


            Timestamp ts = HMSUtilAPI.convertDateToTimestamp(voucher.getVoucherStartDate());

            ps.setTimestamp(4,ts);


            ts = HMSUtilAPI.convertDateToTimestamp(voucher.getVoucherExpiryDate());

            ps.setTimestamp(5,ts);

            ps.setDouble(6,voucher.getVoucherDiscount());
            ps.setInt(7,voucher.getVoucherStatus().getVoucherStatusAsInt());


            ps.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long voucherId = rs.getLong(1);
                    voucher.setVoucherId(voucherId.toString());
                    isVoucherAdditionSuccessful = true;
                    connection.commit();
                }
            } catch (SQLException s) {
                s.printStackTrace();
            }catch (NullPointerException npe) {
                throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            return isVoucherAdditionSuccessful;
        }

    }

    public Boolean updateVoucher(Voucher voucher){


        Boolean isVoucherUpdateSuccessful = false;
        PreparedStatement stmt = null;

        Voucher voucherFromDB = fetchVoucher(voucher.getHotelId(),voucher.getVoucherId());

        try{
            voucherFromDB = requireNonNull(voucherFromDB);
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(VoucherDBQueries.UPDATE_EXISTING_VOUCHER);



            stmt.setString(1, voucher.getVoucherName());
            stmt.setString(2, voucher.getVoucherDescription());

            Timestamp ts = HMSUtilAPI.convertDateToTimestamp(voucher.getVoucherStartDate());
            stmt.setTimestamp(3, ts);
            ts = HMSUtilAPI.convertDateToTimestamp(voucher.getVoucherExpiryDate());
            stmt.setTimestamp(4,ts);

            stmt.setDouble(5,voucher.getVoucherDiscount());
            stmt.setInt(6,voucher.getVoucherStatus().getVoucherStatusAsInt());

            stmt.setString(7,voucher.getHotelId());
            stmt.setString(8,voucher.getVoucherId());


            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int rowsAffected = stmt.executeUpdate();
            if (1 == rowsAffected) {
                isVoucherUpdateSuccessful = true;
            }

            Log.info("\nVoucher[" + voucher.getHotelId() +":"+voucher.getVoucherId()+"] successfully updated");
            stmt = requireNonNull(stmt);
            stmt.close();


        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {

            return isVoucherUpdateSuccessful;
        }
    }

    public Voucher fetchVoucher(String hotelId,String voucherId){

        PreparedStatement stmt;
        ResultSet rs;
        Voucher voucher = null;

        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(VoucherDBQueries.FETCH_VOUCHER_BY_VOUCHER_ID);
            stmt.setString(1, hotelId);
            stmt.setString(2,voucherId);
            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            voucher = populateVoucher(rs);

            if(null != voucher)
                Log.info("\nPopulating Voucher[" + voucher.getHotelId() +":"+voucher.getVoucherId()+"] in Voucher Object");

            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            return voucher;
        }
    }

    public List<Voucher> fetchAllVouchers(String hotelId){

        List<Voucher> vouchers = new ArrayList<Voucher>();
        ResultSet rs;
        PreparedStatement stmt;
        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(VoucherDBQueries.FETCH_VOUCHERS_BY_HOTELID);
            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Voucher voucher = populateVoucher(rs);
                vouchers.add(voucher);
            }


            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();

        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            return vouchers;
        }
    }

    public Boolean updateVoucherPrice(String hotelId,Integer voucherId,Double newVoucherprice){

        return true;
    }

    public Boolean updateVoucherStatus(String hotelId,Integer voucherId,VoucherStatus voucherStatus){

        return true;
    }


    private Voucher populateVoucher(ResultSet rs) throws SQLException{

        if (rs.next() == false) {
            System.out.println("ResultSet is empty in Java");
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
