package com.fantingame.game.mywar.logic.forces.log;

import com.fantingame.game.log.service.IGameLog;

public class BattleLogTable implements IGameLog {

	@Override
	public String createTableSQL(String date) {
		String battleReportLog = "CREATE TABLE IF NOT EXISTS `user_battle_report_"+date+"` (`USER_BATTLE_REPORT_ID`  int(10) NOT NULL AUTO_INCREMENT ,`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,`USER_LEVEL`  int(10) NOT NULL ,`TARGET_ID`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,`TYPE`  tinyint(4) NULL DEFAULT NULL COMMENT '1打关卡 2PK争霸赛 3塔赛' ,`FLAG`  int(50) NULL DEFAULT NULL COMMENT '1是赢了2是平局3失败' ,`CREAT_TIME`  datetime NOT NULL,PRIMARY KEY (`USER_BATTLE_REPORT_ID`),INDEX `creat_time` (`CREAT_TIME`)  ,INDEX `user_id` (`USER_ID`)  )ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=0;";
        return battleReportLog;
	}

}
