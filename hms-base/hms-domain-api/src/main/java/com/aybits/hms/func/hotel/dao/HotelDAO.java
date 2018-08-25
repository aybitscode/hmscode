package com.aybits.hms.func.hotel.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelAttributes;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class HotelDAO {

    static Logger Log = Logger.getLogger(HotelDAO.class);

    private Connection connection = DBConnection.getDBConnection();




    public Boolean addHotel(Hotel hotel) throws HMSException {

        Boolean isHotelAdditionSuccessful = false;
        try (PreparedStatement ps = connection.prepareStatement(HotelDBQueries.INSERT_NEW_HOTEL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, hotel.getHotelAttributes().getHotelName());
            ps.setString(2, hotel.getHotelAttributes().getHotelAddress().toString());
            ps.setString(3,hotel.getHotelAttributes().getHotelContactDetails().toString());
            ps.setString(4, hotel.getHotelAttributes().getHotelRating());
            ps.setString(5, hotel.getHotelAttributes().getHotelLogo());
            ps.setString(6, hotel.getHotelAttributes().getRoomDoorNoFormat());
            ps.setInt(7, hotel.getHotelAttributes().getEmployeeCount());
            ps.setInt(8, hotel.getHotelAttributes().getRoomCount());
            ps.setInt(9, hotel.getHotelAttributes().getTotalBeds());
            ps.setInt(10,hotel.getHotelStatus().getStatusAsInt());

            ps.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int numRowsAffected = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long hotelId = rs.getLong(1);
                    hotel.setHotelId(hotelId.toString());
                    isHotelAdditionSuccessful = true;
                    connection.commit();
                }
            } catch (SQLException s) {
                s.printStackTrace();
            }catch (NullPointerException npe) {
                throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            return isHotelAdditionSuccessful;
        }

    }

    public Hotel fetchHotelByEmployeeId(String employeeId) throws HMSException {

        Hotel hotel = new Hotel();
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_HOTEL_BY_EMPLOYEE_ID);
            stmt.setString(1, employeeId);
            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            hotel = populateHotel(rs);
            Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");
            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            return hotel;
        }
    }

    public Hotel fetchHotelByEmployeeLogin(String employeeLogin) throws HMSException {

        Hotel hotel = null;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_HOTEL_BY_EMPLOYEE_LOGIN);
            stmt.setString(1, employeeLogin);
            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            hotel = populateHotel(rs);
            Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");
            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            return hotel;
        }
    }

    public List<Hotel> fetchAllHotels() throws HMSException {

        List<Hotel> hotels = new ArrayList<Hotel>();
        ResultSet rs;
        PreparedStatement stmt;
        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_ALL_HOTELS);
            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Hotel hotel = populateHotel(rs);
                hotels.add(hotel);
            }


            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();

        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            return hotels;
        }
    }


    public Hotel fetchHotelByHotelId(String hotelId) throws HMSException {

        Hotel hotel = null;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.FETCH_HOTEL_BY_HOTELID);
            stmt.setString(1, hotelId);
            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            hotel = populateHotel(rs);

            if(null != hotel)
                Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");

            rs = requireNonNull(rs);
            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
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

                ContactDetails hotelContactDetails = (ContactDetails) HMSJSONParser.convertJSONToObject(rs.getString("HOTEL_CONTACT_DETAILS"), ContactDetails.class);
                Address hotelAddress = (Address) HMSJSONParser.convertJSONToObject(rs.getString("HOTEL_ADDRESS"), Address.class);
                HotelRegistrationData hotelRegistrationData = (HotelRegistrationData) HMSJSONParser.convertJSONToObject(rs.getString("HOTEL_REGISTRATION_DATA"), HotelRegistrationData.class);

                Integer hotelBedCount = rs.getInt("HOTEL_BED_COUNT");
                Integer hotelStaffCount = rs.getInt("HOTEL_STAFF_COUNT");
                Integer hotelRoomCount = rs.getInt("HOTEL_ROOM_COUNT");
                Integer hotelStatus    = rs.getInt("HOTEL_STATUS");


                HotelAttributes hotelAttributes = new HotelAttributes(hotelName,
                        hotelRating,
                        hotelLogo,
                        hotelRoomDoorNoFormat,
                        hotelRoomCount,
                        hotelStaffCount,
                        hotelBedCount,
                        hotelAddress,
                        hotelContactDetails);

                return new Hotel(hotelId, hotelAttributes, hotelRegistrationData, Status.convertIntToStatus(hotelStatus));

            } while (rs.next());
        }
    }

    public Boolean updateHotelStatus(String hotelId, Status status) throws HMSException {

        Boolean isHotelDisabled = false;
        PreparedStatement stmt = null;
        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.UPDATE_HOTEL_STATUS);
            stmt.setInt(1, status.getStatusAsInt());
            stmt.setString(2, hotelId);
            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                isHotelDisabled = true;
            }

            stmt = requireNonNull(stmt);
            stmt.close();


        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            return isHotelDisabled;
        }
    }

    public Boolean updateHotel(Hotel hotel) throws HMSException {


        Boolean isHotelUpdateSuccessful = false;
        PreparedStatement stmt = null;

        Hotel hotelFromDB = fetchHotelByHotelId(hotel.getHotelId());

        try{
            hotelFromDB = requireNonNull(hotelFromDB);
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.UPDATE_EXISTING_HOTEL);

            stmt.setString(1, hotel.getHotelAttributes().getHotelName());
            stmt.setString(2, hotel.getHotelAttributes().getHotelAddress().toString());
            stmt.setString(2, hotel.getHotelAttributes().getHotelContactDetails().toString());
            stmt.setString(3, hotel.getHotelRegistrationData().toString());
            stmt.setString(4, hotel.getHotelAttributes().getHotelRating());
            stmt.setString(5, hotel.getHotelAttributes().getHotelLogo());
            stmt.setString(6, hotel.getHotelAttributes().getRoomDoorNoFormat());
            stmt.setInt(7, hotel.getHotelAttributes().getEmployeeCount());
            stmt.setInt(8, hotel.getHotelAttributes().getRoomCount());
            stmt.setInt(9, hotel.getHotelAttributes().getTotalBeds());
            stmt.setString(10,hotelFromDB.getHotelId());
            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int rowsAffected = stmt.executeUpdate();
            if (1 == rowsAffected) {
                isHotelUpdateSuccessful = true;
            }

            Log.info("\nHotel[" + hotel.getHotelId() + "] successfully updated");
            stmt = requireNonNull(stmt);
            stmt.close();


        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {

                return isHotelUpdateSuccessful;
        }

    }


}
