ALTER TABLE hms_hotel CHANGE hotel_id hotel_id  int(64) NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE hms_hotel CHANGE hotel_name hotel_name varchar(255) NOT NULL;
ALTER TABLE hms_hotel CHANGE hotel_address hotel_address varchar(500) NOT NULL;
ALTER TABLE hms_hotel CHANGE hotel_rating hotel_rating varchar(45) DEFAULT NULL;
ALTER TABLE hms_hotel CHANGE hotel_staff_count hotel_staff_count int(10) DEFAULT '0';
ALTER TABLE hms_hotel CHANGE hotel_room_count hotel_room_count int(64) DEFAULT '0';
ALTER TABLE hms_hotel CHANGE hotel_bed_count hotel_bed_count int(64) DEFAULT '0';
ALTER TABLE hms_hotel CHANGE hotel_logo hotel_logo varchar(100) DEFAULT NULL ;
ALTER TABLE hms_hotel CHANGE hotel_room_doorno_format hotel_room_doorno_format varchar(45) DEFAULT NULL;
ALTER TABLE hms_hotel CHANGE hotel_status hotel_status int(11) NOT NULL;
ALTER TABLE hms_hotel CHANGE hotel_contact_details hotel_contact_details varchar(500) NOT NULL;
commit;


ALTER TABLE hms_admin_event_log CHANGE admin_id admin_id  varchar(60) NOT NULL;
ALTER TABLE hms_admin_event_log ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_admin_event_log CHANGE event_id event_id  varchar(60) NOT NULL;
ALTER TABLE hms_admin_event_log CHANGE event_log event_log  varchar(500) NOT NULL;
ALTER TABLE hms_admin_event_log CHANGE event_time event_time  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
commit;

ALTER TABLE hms_booking_info ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_booking_info CHANGE customer_id customer_id  varchar(60) NOT NULL;
ALTER TABLE hms_booking_info CHANGE room_id room_id  varchar(60) NOT NULL;
ALTER TABLE hms_booking_info CHANGE checkin_date checkin_date   datetime NOT NULL;
ALTER TABLE hms_booking_info CHANGE booking_id booking_id  varchar(60) NOT NULL;
ALTER TABLE hms_booking_info CHANGE checkout_date checkout_date  datetime DEFAULT NULL;
ALTER TABLE hms_booking_info CHANGE invoice_id invoice_id  varchar(60) NOT NULL;
ALTER TABLE hms_booking_info CHANGE booking_status booking_status  varchar(60) NOT NULL;
ALTER TABLE hms_booking_info CHANGE DATE_CREATED DATE_CREATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_booking_info CHANGE DATE_UPDATED DATE_UPDATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
commit;

ALTER TABLE hms_booking_invoice ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_booking_invoice CHANGE invoice_id invoice_id  varchar(60) NOT NULL;
ALTER TABLE hms_booking_invoice CHANGE booking_id booking_id  varchar(60) NOT NULL;
ALTER TABLE hms_booking_invoice CHANGE room_rent room_rent   decimal(5,0) NOT NULL;
ALTER TABLE hms_booking_invoice CHANGE facilities_charges facilities_charges  decimal(5,0) NOT NULL;
ALTER TABLE hms_booking_invoice CHANGE miscellaneous_charges miscellaneous_charges  decimal(5,0) NOT NULL;
ALTER TABLE hms_booking_invoice CHANGE coupon_id coupon_id  varchar(60) NOT NULL;
ALTER TABLE hms_booking_invoice CHANGE payment_option payment_option  varchar(45) NOT NULL;
ALTER TABLE hms_booking_invoice CHANGE total_amount total_amount  decimal(5,0) NOT NULL;
ALTER TABLE hms_booking_invoice CHANGE DATE_CREATED DATE_CREATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_booking_invoice CHANGE tax_amount tax_amount   decimal(5,0) NOT NULL ;
ALTER TABLE hms_booking_invoice CHANGE DATE_UPDATED DATE_UPDATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_booking_invoice CHANGE invoice_barcode invoice_barcode   varchar(100) NOT NULL;
commit;

