package com.fantingame.game.mywar.logic.tool.log;

import com.fantingame.game.log.service.IGameLog;

public class ToolLogTable implements IGameLog {

	@Override
	public String createTableSQL(String date) {
		String userTreaSureLog = "CREATE TABLE IF NOT EXISTS `user_treasure_log_"+date+"` (`USER_TREASURE_LOG_ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '玩家道具日志编号' ,`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '玩家编号' ,`TREASURE_ID`  int(11) NOT NULL COMMENT '道具编号' ,`TREASURE_TYPE`  int(11) NOT NULL COMMENT '道具类型' ,`CATEGORY`  int(2) NOT NULL COMMENT '类别（1获得 2使用）' ,`OPERATION`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型(任务掉落，战斗掉落，npc产出，商城购买)' ,`CHANGE_NUM`  int(11) NOT NULL COMMENT '改变的数量' ,`EXTINFO`  mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '扩展信息' ,`CREATE_TIME`  datetime NOT NULL COMMENT '创建时间' ,PRIMARY KEY (`USER_TREASURE_LOG_ID`),INDEX `create_time` (`CREATE_TIME`)  ,INDEX `user_id` (`USER_ID`)  )ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=0;";
        return userTreaSureLog;
	}
}
