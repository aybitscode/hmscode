package com.aybits.hms.func.room.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.amenity.beans.Amenity;
import com.aybits.hms.func.amenity.beans.AmenityType;
import com.aybits.hms.func.amenity.dao.AmenitySelectDAO;
import com.aybits.hms.func.facility.beans.Facility;
import com.aybits.hms.func.facility.dao.FacilitySelectDAO;
import com.aybits.hms.func.hotel.dao.HotelSelectDAO;
import com.aybits.hms.func.room.beans.RoomCategory;
import com.aybits.hms.func.service.beans.Service;
import com.aybits.hms.func.service.dao.ServiceSelectDAO;
import com.aybits.hms.func.voucher.beans.Voucher;
import com.aybits.hms.func.voucher.dao.VoucherSelectDAO;

public class RoomCategorySelectDAO {

	static Logger Log = Logger.getLogger(HotelSelectDAO.class);
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    
    public RoomCategory fetchRoomCategory(String categoryId) {

		RoomCategory roomCategory = null;
		try {
			connection = DBCPConnection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(RoomCategoryDBQueries.GET_ROOM_CATEGORY_BY_CATEGORY_ID);

			stmt.setString(1, categoryId);
			stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
			rs = stmt.executeQuery();
            
		//	roomCategory = populateRoomCategory(rs);
                

			connection.commit();

		} catch (SQLException sqle) {
			connection.rollback();
			// TODO Auto-generated catch block
			//throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occurred");
		} catch (NullPointerException npe) {
			connection.rollback();
		//	throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
		} finally {
			DBCPConnection.closeDBConnection(null, stmt, connection);
			return roomCategory;
		}
	}
    
    private RoomCategory populateRoomCategory(ResultSet rs) throws SQLException {
        
    	if (rs.next() == false) {
            System.out.println("ResultSet is empty in Java");
            return null;
        } else {
        	do {
        		String hotelId = rs.getString("hotel_id");
                String categoryId = rs.getString("room_category_id");
                String categoryName = rs.getString("category_name");
                String categoryDescription = rs.getString("category_description");
                Integer bedCount = rs.getInt("bed_count");

                Integer roomCount = rs.getInt("room_count");
                Integer roomCategoryPrice = rs.getInt("category_price");
                Integer roomCapacity = rs.getInt("room_capacity");
                Integer adultOccupancy = rs.getInt("adult_occupancy");
                Integer childOccupancy = rs.getInt("child_occupancy");
                String amenityId = rs.getString("AMENITY_ID");
               
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
                Amenity amenity = new Amenity(hotelId, amenityId, amenityName, amenityDescription,
                        isAvailable,isChargeable, amenityType, amenityCharges);
                
        	}while (rs.next());
        }
    	return new RoomCategory();
    }
    /*private RoomCategory populateRoomCategory(ResultSet rs) throws SQLException {
    
    	if (rs.next() == false) {
            System.out.println("ResultSet is empty in Java");
            return null;
        } else {
        	do {
        		
        		String hotelId = rs.getString("hotel_id");
                String categoryId = rs.getString("room_category_id");
                String categoryName = rs.getString("category_name");
                String categoryDescription = rs.getString("category_description");
                Integer bedCount = rs.getInt("bed_count");

                Integer roomCount = rs.getInt("room_count");
                Integer roomCategoryPrice = rs.getInt("category_price");
                Integer roomCapacity = rs.getInt("room_capacity");
                Integer adultOccupancy = rs.getInt("adult_occupancy");

                Integer childOccupancy = rs.getInt("child_occupancy");
               // String categoryAmenities = rs.getString("HOTEL_SECONDARY_EMAIL");
               // String categoryFacilities = rs.getString("HOTEL_PRIMARY_PHONE");
               // String categoryServices = rs.getString("HOTEL_SECONDARY_PHONE");
               // String categoryVouchers = rs.getString("HOTEL_PRIMARY_MOBILE");
                String isCategoryAvailable = rs.getString("is_category_available");
                
                return new  RoomCategory( hotelId, categoryId,
                         categoryName,  categoryDescription,
                         bedCount,  roomCount,
                         roomCategoryPrice,  roomCapacity,
                         adultOccupancy,  childOccupancy,
                         categoryAmenities, categoryFacilities,
                         categoryServices,  categoryVouchers,
                         isCategoryAvailable);
                
                CategoryAmenities
                
                AmenitySelectDAO amenitySelectDAO = new AmenitySelectDAO();
                Amenity amenity = new Amenity();
               // amenitySelectDAO.getAmenity(hotelId, amenity.getAmenityId());
                
                FacilitySelectDAO facilitySelectDAO = new FacilitySelectDAO();
                Facility facility;
               // facilitySelectDAO.getFacility(hotelId, facility.getFacilityId());
                
                ServiceSelectDAO serviceSelectDAO = new ServiceSelectDAO();
                Service service;
              //  serviceSelectDAO.getService(hotelId, service.getServiceId());
                
                VoucherSelectDAO voucherSelectDAO =  new VoucherSelectDAO();
                Voucher Voucher;
             //   voucherSelectDAO.fetchVoucher(hotelId, Voucher.getVoucherId());
                
                
                
                
                
        	 } while (rs.next());
        	}*/
   // }


}
