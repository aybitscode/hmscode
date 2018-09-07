package com.aybits.hms.func.facility.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.beans.FacilityType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacilitySelectDAO {

    static Logger Log = Logger.getLogger(FacilitySelectDAO.class);
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;


    public List<Facility> fetchFacilitiesByAvailability(String hotelId,Boolean isFacilityAvailable) throws HMSException{

        List<Facility> facilitiesList = new ArrayList<Facility>();
        Facility facility = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(FacilityDBQueries.FETCH_FACILITY_BY_AVAILABILITY);

            Integer availability = HMSUtilAPI.getIntegerValueFromBoolean(isFacilityAvailable);

            stmt.setString(1, hotelId);
            stmt.setInt(2,availability);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            facility = populateFacility(rs);

            if (null != facility) {
                Log.info("\nPopulating Facility[" + facility.getHotelId() + "," + facility.getFacilityId() + "] in Facility Object");
                facilitiesList.add(facility);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return facilitiesList;
        }
    }

    public List<Facility> fetchFacilitiesByChargeability(String hotelId,Boolean isChargeable) throws HMSException{

        List<Facility> facilitiesList = new ArrayList<Facility>();
        Facility facility = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(FacilityDBQueries.FETCH_FACILITY_BY_CHARGEABILITY);

            Integer chargeability = HMSUtilAPI.getIntegerValueFromBoolean(isChargeable);

            stmt.setString(1, hotelId);
            stmt.setInt(2,chargeability);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            facility = populateFacility(rs);

            if (null != facility) {
                Log.info("\nPopulating Facility[" + facility.getHotelId() + "," + facility.getFacilityId() + "] in Facility Object");
                facilitiesList.add(facility);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return facilitiesList;
        }
    }


    public List<Facility> fetchAllFacilities(String hotelId){

        List<Facility> facilitiesList = new ArrayList<Facility>();
        Facility facility = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(FacilityDBQueries.FETCH_ALL_FACILITIES);
            stmt.setString(1, hotelId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            facility = populateFacility(rs);

            if (null != facility) {
                Log.info("\nPopulating Facility[" + facility.getHotelId() + "," + facility.getFacilityId() + "] in Facility Object");
                facilitiesList.add(facility);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return facilitiesList;
        }

    }


    public Facility fetchFacilityByFacilityId(String hotelId,String facilityId) throws HMSException {
        Facility facility = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(FacilityDBQueries.FETCH_FACILITY_BY_FACILITY_ID);
            stmt.setString(1, hotelId);
            stmt.setString(2, facilityId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            facility = populateFacility(rs);

            if (null != facility)
                Log.info("\nPopulating Facility[" + facility.getHotelId()+","+facility.getFacilityId() + "] in Facility Object");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return facility;
        }

    }


    private Facility populateFacility(ResultSet rs) throws SQLException {

        if (rs.next() == false) {
            System.out.println("ResultSet is empty in Java");
            return null;
        } else {
                String facilityId = rs.getString("FACILITY_ID");
                String hotelId = rs.getString("HOTEL_ID");
                String facilityName = rs.getString("FACILITY_NAME");
                String facilityDescription = rs.getString("FACILITY_DESCRIPTION");
                String isFacilityAvailable = rs.getString("IS_AVAILABLE");
                String isFacilityChargeable = rs.getString("IS_CHARGEABLE");

                Boolean isAvailable = HMSUtilAPI.getBooleanValueFromString(isFacilityAvailable);
                Boolean isChargeable = HMSUtilAPI.getBooleanValueFromString(isFacilityChargeable);

                Double facilityCharges = rs.getDouble("FACILITY_CHARGES");


                Date dateCreated = HMSUtilAPI.convertTimestampToDate(rs.getTimestamp("DATE_CREATED"));
                Date dateUpdated = HMSUtilAPI.convertTimestampToDate(rs.getTimestamp("DATE_UPDATED"));


                FacilityType facilityType = FacilityType.convertIntToFacilityType(rs.getInt("FACILITY_TYPE"));

                return new Facility(hotelId, facilityId, facilityName, facilityDescription,
                        facilityType, isAvailable,isChargeable, facilityCharges,dateCreated,dateUpdated,null);
            }
        }
}
