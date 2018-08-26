package com.aybits.hms.func.amenity.dao;

public class AmenityDBQueries {


<<<<<<< HEAD
    protected static final String FETCH_AMENITY_BY_AMENITY_ID = "select * from hms_amenity where amenity_id = ? and hotel_id = ?";
    protected static final String FETCH_ALL_AMENITIES = "select * from hms_amenity where hotel_id = ?";
    protected static final String INSERT_NEW_AMENITY = "INSERT INTO hms_amenity(hotel_id,amenity_id,amenity_name,amenity_description," +
            "facility_availability,is_facility_chargeable,facility_type,facility_price)" +
            "VALUES(?,?,?,?,?,?,?)";

    public static final String UPDATE_EXISTING_AMENITY = "UPDATE hms_facility SET hotel_id = ?,facility_key = ?,facility_description = ?,"+
=======
    protected static final String FETCH_FACILITY_BY_FACILITY_ID = "select * from hms_facility where facility_id = ?";
    protected static final String FETCH_ALL_FACILITIES = "select * from hms_facility";
    protected static final String INSERT_NEW_FACILITY = "INSERT INTO hms_facility(hotel_id,facility_name,facility_description," +
            "facility_availability,is_facility_chargeable,facility_type,facility_price)" +
            "VALUES(?,?,?,?,?,?,?)";

    public static final String UPDATE_EXISTING_FACILITY = "UPDATE hms_facility SET hotel_id = ?,facility_key = ?,facility_description = ?,"+
>>>>>>> 3f9d4031204dee4e4e3daa0a83e5ce91f7e95ed0
    " facility_availability = ?,is_facility_chargeable = ?,facility_type = ?,"+
    " facility_price = ?,applicable_voucher_id = ? WHERE facility_id = ?";

}
