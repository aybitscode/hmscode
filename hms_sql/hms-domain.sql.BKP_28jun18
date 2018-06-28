CREATE DATABASE  IF NOT EXISTS `hms_local` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hms_local`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: hms_local
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hms_admin_event_log`
--

DROP TABLE IF EXISTS `hms_admin_event_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_admin_event_log` (
  `admin_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `event_log` varchar(500) NOT NULL,
  `event_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table records the events initiated and undertaken by a given admin';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_admin_event_log`
--

LOCK TABLES `hms_admin_event_log` WRITE;
/*!40000 ALTER TABLE `hms_admin_event_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_admin_event_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_billing_info`
--

DROP TABLE IF EXISTS `hms_billing_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_billing_info` (
  `invoice_id` int(11) NOT NULL,
  `booking_id` int(11) NOT NULL,
  `room_rent` decimal(5,0) NOT NULL,
  `facilities_charges` decimal(5,0) NOT NULL,
  `miscellaneous_charges` decimal(5,0) NOT NULL,
  `coupon_id` int(11) NOT NULL,
  `payment_option` varchar(45) NOT NULL,
  `total_amount` decimal(5,0) NOT NULL,
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `tax_amount` decimal(5,0) NOT NULL COMMENT 'The total applicable tax amount - service tax , CST, GST etc applicable on the current bill',
  `date_updated` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `invoice_barcode` varchar(45) NOT NULL,
  PRIMARY KEY (`invoice_id`),
  UNIQUE KEY `invoice_id_UNIQUE` (`invoice_id`),
  UNIQUE KEY `coupon_id_UNIQUE` (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_billing_info`
--

LOCK TABLES `hms_billing_info` WRITE;
/*!40000 ALTER TABLE `hms_billing_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_billing_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_booking_info`
--

DROP TABLE IF EXISTS `hms_booking_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_booking_info` (
  `user_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `checkin_date` datetime NOT NULL,
  `booking_id` int(11) NOT NULL COMMENT 'The entire billing details are mapped to this id - bill_id which is a foreign key to the billing_info table ',
  `checkout_date` datetime DEFAULT NULL COMMENT 'This refers to the check-in and check-out details and the no of rooms booked by an individual user.',
  `invoice_id` int(11) NOT NULL,
  `booking_status` int(11) NOT NULL COMMENT 'The Booking status is - 0 - undetermined, 1 - confirmed,2 - cancelled.',
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `date_updated` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  PRIMARY KEY (`user_id`,`hotel_id`,`room_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `invoice_id_UNIQUE` (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_booking_info`
--

LOCK TABLES `hms_booking_info` WRITE;
/*!40000 ALTER TABLE `hms_booking_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_booking_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_corporate`
--

DROP TABLE IF EXISTS `hms_corporate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_corporate` (
  `CORPORATE_ID` int(64) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(60) DEFAULT NULL,
  `OFFICE_PHONE` varchar(20) DEFAULT NULL,
  `MOBILE` int(20) DEFAULT NULL,
  `OFFICE_ADDRESS` varchar(2000) DEFAULT NULL,
  `IDENTIFICATION_PARAM` int(32) NOT NULL,
  `PAYMENT_PARAM` int(32) DEFAULT NULL,
  `company_status` int(11) NOT NULL DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.',
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`CORPORATE_ID`),
  UNIQUE KEY `user_id_UNIQUE` (`CORPORATE_ID`),
  UNIQUE KEY `IDENTIFICATION_PARAM_UNIQUE` (`IDENTIFICATION_PARAM`),
  UNIQUE KEY `MOBILE_UNIQUE` (`MOBILE`),
  UNIQUE KEY `PAYMENT_PARAM_UNIQUE` (`PAYMENT_PARAM`),
  KEY `customer_idx` (`CORPORATE_ID`) USING HASH KEY_BLOCK_SIZE=1024
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='This table holds the information of all the corporate data.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_corporate`
--

LOCK TABLES `hms_corporate` WRITE;
/*!40000 ALTER TABLE `hms_corporate` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_corporate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_customer`
--

DROP TABLE IF EXISTS `hms_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_customer` (
  `CUSTOMER_ID` int(64) NOT NULL AUTO_INCREMENT,
  `CORPORATE_ID` int(64) DEFAULT NULL,
  `FIRST_NAME` varchar(100) NOT NULL,
  `MIDDLE_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(60) NOT NULL,
  `EMAIL` varchar(60) NOT NULL,
  `MOBILE` int(20) NOT NULL,
  `HOME_ADDRESS` varchar(2000) DEFAULT NULL,
  `IDENTIFICATION_PARAM_ID` int(32) NOT NULL,
  `PAYMENT_PARAM` int(32) DEFAULT NULL,
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `hms_hotel_id` int(64) NOT NULL COMMENT 'This is the foreign key reference to the hotel id created in the hms_hotel table',
  `customer_status` int(11) NOT NULL DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.',
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `login_id` varchar(45) NOT NULL COMMENT 'The Login ID of a given user which they set in the hms solution',
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`CUSTOMER_ID`),
  UNIQUE KEY `user_id_UNIQUE` (`CUSTOMER_ID`),
  UNIQUE KEY `IDENTIFICATION_PARAM_ID_UNIQUE` (`IDENTIFICATION_PARAM_ID`),
  UNIQUE KEY `MOBILE_UNIQUE` (`MOBILE`),
  UNIQUE KEY `login_id_UNIQUE` (`login_id`),
  UNIQUE KEY `hms_hotel_id_UNIQUE` (`hms_hotel_id`),
  UNIQUE KEY `PAYMENT_PARAM_UNIQUE` (`PAYMENT_PARAM`),
  KEY `customer_idx` (`CUSTOMER_ID`) USING HASH KEY_BLOCK_SIZE=1024
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='This table holds the information of all the customer data.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_customer`
--

LOCK TABLES `hms_customer` WRITE;
/*!40000 ALTER TABLE `hms_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_customization`
--

DROP TABLE IF EXISTS `hms_customization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_customization` (
  `hotel_id` int(11) NOT NULL,
  `customization_map` varchar(1000) NOT NULL COMMENT 'This customization maps holds parameters to the corresponding mongo_db schema which hold the binary files, logos, images, and other details. This is a JSON and should accept only JSON based information in this map.',
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_customization`
--

LOCK TABLES `hms_customization` WRITE;
/*!40000 ALTER TABLE `hms_customization` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_customization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_employee`
--

DROP TABLE IF EXISTS `hms_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_employee` (
  `EMPLOYEE_ID` int(64) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(100) NOT NULL,
  `MIDDLE_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(60) NOT NULL,
  `EMAIL` varchar(60) NOT NULL,
  `MOBILE` int(20) NOT NULL,
  `HOME_ADDRESS` varchar(2000) DEFAULT NULL,
  `IDENTIFICATION_PARAM` int(32) NOT NULL,
  `employee_status` int(11) NOT NULL DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.',
  `login_id` varchar(45) NOT NULL COMMENT 'The Login ID of a given user which they set in the hms solution',
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `hms_hotel_id` int(64) NOT NULL COMMENT 'This is the foreign key reference to the hotel_id created in the hms_hotel table.',
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`EMPLOYEE_ID`),
  UNIQUE KEY `user_id_UNIQUE` (`EMPLOYEE_ID`),
  UNIQUE KEY `IDENTIFICATION_PARAM_UNIQUE` (`IDENTIFICATION_PARAM`),
  UNIQUE KEY `MOBILE_UNIQUE` (`MOBILE`),
  UNIQUE KEY `login_id_UNIQUE` (`login_id`),
  UNIQUE KEY `hms_hotel_id_UNIQUE` (`hms_hotel_id`),
  KEY `EMPLOYEE_IDx` (`EMPLOYEE_ID`) USING HASH KEY_BLOCK_SIZE=1024
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='This table holds the information of all the customer data.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_employee`
--

LOCK TABLES `hms_employee` WRITE;
/*!40000 ALTER TABLE `hms_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_events`
--

DROP TABLE IF EXISTS `hms_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_events` (
  `event_key` varchar(45) NOT NULL,
  `event_description` varchar(1000) NOT NULL,
  `event_id` int(11) NOT NULL,
  `event_type` varchar(45) NOT NULL COMMENT 'event_type denotes whether it is an admin event or it is an user event.',
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `event_key_UNIQUE` (`event_key`),
  UNIQUE KEY `event_id_UNIQUE` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table tracks of all the events related to both user and admin. This is a metadata table that holds all the event details.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_events`
--

LOCK TABLES `hms_events` WRITE;
/*!40000 ALTER TABLE `hms_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_facilities`
--

DROP TABLE IF EXISTS `hms_facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_facilities` (
  `facility_id` int(11) NOT NULL COMMENT 'The unique identifier to identify the facility assigned',
  `facility_key` varchar(45) NOT NULL COMMENT 'The key to define the facility to show during reporting etc.',
  `facility_description` varchar(100) NOT NULL,
  PRIMARY KEY (`facility_id`,`facility_key`),
  UNIQUE KEY `facility_id_UNIQUE` (`facility_id`),
  UNIQUE KEY `facility_key_UNIQUE` (`facility_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table defines the facilities that are provided for a given hotel';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_facilities`
--

LOCK TABLES `hms_facilities` WRITE;
/*!40000 ALTER TABLE `hms_facilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_hotel`
--

DROP TABLE IF EXISTS `hms_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_hotel` (
  `hotel_id` int(64) NOT NULL COMMENT 'Unique identifier to identify a given hotel',
  `hotel_name` varchar(100) NOT NULL COMMENT 'Hotel Name of the given hotel',
  `hotel_address` varchar(500) NOT NULL COMMENT 'Address of the given hotel',
  `hotel_rating` varchar(45) DEFAULT NULL COMMENT 'Rating of the hotel - whether it is 5-star,3-star,2-star etc',
  `hotel_registration_data` varbinary(1000) DEFAULT NULL COMMENT 'Registration Details of a given hotel w.r.t authorities',
  `hotel_geo_location` varchar(45) NOT NULL COMMENT 'Location co-ordinates of the hotel on location maps',
  `hotel_staff_count` int(64) NOT NULL COMMENT 'total no. of staff working in the hotel',
  PRIMARY KEY (`hotel_id`),
  UNIQUE KEY `hotel_id_UNIQUE` (`hotel_id`),
  UNIQUE KEY `hotel_geo_location_UNIQUE` (`hotel_geo_location`),
  UNIQUE KEY `hotel_registration_info_UNIQUE` (`hotel_registration_data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This is the primary table to store all the information regarding entity - Hotel in the HMS Solution';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_hotel`
--

LOCK TABLES `hms_hotel` WRITE;
/*!40000 ALTER TABLE `hms_hotel` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_hotel_room`
--

DROP TABLE IF EXISTS `hms_hotel_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_hotel_room` (
  `hotel_id` int(64) NOT NULL,
  `room_id` int(64) NOT NULL,
  `room_facilities` varchar(1000) NOT NULL,
  `room_occupancy` int(64) NOT NULL COMMENT 'The maximum capacity of the room to be occupied, max value can be defined for this room depending on the admin input',
  `room_status` int(11) NOT NULL COMMENT 'This column indicates whether the room is - 0 - room_not_available, 1 - available, 2 - booked, 3 - blocked_for_renovation',
  `room_category_id` int(11) NOT NULL COMMENT 'Room category defines the category of the room',
  PRIMARY KEY (`room_id`,`hotel_id`),
  UNIQUE KEY `hotel_id_UNIQUE` (`hotel_id`),
  UNIQUE KEY `room_id_UNIQUE` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table stores the information regarding the hotel and room mapping and the details associated with individual hotel rooms like - facilities, room category, occupancy, ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_hotel_room`
--

LOCK TABLES `hms_hotel_room` WRITE;
/*!40000 ALTER TABLE `hms_hotel_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_hotel_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_hotel_staff`
--

DROP TABLE IF EXISTS `hms_hotel_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_hotel_staff` (
  `hotel_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `role_privilege_list` varchar(1000) NOT NULL COMMENT 'The Role Privilege List refers to the list of privileges that are associated to a given user. This list is from the hms_role_privileges table.',
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'This column indicates when the hotel to user mapping row was created',
  PRIMARY KEY (`hotel_id`),
  UNIQUE KEY `hotel_id_UNIQUE` (`hotel_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_hotel_staff`
--

LOCK TABLES `hms_hotel_staff` WRITE;
/*!40000 ALTER TABLE `hms_hotel_staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_hotel_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_identification_params`
--

DROP TABLE IF EXISTS `hms_identification_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_identification_params` (
  `IDENTIFICATION_PARAM_ID` int(64) NOT NULL AUTO_INCREMENT,
  `IDENTIFICATION_PARAM_TYPE` varchar(64) NOT NULL,
  `IDENTIFICATION_PARAM_FILE` varchar(100) NOT NULL,
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`IDENTIFICATION_PARAM_ID`),
  UNIQUE KEY `IDENTIFICATION_PARAM_ID_UNIQUE` (`IDENTIFICATION_PARAM_ID`),
  KEY `id_param_idx` (`IDENTIFICATION_PARAM_ID`) USING HASH KEY_BLOCK_SIZE=1024
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='This table holds the information of all the customer data.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_identification_params`
--

LOCK TABLES `hms_identification_params` WRITE;
/*!40000 ALTER TABLE `hms_identification_params` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_identification_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_inventory_event_log`
--

DROP TABLE IF EXISTS `hms_inventory_event_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_inventory_event_log` (
  `hotel_id` int(11) NOT NULL,
  `inventory_event_id` int(11) NOT NULL,
  `inventory_event_log` varchar(1000) NOT NULL,
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`hotel_id`,`inventory_event_id`),
  UNIQUE KEY `hotel_id_UNIQUE` (`hotel_id`),
  UNIQUE KEY `inventory_event_id_UNIQUE` (`inventory_event_id`),
  UNIQUE KEY `inventory_event_log_UNIQUE` (`inventory_event_log`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_inventory_event_log`
--

LOCK TABLES `hms_inventory_event_log` WRITE;
/*!40000 ALTER TABLE `hms_inventory_event_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_inventory_event_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_inventory_item_group`
--

DROP TABLE IF EXISTS `hms_inventory_item_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_inventory_item_group` (
  `item_group_name` varchar(100) NOT NULL,
  `item_group_description` varchar(200) NOT NULL,
  UNIQUE KEY `item_group_name_UNIQUE` (`item_group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_inventory_item_group`
--

LOCK TABLES `hms_inventory_item_group` WRITE;
/*!40000 ALTER TABLE `hms_inventory_item_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_inventory_item_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_inventory_items`
--

DROP TABLE IF EXISTS `hms_inventory_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_inventory_items` (
  `hotel_id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `item_count` int(11) NOT NULL,
  `date_created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `item_id` int(11) NOT NULL,
  `item_group_name` varchar(100) NOT NULL,
  `date_modified` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `date_updated` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `item_status` int(11) NOT NULL COMMENT 'item_status - defines either the item is available, out of stock or undetermined.',
  `item_group` varchar(100) DEFAULT NULL COMMENT 'this denotes to which group does this item belongs to.',
  PRIMARY KEY (`hotel_id`,`item_id`,`item_name`),
  UNIQUE KEY `item_name_UNIQUE` (`item_name`),
  UNIQUE KEY `item_id_UNIQUE` (`item_id`),
  UNIQUE KEY `item_group_name_UNIQUE` (`item_group_name`),
  UNIQUE KEY `date_modified_UNIQUE` (`date_modified`),
  UNIQUE KEY `date_created_UNIQUE` (`date_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table stores all the details related to the inventory of a given hotel';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_inventory_items`
--

LOCK TABLES `hms_inventory_items` WRITE;
/*!40000 ALTER TABLE `hms_inventory_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_inventory_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_system_params`
--

DROP TABLE IF EXISTS `hms_system_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_system_params` (
  `param_name` varchar(45) NOT NULL,
  `param_value` varchar(45) NOT NULL,
  `param_description` varchar(500) NOT NULL,
  UNIQUE KEY `system_param_name_UNIQUE` (`param_name`),
  UNIQUE KEY `system_param_UNIQUE` (`param_value`),
  UNIQUE KEY `param_description_UNIQUE` (`param_description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table holds the details for the system level configuration of the hms data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_system_params`
--

LOCK TABLES `hms_system_params` WRITE;
/*!40000 ALTER TABLE `hms_system_params` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_system_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_user`
--

DROP TABLE IF EXISTS `hms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_user` (
  `USER_ID` int(64) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(100) NOT NULL,
  `MIDDLE_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(60) NOT NULL,
  `EMAIL` varchar(60) NOT NULL,
  `MOBILE` int(20) NOT NULL,
  `HOME_ADDRESS` varchar(2000) DEFAULT NULL,
  `IDENTIFICATION_PARAM` int(32) NOT NULL,
  `PAYMENT_PARAM` int(32) DEFAULT NULL,
  `DATE_CREATED` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `user_status` int(11) NOT NULL DEFAULT '0' COMMENT 'This column states whether the user is active, inactive, blocked, deleted - the possible values are 0 - Inactive, 1 - Active, 2 - blocked, 3 - deleted. Default is 0.',
  `DATE_MODIFIED` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `login_id` varchar(45) NOT NULL COMMENT 'The Login ID of a given user which they set in the hms solution',
  `DATE_UPDATED` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `DATE_DELETED` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `user_id_UNIQUE` (`USER_ID`),
  UNIQUE KEY `IDENTIFICATION_PARAM_UNIQUE` (`IDENTIFICATION_PARAM`),
  UNIQUE KEY `MOBILE_UNIQUE` (`MOBILE`),
  UNIQUE KEY `login_id_UNIQUE` (`login_id`),
  UNIQUE KEY `PAYMENT_PARAM_UNIQUE` (`PAYMENT_PARAM`),
  KEY `user_idx` (`USER_ID`) USING HASH KEY_BLOCK_SIZE=1024
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='This table holds the information of all the user data be it either - staff, customer or master admin.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_user`
--

LOCK TABLES `hms_user` WRITE;
/*!40000 ALTER TABLE `hms_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_user_event_log`
--

DROP TABLE IF EXISTS `hms_user_event_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_user_event_log` (
  `user_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `event_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Time of the user event',
  `event_log` varchar(400) NOT NULL COMMENT 'This log describes what activity has happened in that event.',
  PRIMARY KEY (`user_id`,`event_id`,`event_time`),
  UNIQUE KEY `event_id_UNIQUE` (`event_id`),
  UNIQUE KEY `event_time_UNIQUE` (`event_time`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table tracks all the events associated with one specific user starting from the time of booking room to checkout.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_user_event_log`
--

LOCK TABLES `hms_user_event_log` WRITE;
/*!40000 ALTER TABLE `hms_user_event_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_user_event_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_user_identification_param_metadata`
--

DROP TABLE IF EXISTS `hms_user_identification_param_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_user_identification_param_metadata` (
  `param_name` varchar(45) NOT NULL,
  `param_description` varchar(500) NOT NULL,
  `param_attributes` varchar(500) NOT NULL,
  `param_group_id` int(11) NOT NULL,
  PRIMARY KEY (`param_group_id`),
  UNIQUE KEY `param_name_UNIQUE` (`param_name`),
  UNIQUE KEY `param_group_id_UNIQUE` (`param_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This defines the identification parameters and their attributes like - PASSPORT NO, PAN CARD, Aadhar Card Number and the size of these parameters and their other attributes.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_user_identification_param_metadata`
--

LOCK TABLES `hms_user_identification_param_metadata` WRITE;
/*!40000 ALTER TABLE `hms_user_identification_param_metadata` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_user_identification_param_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_user_identification_params`
--

DROP TABLE IF EXISTS `hms_user_identification_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_user_identification_params` (
  `user_id` int(11) NOT NULL,
  `user_identification_param_name` varchar(60) NOT NULL,
  `user_identification_param_value` varchar(100) NOT NULL,
  `user_identification_param_group_id` int(11) NOT NULL COMMENT 'This PARAM ID is to be passed to the mongo_db to fetch the binary data of the user documents saved.',
  PRIMARY KEY (`user_id`,`user_identification_param_name`,`user_identification_param_value`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_identification_param_name_UNIQUE` (`user_identification_param_name`),
  UNIQUE KEY `user_identification_param_value_UNIQUE` (`user_identification_param_value`),
  UNIQUE KEY `user_identification_param_id_UNIQUE` (`user_identification_param_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table has the information about the PII info of the user like Aadhar, Passport No, Voter ID, PAN Card etc., Depending on the security settings set in the system params these details are encrypted and saved in the DB.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_user_identification_params`
--

LOCK TABLES `hms_user_identification_params` WRITE;
/*!40000 ALTER TABLE `hms_user_identification_params` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_user_identification_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_user_privileges`
--

DROP TABLE IF EXISTS `hms_user_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_user_privileges` (
  `privilege_key` varchar(45) NOT NULL COMMENT 'The privilege key for the privilege that is being created',
  `privilege_description` varchar(200) NOT NULL COMMENT 'The Description being given to a created privilege',
  `privilege_id` int(11) NOT NULL COMMENT 'The unique identifier for any given privilege',
  PRIMARY KEY (`privilege_id`,`privilege_key`),
  UNIQUE KEY `privilege_id_UNIQUE` (`privilege_id`),
  UNIQUE KEY `privilege_key_UNIQUE` (`privilege_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_user_privileges`
--

LOCK TABLES `hms_user_privileges` WRITE;
/*!40000 ALTER TABLE `hms_user_privileges` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_user_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_user_role`
--

DROP TABLE IF EXISTS `hms_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_user_role` (
  `role_id` int(11) NOT NULL COMMENT 'The Role ID is primary key of the hms_user_role table and any role can be uniquely identified ',
  `role_name` varchar(45) NOT NULL COMMENT 'The Name of the given role eg: ADMIN, SUPER_ADMIN,MASTER_ADMIN etc',
  `role_privilege` varchar(1000) NOT NULL COMMENT 'The Role Privileges is the list  that a corresponding role has. This has to be a list that consists of privilege objects from the hms_user_privileges table.',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='The hms_user_role maps the user created in hms_user to a specific role assigned. This role is subject to some predefined authorization and authentication privileges and the roles can be altered only by super or master admin';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_user_role`
--

LOCK TABLES `hms_user_role` WRITE;
/*!40000 ALTER TABLE `hms_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hms_version_info`
--

DROP TABLE IF EXISTS `hms_version_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hms_version_info` (
  `db_version` varchar(100) NOT NULL,
  `application_version` varchar(100) NOT NULL,
  `ux_version` varchar(100) NOT NULL,
  UNIQUE KEY `db_version_UNIQUE` (`db_version`),
  UNIQUE KEY `application_version_UNIQUE` (`application_version`),
  UNIQUE KEY `ux_version_UNIQUE` (`ux_version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hms_version_info`
--

LOCK TABLES `hms_version_info` WRITE;
/*!40000 ALTER TABLE `hms_version_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `hms_version_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `successful_bookings_current_month_view`
--

DROP TABLE IF EXISTS `successful_bookings_current_month_view`;
/*!50001 DROP VIEW IF EXISTS `successful_bookings_current_month_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `successful_bookings_current_month_view` AS SELECT 
 1 AS `USER_ID`,
 1 AS `FIRST_NAME`,
 1 AS `MIDDLE_NAME`,
 1 AS `LAST_NAME`,
 1 AS `EMAIL`,
 1 AS `MOBILE`,
 1 AS `HOME_ADDRESS`,
 1 AS `IDENTIFICATION_PARAM`,
 1 AS `PAYMENT_PARAM`,
 1 AS `DATE_CREATED`,
 1 AS `user_status`,
 1 AS `DATE_MODIFIED`,
 1 AS `login_id`,
 1 AS `DATE_UPDATED`,
 1 AS `DATE_DELETED`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `successful_bookings_view`
--

DROP TABLE IF EXISTS `successful_bookings_view`;
/*!50001 DROP VIEW IF EXISTS `successful_bookings_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `successful_bookings_view` AS SELECT 
 1 AS `USER_ID`,
 1 AS `FIRST_NAME`,
 1 AS `MIDDLE_NAME`,
 1 AS `LAST_NAME`,
 1 AS `EMAIL`,
 1 AS `MOBILE`,
 1 AS `HOME_ADDRESS`,
 1 AS `IDENTIFICATION_PARAM`,
 1 AS `PAYMENT_PARAM`,
 1 AS `DATE_CREATED`,
 1 AS `user_status`,
 1 AS `DATE_MODIFIED`,
 1 AS `login_id`,
 1 AS `DATE_UPDATED`,
 1 AS `DATE_DELETED`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'hms_local'
--

--
-- Dumping routines for database 'hms_local'
--

--
-- Final view structure for view `successful_bookings_current_month_view`
--

/*!50001 DROP VIEW IF EXISTS `successful_bookings_current_month_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `successful_bookings_current_month_view` AS select `hms_user`.`USER_ID` AS `USER_ID`,`hms_user`.`FIRST_NAME` AS `FIRST_NAME`,`hms_user`.`MIDDLE_NAME` AS `MIDDLE_NAME`,`hms_user`.`LAST_NAME` AS `LAST_NAME`,`hms_user`.`EMAIL` AS `EMAIL`,`hms_user`.`MOBILE` AS `MOBILE`,`hms_user`.`HOME_ADDRESS` AS `HOME_ADDRESS`,`hms_user`.`IDENTIFICATION_PARAM` AS `IDENTIFICATION_PARAM`,`hms_user`.`PAYMENT_PARAM` AS `PAYMENT_PARAM`,`hms_user`.`DATE_CREATED` AS `DATE_CREATED`,`hms_user`.`user_status` AS `user_status`,`hms_user`.`DATE_MODIFIED` AS `DATE_MODIFIED`,`hms_user`.`login_id` AS `login_id`,`hms_user`.`DATE_UPDATED` AS `DATE_UPDATED`,`hms_user`.`DATE_DELETED` AS `DATE_DELETED` from `hms_user` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `successful_bookings_view`
--

/*!50001 DROP VIEW IF EXISTS `successful_bookings_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `successful_bookings_view` AS select `hms_user`.`USER_ID` AS `USER_ID`,`hms_user`.`FIRST_NAME` AS `FIRST_NAME`,`hms_user`.`MIDDLE_NAME` AS `MIDDLE_NAME`,`hms_user`.`LAST_NAME` AS `LAST_NAME`,`hms_user`.`EMAIL` AS `EMAIL`,`hms_user`.`MOBILE` AS `MOBILE`,`hms_user`.`HOME_ADDRESS` AS `HOME_ADDRESS`,`hms_user`.`IDENTIFICATION_PARAM` AS `IDENTIFICATION_PARAM`,`hms_user`.`PAYMENT_PARAM` AS `PAYMENT_PARAM`,`hms_user`.`DATE_CREATED` AS `DATE_CREATED`,`hms_user`.`user_status` AS `user_status`,`hms_user`.`DATE_MODIFIED` AS `DATE_MODIFIED`,`hms_user`.`login_id` AS `login_id`,`hms_user`.`DATE_UPDATED` AS `DATE_UPDATED`,`hms_user`.`DATE_DELETED` AS `DATE_DELETED` from `hms_user` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-09 17:10:58
