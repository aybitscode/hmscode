package com.aybits.hms.arch.exception;

public enum HMSErrorCodes implements HMSError {
	DB_CONNECTION_FAILED("db_connection_failure","DB Connection and initialization failed"),
	DB_NO_CONNECTIONS_AVAILABLE("no_db_connections_available","No DB Connections available"),
	DB_SQL_EXCEPTION_OCCURED("db_sql_exception_occured","DB SQL Exception occured"),
	
	
	INVALID_CUSTOMER_ID("invalid_customer_id","%s"),
	INVALID_CUSTOMER_PHONE("invalid_customer_phone","%s"),
	INVALID_LOGIN_ATTRIBUTES("invalid_login_attributes","%s"),
    INVALID_HOTEL_ATTRIBUTES("invalid_hotel_attributes","%s"),
    HOTEL_DETAILS_UNAVAILABLE("hotel_not_available","%s"),
	INVALID_ROOM_ATTRIBUTES("invalid_room_attributes","%s"),

    INVALID_EMPLOYEE_ID("invalid_employee_id","%s"),
	INVALID_EMPLOYEE_PHONE("invalid_employee_phone","%s"),

	HOTEL_SETUP_FAILED("hotel_setup_failed","%s"),
	HOTEL_REG_DATA_ADDITION_FAILED("hotel_registration_data_addition_failed","%s"),
	HOTEL_ALREADY_EXISTS ("hotel_already_exists","%s"),
	HOTEL_UPDATE_FAILED("hotel_update_failed","%s"),
	HMS_EXCEPTION("hms_exception","%s"),

	FACILITY_ADDITION_FAILED("facility_addition_failed","%s"),
	FACILITY_UPDATE_FAILED("facility_update_failed","%s"),
	AMENITY_ADDITION_FAILED("amenity_addition_failed","%s"),
	AMENITY_UPDATE_FAILED("amenity_update_failed","%s"),
	SERVICE_ADDITION_FAILED("service_addition_failed","%s"),
	VOUCHER_ADDITION_FAILED("service_addition_failed","%s"),
	SERVICE_UPDATE_FAILED("service_update_failed","%s");

	private String errorCode;
	private String errorMessage;
	
	private HMSErrorCodes(String errorCode,String errorMessage){
		this.errorCode= errorCode;
		this.errorMessage = errorMessage;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
