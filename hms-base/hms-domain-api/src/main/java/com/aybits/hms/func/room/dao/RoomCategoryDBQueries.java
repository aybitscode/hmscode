package com.aybits.hms.func.room.dao;

public class RoomCategoryDBQueries {


    static final String GET_ROOM_CATEGORY_BY_CATEGORY_ID = "Select * from hms_room_category where category_id=? and hotel_id = ?";

    static final String GET_ROOM_CATEGORIES_BY_HOTEL_ID = "Select * from hms_room_category where hotel_id=?";

    static final String ADD_ROOM_CATEGORY = "insert into hms_room_category(hotel_id,category_id,category_name," +
            "category_description,bed_count,category_price,room_count,room_capacity,adult_occupancy,child_occupancy,category_status,room_occupancy,date_created) "+
            "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    static final String UPDATE_ROOM_CATEGORY = "update hms_hotel_room_category set category_name = ?, category_description=?," +
            "bed_count = ?,category_price = ?,room_count = ?,room_capacity = ?,adult_occupancy = ?,child_occupancy = ?," +
            "" +
            " where hotel_id ? and category_id = ?";

    static final String DELETE_ROOM_CATEGORY = "delete from hms_room where room_id = ?";

    static final String DISABLE_ROOM_CATEGORY = "update hms_hotel_room";

}
