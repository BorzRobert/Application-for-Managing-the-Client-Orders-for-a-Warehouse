-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: myprojectdb
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `billId` int NOT NULL,
  `clientId` int NOT NULL,
  `productId` int NOT NULL,
  `clientFirstName` varchar(50) NOT NULL,
  `clientLastName` varchar(50) NOT NULL,
  `clientAddress` varchar(50) NOT NULL,
  `clientEmail` varchar(50) NOT NULL,
  `clientPhoneNumber` varchar(10) NOT NULL,
  `productCategory` varchar(50) NOT NULL,
  `orderId` int NOT NULL,
  PRIMARY KEY (`billId`),
  KEY `clientfk_idx` (`clientId`),
  KEY `productfk_idx` (`productId`),
  KEY `order_bill_fk_idx` (`orderId`),
  CONSTRAINT `client_bill_fk` FOREIGN KEY (`clientId`) REFERENCES `client` (`clientId`),
  CONSTRAINT `order_bill_fk` FOREIGN KEY (`orderId`) REFERENCES `order` (`orderId`),
  CONSTRAINT `product_bill_fk` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,1,3,'Borz','Robert','Strada Garii','b@yahoo.com','0774088244','Food',1),(2,2,1,'Alexia','Sandor','Strada Marului','sandor@yahoo.com','0766206932','Cars',2),(3,3,1,'Alin','Pop','Strada Stramba','apopa@yahoo.com','0768234156','Cars',3),(4,4,3,'Sebastian','Olaru','Strada Bucuresti','solaru@gmail.com','0766206953','Food',4);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 15:17:54
