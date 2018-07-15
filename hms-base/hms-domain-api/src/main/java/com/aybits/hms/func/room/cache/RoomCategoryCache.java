package com.aybits.hms.func.room.cache;

import com.aybits.hms.func.room.beans.RoomCategory;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.List;

public class RoomCategoryCache {


    private static ConcurrentHashMap<String, RoomCategory> roomCategoryCache = new ConcurrentHashMap<>();
    private static HashSet<String> roomCategoryIds = new HashSet<>();

    public Boolean initCache(){
        return false;
    }

    public void addRoomCategory(RoomCategory roomCategory) {
        if (roomCategoryCache.get(String.valueOf(roomCategory.getCategoryId())) == null) {
            roomCategoryIds.add(roomCategory.getCategoryId());
            roomCategoryCache.put(roomCategory.getCategoryId(), roomCategory);
        }
    }

    public void updateRoomCategory(RoomCategory roomCategory) {
        String roomCategoryId = roomCategory.getCategoryId();
        roomCategoryCache.remove(roomCategoryId);
        roomCategoryCache.put(roomCategoryId, roomCategory);
    }


    public RoomCategory getRoomCategoryById(String roomCategoryId) {
        RoomCategory roomCategory = roomCategoryCache.get(roomCategoryId);
        if (roomCategory != null)
            return roomCategory;
        else
            return null;
    }


    public List<RoomCategory> getAllRoomCategorys() {
        ArrayList<RoomCategory> roomCategorys = new ArrayList<>();
        roomCategorys.addAll(roomCategoryCache.values());
        return roomCategorys;
    }

    public List<String> getAllRoomCategoryIds() {
        ArrayList<String> roomCategoryIds = new ArrayList<>();
        roomCategoryIds.addAll(roomCategoryCache.keySet());
        return roomCategoryIds;
    }

    public static ConcurrentHashMap<String,RoomCategory> getRoomCategoryCache(){
        return roomCategoryCache;
    }

}
