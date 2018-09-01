package com.aybits.hms.func.amenity.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
<<<<<<< HEAD
import com.aybits.hms.func.amenity.beans.Amenity;
=======

import com.aybits.hms.func.amenity.beans.Amenity;

import com.aybits.hms.func.facility.beans.Facility;

>>>>>>> ae5dad5d87e8db07071616eabafc3819e5249428
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AmenityDAO {

    static Logger Log = Logger.getLogger(AmenityDAO.class);

    private Connection connection = DBConnection.getDBConnection();

<<<<<<< HEAD
    public Boolean addAmenity(Amenity amenity) throws HMSException {
        boolean isAmenityAdded = false;

        try (PreparedStatement ps = connection.prepareStatement(AmenityDBQueries.INSERT_NEW_AMENITY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            ps.setString(1, amenity.getHotelId());
            ps.setString(2, amenity.getAmenityName());
=======

    public List<Amenity> fetchAmenitiesByType(String amenityType){
        return null;
    }

    public List<Amenity> fetchAmenitiesByChargeApplicable(){
        return null;
    }

    public List<Amenity> fetchAllAmenities(){
        return null;
    };

    public Boolean addAmenity(Amenity amenity) throws HMSException
    {
        boolean isAmenityAdded = false;


        try
        {
            PreparedStatement ps = connection.prepareStatement(AmenityDBQueries.INSERT_NEW_AMENITY);
            connection.setAutoCommit(false);
            ps.setString(1, amenity.getAmenityId());
            ps.setString(2,amenity.getAmenityName());
>>>>>>> ae5dad5d87e8db07071616eabafc3819e5249428
            ps.setString(3, amenity.getAmenityDescription());
            ps.setString(4, amenity.isAvailable().toString());
            ps.setString(5, amenity.isChargeable().toString());
            ps.setString(6, amenity.getAmenityType().toString());
            ps.setDouble(7, amenity.getAmenityCharges());
<<<<<<< HEAD
            ps.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            if (numRowsAffected > 0) {
=======


            ps.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            if(numRowsAffected > 0)
>>>>>>> ae5dad5d87e8db07071616eabafc3819e5249428
                isAmenityAdded = true;
            }
        } catch (SQLException e) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occured::" + e.getMessage());
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {

        }
        return isAmenityAdded;
    }


<<<<<<< HEAD
    public Boolean updateAmenity(Amenity amenity) {
        return true;
    }

    public Boolean deleteAmenity(Amenity amenity) {
        return true;
    }
}
=======
    public Boolean updateAmenity(Amenity amenity){
        return true;
    }

    public Boolean deleteAmenity(Amenity amenity){
        return true;
    }








}
>>>>>>> ae5dad5d87e8db07071616eabafc3819e5249428
