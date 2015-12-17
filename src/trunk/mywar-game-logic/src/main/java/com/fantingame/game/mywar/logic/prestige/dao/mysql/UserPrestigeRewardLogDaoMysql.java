package com.fantingame.game.mywar.logic.prestige.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.prestige.model.UserPrestigeRewardLog;

public class UserPrestigeRewardLogDaoMysql {
    @Autowired
	private Jdbc jdbcUser;
    /**
     * 查询用户领取声望等级日志
     * @param userId
     * @return
     */
    public List<UserPrestigeRewardLog> getUserPrestigeRewardLogList(String userId){
    	String sql = "select * from user_prestige_reward_log where user_id=?";
    	SqlParameter sqlParameter = new SqlParameter();
    	sqlParameter.setString(userId);
    	return jdbcUser.getList(sql, UserPrestigeRewardLog.class, sqlParameter);
    }
    /**
     * 添加声望日志
     * @param log
     * @return
     */
    public boolean addUserPrestigeRewardLog(UserPrestigeRewardLog log){
    	return jdbcUser.insert(log)>0;
    }
}
