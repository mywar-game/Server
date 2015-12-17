package com.fantingame.game.mywar.logic.user.log;

import com.fantingame.game.log.service.IGameLog;

public class UserActionLogTable implements IGameLog {

	@Override
	public String createTableSQL(String date) {
		String userActionLog = "CREATE TABLE IF NOT EXISTS `user_action_log_"+date+"` (`ID` int(11) NOT NULL auto_increment COMMENT '用户动作日志Id',`USER_ID` varchar(32) default NULL COMMENT '用户id',`USER_LEVEL` int(4) default NULL COMMENT '玩家等级',`IP` varchar(255) NOT NULL COMMENT '用户IP',`SOURCE` int(11) default NULL COMMENT '用户来源',`ACTION_ID` int(11) NOT NULL COMMENT '用户动作ID（具体请看a_action_constant）',`TIME` datetime NOT NULL COMMENT '动作发生时间',PRIMARY KEY  (`ID`)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='用户动作日志表';";
        return userActionLog;
	}

}
