package com.aybits.hms.func.facility.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.hotel.dao.HotelDAO;
import com.aybits.hms.func.hotel.dao.HotelDBQueries;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacilityDAO {

    static Logger Log = Logger.getLogger(FacilityDAO.class);

    private Connection connection = DBConnection.getDBConnection();

    public List<Facility> fetchFacilitiesByType(String facilityType){
        return null;
    }

    public List<Facility> fetchFacilitiesByChargeApplicable(){
        return null;
    }

    public List<Facility> fetchAllFacilities(){
        return null;
    }

    public Boolean addFacility(Facility[] facilities) throws HMSException
    {
        boolean isFacilitiesUpdated = false;
        for (Facility facility : facilities)
        {
            try (PreparedStatement ps = connection.prepareStatement(FacilityDBQuries.INSERT_NEW_FACILITY, Statement.RETURN_GENERATED_KEYS))
            {
                connection.setAutoCommit(false);
                ps.setString(1, facility.getHotelId());
                ps.setString(2, facility.getFacilityDescription());
                ps.setString(3, facility.getIsFacilityAvailable().toString());
                ps.setString(4, facility.getChargeable().toString());
                ps.setString(5, facility.getFacilityType().toString());
                ps.setDouble(6, facility.getFacilityPrice());
                ps.setInt(7, facility.getApplicableVocher());

                ps.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                int numRowsAffected = ps.executeUpdate();
                isFacilitiesUpdated = true;
            } catch (SQLException e) {
                isFacilitiesUpdated = false;
                e.printStackTrace();
                throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occured::" + e.getMessage());
            } catch (NullPointerException npe) {
                isFacilitiesUpdated = false;
                throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
            } finally {

            }
        }
        return isFacilitiesUpdated;
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
