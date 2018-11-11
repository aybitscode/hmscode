package com.aybits.hms.func.room.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.room.beans.Room;
import com.aybits.hms.func.room.dao.RoomDAO;
import org.json.JSONObject;

import java.util.List;

public class RoomAPI implements HmsAPI {

    private RoomDAO roomDAO = new RoomDAO();

    @Override
    public Object init(JSONObject object) {
        return null;
    }

    @Override
    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {
    }


    public List<Room> fetchRoomsByHotel(String hotelId) {
        List<Room> rooms = null;
        try{
            rooms = roomDAO.fetchRoomsByHotelId(hotelId);
        }catch(HMSRuntimeException e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_ROOM_ATTRIBUTES,"Room does not exist for this hotel"));
        }finally{
            return rooms;
        }
    }

    public Room fetchRoomDetails(String roomId){
        Room room = null;
        try{
            room = roomDAO.fetchRoomByRoomId(roomId);
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_ROOM_ATTRIBUTES,"Invalid Room ID"));
        }finally{
            return room;
        }

    }


    @Override
    public String fetch(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String fetchAll(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String update(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String disable(JSONObject json) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String delete(JSONObject json) throws HMSRuntimeException {
        return null;
    }

}
