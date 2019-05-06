-- MySQL dump 10.13  Distrib 5.7.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: livrariavirtual
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.38-MariaDB

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
-- Table structure for table `livraria`
--

DROP TABLE IF EXISTS `livraria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livraria` (
  `livrariaId` int(3) NOT NULL AUTO_INCREMENT,
  `numImpressos` int(20) NOT NULL,
  `numEletronicos` int(20) NOT NULL,
  `numVendas` int(20) NOT NULL,
  PRIMARY KEY (`livrariaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livraria`
--

LOCK TABLES `livraria` WRITE;
/*!40000 ALTER TABLE `livraria` DISABLE KEYS */;
/*!40000 ALTER TABLE `livraria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livro` (
  `livroId` int(20) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(40) DEFAULT NULL,
  `autores` varchar(40) DEFAULT NULL,
  `editora` varchar(40) DEFAULT NULL,
  `preco` float(4,2) DEFAULT NULL,
  `numVendas` int(50) DEFAULT NULL,
  PRIMARY KEY (`livroId`),
  KEY `numVendas_fk_idx` (`numVendas`),
  CONSTRAINT `numVendas_fk` FOREIGN KEY (`numVendas`) REFERENCES `venda` (`numVendas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
INSERT INTO `livro` VALUES (1,'brunao','silva','azul',50.00,NULL),(2,'fodeovei','bruno e adriano','tocomedo',50.00,NULL),(3,'brubis','adriano100%hotuils','fodeo',50.00,NULL),(4,'fodeovei2','adrianous','vinteecinco',25.00,NULL),(5,'kk','kk','kk',12.00,NULL),(6,'kk','kk','kk',15.00,NULL),(7,'kk','kk','kk',15.00,NULL),(8,'a','a','a',14.00,NULL),(9,'a','a','a',14.00,NULL),(10,'aoskda','sagjskd','nusei',10.00,NULL),(11,'brusasd','bsdaj','as',5.00,NULL),(12,'poramento puto','eagora','nusei',5.00,NULL),(13,'cu','fedido','do',15.00,NULL),(14,'vaimenpufavo','agorafoi','sera',15.00,NULL),(15,'brun','n','s',15.00,NULL),(16,'foimen','nice','k',20.00,NULL);
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livroeletronico`
--

DROP TABLE IF EXISTS `livroeletronico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livroeletronico` (
  `livroEletronicoId` int(3) NOT NULL AUTO_INCREMENT,
  `tamanho` float(4,2) NOT NULL,
  `livroId` int(3) DEFAULT NULL,
  `livrariaId` int(3) DEFAULT NULL,
  `numVendas` int(3) DEFAULT NULL,
  PRIMARY KEY (`livroEletronicoId`),
  KEY `livroId_fk_idx` (`livroId`),
  KEY `livrariaIdd_fk_idx` (`livrariaId`),
  KEY `numVendas` (`numVendas`),
  CONSTRAINT `livrariaIdd_fk` FOREIGN KEY (`livrariaId`) REFERENCES `livraria` (`livrariaId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `livroIdd_fk` FOREIGN KEY (`livroId`) REFERENCES `livro` (`livroId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `livroeletronico_ibfk_1` FOREIGN KEY (`numVendas`) REFERENCES `venda` (`numVendas`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livroeletronico`
--

LOCK TABLES `livroeletronico` WRITE;
/*!40000 ALTER TABLE `livroeletronico` DISABLE KEYS */;
INSERT INTO `livroeletronico` VALUES (1,16.00,16,NULL,NULL);
/*!40000 ALTER TABLE `livroeletronico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livroimpresso`
--

DROP TABLE IF EXISTS `livroimpresso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livroimpresso` (
  `livroImpressoId` int(3) NOT NULL AUTO_INCREMENT,
  `frete` float(4,2) NOT NULL,
  `estoque` int(20) NOT NULL,
  `livroId` int(3) DEFAULT NULL,
  `livrariaId` int(3) DEFAULT NULL,
  `numVendas` int(3) DEFAULT NULL,
  PRIMARY KEY (`livroImpressoId`),
  KEY `livroid_fk_idx` (`livroId`),
  KEY `livrariaId_fk_idx` (`livrariaId`),
  KEY `numVendas` (`numVendas`),
  CONSTRAINT `livrariaId_fk` FOREIGN KEY (`livrariaId`) REFERENCES `livraria` (`livrariaId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `livroid_fk` FOREIGN KEY (`livroId`) REFERENCES `livro` (`livroId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `livroimpresso_ibfk_1` FOREIGN KEY (`numVendas`) REFERENCES `venda` (`numVendas`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livroimpresso`
--

LOCK TABLES `livroimpresso` WRITE;
/*!40000 ALTER TABLE `livroimpresso` DISABLE KEYS */;
INSERT INTO `livroimpresso` VALUES (1,30.00,15,NULL,NULL,NULL),(2,3.00,2,1,NULL,NULL),(3,10.00,15,2,NULL,NULL),(4,16.00,16,16,NULL,NULL);
/*!40000 ALTER TABLE `livroimpresso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `numero` int(3) DEFAULT NULL,
  `cliente` varchar(40) DEFAULT NULL,
  `valor` float(4,2) DEFAULT NULL,
  `livrariaId` int(3) DEFAULT NULL,
  `numVendas` int(50) NOT NULL AUTO_INCREMENT,
  `livroId` int(3) DEFAULT NULL,
  `livroimpressoId` int(3) DEFAULT NULL,
  `livroeletronicoId` int(3) DEFAULT NULL,
  PRIMARY KEY (`numVendas`),
  KEY `livrariaIddd_fk_idx` (`livrariaId`),
  KEY `livroId` (`livroId`),
  KEY `livroimpressoId` (`livroimpressoId`),
  KEY `livroeletronicoId` (`livroeletronicoId`),
  CONSTRAINT `livrariaIddd_fk` FOREIGN KEY (`livrariaId`) REFERENCES `livraria` (`livrariaId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`livroId`) REFERENCES `livro` (`livroId`),
  CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`livroimpressoId`) REFERENCES `livroimpresso` (`livroImpressoId`),
  CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`livroeletronicoId`) REFERENCES `livroeletronico` (`livroEletronicoId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (NULL,'Robertin',NULL,NULL,1,NULL,NULL,NULL),(NULL,'Robertin',NULL,NULL,2,NULL,NULL,NULL),(NULL,'Robertin',NULL,NULL,3,NULL,NULL,NULL),(NULL,NULL,NULL,NULL,4,16,NULL,NULL),(NULL,'Robertin',NULL,NULL,5,16,NULL,NULL);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'livrariavirtual'
--

--
-- Dumping routines for database 'livrariavirtual'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-06 18:45:26