ALTER TABLE hms_corporate CHANGE CORPORATE_ID CORPORATE_ID varchar(60) PRIMARY KEY;
ALTER TABLE hms_corporate CHANGE COMPANY_NAME COMPANY_NAME VARCHAR(255) NOT NULL;
ALTER TABLE hms_corporate CHANGE EMAIL EMAIL VARCHAR(60) NOT NULL;
ALTER TABLE hms_corporate CHANGE MOBILE MOBILE int(25) NOT NULL;
ALTER TABLE hms_corporate CHANGE OFFICE_ADDRESS OFFICE_ADDRESS VARCHAR(2000) NOT NULL;
ALTER TABLE hms_corporate CHANGE OFFICE_ADDRESS OFFICE_ADDRESS VARCHAR(60) NOT NULL;
ALTER TABLE hms_corporate CHANGE PAYMENT_PARAM  PAYMENT_PARAM VARCHAR(60) NOT NULL;
ALTER TABLE hms_corporate CHANGE COMPANY_STATUS COMPANY_STATUS INT(20) DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.';
ALTER TABLE hms_corporate CHANGE DATE_CREATED DATE_CREATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_corporate ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_corporate CHANGE DATE_MODIFIED DATE_MODIFIED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_corporate CHANGE DATE_UPDATED DATE_UPDATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_corporate CHANGE DATE_DELETED DATE_DELETED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
commit;

ALTER TABLE hms_customer CHANGE CUSTOMER_ID CUSTOMER_ID varchar(60) PRIMARY KEY;
ALTER TABLE hms_customer CHANGE CORPORATE_ID CORPORATE_ID varchar(60) DEFAULT NULL;
ALTER TABLE hms_customer CHANGE CUSTOMER_FULL_NAME CUSTOMER_FULL_NAME varchar(300) NOT NULL;
ALTER TABLE hms_customer CHANGE EMAIL EMAIL VARCHAR(60) NOT NULL;
ALTER TABLE hms_customer CHANGE MOBILE  MOBILE int(20) NOT NULL;
ALTER TABLE hms_customer CHANGE HOME_ADDRESS HOME_ADDRESS varchar(2000) DEFAULT NULL;
ALTER TABLE hms_customer CHANGE IDENTIFICATION_PARAM_ID IDENTIFICATION_PARAM_ID  VARCHAR(60) NOT NULL;
ALTER TABLE hms_customer CHANGE PAYMENT_PARAM PAYMENT_PARAM  varchar(60) DEFAULT NULL;
ALTER TABLE hms_customer CHANGE DATE_CREATED DATE_CREATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_customer ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_customer CHANGE customer_status  customer_status int(20) NOT NULL DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.';
ALTER TABLE hms_customer CHANGE DATE_MODIFIED DATE_MODIFIED datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_customer CHANGE DATE_UPDATED DATE_UPDATED   datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_customer CHANGE DATE_DELETED DATE_DELETED   datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
commit;


ALTER TABLE hms_customization CHANGE hotel_id hotel_id varchar(60) PRIMARY KEY;
ALTER TABLE hms_customization CHANGE customization_map customization_map varchar(1000)  NOT NULL;
commit;

ALTER TABLE hms_employee CHANGE EMPLOYEE_ID EMPLOYEE_ID varchar(60) PRIMARY KEY;
ALTER TABLE hms_employee CHANGE EMP_FULL_NAME EMP_FULL_NAME varchar(300)  NOT NULL;
ALTER TABLE hms_employee CHANGE EMAIL EMAIL VARCHAR(60) NOT NULL;
ALTER TABLE hms_employee CHANGE MOBILE  MOBILE int(20) NOT NULL;
ALTER TABLE hms_employee CHANGE HOME_ADDRESS HOME_ADDRESS varchar(2000) DEFAULT NULL;
ALTER TABLE hms_employee CHANGE IDENTIFICATION_PARAM IDENTIFICATION_PARAM  VARCHAR(60) NOT NULL;
ALTER TABLE hms_employee CHANGE employee_status  employee_status int(11) NOT NULL DEFAULT '0';
ALTER TABLE hms_employee CHANGE login_id login_id varchar(60) NOT NULL;
ALTER TABLE hms_employee CHANGE employee_role employee_role varchar(60) DEFAULT NULL;
ALTER TABLE hms_employee CHANGE password password varchar(100) DEFAULT NULL;
ALTER TABLE hms_employee CHANGE DATE_CREATED DATE_CREATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_employee ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_employee CHANGE DATE_MODIFIED DATE_MODIFIED datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_employee CHANGE DATE_UPDATED DATE_UPDATED   datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_employee CHANGE DATE_DELETED DATE_DELETED   datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
commit;

ALTER TABLE hms_event CHANGE event_key event_key varchar(60) PRIMARY KEY;
ALTER TABLE hms_event CHANGE event_description event_description varchar(1000)  NOT NULL;
ALTER TABLE hms_event CHANGE event_id event_id varchar(60) NOT NULL;
ALTER TABLE hms_event CHANGE event_type event_type varchar(45) NOT NULL;
commit;

