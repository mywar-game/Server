/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.8
Source Server Version : 50095
Source Host           : 192.168.1.8:3306
Source Database       : mywar_game_user

Target Server Type    : MYSQL
Target Server Version : 50095
File Encoding         : 65001

Date: 2014-08-29 13:07:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `payment_log`
-- ----------------------------
DROP TABLE IF EXISTS `payment_log`;
CREATE TABLE `payment_log` (
  `payment_log_id` int(11) NOT NULL auto_increment COMMENT '唯1ID',
  `partner_id` varchar(10) NOT NULL default '0' COMMENT '合作商ID',
  `server_id` varchar(10) NOT NULL default '0' COMMENT '服务器ID',
  `partner_user_id` varchar(32) NOT NULL COMMENT '合作商用户ID',
  `user_id` char(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `channel` varchar(20) NOT NULL COMMENT '渠道',
  `order_id` varchar(50) NOT NULL COMMENT '订单ID',
  `amount` decimal(24,2) NOT NULL default '0.00' COMMENT '充值金额',
  `gold` int(11) NOT NULL COMMENT '金币',
  `user_ip` varchar(30) default NULL COMMENT '用户IP',
  `remark` varchar(50) default NULL COMMENT '备注',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY  (`payment_log_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment_log
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `user_id` char(32) NOT NULL COMMENT '用户编号',
  `ft_id` int(11) NOT NULL default '0',
  `name` varchar(20) NOT NULL,
  `level` int(11) NOT NULL,
  `exp` bigint(20) NOT NULL COMMENT '经验',
  `money` int(11) NOT NULL default '0' COMMENT '钱数，用人民币充值的那个游戏中的货币',
  `vip_level` int(10) NOT NULL default '0' COMMENT 'VIP等级',
  `reg_time` datetime NOT NULL COMMENT '注册时间',
  `updated_time` datetime NOT NULL COMMENT '数据最后更新时间',
  PRIMARY KEY  (`user_id`),
  KEY `user_id_index` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('56bd02c23fea462582249ee7c73e6b32', '4', '超哥', '1', '0', '0', '0', '2014-08-27 19:31:09', '2014-08-27 19:31:09');

-- ----------------------------
-- Table structure for `runtime_data`
-- ----------------------------
DROP TABLE IF EXISTS `runtime_data`;
CREATE TABLE `runtime_data` (
  `Id` int(11) NOT NULL auto_increment,
  `value_key` varchar(50) NOT NULL,
  `value` int(11) NOT NULL,
  `created_time` date NOT NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of runtime_data
-- ----------------------------
INSERT INTO `runtime_data` VALUES ('1', 'ft_id_s2', '4', '2014-08-27');

-- ----------------------------
-- Table structure for `user_mapper`
-- ----------------------------
DROP TABLE IF EXISTS `user_mapper`;
CREATE TABLE `user_mapper` (
  `user_mapper_id` int(11) NOT NULL auto_increment,
  `partner_id` varchar(10) NOT NULL default '',
  `server_id` varchar(10) NOT NULL default '',
  `user_id` char(32) NOT NULL,
  `partner_user_id` varchar(50) NOT NULL,
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  `qn` varchar(20) default '0',
  `imei` varchar(200) default NULL,
  `mac` varchar(100) default NULL,
  `idfa` varchar(100) default NULL,
  PRIMARY KEY  (`user_mapper_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_mapper
-- ----------------------------
INSERT INTO `user_mapper` VALUES ('3104', '1001', 'h1', '43913d0b7dbe432ca489075ffcf0ab7f', 'bmccccbb', '2014-07-26 02:11:26', '2014-07-26 02:11:26', '0', null, null, null);
INSERT INTO `user_mapper` VALUES ('3105', '2001', 's2', '56bd02c23fea462582249ee7c73e6b32', '33333295cc', '2014-08-11 14:36:54', '2014-08-11 14:36:54', '0', null, null, null);
INSERT INTO `user_mapper` VALUES ('3106', '2001', 's1', '146122b21eee4bc0bdf70470ce9eb0ee', 'bmcccc', '2014-08-16 00:31:52', '2014-08-16 00:31:52', '0', null, null, null);
