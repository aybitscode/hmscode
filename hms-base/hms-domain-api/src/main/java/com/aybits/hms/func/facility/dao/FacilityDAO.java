package com.aybits.hms.func.facility.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.facility.beans.Facility;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class FacilityDAO {

    static Logger Log = Logger.getLogger(FacilityDAO.class);
    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    protected Boolean addFacility(Facility facility) throws HMSRuntimeException
    {
        boolean isFacilityAdded = false;
        try {
            connection = DBCPConnection.getDBConnection();
            stmt = connection.prepareStatement(FacilityDBQueries.INSERT_NEW_FACILITY);
            connection.setAutoCommit(false);

            String keyPrefix = "HFAC";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey("hotel_id","facility_id","hms_facility");

            String facilityId = hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix,false);
            facility.setFacilityId(facilityId);
            stmt.setString(1, facility.getHotelId());
            stmt.setString(2, facility.getFacilityId());
            stmt.setString(3, facility.getFacilityName());
            stmt.setString(4, facility.getFacilityDescription());
            stmt.setString(5, facility.isAvailable()? HMSAPIConstants.YES:HMSAPIConstants.NO);
            stmt.setString(6, facility.isChargeable()? HMSAPIConstants.YES:HMSAPIConstants.NO);
            stmt.setInt(7, facility.getFacilityType().getFacilityTypeAsInt());
            stmt.setDouble(8, facility.getFacilityCharges());

            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int numRowsAffected = stmt.executeUpdate();
            if(numRowsAffected > 0)
                isFacilityAdded = true;
        } catch (SQLException e) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        return isFacilityAdded;
    }

    protected Boolean updateFacilityStatus(String hotelId,String facilityId, Status status) throws HMSRuntimeException {
        Boolean isFacilityDisabled = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(FacilityDBQueries.UPDATE_FACILITY_STATUS);
            stmt.setInt(1, status.getStatusAsInt());
            stmt.setString(2, hotelId);
            stmt.setString(3,facilityId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                isFacilityDisabled = true;
            }

            connection.commit();

        } catch (SQLException sqle) {
            connection.rollback();
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } catch (NullPointerException npe) {
            connection.rollback();
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isFacilityDisabled;
        }
    }

    protected Boolean updateFacilityCharges(String hotelId,String facilityId,Double facilityCharges) throws HMSRuntimeException{
        Boolean isFacilityDisabled = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(FacilityDBQueries.UPDATE_FACILITY_STATUS);
            stmt.setDouble(1, facilityCharges);
            stmt.setString(2, hotelId);
            stmt.setString(3,facilityId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                isFacilityDisabled = true;
            }

            connection.commit();

        } catch (SQLException sqle) {
            connection.rollback();
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED,"DB SQL Exception occured"));
        } catch (NullPointerException npe) {
            connection.rollback();
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isFacilityDisabled;
        }
    }


}
