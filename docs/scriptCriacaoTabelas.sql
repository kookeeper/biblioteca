-- MySQL dump 10.11
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt

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
-- Table structure for table `bloquear_material_bibliografico`
--

DROP TABLE IF EXISTS `bloquear_material_bibliografico`;
CREATE TABLE `bloquear_material_bibliografico` (
  `id_bloquear_material_bibliografico` int(11) NOT NULL auto_increment,
  `id_material_bibliografico` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `data_bloqueio` datetime NOT NULL,
  `motivo_bloqueio` text NOT NULL,
  `status_bloqueio` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY  (`id_bloquear_material_bibliografico`),
  KEY `fk_bloquear_material_bibliografico_material_bibliografico1` (`id_material_bibliografico`),
  KEY `fk_bloquear_material_bibliografico_usuario1` (`id_usuario`),
  CONSTRAINT `fk_bloquear_material_bibliografico_material_bibliografico1` FOREIGN KEY (`id_material_bibliografico`) REFERENCES `material_bibliografico` (`id_material_bibliografico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bloquear_material_bibliografico_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `emprestar_material_bibliografico`
--

DROP TABLE IF EXISTS `emprestar_material_bibliografico`;
CREATE TABLE `emprestar_material_bibliografico` (
  `id_emprestar_material_bibliografico` int(11) NOT NULL auto_increment,
  `id_material_bibliografico` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `data_emprestimo` datetime NOT NULL,
  `data_devolucao_prevista` datetime NOT NULL,
  `data_devolucao_efetiva` datetime default NULL,
  PRIMARY KEY  (`id_emprestar_material_bibliografico`),
  KEY `fk_reserva_material_bibliografico_material_bibliografico1` (`id_material_bibliografico`),
  KEY `fk_emprestimo_material_bibliografico_usuario1` (`id_usuario`),
  CONSTRAINT `fk_emprestimo_material_bibliografico_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_material_bibliografico_material_bibliografico1` FOREIGN KEY (`id_material_bibliografico`) REFERENCES `material_bibliografico` (`id_material_bibliografico`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `grupo_usuario`
--

DROP TABLE IF EXISTS `grupo_usuario`;
CREATE TABLE `grupo_usuario` (
  `id_grupo_usuario` int(11) NOT NULL auto_increment,
  `descricao_grupo_usuario` varchar(50) NOT NULL,
  PRIMARY KEY  (`id_grupo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `grupo_usuario_permissao`
--

DROP TABLE IF EXISTS `grupo_usuario_permissao`;
CREATE TABLE `grupo_usuario_permissao` (
  `id_grupo_usuario_permissao` int(11) NOT NULL auto_increment,
  `id_grupo_usuario` int(11) NOT NULL,
  `id_permissao` int(11) NOT NULL,
  `tipo_permissao` set('consultar','alterar','excluir') NOT NULL,
  PRIMARY KEY  (`id_grupo_usuario_permissao`),
  KEY `fk_grupo_usuario_permissao_grupo_usuario1` (`id_grupo_usuario`),
  KEY `fk_grupo_usuario_permissao_permissao1` (`id_permissao`),
  CONSTRAINT `fk_grupo_usuario_permissao_grupo_usuario1` FOREIGN KEY (`id_grupo_usuario`) REFERENCES `grupo_usuario` (`id_grupo_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_usuario_permissao_permissao1` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id_permissao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `material_bibliografico`
--

DROP TABLE IF EXISTS `material_bibliografico`;
CREATE TABLE `material_bibliografico` (
  `id_material_bibliografico` int(11) NOT NULL auto_increment,
  `id_tipo_material_bibliografico` int(11) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `edicao` varchar(45) NOT NULL,
  `data_aquisicao` datetime NOT NULL,
  `editora` varchar(45) NOT NULL,
  `resenha` text,
  `capa` blob,
  PRIMARY KEY  (`id_material_bibliografico`),
  KEY `fk_material_bibliografico_tipo_material_bibliografico1` (`id_tipo_material_bibliografico`),
  CONSTRAINT `fk_material_bibliografico_tipo_material_bibliografico1` FOREIGN KEY (`id_tipo_material_bibliografico`) REFERENCES `tipo_material_bibliografico` (`id_tipo_material_bibliografico`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
CREATE TABLE `permissao` (
  `id_permissao` int(11) NOT NULL auto_increment,
  `funcionalidade` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_permissao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `reservar_material_bibliografico`
--

DROP TABLE IF EXISTS `reservar_material_bibliografico`;
CREATE TABLE `reservar_material_bibliografico` (
  `id_reservar_material_bibliografico` int(11) NOT NULL auto_increment,
  `id_material_bibliografico` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_solicitar_reserva` int(11) default NULL,
  `data_inicio_reserva` datetime NOT NULL,
  `periodo_reserva` int(11) NOT NULL,
  `status_reserva` varchar(45) default NULL,
  `observacao` text,
  PRIMARY KEY  (`id_reservar_material_bibliografico`),
  KEY `fk_reserva_material_bibliografico_usuario1` (`id_usuario`),
  KEY `fk_reserva_material_bibliografico_material_bibliografico2` (`id_material_bibliografico`),
  KEY `fk_reserva_material_bibliografico_solicitar_reserva1` (`id_solicitar_reserva`),
  CONSTRAINT `fk_reserva_material_bibliografico_material_bibliografico2` FOREIGN KEY (`id_material_bibliografico`) REFERENCES `material_bibliografico` (`id_material_bibliografico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_material_bibliografico_solicitar_reserva1` FOREIGN KEY (`id_solicitar_reserva`) REFERENCES `solicitar_reserva` (`id_solicitar_reserva`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_material_bibliografico_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `solicitar_reserva`
--

DROP TABLE IF EXISTS `solicitar_reserva`;
CREATE TABLE `solicitar_reserva` (
  `id_solicitar_reserva` int(11) NOT NULL auto_increment,
  `id_usuario` int(11) NOT NULL,
  `id_material_bibliografico` int(11) NOT NULL,
  `data_solicitacao` datetime NOT NULL,
  `observacao` text,
  PRIMARY KEY  (`id_solicitar_reserva`),
  KEY `fk_solicitar_reserva_usuario1` (`id_usuario`),
  KEY `fk_solicitar_reserva_material_bibliografico1` (`id_material_bibliografico`),
  CONSTRAINT `fk_solicitar_reserva_material_bibliografico1` FOREIGN KEY (`id_material_bibliografico`) REFERENCES `material_bibliografico` (`id_material_bibliografico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitar_reserva_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `tipo_material_bibliografico`
--

DROP TABLE IF EXISTS `tipo_material_bibliografico`;
CREATE TABLE `tipo_material_bibliografico` (
  `id_tipo_material_bibliografico` int(11) NOT NULL auto_increment,
  `descricao_tipo_material_bibliografico` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_tipo_material_bibliografico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL auto_increment,
  `id_grupo_usuario` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `data_cadastro` datetime default NULL,
  `bloqueado` tinyint(1) NOT NULL default '0',
  `motivo_bloqueio` text,
  `email` varchar(50) default NULL,
  `nome_usuario` varchar(100) NOT NULL,
  PRIMARY KEY  (`id_usuario`),
  UNIQUE KEY `uk_login` (`login`),
  KEY `fk_usuario_grupo_usuario` (`id_grupo_usuario`),
  CONSTRAINT `fk_usuario_grupo_usuario` FOREIGN KEY (`id_grupo_usuario`) REFERENCES `grupo_usuario` (`id_grupo_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-08 23:46:39

