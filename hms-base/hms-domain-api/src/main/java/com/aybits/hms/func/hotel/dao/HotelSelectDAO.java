package com.aybits.hms.func.hotel.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelAttributes;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelSelectDAO {


    static Logger Log = Logger.getLogger(HotelSelectDAO.class);
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public Hotel fetchHotelByEmployeeId(String employeeId) throws HMSException {
        Hotel hotel = new Hotel();

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_HOTEL_BY_EMPLOYEE_ID);
            stmt.setString(1, employeeId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            hotel = populateHotel(rs);
            Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");

        } catch (Exception npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return hotel;
        }
    }

    public Hotel fetchHotelByEmployeeLogin(String employeeLogin) throws HMSException {
        Hotel hotel = null;

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_HOTEL_BY_EMPLOYEE_LOGIN);
            stmt.setString(1, employeeLogin);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            hotel = populateHotel(rs);
            Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return hotel;
        }
    }

    public List<Hotel> fetchAllHotels() throws HMSException {
        List<Hotel> hotels = new ArrayList<Hotel>();
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_ALL_HOTELS);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Hotel hotel = populateHotel(rs);
                hotels.add(hotel);
            }

        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return hotels;
        }
    }


    public Hotel fetchHotelByHotelId(String hotelId) throws HMSException {
        Hotel hotel = null;

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_HOTEL_BY_HOTELID);
            stmt.setString(1, hotelId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            hotel = populateHotel(rs);

            if (null != hotel)
                Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return hotel;
        }
    }

    public Hotel fetchHotelByContactDetails(String primaryEmail, String primaryPhone, String primaryMobileNumber) throws HMSException {
        Hotel hotel = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_HOTEL_BY_CONTACT_DETAILS);
            stmt.setString(1, primaryEmail);
            stmt.setString(2, primaryPhone);
            stmt.setString(3, primaryMobileNumber);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            hotel = populateHotel(rs);

            if (null != hotel)
                Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return hotel;
        }

    }


    private Hotel populateHotel(ResultSet rs) throws SQLException {

        if (rs.next() == false) {
            System.out.println("ResultSet is empty in Java");
            return null;
        } else {

            do {
                String hotelId = rs.getString("HOTEL_ID");
                String hotelName = rs.getString("HOTEL_NAME");
                String hotelRating = rs.getString("HOTEL_RATING");
                String hotelLogo = rs.getString("HOTEL_LOGO");
                String hotelRoomDoorNoFormat = rs.getString("HOTEL_ROOM_DOORNO_FORMAT");

                Address hotelAddress = (Address) HMSJSONParser.convertJSONToObject(rs.getString("HOTEL_ADDRESS"), Address.class);


                Integer hotelBedCount = rs.getInt("HOTEL_BED_COUNT");
                Integer hotelStaffCount = rs.getInt("HOTEL_STAFF_COUNT");
                Integer hotelRoomCount = rs.getInt("HOTEL_ROOM_COUNT");
                Integer hotelStatus = rs.getInt("HOTEL_STATUS");

                String primaryEmail = rs.getString("HOTEL_PRIMARY_EMAIL");
                String secondaryEmail = rs.getString("HOTEL_SECONDARY_EMAIL");
                String primaryPhone = rs.getString("HOTEL_PRIMARY_PHONE");
                String secondaryPhone = rs.getString("HOTEL_SECONDARY_PHONE");
                String primaryMobile = rs.getString("HOTEL_PRIMARY_MOBILE");
                String secondaryMobile = rs.getString("HOTEL_SECONDARY_MOBILE");
                String faxNumber = rs.getString("HOTEL_FAX_NUMBER");

                ContactDetails hotelContactDetails = new ContactDetails(primaryEmail, primaryPhone, secondaryEmail, secondaryPhone, faxNumber, primaryMobile, secondaryMobile);

                HotelAttributes hotelAttributes = new HotelAttributes(hotelName,
                        hotelRating,
                        hotelLogo,
                        hotelRoomDoorNoFormat,
                        hotelRoomCount,
                        hotelStaffCount,
                        hotelBedCount,
                        hotelAddress,
                        hotelContactDetails);

                return new Hotel(hotelId, hotelAttributes, Status.convertIntToStatus(hotelStatus));

            } while (rs.next());
        }
    }


}
