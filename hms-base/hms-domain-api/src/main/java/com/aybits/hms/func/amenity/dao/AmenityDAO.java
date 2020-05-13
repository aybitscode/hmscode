package com.aybits.hms.func.amenity.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.aybits.hms.arch.exception.HMSErrorCodes.HMS_EXCEPTION;

public class AmenityDAO {

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();

    static Logger Log = Logger.getLogger(AmenityDAO.class);



    public Boolean addAmenity(Amenity amenity) throws HMSRuntimeException {
        boolean isAmenityAdded = false;
        Connection connection  = null;
        PreparedStatement ps = null;
        try {
            connection = DBCPConnection.getDBConnection();
            ps = connection.prepareStatement(AmenityDBQueries.INSERT_NEW_AMENITY);


            String keyPrefix = "HAME";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey("hotel_id","amenity_id","hms_amenity");

            String amenityId = hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix,false);
            amenity.setAmenityId(amenityId);
            connection.setAutoCommit(false);
            ps.setString(1, amenity.getAmenityId());
            ps.setString(2, amenity.getAmenityName());
            ps.setString(3, amenity.getAmenityDescription());
            ps.setString(4, amenity.isAvailable()? HMSAPIConstants.YES:HMSAPIConstants.NO);
            ps.setString(5, amenity.isChargeable()? HMSAPIConstants.YES:HMSAPIConstants.NO);
            ps.setInt(6, amenity.getAmenityType().getAmenityTypeAsInt());
            ps.setDouble(7, amenity.getAmenityCharges());
            ps.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            if (numRowsAffected > 0)
                isAmenityAdded = true;

        } catch (SQLException e) {
            Log.error("error occurred", e);
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMS_EXCEPTION, "sql Exception occurred::" + e.getMessage()));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, ps, connection);
        }
        return isAmenityAdded;
    }


    public Boolean updateAmenityChargeability(String amenityId,String hotelId,Boolean isChargeable){

        Boolean isUpdateSuccessful = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(AmenityDBQueries.UPDATE_AMENITY_CHARGEABILITY);
            stmt.setString(1, isChargeable?HMSAPIConstants.YES:HMSAPIConstants.NO);
            stmt.setString(2, hotelId);
            stmt.setString(3,amenityId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                isUpdateSuccessful = true;
            }

            connection.commit();

        } catch (SQLException sqle) {
            connection.rollback();
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occurred"));
        } catch (NullPointerException npe) {
            connection.rollback();
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isUpdateSuccessful;
        }

    }
    public Boolean updateAmenityStatus(String hotelId,String amenityId, Status status) throws HMSRuntimeException {
        Boolean isUpdateSuccessful = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(AmenityDBQueries.UPDATE_AMENITY_STATUS);
            stmt.setInt(1, status.getStatusAsInt());
            stmt.setString(2, hotelId);
            stmt.setString(3,amenityId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                isUpdateSuccessful = true;
            }

            connection.commit();

        } catch (SQLException sqle) {
            connection.rollback();
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occurred"));
        } catch (NullPointerException npe) {
            connection.rollback();
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isUpdateSuccessful;
        }
    }

    public Boolean updateAmenityCharges(String hotelId,String amenityId,Double amenityCharges) throws HMSRuntimeException{
        Boolean isFacilityDisabled = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(AmenityDBQueries.UPDATE_AMENITY_STATUS);
            stmt.setDouble(1, amenityCharges);
            stmt.setString(2, hotelId);
            stmt.setString(3, amenityId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                isFacilityDisabled = true;
            }

            connection.commit();

        } catch (SQLException sqle) {
            connection.rollback();
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occurred"));
        } catch (NullPointerException npe) {
            connection.rollback();
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isFacilityDisabled;
        }
    }


}
