package com.fantingame.game.gateway.server.manager;

import java.util.concurrent.TimeUnit;

import com.fantingame.game.msgbody.user.UserToken;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 离线token 管理
 * 
 * @author
 * 
 */
public class OfflineTokenManager {
	private static OfflineTokenManager mgr = new OfflineTokenManager();

	private Cache<String,UserToken> offLineTokenMap = CacheBuilder.newBuilder().expireAfterAccess(48, TimeUnit.HOURS).maximumSize(20480).build();
	private OfflineTokenManager() {}
	public static OfflineTokenManager getInstance() {
		return mgr;
	}
	public void setToken(String token, UserToken userToken) {
		offLineTokenMap.put(token, userToken);
	}
	public UserToken getToken(String token) {
		return offLineTokenMap.getIfPresent(token);
	}

	public void removeToken(String token) {
		offLineTokenMap.invalidate(token);
	}
}
