-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: compras
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `compras` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `num_nota` varchar(6) NOT NULL,
  `dat_compra` datetime NOT NULL,
  `fornecedores_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_compras_fornecedores` (`fornecedores_id`),
  CONSTRAINT `FK_compras_fornecedores` FOREIGN KEY (`fornecedores_id`) REFERENCES `fornecedores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras_itens`
--

DROP TABLE IF EXISTS `compras_itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `compras_itens` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `quantidade` decimal(10,2) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `compras_id` int(10) unsigned NOT NULL,
  `produtos_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_compras_itens_compras` (`compras_id`),
  KEY `FK_compras_itens_produtos` (`produtos_id`),
  CONSTRAINT `FK_compras_itens_compras` FOREIGN KEY (`compras_id`) REFERENCES `compras` (`id`),
  CONSTRAINT `FK_compras_itens_produtos` FOREIGN KEY (`produtos_id`) REFERENCES `produtos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras_itens`
--

LOCK TABLES `compras_itens` WRITE;
/*!40000 ALTER TABLE `compras_itens` DISABLE KEYS */;
/*!40000 ALTER TABLE `compras_itens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fornecedores` (
  `id` int(10) unsigned NOT NULL,
  `razao` varchar(100) NOT NULL,
  `nome_fantasia` varchar(100) DEFAULT NULL,
  `telefone` varchar(25) NOT NULL,
  `celular` varchar(25) NOT NULL,
  `nome_contato` varchar(100) NOT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `cnpj` varchar(18) NOT NULL,
  `inscricao` varchar(20) NOT NULL,
  `dat_ultima_compra` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores`
--

LOCK TABLES `fornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores` DISABLE KEYS */;
INSERT INTO `fornecedores` VALUES (2,'Zezinhooo','Zezinho fantasia','ewrtyhry','(11) 1 1111-111','Zezinho fantasia',NULL,NULL,NULL,NULL,NULL,'10000','1020102223',NULL),(3,'Rico asdas2','Rico','asdas','(11) 1 1111-111','Rico',NULL,NULL,NULL,NULL,NULL,'20000',' asd',NULL),(12,'teste 1111111','aaaaaaa','1212121222','(11) 1 1111-111','aaaaaaa',NULL,NULL,NULL,NULL,NULL,'2121212','1111111',NULL);
/*!40000 ALTER TABLE `fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produtos` (
  `id` int(10) unsigned NOT NULL,
  `nome` varchar(255) NOT NULL,
  `codigo_barras` varchar(13) DEFAULT NULL,
  `dat_ultima_compra` datetime DEFAULT NULL,
  `dat_ultima_venda` datetime DEFAULT NULL,
  `vlr_custo` decimal(10,2) NOT NULL,
  `vlr_venda` decimal(10,2) NOT NULL,
  `margem_lucro` decimal(10,3) DEFAULT NULL,
  `estoque` int(11) NOT NULL,
  `fornecedores_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (4,'Produto 11',NULL,NULL,NULL,10.00,30.00,NULL,1000,2);
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'compras'
--

--
-- Dumping routines for database 'compras'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-08  0:03:59
