-- MySQL dump 10.14  Distrib 5.5.56-MariaDB, for Linux (x86_64)
--
-- Host: mysql2.cs.stonybrook.edu    Database: jiama
-- ------------------------------------------------------
-- Server version	5.5.25

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
--

USE ajax305;
--
-- Table structure for table `advpurchasediscount`
--

DROP TABLE IF EXISTS `advpurchasediscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advpurchasediscount` (
  `AirlineID` char(2) NOT NULL,
  `Days` int(11) NOT NULL,
  `DiscountRate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AirlineID`,`Days`),
  CONSTRAINT `advpurchasediscount_ibfk_1` FOREIGN KEY (`AirlineID`) REFERENCES `airline` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advpurchasediscount`
--

LOCK TABLES `advpurchasediscount` WRITE;
/*!40000 ALTER TABLE `advpurchasediscount` DISABLE KEYS */;
INSERT INTO `advpurchasediscount` VALUES ('AJ',10000,75.00),('AJ',100000,35.00),('AJ',1000000,25.00);
/*!40000 ALTER TABLE `advpurchasediscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airline` (
  `Id` char(2) NOT NULL,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline`
--

LOCK TABLES `airline` WRITE;
/*!40000 ALTER TABLE `airline` DISABLE KEYS */;
INSERT INTO `airline` VALUES ('AA','American Airlines'),('AB','Air Berlin'),('AJ','Air Japan'),('AM','Air Madagascar'),('BA','British Airways'),('DA','Delta Airlines'),('JA','JetBlue Airways'),('LU','Lufthansa'),('SA','Southwest Airlines'),('UA','United Airlines');
/*!40000 ALTER TABLE `airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `Id` char(3) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `City` varchar(50) NOT NULL,
  `Country` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES ('BET','Berlin Tegel','Berlin','Germany'),('CHI','Chicago O\'Hare International','Chicago','Illinois'),('HJI','Hartsfield-Jackson Atlanta Int ','Atlanta ','United States of America\n'),('IVI','Ivato International ','Antananarivo ','Madagascar\n'),('JFK','John F. Kennedy International ','New York ','United States of America\n'),('LAI','Los Angeles International ','Los Angeles ','United States of America\n'),('LGA','LaGuardia ','New York ','United States of America\n'),('LHA','London Heathrow ','London ','United Kingdom\n'),('LIA','Logan International ','Boston ','United States of America\n'),('SFI','San Francisco International ','San Francisco ','United States of America\n'),('TIA','Tokyo International ','Tokyo ','Japan');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auctions`
--

DROP TABLE IF EXISTS `auctions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auctions` (
  `AccountNo` int(11) NOT NULL,
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Date` datetime NOT NULL,
  `NYOP` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AccountNo`,`AirlineID`,`FlightNo`,`Class`,`Date`),
  KEY `AirlineID` (`AirlineID`,`FlightNo`),
  CONSTRAINT `auctions_ibfk_1` FOREIGN KEY (`AccountNo`) REFERENCES `customer` (`AccountNo`),
  CONSTRAINT `auctions_ibfk_2` FOREIGN KEY (`AirlineID`, `FlightNo`) REFERENCES `flight` (`AirlineID`, `FlightNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` VALUES (2,'AA',111,'Economy','2011-01-05 11:00:00',400.00),(2,'AA',111,'Economy','2011-01-05 11:22:39',991.00),(5213,'AJ',23,'Economy','2009-06-20 00:00:00',900.00);
/*!40000 ALTER TABLE `auctions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `Id` int(11) NOT NULL,
  `AccountNo` int(11) NOT NULL AUTO_INCREMENT,
  `CreditCardNo` char(16) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `CreationDate` datetime NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`AccountNo`),
  KEY `Id` (`Id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `person` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5214 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,1,'1234567890123456','awesomejane@ftw.com','2011-01-01 00:00:00',0),(2,2,'2345678901234567','jdoe@woot.com','2011-01-01 00:00:00',0),(3,3,'3456789012345678','rickroller@rolld.com','2011-01-01 00:00:00',0),(4,4,'1020304050607081','Emily@google.us','1990-09-22 00:00:00',5),(5,5213,'9878654532127319','tiffany@del.icio.us','2000-05-22 00:00:00',7);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerpreferences`
--

DROP TABLE IF EXISTS `customerpreferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customerpreferences` (
  `AccountNo` int(11) NOT NULL,
  `Preference` varchar(50) NOT NULL,
  PRIMARY KEY (`AccountNo`,`Preference`),
  CONSTRAINT `customerpreferences_ibfk_1` FOREIGN KEY (`AccountNo`) REFERENCES `customer` (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerpreferences`
--

LOCK TABLES `customerpreferences` WRITE;
/*!40000 ALTER TABLE `customerpreferences` DISABLE KEYS */;
INSERT INTO `customerpreferences` VALUES (1,'Lox'),(5213,'Lots of sushi?'),(5213,'More Sushi?'),(5213,'Sushi'),(5213,'sushi buffet');
/*!40000 ALTER TABLE `customerpreferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `Id` int(11) NOT NULL,
  `SSN` int(11) NOT NULL,
  `IsManager` tinyint(1) NOT NULL,
  `StartDate` date NOT NULL,
  `HourlyRate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `Id` (`Id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `person` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (0,0,1,'1980-12-31',9999.99),(2,136923687,1,'2016-01-14',1.00),(3,236542365,0,'2010-11-30',24.00),(1,670369138,0,'2017-10-23',10.00);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fare`
--

DROP TABLE IF EXISTS `fare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fare` (
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `FareType` varchar(20) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Fare` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AirlineID`,`FlightNo`,`FareType`,`Class`),
  CONSTRAINT `fare_ibfk_1` FOREIGN KEY (`AirlineID`, `FlightNo`) REFERENCES `flight` (`AirlineID`, `FlightNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fare`
--

LOCK TABLES `fare` WRITE;
/*!40000 ALTER TABLE `fare` DISABLE KEYS */;
INSERT INTO `fare` VALUES ('AA',111,'Hidden','Economy',990.00),('AA',111,'Regular','Economy',999.00),('AJ',23,'Hidden','Economy',20.00),('AJ',23,'Regular','Economy',79.99),('AJ',24,'Regular','Economy',20.00),('AJ',25,'Advanced','Supreme',0.01);
/*!40000 ALTER TABLE `fare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `NoOfSeats` int(11) NOT NULL,
  `DaysOperating` char(7) NOT NULL,
  `MinLengthOfStay` int(11) NOT NULL,
  `MaxLengthOfStay` int(11) NOT NULL,
  PRIMARY KEY (`AirlineID`,`FlightNo`),
  CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`AirlineID`) REFERENCES `airline` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES ('AA',111,100,'1010100',0,90),('AB',21,305,'0001000',9,12),('AJ',22,373,'0011100',3,15),('AJ',23,392,'0010100',2,16),('AJ',24,320,'0010101',4,14),('AJ',25,220,'1010101',7,7),('AM',1337,33,'0000011',0,90),('JA',111,150,'1111111',0,90);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `includes`
--

DROP TABLE IF EXISTS `includes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `includes` (
  `ResrNo` int(11) NOT NULL,
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `LegNo` int(11) NOT NULL,
  `DepDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ResrNo`,`AirlineID`,`FlightNo`,`LegNo`),
  KEY `AirlineID` (`AirlineID`,`FlightNo`,`LegNo`),
  CONSTRAINT `includes_ibfk_1` FOREIGN KEY (`ResrNo`) REFERENCES `reservation` (`ResrNo`),
  CONSTRAINT `includes_ibfk_2` FOREIGN KEY (`AirlineID`, `FlightNo`, `LegNo`) REFERENCES `leg` (`AirlineID`, `FlightNo`, `LegNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `includes`
--

LOCK TABLES `includes` WRITE;
/*!40000 ALTER TABLE `includes` DISABLE KEYS */;
INSERT INTO `includes` VALUES (111,'AA',111,1,'2011-01-05 11:00:00'),(111,'AA',111,2,'2011-01-05 19:00:00'),(222,'JA',111,1,'2011-01-14 22:30:00'),(333,'AM',1337,1,'2011-01-13 07:00:00'),(334,'AA',111,1,'2011-01-05 13:20:01');
/*!40000 ALTER TABLE `includes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leg`
--

DROP TABLE IF EXISTS `leg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leg` (
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `LegNo` int(11) NOT NULL,
  `DepAirportId` char(3) NOT NULL,
  `ArrTime` datetime NOT NULL,
  `DepTime` datetime NOT NULL,
  `ArrAirportId` char(3) NOT NULL,
  PRIMARY KEY (`AirlineID`,`FlightNo`,`LegNo`),
  UNIQUE KEY `AirlineID` (`AirlineID`,`FlightNo`,`DepAirportId`),
  KEY `DepAirportID` (`DepAirportId`),
  KEY `ArrAirportId` (`ArrAirportId`),
  CONSTRAINT `leg_ibfk_1` FOREIGN KEY (`AirlineID`, `FlightNo`) REFERENCES `flight` (`AirlineID`, `FlightNo`),
  CONSTRAINT `leg_ibfk_2` FOREIGN KEY (`DepAirportId`) REFERENCES `airport` (`Id`),
  CONSTRAINT `leg_ibfk_3` FOREIGN KEY (`ArrAirportId`) REFERENCES `airport` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leg`
--

LOCK TABLES `leg` WRITE;
/*!40000 ALTER TABLE `leg` DISABLE KEYS */;
INSERT INTO `leg` VALUES ('AA',111,1,'LGA','2011-01-05 09:00:00','2011-01-05 11:00:00','LAI'),('AA',111,2,'LAI','2011-01-05 17:00:00','2011-01-05 19:00:00','TIA'),('AM',1337,1,'JFK','2011-01-13 05:00:00','2011-01-13 07:00:00','IVI'),('JA',111,1,'SFI','2011-01-10 12:00:00','2011-01-10 14:00:00','LIA'),('JA',111,2,'LIA','2011-01-10 19:30:00','2011-01-10 22:30:00','LHA');
/*!40000 ALTER TABLE `leg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passenger` (
  `Id` int(11) NOT NULL,
  `AccountNo` int(11) NOT NULL,
  PRIMARY KEY (`Id`,`AccountNo`),
  KEY `AccountNo` (`AccountNo`),
  CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `person` (`Id`),
  CONSTRAINT `passenger_ibfk_2` FOREIGN KEY (`AccountNo`) REFERENCES `customer` (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `City` varchar(50) NOT NULL,
  `State` varchar(50) NOT NULL,
  `ZipCode` int(11) NOT NULL,
  `Phone` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (0,'SegFault','CoreDumped','503 Memory Lane','Silicon Valley','Frozen',94027,'8005984637'),(1,'Jane','Smith','100 Nicolls Rd','Stony Brook','New York',17790,'5555555555'),(2,'John','Doe','123 N Fake Street','New York','New York',10001,'1231231234'),(3,'Rick','Astley','1337 Internet Lane','Los Angeles','California',90001,'3141592653'),(4,'Emma','Smith','1600 Penn Ave','Washington DC','DC',20500,'2024561111'),(5,'Olivia','Murphy','11 Wall Street','New York City','New York',10005,'8005687625'),(6,'Ava','Martin','350 Fifth Avenue','New York','New York',10118,'2123326868'),(7,'Isabella','Brown','221 B Baker St','London','England',63715,'2072243688'),(8,'Isabella','Roy','Tour Eiffel Champ de Mars','Paris','France',75004,'892701239'),(9,'Charlotte','Tremblay','4059 Mt Lee Dr. Hollywood','Hollywood','California',33004,'3238488850'),(10,'Scarlett','Wilson','Statue of Liberty','Liberty Island','New York',10004,'2123633200'),(11,'Lily','Lavoie','Manager Square','Bethlehem','West Bank',70729,'8008102301'),(12,'Grace','Diaz','2 Macquarie Street','West Bank','Australia',2019,'794605008'),(13,'Noraa','Sánchez','10 Downing Street','BestPlace','United Kingdom',65217,'853641898'),(14,'Queen','Elizabeth II','Buckingham Palace','London','England',1,'1216272000');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `ResrNo` int(11) NOT NULL AUTO_INCREMENT,
  `ResrDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `BookingFee` decimal(10,2) NOT NULL,
  `TotalFare` decimal(10,2) NOT NULL,
  `RepSSN` int(11) DEFAULT NULL,
  `AccountNo` int(11) NOT NULL,
  PRIMARY KEY (`ResrNo`),
  KEY `RepSSN` (`RepSSN`),
  KEY `AccountNo` (`AccountNo`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`RepSSN`) REFERENCES `employee` (`SSN`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`AccountNo`) REFERENCES `customer` (`AccountNo`)
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (111,'2011-01-01 06:00:00',1200.00,1200.00,236542365,2),(222,'2011-01-01 06:05:00',500.00,500.00,236542365,1),(333,'2011-01-01 07:00:00',3333.33,3333.33,236542365,3),(334,'2017-10-25 02:20:50',20.00,1000.00,236542365,2);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservationpassenger`
--

DROP TABLE IF EXISTS `reservationpassenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservationpassenger` (
  `ResrNo` int(11) NOT NULL,
  `Id` int(11) NOT NULL,
  `AccountNo` int(11) NOT NULL,
  `SeatNo` char(5) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Meal` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ResrNo`,`Id`,`AccountNo`),
  KEY `Id` (`Id`,`AccountNo`),
  CONSTRAINT `reservationpassenger_ibfk_1` FOREIGN KEY (`ResrNo`) REFERENCES `reservation` (`ResrNo`),
  CONSTRAINT `reservationpassenger_ibfk_2` FOREIGN KEY (`Id`, `AccountNo`) REFERENCES `passenger` (`Id`, `AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservationpassenger`
--

LOCK TABLES `reservationpassenger` WRITE;
/*!40000 ALTER TABLE `reservationpassenger` DISABLE KEYS */;
INSERT INTO `reservationpassenger` VALUES (111,2,2,'33F','Economy','Chips'),(222,1,1,'13A','First','Fish and Chips'),(333,3,3,'1A','First','Sushi');
/*!40000 ALTER TABLE `reservationpassenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stopsat`
--

DROP TABLE IF EXISTS `stopsat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stopsat` (
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `StopNo` int(11) NOT NULL,
  `AirportId` char(3) NOT NULL,
  `ArrTime` datetime NOT NULL,
  `DepTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`AirlineID`,`FlightNo`,`StopNo`),
  UNIQUE KEY `AirlineID` (`AirlineID`,`FlightNo`,`AirportId`),
  KEY `AirportId` (`AirportId`),
  CONSTRAINT `stopsat_ibfk_1` FOREIGN KEY (`AirlineID`, `FlightNo`) REFERENCES `flight` (`AirlineID`, `FlightNo`),
  CONSTRAINT `stopsat_ibfk_2` FOREIGN KEY (`AirportId`) REFERENCES `airport` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stopsat`
--

LOCK TABLES `stopsat` WRITE;
/*!40000 ALTER TABLE `stopsat` DISABLE KEYS */;
INSERT INTO `stopsat` VALUES ('AA',111,1,'LGA','2011-01-05 09:00:00','2011-01-05 16:00:00'),('AA',111,2,'LAI','2011-01-05 17:00:00','2011-01-06 00:00:00'),('AA',111,3,'TIA','2011-01-06 07:30:00','2011-01-06 15:00:00'),('AM',1337,1,'JFK','2011-01-13 05:00:00','2011-01-13 12:00:00'),('AM',1337,2,'IVI','2011-01-13 23:00:00','2011-01-14 08:00:00'),('JA',111,1,'SFI','2011-01-10 12:00:00','2011-01-10 19:00:00'),('JA',111,2,'LIA','2011-01-10 19:30:00','2011-01-11 03:30:00'),('JA',111,3,'LHA','2011-01-11 05:00:00','2011-01-11 13:00:00');
/*!40000 ALTER TABLE `stopsat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-24 22:58:48
