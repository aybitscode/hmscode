package com.aybits.hms.func.voucher.dao;

public class VoucherDBQueries {

    protected static final String FETCH_VOUCHERS_BY_HOTELID = "select * from hms_voucher where hotel_id = ?";
    protected static final String FETCH_VOUCHER_BY_VOUCHER_ID = "select * from hms_voucher where hotel_id = ? and voucher_id = ?";
    protected static final String UPDATE_VOUCHER_STATUS = "update hms_voucher set voucher_status = ? where hotel_id = ? and voucher_id = ?";
    protected static final String INSERT_NEW_VOUCHER = "insert into hms_voucher(hotel_id,voucher_name,voucher_description," +
            "voucher_start_date,voucher_expiry_date," +
            "voucher_discount,voucher_status";

    protected static final String UPDATE_EXISTING_VOUCHER = "update hms_voucher set" +
            "voucher_name = ?" +
            "voucher_description = ?" +
            "voucher_start_date = ?" +
            "voucher_expiry_date = ?" +
            "voucher_discount = ?" +
            "voucher_status = ?" +
            " where hotel_id = ? and voucher_id = ?";

    protected static final String DELETE_EXISTING_VOUCHER = "delete from hms_voucher where hotel_id = ? and voucher_id = ?";
}
