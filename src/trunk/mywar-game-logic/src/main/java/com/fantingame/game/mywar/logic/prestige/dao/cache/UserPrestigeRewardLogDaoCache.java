package com.fantingame.game.mywar.logic.prestige.dao.cache;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.prestige.dao.mysql.UserPrestigeRewardLogDaoMysql;
import com.fantingame.game.mywar.logic.prestige.model.UserPrestigeRewardLog;
import com.google.common.collect.Maps;

public class UserPrestigeRewardLogDaoCache extends BaseCacheMapDao<UserPrestigeRewardLog>{
    
	@Autowired
	private UserPrestigeRewardLogDaoMysql userPrestigeRewardLogDaoMysql;
	
	/**
	 * 判断奖励是否已经领取
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean isReward(String userId,int id){
		return super.getByKey(userId, id+"")==null?false:true;
	}
	
	/**
	 * 添加
	 * @param userPrestigeRewardLog
	 * @return
	 */
	public boolean addUserPrestigeRewardLog(UserPrestigeRewardLog userPrestigeRewardLog){
		if(userPrestigeRewardLogDaoMysql.addUserPrestigeRewardLog(userPrestigeRewardLog)){
			if(super.isExitKey(userPrestigeRewardLog.getUserId())){
				super.addMapValues(userPrestigeRewardLog.getUserId(), userPrestigeRewardLog.getId()+"", userPrestigeRewardLog);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 获取声望获取日志
	 * @param userId
	 * @return
	 */
	public List<UserPrestigeRewardLog> getUserPrestigeRewardLogList(String userId){
		return super.getMapList(userId);
	}
	
	@Override
	protected Map<String, UserPrestigeRewardLog> loadFromDb(String key) {
		Map<String, UserPrestigeRewardLog> map = Maps.newConcurrentMap();
		List<UserPrestigeRewardLog> list = userPrestigeRewardLogDaoMysql.getUserPrestigeRewardLogList(key);
		for(UserPrestigeRewardLog userPrestigeRewardLog:list){
			map.put(userPrestigeRewardLog.getId()+"",userPrestigeRewardLog);
		}
		return map;
	}
}
