package com.aybits.hms.func.room.dao;

public class RoomDBQueries {

    static final String GET_ROOM_BY_ROOM_ID = "Select * from hms_room where room_id=? and hotel_id = ?";
    static final String GET_ROOMS_BY_HOTEL_ID = "Select * from hms_room where hotel_id=?";
    static final String ADD_ROOM = "insert into hms_hotel_room(hotel_id,room_id,room_facilities,room_status,room_category_id,room_occupancy,date_created) "+
            "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    static final String UPDATE_ROOM = "update hms_hotel_room set first_name = ?,last_name = ?, home_address = ?, identification_param_id, payment_param," +
            "							   email = ?, mobile = ? where room_id = ?";

    static final String DELETE_ROOM = "delete from hms_room where room_id = ?";

}
