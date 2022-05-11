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
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_item`
--

LOCK TABLES `menu_item` WRITE;
/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
INSERT INTO `menu_item` VALUES (1,'pasta1',13.50,'pasta'),(2,'pasta2',10.50,'pasta'),(3,'pasta3',12.00,'pasta'),(4,'pasta4',10.00,'pasta'),(5,'pasta5',10.00,'pasta'),(6,'pasta6',11.00,'pasta'),(7,'pasta7',18.00,'pasta'),(8,'pasta8',11.00,'pasta'),(9,'pasta9',14.00,'pasta'),(10,'pasta10',12.00,'pasta'),(11,'pasta11',10.50,'pasta'),(12,'pasta12',18.00,'pasta'),(13,'pasta13',12.00,'pasta'),(14,'pasta14',10.50,'pasta'),(15,'pasta15',13.00,'pasta'),(16,'pasta16',16.50,'pasta'),(17,'pasta17',11.50,'pasta'),(18,'pasta18',12.00,'pasta'),(19,'pasta19',14.00,'pasta'),(20,'pasta20',12.00,'pasta'),(21,'pasta21',13.00,'pasta'),(22,'pasta22',18.00,'pasta'),(23,'pasta23',10.00,'pasta'),(24,'pasta24',14.50,'pasta'),(25,'pasta25',13.50,'pasta'),(26,'pizza1',28.00,'pizza'),(27,'pizza2',26.00,'pizza'),(28,'pizza3',14.50,'pizza'),(29,'pizza4',23.50,'pizza'),(30,'pizza5',15.00,'pizza'),(31,'pizza6',18.50,'pizza'),(32,'pizza7',18.50,'pizza'),(33,'pizza8',18.50,'pizza'),(34,'pizza9',22.50,'pizza'),(35,'pizza10',23.50,'pizza'),(36,'pizza11',15.00,'pizza'),(37,'pizza12',18.50,'pizza'),(38,'pizza13',22.50,'pizza'),(39,'pizza14',26.00,'pizza'),(40,'pizza15',18.00,'pizza'),(41,'pizza16',14.50,'pizza'),(42,'pizza17',23.50,'pizza'),(43,'pizza18',28.00,'pizza'),(44,'pizza19',28.00,'pizza'),(45,'pizza20',20.00,'pizza'),(46,'pizza21',18.50,'pizza'),(47,'pizza22',22.50,'pizza'),(48,'pizza23',17.50,'pizza'),(49,'pizza24',18.00,'pizza'),(50,'pizza25',28.00,'pizza'),(51,'baked rice1',9.00,'baked rice'),(52,'baked rice2',9.50,'baked rice'),(53,'baked rice3',14.00,'baked rice'),(54,'baked rice4',11.00,'baked rice'),(55,'baked rice5',12.00,'baked rice'),(56,'baked rice6',9.00,'baked rice'),(57,'baked rice7',9.50,'baked rice'),(58,'baked rice8',12.00,'baked rice'),(59,'baked rice9',14.00,'baked rice'),(60,'baked rice10',8.50,'baked rice'),(61,'baked rice11',11.00,'baked rice'),(62,'baked rice12',14.00,'baked rice'),(63,'baked rice13',10.00,'baked rice'),(64,'baked rice14',11.00,'baked rice'),(65,'baked rice15',11.00,'baked rice'),(66,'baked rice16',8.00,'baked rice'),(67,'baked rice17',8.00,'baked rice'),(68,'baked rice18',10.00,'baked rice'),(69,'baked rice19',10.00,'baked rice'),(70,'baked rice20',8.50,'baked rice'),(71,'baked rice21',14.00,'baked rice'),(72,'baked rice22',9.50,'baked rice'),(73,'baked rice23',9.00,'baked rice'),(74,'baked rice24',8.00,'baked rice'),(75,'baked rice25',11.00,'baked rice'),(76,'drink1',4.00,'drinks'),(77,'drink2',3.50,'drinks'),(78,'drink3',4.00,'drinks'),(79,'drink4',4.00,'drinks'),(80,'drink5',3.50,'drinks'),(81,'drink6',3.00,'drinks'),(82,'drink7',3.00,'drinks'),(83,'drink8',3.50,'drinks'),(84,'drink9',4.00,'drinks'),(85,'drink10',3.50,'drinks'),(86,'drink11',3.00,'drinks'),(87,'drink12',4.00,'drinks'),(88,'drink13',4.00,'drinks'),(89,'drink14',3.00,'drinks'),(90,'drink15',3.50,'drinks'),(91,'drink16',3.00,'drinks'),(92,'drink17',4.00,'drinks'),(93,'drink18',3.50,'drinks'),(94,'drink19',3.00,'drinks'),(95,'drink20',3.50,'drinks'),(96,'drink21',3.00,'drinks'),(97,'drink22',3.50,'drinks'),(98,'drink23',3.00,'drinks'),(99,'drink24',3.00,'drinks'),(100,'drink25',4.00,'drinks');
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
  `transaction_id` int NOT NULL,
  `item_id` int NOT NULL,
  `qty` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `fulfilled` char(1) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `transaction_id_idx` (`transaction_id`),
  KEY `item_id_idx` (`item_id`),
  CONSTRAINT `item_id` FOREIGN KEY (`item_id`) REFERENCES `menu_item` (`item_id`),
  CONSTRAINT `transaction_id` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_history` (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_history`
--

LOCK TABLES `order_history` WRITE;
/*!40000 ALTER TABLE `order_history` DISABLE KEYS */;
INSERT INTO `order_history` VALUES (1,1,77,2,8.00,'Y'),(2,1,81,3,9.00,'Y'),(3,2,28,1,15.00,'Y'),(4,2,24,1,15.00,'Y'),(5,3,96,1,3.00,'Y'),(6,3,68,2,20.00,'Y'),(7,4,11,3,33.00,'Y'),(8,4,78,2,8.00,'Y'),(9,5,44,3,84.00,'Y'),(10,5,82,1,3.00,'Y'),(11,6,80,3,12.00,'Y'),(12,6,17,2,24.00,'Y'),(13,7,61,1,11.00,'Y'),(14,7,52,1,10.00,'Y'),(15,8,31,3,57.00,'Y'),(16,8,60,2,18.00,'Y'),(17,9,46,3,57.00,'Y'),(18,9,65,3,33.00,'Y'),(19,10,91,2,6.00,'Y'),(20,10,80,1,4.00,'Y'),(21,11,69,3,30.00,'Y'),(22,11,77,3,12.00,'Y'),(23,12,41,1,15.00,'Y'),(24,12,27,3,78.00,'Y'),(25,13,37,3,57.00,'Y'),(26,13,27,3,78.00,'Y'),(27,14,6,3,33.00,'Y'),(28,14,44,3,84.00,'Y'),(29,15,61,3,33.00,'Y'),(30,15,95,1,4.00,'Y'),(31,16,8,2,22.00,'Y'),(32,16,19,1,14.00,'Y'),(33,17,79,2,8.00,'Y'),(34,17,78,1,4.00,'Y'),(35,18,30,3,45.00,'Y'),(36,18,82,2,6.00,'Y'),(37,19,88,1,4.00,'Y'),(38,19,51,1,9.00,'Y'),(39,20,18,2,24.00,'Y'),(40,20,42,2,48.00,'Y'),(41,21,40,1,18.00,'Y'),(42,21,66,1,8.00,'Y'),(43,22,14,2,22.00,'Y'),(44,22,77,3,12.00,'Y'),(45,23,45,1,20.00,'Y'),(46,23,95,3,12.00,'Y'),(47,24,99,1,3.00,'Y'),(48,24,95,3,12.00,'Y'),(49,25,45,1,20.00,'Y'),(50,25,31,2,38.00,'Y'),(51,26,33,3,57.00,'Y'),(52,26,25,2,28.00,'Y'),(53,27,21,1,13.00,'Y'),(54,27,60,2,18.00,'Y'),(55,28,74,2,16.00,'Y'),(56,28,11,1,11.00,'Y'),(57,29,47,2,46.00,'Y'),(58,29,48,2,36.00,'Y'),(59,30,10,2,24.00,'Y'),(60,30,79,3,12.00,'Y'),(61,31,37,3,57.00,'Y'),(62,31,36,1,15.00,'Y'),(63,32,72,1,10.00,'Y'),(64,32,91,3,9.00,'Y'),(65,33,20,2,24.00,'Y'),(66,33,38,2,46.00,'Y'),(67,34,75,3,33.00,'Y'),(68,34,97,3,12.00,'Y'),(69,35,4,1,10.00,'Y'),(70,35,14,2,22.00,'Y'),(71,36,18,3,36.00,'Y'),(72,36,27,1,26.00,'Y'),(73,37,94,2,6.00,'Y'),(74,37,93,1,4.00,'Y'),(75,38,16,2,34.00,'Y'),(76,38,31,2,38.00,'Y'),(77,39,51,3,27.00,'Y'),(78,39,79,1,4.00,'Y'),(79,40,48,2,36.00,'Y'),(80,40,33,2,38.00,'Y'),(81,41,58,1,12.00,'Y'),(82,41,92,2,8.00,'Y'),(83,42,54,3,33.00,'Y'),(84,42,90,1,4.00,'Y'),(85,43,91,1,3.00,'Y'),(86,43,43,1,28.00,'Y'),(87,44,27,2,52.00,'Y'),(88,44,18,1,12.00,'Y'),(89,45,93,2,8.00,'Y'),(90,45,30,2,30.00,'Y'),(91,46,57,3,30.00,'Y'),(92,46,85,1,4.00,'Y'),(93,47,28,3,45.00,'Y'),(94,47,7,1,18.00,'Y'),(95,48,11,1,11.00,'Y'),(96,48,82,1,3.00,'Y'),(97,49,68,1,10.00,'Y'),(98,49,42,3,72.00,'Y'),(99,50,53,3,42.00,'Y'),(100,50,24,2,30.00,'Y'),(101,51,53,3,42.00,'Y'),(102,51,79,1,4.00,'Y'),(103,52,80,2,8.00,'Y'),(104,52,90,1,4.00,'Y'),(105,53,82,3,9.00,'Y'),(106,53,88,2,8.00,'Y'),(107,54,86,2,6.00,'Y'),(108,54,27,2,52.00,'Y'),(109,55,87,2,8.00,'Y'),(110,55,1,3,42.00,'Y'),(111,56,29,1,24.00,'Y'),(112,56,31,2,38.00,'Y'),(113,57,76,2,8.00,'Y'),(114,57,28,3,45.00,'Y'),(115,58,35,2,48.00,'Y'),(116,58,10,3,36.00,'Y'),(117,59,62,3,42.00,'Y'),(118,59,32,1,19.00,'Y'),(119,60,26,3,84.00,'Y'),(120,60,69,3,30.00,'Y'),(121,61,83,3,12.00,'Y'),(122,61,92,1,4.00,'Y'),(124,63,79,2,8.00,'Y'),(125,62,20,3,36.00,'Y'),(126,63,10,1,12.00,'Y'),(127,64,80,2,8.00,'Y'),(128,65,33,3,57.00,'Y'),(129,66,83,3,12.00,'Y'),(130,67,57,2,20.00,'Y'),(131,68,73,1,9.00,'Y'),(132,69,42,1,24.00,'Y'),(133,70,94,2,6.00,'Y'),(134,71,99,3,9.00,'Y'),(135,72,50,2,56.00,'Y'),(136,73,12,3,54.00,'Y'),(137,74,57,3,30.00,'Y'),(138,75,39,2,52.00,'Y'),(139,76,55,3,36.00,'Y'),(140,77,18,2,24.00,'Y'),(141,78,10,3,36.00,'Y'),(142,79,3,3,36.00,'Y'),(143,80,74,3,24.00,'Y'),(144,81,75,1,11.00,'Y'),(145,82,88,3,12.00,'Y'),(146,83,27,2,52.00,'Y'),(147,84,100,1,4.00,'Y'),(148,85,37,2,38.00,'Y'),(149,86,26,3,84.00,'Y'),(150,87,60,3,27.00,'Y'),(151,88,20,1,12.00,'Y'),(152,89,81,3,9.00,'Y'),(153,90,7,2,36.00,'Y'),(154,91,57,3,30.00,'Y'),(155,92,15,2,26.00,'Y'),(156,93,50,2,56.00,'Y'),(157,94,68,2,20.00,'Y'),(158,95,88,2,8.00,'Y'),(159,96,68,3,30.00,'Y'),(160,97,38,2,46.00,'Y');
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
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_history`
--

LOCK TABLES `transaction_history` WRITE;
/*!40000 ALTER TABLE `transaction_history` DISABLE KEYS */;
INSERT INTO `transaction_history` VALUES (1,11,'2022-04-14','97774566',17.00,'Y'),(2,20,'2022-04-14','98874566',30.00,'Y'),(3,1,'2022-04-16','85459090',23.00,'Y'),(4,14,'2022-04-18','98874566',41.00,'Y'),(5,25,'2022-04-18','94556636',87.00,'Y'),(6,20,'2022-04-19','98874555',36.00,'Y'),(7,20,'2022-04-19','98874555',21.00,'Y'),(8,20,'2022-04-21','98874555',75.00,'Y'),(9,20,'2022-04-21','99885242',90.00,'Y'),(10,10,'2022-04-22','99885242',10.00,'Y'),(11,16,'2022-04-22','97885242',42.00,'Y'),(12,4,'2022-04-22','97852221',93.00,'Y'),(13,6,'2022-04-24','98874566',135.00,'Y'),(14,9,'2022-04-24','98874536',117.00,'Y'),(15,1,'2022-04-26','97558345',37.00,'Y'),(16,8,'2022-04-26','88330902',36.00,'Y'),(17,5,'2022-04-26','98874566',12.00,'Y'),(18,16,'2022-04-26','97558345',51.00,'Y'),(19,20,'2022-04-26','94908854',13.00,'Y'),(20,19,'2022-04-26','85459090',72.00,'Y'),(21,11,'2022-04-27','99220905',26.00,'Y'),(22,13,'2022-04-27','88330902',34.00,'Y'),(23,13,'2022-04-27','85574431',32.00,'Y'),(24,28,'2022-04-30','97774566',15.00,'Y'),(25,25,'2022-04-30','94443049',58.00,'Y'),(26,8,'2022-04-30','98874566',85.00,'Y'),(27,12,'2022-05-01','97774566',31.00,'Y'),(28,22,'2022-05-01','94908854',27.00,'Y'),(29,22,'2022-05-01','94443049',82.00,'Y'),(30,19,'2022-05-02','97852221',36.00,'Y'),(31,23,'2022-05-02','98874566',72.00,'Y'),(32,5,'2022-05-02','97885242',19.00,'Y'),(33,21,'2022-05-03','97774566',70.00,'Y'),(34,4,'2022-05-03','84503302',45.00,'Y'),(35,27,'2022-05-05','98874566',32.00,'Y'),(36,14,'2022-05-05','97852221',62.00,'Y'),(37,1,'2022-05-07','98874566',10.00,'Y'),(38,26,'2022-05-07','94443049',72.00,'Y'),(39,30,'2022-05-11','97885242',31.00,'Y'),(40,24,'2022-05-11','94908854',74.00,'Y'),(41,13,'2022-05-11','97885242',20.00,'Y'),(42,22,'2022-05-12','97774566',37.00,'Y'),(43,30,'2022-05-12','99403099',31.00,'Y'),(44,3,'2022-05-12','88330902',64.00,'Y'),(45,1,'2022-05-13','97885242',38.00,'Y'),(46,23,'2022-05-13','97558345',34.00,'Y'),(47,2,'2022-05-13','97885242',63.00,'Y'),(48,14,'2022-05-14','88330902',14.00,'Y'),(49,23,'2022-05-14','94908854',82.00,'Y'),(50,17,'2022-05-15','98874566',72.00,'Y'),(51,9,'2022-05-15','97558345',46.00,'Y'),(52,27,'2022-05-16','85459090',12.00,'Y'),(53,11,'2022-05-16','97885242',17.00,'Y'),(54,22,'2022-05-17','84503302',58.00,'Y'),(55,21,'2022-05-18','88330902',50.00,'Y'),(56,21,'2022-05-18','99403099',62.00,'Y'),(57,14,'2022-05-19','97774566',53.00,'Y'),(58,18,'2022-05-19','97885242',84.00,'Y'),(59,9,'2022-05-20','85459090',61.00,'Y'),(60,12,'2022-05-20','85459090',114.00,'Y'),(61,9,'2022-05-20','97885242',16.00,'Y'),(62,19,'2022-05-21','99403099',36.00,'Y'),(63,23,'2022-05-21','97885242',20.00,'Y'),(64,15,'2022-05-21','99220905',8.00,'Y'),(65,15,'2022-05-21','85459090',57.00,'Y'),(66,4,'2022-05-21','94443049',12.00,'Y'),(67,24,'2022-05-21','97852221',20.00,'Y'),(68,22,'2022-05-21','97852221',9.00,'Y'),(69,19,'2022-05-21','98874566',24.00,'Y'),(70,9,'2022-05-22','98874566',6.00,'Y'),(71,2,'2022-05-22','97885242',9.00,'Y'),(72,21,'2022-05-22','94908854',56.00,'Y'),(73,27,'2022-05-22','94443049',54.00,'Y'),(74,27,'2022-05-22','97774566',30.00,'Y'),(75,21,'2022-05-22','97885242',52.00,'Y'),(76,14,'2022-05-23','97885242',36.00,'Y'),(77,26,'2022-05-23','97558345',24.00,'Y'),(78,3,'2022-05-23','97774566',36.00,'Y'),(79,23,'2022-05-23','98874566',36.00,'Y'),(80,28,'2022-05-23','88330902',24.00,'Y'),(81,8,'2022-05-23','99403099',11.00,'Y'),(82,3,'2022-05-23','99220905',12.00,'Y'),(83,23,'2022-05-23','94908854',52.00,'Y'),(84,13,'2022-05-24','99403099',4.00,'Y'),(85,11,'2022-05-24','97774566',38.00,'Y'),(86,29,'2022-05-24','97885242',84.00,'Y'),(87,18,'2022-05-24','98874566',27.00,'Y'),(88,7,'2022-05-24','94908854',12.00,'Y'),(89,24,'2022-05-24','98874566',9.00,'Y'),(90,2,'2022-05-24','97774566',36.00,'Y'),(91,7,'2022-05-25','98874566',30.00,'Y'),(92,10,'2022-05-25','84503302',26.00,'Y'),(93,14,'2022-05-25','94443049',56.00,'Y'),(94,13,'2022-05-25','98874566',20.00,'Y'),(95,20,'2022-05-25','85459090',8.00,'Y'),(96,25,'2022-05-25','84503302',30.00,'Y'),(97,10,'2022-05-25','84503302',46.00,'Y');
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
INSERT INTO `user` VALUES ('manager0','manager0','RESTAURANT MANAGER','Y'),('manager1','manager1','RESTAURANT MANAGER','Y'),('manager10','manager10','RESTAURANT MANAGER','Y'),('manager2','manager2','RESTAURANT MANAGER','Y'),('manager3','manager3','RESTAURANT MANAGER','Y'),('manager4','manager4','RESTAURANT MANAGER','Y'),('manager5','manager5','RESTAURANT MANAGER','Y'),('manager6','manager6','RESTAURANT MANAGER','Y'),('manager7','manager7','RESTAURANT MANAGER','Y'),('manager8','manager8','RESTAURANT MANAGER','Y'),('manager9','manager9','RESTAURANT MANAGER','Y'),('owner1','owner1','RESTAURANT OWNER','Y'),('owner2','owner2','RESTAURANT OWNER','Y'),('staff1','staff1','RESTAURANT STAFF','Y'),('staff10','staff10','RESTAURANT STAFF','Y'),('staff11','staff11','RESTAURANT STAFF','Y'),('staff12','staff12','RESTAURANT STAFF','Y'),('staff13','staff13','RESTAURANT STAFF','Y'),('staff14','staff14','RESTAURANT STAFF','Y'),('staff15','staff15','RESTAURANT STAFF','Y'),('staff16','staff16','RESTAURANT STAFF','Y'),('staff17','staff17','RESTAURANT STAFF','Y'),('staff18','staff18','RESTAURANT STAFF','Y'),('staff19','staff19','RESTAURANT STAFF','Y'),('staff2','staff2','RESTAURANT STAFF','Y'),('staff20','staff20','RESTAURANT STAFF','Y'),('staff21','staff21','RESTAURANT STAFF','Y'),('staff22','staff22','RESTAURANT STAFF','Y'),('staff23','staff23','RESTAURANT STAFF','Y'),('staff24','staff24','RESTAURANT STAFF','Y'),('staff25','staff25','RESTAURANT STAFF','Y'),('staff26','staff26','RESTAURANT STAFF','Y'),('staff27','staff27','RESTAURANT STAFF','Y'),('staff28','staff28','RESTAURANT STAFF','Y'),('staff29','staff29','RESTAURANT STAFF','Y'),('staff3','staff3','RESTAURANT STAFF','Y'),('staff30','staff30','RESTAURANT STAFF','Y'),('staff31','staff31','RESTAURANT STAFF','Y'),('staff32','staff32','RESTAURANT STAFF','Y'),('staff33','staff33','RESTAURANT STAFF','Y'),('staff34','staff34','RESTAURANT STAFF','Y'),('staff35','staff35','RESTAURANT STAFF','Y'),('staff36','staff36','RESTAURANT STAFF','Y'),('staff37','staff37','RESTAURANT STAFF','Y'),('staff38','staff38','RESTAURANT STAFF','Y'),('staff39','staff39','RESTAURANT STAFF','Y'),('staff4','staff4','RESTAURANT STAFF','Y'),('staff40','staff40','RESTAURANT STAFF','Y'),('staff41','staff41','RESTAURANT STAFF','Y'),('staff42','staff42','RESTAURANT STAFF','Y'),('staff43','staff43','RESTAURANT STAFF','Y'),('staff44','staff44','RESTAURANT STAFF','Y'),('staff45','staff45','RESTAURANT STAFF','Y'),('staff46','staff46','RESTAURANT STAFF','Y'),('staff47','staff47','RESTAURANT STAFF','Y'),('staff48','staff48','RESTAURANT STAFF','Y'),('staff49','staff49','RESTAURANT STAFF','Y'),('staff5','staff5','RESTAURANT STAFF','Y'),('staff50','staff50','RESTAURANT STAFF','Y'),('staff51','staff51','RESTAURANT STAFF','Y'),('staff52','staff52','RESTAURANT STAFF','Y'),('staff53','staff53','RESTAURANT STAFF','Y'),('staff54','staff54','RESTAURANT STAFF','Y'),('staff55','staff55','RESTAURANT STAFF','Y'),('staff56','staff56','RESTAURANT STAFF','Y'),('staff57','staff57','RESTAURANT STAFF','Y'),('staff58','staff58','RESTAURANT STAFF','Y'),('staff59','staff59','RESTAURANT STAFF','Y'),('staff6','staff6','RESTAURANT STAFF','Y'),('staff60','staff60','RESTAURANT STAFF','Y'),('staff61','staff61','RESTAURANT STAFF','Y'),('staff62','staff62','RESTAURANT STAFF','Y'),('staff63','staff63','RESTAURANT STAFF','Y'),('staff64','staff64','RESTAURANT STAFF','Y'),('staff65','staff65','RESTAURANT STAFF','Y'),('staff66','staff66','RESTAURANT STAFF','Y'),('staff67','staff67','RESTAURANT STAFF','Y'),('staff68','staff68','RESTAURANT STAFF','Y'),('staff69','staff69','RESTAURANT STAFF','Y'),('staff7','staff7','RESTAURANT STAFF','Y'),('staff70','staff70','RESTAURANT STAFF','Y'),('staff71','staff71','RESTAURANT STAFF','Y'),('staff72','staff72','RESTAURANT STAFF','Y'),('staff73','staff73','RESTAURANT STAFF','Y'),('staff74','staff74','RESTAURANT STAFF','Y'),('staff75','staff75','RESTAURANT STAFF','Y'),('staff76','staff76','RESTAURANT STAFF','Y'),('staff77','staff77','RESTAURANT STAFF','Y'),('staff78','staff78','RESTAURANT STAFF','Y'),('staff79','staff79','RESTAURANT STAFF','Y'),('staff8','staff8','RESTAURANT STAFF','Y'),('staff80','staff80','RESTAURANT STAFF','Y'),('staff81','staff81','RESTAURANT STAFF','Y'),('staff82','staff82','RESTAURANT STAFF','Y'),('staff83','staff83','RESTAURANT STAFF','Y'),('staff84','staff84','RESTAURANT STAFF','Y'),('staff85','staff85','RESTAURANT STAFF','Y'),('staff9','staff9','RESTAURANT STAFF','Y'),('useradmin1','useradmin1','USER ADMIN','Y'),('useradmin2','useradmin2','USER ADMIN','Y');
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

-- Dump completed on 2022-05-11 17:57:24
