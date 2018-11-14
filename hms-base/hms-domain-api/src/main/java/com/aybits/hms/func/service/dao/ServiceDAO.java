package com.aybits.hms.func.service.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSAPIConstants;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.service.beans.Service;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO {

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();

    static Logger Log = Logger.getLogger(ServiceDAO.class);



    public Boolean addService(Service service) throws HMSRuntimeException {
        boolean isServiceAdded = false;
        Connection connection  = null;
        PreparedStatement ps = null;
        try {
            connection = DBCPConnection.getDBConnection();
            ps = connection.prepareStatement(ServiceDBQueries.INSERT_NEW_SERVICE);


            String keyPrefix = "HAME";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey("hotel_id","service_id","hms_service");

            String serviceId = hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix,false);
            service.setServiceId(serviceId);
            connection.setAutoCommit(false);
            ps.setString(1, service.getServiceId());
            ps.setString(2, service.getServiceName());
            ps.setString(3, service.getServiceDescription());
            ps.setString(4, service.isAvailable()? HMSAPIConstants.YES:HMSAPIConstants.NO);
            ps.setString(5, service.isChargeable()? HMSAPIConstants.YES:HMSAPIConstants.NO);
            ps.setInt(6, service.getServiceType().getServiceTypeAsInt());
            ps.setDouble(7, service.getServiceCharges());
            ps.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            if (numRowsAffected > 0)
                isServiceAdded = true;

        } catch (SQLException e) {
            Log.error("error occurred", e);
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occurred::" + e.getMessage()));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, ps, connection);
        }
        return isServiceAdded;
    }


    public Boolean updateServiceChargeability(String serviceId,String hotelId,Boolean isChargeable){

        Boolean isUpdateSuccessful = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(ServiceDBQueries.UPDATE_SERVICE_CHARGEABILITY);
            stmt.setString(1, isChargeable?HMSAPIConstants.YES:HMSAPIConstants.NO);
            stmt.setString(2, hotelId);
            stmt.setString(3,serviceId);
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
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isUpdateSuccessful;
        }

    }
    public Boolean updateServiceStatus(String hotelId,String serviceId, Status status) throws HMSRuntimeException {
        Boolean isUpdateSuccessful = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(ServiceDBQueries.UPDATE_SERVICE_STATUS);
            stmt.setInt(1, status.getStatusAsInt());
            stmt.setString(2, hotelId);
            stmt.setString(3,serviceId);
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
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isUpdateSuccessful;
        }
    }

    public Boolean updateServiceCharges(String hotelId,String serviceId,Double serviceCharges) throws HMSRuntimeException{
        Boolean isServiceDisabled = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(ServiceDBQueries.UPDATE_SERVICE_CHARGES);
            stmt.setDouble(1, serviceCharges);
            stmt.setString(2, hotelId);
            stmt.setString(3, serviceId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                isServiceDisabled = true;
            }

            connection.commit();

        } catch (SQLException sqle) {
            connection.rollback();
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occurred"));
        } catch (NullPointerException npe) {
            connection.rollback();
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isServiceDisabled;
        }
    }
}
