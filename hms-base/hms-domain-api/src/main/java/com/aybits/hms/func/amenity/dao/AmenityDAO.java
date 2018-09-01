package com.aybits.hms.func.amenity.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AmenityDAO {

    static Logger Log = Logger.getLogger(AmenityDAO.class);

    private Connection connection = DBConnection.getDBConnection();

    public Boolean addAmenity(Amenity amenity) throws HMSException {
        boolean isAmenityAdded = false;

        try (PreparedStatement ps = connection.prepareStatement(AmenityDBQueries.INSERT_NEW_AMENITY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            ps.setString(1, amenity.getHotelId());
            ps.setString(2, amenity.getAmenityName());
            ps.setString(3, amenity.getAmenityDescription());
            ps.setString(4, amenity.isAvailable().toString());
            ps.setString(5, amenity.isChargeable().toString());
            ps.setString(6, amenity.getAmenityType().toString());
            ps.setDouble(7, amenity.getAmenityCharges());
            ps.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            if (numRowsAffected > 0) {
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


    public Boolean updateAmenity(Amenity amenity) {
        return true;
    }

    public Boolean deleteAmenity(Amenity amenity) {
        return true;
    }
}