ALTER TABLE hms_facility ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id); ;
ALTER TABLE hms_facility CHANGE facility_id facility_id varchar(60) NOT NULL PRIMARY KEY;
ALTER TABLE hms_facility CHANGE facility_key facility_key varchar(45) NOT NULL;
ALTER TABLE hms_facility CHANGE facility_description facility_description varchar(100) NOT NULL;
ALTER TABLE hms_facility CHANGE facility_availability facility_availability int(11) NOT NULL DEFAULT '1';
ALTER TABLE hms_facility CHANGE is_facility_chargeable is_facility_chargeable int(11) NOT NULL DEFAULT '0';
ALTER TABLE hms_facility CHANGE facility_type facility_type int(11) NOT NULL;
ALTER TABLE hms_facility CHANGE facility_price facility_price double NOT NULL DEFAULT '0';
ALTER TABLE hms_facility CHANGE applicable_voucher_id applicable_voucher_id varchar(60) NOT NULL;
commit;

ALTER TABLE hms_hotel_registration_data CHANGE registration_data_id registration_data_id  int(11) NOT NULL PRIMARY KEY;
ALTER TABLE hms_hotel_registration_data ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_hotel_registration_data CHANGE building_permit_no building_permit_no varchar(200) DEFAULT NULL;
ALTER TABLE hms_hotel_registration_data CHANGE fire_safety_permit_no fire_safety_permit_no varchar(200) DEFAULT NULL;
ALTER TABLE hms_hotel_registration_data CHANGE police_license_no police_license_no varchar(200) DEFAULT NULL;
ALTER TABLE hms_hotel_registration_data CHANGE health_trade_license_no health_trade_license_no varchar(200) DEFAULT NULL;
ALTER TABLE hms_hotel_registration_data CHANGE liquor_license_no liquor_license_no varchar(200) DEFAULT NULL;
ALTER TABLE hms_hotel_registration_data CHANGE fssai_license_no fssai_license_no varchar(200) DEFAULT NULL ;
ALTER TABLE hms_hotel_registration_data CHANGE gst_license_no gst_license_no varchar(200) DEFAULT NULL;
ALTER TABLE hms_hotel_registration_data CHANGE esi_registration_no esi_registration_no varchar(200) DEFAULT NULL;
ALTER TABLE hms_hotel_registration_data CHANGE epf_registration_no epf_registration_no varchar(200) DEFAULT NULL;
commit;

ALTER TABLE hms_hotel_room CHANGE room_id room_id  varchar(60) NOT NULL PRIMARY KEY;
ALTER TABLE hms_hotel_room ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_hotel_room CHANGE room_facilities room_facilities varchar(1000) NOT NULL;
ALTER TABLE hms_hotel_room CHANGE room_occupancy room_occupancy  int(64) NOT NULL;
ALTER TABLE hms_hotel_room CHANGE room_status room_status int(11) NOT NULL ;
ALTER TABLE hms_hotel_room CHANGE room_category_id room_category_id varchar(60) NOT NULL;
commit;

ALTER TABLE hms_identification_params ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_identification_params CHANGE IDENTIFICATION_PARAM_ID IDENTIFICATION_PARAM_ID  varchar(60) NOT NULL PRIMARY KEY;
ALTER TABLE hms_identification_params CHANGE IDENTIFICATION_PARAM_TYPE IDENTIFICATION_PARAM_TYPE varchar(1000) NOT NULL;
ALTER TABLE hms_identification_params CHANGE IDENTIFICATION_PARAM_FILE IDENTIFICATION_PARAM_FILE  int(64) NOT NULL;
ALTER TABLE hms_identification_params CHANGE DATE_CREATED DATE_CREATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_identification_params CHANGE DATE_MODIFIED DATE_MODIFIED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_identification_params CHANGE DATE_UPDATED DATE_UPDATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_identification_params CHANGE DATE_DELETED DATE_DELETED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
commit;

ALTER TABLE hms_inventory_event_log ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_inventory_event_log CHANGE inventory_event_id inventory_event_id  int(11) NOT NULL PRIMARY KEY;
ALTER TABLE hms_inventory_event_log CHANGE inventory_event_log inventory_event_log varchar(1000) NOT NULL;
ALTER TABLE hms_inventory_event_log CHANGE date_created date_created timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_inventory_event_log CHANGE user_id user_id  int(11) NOT NULL;
commit;

ALTER TABLE hms_inventory_item_group CHANGE item_group_name item_group_name   varchar(100)  NOT NULL PRIMARY KEY;
ALTER TABLE hms_inventory_item_group CHANGE item_group_description item_group_description  varchar(200)  NOT NULL;
commit;



