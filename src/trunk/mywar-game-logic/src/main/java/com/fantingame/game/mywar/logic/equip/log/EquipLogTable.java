package com.fantingame.game.mywar.logic.equip.log;

import com.fantingame.game.log.service.IGameLog;

public class EquipLogTable implements IGameLog {

	@Override
	public String createTableSQL(String date) {
		String userEquipLog = "CREATE TABLE IF NOT EXISTS `user_equipment_log_" + date + "` ( `USER_EQUIPMENT_LOG_ID` int(11) NOT NULL auto_increment,`USER_ID` varchar(32) NOT NULL,`USER_EQUIPMENT_ID` varchar(32) NOT NULL,`EQUIPMENT_ID` int(11) NOT NULL,`USER_HERO_ID` varchar(32) NOT NULL COMMENT '佩戴虎将id',`EQUIPMENT_LEVEL` int(11) NOT NULL COMMENT '装备等级',`TYPE` int(11) NOT NULL COMMENT '类型( 1.强化2.进阶.3.获取 4.出售)',`OPERATION_TIME` datetime NOT NULL COMMENT '操作时间',PRIMARY KEY  (`USER_EQUIPMENT_LOG_ID`),KEY `operation_time` USING BTREE (`OPERATION_TIME`),KEY `user_id` USING BTREE (`USER_ID`)) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=0;";
        return userEquipLog;
	}
}
