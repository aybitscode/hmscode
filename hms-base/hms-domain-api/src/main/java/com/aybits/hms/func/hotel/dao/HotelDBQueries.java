package com.aybits.hms.func.hotel.dao;

public class HotelDBQueries {


    protected static final String FETCH_HOTEL_BY_HOTELID = "select * from hms_hotel where hotel_id = ?";
    protected static final String FETCH_HOTEL_BY_CONTACT_DETAILS = "select * from hms_hotel where hotel_primary_email = ?, hotel_primary_phone = ?, hotel_primary_mobile = ?";
    protected static final String FETCH_ALL_HOTELS = "select * from hms_hotel";
    protected static final String FETCH_HOTEL_BY_EMPLOYEE_LOGIN = "select * from hms_hotel where hotel_id = (select hotel_id from " +
            "hms_employee where login_id = ?)";
    protected static final String FETCH_HOTEL_BY_EMPLOYEE_ID = "select * from hms_hotel where hotel_id = (select hotel_id from " +
            "hms_employee where employee_id = ?)";
    protected static final String UPDATE_HOTEL_STATUS = "update hms_hotel set hotel_status = ? where hotel_id = ?";
    protected static final String INSERT_NEW_HOTEL = "insert into hms_hotel(hotel_name,hotel_address," +
            "hotel_primary_email,hotel_secondary_email,hotel_primary_phone,hotel_secondary_phone," +
            "hotel_primary_mobile,hotel_secondary_mobile,hotel_fax_number," +
            "hotel_rating,hotel_logo,hotel_room_doorno_format," +
            "hotel_staff_count,hotel_room_count,hotel_bed_count,hotel_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String UPDATE_EXISTING_HOTEL = "update hms_hotel set" +
            "hotel_name = ?" +
            ",hotel_address = ?" +
            ",hotel_registration_data = ?" +
            ",hotel_rating = ?" +
            ",hotel_primary_email = ?,hotel_secondary_email = ?,hotel_primary_phone = ?,hotel_secondary_phone = ?" +
            ",hotel_primary_mobile = ?, hotel_secondary_mobile = ?,hotel_fax_number = ?"+
            ",hotel_logo = ?" +
            ",hotel_room_doorno_format = ?" +
            ",hotel_staff_count = ?" +
            ",hotel_room_count = ?" +
            ",hotel_bed_count = ?" +
            " where hotel_id = ?";

}
