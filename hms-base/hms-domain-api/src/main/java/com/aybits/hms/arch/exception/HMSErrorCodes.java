package com.aybits.hms.arch.exception;

public enum HMSErrorCodes implements HMSError {
	DB_CONNECTION_FAILED("db_connection_failure","DB Connection and initialization failed"),
	DB_NO_CONNECTIONS_AVAILABLE("no_db_connections_available","No DB Connections available"),
	DB_SQL_EXCEPTION_OCCURED("db_sql_exception_occured","DB SQL Exception occured"),
	
	
	INVALID_CUSTOMER_ID("invalid_customer_id","%s"),
	INVALID_CUSTOMER_PHONE("invalid_customer_phone","%s"),
	INVALID_LOGIN_ATTRIBUTES("invalid_login_attributes","%s"),
    HOTEL_DETAILS_UNAVAILABLE("hotel_not_available","%s"),
	INVALID_ROOM_ATTRIBUTES("invalid_room_attributes","%s"),
	
	
    INVALID_EMPLOYEE_ID("invalid_employee_id","%s"),
	INVALID_EMPLOYEE_PHONE("invalid_employee_phone","%s"),

	HOTEL_SETUP_FAILED("hotel_setup_failed","%s"),
	INVALID_HOTEL_SETUP_DATA("invalid_hotel_setup_data","%s"),
	INVALID_EMPLOYEE_DETAILS("invalid_employee_details","%s"),
	INVALID_HOTEL_ATTRIBUTES("invalid_hotel_attributes","%s"),
	INVALID_CONTACT_DETAILS("missing_contact_details","%s"),
	INVALID_ADDRESS_DETAILS("missing_address_details","%s"),
	INVALID_PHONE("invalid_phone","%s"),
	INVALID_EMAIL("invalid_email","%s"),
	INVALID_MOBILE_PHONE("invalid_mobile_phone","%s"),

	
	INVALID_FEATURE_DETAILS("invalid_feature_details","%s"),
	INVALID_FACILIY_DETAILS("invalid_facility_details","%s"),
	INVALID_FACILITY_ID("invalid_facility_id","%s"),
	INVALID_FACILITY_NAME("invalid_facility_name","%s"),
	INVALID_FACILITY_TYPE("invalid_facility_type","%s"),
	INVALID_FACILITY_AVAILABILE_VALUE("invalid_facility_available_value","%s"),
	INVALID_FACILITY_CHARGEABLE_VALUE("invalid_facility_chargeable_value","%s"),
	INVALID_FACILITY_CHARGES("invalid_facility_charges","%s"),


	INVALID_AMENITY_DETAILS("invalid_amenity_details","%s"),
	INVALID_AMENITY_CHARGES("invalid_amenity_charges","%s"),
	INVALID_AMENITY_ID("invalid_amenity_id","%s"),
	INVALID_AMENITY_NAME("invalid_amenity_name","%s"),
	INVALID_AMENITY_TYPE("invalid_amenity_type","%s"),
	INVALID_AMENITY_AVAILABILE_VALUE("invalid_amenity_available_value","%s"),
	INVALID_AMENITY_CHARGEABLE_VALUE("invalid_amenity_chargeable_value","%s"),

	INVALID_SERVICE_DETAILS("invalid_service_details","%s"),
	INVALID_SERVICE_CHARGES("invalid_service_charges","%s"),
	INVALID_SERVICE_ID("invalid_service_id","%s"),
	INVALID_SERVICE_NAME("invalid_service_name","%s"),
	INVALID_SERVICE_TYPE("invalid_service_type","%s"),
	INVALID_SERVICE_AVAILABILE_VALUE("invalid_service_available_value","%s"),
	INVALID_SERVICE_CHARGEABLE_VALUE("invalid_service_chargeable_value","%s"),


	INVALID_HOTEL_NAME("invalid_hotel_name","%s"),
	HOTEL_REG_DATA_ADDITION_FAILED("hotel_registration_data_addition_failed","%s"),
	INVALID_HOTEL_REGISTRATION_DATA("invalid_hotel_registration_data","%s"),
	HOTEL_ALREADY_EXISTS ("hotel_already_exists","%s"),
	EMPLOYEE_ALREADY_EXISTS("employee_already_exists","%s"),
	EMPLOYEE_UPDATE_FAILED("employee_update_failed","%s"),
	HOTEL_UPDATE_FAILED("hotel_update_failed","%s"),
	HMS_EXCEPTION("hms_exception","%s"),
	HMS_LENGTH_CHECK_FAILED("hms_length_check_failed","%s"),
    FEATURE_ADDITION_FAILED("feature_addition_failed","%s"),
	FACILITY_ADDITION_FAILED("facility_addition_failed","%s"),
	FACILITY_UPDATE_FAILED("facility_update_failed","%s"),
	AMENITY_ADDITION_FAILED("amenity_addition_failed","%s"),
	AMENITY_UPDATE_FAILED("amenity_update_failed","%s"),
	SERVICE_ADDITION_FAILED("service_addition_failed","%s"),
	VOUCHER_ADDITION_FAILED("service_addition_failed","%s"),
	SERVICE_UPDATE_FAILED("service_update_failed","%s"),

	//added by nouman
	TAX_RULE_FAILED("TaxRule_Failed_Toadd","%s");
	
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
