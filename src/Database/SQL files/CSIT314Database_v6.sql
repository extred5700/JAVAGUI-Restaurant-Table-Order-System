CREATE DATABASE  IF NOT EXISTS `csit314` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `csit314`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: csit314
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `coupon` varchar(20) NOT NULL,
  `discount_value` decimal(10,2) NOT NULL,
  PRIMARY KEY (`coupon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES ('10OFF',0.90),('15OFF',0.85);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_item`
--

DROP TABLE IF EXISTS `menu_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_item` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `item_price` decimal(10,2) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_item`
--

LOCK TABLES `menu_item` WRITE;
/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
INSERT INTO `menu_item` VALUES (1,'marinara',13.00,'pasta'),(2,'beef bolognese',10.00,'pasta'),(3,'spicy chicken',10.00,'pasta'),(4,'carbonara',12.00,'pasta'),(5,'alfredo',12.00,'pasta'),(6,'beef pepperoni',16.00,'pizza'),(7,'margherita',14.00,'pizza'),(8,'hawaiian',15.00,'pizza'),(9,'mushrooms',15.00,'pizza'),(10,'mushroom baked rice',11.00,'baked rice'),(11,'chicken sausage baked rice',12.00,'baked rice'),(12,'coke',3.00,'drinks'),(13,'sprite',3.00,'drinks'),(14,'coke zero',3.00,'drinks'),(15,'a&w root beer',3.00,'drinks'),(16,'fanta grape',3.00,'drinks'),(17,'fanta orange',3.00,'drinks'),(18,'aglio olio',9.00,'pasta');
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_history`
--

DROP TABLE IF EXISTS `order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_history` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `transaction_id` int DEFAULT NULL,
  `item_id` int DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `fulfilled` char(1) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `transaction_id_idx` (`transaction_id`),
  KEY `item_id_idx` (`item_id`),
  CONSTRAINT `item_id` FOREIGN KEY (`item_id`) REFERENCES `menu_item` (`item_id`),
  CONSTRAINT `transaction_id` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_history` (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_history`
--

LOCK TABLES `order_history` WRITE;
/*!40000 ALTER TABLE `order_history` DISABLE KEYS */;
INSERT INTO `order_history` VALUES (1,1,1,3,39.00,'N'),(2,1,2,1,10.00,'N'),(3,2,2,2,20.00,'N'),(4,3,4,1,12.00,'N'),(5,3,7,1,14.00,'N'),(6,4,4,1,12.00,'N'),(7,5,2,5,50.00,'N'),(8,6,1,1,13.00,'Y'),(9,6,3,1,10.00,'N'),(10,7,3,2,20.00,'N'),(11,7,6,1,16.00,'N'),(12,8,10,1,11.00,'N'),(13,8,11,1,12.00,'N'),(14,9,11,1,12.00,'N'),(15,9,3,6,60.00,'N'),(16,10,3,5,50.00,'N'),(17,10,1,1,13.00,'N'),(18,11,8,3,45.00,'N'),(19,12,3,2,20.00,'N'),(20,12,1,1,13.00,'N'),(21,12,5,1,12.00,'N'),(22,12,5,1,12.00,'N'),(23,13,2,2,20.00,'N'),(24,13,2,2,20.00,'N'),(25,14,8,2,30.00,'N');
/*!40000 ALTER TABLE `order_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `order_history_BEFORE_INSERT` BEFORE INSERT ON `order_history` FOR EACH ROW BEGIN
DECLARE tPrice INT;
SELECT item_price INTO tPrice FROM menu_item WHERE menu_item.item_id = NEW.item_id;
SET NEW.price = NEW.qty * tPrice;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `order_history_AFTER_INSERT` AFTER INSERT ON `order_history` FOR EACH ROW BEGIN
UPDATE transaction_history SET total_price = (SELECT SUM(price) FROM order_history WHERE transaction_history.transaction_id = order_history.transaction_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `order_history_BEFORE_UPDATE` BEFORE UPDATE ON `order_history` FOR EACH ROW BEGIN
DECLARE tPrice INT;
SELECT item_price INTO tPrice FROM menu_item WHERE menu_item.item_id = NEW.item_id;
SET NEW.price = NEW.qty * tPrice;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `order_history_AFTER_UPDATE` AFTER UPDATE ON `order_history` FOR EACH ROW BEGIN
UPDATE transaction_history SET total_price = (SELECT SUM(price) FROM order_history WHERE transaction_history.transaction_id = order_history.transaction_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `order_history_AFTER_DELETE` AFTER DELETE ON `order_history` FOR EACH ROW BEGIN
UPDATE transaction_history SET total_price = (SELECT SUM(price) FROM order_history WHERE transaction_history.transaction_id = order_history.transaction_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `transaction_history`
--

DROP TABLE IF EXISTS `transaction_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_history` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `table_no` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `pNum` varchar(8) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `paid` char(1) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_history`
--

LOCK TABLES `transaction_history` WRITE;
/*!40000 ALTER TABLE `transaction_history` DISABLE KEYS */;
INSERT INTO `transaction_history` VALUES (1,11,'2022-04-26','97774566',49.00,'N'),(2,20,'2022-04-26','98874566',20.00,'N'),(3,1,'2022-05-01','92274586',26.00,'N'),(4,14,'2022-04-16','98874566',12.00,'N'),(5,25,'2022-05-03','94556636',50.00,'N'),(6,20,'2022-04-22','98874555',23.00,'N'),(7,20,'2022-05-02','98874555',36.00,'N'),(8,20,'2022-04-11','98874555',23.00,'N'),(9,20,'2022-04-11','99885242',72.00,'N'),(10,10,'2022-04-13','99885242',63.00,'N'),(11,16,'2022-04-12','97885242',45.00,'N'),(12,4,'2022-04-27','97852221',57.00,'N'),(13,6,'2022-05-04','98874566',40.00,'N'),(14,9,'2022-05-09','98874536',30.00,'N');
/*!40000 ALTER TABLE `transaction_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `user_profile` varchar(20) DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('manager1','manager1','Restaurant Manager','Y'),('manager2','manager2','Restaurant Manager','Y'),('manager3','manager3','Restaurant Manager','Y'),('manager4','manager4','Restaurant Manager','Y'),('manager5','manager5','Restaurant Manager','N'),('owner1','owner1','Restaurant Owner','Y'),('TEST1','TEST1','RESTAURANT MANAGER','Y'),('user_admin1','user_admin1','User Admin','Y'),('user1','user1','Restaurant Staff','Y'),('user2','user2','Restaurant Staff','Y'),('user3','user3','Restaurant Staff','Y'),('user4','testing','Restaurant Staff','N'),('user5','user5','Restaurant Staff','N');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-09 15:03:39
