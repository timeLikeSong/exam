/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-11 23:08:44 
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `view_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_address
-- ----------------------------

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', '2017-03-11 23:00:36', '超级管理，拥有最高管理权限', 'liangxiaoyihao@163.com', '73cf0e388971ee4ec34e8daedd0d36cc', '15732154918', null, 'Lx', '1', 'admin');

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role` (
  `ROLE_ID` bigint(20) NOT NULL,
  `ADMIN_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ADMIN_ID`,`ROLE_ID`),
  KEY `FKED864518523ADEE` (`ROLE_ID`),
  KEY `FKED86451F478E1E6` (`ADMIN_ID`),
  CONSTRAINT `FKED864518523ADEE` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKED86451F478E1E6` FOREIGN KEY (`ADMIN_ID`) REFERENCES `t_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_config
-- ----------------------------
INSERT INTO `t_config` VALUES ('1', 'APP_NAME', 'Java竞赛网');
INSERT INTO `t_config` VALUES ('2', 'COPYRIGHT', '@CopyRight By Java 竞赛王. 2017-202');

-- ----------------------------
-- Table structure for t_datacode
-- ----------------------------
DROP TABLE IF EXISTS `t_datacode`;
CREATE TABLE `t_datacode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC6CDF8222CC45C36` (`PID`),
  CONSTRAINT `FKC6CDF8222CC45C36` FOREIGN KEY (`PID`) REFERENCES `t_datacode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_datacode
-- ----------------------------

-- ----------------------------
-- Table structure for t_email
-- ----------------------------
DROP TABLE IF EXISTS `t_email`;
CREATE TABLE `t_email` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `receiver_id` bigint(20) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK19827D1B5C1112A` (`receiver_id`),
  KEY `FK19827D130279940` (`sender_id`),
  CONSTRAINT `FK19827D130279940` FOREIGN KEY (`sender_id`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `FK19827D1B5C1112A` FOREIGN KEY (`receiver_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_email
-- ----------------------------

-- ----------------------------
-- Table structure for t_enroll
-- ----------------------------
DROP TABLE IF EXISTS `t_enroll`;
CREATE TABLE `t_enroll` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enroll_time` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3182BAF13F109A06` (`event_id`),
  KEY `FK3182BAF12A4E71CE` (`user_id`),
  CONSTRAINT `FK3182BAF12A4E71CE` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK3182BAF13F109A06` FOREIGN KEY (`event_id`) REFERENCES `t_event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_enroll
-- ----------------------------

-- ----------------------------
-- Table structure for t_event
-- ----------------------------
DROP TABLE IF EXISTS `t_event`;
CREATE TABLE `t_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_step` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enroll_end_time` datetime DEFAULT NULL,
  `enroll_start_time` datetime DEFAULT NULL,
  `group_rule` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `steps` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_event
-- ----------------------------

-- ----------------------------
-- Table structure for t_exam
-- ----------------------------
DROP TABLE IF EXISTS `t_exam`;
CREATE TABLE `t_exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `canIn` varchar(255) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `face_group` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `step` int(11) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  `paper_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK94B27D2A3F109A06` (`event_id`),
  KEY `FK94B27D2A71B1DCC6` (`paper_id`),
  CONSTRAINT `FK94B27D2A3F109A06` FOREIGN KEY (`event_id`) REFERENCES `t_event` (`id`),
  CONSTRAINT `FK94B27D2A71B1DCC6` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_exam
-- ----------------------------

-- ----------------------------
-- Table structure for t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `funcName` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAE73B3631478A357` (`PID`),
  CONSTRAINT `FKAE73B3631478A357` FOREIGN KEY (`PID`) REFERENCES `t_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_function
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `operator` tinyblob,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `receiver_id` bigint(20) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK96BA9EFCB5C1112A` (`receiver_id`),
  KEY `FK96BA9EFC34FEB324` (`sender_id`),
  CONSTRAINT `FK96BA9EFC34FEB324` FOREIGN KEY (`sender_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK96BA9EFCB5C1112A` FOREIGN KEY (`receiver_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `modifyor_id` bigint(20) DEFAULT NULL,
  `publisher_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK94B64FDEBF7B2819` (`publisher_id`),
  KEY `FK94B64FDEF9E98AEB` (`type_id`),
  KEY `FK94B64FDEEE12CA58` (`modifyor_id`),
  CONSTRAINT `FK94B64FDEBF7B2819` FOREIGN KEY (`publisher_id`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `FK94B64FDEEE12CA58` FOREIGN KEY (`modifyor_id`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `FK94B64FDEF9E98AEB` FOREIGN KEY (`type_id`) REFERENCES `t_datacode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_news
-- ----------------------------

-- ----------------------------
-- Table structure for t_paper
-- ----------------------------
DROP TABLE IF EXISTS `t_paper`;
CREATE TABLE `t_paper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `data` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_auto_check` tinyint(1) DEFAULT NULL,
  `is_random` tinyint(1) DEFAULT NULL,
  `is_show_answer` int(11) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `total_score` double DEFAULT NULL,
  `view_order` int(11) DEFAULT NULL,
  `modifyor_id` bigint(20) DEFAULT NULL,
  `poster_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22DEDC1EE12CA58` (`modifyor_id`),
  KEY `FK22DEDC1A5F2BF28` (`poster_id`),
  CONSTRAINT `FK22DEDC1A5F2BF28` FOREIGN KEY (`poster_id`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `FK22DEDC1EE12CA58` FOREIGN KEY (`modifyor_id`) REFERENCES `t_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_paper
-- ----------------------------

-- ----------------------------
-- Table structure for t_paper_random
-- ----------------------------
DROP TABLE IF EXISTS `t_paper_random`;
CREATE TABLE `t_paper_random` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` varchar(255) DEFAULT NULL,
  `paper_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK99961D0171B1DCC6` (`paper_id`),
  CONSTRAINT `FK99961D0171B1DCC6` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_paper_random
-- ----------------------------

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `data` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `resolve` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `level` bigint(20) DEFAULT NULL,
  `modifyor_id` bigint(20) DEFAULT NULL,
  `questiondb_id` bigint(20) DEFAULT NULL,
  `poster_id` bigint(20) DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16A67B71BBF7242E` (`questiondb_id`),
  KEY `FK16A67B7132E4462F` (`level`),
  KEY `FK16A67B71EE12CA58` (`modifyor_id`),
  KEY `FK16A67B71A5F2BF28` (`poster_id`),
  KEY `FK16A67B712CF939E5` (`type`),
  CONSTRAINT `FK16A67B712CF939E5` FOREIGN KEY (`type`) REFERENCES `t_datacode` (`id`),
  CONSTRAINT `FK16A67B7132E4462F` FOREIGN KEY (`level`) REFERENCES `t_datacode` (`id`),
  CONSTRAINT `FK16A67B71A5F2BF28` FOREIGN KEY (`poster_id`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `FK16A67B71BBF7242E` FOREIGN KEY (`questiondb_id`) REFERENCES `t_question_db` (`id`),
  CONSTRAINT `FK16A67B71EE12CA58` FOREIGN KEY (`modifyor_id`) REFERENCES `t_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------

-- ----------------------------
-- Table structure for t_question_db
-- ----------------------------
DROP TABLE IF EXISTS `t_question_db`;
CREATE TABLE `t_question_db` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `modifyor_id` bigint(20) DEFAULT NULL,
  `poster_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD7B8700CEE12CA58` (`modifyor_id`),
  KEY `FKD7B8700CA5F2BF28` (`poster_id`),
  CONSTRAINT `FKD7B8700CA5F2BF28` FOREIGN KEY (`poster_id`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `FKD7B8700CEE12CA58` FOREIGN KEY (`modifyor_id`) REFERENCES `t_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question_db
-- ----------------------------

-- ----------------------------
-- Table structure for t_result
-- ----------------------------
DROP TABLE IF EXISTS `t_result`;
CREATE TABLE `t_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_pass` tinyint(1) DEFAULT NULL,
  `person_score` double DEFAULT NULL,
  `team_average_score` double DEFAULT NULL,
  `exam_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK473372682A4E71CE` (`user_id`),
  KEY `FK47337268E431214E` (`exam_id`),
  CONSTRAINT `FK473372682A4E71CE` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK47337268E431214E` FOREIGN KEY (`exam_id`) REFERENCES `t_exam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_result
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleName` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_role_function
-- ----------------------------
DROP TABLE IF EXISTS `t_role_function`;
CREATE TABLE `t_role_function` (
  `ROLE_ID` bigint(20) NOT NULL,
  `FUNCTION_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`FUNCTION_ID`,`ROLE_ID`),
  KEY `FKA521F0D68523ADEE` (`ROLE_ID`),
  KEY `FKA521F0D6A07240AE` (`FUNCTION_ID`),
  CONSTRAINT `FKA521F0D68523ADEE` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKA521F0D6A07240AE` FOREIGN KEY (`FUNCTION_ID`) REFERENCES `t_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_function
-- ----------------------------

-- ----------------------------
-- Table structure for t_school
-- ----------------------------
DROP TABLE IF EXISTS `t_school`;
CREATE TABLE `t_school` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `properties` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_school
-- ----------------------------

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` varchar(255) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `exam_id` bigint(20) DEFAULT NULL,
  `paper_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2591AC72A4E71CE` (`user_id`),
  KEY `FK2591AC771B1DCC6` (`paper_id`),
  KEY `FK2591AC7E431214E` (`exam_id`),
  CONSTRAINT `FK2591AC72A4E71CE` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK2591AC771B1DCC6` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`),
  CONSTRAINT `FK2591AC7E431214E` FOREIGN KEY (`exam_id`) REFERENCES `t_exam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_score
-- ----------------------------

-- ----------------------------
-- Table structure for t_team
-- ----------------------------
DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `leader_id` bigint(20) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK94B907683F109A06` (`event_id`),
  KEY `FK94B90768EBF20657` (`teacher_id`),
  KEY `FK94B90768776EA7D0` (`leader_id`),
  CONSTRAINT `FK94B907683F109A06` FOREIGN KEY (`event_id`) REFERENCES `t_event` (`id`),
  CONSTRAINT `FK94B90768776EA7D0` FOREIGN KEY (`leader_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK94B90768EBF20657` FOREIGN KEY (`teacher_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------

-- ----------------------------
-- Table structure for t_team_user
-- ----------------------------
DROP TABLE IF EXISTS `t_team_user`;
CREATE TABLE `t_team_user` (
  `USER_ID` bigint(20) NOT NULL,
  `TEAM_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`TEAM_ID`,`USER_ID`),
  KEY `FK1FADF6422A4E71CE` (`USER_ID`),
  KEY `FK1FADF642DD49108E` (`TEAM_ID`),
  CONSTRAINT `FK1FADF6422A4E71CE` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK1FADF642DD49108E` FOREIGN KEY (`TEAM_ID`) REFERENCES `t_team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_detail` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idcard` varchar(18) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `school_id` bigint(20) DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idcard` (`idcard`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `username` (`username`),
  KEY `FK94B9B0D6C00AB82E` (`school_id`),
  KEY `FK94B9B0D6E03D70C6` (`address_id`),
  KEY `FK94B9B0D62CF939E5` (`type`),
  CONSTRAINT `FK94B9B0D62CF939E5` FOREIGN KEY (`type`) REFERENCES `t_datacode` (`id`),
  CONSTRAINT `FK94B9B0D6C00AB82E` FOREIGN KEY (`school_id`) REFERENCES `t_school` (`id`),
  CONSTRAINT `FK94B9B0D6E03D70C6` FOREIGN KEY (`address_id`) REFERENCES `t_address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
