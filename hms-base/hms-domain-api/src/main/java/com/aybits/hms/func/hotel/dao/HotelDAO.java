package com.aybits.hms.func.hotel.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.HMSAddress;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.dao.FacilityDAO;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelAttributes;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
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

    private static Connection connection = DBConnection.getDBConnection();

    private static final String FETCH_HOTEL_BY_HOTELID = "select * from hms_hotel where hotel_id = ?";
    private static final String FETCH_ALL_HOTELS = "select * from hms_hotel";
    private static final String FETCH_HOTEL_BY_EMPLOYEE_LOGIN = "select * from hms_hotel where hotel_id = (select hotel_id from " +
            "hms_employee where login_id = ?)";
    private static final String FETCH_HOTEL_BY_EMPLOYEE_ID = "select * from hms_hotel where hotel_id = (select hotel_id from " +
            "hms_employee where employee_id = ?)";
    private static final String UPDATE_HOTEL_STATUS = "update hms_hotel set hotel_status = ? where hotel_id = ?";
    private static final String INSERT_NEW_HOTEL = "insert into hms_hotel(" +
            "hotel_id," +
            "hotel_name," +
            "hotel_address," +
            "hotel_registration_data," +
            "hotel_rating," +
            "hotel_logo," +
            "hotel_room_doorno_format" +
            "hotel_staff_count," +
            "hotel_room_count," +
            "hotel_bed_count)" +
            "values(?,?,?,?,?,?,?,?,?,?)";

    public static final String UPDATE_EXISTING_HOTEL = "update hms_hotel set" +
            "hotel_name = ?" +
            "hotel_address = ?" +
            "hotel_registration_data = ?" +
            "hotel_rating = ?" +
            "hotel_logo = ?" +
            "hotel_room_doorno_format = ?" +
            "hotel_staff_count = ?" +
            "hotel_room_count = ?" +
            "hotel_bed_count = ?" +
            " where hotel_id = ?";



    public Boolean addHotel(Hotel hotel) throws HMSException {

        Boolean isHotelAdditionSuccessful = false;
        PreparedStatement stmt;

        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(INSERT_NEW_HOTEL);
            stmt.setString(1, hotel.getHotelId());
            stmt.setString(2, hotel.getHotelAttributes().getHotelName());
            stmt.setString(3, hotel.getHotelAttributes().getHotelAddress().toString());
            stmt.setString(4, hotel.getHotelRegistrationData().toString());
            stmt.setString(5, hotel.getHotelAttributes().getHotelRating());
            stmt.setString(6, hotel.getHotelAttributes().getHotelLogo());
            stmt.setString(7, hotel.getHotelAttributes().getRoomDoorNoFormat());
            stmt.setInt(8, hotel.getHotelAttributes().getEmployeeCount());
            stmt.setInt(9, hotel.getHotelAttributes().getRoomCount());
            stmt.setInt(10, hotel.getHotelAttributes().getTotalBeds());

            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
            int rowsAffected = stmt.executeUpdate();
            if (1 == rowsAffected) {
                isHotelAdditionSuccessful = true;
            }

            Log.info("\nHotel[" + hotel.getHotelId() + "] successfully added to the DB");
            stmt = requireNonNull(stmt);
            stmt.close();

        } catch (SQLException se) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
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
            stmt = connection.prepareStatement(FETCH_HOTEL_BY_EMPLOYEE_ID);
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
            stmt = connection.prepareStatement(FETCH_HOTEL_BY_EMPLOYEE_LOGIN);
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
            stmt = connection.prepareStatement(FETCH_ALL_HOTELS);
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
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
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
            stmt = connection.prepareStatement(FETCH_HOTEL_BY_HOTELID);
            stmt.setString(1, hotelId);
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

    private Hotel populateHotel(ResultSet rs) throws SQLException {

        String hotelId = rs.getString("HOTEL_ID");
        String hotelName = rs.getString("HOTEL_NAME");
        String hotelRating = rs.getString("HOTEL_RATING");
        String hotelLogo = rs.getString("HOTEL_LOGO");
        String hotelRoomDoorNoFormat = rs.getString("HOTEL_ROOM_DOORNO_FORMAT");

        HMSAddress hotelAddress = (HMSAddress) HMSJSONParser.convertJSONToObject(rs.getString("HOTEL_ADDRESS"), HMSAddress.class);
        HotelRegistrationData hotelRegistrationData = (HotelRegistrationData) HMSJSONParser.convertJSONToObject(rs.getString("HOTEL_REGISTRATION_DATA"), HotelRegistrationData.class);

        Integer hotelBedCount = rs.getInt("HOTEL_BED_COUNT");
        Integer hotelStaffCount = rs.getInt("HOTEL_STAFF_COUNT");
        Integer hotelRoomCount = rs.getInt("HOTEL_ROOM_COUNT");

        HotelAttributes hotelAttributes = new HotelAttributes(hotelName,
                hotelRating,
                hotelLogo,
                hotelRoomDoorNoFormat,
                hotelRoomCount,
                hotelStaffCount,
                hotelBedCount,
                hotelAddress);


        FacilityDAO facilityDAO = new FacilityDAO();
        List<Facility> hotelFacilities = facilityDAO.fetchFacilitiesByType(HMSAPIServiceConstants.HOTEL_FACILITY);

        return new Hotel(hotelId, hotelAttributes, hotelRegistrationData, hotelFacilities);
    }

    public Boolean updateHotelStatus(String hotelId, Status status) throws HMSException {

        Boolean isHotelDisabled = false;
        PreparedStatement stmt = null;
        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(UPDATE_HOTEL_STATUS);
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
            stmt = connection.prepareStatement(UPDATE_EXISTING_HOTEL);

            stmt.setString(1, hotel.getHotelAttributes().getHotelName());
            stmt.setString(2, hotel.getHotelAttributes().getHotelAddress().toString());
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
