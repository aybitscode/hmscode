CREATE TABLE `hms_version_info` (
  `db_version` varchar(100) NOT NULL,
  `application_version` varchar(100) NOT NULL,
  `ux_version` varchar(100) NOT NULL
);

CREATE TABLE `hms_hotel` (
  `hotel_id` int(64) NOT NULL COMMENT 'Unique identifier to identify a given hotel',
  `hotel_name` varchar(100) NOT NULL COMMENT 'Hotel Name of the given hotel',
  `hotel_address` varchar(500) NOT NULL COMMENT 'Address of the given hotel',
  `hotel_rating` varchar(45) DEFAULT NULL COMMENT 'Rating of the hotel - whether it is 5-star,3-star,2-star etc',
  `hotel_staff_count` int(64) NOT NULL COMMENT 'total no. of staff working in the hotel',
  `hotel_room_count` int(64) DEFAULT NULL,
  `hotel_bed_count` int(64) DEFAULT NULL,
  `hotel_logo` varchar(100) DEFAULT NULL,
  `hotel_room_doorno_format` varchar(45) DEFAULT NULL,
  `hotel_status` int(11) NOT NULL COMMENT 'This column saves the status details whether hotel is enabled,active or disabled',
  `hotel_token_id` varchar(45) NOT NULL COMMENT 'This is an unique token to identify the hotel along with the other parameter which is hotel_id'
)  COMMENT='This is the primary table to store all the information regarding entity - Hotel in the HMS Solution';


CREATE TABLE `hms_billing_info` (
  `invoice_id` varchar(60) NOT NULL,
  `booking_id` varchar(60) NOT NULL,
  `room_rent` decimal(5,0) NOT NULL,
  `facilities_charges` decimal(5,0) NOT NULL,
  `miscellaneous_charges` decimal(5,0) NOT NULL,
  `coupon_id` varchar(60) NOT NULL,
  `payment_option` varchar(45) NOT NULL,
  `total_amount` decimal(5,0) NOT NULL,
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `tax_amount` decimal(5,0) NOT NULL COMMENT 'The total applicable tax amount - service tax , CST, GST etc applicable on the current bill',
  `date_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `invoice_barcode` varchar(100) NOT NULL
);

/* HMS_ADMIN_EVENT_LOG Table */
CREATE TABLE `hms_admin_event_log` (
  `admin_id` varchar(60) NOT NULL,
  `hotel_id` varchar(20) NOT NULL,
  `event_id` varchar(60) NOT NULL,
  `event_log` varchar(500) NOT NULL,
  `event_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
) COMMENT='This table records the events initiated and undertaken by a given admin';

/* HMS_CORPORATE Table */
CREATE TABLE `hms_corporate` (
  `CORPORATE_ID` varchar(60) NOT NULL,
  `COMPANY_NAME` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(60) DEFAULT NULL,
  `OFFICE_PHONE` varchar(20) DEFAULT NULL,
  `MOBILE` int(20) DEFAULT NULL,
  `OFFICE_ADDRESS` varchar(2000) DEFAULT NULL,
  `IDENTIFICATION_PARAM` varchar(60) NOT NULL,
  `PAYMENT_PARAM` varchar(60) DEFAULT NULL,
  `company_status` int(11) NOT NULL DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.',
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `hotel_id` int(64) NOT NULL,
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
)COMMENT='This table holds the information of all the corporate data.';

/* HMS_VOUCHER Table */
CREATE TABLE `hms_voucher` (
  `voucher_id` varchar(60) NOT NULL,
  `voucher_name` varchar(60) DEFAULT NULL,
  `voucher_description` varchar(200) DEFAULT NULL,
  `voucher_expiry_date` datetime(6) DEFAULT NULL,
  `voucer_start_date` datetime(6) DEFAULT NULL,
  `voucher_discount` double DEFAULT NULL,
  `vouhcer_status` int(11) DEFAULT NULL
);

/* HMS_SYSTEM_PARAMS Table */
CREATE TABLE `hms_system_params` (
  `param_name` varchar(45) NOT NULL,
  `param_value` varchar(45) NOT NULL,
  `param_description` varchar(500) NOT NULL
)COMMENT='This table stores the details for the system level configuration of the hms data';


