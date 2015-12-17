package com.fantingame.game.mywar.logic.hero.log;

import com.fantingame.game.log.service.IGameLog;

public class HeroSkillLogTable implements IGameLog {

	@Override
	public String createTableSQL(String date) {
		String userHeroSkillLog = "CREATE TABLE IF NOT EXISTS `user_hero_skill_log_"+date+"` (`USER_HERO_SKILL_LOG_ID` bigint(64) NOT NULL auto_increment COMMENT '英雄日志ID',`USER_ID` varchar(32) NOT NULL COMMENT '玩家ID',`USER_HERO_SKILL_ID` varchar(32) NOT NULL COMMENT '玩家英雄ID',`SYSTEM_HERO_SKILL_ID` int(11) NOT NULL COMMENT '英雄常量编号',`TYPE` int(11) NOT NULL COMMENT '（1.获取 2.升级..）',`SMALL_TPYE` int(11) NOT NULL COMMENT '操作子类型',`LEVEL` int(11) NOT NULL COMMENT '等级',`EXP` int(11) NOT NULL,`POS` tinyint(4) NOT NULL COMMENT '所在位置',`EXP_NUM` int(11) NOT NULL default '0' COMMENT '增加的经验值',`USER_HERO_ID` varchar(32) default NULL COMMENT '所在英雄id',`OPERATION_TIME` datetime NOT NULL COMMENT '玩家操作时间',PRIMARY KEY  (`USER_HERO_SKILL_LOG_ID`),KEY `operation_time` USING BTREE (`OPERATION_TIME`),KEY `user_id` USING BTREE (`USER_ID`)) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=0;";
        return userHeroSkillLog;
	}

}
