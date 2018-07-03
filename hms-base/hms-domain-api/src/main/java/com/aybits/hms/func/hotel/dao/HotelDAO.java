package com.aybits.hms.func.hotel.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.HMSAddress;
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
import java.util.List;

public class HotelDAO {

    static Logger Log = Logger.getLogger(HotelDAO.class);

    private static Connection connection = DBConnection.getDBConnection();

    private static final String GET_HOTEL_BY_HOTELID = "select * from hms_hotel where hotel_id = ?";
    private static final String GET_HOTEL_BY_EMPLOYEE_LOGIN = "select * from hms_hotel where hotel_id = (select hotel_id from " +
            "hms_employee where login_id = ?)";
    private static final String GET_HOTEL_BY_EMPLOYEE_ID = "select * from hms_hotel where hotel_id = (select hotel_id from " +
            "hms_employee where employee_id = ?)";

    private static final String INSERT_NEW_HOTEL =  "insert into hms_hotel(" +
                                                "hotel_id," +
                                                "hotel_name," +
                                                "hotel_address," +
                                                "hotel_registration_data," +
                                                "hotel_rating," +
                                                "hotel_logo,"+
                                                "hotel_room_doorno_format" +
                                                "hotel_staff_count," +
                                                "hotel_room_count," +
                                                "hotel_bed_count)"+
                                                "values(?,?,?,?,?,?,?,?,?,?)";



    public Boolean insertHotelDetails(Hotel hotel) throws HMSException{

        Boolean isHotelAdditionSuccessful = false;

        if (connection == null) {
            throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
        }


        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            if (connection != null) {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(INSERT_NEW_HOTEL);
                stmt.setString(1, hotel.getHotelId());
                stmt.setString(2,hotel.getHotelAttributes().getHotelName());
                stmt.setString(3,hotel.getHotelAttributes().getHotelAddress().toString());
                stmt.setString(4,hotel.getHotelRegistrationData().toString());
                stmt.setString(5,hotel.getHotelAttributes().getHotelRating());
                stmt.setString(6,hotel.getHotelAttributes().getHotelLogo());
                stmt.setString(7,hotel.getHotelAttributes().getRoomDoorNoFormat());
                stmt.setInt(8,hotel.getHotelAttributes().getEmployeeCount());
                stmt.setInt(9,hotel.getHotelAttributes().getRoomCount());
                stmt.setInt(10,hotel.getHotelAttributes().getTotalBeds());

                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                int rowsAffected = stmt.executeUpdate();
                if (1 == rowsAffected){
                    isHotelAdditionSuccessful = true;
                }

                Log.info("\nHotel[" + hotel.getHotelId() + "] successfully added to the DB");

            } else {
                throw new HMSException(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE, "No DB Connections available");
            }
        }catch(SQLException se){

        }finally{
            return isHotelAdditionSuccessful;
        }

    }

    public Hotel fetchHotelByEmployeeId(String employeeId) throws HMSException {

        if (connection == null) {
            throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
        }

        Hotel hotel = new Hotel();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            if (connection != null) {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(GET_HOTEL_BY_EMPLOYEE_ID);
                stmt.setString(1, employeeId);
                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();

                hotel = populateHotel(rs);
                Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");

            } else {
                throw new HMSException(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE, "No DB Connections available");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return hotel;
        }
    }

    public Hotel fetchHotelByEmployeeLogin(String employeeLogin) throws HMSException {

        if (connection == null) {
            throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
        }

        Hotel hotel = new Hotel();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            if (connection != null) {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(GET_HOTEL_BY_EMPLOYEE_LOGIN);
                stmt.setString(1, employeeLogin);
                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();

                hotel = populateHotel(rs);
                Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");

            } else {
                throw new HMSException(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE, "No DB Connections available");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return hotel;
        }
    }

    public Hotel fetchHotelByHotelId(Integer hotelId) throws HMSException {
        if (connection == null) {
            throw new HMSException(HMSErrorCodes.DB_CONNECTION_FAILED);
        }

        Hotel hotel = new Hotel();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            if (connection != null) {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(GET_HOTEL_BY_HOTELID);
                stmt.setInt(1, hotelId);
                stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
                rs = stmt.executeQuery();

                hotel = populateHotel(rs);
                Log.info("\nPopulating Hotel[" + hotel.getHotelId() + "] in Hotel Object");

            } else {
                throw new HMSException(HMSErrorCodes.DB_NO_CONNECTIONS_AVAILABLE, "No DB Connections available");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return hotel;
        }
    }

    private Hotel populateHotel(ResultSet rs) throws SQLException {

        String hotelId                  = rs.getString("HOTEL_ID");
        String hotelName                = rs.getString("HOTEL_NAME");
        String hotelRating              = rs.getString("HOTEL_RATING");
        String hotelLogo                = rs.getString("HOTEL_LOGO");
        String hotelRoomDoorNoFormat    = rs.getString("HOTEL_ROOM_DOORNO_FORMAT");

        HMSAddress hotelAddress = (HMSAddress)HMSJSONParser.convertJSONToObject(rs.getString("HOTEL_ADDRESS"),HMSAddress.class);
        HotelRegistrationData hotelRegistrationData = (HotelRegistrationData)HMSJSONParser.convertJSONToObject(rs.getString("HOTEL_REGISTRATION_DATA"),HotelRegistrationData.class);
        List<Facility> facilities = null;

        Integer hotelBedCount = rs.getInt("HOTEL_BED_COUNT");
        Integer hotelStaffCount = rs.getInt("HOTEL_STAFF_COUNT");
        Integer hotelRoomCount  = rs.getInt("HOTEL_ROOM_COUNT");

        HotelAttributes hotelAttributes = new HotelAttributes(  hotelName,
                                                                hotelRating,
                                                                hotelLogo,
                                                                hotelRoomDoorNoFormat,
                                                                hotelRoomCount,
                                                                hotelStaffCount,
                                                                hotelBedCount,
                                                                hotelAddress);


        FacilityDAO facilityDAO = new FacilityDAO();
        List<Facility> hotelFacilities = facilityDAO.fetchFacilitiesByType(HMSAPIServiceConstants.HOTEL_FACILITY);

        return new Hotel(hotelId,hotelAttributes,hotelRegistrationData,hotelFacilities);
    }

    


}
