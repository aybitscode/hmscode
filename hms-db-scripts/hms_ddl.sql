CREATE TABLE `hms_hotel` (
  `hotel_id` varchar(64) ,
  `hotel_name` varchar(255) ,
  `hotel_address` varchar(500) COMMENT 'Address of the given hotel',
  `hotel_rating` varchar(45) COMMENT 'Rating of the hotel - whether it is 5-star,3-star,2-star etc',
  `hotel_staff_count` int(10) ,
  `hotel_room_count` int(64) ,
  `hotel_bed_count` int(64) ,
  `hotel_logo` varchar(100) ,
  `hotel_room_doorno_format` varchar(45) ,
  `hotel_primary_phone` varchar(64),
  `hotel_secondary_phone` varchar(64),
  `hotel_primary_email` varchar(64),
  `hotel_secondary_email` varchar(64),
  `hotel_primary_mobile` varchar(64),
  `hotel_secondary_mobile` varchar(64),
  `hotel_status` int(11)  COMMENT 'This column saves the status details whether hotel is enabled,active or disabled'
) COMMENT='This is the primary table to store all the information regarding entity - Hotel in the HMS Solution';

CREATE TABLE `hms_admin_event_log` (
  `admin_id` varchar(60) ,
  `hotel_id` varchar(64)  COMMENT 'Unique identifier to identify a given hotel',
  `event_id` varchar(60) ,
  `event_log` varchar(500) ,
  `event_time` timestamp(6)
)  COMMENT='This table records the events initiated and undertaken by a given admin';

CREATE TABLE `hms_booking_info` (
  `customer_id` varchar(60) ,
  `hotel_id` varchar(64)  COMMENT 'Unique identifier to identify a given hotel',
  `room_id` varchar(60) ,
  `checkin_date` datetime ,
  `booking_id` varchar(60) NOT NULL COMMENT 'The entire billing details are mapped to this id - bill_id which is a foreign key to the billing_info table ',
  `checkout_date` datetime DEFAULT NULL COMMENT 'This refers to the check-in and check-out details and the no of rooms booked by an individual user.',
  `invoice_id` varchar(60) ,
  `booking_status` varchar(60) NOT NULL COMMENT 'The Booking status is - 0 - undetermined, 1 - confirmed,2 - cancelled.',
  `date_created` TIMESTAMP(6),
  `date_updated` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)
);

CREATE TABLE `hms_booking_invoice` (
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `invoice_id` varchar(60),
  `booking_id` varchar(60) ,
  `room_rent` decimal(5,0),
  `facilities_charges` decimal(5,0) ,
  `miscellaneous_charges` decimal(5,0) ,
  `coupon_id` varchar(60) ,
  `payment_option` varchar(45) ,
  `total_amount` decimal(5,0) ,
  `DATE_CREATED` TIMESTAMP(6) ,
  `tax_amount` decimal(5,0) COMMENT 'The total applicable tax amount - service tax , CST, GST etc applicable on the current bill',
  `DATE_UPDATED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `invoice_barcode` varchar(100) 
) ;

CREATE  TABLE `hms_corporate` (
  `CORPORATE_ID` varchar(60) ,
  `COMPANY_NAME` varchar(100),
  `EMAIL` varchar(60) ,
  `OFFICE_PHONE` varchar(20) ,
  `MOBILE` int(20) ,
  `OFFICE_ADDRESS` varchar(2000),
  `IDENTIFICATION_PARAM` varchar(60) ,
  `PAYMENT_PARAM` varchar(60) ,
  `COMPANY_STATUS` int(11)  ,
  `DATE_CREATED` TIMESTAMP(6) ,
  `DATE_MODIFIED`TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `DATE_DELETED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `hotel_id` varchar(64)  COMMENT 'Unique identifier to identify a given hotel'
)COMMENT='This table holds the information of all the corporate data.';

