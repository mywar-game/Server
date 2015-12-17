package com.fantingame.game.gateway.server.manager;

import com.fantingame.game.msgbody.user.UserToken;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;



public class TokenManager {
	private static TokenManager mgr = new TokenManager();
	
	private Cache<String,UserToken> tokenMap = CacheBuilder.newBuilder().maximumSize(10240).build();
	
	private TokenManager(){}
	
	public static TokenManager getInstance(){
			return mgr;
	}
	
	public void setToken(String token, UserToken userToken){
		tokenMap.put(token, userToken);
	}
	
	public UserToken getToken(String token){
		return tokenMap.getIfPresent(token);
	}
	
	public void removeToken(String token){
		tokenMap.invalidate(token);
	}
}