/* HMS_ROOM_CATEGORY Table */
CREATE TABLE `hms_room_category` (
  `category_id` varchar(60) NOT NULL,
  `category_name` varchar(120) DEFAULT NULL,
  `category_description` varchar(400) DEFAULT NULL,
  `bed_count` int(11) DEFAULT NULL,
  `category_price` double NOT NULL DEFAULT '0',
  `room_count` int(11) DEFAULT NULL,
  `room_capacity` int(11) DEFAULT NULL,
  `adult_occupancy` int(11) DEFAULT NULL,
  `child_occupancy` int(11) DEFAULT NULL,
  `category_facilities` varchar(1000) DEFAULT NULL,
  `category_amenities` varchar(1000) DEFAULT NULL,
  `category_services` varchar(1000) DEFAULT NULL,
  `category_vouchers` varchar(1000) DEFAULT NULL,
  `is_category_active` int(11) DEFAULT NULL 
)COMMENT = 'This table stores the details for the various categories and prices,services,facilities,vouchers assigned to each category';

/* HMS_INVENTORY_ITEMS Table */
CREATE TABLE `hms_inventory_items` (
  `hotel_id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `item_count` int(11) NOT NULL,
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `item_id` int(11) NOT NULL,
  `item_group_name` varchar(100) NOT NULL,
  `date_modified` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `date_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `item_status` int(11) NOT NULL COMMENT 'item_status - defines either the item is available, out of stock or undetermined.',
  `item_group` varchar(100) DEFAULT NULL COMMENT 'this denotes to which group does this item belongs to.'
)COMMENT='This table stores all the details related to the inventory of a given hotel';

/* HMS_INVENTORY_ITEM_GROUP Table */
CREATE TABLE `hms_inventory_item_group` (
  `item_group_name` varchar(100) NOT NULL,
  `item_group_description` varchar(200) NOT NULL
);

/* HMS_INVENTORY_EVENT_LOG Table */
CREATE TABLE `hms_inventory_event_log` (
  `hotel_id` varchar(60) NOT NULL,
  `inventory_event_id` int(11) NOT NULL,
  `inventory_event_log` varchar(1000) NOT NULL,
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `user_id` int(11) NOT NULL
);


/* HMS_IDENTIFICATION_PARAMS Table */
CREATE TABLE `hms_identification_params` (
  `IDENTIFICATION_PARAM_ID` varchar(60) NOT NULL,
  `IDENTIFICATION_PARAM_TYPE` varchar(60) NOT NULL,
  `IDENTIFICATION_PARAM_FILE` varchar(100) NOT NULL,
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
)COMMENT='This table holds the information of all the customer data.';

/* HMS_HOTEL_ROOM Table */
CREATE TABLE `hms_hotel_room` (
  `hotel_id` varchar(60) NOT NULL,
  `room_id` varchar(60) NOT NULL,
  `room_facilities` varchar(1000) NOT NULL,
  `room_occupancy` int(64) NOT NULL COMMENT 'The maximum capacity of the room to be occupied, max value can be defined for this room depending on the admin input',
  `room_status` int(11) NOT NULL COMMENT 'This column indicates whether the room is - 0 - room_not_available, 1 - available, 2 - booked, 3 - blocked_for_renovation',
  `room_category_id` varchar(60) NOT NULL COMMENT 'Room category defines the category of the room'
) COMMENT='This table stores the information regarding the hotel and room mapping and the details associated with individual hotel rooms like - facilities, room category, occupancy, ';


/* HMS_FACILITY Table */
CREATE TABLE `hms_facility` (
  `facility_id` varchar(60) NOT NULL COMMENT 'The unique identifier to identify the facility assigned',
  `facility_key` varchar(45) NOT NULL COMMENT 'The key to define the facility to show during reporting etc.',
  `facility_description` varchar(100) NOT NULL,
  `facility_availability` int(11) NOT NULL DEFAULT '1',
  `is_facility_chargeable` int(11) NOT NULL DEFAULT '0',
  `facility_type` int(11) NOT NULL,
  `facility_price` double NOT NULL DEFAULT '0',
  `applicable_voucher_id` varchar(60) NOT NULL 
)COMMENT='This table defines the facilities that are provided for a given hotel';


