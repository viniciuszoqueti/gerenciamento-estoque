-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.27


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema compras
--

CREATE DATABASE IF NOT EXISTS compras;
USE compras;

--
-- Definition of table `compras`
--

DROP TABLE IF EXISTS `compras`;
CREATE TABLE `compras` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `num_nota` varchar(6) NOT NULL,
  `dat_compra` datetime NOT NULL,
  `fornecedores_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_compras_fornecedores` (`fornecedores_id`),
  CONSTRAINT `FK_compras_fornecedores` FOREIGN KEY (`fornecedores_id`) REFERENCES `fornecedores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `compras`
--

/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` (`id`,`num_nota`,`dat_compra`,`fornecedores_id`) VALUES 
 (2,'000001','2018-11-12 00:00:00',1),
 (3,'000002','2018-11-13 00:00:00',2);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;


--
-- Definition of table `compras_itens`
--

DROP TABLE IF EXISTS `compras_itens`;
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

--
-- Dumping data for table `compras_itens`
--

/*!40000 ALTER TABLE `compras_itens` DISABLE KEYS */;
INSERT INTO `compras_itens` (`id`,`quantidade`,`valor`,`compras_id`,`produtos_id`) VALUES 
 (3,'10.00','2.50',2,1),
 (4,'5.00','30.00',2,2),
 (5,'1.00','10.00',3,1);
/*!40000 ALTER TABLE `compras_itens` ENABLE KEYS */;


--
-- Definition of table `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
CREATE TABLE `fornecedores` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fornecedores`
--

/*!40000 ALTER TABLE `fornecedores` DISABLE KEYS */;
INSERT INTO `fornecedores` (`id`,`razao`,`nome_fantasia`,`telefone`,`celular`,`nome_contato`,`endereco`,`numero`,`bairro`,`cidade`,`estado`,`cnpj`,`inscricao`,`dat_ultima_compra`) VALUES 
 (1,'Fornecedor 1','fornecedor 1','4444444444444444','555555555555555555555','Paulo',NULL,NULL,NULL,NULL,NULL,'01.001.001/0001-01','444444444444444',NULL),
 (2,'Fornecedor 2','Fornecedor 2','5555555555555555','66666666666666666666','João e Maria',NULL,NULL,NULL,NULL,NULL,'00.000.000/00001-0','6666666666666666',NULL);
/*!40000 ALTER TABLE `fornecedores` ENABLE KEYS */;


--
-- Definition of table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
CREATE TABLE `produtos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produtos`
--

/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` (`id`,`nome`,`codigo_barras`,`dat_ultima_compra`,`dat_ultima_venda`,`vlr_custo`,`vlr_venda`,`margem_lucro`,`estoque`,`fornecedores_id`) VALUES 
 (1,'Produto 1','123456789012',NULL,NULL,'1.00','10.00',NULL,10,1),
 (2,'Produto 2','098765432109',NULL,NULL,'2.00','20.00',NULL,5,1);
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
