package com.aybits.hms.func.room.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSRandomAPI;
import com.aybits.hms.func.common.dao.HMSCommonDAO;
import com.aybits.hms.func.room.beans.RoomCategory;
public class  RoomCategoryDAO {

    static Logger Log = Logger.getLogger(RoomCategoryDAO.class);
    private HMSCommonDAO hmsCommonDAO = new HMSCommonDAO();
	private HMSRandomAPI hmsRandomAPI = new HMSRandomAPI();
	
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
    private Connection connection = DBCPConnection.getDBConnection();

    public Boolean addRoomCategory(RoomCategory roomCategory) throws HMSException {

		boolean isRoomCatAdded = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBCPConnection.getDBConnection();
			ps = connection.prepareStatement(RoomCategoryDBQueries.ADD_ROOM_CATEGORY);

			String keyPrefix = "HMRC";
			String keySuffix = hmsCommonDAO.getNextPrimaryKey("hotel_id", "category_id", "hms_room_category");

			String categoryId = hmsRandomAPI.generatePrimaryKey(keyPrefix, keySuffix, false);

			roomCategory.setCategoryId(categoryId);

			connection.setAutoCommit(false);
			ps.setString(1, roomCategory.getHotelId());
			ps.setString(2, roomCategory.getCategoryId());
			ps.setString(3, roomCategory.getCategoryName());
			ps.setString(4, roomCategory.getCategoryDescription());
			ps.setString(5, roomCategory.getBedCount());
			
			Double price= roomCategory.getRoomPrice().getRoomBasePrice();
			ps.setDouble(6, price);
			
			ps.setInt(7, roomCategory.getRoomCount());
			ps.setInt(8, roomCategory.getRoomCapacity().getRoomCapacity());
			ps.setInt(9, roomCategory.getAdultOccupancy());
			ps.setInt(10, roomCategory.getChildOccupancy());
			// ps.setstring(6, roomCategory.get);
			ps.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
			int numRowsAffected = ps.executeUpdate();
			if (numRowsAffected > 0)
				isRoomCatAdded = true;

		} catch (SQLException e) {
			Log.error("error occurred", e);
		//	throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "sql Exception occurred::" + e.getMessage());
		} catch (NullPointerException npe) {
		//	throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
		} finally {
			DBCPConnection.closeDBConnection(null, ps, connection);
		}
		return isRoomCatAdded;
    }

    public Boolean updateRoomCategory(String hotelId, String categoryId){
    	Boolean isUpdateSuccessful = false;
		try {
			connection = DBCPConnection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(RoomCategoryDBQueries.UPDATE_ROOM_CATEGORY);
			stmt.setString(1, hotelId);
			stmt.setString(2, categoryId);
			stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated > 0) {
				isUpdateSuccessful = true;
			}

			connection.commit();

		} catch (SQLException sqle) {
			connection.rollback();
			// TODO Auto-generated catch block
		//	throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occurred");
		} catch (NullPointerException npe) {
			connection.rollback();
		//	throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
		} finally {
			DBCPConnection.closeDBConnection(null, stmt, connection);
			return isUpdateSuccessful;
		}
    }

    public RoomCategory fetchRoomCategory(Integer categoryId){
        return null;
    }

    public Boolean deleteRoomCategory(Integer categoryId) {
		try {
			connection = DBCPConnection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(RoomCategoryDBQueries.DELETE_ROOM_CATEGORY);

			stmt.setInt(1, categoryId);
			stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
		//	int rowsDeleted = stmt.executeQuery();

			connection.commit();

		} catch (SQLException sqle) {
			connection.rollback();
			// TODO Auto-generated catch block
		//	throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occurred");
		} catch (NullPointerException npe) {
			connection.rollback();
		//	throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
		} finally {
			DBCPConnection.closeDBConnection(null, stmt, connection);
			return true;
		}
    }

    public Boolean disableRoomCategory(Integer categoryId){
        return true;
    }
}
