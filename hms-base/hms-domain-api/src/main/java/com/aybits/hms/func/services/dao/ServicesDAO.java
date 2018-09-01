package com.aybits.hms.func.services.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.facility.beans.Facility;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServicesDAO {

    static Logger Log = Logger.getLogger(ServicesDAO.class);

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



    public Boolean addFacility(Facility facility) throws HMSException
    {
        boolean isFacilityAdded = false;

        try (PreparedStatement ps = connection.prepareStatement(ServicesDBQueries.INSERT_NEW_FACILITY, Statement.RETURN_GENERATED_KEYS))
        {
            connection.setAutoCommit(false);
            ps.setString(1, facility.getHotelId());
            ps.setString(2,facility.getFacilityName());
            ps.setString(3, facility.getFacilityDescription());
            ps.setString(4, facility.getIsFacilityAvailable().toString());
            ps.setString(5, facility.getChargeable().toString());
            ps.setString(6, facility.getFacilityType().toString());
<<<<<<< HEAD
         //   ps.setDouble(7, facility.getFacilityPrice());
=======
           // ps.setDouble(7, facility.getFacilityPrice());
>>>>>>> ae5dad5d87e8db07071616eabafc3819e5249428

            ps.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            if(numRowsAffected > 0)
                isFacilityAdded = true;
        } catch (SQLException e) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occured::" + e.getMessage());
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {

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
