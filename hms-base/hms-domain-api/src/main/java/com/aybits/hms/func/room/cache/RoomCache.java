package com.aybits.hms.func.room.cache;

import com.aybits.hms.func.room.beans.Room;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RoomCache {

    private static ConcurrentHashMap<String, Room> roomCache = new ConcurrentHashMap<String,Room>();
    private static HashSet<String> roomIds = new HashSet<>();

    public Boolean initCache(String hotelId){
        return false;
    }

    public void addRoom(Room room) {
        if (roomCache.get(String.valueOf(room.getRoomId())) == null) {
            roomIds.add(room.getRoomId());
            roomCache.put(room.getRoomId(), room);
        }
    }

    public void updateRoom(Room room) {
        String roomId = room.getRoomId();
        roomCache.remove(roomId);
        roomCache.put(roomId, room);
    }


    public Room getRoomById(String roomId) {
        Room room = roomCache.get(roomId);
        if (room != null)
            return room;
        else
            return null;
    }


    public List<Room> getAllRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.addAll(roomCache.values());
        return rooms;
    }

    public List<String> getAllRoomIds() {
        ArrayList<String> roomIds = new ArrayList<>();
        roomIds.addAll(roomCache.keySet());
        return roomIds;
    }

    public static ConcurrentHashMap<String,Room> getRoomCache(){
        return roomCache;
    }
}
