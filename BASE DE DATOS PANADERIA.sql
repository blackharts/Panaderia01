-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.7.19 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for panaderia
CREATE DATABASE IF NOT EXISTS `panaderia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `panaderia`;

-- Dumping structure for table panaderia.costos
CREATE TABLE IF NOT EXISTS `costos` (
  `ID_COSTO` int(11) NOT NULL,
  `FECHA_COSTO` date DEFAULT NULL,
  `VALOR_COSTO` int(11) DEFAULT NULL,
  `PRODUCTO_COSTO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_COSTO`),
  KEY `PRODUCTO_COSTO_FK` (`PRODUCTO_COSTO`),
  CONSTRAINT `PRODUCTO_COSTO_FK` FOREIGN KEY (`PRODUCTO_COSTO`) REFERENCES `producto` (`ID_PRODUCTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table panaderia.costos: ~0 rows (approximately)
/*!40000 ALTER TABLE `costos` DISABLE KEYS */;
/*!40000 ALTER TABLE `costos` ENABLE KEYS */;

-- Dumping structure for table panaderia.familia_producto
CREATE TABLE IF NOT EXISTS `familia_producto` (
  `ID_FAMILIA` int(11) NOT NULL,
  `NOMBRE_FAMILIA` varchar(45) NOT NULL,
  `LINEA_PROD` int(11) NOT NULL,
  PRIMARY KEY (`ID_FAMILIA`),
  KEY `LINEA_PROD_FK` (`LINEA_PROD`),
  CONSTRAINT `LINEA_PROD_FK` FOREIGN KEY (`LINEA_PROD`) REFERENCES `linea_producto` (`ID_LINEA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table panaderia.familia_producto: ~0 rows (approximately)
/*!40000 ALTER TABLE `familia_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `familia_producto` ENABLE KEYS */;

-- Dumping structure for table panaderia.linea_producto
CREATE TABLE IF NOT EXISTS `linea_producto` (
  `ID_LINEA` int(11) NOT NULL,
  `NOMBRE_LINEA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_LINEA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table panaderia.linea_producto: ~0 rows (approximately)
/*!40000 ALTER TABLE `linea_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `linea_producto` ENABLE KEYS */;

-- Dumping structure for table panaderia.precio_venta
CREATE TABLE IF NOT EXISTS `precio_venta` (
  `ID-VENTA` int(11) NOT NULL,
  `FECHA_VENTA` date DEFAULT NULL,
  `VALOR_VENTA` int(11) DEFAULT NULL,
  `PROCUTO_VENTA` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID-VENTA`),
  KEY `PRODUCTO_ID` (`PROCUTO_VENTA`),
  CONSTRAINT `PRODUCTO_ID` FOREIGN KEY (`PROCUTO_VENTA`) REFERENCES `producto` (`ID_PRODUCTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table panaderia.precio_venta: ~0 rows (approximately)
/*!40000 ALTER TABLE `precio_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `precio_venta` ENABLE KEYS */;

-- Dumping structure for table panaderia.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `ID_PRODUCTO` int(11) NOT NULL,
  `NOMBRE_PRODUCTO` varchar(100) DEFAULT NULL,
  `MARCA_PRODUCTO` varchar(100) DEFAULT NULL,
  `FORMATO_PRODUCTO` int(11) DEFAULT NULL,
  `FAMILIA_PROD` int(11) DEFAULT NULL,
  `MEDIDA_PRODUCTO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUCTO`),
  KEY `FAMILIA_FK` (`FAMILIA_PROD`),
  KEY `UNIDAD_FK` (`MEDIDA_PRODUCTO`),
  CONSTRAINT `FAMILIA_FK` FOREIGN KEY (`FAMILIA_PROD`) REFERENCES `familia_producto` (`ID_FAMILIA`),
  CONSTRAINT `UNIDAD_FK` FOREIGN KEY (`MEDIDA_PRODUCTO`) REFERENCES `unidad_medida` (`ID_MEDIDA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table panaderia.producto: ~0 rows (approximately)
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Dumping structure for table panaderia.receta
CREATE TABLE IF NOT EXISTS `receta` (
  `ID_RECETA` int(11) NOT NULL,
  `PRODUCTO_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_RECETA`),
  KEY `PRODUCTO_D_FK` (`PRODUCTO_ID`),
  CONSTRAINT `PRODUCTO_D_FK` FOREIGN KEY (`PRODUCTO_ID`) REFERENCES `producto` (`ID_PRODUCTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table panaderia.receta: ~0 rows (approximately)
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;

-- Dumping structure for table panaderia.receta_detalle
CREATE TABLE IF NOT EXISTS `receta_detalle` (
  `RECETA_ID` int(11) DEFAULT NULL,
  `PRODUCTO_ID` int(11) DEFAULT NULL,
  `CANTIDAD` float NOT NULL,
  PRIMARY KEY (`CANTIDAD`),
  KEY `RECETA_ID_FK` (`RECETA_ID`),
  KEY `PRODUCTO_ID_FK` (`PRODUCTO_ID`),
  CONSTRAINT `PRODUCTO_ID_FK` FOREIGN KEY (`PRODUCTO_ID`) REFERENCES `producto` (`ID_PRODUCTO`),
  CONSTRAINT `RECETA_ID_FK` FOREIGN KEY (`RECETA_ID`) REFERENCES `receta` (`ID_RECETA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table panaderia.receta_detalle: ~0 rows (approximately)
/*!40000 ALTER TABLE `receta_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `receta_detalle` ENABLE KEYS */;

-- Dumping structure for table panaderia.unidad_medida
CREATE TABLE IF NOT EXISTS `unidad_medida` (
  `ID_MEDIDA` int(11) NOT NULL,
  `CODIGO` varchar(100) DEFAULT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_MEDIDA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table panaderia.unidad_medida: ~0 rows (approximately)
/*!40000 ALTER TABLE `unidad_medida` DISABLE KEYS */;
/*!40000 ALTER TABLE `unidad_medida` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
