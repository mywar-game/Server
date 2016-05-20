/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.8
Source Server Version : 50095
Source Host           : 192.168.1.8:3306
Source Database       : mywar_game_config

Target Server Type    : MYSQL
Target Server Version : 50095
File Encoding         : 65001

Date: 2014-08-29 13:08:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `system_config_data`
-- ----------------------------
DROP TABLE IF EXISTS `system_config_data`;
CREATE TABLE `system_config_data` (
  `config_data_id` int(10) NOT NULL auto_increment,
  `config_key` varchar(50) default NULL,
  `config_value` varchar(150) default NULL,
  PRIMARY KEY  (`config_data_id`),
  UNIQUE KEY `config_key` (`config_key`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_config_data
-- ----------------------------

-- ----------------------------
-- Table structure for `system_gold_set`
-- ----------------------------
DROP TABLE IF EXISTS `system_gold_set`;
CREATE TABLE `system_gold_set` (
  `system_gold_set_id` int(11) NOT NULL COMMENT '唯一ID',
  `money` decimal(12,2) NOT NULL,
  `gold` int(11) NOT NULL COMMENT '金币数量',
  `type` tinyint(4) NOT NULL COMMENT '类型 1 默认 2 活动',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `description` varchar(500) NOT NULL COMMENT '描述',
  PRIMARY KEY  (`system_gold_set_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='金币套餐';

-- ----------------------------
-- Records of system_gold_set
-- ----------------------------
INSERT INTO `system_gold_set` VALUES ('11', '5000.00', '57500', '0', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '50000元宝', '额外赠送7500元宝');
INSERT INTO `system_gold_set` VALUES ('10', '2000.00', '22700', '0', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '20000元宝', '额外赠送2700元宝');
INSERT INTO `system_gold_set` VALUES ('9', '1000.00', '11300', '0', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '10000元宝', '额外赠送1300元宝');
INSERT INTO `system_gold_set` VALUES ('8', '500.00', '5625', '1', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '5000元宝', '额外赠送625元宝');
INSERT INTO `system_gold_set` VALUES ('7', '200.00', '2240', '1', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '2000元宝', '额外赠送240元宝');
INSERT INTO `system_gold_set` VALUES ('6', '100.00', '1115', '1', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '1000元宝', '额外赠送115元宝');
INSERT INTO `system_gold_set` VALUES ('5', '50.00', '555', '1', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '500元宝', '额外赠送55元宝');
INSERT INTO `system_gold_set` VALUES ('4', '30.00', '330', '1', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '300元宝', '额外赠送30元宝');
INSERT INTO `system_gold_set` VALUES ('3', '20.00', '220', '0', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '200元宝', '额外赠送20元宝');
INSERT INTO `system_gold_set` VALUES ('2', '10.00', '110', '1', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '100元宝', '额外赠送10元宝');
INSERT INTO `system_gold_set` VALUES ('1', '5.00', '55', '1', '2013-05-07 00:00:00', '2024-05-07 00:00:00', '50元宝', '额外赠送5元宝');
