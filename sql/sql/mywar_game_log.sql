/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.8
Source Server Version : 50095
Source Host           : 192.168.1.8:3306
Source Database       : mywar_game_log

Target Server Type    : MYSQL
Target Server Version : 50095
File Encoding         : 65001

Date: 2014-08-29 13:07:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activity_draw_log`
-- ----------------------------
DROP TABLE IF EXISTS `activity_draw_log`;
CREATE TABLE `activity_draw_log` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` char(32) NOT NULL,
  `tool_type` int(11) NOT NULL,
  `tool_id` int(11) NOT NULL,
  `tool_num` int(11) NOT NULL,
  `out_id` int(11) NOT NULL,
  `times` int(1) NOT NULL COMMENT '抽奖次数',
  `create_time` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_draw_log
-- ----------------------------

-- ----------------------------
-- Table structure for `payment_log`
-- ----------------------------
DROP TABLE IF EXISTS `payment_log`;
CREATE TABLE `payment_log` (
  `PAYMENT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '唯1ID',
  `PARTNER_ID` varchar(10) NOT NULL default '0' COMMENT '合作商ID',
  `SERVER_ID` varchar(10) NOT NULL default '0' COMMENT '服务器ID',
  `PARTNER_USER_ID` varchar(32) NOT NULL COMMENT '合作商用户ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `USER_NAME` varchar(20) NOT NULL COMMENT '用户名',
  `USER_LEVEL` int(11) NOT NULL COMMENT '充值玩家级别',
  `CHANNEL` varchar(20) NOT NULL COMMENT '渠道',
  `ORDER_ID` varchar(50) NOT NULL COMMENT '订单ID',
  `AMOUNT` decimal(24,2) NOT NULL default '0.00' COMMENT '充值金额',
  `GOLD` int(11) NOT NULL COMMENT '金币',
  `USER_IP` varchar(255) default NULL COMMENT '用户IP',
  `REMARK` varchar(50) default NULL COMMENT '备注',
  `CREATED_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY  (`PAYMENT_LOG_ID`),
  KEY `user_id` USING BTREE (`USER_ID`),
  KEY `created_time` USING BTREE (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家id',
  `USER_NAME` varchar(64) NOT NULL COMMENT '用户名',
  `NAME` varchar(64) character set utf8 collate utf8_bin NOT NULL COMMENT '角色名',
  `SEX` int(2) NOT NULL COMMENT '性别(0男1女)',
  `LODO_ID` int(11) NOT NULL COMMENT 'lodoId',
  `REG_TIME` datetime NOT NULL COMMENT '注册时间',
  `MAIN_ROLE_ID` int(11) default NULL COMMENT '主角id',
  `REG_IP` varchar(128) default NULL COMMENT 'ip',
  `REG_MAC` varchar(128) default NULL COMMENT 'mac',
  `REG_IMEI` varchar(128) default NULL COMMENT 'imei',
  `REG_IDFA` varchar(128) default NULL COMMENT 'idfa',
  PRIMARY KEY  (`USER_ID`),
  KEY `reg_time` USING BTREE (`REG_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表（方便查询userId,因为管理后台跟游戏通讯时只传userId过去）';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('56bd02c23fea462582249ee7c73e6b32', '超哥', '超哥', '1', '4', '2014-08-27 19:31:09', '2', '', 'mac', 'imei', 'idfa');

-- ----------------------------
-- Table structure for `user_action_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log`;
CREATE TABLE `user_action_log` (
  `ID` int(11) NOT NULL auto_increment COMMENT '用户动作日志Id',
  `USER_ID` varchar(32) default NULL COMMENT '用户id',
  `USER_LEVEL` int(4) default NULL COMMENT '玩家等级',
  `IP` varchar(255) NOT NULL COMMENT '用户IP',
  `SOURCE` int(11) default NULL COMMENT '用户来源',
  `ACTION_ID` int(11) NOT NULL COMMENT '用户动作ID（具体请看a_action_constant）',
  `TIME` datetime NOT NULL COMMENT '动作发生时间',
  PRIMARY KEY  (`ID`),
  KEY `time` (`TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户动作日志表';

-- ----------------------------
-- Records of user_action_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140801`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140801`;
CREATE TABLE `user_action_log_20140801` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140801
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140802`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140802`;
CREATE TABLE `user_action_log_20140802` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140802
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140808`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140808`;
CREATE TABLE `user_action_log_20140808` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140808
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140809`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140809`;
CREATE TABLE `user_action_log_20140809` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140809
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140811`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140811`;
CREATE TABLE `user_action_log_20140811` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140811
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140812`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140812`;
CREATE TABLE `user_action_log_20140812` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140812
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140813`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140813`;
CREATE TABLE `user_action_log_20140813` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140813
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140814`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140814`;
CREATE TABLE `user_action_log_20140814` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140814
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140815`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140815`;
CREATE TABLE `user_action_log_20140815` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140815
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140816`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140816`;
CREATE TABLE `user_action_log_20140816` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140816
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140817`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140817`;
CREATE TABLE `user_action_log_20140817` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140817
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140818`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140818`;
CREATE TABLE `user_action_log_20140818` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140818
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140819`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140819`;
CREATE TABLE `user_action_log_20140819` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140819
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140820`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140820`;
CREATE TABLE `user_action_log_20140820` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140820
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140821`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140821`;
CREATE TABLE `user_action_log_20140821` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140821
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140822`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140822`;
CREATE TABLE `user_action_log_20140822` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140822
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140823`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140823`;
CREATE TABLE `user_action_log_20140823` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140823
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140825`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140825`;
CREATE TABLE `user_action_log_20140825` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140825
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140826`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140826`;
CREATE TABLE `user_action_log_20140826` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140826
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140827`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140827`;
CREATE TABLE `user_action_log_20140827` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140827
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_20140828`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_20140828`;
CREATE TABLE `user_action_log_20140828` (
  `ID` int(11) NOT NULL auto_increment COMMENT '??????Id',
  `USER_ID` varchar(32) default NULL COMMENT '??id',
  `USER_LEVEL` int(4) default NULL COMMENT '????',
  `IP` varchar(255) NOT NULL COMMENT '??IP',
  `SOURCE` int(11) default NULL COMMENT '????',
  `ACTION_ID` int(11) NOT NULL COMMENT '????ID?????a_action_constant?',
  `TIME` datetime NOT NULL COMMENT '??????',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';

-- ----------------------------
-- Records of user_action_log_20140828
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_bak`;
CREATE TABLE `user_action_log_bak` (
  `ID` int(11) NOT NULL auto_increment COMMENT '用户动作日志Id',
  `USER_ID` varchar(32) default NULL COMMENT '用户id',
  `USER_LEVEL` int(4) default NULL COMMENT '玩家等级',
  `IP` varchar(255) NOT NULL COMMENT '用户IP',
  `SOURCE` int(11) default NULL COMMENT '用户来源',
  `ACTION_ID` int(11) NOT NULL COMMENT '用户动作ID（具体请看a_action_constant）',
  `TIME` datetime NOT NULL COMMENT '动作发生时间',
  PRIMARY KEY  (`ID`),
  KEY `time` (`TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户动作日志表';

-- ----------------------------
-- Records of user_action_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log`;
CREATE TABLE `user_artifact_log` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '神器操作日志id',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户编号',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '用户神器id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '神器id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '当前属性值',
  `NUM` int(11) NOT NULL COMMENT '数量',
  `OP_TYPE` int(11) NOT NULL COMMENT '操作类型(1 获取 2普通锻造 3保存普通 4放弃普通锻造  5元宝锻造 6进阶 7被进阶  8出售)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '操作子类型(与tool的useType相同)',
  `CREATED_TIME` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140801`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140801`;
CREATE TABLE `user_artifact_log_20140801` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140801
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140802`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140802`;
CREATE TABLE `user_artifact_log_20140802` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140802
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140808`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140808`;
CREATE TABLE `user_artifact_log_20140808` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140808
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140809`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140809`;
CREATE TABLE `user_artifact_log_20140809` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140809
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140811`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140811`;
CREATE TABLE `user_artifact_log_20140811` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140811
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140812`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140812`;
CREATE TABLE `user_artifact_log_20140812` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140812
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140813`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140813`;
CREATE TABLE `user_artifact_log_20140813` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140813
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140814`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140814`;
CREATE TABLE `user_artifact_log_20140814` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140814
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140815`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140815`;
CREATE TABLE `user_artifact_log_20140815` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140815
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140816`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140816`;
CREATE TABLE `user_artifact_log_20140816` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140816
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140817`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140817`;
CREATE TABLE `user_artifact_log_20140817` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140817
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140818`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140818`;
CREATE TABLE `user_artifact_log_20140818` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140818
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140819`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140819`;
CREATE TABLE `user_artifact_log_20140819` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140819
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140820`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140820`;
CREATE TABLE `user_artifact_log_20140820` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140820
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140821`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140821`;
CREATE TABLE `user_artifact_log_20140821` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140821
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140822`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140822`;
CREATE TABLE `user_artifact_log_20140822` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140822
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140823`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140823`;
CREATE TABLE `user_artifact_log_20140823` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140823
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140825`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140825`;
CREATE TABLE `user_artifact_log_20140825` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140825
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140826`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140826`;
CREATE TABLE `user_artifact_log_20140826` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140826
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140827`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140827`;
CREATE TABLE `user_artifact_log_20140827` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140827
-- ----------------------------

-- ----------------------------
-- Table structure for `user_artifact_log_20140828`
-- ----------------------------
DROP TABLE IF EXISTS `user_artifact_log_20140828`;
CREATE TABLE `user_artifact_log_20140828` (
  `USER_ARTIFACT_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????id',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `USER_ARTIFACT_ID` varchar(32) NOT NULL COMMENT '????id',
  `ARTIFACT_ID` int(11) NOT NULL COMMENT '??id',
  `ARTIFACT_ATT_STR` varchar(512) NOT NULL COMMENT '?????',
  `NUM` int(11) NOT NULL COMMENT '??',
  `OP_TYPE` int(11) NOT NULL COMMENT '????(1 ??  2?? 3?? 4??? 5??)',
  `OP_SMALL_TYPE` int(11) NOT NULL COMMENT '?????(?tool?useType??)',
  `CREATED_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_ARTIFACT_LOG_ID`),
  KEY `userID` (`USER_ID`),
  KEY `createdTime` (`CREATED_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_artifact_log_20140828
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report`;
CREATE TABLE `user_battle_report` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1打关卡 2PK争霸赛 3塔赛',
  `FLAG` int(50) default NULL COMMENT '1是赢了2是平局3失败',
  `CREAT_TIME` varchar(50) default NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` USING BTREE (`CREAT_TIME`),
  KEY `user_id` USING BTREE (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140801`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140801`;
CREATE TABLE `user_battle_report_20140801` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140801
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140802`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140802`;
CREATE TABLE `user_battle_report_20140802` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140802
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140808`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140808`;
CREATE TABLE `user_battle_report_20140808` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140808
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140809`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140809`;
CREATE TABLE `user_battle_report_20140809` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140809
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140811`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140811`;
CREATE TABLE `user_battle_report_20140811` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140811
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140812`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140812`;
CREATE TABLE `user_battle_report_20140812` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140812
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140813`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140813`;
CREATE TABLE `user_battle_report_20140813` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140813
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140814`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140814`;
CREATE TABLE `user_battle_report_20140814` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140814
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140815`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140815`;
CREATE TABLE `user_battle_report_20140815` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140815
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140816`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140816`;
CREATE TABLE `user_battle_report_20140816` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140816
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140817`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140817`;
CREATE TABLE `user_battle_report_20140817` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140817
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140818`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140818`;
CREATE TABLE `user_battle_report_20140818` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140818
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140819`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140819`;
CREATE TABLE `user_battle_report_20140819` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140819
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140820`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140820`;
CREATE TABLE `user_battle_report_20140820` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140820
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140821`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140821`;
CREATE TABLE `user_battle_report_20140821` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140821
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140822`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140822`;
CREATE TABLE `user_battle_report_20140822` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140822
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140823`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140823`;
CREATE TABLE `user_battle_report_20140823` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140823
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140825`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140825`;
CREATE TABLE `user_battle_report_20140825` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140825
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140826`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140826`;
CREATE TABLE `user_battle_report_20140826` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140826
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140827`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140827`;
CREATE TABLE `user_battle_report_20140827` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140827
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_20140828`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_20140828`;
CREATE TABLE `user_battle_report_20140828` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1??? 2PK??? 3??',
  `FLAG` int(50) default NULL COMMENT '1???2???3??',
  `CREAT_TIME` datetime NOT NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`),
  KEY `creat_time` (`CREAT_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_20140828
-- ----------------------------

-- ----------------------------
-- Table structure for `user_battle_report_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_battle_report_bak`;
CREATE TABLE `user_battle_report_bak` (
  `USER_BATTLE_REPORT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_LEVEL` int(10) NOT NULL,
  `TARGET_ID` varchar(50) default NULL,
  `TYPE` tinyint(4) default NULL COMMENT '1打关卡 2PK争霸赛 3塔赛',
  `FLAG` int(50) default NULL COMMENT '1是赢了 否则失败了',
  `CREAT_TIME` varchar(50) default NULL,
  PRIMARY KEY  (`USER_BATTLE_REPORT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_battle_report_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log`;
CREATE TABLE `user_equipment_log` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '佩戴虎将id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '装备等级',
  `TYPE` int(11) NOT NULL COMMENT '类型( 1.强化2.进阶.3.获取 4.出售)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` USING BTREE (`OPERATION_TIME`),
  KEY `user_id` USING BTREE (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140801`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140801`;
CREATE TABLE `user_equipment_log_20140801` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140801
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140802`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140802`;
CREATE TABLE `user_equipment_log_20140802` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140802
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140808`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140808`;
CREATE TABLE `user_equipment_log_20140808` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140808
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140809`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140809`;
CREATE TABLE `user_equipment_log_20140809` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140809
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140811`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140811`;
CREATE TABLE `user_equipment_log_20140811` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140811
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140812`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140812`;
CREATE TABLE `user_equipment_log_20140812` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140812
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140813`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140813`;
CREATE TABLE `user_equipment_log_20140813` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140813
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140814`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140814`;
CREATE TABLE `user_equipment_log_20140814` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140814
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140815`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140815`;
CREATE TABLE `user_equipment_log_20140815` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140815
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140816`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140816`;
CREATE TABLE `user_equipment_log_20140816` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140816
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140817`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140817`;
CREATE TABLE `user_equipment_log_20140817` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140817
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140818`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140818`;
CREATE TABLE `user_equipment_log_20140818` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140818
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140819`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140819`;
CREATE TABLE `user_equipment_log_20140819` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140819
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140820`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140820`;
CREATE TABLE `user_equipment_log_20140820` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140820
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140821`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140821`;
CREATE TABLE `user_equipment_log_20140821` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140821
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140822`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140822`;
CREATE TABLE `user_equipment_log_20140822` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140822
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140823`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140823`;
CREATE TABLE `user_equipment_log_20140823` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140823
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140825`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140825`;
CREATE TABLE `user_equipment_log_20140825` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140825
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140826`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140826`;
CREATE TABLE `user_equipment_log_20140826` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140826
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140827`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140827`;
CREATE TABLE `user_equipment_log_20140827` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140827
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_20140828`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_20140828`;
CREATE TABLE `user_equipment_log_20140828` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '????',
  `TYPE` int(11) NOT NULL COMMENT '??( 1.??2.??.3.?? 4.??)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_20140828
-- ----------------------------

-- ----------------------------
-- Table structure for `user_equipment_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_equipment_log_bak`;
CREATE TABLE `user_equipment_log_bak` (
  `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,
  `USER_ID` varchar(32) NOT NULL,
  `USER_EQUIPMENT_ID` varchar(32) NOT NULL,
  `EQUIPMENT_ID` int(11) NOT NULL,
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '佩戴虎将id',
  `EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '装备等级',
  `TYPE` int(11) NOT NULL COMMENT '类型( 1.强化2.进阶.3.获取 4.出售)',
  `OPERATION_TIME` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_equipment_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_gold_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_gold_log`;
CREATE TABLE `user_gold_log` (
  `LOG_ID` int(11) NOT NULL auto_increment COMMENT '日志编号',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `USER_LEVEL` int(11) NOT NULL COMMENT '玩家等级',
  `CATEGORY` int(2) NOT NULL COMMENT '类别(1获得 2使用 )',
  `TYPE` int(2) NOT NULL COMMENT '类型',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '元宝改变数量',
  `TIME` datetime NOT NULL COMMENT '消费时间',
  PRIMARY KEY  (`LOG_ID`),
  KEY `USER_ID` USING BTREE (`USER_ID`),
  KEY `TYPE` USING BTREE (`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家金币日志表';

-- ----------------------------
-- Records of user_gold_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_gold_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_gold_log_bak`;
CREATE TABLE `user_gold_log_bak` (
  `LOG_ID` int(11) NOT NULL auto_increment COMMENT '日志编号',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `USER_LEVEL` int(11) NOT NULL COMMENT '玩家等级',
  `CATEGORY` int(2) NOT NULL COMMENT '类别(1获得 2使用 )',
  `TYPE` int(2) NOT NULL COMMENT '类型',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '元宝改变数量',
  `TIME` datetime NOT NULL COMMENT '消费时间',
  PRIMARY KEY  (`LOG_ID`),
  KEY `USER_ID` USING BTREE (`USER_ID`),
  KEY `TYPE` USING BTREE (`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家金币日志表';

-- ----------------------------
-- Records of user_gold_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log`;
CREATE TABLE `user_hero_log` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '英雄日志ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '玩家英雄ID',
  `HERO_ID` int(11) NOT NULL COMMENT '英雄常量编号',
  `TYPE` int(11) NOT NULL COMMENT '（1.获取 2.吞噬 3.被吞噬 4.进阶  5.出售）',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '玩家目前经验值',
  `LEVEL` int(11) NOT NULL COMMENT '等级',
  `POS` tinyint(4) NOT NULL COMMENT '英雄布阵',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '增加的经验值',
  `OPERATION_TIME` datetime NOT NULL COMMENT '玩家操作时间',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '英雄姓名',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` USING BTREE (`OPERATION_TIME`),
  KEY `user_id` USING BTREE (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140801`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140801`;
CREATE TABLE `user_hero_log_20140801` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140801
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140802`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140802`;
CREATE TABLE `user_hero_log_20140802` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140802
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140808`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140808`;
CREATE TABLE `user_hero_log_20140808` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140808
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140809`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140809`;
CREATE TABLE `user_hero_log_20140809` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140809
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140811`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140811`;
CREATE TABLE `user_hero_log_20140811` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140811
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140812`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140812`;
CREATE TABLE `user_hero_log_20140812` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140812
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140813`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140813`;
CREATE TABLE `user_hero_log_20140813` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140813
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140814`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140814`;
CREATE TABLE `user_hero_log_20140814` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140814
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140815`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140815`;
CREATE TABLE `user_hero_log_20140815` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140815
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140816`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140816`;
CREATE TABLE `user_hero_log_20140816` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140816
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140817`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140817`;
CREATE TABLE `user_hero_log_20140817` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140817
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140818`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140818`;
CREATE TABLE `user_hero_log_20140818` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140818
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140819`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140819`;
CREATE TABLE `user_hero_log_20140819` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140819
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140820`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140820`;
CREATE TABLE `user_hero_log_20140820` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140820
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140821`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140821`;
CREATE TABLE `user_hero_log_20140821` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140821
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140822`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140822`;
CREATE TABLE `user_hero_log_20140822` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140822
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140823`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140823`;
CREATE TABLE `user_hero_log_20140823` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140823
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140825`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140825`;
CREATE TABLE `user_hero_log_20140825` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140825
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140826`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140826`;
CREATE TABLE `user_hero_log_20140826` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140826
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140827`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140827`;
CREATE TABLE `user_hero_log_20140827` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140827
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_20140828`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_20140828`;
CREATE TABLE `user_hero_log_20140828` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '????ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '??ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '????ID',
  `HERO_ID` int(11) NOT NULL COMMENT '??????',
  `TYPE` int(11) NOT NULL COMMENT '?1.?? 2.?? 3.??? 4.??  5.???',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '???????',
  `LEVEL` int(11) NOT NULL COMMENT '??',
  `POS` tinyint(4) NOT NULL COMMENT '????',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '??????',
  `OPERATION_TIME` datetime NOT NULL COMMENT '??????',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_HERO_LOG_ID`),
  KEY `operation_time` (`OPERATION_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_20140828
-- ----------------------------

-- ----------------------------
-- Table structure for `user_hero_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_hero_log_bak`;
CREATE TABLE `user_hero_log_bak` (
  `USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '英雄日志ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家ID',
  `USER_HERO_ID` varchar(32) NOT NULL COMMENT '玩家英雄ID',
  `HERO_ID` int(11) NOT NULL COMMENT '英雄常量编号',
  `TYPE` int(11) NOT NULL COMMENT '（1.获取 2.吞噬 3.被吞噬 4.进阶  5.出售）',
  `EXP` bigint(20) NOT NULL default '0' COMMENT '玩家目前经验值',
  `LEVEL` int(11) NOT NULL COMMENT '等级',
  `POS` tinyint(4) NOT NULL COMMENT '英雄布阵',
  `EXP_NUM` int(11) NOT NULL default '0' COMMENT '增加的经验值',
  `OPERATION_TIME` datetime NOT NULL COMMENT '玩家操作时间',
  `HERO_NAME` varchar(21) NOT NULL COMMENT '英雄姓名',
  PRIMARY KEY  (`USER_HERO_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_hero_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_levelup_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_levelup_log`;
CREATE TABLE `user_levelup_log` (
  `ID` int(11) NOT NULL auto_increment COMMENT '玩家升级日志ID',
  `TIME` datetime NOT NULL COMMENT '记录时间',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `LEVEL` int(11) NOT NULL COMMENT '玩家等级',
  PRIMARY KEY  (`ID`),
  KEY `USER_ID` USING BTREE (`USER_ID`),
  KEY `LEVEL` USING BTREE (`LEVEL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家升级日志表';

-- ----------------------------
-- Records of user_levelup_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log` (
  `ID` int(11) NOT NULL auto_increment COMMENT '用户登入日志ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `LEVEL` int(11) NOT NULL COMMENT '玩家等级',
  `IP` varchar(255) NOT NULL COMMENT '用户IP',
  `LOGIN_TIME` datetime NOT NULL COMMENT '用户登入时间',
  PRIMARY KEY  (`ID`),
  KEY `USER_ID` USING BTREE (`USER_ID`),
  KEY `IP` USING BTREE (`IP`),
  KEY `login_time` USING BTREE (`LOGIN_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登入日志表';

-- ----------------------------
-- Records of user_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_login_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log_bak`;
CREATE TABLE `user_login_log_bak` (
  `ID` int(11) NOT NULL auto_increment COMMENT '用户登入日志ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `LEVEL` int(11) NOT NULL COMMENT '玩家等级',
  `IP` varchar(255) NOT NULL COMMENT '用户IP',
  `LOGIN_TIME` datetime NOT NULL COMMENT '用户登入时间',
  PRIMARY KEY  (`ID`),
  KEY `USER_ID` USING BTREE (`USER_ID`),
  KEY `IP` USING BTREE (`IP`),
  KEY `login_time` USING BTREE (`LOGIN_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登入日志表';

-- ----------------------------
-- Records of user_login_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_logout_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_logout_log`;
CREATE TABLE `user_logout_log` (
  `ID` int(11) NOT NULL auto_increment COMMENT '用户登出日志ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `IP` varchar(256) NOT NULL COMMENT '用户IP',
  `LOGOUT_TIME` datetime NOT NULL COMMENT '用户登出时间',
  `LIVE_MINUTES` int(11) default NULL COMMENT '在线分钟数',
  PRIMARY KEY  (`ID`),
  KEY `USER_ID` USING BTREE (`USER_ID`),
  KEY `logout_time` USING BTREE (`LOGOUT_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登出日志表';

-- ----------------------------
-- Records of user_logout_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_logout_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_logout_log_bak`;
CREATE TABLE `user_logout_log_bak` (
  `ID` int(11) NOT NULL auto_increment COMMENT '用户登出日志ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `IP` varchar(256) NOT NULL COMMENT '用户IP',
  `LOGOUT_TIME` datetime NOT NULL COMMENT '用户登出时间',
  `LIVE_MINUTES` int(11) default NULL COMMENT '在线分钟数',
  PRIMARY KEY  (`ID`),
  KEY `USER_ID` USING BTREE (`USER_ID`),
  KEY `logout_time` USING BTREE (`LOGOUT_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登出日志表';

-- ----------------------------
-- Records of user_logout_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_mall_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_mall_log`;
CREATE TABLE `user_mall_log` (
  `ID` int(11) NOT NULL auto_increment COMMENT '商城购买道具日志表主键',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `USER_LEVEL` int(11) NOT NULL COMMENT '玩家等级',
  `TREASURE_ID` int(11) NOT NULL COMMENT '购买的道具编号',
  `BUY_NUM` int(11) NOT NULL COMMENT '购买数量',
  `COST` int(11) NOT NULL COMMENT '花费的元宝数量',
  `COSTCOPPER` int(11) NOT NULL default '0' COMMENT '花费的银币数量',
  `TIME` datetime NOT NULL COMMENT '记录时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3660 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_mall_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_mall_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_mall_log_bak`;
CREATE TABLE `user_mall_log_bak` (
  `ID` int(11) NOT NULL auto_increment COMMENT '商城购买道具日志表主键',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `USER_LEVEL` int(11) NOT NULL COMMENT '玩家等级',
  `TREASURE_ID` int(11) NOT NULL COMMENT '购买的道具编号',
  `BUY_NUM` int(11) NOT NULL COMMENT '购买数量',
  `COST` int(11) NOT NULL COMMENT '花费的元宝数量',
  `COSTCOPPER` int(11) NOT NULL default '0' COMMENT '花费的银币数量',
  `TIME` datetime NOT NULL COMMENT '记录时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3614 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_mall_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_online_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_online_log`;
CREATE TABLE `user_online_log` (
  `ID` int(11) NOT NULL auto_increment COMMENT '在线玩家数日志ID',
  `TIME` datetime NOT NULL COMMENT '记录时间',
  `ONLINE_AMOUNT` int(11) default NULL COMMENT '在线玩家数',
  `USER_STR` longtext NOT NULL COMMENT '在线玩家编号字符串',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线玩家数日志表';

-- ----------------------------
-- Records of user_online_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_reg_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_reg_log`;
CREATE TABLE `user_reg_log` (
  `USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `USER_NAME` varchar(64) default NULL COMMENT '平台游戏id',
  `CHANNEL` varchar(128) default NULL COMMENT '渠道(大渠道号)',
  `SMALL_CHANNEL` varchar(128) default NULL COMMENT '小渠道号',
  `REG_TIME` datetime NOT NULL COMMENT '注册时间',
  `REG_IP` varchar(128) default NULL COMMENT 'ip',
  `REG_MAC` varchar(128) default NULL COMMENT 'mac',
  `REG_IMEI` varchar(128) default NULL COMMENT 'imei',
  `REG_IDFA` varchar(128) default NULL COMMENT 'idfa',
  `REG_MOBILE` varchar(128) default NULL COMMENT '机型',
  PRIMARY KEY  (`USER_ID`),
  KEY `reg_time` USING BTREE (`REG_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户注册表';

-- ----------------------------
-- Records of user_reg_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log`;
CREATE TABLE `user_resource_log` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '玩家资源日志',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `OPERATION` varchar(255) NOT NULL COMMENT '操作类型（升级，加速）',
  `MONEY` int(11) NOT NULL COMMENT '金钱',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` USING BTREE (`CREATE_TIME`),
  KEY `user_id` USING BTREE (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140801`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140801`;
CREATE TABLE `user_resource_log_20140801` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140801
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140802`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140802`;
CREATE TABLE `user_resource_log_20140802` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140802
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140808`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140808`;
CREATE TABLE `user_resource_log_20140808` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140808
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140809`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140809`;
CREATE TABLE `user_resource_log_20140809` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140809
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140811`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140811`;
CREATE TABLE `user_resource_log_20140811` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140811
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140812`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140812`;
CREATE TABLE `user_resource_log_20140812` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140812
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140813`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140813`;
CREATE TABLE `user_resource_log_20140813` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140813
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140814`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140814`;
CREATE TABLE `user_resource_log_20140814` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140814
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140815`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140815`;
CREATE TABLE `user_resource_log_20140815` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140815
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140816`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140816`;
CREATE TABLE `user_resource_log_20140816` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140816
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140817`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140817`;
CREATE TABLE `user_resource_log_20140817` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140817
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140818`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140818`;
CREATE TABLE `user_resource_log_20140818` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140818
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140819`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140819`;
CREATE TABLE `user_resource_log_20140819` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140819
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140820`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140820`;
CREATE TABLE `user_resource_log_20140820` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140820
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140821`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140821`;
CREATE TABLE `user_resource_log_20140821` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140821
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140822`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140822`;
CREATE TABLE `user_resource_log_20140822` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140822
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140823`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140823`;
CREATE TABLE `user_resource_log_20140823` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140823
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140825`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140825`;
CREATE TABLE `user_resource_log_20140825` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140825
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140826`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140826`;
CREATE TABLE `user_resource_log_20140826` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140826
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140827`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140827`;
CREATE TABLE `user_resource_log_20140827` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140827
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_20140828`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_20140828`;
CREATE TABLE `user_resource_log_20140828` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '??????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `OPERATION` varchar(255) NOT NULL COMMENT '???????????',
  `MONEY` int(11) NOT NULL COMMENT '??',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_20140828
-- ----------------------------

-- ----------------------------
-- Table structure for `user_resource_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_resource_log_bak`;
CREATE TABLE `user_resource_log_bak` (
  `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '玩家资源日志',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `OPERATION` varchar(255) NOT NULL COMMENT '操作类型（升级，加速）',
  `MONEY` int(11) NOT NULL COMMENT '金钱',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY  (`USER_RESOURCE_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_resource_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log`;
CREATE TABLE `user_treasure_log` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '玩家道具日志编号',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `TREASURE_ID` int(11) NOT NULL COMMENT '道具编号',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '道具类型',
  `CATEGORY` int(2) NOT NULL COMMENT '类别（1获得 2使用）',
  `OPERATION` varchar(30) NOT NULL COMMENT '操作类型(任务掉落，战斗掉落，npc产出，商城购买)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '改变的数量',
  `EXTINFO` mediumtext COMMENT '扩展信息',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` USING BTREE (`CREATE_TIME`),
  KEY `user_id` USING BTREE (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140801`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140801`;
CREATE TABLE `user_treasure_log_20140801` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140801
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140802`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140802`;
CREATE TABLE `user_treasure_log_20140802` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140802
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140808`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140808`;
CREATE TABLE `user_treasure_log_20140808` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140808
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140809`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140809`;
CREATE TABLE `user_treasure_log_20140809` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140809
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140811`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140811`;
CREATE TABLE `user_treasure_log_20140811` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140811
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140812`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140812`;
CREATE TABLE `user_treasure_log_20140812` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140812
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140813`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140813`;
CREATE TABLE `user_treasure_log_20140813` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140813
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140814`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140814`;
CREATE TABLE `user_treasure_log_20140814` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140814
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140815`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140815`;
CREATE TABLE `user_treasure_log_20140815` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140815
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140816`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140816`;
CREATE TABLE `user_treasure_log_20140816` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140816
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140817`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140817`;
CREATE TABLE `user_treasure_log_20140817` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140817
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140818`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140818`;
CREATE TABLE `user_treasure_log_20140818` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140818
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140819`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140819`;
CREATE TABLE `user_treasure_log_20140819` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140819
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140820`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140820`;
CREATE TABLE `user_treasure_log_20140820` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140820
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140821`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140821`;
CREATE TABLE `user_treasure_log_20140821` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140821
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140822`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140822`;
CREATE TABLE `user_treasure_log_20140822` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140822
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140823`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140823`;
CREATE TABLE `user_treasure_log_20140823` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140823
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140825`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140825`;
CREATE TABLE `user_treasure_log_20140825` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140825
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140826`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140826`;
CREATE TABLE `user_treasure_log_20140826` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140826
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140827`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140827`;
CREATE TABLE `user_treasure_log_20140827` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140827
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_20140828`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_20140828`;
CREATE TABLE `user_treasure_log_20140828` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '????????',
  `USER_ID` varchar(32) NOT NULL COMMENT '????',
  `TREASURE_ID` int(11) NOT NULL COMMENT '????',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '????',
  `CATEGORY` int(2) NOT NULL COMMENT '???1?? 2???',
  `OPERATION` varchar(30) NOT NULL COMMENT '????(??????????npc???????)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '?????',
  `EXTINFO` mediumtext COMMENT '????',
  `CREATE_TIME` datetime NOT NULL COMMENT '????',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` (`CREATE_TIME`),
  KEY `user_id` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_20140828
-- ----------------------------

-- ----------------------------
-- Table structure for `user_treasure_log_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_treasure_log_bak`;
CREATE TABLE `user_treasure_log_bak` (
  `USER_TREASURE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '玩家道具日志编号',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `TREASURE_ID` int(11) NOT NULL COMMENT '道具编号',
  `TREASURE_TYPE` int(11) NOT NULL COMMENT '道具类型',
  `CATEGORY` int(2) NOT NULL COMMENT '类别（1获得 2使用）',
  `OPERATION` varchar(30) NOT NULL COMMENT '操作类型(任务掉落，战斗掉落，npc产出，商城购买)',
  `CHANGE_NUM` int(11) NOT NULL COMMENT '改变的数量',
  `EXTINFO` mediumtext COMMENT '扩展信息',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY  (`USER_TREASURE_LOG_ID`),
  KEY `create_time` USING BTREE (`CREATE_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_treasure_log_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `user_vip_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_vip_log`;
CREATE TABLE `user_vip_log` (
  `ID` int(11) NOT NULL auto_increment COMMENT '玩家vip日志表主键',
  `USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',
  `VIP_LEVEL` int(11) NOT NULL COMMENT 'vip等级',
  `TIME` datetime NOT NULL COMMENT '记录时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_vip_log
-- ----------------------------
