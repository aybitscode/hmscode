package com.aybits.hms.func.room.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.func.room.beans.RoomCategory;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class  RoomCategoryDAO {

    static Logger Log = Logger.getLogger(RoomCategoryDAO.class);

    private Connection connection = DBConnection.getDBConnection();

    public Boolean addRoomCategory(RoomCategory roomCategory){

        Boolean isHotelAdditionSuccessful = false;

      /*  try (PreparedStatement ps = connection.prepareStatement(RoomCategoryDBQueries.INSERT_NEW_ROOM_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
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
*/

        return true;
    }

    public Boolean updateRoomCategory(RoomCategory roomCategory){
        return true;
    }

    public RoomCategory fetchRoomCategory(Integer categoryId){
        return null;
    }

    public Boolean deleteRoomCategory(Integer categoryId){
        return true;
    }

    public Boolean disableRoomCategory(Integer categoryId){
        return true;
    }
}
