/*
SQLyog Ultimate v8.32 
MySQL - 5.5.36 : Database - librarysys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`librarysys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `librarysys`;

/*Table structure for table `book_info` */

DROP TABLE IF EXISTS `book_info`;

CREATE TABLE `book_info` (
  `book_id` varchar(45) NOT NULL,
  `book_name` varchar(45) NOT NULL,
  `book_type_id` varchar(45) NOT NULL,
  `book_press` varchar(100) NOT NULL,
  `bookrack_id` int(11) NOT NULL,
  `bookrack_number` int(11) NOT NULL,
  `book_writer` varchar(45) DEFAULT NULL,
  `book_is_borrow` tinyint(1) NOT NULL,
  `book_introduce` varchar(2000) DEFAULT NULL,
  `book_is_delete` tinyint(1) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK_book_info_press` (`book_press`),
  KEY `FK_book_info_book_type` (`book_type_id`),
  KEY `FK_book_info` (`bookrack_id`),
  CONSTRAINT `FK_book_info` FOREIGN KEY (`bookrack_id`) REFERENCES `bookrack` (`bookrack_id`),
  CONSTRAINT `FK_book_info_book_type` FOREIGN KEY (`book_type_id`) REFERENCES `book_type` (`boty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

/*Data for the table `book_info` */

insert  into `book_info`(`book_id`,`book_name`,`book_type_id`,`book_press`,`bookrack_id`,`bookrack_number`,`book_writer`,`book_is_borrow`,`book_introduce`,`book_is_delete`,`id`) values ('AVEDM34g','书籍1','0o9wpNa9','1',29,0,'作家1',1,'\n阿斯蒂芬',0,91),('AVEDM34g','书籍1','0o9wpNa9','1',2,0,'作家1',0,'\n阿斯蒂芬',1,92),('tzNongVI','书籍2','YouLAOwn','地方',29,0,'作者1',1,'\n',0,93),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',1,94),('GAGpM7B3','书库12','0o9wpNa9','4',2,0,'4',0,'\n\n\n',1,95),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',1,'\n\n\n',0,96),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',0,97),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',0,98),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',1,99),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',1,100),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',1,101),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',1,102),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',1,103),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',1,104),('GAGpM7B3','书库12','0o9wpNa9','4',28,2,'4',0,'\n\n\n',1,105);

/*Table structure for table `book_type` */

DROP TABLE IF EXISTS `book_type`;

