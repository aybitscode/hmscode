package com.aybits.hms.func.facility.dao;

public class FacilityDBQueries {


    /**
     *  DDL QUERIES
     */

    protected static final String INSERT_NEW_FACILITY = "INSERT INTO hms_facility(hotel_id,facility_id,facility_name,facility_description," +
            "is_available,is_chargeable,facility_type,facility_charges)" +
            "VALUES(?,?,?,?,?,?,?,?)";

    protected static final String UPDATE_FACILITY_STATUS = "update hms_facility set is_available = ? where hotel_id = ? and facility_id = ?";
    protected static final String UPDATE_FACILITY_CHARGES = "update hms_facility set facility_charges = ? where facility_id = ? and hotel_id = ?";
    protected static final String UPDATE_EXISTING_FACILITY = "UPDATE hms_facility SET facility_name = ?,facility_description = ?,"+
            " is_available = ?,is_chargeable = ?,facility_type = ?,"+
            " facility_charges = ?,applicable_voucher_id = ? WHERE facility_id = ? and hotel_id = ?";


    /**
     * DML QUERIES
     */
    protected static final String FETCH_FACILITY_BY_FACILITY_ID = "select * from hms_facility where hotel_id = ? and facility_id = ?";
    protected static final String FETCH_ALL_FACILITIES = "select * from hms_facility where hotel_id = ?";
    protected static final String FETCH_FACILITY_BY_AVAILABILITY = "select * from hms_facility where hotel_id = ? and is_available = ?";
    protected static final String FETCH_FACILITY_BY_CHARGEABILITY = "select * from hms_facility where hotel_id = ? and is_chargeable = ?";



}