/* HMS_EVENT Table */
CREATE TABLE `hms_event` (
  `event_key` varchar(60) NOT NULL,
  `event_description` varchar(1000) NOT NULL,
  `event_id` varchar(60) NOT NULL,
  `event_type` varchar(45) NOT NULL COMMENT 'event_type denotes whether it is an admin event or it is an user event.'
)COMMENT='This table tracks of all the events related to both user and admin. This is a metadata table that holds all the event details.';


/* HMS_EMPLOYEE Table */
CREATE TABLE `hms_employee` (
  `EMPLOYEE_ID` varchar(60) NOT NULL,
  `FIRST_NAME` varchar(100) NOT NULL,
  `MIDDLE_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(60) NOT NULL,
  `EMAIL` varchar(60) NOT NULL,
  `MOBILE` int(20) NOT NULL,
  `HOME_ADDRESS` varchar(2000) DEFAULT NULL,
  `IDENTIFICATION_PARAM` varchar(60) NOT NULL,
  `employee_status` int(11) NOT NULL DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.',
  `login_id` varchar(60) NOT NULL COMMENT 'The Login ID of a given user which they set in the hms solution',
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `employee_role` varchar(60) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `hms_hotel_id` int(64) NOT NULL COMMENT 'This is the foreign key reference to the hotel_id created in the hms_hotel table.',
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
)COMMENT='This table holds the information of all the customer data.';

/* HMS_CUSTOMIZATION Table */
CREATE TABLE `hms_customization` (
  `hotel_id` varchar(60) NOT NULL,
  `customization_map` varchar(1000) NOT NULL COMMENT 'This customization maps holds parameters to the corresponding mongo_db schema which hold the binary files, logos, images, and other details. This is a JSON and should accept only JSON based information in this map.'
) ;

/* HMS_HOTEL_REGISTRATION_DATA Table */
CREATE TABLE `hms_hotel_registration_data` (
  `registration_data_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `building_permit_no` varchar(200) DEFAULT NULL,
  `fire_safety_permit_no` varchar(200) DEFAULT NULL,
  `police_license_no` varchar(200) DEFAULT NULL,
  `health_trade_license_no` varchar(200) DEFAULT NULL,
  `liquor_license_no` varchar(200) DEFAULT NULL,
  `fssai_license_no` varchar(200) DEFAULT NULL,
  `gst_license_no` varchar(200) DEFAULT NULL,
  `esi_registration_no` varchar(200) DEFAULT NULL,
  `epf_registration_no` varchar(200) DEFAULT NULL
);

/* HMS_CUSTOMER Table */
CREATE TABLE `hms_customer` (
  `CUSTOMER_ID` varchar(60) NOT NULL,
  `CORPORATE_ID` varchar(60) DEFAULT NULL,
  `FIRST_NAME` varchar(100) NOT NULL,
  `MIDDLE_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(60) NOT NULL,
  `EMAIL` varchar(60) NOT NULL,
  `MOBILE` int(20) NOT NULL,
  `HOME_ADDRESS` varchar(2000) DEFAULT NULL,
  `IDENTIFICATION_PARAM_ID` varchar(60) NOT NULL,
  `PAYMENT_PARAM` varchar(60) DEFAULT NULL,
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `hms_hotel_id` int(64) NOT NULL COMMENT 'This is the foreign key reference to the hotel id created in the hms_hotel table',
  `customer_status` int(11) NOT NULL DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.',
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
) COMMENT='This table holds the information of all the customer data.';

/* HMS_BOOKING_INFO */
CREATE TABLE `hms_booking_info` (
  `customer_id` varchar(60) NOT NULL,
  `hotel_id` varchar(60) NOT NULL,
  `room_id` varchar(60) NOT NULL,
  `checkin_date` datetime NOT NULL,
  `booking_id` varchar(60) NOT NULL COMMENT 'The entire billing details are mapped to this id - bill_id which is a foreign key to the billing_info table ',
  `checkout_date` datetime DEFAULT NULL COMMENT 'This refers to the check-in and check-out details and the no of rooms booked by an individual user.',
  `invoice_id` varchar(60) NOT NULL,
  `booking_status` varchar(60) NOT NULL COMMENT 'The Booking status is - 0 - undetermined, 1 - confirmed,2 - cancelled.',
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `date_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);










