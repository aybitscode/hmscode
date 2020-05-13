package com.aybits.hms.func.service.dao;

public class ServiceDBQueries {


    /**
     * DML QUERIES
     */
    protected static final String FETCH_SERVICE_BY_SERVICE_ID = "select * from hms_service where service_id = ? and hotel_id = ?";
    protected static final String FETCH_ALL_SERVICES = "select * from hms_service where hotel_id = ?";
    protected static final String FETCH_SERVICE_BY_AVAILABILITY = "select * from hms_service where hotel_id = ? and is_available = ?";
    protected static final String FETCH_SERVICE_BY_CHARGEABILITY = "select * from hms_service where hotel_id = ? and is_chargeable = ?";
    
    
    
    protected static final String INSERT_NEW_SERVICE = "INSERT INTO hms_service(hotel_id,service_name,service_description," +
            "service_availability,is_service_chargeable,service_type,service_price)" +
            "VALUES(?,?,?,?,?,?,?)";

    protected static final String UPDATE_EXISTING_SERVICE = "UPDATE hms_service SET hotel_id = ?,service_key = ?,service_description = ?,"+
    " service_availability = ?,is_service_chargeable = ?,service_type = ?,"+
    " service_price = ?,applicable_voucher_id = ? WHERE service_id = ?";

    protected static final String UPDATE_SERVICE_STATUS = "UPDATE hms_amenity SET is_available = ? where amenity_id = ? and hotel_id = ?";
    protected static final String UPDATE_SERVICE_CHARGEABILITY = "UPDATE hms_amenity SET is_chargeable = ? where amenity_id = ? and hotel_id = ?";
    protected static final String UPDATE_SERVICE_CHARGES = "UPDATE hms_amenity SET amenity_charges = ? where amenity_id = ? and hotel_id = ?";

}
