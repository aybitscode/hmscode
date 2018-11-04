package com.aybits.hms.func.voucher.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.voucher.beans.Voucher;
import com.aybits.hms.func.voucher.beans.VoucherStatus;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class VoucherDAO {

    static Logger Log = Logger.getLogger(VoucherDAO.class);
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public Boolean addVoucher(Voucher voucher){

        Boolean isVoucherAdditionSuccessful = false;
        try {
            connection = DBCPConnection.getDBConnection();
            stmt = connection.prepareStatement(VoucherDBQueries.INSERT_NEW_VOUCHER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,voucher.getHotelId());
            stmt.setString(2, voucher.getVoucherName());
            stmt.setString(3, voucher.getVoucherDescription());


            Timestamp ts = HMSUtilAPI.convertDateToTimestamp(voucher.getVoucherStartDate());

            stmt.setTimestamp(4,ts);


            ts = HMSUtilAPI.convertDateToTimestamp(voucher.getVoucherExpiryDate());

            stmt.setTimestamp(5,ts);

            stmt.setDouble(6,voucher.getVoucherDiscount());
            stmt.setInt(7,voucher.getVoucherStatus().getVoucherStatusAsInt());


            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int numRowsAffected = stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long voucherId = rs.getLong(1);
                    voucher.setVoucherId(voucherId.toString());
                    isVoucherAdditionSuccessful = true;
                    connection.commit();
                }
        } catch (Exception npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs , stmt, connection);
            return isVoucherAdditionSuccessful;
        }

    }

    public Boolean updateVoucher(Voucher voucher){
        Boolean isVoucherUpdateSuccessful = false;
        Voucher voucherFromDB = fetchVoucher(voucher.getHotelId(),voucher.getVoucherId());

        try{
            connection = DBCPConnection.getDBConnection();
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


            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsAffected = stmt.executeUpdate();
            if (1 == rowsAffected) {
                isVoucherUpdateSuccessful = true;
            }

            Log.info("\nVoucher[" + voucher.getHotelId() +":"+voucher.getVoucherId()+"] successfully updated");
            stmt = requireNonNull(stmt);
            stmt.close();


        }  catch (Exception npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return isVoucherUpdateSuccessful;
        }
    }

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
                Log.info("\nPopulating Voucher[" + voucher.getHotelId() +":"+voucher.getVoucherId()+"] in Voucher Object");

            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();


        }catch (Exception npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return voucher;
        }
    }

    public List<Voucher> fetchAllVouchers(String hotelId) throws HMSRuntimeException{

        List<Voucher> vouchers = new ArrayList<Voucher>();
        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(VoucherDBQueries.FETCH_VOUCHERS_BY_HOTELID);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Voucher voucher = populateVoucher(rs);
                vouchers.add(voucher);
            }


            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();

        } catch (Exception npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return vouchers;
        }
    }


    public List<Voucher> fetchAllVouchers() throws HMSRuntimeException{

        List<Voucher> vouchers = new ArrayList<Voucher>();
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(VoucherDBQueries.FETCH_ALL_VOUCHERS);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Voucher voucher = populateVoucher(rs);
                vouchers.add(voucher);
            }


            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();

        }  catch (Exception npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
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


    public static class VoucherCache {


        static Logger log = Logger.getLogger(VoucherCache.class);
        private static final ConcurrentHashMap voucherConcurrentHashMap = new ConcurrentHashMap();
        private static final HashSet<String> voucherIds = new HashSet<>();
        private VoucherDAO voucherDAO = new VoucherDAO();

        public Boolean initCache(String hotelId){

            Boolean isVoucherCacheInitialized = false;
            if(voucherConcurrentHashMap.isEmpty()){
                List<Voucher> vouchers = null;
                try {
                    vouchers = voucherDAO.fetchAllVouchers(hotelId);
                    if(!vouchers.isEmpty()) {
                        for (Voucher voucher : vouchers) {
                            voucherIds.add(voucher.getVoucherId());
                            voucherConcurrentHashMap.put(voucher.getVoucherId(), voucher);
                        }
                    }

                }catch(HMSRuntimeException e){
                    //LOG Cache Initialization failed
                    //  throw new HMSRuntimeException(HMSErrorCodes.HOTEL_DETAILS_UNAVAILABLE,"Fetching all voucher details failed");
                }finally{
                    if(!voucherConcurrentHashMap.keySet().isEmpty()){
                        isVoucherCacheInitialized = true;
                    }
                }
            }


            return isVoucherCacheInitialized;
        }

        public Boolean addVoucher(Voucher voucher) throws HMSRuntimeException {

            Boolean isVoucherAdditionSuccessful = voucherDAO.addVoucher(voucher);
            if(isVoucherAdditionSuccessful){
                if (voucherConcurrentHashMap.get(voucher.getVoucherId()) == null) {
                    voucherIds.add(voucher.getVoucherId());
                    voucherConcurrentHashMap.put(voucher.getVoucherId(), voucher);
                }
            }
            return isVoucherAdditionSuccessful;

        }

        public Boolean updateVoucher(Voucher voucher) throws HMSRuntimeException {
            Boolean isVoucherUpdateSuccessful = voucherDAO.updateVoucher(voucher);
            if(isVoucherUpdateSuccessful) {
                String voucherId = voucher.getVoucherId();
                if (voucherIds.contains(voucher.getVoucherId())) {
                    voucherConcurrentHashMap.remove(voucherId);
                }
                voucherConcurrentHashMap.put(voucherId, voucher);
            }
            return isVoucherUpdateSuccessful;
        }


        public Voucher fetchVoucherById(String hotelId,String voucherId) throws HMSRuntimeException {
            Voucher voucher = null;
            try{
                voucher = (Voucher)voucherConcurrentHashMap.get(voucherId);
                if (voucher == null){
                    voucher = voucherDAO.fetchVoucher(hotelId,voucherId);
                    voucher = requireNonNull(voucher);
                    voucherConcurrentHashMap.put(voucherId,voucher);
                }
            }catch(NullPointerException npe){
                log.info("No Voucher Present for the given voucherId["+voucherId+"]");
            }finally{
                return voucher;
            }
        }


        public  List<Voucher> fetchAllVouchers(String hotelId) throws HMSRuntimeException {

            ArrayList<Object> voucherObjs = new ArrayList<>();
            ArrayList<Voucher> vouchers = new ArrayList<>();

            voucherObjs.addAll(voucherConcurrentHashMap.values());
            if(voucherObjs.isEmpty()){
                initCache(hotelId);
                voucherObjs.addAll(voucherConcurrentHashMap.values());
            }
            for(Object obj:voucherObjs){
                Voucher voucher = (Voucher)obj;
                vouchers.add(voucher);
            }
            return vouchers;
        }

        public List<String> fetchAllVoucherIds() {
            ArrayList<String> voucherIds = new ArrayList<>();
            voucherIds.addAll(voucherConcurrentHashMap.keySet());
            return voucherIds;
        }


        public Boolean isVoucherPresent(String hotelId,String voucherId){
            Boolean isVoucherPresent = false;
            Voucher voucher = null;
            try {
                voucher = fetchVoucherById(hotelId,voucherId);
                if(voucher != null && voucher.getVoucherId() != null){
                    isVoucherPresent = true;
                }
            } catch (HMSRuntimeException e) {
                log.error("Exception occured while checking if Voucher["+voucher.getHotelId()+":"+voucher.getVoucherId()+"] is present in the system");
            }
            return isVoucherPresent;

        }

        public ConcurrentHashMap<String,Voucher> getVoucherCache(){
            return voucherConcurrentHashMap;
        }

    }
}
