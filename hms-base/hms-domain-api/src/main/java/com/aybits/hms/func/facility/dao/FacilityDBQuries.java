package com.aybits.hms.func.facility.dao;

public class FacilityDBQuries {


    protected static final String FETCH_FACILITY_BY_FACILITY_ID = "select * from hms_facility where facility_id = ?";
    protected static final String FETCH_ALL_FACILITIES = "select * from hms_facility";
    protected static final String INSERT_NEW_FACILITY = "INSERT INTO hms_facility(hotel_id,facility_description," +
            "facility_availability,is_facility_chargeable,facility_type,facility_price,applicable_voucher_id)" +
            "VALUES(?,?,?,?,?,?,?,?,?)";

    public static final String UPDATE_EXISTING_FACILITY = "UPDATE hms_facility SET hotel_id = ?,facility_id = ?,facility_key = ?,facility_description = ?,"+
    " facility_availability = ?,is_facility_chargeable = ?,facility_type = ?,"+
    " facility_price = ?,applicable_voucher_id = ? WHERE facility_id = ?";

}
