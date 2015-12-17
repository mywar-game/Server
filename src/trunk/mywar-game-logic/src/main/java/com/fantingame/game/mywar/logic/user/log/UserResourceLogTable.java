package com.fantingame.game.mywar.logic.user.log;

import com.fantingame.game.log.service.IGameLog;

public class UserResourceLogTable implements IGameLog {

	@Override
	public String createTableSQL(String date) {
		String resourceLog = "CREATE TABLE IF NOT EXISTS `user_resource_log_"+date+"` ( `USER_RESOURCE_LOG_ID` int(11) NOT NULL auto_increment COMMENT '玩家资源日志',`USER_ID` varchar(32) NOT NULL COMMENT '玩家编号',`SOURCE_TYPE` int(11) NOT NULL COMMENT '资源类型(2 金币,3 经验 6体力  7活力)',`OPERATION` varchar(255) NOT NULL COMMENT '操作类型（升级，加速）',`CHANGE_NUM` int(11) NOT NULL COMMENT '改变数量(负数为减 正数为加)',`NOW_NUM` int(11) NOT NULL COMMENT '现有数量',`CREATE_TIME` datetime NOT NULL COMMENT '创建时间',PRIMARY KEY  (`USER_RESOURCE_LOG_ID`),KEY `create_time` USING BTREE (`CREATE_TIME`),KEY `user_id` USING BTREE (`USER_ID`)) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=0;";
        return resourceLog;
	}

}