CREATE TABLE `hms_customer` (
  `CUSTOMER_ID` varchar(60) ,
  `CORPORATE_ID` varchar(60) ,
  `CUSTOMER_FULL_NAME` varchar(300),
  `EMAIL` varchar(60) ,
  `MOBILE` int(20) ,
  `HOME_ADDRESS` varchar(2000) ,
  `IDENTIFICATION_PARAM_ID` varchar(60) ,
  `PAYMENT_PARAM` varchar(60) ,
  `DATE_CREATED` timestamp(6),
  `hotel_id` varchar(64) NOT NULL COMMENT 'This is the foreign key reference to the hotel id created in the hms_hotel table',
  `customer_status` int(11) ,
  `DATE_MODIFIED` timestamp(6) DEFAULT CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` timestamp(6) DEFAULT CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) DEFAULT CURRENT_TIMESTAMP(6) 
) COMMENT='This table holds the information of all the customer data.';

CREATE TABLE `hms_customization` (
  `hotel_id` varchar(60),
  `customization_map` varchar(1000)  COMMENT 'This customization maps holds parameters to the corresponding mongo_db schema which hold the binary files, logos, images, and other details. This is a JSON and should accept only JSON based information in this map.'
);

CREATE TABLE `hms_employee` (
  `EMPLOYEE_ID` varchar(60) ,
  `EMP_FULL_NAME` varchar(300) ,
  `EMAIL` varchar(60) ,
  `MOBILE` int(20) ,
  `HOME_ADDRESS` varchar(2000) ,
  `IDENTIFICATION_PARAM` varchar(60) ,
  `employee_status` int(11) COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.',
  `login_id` varchar(60) COMMENT 'The Login ID of a given user which they set in the hms solution',
  `DATE_CREATED` TIMESTAMP(6),
  `employee_role` varchar(60),
  `password` varchar(100),
  `hotel_id` varchar(64) NOT NULL COMMENT 'This is the foreign key reference to the hotel_id created in the hms_hotel table.',
  `DATE_MODIFIED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `DATE_DELETED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)
) COMMENT='This table holds the information of all the employee data.';

CREATE TABLE `hms_event` (
  `event_key` varchar(60) ,
  `event_description` varchar(1000),
  `event_id` varchar(60) ,
  `event_type` varchar(45) COMMENT 'event_type denotes whether it is an admin event or it is an user event.'
) COMMENT='This table tracks of all the events related to both user and admin. This is a metadata table that holds all the event details.';

CREATE TABLE `hms_facility` (
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `facility_id` varchar(60) COMMENT 'The unique identifier to identify the facility assigned',
  `facility_key` varchar(45) COMMENT 'The key to define the facility to show during reporting etc.',
  `facility_description` varchar(100) ,
  `facility_availability` int(11) ,
  `is_facility_chargeable` int(11) ,
  `facility_type` int(11),
  `facility_price` double ,
  `applicable_voucher_id` varchar(60) NOT NULL
) COMMENT='This table defines the facilities that are provided for a given hotel';

CREATE TABLE `hms_facility_voucher_map` (
  `hotel_id` varchar(64) DEFAULT NULL COMMENT 'This column represents the unique hotel ID generated during hotel setup and uniquely identifies the hotel created in hms solution',
  `facility_id` varchar(60) DEFAULT NULL COMMENT 'This column is generated when a facility is created and uniquely represents a facility created in hms solution',
  `voucher_id` varchar(60) DEFAULT NULL COMMENT 'This column represents the voucher created for a given hotel and is mapped to a given hotel''s facility'
)COMMENT='This table defines the Vouchers associated with a given facility';


CREATE TABLE `hms_hotel_registration_data` (
  `registration_data_id` varchar(60),
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `building_permit_no` varchar(200) ,
  `fire_safety_permit_no` varchar(200) ,
  `police_license_no` varchar(200) ,
  `health_trade_license_no` varchar(200) ,
  `liquor_license_no` varchar(200) ,
  `fssai_license_no` varchar(200) ,
  `gst_license_no` varchar(200) ,
  `esi_registration_no` varchar(200) ,
  `epf_registration_no` varchar(200) 
) ;


CREATE TABLE `hms_hotel_room` (
  `hotel_id` varchar(64),
  `room_id` varchar(60),
  `room_facilities` varchar(1000),
  `room_occupancy` int(64) COMMENT 'The maximum capacity of the room to be occupied, max value can be defined for this room depending on the admin input',
  `room_status` int(11)  COMMENT 'This column indicates whether the room is - 0 - room_not_available, 1 - available, 2 - booked, 3 - blocked_for_renovation',
  `room_category_id` varchar(60)  COMMENT 'Room category defines the category of the room'
) COMMENT='This table stores the information regarding the hotel and room mapping and the details associated with individual hotel rooms like - facilities, room category, occupancy, ';

CREATE TABLE `hms_identification_params` (
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `IDENTIFICATION_PARAM_ID` varchar(60),
  `IDENTIFICATION_PARAM_TYPE` varchar(60) NOT NULL,
  `IDENTIFICATION_PARAM_FILE` varchar(100) NOT NULL,
  `DATE_CREATED` TIMESTAMP(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_MODIFIED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `DATE_DELETED` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)
) COMMENT='This table holds the information of all the customer data.';

CREATE TABLE `hms_inventory_event_log` (
  `hotel_id` varchar(64)  COMMENT 'Unique identifier to identify a given hotel',
  `inventory_event_id` int(11) ,
  `inventory_event_log` varchar(1000) ,
  `date_created` timestamp(6) ,
  `user_id` int(11) 
) ;

CREATE TABLE `hms_inventory_item_group` (
  `item_group_name` varchar(100),
  `item_group_description` varchar(200)
) ;

CREATE TABLE `hms_inventory_items` (
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `item_name` varchar(100) ,
  `item_count` int(11) ,
  `DATE_CREATED` TIMESTAMP(6) ,
  `item_id` int(11) NOT NULL,
  `item_group_name` varchar(100) ,
  `date_modified` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `date_updated` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
  `item_status` int(11) COMMENT 'item_status - defines either the item is available, out of stock or undetermined.',
  `item_group` varchar(100) COMMENT 'this denotes to which group does this item belongs to.'
) COMMENT='This table stores all the details related to the inventory of a given hotel';

CREATE TABLE `hms_room_category` (
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `category_id` varchar(60) ,
  `category_name` varchar(120) ,
  `category_description` varchar(400),
  `bed_count` int(11) ,
  `category_price` double ,
  `room_count` int(11),
  `room_capacity` int(11) ,
  `adult_occupancy` int(11) ,
  `child_occupancy` int(11) ,
  `category_facilities` varchar(1000) ,
  `category_amenities` varchar(1000) ,
  `category_services` varchar(1000) ,
  `category_vouchers` varchar(1000) ,
  `is_category_active` int(11)
) COMMENT='This table stores the details for the various categories and prices,services,facilities,vouchers assigned to each category';

CREATE TABLE `hms_system_params` (
  `param_name` varchar(45),
  `param_value` varchar(45) ,
  `param_description` varchar(500) 
) COMMENT='This table stores the details for the system level configuration of the hms data';


CREATE TABLE `hms_version_info` (
  `db_version` varchar(100) ,
  `application_version` varchar(100),
  `ux_version` varchar(100) 
) ;


CREATE TABLE `hms_voucher` (
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `voucher_id` varchar(60) ,
  `voucher_name` varchar(60) ,
  `voucher_description` varchar(200) ,
  `voucher_expiry_date` datetime(6) ,
  `voucer_start_date` datetime(6) ,
  `voucher_discount` double ,
  `vouhcer_status` int(11) 
);

CREATE TABLE `hms_service` (
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `service_id` varchar(60) COMMENT 'The unique identifier to identify the service assigned',
  `service_name` varchar(45) COMMENT 'The key to define the service to show during reporting etc.',
  `service_description` varchar(100) ,
  `service_availability` int(11) ,
  `is_service_chargeable` int(11) ,
  `service_type` int(11),
  `service_price` double
) COMMENT='This table defines the services that are provided for a given hotel';

CREATE TABLE `hms_amenity` (
  `hotel_id` varchar(64) COMMENT 'Unique identifier to identify a given hotel',
  `amenity_id` varchar(60) COMMENT 'The unique identifier to identify the amenity assigned',
  `amenity_name` varchar(45) COMMENT 'The key to define the amenity to show during reporting etc.',
  `amenity_description` varchar(100) ,
  `amenity_availability` int(11) ,
  `is_amenity_chargeable` int(11) ,
  `amenity_type` int(11),
  `amenity_price` double
) COMMENT='This table defines the amenities that are provided for a given hotel';


CREATE TABLE `hms_facility_voucher_map` (
  `hotel_id` varchar(64) COMMENT 'This column represents the unique hotel ID generated during hotel setup and uniquely identifies the hotel created in hms solution',
  `facility_id` varchar(60) COMMENT 'This column is generated when a facility is created and uniquely represents a facility created in hms solution',
  `voucher_id` varchar(60) COMMENT 'This column represents the voucher created for a given hotel and is mapped to a given hotel''s facility'
)COMMENT='This table defines the Vouchers associated with a given facility';

CREATE TABLE `hms_service_voucher_map` (
  `hotel_id` varchar(64)  COMMENT 'This column represents the unique hotel ID generated during hotel setup and uniquely identifies the hotel created in hms solution',
  `service_id` varchar(60)  COMMENT 'This column is generated when a service is created in hms solution',
  `voucher_id` varchar(60)  COMMENT 'This column represents the voucher created for a given hotel and is mapped to a given hotel''s service'
)COMMENT='This table defines the Vouchers associated with a given service';

CREATE TABLE `hms_amenity_voucher_map` (
  `hotel_id` varchar(64)  COMMENT 'This column represents the unique hotel ID generated during hotel setup and uniquely identifies the hotel created in hms solution',
  `amenity_id` varchar(60)  COMMENT 'This column is generated when a amenity is created and uniquely represents a amenity in hms solution',
  `voucher_id` varchar(60)  COMMENT 'This column represents the voucher created for a given hotel and is mapped to a given hotel''s amenity'
)COMMENT='This table defines the Vouchers associated with a given amenity';

CREATE TABLE `hms_room_category_voucher_map` (
  `hotel_id` varchar(64)  COMMENT 'This column represents the unique hotel ID generated during hotel setup and uniquely identifies the hotel created in hms solution',
  `room_category_id` varchar(60)  COMMENT 'This column is generated when a room category is created and uniquely represents a room category in hms solution',
  `voucher_id` varchar(60)  COMMENT 'This column represents the voucher created for a given hotel and is mapped to a given hotel''s amenity'
)COMMENT='This table defines the Vouchers associated with a given room category';









