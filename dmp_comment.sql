/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost
 Source Database       : dmp_comment

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : utf-8

 Date: 03/06/2019 10:58:05 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dmp_comment`
-- ----------------------------
DROP TABLE IF EXISTS `dmp_comment`;
CREATE TABLE `dmp_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `category` int(11) NOT NULL DEFAULT '0' COMMENT '0:咨询,1:回复',
  `status` int(11) DEFAULT '0' COMMENT '0:已发布,1:待审核,2:创建',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
