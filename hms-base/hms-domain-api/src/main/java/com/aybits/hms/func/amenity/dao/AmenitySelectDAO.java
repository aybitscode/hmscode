package com.aybits.hms.func.amenity.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.beans.AmenityType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AmenitySelectDAO {

    static Logger Log = Logger.getLogger(AmenitySelectDAO.class);
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;


    protected List<Amenity> getAmenitiesByAvailability(String hotelId, Boolean isAmenityAvailable) throws HMSRuntimeException{

        List<Amenity> amenitiesList = new ArrayList<Amenity>();
        Amenity amenity = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(AmenityDBQueries.FETCH_AMENITY_BY_AVAILABILITY);

            Integer availability = HMSUtilAPI.getIntegerValueFromBoolean(isAmenityAvailable);

            stmt.setString(1, hotelId);
            stmt.setInt(2,availability);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            amenity = populateAmenity(rs);

            if (null != amenity) {
                Log.info("\nPopulating Amenity[" + amenity.getHotelId() + "," + amenity.getAmenityId() + "] in Amenity Object");
                amenitiesList.add(amenity);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return amenitiesList;
        }
    }

    protected List<Amenity> getAmenitiesByChargeability(String hotelId, Boolean isChargeable) throws HMSRuntimeException{

        List<Amenity> amenitiesList = new ArrayList<Amenity>();
        Amenity amenity = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(AmenityDBQueries.FETCH_AMENITY_BY_CHARGEABILITY);

            Integer chargeability = HMSUtilAPI.getIntegerValueFromBoolean(isChargeable);

            stmt.setString(1, hotelId);
            stmt.setInt(2,chargeability);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            amenity = populateAmenity(rs);

            if (null != amenity) {
                Log.info("\nPopulating Amenity[" + amenity.getHotelId() + "," + amenity.getAmenityId() + "] in Amenity Object");
                amenitiesList.add(amenity);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return amenitiesList;
        }
    }


    private List<Amenity> getAllAmenities(String hotelId) throws HMSRuntimeException{

        List<Amenity> amenitiesList = new ArrayList<Amenity>();
        Amenity amenity = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(AmenityDBQueries.FETCH_ALL_AMENITIES);
            stmt.setString(1, hotelId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            amenity = populateAmenity(rs);

            if (null != amenity) {
                Log.info("\nPopulating Amenity[" + amenity.getHotelId() + "," + amenity.getAmenityId() + "] in Amenity Object");
                amenitiesList.add(amenity);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return amenitiesList;
        }

    }


    protected Amenity getAmenity(String hotelId, String amenityId) throws HMSRuntimeException {
        Amenity amenity = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(AmenityDBQueries.FETCH_AMENITY_BY_AMENITY_ID);
            stmt.setString(1, hotelId);
            stmt.setString(2, amenityId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            amenity = populateAmenity(rs);

            if (null != amenity)
                Log.info("\nPopulating Amenity[" + amenity.getHotelId()+","+amenity.getAmenityId() + "] in Amenity Object");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return amenity;
        }

    }


    private Amenity populateAmenity(ResultSet rs) throws SQLException {

        if (rs.next() == false) {
            System.out.println("ResultSet is empty in Java");
            return null;
        } else {
            String amenityId = rs.getString("AMENITY_ID");
            String hotelId = rs.getString("HOTEL_ID");
            String amenityName = rs.getString("AMENITY_NAME");
            String amenityDescription = rs.getString("AMENITY_DESCRIPTION");
            String isAmenityAvailable = rs.getString("IS_AVAILABLE");
            String isAmenityChargeable = rs.getString("IS_CHARGEABLE");

            Boolean isAvailable = HMSUtilAPI.getBooleanValueFromString(isAmenityAvailable);
            Boolean isChargeable = HMSUtilAPI.getBooleanValueFromString(isAmenityChargeable);

            Double amenityCharges = rs.getDouble("AMENITY_CHARGES");


            Date dateCreated = HMSUtilAPI.convertTimestampToDate(rs.getTimestamp("DATE_CREATED"));
            Date dateUpdated = HMSUtilAPI.convertTimestampToDate(rs.getTimestamp("DATE_UPDATED"));


            AmenityType amenityType = AmenityType.convertIntToAmenityType(rs.getInt("AMENITY_TYPE"));

            return new Amenity(hotelId, amenityId, amenityName, amenityDescription,
                    isAvailable,isChargeable, amenityType, amenityCharges);
        }
    }


    protected HashMap<String,Amenity> getAmenitiesMapByHotelId(String hotelId) throws HMSRuntimeException{

        HashMap<String,Amenity> amenitiesHashMap = new HashMap<>();
        try{
            List<Amenity> amenitiesList = getAllAmenities(hotelId);
            for(Amenity amenity:amenitiesList){
                String amenityId = amenity.getAmenityId();
                amenitiesHashMap.put(amenityId,amenity);
            }

        }catch(HMSRuntimeException he){
            Log.error("Exception occurred while loading Amenities Map from DB");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION,"Exception occurred while loading Amenities Map from DB"));
        }
        return amenitiesHashMap;
    }
}
