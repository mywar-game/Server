package com.fantingame.game.mywar.logic.gemstone.log;

import com.fantingame.game.log.service.IGameLog;

public class GemstoneLogTable implements IGameLog {

	@Override
	public String createTableSQL(String date) {
		String userGemstoneLog = "CREATE TABLE IF NOT EXISTS `user_gemstone_log_" + date + "` ( `USER_GEMSTONE_LOG_ID` int(11) NOT NULL auto_increment,`USER_ID` varchar(32) NOT NULL,`USER_GEMSTONE_ID` varchar(32) NOT NULL,`GEMSTONE_ID` int(11) NOT NULL,`USER_EQUIP_ID` varchar(32) NOT NULL COMMENT '镶嵌的装备id',`GEMSTONE_LEVEL` int(11) NOT NULL COMMENT '宝石等级',`TYPE` int(11) NOT NULL COMMENT '类型( 1.合成 2.升级. 3分解)',`OPERATION_TIME` datetime NOT NULL COMMENT '操作时间',PRIMARY KEY  (`USER_GEMSTONE_LOG_ID`),KEY `operation_time` USING BTREE (`OPERATION_TIME`),KEY `user_id` USING BTREE (`USER_ID`)) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=0;";
        return userGemstoneLog;
	}

}
