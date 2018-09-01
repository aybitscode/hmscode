package com.aybits.hms.func.amenity.dao;

public class AmenityDBQueries {


    protected static final String FETCH_AMENITY_BY_AMENITY_ID = "select * from hms_amenity where amenity_id = ? and hotel_id = ?";
    protected static final String FETCH_ALL_AMENITIES = "select * from hms_amenity where hotel_id = ?";
    protected static final String INSERT_NEW_AMENITY = "INSERT INTO hms_amenity(hotel_id,amenity_id,amenity_name,amenity_description," +
 "is_available,is_chargeable,amenity_type,amenity_charges)" +
            "VALUES(?,?,?,?,?,?,?,?)";

    public static final String UPDATE_EXISTING_AMENITY = "UPDATE hms_amenity SET hotel_id = ?,amenity_name = ?,amenity_description = ?,"+
            " is_available = ?,is_chargeable = ?,amenity_type = ?,"+
            " amenity_charges = ? WHERE amenity_id = ? and hotel_id = ?";


}
