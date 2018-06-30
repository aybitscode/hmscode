package com.aybits.hms.func.room.dao;

import com.aybits.hms.func.room.beans.RoomCategory;

public class RoomCategoryDAO {


    public Boolean addRoomCategory(RoomCategory roomCategory){
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
