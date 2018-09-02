package com.aybits.hms.func.hotel.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.beans.Address;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
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

    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();
    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public String addHotel(Hotel hotel) throws HMSException {
        Boolean isHotelAdditionSuccessful = false;
        String hotelId = null;

        try {
            connection = DBCPConnection.getDBConnection();
            stmt = connection.prepareStatement(HotelDBQueries.INSERT_NEW_HOTEL);
            connection.setAutoCommit(false);

            String keyPrefix = "H";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey("hotel_id","hms_hotel");


            hotelId = hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix);
            hotel.setHotelId(hotelId);

            stmt.setString(1,hotelId);
            stmt.setString(2, hotel.getHotelAttributes().getHotelName());
            stmt.setString(3, hotel.getHotelAttributes().getHotelAddress().toString());

            String primaryEmail = hotel.getHotelAttributes().getHotelContactDetails().getPrimaryEmail();
            stmt.setString(4, primaryEmail);
            String secondaryEmail = hotel.getHotelAttributes().getHotelContactDetails().getSecondaryEmail();
            stmt.setString(5, secondaryEmail);
            String primaryPhone = hotel.getHotelAttributes().getHotelContactDetails().getPrimaryPhone();
            stmt.setString(6, primaryPhone);
            String secondaryPhone = hotel.getHotelAttributes().getHotelContactDetails().getSecondaryPhone();
            stmt.setString(7, secondaryPhone);
            String primaryMobile = hotel.getHotelAttributes().getHotelContactDetails().getPrimaryMobileNumber();
            stmt.setString(8, primaryMobile);
            String secondaryMobile = hotel.getHotelAttributes().getHotelContactDetails().getSecondaryMobileNumber();
            stmt.setString(9, secondaryMobile);
            String faxNumber = hotel.getHotelAttributes().getHotelContactDetails().getFaxNumber();
            stmt.setString(10, faxNumber);

            stmt.setString(11, hotel.getHotelAttributes().getHotelRating());
            stmt.setString(12, hotel.getHotelAttributes().getHotelLogo());
            stmt.setString(13, hotel.getHotelAttributes().getRoomDoorNoFormat());
            stmt.setInt(14, hotel.getHotelAttributes().getEmployeeCount());
            stmt.setInt(15, hotel.getHotelAttributes().getRoomCount());
            stmt.setInt(16, hotel.getHotelAttributes().getTotalBeds());
            stmt.setInt(17, hotel.getHotelStatus().getStatusAsInt());

            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int numRowsAffected = stmt.executeUpdate();

            connection.commit();
            Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");
        } catch (SQLException e) {
            hotelId = null;
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + e.getMessage());
        } catch (NullPointerException npe) {
            hotelId = null;
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return hotelId;
        }
    }

    public String addHotelRegistrationData(HotelRegistrationData hotelRegistrationData) throws HMSException{


        Boolean isHotelRegistrationDataAdded = false;
        String hotelRegistrationId = null;
        try{
            connection = DBCPConnection.getDBConnection();
            stmt = connection.prepareStatement(HotelDBQueries.INSERT_NEW_HOTEL_REGISTRATION_DATA);
            connection.setAutoCommit(false);
            stmt.setString(1,hotelRegistrationData.getHotelId());

            String keyPrefix = "HREG";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey("hotel_registration_id","hms_hotel_registration_id");

            stmt.setString(2,hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix));
            stmt.setString(3,hotelRegistrationData.getBuildingPermitNo());
            stmt.setString(4,hotelRegistrationData.getFireSafetyPermitNo());
            stmt.setString(5,hotelRegistrationData.getPoliceLicenseNo());
            stmt.setString(6,hotelRegistrationData.getHealthTradeLicenseNo());
            stmt.setString(7,hotelRegistrationData.getLiquorLicenseNo());
            stmt.setString(8,hotelRegistrationData.getFssaiLicenseNo());
            stmt.setString(9,hotelRegistrationData.getGstNo());
            stmt.setString(10,hotelRegistrationData.getEsiRegistrationNo());
            stmt.setString(11,hotelRegistrationData.getPfRegistrationNo());

        }catch (SQLException e) {
            hotelRegistrationId = null;
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + e.getMessage());
        } catch (NullPointerException npe) {
            hotelRegistrationId = null;
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally{
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return hotelRegistrationId;
        }
    }

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

    public Boolean updateHotelStatus(String hotelId, Status status) throws HMSException {
        Boolean isHotelDisabled = false;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.UPDATE_HOTEL_STATUS);
            stmt.setInt(1, status.getStatusAsInt());
            stmt.setString(2, hotelId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                isHotelDisabled = true;
            }

        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instanstiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isHotelDisabled;
        }
    }

    public Boolean updateHotel(Hotel hotel) throws HMSException {
        Boolean isHotelUpdateSuccessful = false;
        Hotel hotelFromDB = fetchHotelByHotelId(hotel.getHotelId());

        try {
            hotelFromDB = requireNonNull(hotelFromDB);
            connection = DBCPConnection.getDBConnection();
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
            stmt.setString(10, hotelFromDB.getHotelId());
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            int rowsAffected = stmt.executeUpdate();
            if (1 == rowsAffected) {
                isHotelUpdateSuccessful = true;
            }

            Log.info("\nHotel[" + hotel.getHotelId() + "] successfully updated");

        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isHotelUpdateSuccessful;
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







    




}
