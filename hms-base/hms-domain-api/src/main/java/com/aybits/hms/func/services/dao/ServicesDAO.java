package com.aybits.hms.func.services.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.facility.beans.Facility;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServicesDAO {

    static Logger Log = Logger.getLogger(ServicesDAO.class);

    public List<Facility> fetchFacilitiesByType(String facilityType){
        return null;
    }

    public List<Facility> fetchFacilitiesByChargeApplicable(){
        return null;
    }

    public List<Facility> fetchAllFacilities(){
        return null;
    }

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public Boolean addFacility(Facility facility) throws HMSException
    {
        boolean isFacilityAdded = false;

        try
        {
            stmt = connection.prepareStatement(ServicesDBQueries.INSERT_NEW_FACILITY, Statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false);
            stmt.setString(1, facility.getHotelId());
            stmt.setString(2,facility.getFacilityName());
            stmt.setString(3, facility.getFacilityDescription());
            stmt.setString(4, facility.getIsFacilityAvailable().toString());
            stmt.setString(5, facility.getChargeable().toString());
            stmt.setString(6, facility.getFacilityType().toString());
            stmt.setDouble(7, facility.getFacilityCharges());

            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int numRowsAffected = stmt.executeUpdate();
            if(numRowsAffected > 0)
                isFacilityAdded = true;
        } catch (SQLException e) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occured::" + e.getMessage());
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
        }
        return isFacilityAdded;
    }


    public Boolean updateFacility(Facility facility){
        return true;
    }

    public Boolean deleteFacility(Facility facility){
        return true;
    }

    public Boolean updateFacilityStatus(String facilityId,Integer status){
        return true;
    }

}
