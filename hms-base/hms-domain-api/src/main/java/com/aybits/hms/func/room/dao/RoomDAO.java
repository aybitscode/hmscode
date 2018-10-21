package com.aybits.hms.func.room.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.room.beans.Room;
import com.aybits.hms.func.room.beans.RoomStatus;

import java.sql.Connection;
import java.util.List;

public class RoomDAO {

    Connection connection  = null;

    public List<Room> fetchRoomsByHotelId(String hotelId) throws HMSException {
        return null;
    }

    public Room fetchRoomByRoomId(String roomId) throws HMSException{
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
            pst.setString(++i, customer.getCustomerFullName());

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
