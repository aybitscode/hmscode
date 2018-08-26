package com.aybits.hms.func.amenity.dao;

public class AmenityDBQueries {


    protected static final String FETCH_AMENITY_BY_AMENITY_ID = "select * from hms_amenity where amenity_id = ? and hotel_id = ?";
    protected static final String FETCH_ALL_AMENITIES = "select * from hms_amenity where hotel_id = ?";
    protected static final String INSERT_NEW_AMENITY = "INSERT INTO hms_amenity(hotel_id,amenity_id,amenity_name,amenity_description," +
            "facility_availability,is_facility_chargeable,facility_type,facility_price)" +
            "VALUES(?,?,?,?,?,?,?)";

    public static final String UPDATE_EXISTING_AMENITY = "UPDATE hms_facility SET hotel_id = ?,facility_key = ?,facility_description = ?,"+
    " facility_availability = ?,is_facility_chargeable = ?,facility_type = ?,"+
    " facility_price = ?,applicable_voucher_id = ? WHERE facility_id = ?";

}
