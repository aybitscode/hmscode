package com.aybits.hms.func.room.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProvider;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.room.beans.Room;
import com.aybits.hms.func.room.dao.RoomDAO;

import java.util.List;

public class RoomAPI extends HMSAPIProviderImpl {

    private RoomDAO roomDAO = new RoomDAO();

    @Override
    public Object init(Object object) {
        return null;
    }
    @Override
    public Object process(Object object) {
        return null;
    }


    public List<Room> fetchRoomsByHotel(String hotelId) {
        List<Room> rooms = null;
        try{
            rooms = roomDAO.fetchRoomsByHotelId(hotelId);
        }catch(HMSException e){
            throw new HMSException(HMSErrorCodes.INVALID_ROOM_ATTRIBUTES,"Room does not exist for this hotel");
        }finally{
            return rooms;
        }
    }

    public Room fetchRoomDetails(String roomId){
        Room room = null;
        try{
            room = roomDAO.fetchRoomByRoomId(roomId);
        }catch(Exception e){
            throw new HMSException(HMSErrorCodes.INVALID_ROOM_ATTRIBUTES,"Invalid Room ID");
        }finally{
            return room;
        }

    }


    @Override
    public Boolean validate(Object object) throws HMSException {
        return null;
    }

}
