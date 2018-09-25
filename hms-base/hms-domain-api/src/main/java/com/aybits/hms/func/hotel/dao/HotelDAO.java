package com.aybits.hms.func.hotel.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.beans.Status;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.util.Objects.requireNonNull;

public class HotelDAO {

    static Logger Log = Logger.getLogger(HotelDAO.class);

    private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();
    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
    HotelSelectDAO hotelSelectDAO = new HotelSelectDAO();

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public String addHotel(Hotel hotel) throws HMSException {
        String hotelId = null;

        try {
            connection = DBCPConnection.getDBConnection();
            stmt = connection.prepareStatement(HotelDBQueries.INSERT_NEW_HOTEL);
            connection.setAutoCommit(false);

            String keyPrefix = "H";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey("hotel_id","hms_hotel");
            Boolean isRandomSaltRequired = false;

            hotelId = hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix,isRandomSaltRequired);
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


        String hotelRegistrationId = null;
        try{
            connection = DBCPConnection.getDBConnection();
            stmt = connection.prepareStatement(HotelDBQueries.INSERT_HOTEL_REGISTRATION_DATA);
            connection.setAutoCommit(false);

            String keyPrefix = "HREG";
            String keySuffix = hmsCommonDAO.getNextPrimaryKey("registration_data_id","hms_hotel_registration_data");

            Boolean isRandomSaltRequired = false;
            hotelRegistrationId = hmsRandomAPI.generatePrimaryKey(keyPrefix,keySuffix,isRandomSaltRequired);
            hotelRegistrationData.setHotelRegistrationId(hotelRegistrationId);

            stmt.setString(1,hotelRegistrationData.getHotelId());
            stmt.setString(2,hotelRegistrationData.getHotelRegistrationId());
            stmt.setString(3,hotelRegistrationData.getBuildingPermitNo());
            stmt.setString(4,hotelRegistrationData.getFireSafetyPermitNo());
            stmt.setString(5,hotelRegistrationData.getPoliceLicenseNo());
            stmt.setString(6,hotelRegistrationData.getHealthTradeLicenseNo());
            stmt.setString(7,hotelRegistrationData.getLiquorLicenseNo());
            stmt.setString(8,hotelRegistrationData.getFssaiLicenseNo());
            stmt.setString(9,hotelRegistrationData.getGstNo());
            stmt.setString(10,hotelRegistrationData.getEsiRegistrationNo());
            stmt.setString(11,hotelRegistrationData.getPfRegistrationNo());
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            stmt.executeUpdate();
            connection.commit();

        }catch (SQLException e) {
            e.printStackTrace();
            hotelRegistrationId = null;
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + e.getMessage());
        }catch (NullPointerException npe) {
            hotelRegistrationId = null;
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally{
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return hotelRegistrationId;
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

            connection.commit();

        } catch (SQLException sqle) {
            connection.rollback();
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            connection.rollback();
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isHotelDisabled;
        }
    }

    public Boolean updateHotel(Hotel hotel) throws HMSException {
        Boolean isHotelUpdateSuccessful = false;
        Hotel hotelFromDB = hotelSelectDAO.fetchHotelByHotelId(hotel.getHotelId());

        try {
            hotelFromDB = requireNonNull(hotelFromDB);
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(HotelDBQueries.UPDATE_EXISTING_HOTEL);

            stmt.setString(1, hotel.getHotelAttributes().getHotelName());
            stmt.setString(2, hotel.getHotelAttributes().getHotelAddress().toString());
            stmt.setString(3, hotel.getHotelAttributes().getHotelContactDetails().toString());
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
            connection.commit();
            Log.info("\nHotel[" + hotel.getHotelId() + "] successfully updated");

        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            connection.rollback();
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            connection.rollback();
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return isHotelUpdateSuccessful;
        }

    }










    




}
