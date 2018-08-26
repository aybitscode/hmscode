package com.aybits.hms.func.amenity.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
<<<<<<< HEAD
import com.aybits.hms.func.amenity.beans.Amenity;
=======
import com.aybits.hms.func.facility.beans.Facility;
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AmenityDAO {

    static Logger Log = Logger.getLogger(AmenityDAO.class);

    private Connection connection = DBConnection.getDBConnection();

<<<<<<< HEAD
    public List<Amenity> fetchAmenitiesByType(String amenityType){
        return null;
    }

    public List<Amenity> fetchAmenitiesByChargeApplicable(){
        return null;
    }

    public List<Amenity> fetchAllAmenities(){
=======
    public List<Facility> fetchFacilitiesByType(String facilityType){
        return null;
    }

    public List<Facility> fetchFacilitiesByChargeApplicable(){
        return null;
    }

    public List<Facility> fetchAllFacilities(){
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0
        return null;
    }



<<<<<<< HEAD
    public Boolean addAmenity(Amenity amenity) throws HMSException
    {
        boolean isAmenityAdded = false;

        try (PreparedStatement ps = connection.prepareStatement(AmenityDBQueries.INSERT_NEW_AMENITY, Statement.RETURN_GENERATED_KEYS))
        {
            connection.setAutoCommit(false);
            ps.setString(1, amenity.getHotelId());
            ps.setString(2,amenity.getAmenityName());
            ps.setString(3, amenity.getAmenityDescription());
            ps.setString(4, amenity.isAvailable().toString());
            ps.setString(5, amenity.isChargeable().toString());
            ps.setString(6, amenity.getAmenityType().toString());
            ps.setDouble(7, amenity.getAmenityCharges());
=======
    public Boolean addFacility(Facility facility) throws HMSException
    {
        boolean isFacilityAdded = false;

        try (PreparedStatement ps = connection.prepareStatement(AmenityDBQueries.INSERT_NEW_FACILITY, Statement.RETURN_GENERATED_KEYS))
        {
            connection.setAutoCommit(false);
            ps.setString(1, facility.getHotelId());
            ps.setString(2,facility.getFacilityName());
            ps.setString(3, facility.getFacilityDescription());
            ps.setString(4, facility.getIsFacilityAvailable().toString());
            ps.setString(5, facility.getChargeable().toString());
            ps.setString(6, facility.getFacilityType().toString());
            ps.setDouble(7, facility.getFacilityPrice());
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0

            ps.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            if(numRowsAffected > 0)
<<<<<<< HEAD
                isAmenityAdded = true;
        } catch (SQLException e) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occured::" + e.getMessage());
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {

        }
        return isAmenityAdded;
    }


    public Boolean updateAmenity(Amenity amenity){
        return true;
    }

    public Boolean deleteAmenity(Amenity amenity){
        return true;
    }

    public Boolean updateAmenityStatus(String amenityId,Integer status){
=======
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
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0
        return true;
    }

}
