/*
SQLyog Ultimate v10.42 
MySQL - 5.5.5-10.4.8-MariaDB : Database - reservasvueloshotelesl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`reservasvueloshotelesl` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `reservasvueloshotelesl`;

/*Table structure for table `hotel` */

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `estrellas` float DEFAULT NULL,
  `disponibilidad` int(11) DEFAULT NULL,
  `precioNoche` float DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `hotel` */

insert  into `hotel`(`id`,`nombre`,`direccion`,`estrellas`,`disponibilidad`,`precioNoche`,`pais`,`ciudad`) values (1,'Savoy Hotel','Avenida callao 181',4,500,7000,'Argentina','Buenos Aires'),(2,'Faena Hotel Buenos Aires','445 Martha Salotti',5,700,8000,'Argentina','Buenos Aires'),(3,'Sheraton Buenos Aires Hotel','San Martin 1225/1275',5,1498,12000,'Argentina','Buenos Aires'),(4,'Lennox Miami Beach','1900 Collins Avenue',4,1689,13000,'Estados Unidos','Miami'),(5,'Hotel South Beach','2341 Collins Avenue',5,1981,23000,'Estados Unidos','Miami'),(6,'Trump International New York','One Central Park West',5,3500,23400,'Estados Unidos','New York'),(7,'Hotel Riu Plaza España','Calle Gran Vía, 84',4,1500,7500,'España','Madrid'),(8,'Hyatt Centric Gran Via Madrid','Gran Via, 31',5,3298,17000,'España','Madrid'),(10,'InterContinental Mendoza','Blvd Perez Cuesta Esq Acceso Este Lateral Norte',5,3000,15000,'Argentina','Mendoza'),(11,'Park Hyatt Mendoza Hotel','Chile 1124',4,1700,9500,'Argentina','Mendoza');

/*Table structure for table `reserva` */

DROP TABLE IF EXISTS `reserva`;

CREATE TABLE `reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clase` varchar(100) DEFAULT NULL,
  `desde` date DEFAULT NULL,
  `hasta` date DEFAULT NULL,
  `pasajeros` int(11) DEFAULT NULL,
  `habitaciones` int(11) DEFAULT NULL,
  `adultos` int(11) DEFAULT NULL,
  `menores` int(11) DEFAULT NULL,
  `precioTotal` float DEFAULT NULL,
  `vuelo_id` int(11) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

/*Data for the table `reserva` */

insert  into `reserva`(`id`,`clase`,`desde`,`hasta`,`pasajeros`,`habitaciones`,`adultos`,`menores`,`precioTotal`,`vuelo_id`,`hotel_id`) values (1,'Premium Economy','2020-06-10','2020-06-15',2,1,2,0,130000,1,5),(2,'Business','2020-06-11','2020-06-18',3,1,2,1,79500,2,3),(3,'Business','2020-07-02','2020-07-10',2,1,1,1,190700,7,6),(4,'Economy','2020-07-10','2020-07-20',1,1,1,0,92500,10,2),(5,'Economy','2020-06-11','2020-06-12',1,1,1,0,28000,1,4),(20,'Business','2020-07-18','2020-07-26',2,1,2,0,148500,11,8),(21,'Economy','2020-06-10','2020-06-12',1,1,2,0,61000,1,5),(22,'Premium Economy','2020-06-13','2020-06-17',2,2,2,0,66000,3,3);

/*Table structure for table `vuelo` */

DROP TABLE IF EXISTS `vuelo`;

CREATE TABLE `vuelo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aerolinea` varchar(100) DEFAULT NULL,
  `fechaPartida` date DEFAULT NULL,
  `horaPartida` varchar(100) DEFAULT NULL,
  `fechaLlegada` date DEFAULT NULL,
  `horaLlegada` varchar(100) DEFAULT NULL,
  `escala` varchar(50) DEFAULT NULL,
  `asientosDisp` int(11) DEFAULT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `ciudadOrigen` varchar(100) DEFAULT NULL,
  `ciudadDestino` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `vuelo` */

insert  into `vuelo`(`id`,`aerolinea`,`fechaPartida`,`horaPartida`,`fechaLlegada`,`horaLlegada`,`escala`,`asientosDisp`,`capacidad`,`precio`,`ciudadOrigen`,`ciudadDestino`) values (1,'Aerolíneas Argentinas','2020-06-09','16:00','2020-06-09','20:00','Si',22,100,15000,'Mendoza','Miami'),(2,'Aerolíneas Argentinas','2020-06-10','12:00','2020-06-10','13:00','No',40,60,7500,'Mendoza','Buenos Aires'),(3,'American Airlines	','2020-06-12','20:00','2020-06-13','03:30','No',13,120,18000,'New York','Buenos Aires'),(4,'American Airlines','2020-06-16','17:30','2020-06-16','20:15','No',25,100,14000,'Buenos Aires','Miami'),(5,'Aerolíneas Argentinas','2020-06-20','09:00','2020-06-20','19:00','No',40,80,12500,'Mendoza','Madrid'),(6,'Emirates','2020-06-27','22:00','2020-06-28','08:00','No',25,100,14000,'Madrid','Mendoza'),(7,'Emirates','2020-07-01','19:30','2020-07-01','21:00','No',10,100,3500,'Miami','New York'),(8,'Emirates','2020-07-03','15:45','2020-07-03','17:00','No',23,100,3500,'New York','Miami'),(9,'American Airlines','2020-07-10','13:00','2020-07-07','20:00','Si',45,120,18000,'Buenos Aires','New York'),(10,'Qatar Airways','2020-07-16','17:00','2020-07-17','15:00','No',32,100,12500,'Madrid','Buenos Aires'),(11,'Qatar Airways','2020-07-09','18:30','2020-07-10','16:30','No',53,100,12500,'Buenos Aires','Madrid'),(12,'Aerolíneas Argentinas','2020-07-11','22:00','2020-07-11','23:30','No',30,60,7500,'Mendoza','Buenos Aires');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