ALTER TABLE hms_inventory_items ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_inventory_items CHANGE item_name item_name  varchar(100)  NOT NULL;
ALTER TABLE hms_inventory_items CHANGE item_count item_count  int(11) NOT NULL;
ALTER TABLE hms_inventory_items CHANGE DATE_CREATED DATE_CREATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_inventory_items CHANGE item_id item_id  int(11) NOT NULL PRIMARY KEY;
ALTER TABLE hms_inventory_items CHANGE item_group_name item_group_name  varchar(100)  NOT NULL;
ALTER TABLE hms_inventory_items CHANGE DATE_MODIFIED DATE_MODIFIED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_inventory_items CHANGE DATE_UPDATED DATE_UPDATED  datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6);
ALTER TABLE hms_inventory_items CHANGE item_status item_status   int(11) NOT NULL;
ALTER TABLE hms_inventory_items CHANGE item_group item_group  varchar(100) DEFAULT NULL;
commit;

ALTER TABLE hms_room_category ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_room_category CHANGE category_id category_id  varchar(60) NOT NULL PRIMARY KEY ;
ALTER TABLE hms_room_category CHANGE category_name category_name   varchar(120) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE category_description category_description  varchar(400) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE bed_count bed_count  int(11) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE category_price category_price  double NOT NULL DEFAULT '0';
ALTER TABLE hms_room_category CHANGE room_count room_count  int(11) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE room_capacity room_capacity  int(11) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE adult_occupancy adult_occupancy  int(11) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE child_occupancy child_occupancy  int(11) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE category_facilities category_facilities  varchar(1000) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE category_amenities category_amenities  varchar(1000) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE category_services category_services    varchar(1000) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE category_vouchers category_vouchers    varchar(1000) DEFAULT NULL;
ALTER TABLE hms_room_category CHANGE is_category_active is_category_active  int(11) DEFAULT NULL;
commit;


ALTER TABLE hms_system_params CHANGE param_name param_name  varchar(45) NOT NULL PRIMARY KEY ;
ALTER TABLE hms_system_params CHANGE param_value param_value  varchar(45) NOT NULL;
ALTER TABLE hms_system_params CHANGE param_description param_description  varchar(500) NOT NULL;
commit;

ALTER TABLE hms_version_info CHANGE db_version db_version  varchar(100) NOT NULL;
ALTER TABLE hms_version_info CHANGE application_version application_version  varchar(100) NOT NULL;
ALTER TABLE hms_version_info CHANGE ux_version ux_version   varchar(100) NOT NULL;
commit;

ALTER TABLE hms_voucher ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_voucher CHANGE voucher_id voucher_id  varchar(60) DEFAULT NULL;
ALTER TABLE hms_voucher CHANGE voucher_name voucher_name  varchar(60) DEFAULT NULL;
ALTER TABLE hms_voucher CHANGE voucher_description voucher_description   varchar(200) DEFAULT NULL;
ALTER TABLE hms_voucher CHANGE voucher_expiry_date voucher_expiry_date  datetime(6) DEFAULT NULL;
ALTER TABLE hms_voucher CHANGE voucer_start_date voucer_start_date  datetime(6) DEFAULT NULL;
ALTER TABLE hms_voucher CHANGE voucher_discount voucher_discount   double DEFAULT NULL;
ALTER TABLE hms_voucher CHANGE vouhcer_status vouhcer_status   int(11) DEFAULT NULL;
commit;


ALTER TABLE hms_room_category_voucher_map ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_room_category_voucher_map ADD FOREIGN KEY (voucher_id) REFERENCES hms_voucher(voucher_id);
ALTER TABLE hms_room_category_voucher_map ADD FOREIGN KEY (room_category_id) REFERENCES hms_room_category(category_id);

ALTER TABLE hms_amenity_voucher_map ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_amenity_voucher_map ADD FOREIGN KEY (voucher_id) REFERENCES hms_voucher(voucher_id);
ALTER TABLE hms_amenity_voucher_map ADD FOREIGN KEY (amenity_id) REFERENCES hms_amenity(amenity_id);

ALTER TABLE hms_service_voucher_map ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_service_voucher_map ADD FOREIGN KEY (voucher_id) REFERENCES hms_voucher(voucher_id);
ALTER TABLE hms_service_voucher_map ADD FOREIGN KEY (amenity_id) REFERENCES hms_service(service_id);

ALTER TABLE hms_facility_voucher_map ADD FOREIGN KEY (hotel_id) REFERENCES hms_hotel(hotel_id);
ALTER TABLE hms_facility_voucher_map ADD FOREIGN KEY (voucher_id) REFERENCES hms_voucher(voucher_id);
ALTER TABLE hms_facility_voucher_map ADD FOREIGN KEY (amenity_id) REFERENCES hms_facility(facility_id);

commit;
