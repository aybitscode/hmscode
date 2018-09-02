package com.aybits.hms.func.room.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.func.room.beans.RoomCategory;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class  RoomCategoryDAO {

    static Logger Log = Logger.getLogger(RoomCategoryDAO.class);

    private Connection connection = DBCPConnection.getDBConnection();

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
