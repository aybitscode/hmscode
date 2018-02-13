-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `billing`
--

DROP TABLE IF EXISTS `billing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billing` (
  `invoiceID` varchar(25) NOT NULL,
  `booking_cgst` varchar(45) DEFAULT NULL,
  `booking_sgst` varchar(45) DEFAULT NULL,
  `service_cgst` varchar(45) DEFAULT NULL,
  `service_sgst` varchar(45) DEFAULT NULL,
  `extraBedCharges` varchar(45) DEFAULT NULL,
  `employeeID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`invoiceID`),
  KEY `billing_employee` (`employeeID`),
  CONSTRAINT `billing_employee` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `bookingID` varchar(11) NOT NULL,
  `bookingDate` timestamp(6) NULL DEFAULT NULL,
  `bookedDate` timestamp(6) NULL DEFAULT NULL,
  `checkoutDate` timestamp(6) NULL DEFAULT NULL,
  `roomCategoryID` varchar(25) NOT NULL,
  `roomDoorNumber` varchar(25) NOT NULL,
  `mobile` varchar(25) NOT NULL,
  `bookingRoomCost` varchar(25) DEFAULT NULL,
  `facilitiesCost` varchar(45) DEFAULT NULL,
  `bookingTotalNights` varchar(25) DEFAULT NULL,
  `grossAmount` varchar(45) DEFAULT NULL,
  `couponName` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `paymentMode` varchar(45) DEFAULT NULL,
  `invoiceID` varchar(45) DEFAULT NULL,
  `employeeID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bookingID`,`roomDoorNumber`),
  KEY `booking_ibfk_1` (`roomCategoryID`),
  KEY `booking_ibfk_2` (`roomDoorNumber`),
  KEY `booking_ibfk_3` (`mobile`),
  KEY `booking_coupon_idx` (`couponName`),
  KEY `booking_invoice_idx` (`invoiceID`),
  KEY `booking_emplyeeID_idx` (`employeeID`),
  CONSTRAINT `booking_coupon` FOREIGN KEY (`couponName`) REFERENCES `coupons` (`couponName`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`roomCategoryID`) REFERENCES `roomcategory` (`roomCategoryID`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`roomDoorNumber`) REFERENCES `rooms` (`roomDoorNumber`),
  CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`mobile`) REFERENCES `customers` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checkin`
--

DROP TABLE IF EXISTS `checkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkin` (
  `checkInID` varchar(25) NOT NULL,
  `checkInDate` timestamp(6) NULL DEFAULT NULL,
  `bookingID` varchar(25) NOT NULL,
  `totalAdults` varchar(45) DEFAULT NULL,
  `totalChilds` varchar(45) DEFAULT NULL,
  `advanceAmt` varchar(25) NOT NULL,
  `employeeID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`checkInID`),
  KEY `bookingID` (`bookingID`),
  KEY `employeeID_checkin_idx` (`employeeID`),
  CONSTRAINT `bookingID` FOREIGN KEY (`bookingID`) REFERENCES `booking` (`bookingID`),
  CONSTRAINT `employeeID_checkin` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checkout`
--

DROP TABLE IF EXISTS `checkout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkout` (
  `checkOutID` varchar(25) NOT NULL,
  `bookingID` varchar(25) NOT NULL,
  `checkOutDate` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`checkOutID`),
  KEY `bookingID_checkout` (`bookingID`),
  CONSTRAINT `bookingID_checkout` FOREIGN KEY (`bookingID`) REFERENCES `booking` (`bookingID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupons` (
  `couponID` varchar(10) NOT NULL,
  `couponName` varchar(25) NOT NULL,
  `couponDiscount` varchar(100) NOT NULL,
  `couponType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`couponID`),
  UNIQUE KEY `couponName_UNIQUE` (`couponName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
insert into coupons(couponID, couponName, couponDiscount, couponType)values('CPN001', 'NO_DISCOUNT', '0.00', 'GENERAL');
--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `customerID` varchar(50) NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(45) NOT NULL,
  `addrs` varchar(500) DEFAULT NULL,
  `points` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `employeeID` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` text,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `mode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employeeID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
insert into employees(employeeID,firstName,lastName,username,password,email,mobile,birthDate,gender,role,mode)values('E001','Master','Admin','admin','¦ù9§Èç‚x“ùqÔäH-‰','test@domain.com','1234567890','2017-12-10','MALE','ADMIN','OFF');
--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expenses` (
  `requestId` varchar(25) NOT NULL,
  `department` varchar(45) DEFAULT NULL,
  `paidTo` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `createdDate` timestamp(6) NULL DEFAULT NULL,
  `employeeID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`requestId`),
  KEY `expense_employee_idx` (`employeeID`),
  CONSTRAINT `expense_employee` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `hotelID` varchar(10) NOT NULL,
  `hotelName` varchar(25) NOT NULL,
  `hotelDesc` varchar(100) NOT NULL,
  `hotelTotalRooms` varchar(25) NOT NULL,
  `hotelTotalFloors` varchar(25) NOT NULL,
  PRIMARY KEY (`hotelID`),
  UNIQUE KEY `hotelName_UNIQUE` (`hotelName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `miscellaneous`
--

DROP TABLE IF EXISTS `miscellaneous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous` (
  `serviceID` int(11) NOT NULL AUTO_INCREMENT,
  `serviceName` varchar(45) NOT NULL,
  `serviceDesc` varchar(45) NOT NULL,
  `servicePrice` varchar(45) NOT NULL,
  `bookingID` varchar(45) NOT NULL,
  PRIMARY KEY (`serviceID`),
  KEY `misc_booking` (`bookingID`),
  CONSTRAINT `misc_booking` FOREIGN KEY (`bookingID`) REFERENCES `booking` (`bookingID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `slNo` int(4) NOT NULL,
  `particulars` text NOT NULL,
  `amount` varchar(65) NOT NULL,
  PRIMARY KEY (`slNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roomcapacity`
--

DROP TABLE IF EXISTS `roomcapacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomcapacity` (
  `roomCapacityID` varchar(10) NOT NULL,
  `roomCapacityName` varchar(25) NOT NULL,
  `roomCapacityAdults` varchar(25) NOT NULL,
  `roomCapacityChilds` varchar(25) NOT NULL,
  `totalCapacity` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`roomCapacityID`),
  UNIQUE KEY `roomCapacityName_UNIQUE` (`roomCapacityName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roomcategory`
--

DROP TABLE IF EXISTS `roomcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomcategory` (
  `roomCategoryID` varchar(25) NOT NULL,
  `roomCategoryName` varchar(25) NOT NULL,
  `roomCategoryDesc` varchar(45) NOT NULL,
  `roomCapacityName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`roomCategoryID`),
  KEY `room_capacity_idx` (`roomCapacityName`),
  CONSTRAINT `room_capacity` FOREIGN KEY (`roomCapacityName`) REFERENCES `roomcapacity` (`roomCapacityName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roomfacilities`
--

DROP TABLE IF EXISTS `roomfacilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomfacilities` (
  `roomFacilitiesID` varchar(10) NOT NULL,
  `roomFacilitiesName` varchar(25) DEFAULT NULL,
  `roomFacilitiesDesc` varchar(100) DEFAULT NULL,
  `roomFacilitiesPrice` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`roomFacilitiesID`),
  UNIQUE KEY `roomFacilitiesName_UNIQUE` (`roomFacilitiesName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
insert into roomfacilities(roomFacilitiesID, roomFacilitiesName, roomFacilitiesDesc, roomFacilitiesPrice)values('RFC001', 'NONE', 'NO FACILITIES', '0.00');
--
-- Table structure for table `roomprice`
--

DROP TABLE IF EXISTS `roomprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomprice` (
  `roomPriceID` varchar(10) NOT NULL,
  `roomCategoryID` varchar(45) NOT NULL,
  `roomPrice` varchar(45) NOT NULL,
  PRIMARY KEY (`roomPriceID`),
  UNIQUE KEY `roomCategoryID_UNIQUE` (`roomCategoryID`),
  KEY `roomCategoryID` (`roomCategoryID`),
  CONSTRAINT `roomprice_ibfk_1` FOREIGN KEY (`roomCategoryID`) REFERENCES `roomcategory` (`roomCategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roomprice_facilities`
--

DROP TABLE IF EXISTS `roomprice_facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomprice_facilities` (
  `slno` int(11) NOT NULL AUTO_INCREMENT,
  `roomPriceID` varchar(25) NOT NULL,
  `roomFacilitiesName` varchar(25) NOT NULL,
  PRIMARY KEY (`slno`),
  KEY `roomprice_facilities_ibfk_1` (`roomPriceID`),
  KEY `roomprice_facilities_ibfk_2` (`roomFacilitiesName`),
  CONSTRAINT `roomprice_facilities_ibfk_1` FOREIGN KEY (`roomPriceID`) REFERENCES `roomprice` (`roomPriceID`),
  CONSTRAINT `roomprice_facilities_ibfk_2` FOREIGN KEY (`roomFacilitiesName`) REFERENCES `roomfacilities` (`roomFacilitiesName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `roomID` varchar(45) NOT NULL,
  `roomDoorNumber` varchar(45) DEFAULT NULL,
  `roomCategoryID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`roomID`),
  UNIQUE KEY `roomDoorNumber_UNIQUE` (`roomDoorNumber`),
  KEY `roomCategoryID` (`roomCategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `seasons`
--

DROP TABLE IF EXISTS `seasons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seasons` (
  `seasonID` varchar(50) NOT NULL,
  `seasonName` varchar(25) DEFAULT NULL,
  `dateStart` date DEFAULT NULL,
  `dateEnd` date DEFAULT NULL,
  `couponName` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`seasonID`),
  UNIQUE KEY `seasonName_UNIQUE` (`seasonName`),
  KEY `couponName` (`couponName`),
  CONSTRAINT `seasons_ibfk_1` FOREIGN KEY (`couponName`) REFERENCES `coupons` (`couponName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tax_rules`
--

DROP TABLE IF EXISTS `tax_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tax_rules` (
  `taxID` varchar(10) NOT NULL,
  `lowerbound` varchar(25) NOT NULL,
  `upperbound` varchar(100) NOT NULL,
  `cgst` varchar(45) DEFAULT NULL,
  `sgst` varchar(45) DEFAULT NULL,
  `taxpercentage` varchar(25) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`taxID`),
  UNIQUE KEY `tax_type_UNIQUE` (`lowerbound`,`upperbound`,`category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-11 21:52:30
