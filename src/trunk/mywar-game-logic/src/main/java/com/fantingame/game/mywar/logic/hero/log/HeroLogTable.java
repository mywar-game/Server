package com.fantingame.game.mywar.logic.hero.log;

import com.fantingame.game.log.service.IGameLog;

public class HeroLogTable implements IGameLog {

	@Override
	public String createTableSQL(String date) {
		String userHeroLog = "CREATE TABLE IF NOT EXISTS `user_hero_log_"
				+ date
				+ "` (`USER_HERO_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '英雄日志ID',`USER_ID` varchar(32) NOT NULL COMMENT '玩家ID',`USER_HERO_ID` varchar(32) NOT NULL COMMENT '玩家英雄ID',`HERO_ID` int(11) NOT NULL COMMENT '英雄常量编号',`TYPE` int(11) NOT NULL COMMENT '（1.获取 2.吞噬 3.被吞噬 4.进阶  5.出售）',`SMALL_TPYE` int(11) NOT NULL COMMENT '操作子类型',`EXP` bigint(20) NOT NULL default '0' COMMENT '玩家目前经验值',`LEVEL` int(11) NOT NULL COMMENT '等级',`POS` tinyint(4) NOT NULL COMMENT '英雄布阵',`EXP_NUM` int(11) NOT NULL default '0' COMMENT '增加的经验值',`OPERATION_TIME` datetime NOT NULL COMMENT '玩家操作时间',`HERO_NAME` varchar(21) NOT NULL COMMENT '英雄姓名',PRIMARY KEY  (`USER_HERO_LOG_ID`),KEY `operation_time` USING BTREE (`OPERATION_TIME`),KEY `user_id` USING BTREE (`USER_ID`)) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=0;";
        return userHeroLog;
	}

}
