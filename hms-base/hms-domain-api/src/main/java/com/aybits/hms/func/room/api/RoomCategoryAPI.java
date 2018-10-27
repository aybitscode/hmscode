package com.aybits.hms.func.room.api;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import com.aybits.hms.func.room.beans.RoomCategory;
import com.aybits.hms.func.room.cache.RoomCategoryCache;
import com.aybits.hms.func.room.dao.RoomCategoryDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class RoomCategoryAPI extends HMSAPIProviderImpl {


    static Logger log = Logger.getLogger(RoomCategoryAPI.class);
    RoomCategoryCache roomCategoryCache = new RoomCategoryCache();
    RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAO();

    public Boolean addRoomCategory(RoomCategory roomCategory) throws HMSException {
        return true;
    }

    public List<RoomCategory> fetchAllRoomCategories(String hotelId){
        return null;
    }

    public RoomCategory fetchRoomCategoryById(String hotelId,String roomCategoryId){
        return null;    }

    public Boolean updateRoomCategory(RoomCategory roomCategory){
        return false;
    }

    public Boolean disableRoomCategory(RoomCategory roomCategory){
        return true;
    }

}
