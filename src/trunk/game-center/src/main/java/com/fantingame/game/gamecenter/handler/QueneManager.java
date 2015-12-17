package com.fantingame.game.gamecenter.handler;

import java.util.Date;
import java.util.Map;

import com.fantingame.game.gamecenter.bo.UserQueneInfo;
import com.fantingame.game.gamecenter.util.IDGenerator;
import com.google.common.collect.Maps;

public class QueneManager {

	private static QueneManager ins;

	private Map<String, Map<String, UserQueneInfo>> queneMap;

	public static QueneManager instance() {
		synchronized (QueneManager.class) {
			if (ins == null) {
				ins = new QueneManager();
			}
		}

		return ins;
	}
	
	/**
	 * 获取排队信息
	 * 
	 * @param serverId
	 * @param queneToken
	 * @return
	 */
	public UserQueneInfo getUserQueneInfo(String serverId, String queneToken) {
		Date now = new Date();
		UserQueneInfo queneInfo;
	
		if (queneMap.containsKey(serverId)) {
			Map<String, UserQueneInfo> uuIdMap = queneMap.get(serverId);
			// 未登陆过
			if (queneToken == null || !uuIdMap.containsKey(queneToken)) {
				queneToken = IDGenerator.getID();
				queneInfo = createUserQueneInfo(queneToken, now, uuIdMap.size());
				uuIdMap.put(queneToken, queneInfo);
			
				return queneInfo;
			} 
		
			queneInfo = uuIdMap.get(queneToken);
			// 判断是否仍在排队中
			if (now.getTime() >= (queneInfo.getLoginTime().getTime() + queneInfo.getWaitingTime())) {					
				queneInfo.setLoginTime(now);
				queneInfo.setWaitingTime(0);
				queneInfo.setPerson(uuIdMap.size());
			} else {
				queneInfo.setWaitingTime((queneInfo.getLoginTime().getTime() + queneInfo.getWaitingTime()) - now.getTime());
			}
		
			return queneInfo;
		} 
	
		queneToken = IDGenerator.getID();
		Map<String, UserQueneInfo> uuIdMap = Maps.newConcurrentMap();
		queneInfo = createUserQueneInfo(queneToken, now, uuIdMap.size());
		uuIdMap.put(queneToken, queneInfo);
		queneMap.put(serverId, uuIdMap);
	
		return queneInfo;	
	}
	
	/**
	 * 移除过期的信息 
	 */
	public void checkQueneInfo() {
		if (queneMap.size() == 0)
			return;
		
		Date now = new Date();
		for (Map<String, UserQueneInfo> map : queneMap.values()) {
			if (map.size() == 0)
				continue;
			
			for (UserQueneInfo userQueneInfo : map.values()) {				
				if (userQueneInfo.getRemoveTime() != null && (now.getTime() > userQueneInfo.getRemoveTime().getTime()))
					map.remove(userQueneInfo.getQueneToken());
				
				// 等待时间到了，但是五秒后还没登陆，先移除
				if (userQueneInfo.getRemoveTime() == null 
						&& now.getTime() > (userQueneInfo.getLoginTime().getTime() + userQueneInfo.getWaitingTime() + 3000))
					map.remove(userQueneInfo.getQueneToken());
			}
		}
	}
	
	private UserQueneInfo createUserQueneInfo(String queneToken, Date loginTime, int queneSize) {
		UserQueneInfo queneInfo = new UserQueneInfo();
		queneInfo.setQueneToken(queneToken);
		queneInfo.setLoginTime(loginTime);
		queneInfo.setWaitingTime((queneSize / 50) * 1000);
		// queneInfo.setWaitingTime((queneSize / 1) * 10000);
		queneInfo.setPerson(queneSize);
		
		return queneInfo;
	}

	private QueneManager() {
		queneMap = Maps.newConcurrentMap();
	}

}