CREATE TABLE `book_type` (
  `boty_id` varchar(45) NOT NULL,
  `book_type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`boty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book_type` */

insert  into `book_type`(`boty_id`,`book_type_name`) values ('0o9wpNa9','撒反对'),('2','文学'),('3','小说'),('JlyVgg1t','阿斯顿发'),('o4dO6hzH','撒地方'),('PhaeU4DH','的说法'),('qJzEU8sE','阿斯顿发'),('QlVAE7Qs','阿斯顿发生'),('QQ4KAYFb','打发第三方'),('syOv4MTD','阿发'),('xbQ0jLh5','手动阀是打发是的发生'),('xwS39ot7','范德萨'),('YouLAOwn','你哈'),('zhzyQXPd','阿斯顿发');

/*Table structure for table `bookrack` */

DROP TABLE IF EXISTS `bookrack`;

CREATE TABLE `bookrack` (
  `bookrack_id` int(11) NOT NULL AUTO_INCREMENT,
  `sr_id` int(11) NOT NULL,
  `bookr_name` varchar(45) NOT NULL,
  `br_add_book_number` int(11) NOT NULL,
  PRIMARY KEY (`bookrack_id`),
  KEY `FK_bookrack` (`sr_id`),
  CONSTRAINT `FK_bookrack` FOREIGN KEY (`sr_id`) REFERENCES `stack_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `bookrack` */

insert  into `bookrack`(`bookrack_id`,`sr_id`,`bookr_name`,`br_add_book_number`) values (1,1,'闲置书架',0),(2,2,'下架书架',0),(28,32,'默认书架1',2),(29,32,'书架2',2);

/*Table structure for table `borrow_info` */

DROP TABLE IF EXISTS `borrow_info`;

CREATE TABLE `borrow_info` (
  `id` varchar(45) NOT NULL,
  `book_id` int(45) NOT NULL,
  `reader_id` varchar(45) NOT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `boorow_is_renew` tinyint(1) NOT NULL,
  `borrow_status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrow_info` */

insert  into `borrow_info`(`id`,`book_id`,`reader_id`,`begin_time`,`end_time`,`boorow_is_renew`,`borrow_status`) values ('b2837ba725b64881863608d8568b6950',93,'141110045','2017-07-13 17:38:15','2017-07-13 17:38:24',0,2),('d26b2ec5f4114996b78c9b32a85cb7c1',96,'100000005','2017-07-13 19:08:52','2017-08-12 23:59:59',0,1),('d5d67da40d8d438d8dc1489f24ab4141',91,'141110045','2017-07-13 22:29:26','2017-08-12 23:59:59',1,1),('f535b1dfcf284e2184d59dde45d764b8',91,'141110045','2017-07-11 11:31:51','2017-07-11 11:32:20',0,2),('f9a1f81279ec4dbeb50a40b0ada55154',93,'141110045','2017-07-13 22:29:45','2017-09-11 23:59:59',1,1);

/*Table structure for table `borrow_operation` */

DROP TABLE IF EXISTS `borrow_operation`;

CREATE TABLE `borrow_operation` (
  `id` varchar(45) NOT NULL,
  `borrow_id` varchar(45) NOT NULL,
  `operation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operation_type` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_borrow_operation` (`borrow_id`),
  CONSTRAINT `FK_borrow_operation` FOREIGN KEY (`borrow_id`) REFERENCES `borrow_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrow_operation` */

insert  into `borrow_operation`(`id`,`borrow_id`,`operation_time`,`operation_type`) values ('13848bf69fcf415580e8b734e833f1a0','b2837ba725b64881863608d8568b6950','2017-07-13 17:38:24',0),('1e7201d8ae59469190f99d23a6796d38','d5d67da40d8d438d8dc1489f24ab4141','2017-07-13 22:29:26',1),('26309cc344dc432f97efadee7e5a50a0','f535b1dfcf284e2184d59dde45d764b8','2017-07-11 11:32:20',0),('5c65bb2a4db8418a9c2734cec722b945','f9a1f81279ec4dbeb50a40b0ada55154','2017-07-13 22:29:45',1),('5e77a41e94aa4f2cb388d104df0c2f6a','f535b1dfcf284e2184d59dde45d764b8','2017-07-11 11:31:51',1),('9ec54e60b7fd4b12941307bcae2b2c44','b2837ba725b64881863608d8568b6950','2017-07-13 17:38:15',1),('c926a55a6ae84722af2138dd738ee066','d26b2ec5f4114996b78c9b32a85cb7c1','2017-07-13 19:08:52',1);

/*Table structure for table `library_admin` */

DROP TABLE IF EXISTS `library_admin`;

CREATE TABLE `library_admin` (
  `id_card` char(18) NOT NULL,
  `pic` varchar(200) DEFAULT NULL,
  `account` varchar(11) NOT NULL,
  `passwd` varbinary(50) NOT NULL,
  `admin_name` varchar(45) NOT NULL,
  `ais_root` tinyint(1) NOT NULL,
  `asr_ju` tinyint(1) NOT NULL,
  `lib_ju` tinyint(1) NOT NULL,
  `read_type_ju` tinyint(1) NOT NULL,
  `reader_ju` tinyint(1) NOT NULL,
  `ais_status` tinyint(1) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `library_admin` */

insert  into `library_admin`(`id_card`,`pic`,`account`,`passwd`,`admin_name`,`ais_root`,`asr_ju`,`lib_ju`,`read_type_ju`,`reader_ju`,`ais_status`) values ('----','admin/10000.jpg','10000','x�o|$_�~K�Hf��sҽ�s]���K{','root',1,1,1,1,1,1),('440583111111111111','admin/10001.jpg','10001','��F� g�Y�lۏ�2�A\"�F����Ύ�0','你好啊',0,0,1,0,1,0),('111111111111111111','admin/10002.jpg','10002','	~�/�U��叒o�?B�L,C�A��/���%','沙发',0,0,0,0,0,0),('111111111111111111','admin/10003.jpg','10003','���S�S1>a�0�#��ݚ��4�M�2�z�','111111111111111111',0,0,0,0,0,0),('111111111111111111','admin/10004.jpg','10004','��6}Wl�� ��q;i�IJ8|K���','111111111111111111',0,0,0,0,0,0),('123456789098765432','admin/10005.jpg','10005','�#,ژ���6�s�C\\��|h�1��qw�e\'','林先生',0,0,1,0,1,1),('----',NULL,'662805594','#��,uO\0o^Qy�5�qr�/����_S\"�','662805594',0,1,1,1,0,0),('----',NULL,'681713848','}��o\\�Q-$!�� ����4�Q4�:��','681713848',0,1,1,0,1,0),('----',NULL,'685775980','V\0gw-����n�^/j\"��̿c���Bt���','685775980',0,0,0,0,1,0),('----',NULL,'709187509','M��9Ŭy�ߔV�%�Q�Q�����X�ǜ','709187509',0,0,0,0,0,0),('----',NULL,'714335619','\\���Q*S��9\n�����ãJd&³<','714335619',0,0,0,1,1,0);

/*Table structure for table `reader` */

DROP TABLE IF EXISTS `reader`;

CREATE TABLE `reader` (
  `id_card` char(18) NOT NULL,
  `pic` varchar(200) DEFAULT NULL,
  `ris_status` tinyint(1) NOT NULL,
  `account` varchar(11) NOT NULL,
  `type` int(11) NOT NULL,
  `passwd` varbinary(50) NOT NULL,
  `users_name` varchar(45) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK_reader_type` (`type`),
  CONSTRAINT `FK_reader_type` FOREIGN KEY (`type`) REFERENCES `reader_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `reader` */

insert  into `reader`(`id_card`,`pic`,`ris_status`,`account`,`type`,`passwd`,`users_name`,`id`) values ('123456789032165487','reader/141110045.jpg',1,'141110045',1,'�	G���RM��_�{�<N����I�|L8�','发',31),('123456789876543210','reader/100000005.jpg',1,'100000005',1,'�J\"|��k�D���;/W#Vbm,\Z�%\\t�','你好',32);

/*Table structure for table `reader_type` */

DROP TABLE IF EXISTS `reader_type`;

CREATE TABLE `reader_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  `borrow` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `reader_type` */

insert  into `reader_type`(`id`,`type_name`,`borrow`) values (1,'学生',5),(2,'SDF',4),(4,'人违反',45),(5,'打发',0),(6,'士大夫',45),(7,'撒地方',45);

/*Table structure for table `stack_room` */

DROP TABLE IF EXISTS `stack_room`;

CREATE TABLE `stack_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sr_name` varchar(20) NOT NULL,
  `sr_address` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `stack_room` */

insert  into `stack_room`(`id`,`sr_name`,`sr_address`) values (1,'闲置书库','0'),(2,'下架书库','0'),(32,'书库1','101');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
