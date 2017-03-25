/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-25 13:56:21
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_datacode
-- ----------------------------
INSERT INTO `t_datacode` VALUES ('5', 'questionType', 'fa-align-justify', '试题类型', null);
INSERT INTO `t_datacode` VALUES ('6', 'singleChoice', 'fa-check-circle-o', '单选题', '5');
INSERT INTO `t_datacode` VALUES ('7', 'multipleChoice', 'fa-check-square-o', '多选题', '5');
INSERT INTO `t_datacode` VALUES ('8', 'judgement', 'fa-check', '判断题', '5');
INSERT INTO `t_datacode` VALUES ('9', 'essay', 'fa-pencil-square-o', '简述题', '5');
INSERT INTO `t_datacode` VALUES ('10', 'program', 'fa-code', '编程题', '5');
INSERT INTO `t_datacode` VALUES ('11', 'userType', 'fa-align-justify', '用户类型', null);
INSERT INTO `t_datacode` VALUES ('12', 'student', 'fa-user', '学生', '11');
INSERT INTO `t_datacode` VALUES ('13', 'teacher', 'fa-user', '教师', '11');
INSERT INTO `t_datacode` VALUES ('14', 'society', 'fa-user', '就职人员', '11');
INSERT INTO `t_datacode` VALUES ('15', 'questionLevel', 'fa-align-justify', '试题难度', null);
INSERT INTO `t_datacode` VALUES ('16', '0', 'fa-star-o', '简单', '15');
INSERT INTO `t_datacode` VALUES ('17', '1', 'fa-star-half-o', '普通', '15');
INSERT INTO `t_datacode` VALUES ('18', '2', 'fa-star', '困难', '15');
