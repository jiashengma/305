


-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: ajax305
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
INSERT INTO `airport` VALUES ('BET','Berlin Tegel','Berlin','Germany'),('CHI','Chicago O\'Hare International','Chicago','Illinois'),('HJI','Hartsfield-Jackson Atlanta Int','Atlanta','United States of America'),('IVI','Ivato International','Antananarivo','Madagascar'),('JFK','John F. Kennedy International','New York','United States of America'),('LAI','Los Angeles International','Los Angeles','United States of America'),('LGA','LaGuardia','New York','United States of America'),('LHA','London Heathrow','London','United Kingdom'),('LIA','Logan International','Boston','United States of America'),('SFI','San Francisco International','San Francisco','United States of America'),('TIA','Tokyo International','Tokyo','Japan');
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
  `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NYOP` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AccountNo`,`AirlineID`,`FlightNo`,`Class`,`Date`),
  KEY `AirlineID` (`AirlineID`,`FlightNo`),
  CONSTRAINT `auctions_ibfk_1` FOREIGN KEY (`AccountNo`) REFERENCES `customer` (`AccountNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `auctions_ibfk_2` FOREIGN KEY (`AirlineID`, `FlightNo`) REFERENCES `flight` (`AirlineID`, `FlightNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` VALUES (4,'AA',111,'Economy','2017-12-05 16:21:08',200.00),(4,'AA',111,'Economy','2017-12-05 16:21:23',2001.00),(6,'AA',111,'Economy','2017-12-06 02:01:59',9999.00),(6,'AA',111,'Economy','2017-12-06 02:13:06',9999.00),(6,'AA',111,'Economy','2017-12-06 02:13:33',9999.00),(6,'AA',111,'Economy','2017-12-06 02:13:51',9999.00),(6,'AA',111,'Economy','2017-12-06 02:14:37',9999.00),(6,'AA',111,'Economy','2017-12-06 02:15:26',9999.00),(6,'AA',111,'First','2017-12-06 02:01:25',999.00),(6,'AA',111,'First','2017-12-06 02:01:32',1001.00),(6,'AA',111,'First','2017-12-06 02:01:38',9999.00),(6,'AA',111,'First','2017-12-06 02:01:45',10000.00);
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
  `CreditCardNo` mediumtext,
  `Email` varchar(50) DEFAULT NULL,
  `CreationDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`AccountNo`),
  KEY `Id` (`Id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `person` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (4,4,'1234567890123456','user1@email.com','2017-11-24 12:08:46',0),(5,5,'111','user2@email.com','2017-11-24 12:46:41',0),(11,6,'1020365024169843','some@place','2017-12-05 16:28:40',0),(12,7,'1245693258745698','a@a','2017-12-07 12:52:27',0);
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
  CONSTRAINT `customerpreferences_ibfk_1` FOREIGN KEY (`AccountNo`) REFERENCES `customer` (`AccountNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerpreferences`
--

LOCK TABLES `customerpreferences` WRITE;
/*!40000 ALTER TABLE `customerpreferences` DISABLE KEYS */;
INSERT INTO `customerpreferences` VALUES (6,'chipotle'),(7,'meal1');
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
  `SSN` char(9) NOT NULL,
  `IsManager` tinyint(1) NOT NULL DEFAULT '0',
  `StartDate` date NOT NULL,
  `HourlyRate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `Id` (`Id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `person` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (0,'0',1,'1980-12-31',9999.99),(10,'121212120',0,'2017-11-28',11.00),(2,'136923687',1,'2016-01-14',1.00),(3,'236542365',0,'2010-11-30',24.00),(1,'670369138',1,'2017-10-23',10.00);
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
  CONSTRAINT `includes_ibfk_1` FOREIGN KEY (`ResrNo`) REFERENCES `reservation` (`ResrNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `includes_ibfk_2` FOREIGN KEY (`AirlineID`, `FlightNo`, `LegNo`) REFERENCES `leg` (`AirlineID`, `FlightNo`, `LegNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `includes`
--

LOCK TABLES `includes` WRITE;
/*!40000 ALTER TABLE `includes` DISABLE KEYS */;
INSERT INTO `includes` VALUES (3,'JA',111,1,'2017-12-04 19:20:34'),(19,'AA',111,1,'2017-12-06 16:24:02'),(22,'AA',111,1,'2017-12-07 12:54:03');
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
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(24) NOT NULL,
  `password` char(64) NOT NULL,
  PRIMARY KEY (`id`,`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (0,'andlam','a990f12d738d0df16ea4ad6c40abc86053a8dab0f3022fda65b215399948dfd0'),(1,'aaliberti','a990f12d738d0df16ea4ad6c40abc86053a8dab0f3022fda65b215399948dfd0'),(2,'jiama','a990f12d738d0df16ea4ad6c40abc86053a8dab0f3022fda65b215399948dfd0'),(3,'sas','a990f12d738d0df16ea4ad6c40abc86053a8dab0f3022fda65b215399948dfd0'),(4,'user1','a990f12d738d0df16ea4ad6c40abc86053a8dab0f3022fda65b215399948dfd0'),(5,'user2','a990f12d738d0df16ea4ad6c40abc86053a8dab0f3022fda65b215399948dfd0'),(10,'emp1','a990f12d738d0df16ea4ad6c40abc86053a8dab0f3022fda65b215399948dfd0'),(11,'gm','1fd416b51d6d5589088e931285935a7c69ff1e1ef85bfbf5c0f7b7e8c83828da'),(12,'gnu','9527c57a922d158f4b94388d3f01b6dde6e5e315a459779cec5b90516510158b');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
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
  CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `person` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `passenger_ibfk_2` FOREIGN KEY (`AccountNo`) REFERENCES `customer` (`AccountNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (4,4),(5,5),(0,6),(12,7);
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
  `Street` varchar(52) NOT NULL,
  `City` varchar(50) NOT NULL,
  `State` varchar(50) NOT NULL,
  `ZipCode` int(11) NOT NULL,
  `Phone` mediumtext,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (0,'Andrew','Lam','503 Memory Lane','Silicon Valley','NY',94027,'8005984637'),(1,'Antonio','Aliberti','100 Nicolls Rd','Stony Brook','NY',17790,'5555555555'),(2,'Jia Sheng','Ma','123 N Fake Street','New York','NY',10001,'1231231234'),(3,'Scott','Smolka','1337 Internet Lane','Los Angeles','CA',90001,'3141592653'),(4,'User','One','333 N','Phenix','AR',99912,'1234567890'),(5,'User','Two','123 main','BROOKLYN','NY',11222,'0987654321'),(10,'Employee','One','1 Nicols Rd','Stony Brook','NY',11790,'9170000000'),(11,'your','name','123','hometown','HI',112211,'1011124589'),(12,'apple','banana','aaa','11','AL',11228,'1214151213');
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
  `RepSSN` char(9) NOT NULL,
  `AccountNo` int(11) NOT NULL,
  PRIMARY KEY (`ResrNo`),
  KEY `RepSSN` (`RepSSN`),
  KEY `AccountNo` (`AccountNo`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`RepSSN`) REFERENCES `employee` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`AccountNo`) REFERENCES `customer` (`AccountNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (2,'2017-12-04 19:31:01',100.00,120.00,'0',4),(3,'2017-12-04 22:42:55',60.00,220.00,'0',5),(19,'2017-12-06 21:24:02',1198.80,999.00,'121212120',6),(22,'2017-12-07 17:54:03',1198.80,999.00,'121212120',7);
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
  CONSTRAINT `reservationpassenger_ibfk_1` FOREIGN KEY (`ResrNo`) REFERENCES `reservation` (`ResrNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservationpassenger_ibfk_2` FOREIGN KEY (`Id`, `AccountNo`) REFERENCES `passenger` (`Id`, `AccountNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservationpassenger`
--

LOCK TABLES `reservationpassenger` WRITE;
/*!40000 ALTER TABLE `reservationpassenger` DISABLE KEYS */;
INSERT INTO `reservationpassenger` VALUES (2,4,4,'24','First','Potatoes'),(3,5,5,'14','Economic','Spaghetti Carbonara with Pancetta and Mushrooms'),(19,0,6,'2D','Economy','chipotle'),(22,12,7,'2E','Economy','meal1');
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

-- Dump completed on 2017-12-07 12:57:40
