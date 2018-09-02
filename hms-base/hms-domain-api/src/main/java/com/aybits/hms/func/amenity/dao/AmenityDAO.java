package com.aybits.hms.func.amenity.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.amenity.beans.Amenity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AmenityDAO {

    static Logger Log = Logger.getLogger(AmenityDAO.class);

    public List<Amenity> fetchAmenitiesByType(String amenityType) {
        return null;
    }

    public List<Amenity> fetchAmenitiesByChargeApplicable() {
        return null;
    }

    public List<Amenity> fetchAllAmenities() {
        return null;
    }

    ;

    public Boolean addAmenity(Amenity amenity) throws HMSException {
        boolean isAmenityAdded = false;
        Connection connection  = null;
        PreparedStatement ps = null;
        try {
            connection = DBCPConnection.getDBConnection();
            ps = connection.prepareStatement(AmenityDBQueries.INSERT_NEW_AMENITY);
            connection.setAutoCommit(false);
            ps.setString(1, amenity.getAmenityId());
            ps.setString(2, amenity.getAmenityName());

            ps.setString(3, amenity.getAmenityDescription());
            ps.setString(4, amenity.isAvailable().toString());
            ps.setString(5, amenity.isChargeable().toString());
            ps.setString(6, amenity.getAmenityType().toString());
            ps.setDouble(7, amenity.getAmenityCharges());
            ps.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            if (numRowsAffected > 0)
                isAmenityAdded = true;

        } catch (SQLException e) {
            Log.error("error occured", e);
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occured::" + e.getMessage());
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, ps, connection);
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
