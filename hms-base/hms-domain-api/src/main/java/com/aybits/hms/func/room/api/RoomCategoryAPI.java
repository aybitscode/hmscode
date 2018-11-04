package com.aybits.hms.func.room.api;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.api.HmsAPIImpl;
import com.aybits.hms.func.room.beans.RoomCategory;
import com.aybits.hms.func.room.cache.RoomCategoryCache;
import com.aybits.hms.func.room.dao.RoomCategoryDAO;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

public class RoomCategoryAPI implements HmsAPI {


    static Logger log = Logger.getLogger(RoomCategoryAPI.class);
    RoomCategoryCache roomCategoryCache = new RoomCategoryCache();
    RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAO();

    public Boolean addRoomCategory(RoomCategory roomCategory) throws HMSRuntimeException {
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

    @Override
    public Object init(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public String process(JSONObject object) throws HMSRuntimeException {
        return null;
    }

    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {

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
