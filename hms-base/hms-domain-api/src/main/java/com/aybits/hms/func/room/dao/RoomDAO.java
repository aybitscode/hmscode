package com.aybits.hms.func.room.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.customer.dao.CustomerDBQueries;
import com.aybits.hms.func.room.beans.Room;
import com.aybits.hms.func.room.beans.RoomCategory;
import com.aybits.hms.func.room.beans.RoomStatus;

import java.sql.*;
import java.util.List;

public class RoomDAO {

    Connection connection  = DBConnection.getDBConnection();

    public List<Room> getRoomsByHotelId(String hotelId) throws HMSException {
        return null;
    }

    public Room getRoomByRoomId(String roomId) throws HMSException{
        return null;
    }

    public Boolean addRoom(Room room) throws HMSException{
/*
        PreparedStatement pst;
        Boolean additionStatus = false;
        int i  = 0;
        try {

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(RoomDBQueries.ADD_ROOM);
            pst.setString(++i, generateRoomId());
            pst.setString(++i, room.getFirstName());
            pst.setString(++i, customer.getLastName());

            pst.setString(++i, customer.getContactDetails().getPrimaryEmail());
            pst.setString(++i, customer.getContactDetails().getPrimaryPhone());
            pst.setString(++i, customer.getCustomerAddress().toString());
            pst.setString(++i, customer.getIdentificationParam().getIdentifierValue().toString());
            pst.setString(++i, customer.getPaymentParams().getPaymentType().getPaymentTypeAsString());
            pst.setInt(++i, customer.getHotelId());
            pst.setString(++i,customer.getStatus().getStatusAsString());
            pst.setDate(++i, new java.sql.Date(customer.getDateCreated().getTime()));


            int s=pst.executeUpdate();
            connection.commit();
            if(s>0){
                additionStatus = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return additionStatus;*/

        return true;
    }

    public Boolean updateRoom(Room room) throws HMSException{
        return true;
    }

    public Boolean updateRoomStatus(RoomStatus roomStatus,String hotelId,String roomId) throws HMSException{
        return true;
    }

    public Boolean deleteRoom(Room room) throws HMSException{
        return true;
    }

    public Boolean fetchRoomStatus(String hotelId,String roomId) throws HMSException{
        return true;
    }




}
