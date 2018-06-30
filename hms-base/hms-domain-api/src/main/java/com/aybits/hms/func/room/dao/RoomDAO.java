package com.aybits.hms.func.room.dao;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.room.beans.Room;
import com.aybits.hms.func.room.beans.RoomCategory;
import com.aybits.hms.func.room.beans.RoomStatus;

import java.util.List;

public class RoomDAO {


    public List<Room> fetchRoomsByHotelId(String hotelId) throws HMSException {
        return null;
    }

    public Room fetchRoomByRoomId(String roomId) throws HMSException{
        return null;
    }

    public Boolean addRoom(Room room) throws HMSException{
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
