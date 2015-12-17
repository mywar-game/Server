package com.fantingame.game.cuser.dao.cache;


import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;





import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.cuser.dao.UserMapperDao;
import com.fantingame.game.cuser.dao.mysql.UserMapperDaoMysqlImpl;
import com.fantingame.game.cuser.model.UserMapper;
import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


public class UserMapperDaoCacheImpl implements UserMapperDao{
	private UserMapperDaoMysqlImpl userMapperDaoMysqlImpl; 
    //用户id与UserMapper的映射map
	private LoadingCache<String,Optional<UserMapper>> userIdMapCache = CacheBuilder.newBuilder().expireAfterAccess(24, TimeUnit.HOURS).maximumSize(8192).build(new CacheLoader<String, Optional<UserMapper>>() {
		@Override
		public Optional<UserMapper> load(String key) throws Exception {
			UserMapper userMapper = userMapperDaoMysqlImpl.get(key);
			return Optional.fromNullable(userMapper);
		}
	});
	//partnerId+"-"+partnerUserId+"-"+serverId 对应的userMapper记录
//	private Cache<String, UserMapper> pspMapCache = CacheBuilder.newBuilder().expireAfterAccess(24, TimeUnit.HOURS).build();
	private Cache<String,Optional<UserMapper>> pspMapCache = CacheBuilder.newBuilder().expireAfterAccess(24, TimeUnit.HOURS).maximumSize(8192).build();
//			new CacheLoader<String, Optional<UserMapper>>() {
//		@Override
//		public Optional<UserMapper> load(String key) throws Exception {
//			String[] array = splitKey(key);
//			UserMapper userMapper = userMapperDaoMysqlImpl.getByPartnerUserId(array[1], array[0], array[2]);
//			return Optional.fromNullable(userMapper);
//		}
//	});
	@Override
	public boolean save(UserMapper userMapper) {
		if(userMapperDaoMysqlImpl.save(userMapper)){
			initCache(userMapper);
		    return true;
		}
		return false;
	}
	private String generatorKey(String partnerId,String partnerUserId,
			 String serverId){
		return partnerId+"-"+partnerUserId+"-"+serverId;
	}
	/**
	 * 初始化缓存
	 * @param userMapper
	 */
	private void initCache(UserMapper userMapper){
		if(userMapper==null){
			return;
		}
		String key  = generatorKey(userMapper.getPartnerId(),userMapper.getPartnerUserId(),
				userMapper.getServerId());
		userIdMapCache.put(userMapper.getUserId(), Optional.of(userMapper));
		pspMapCache.put(key, Optional.of(userMapper));
	}
	@Override
	public UserMapper getByPartnerUserId(final String partnerUserId,
			final String partnerId, final String serverId) {
		String key  = generatorKey(partnerId,partnerUserId,
				serverId);
		try {
			Optional<UserMapper> option = pspMapCache.get(key, new Callable<Optional<UserMapper>>() {
				@Override
				public Optional<UserMapper> call() throws Exception {
					UserMapper userMapper = userMapperDaoMysqlImpl.getByPartnerUserId(partnerUserId, partnerId, serverId);
					return Optional.fromNullable(userMapper);
				}
			});
			return option.orNull();
		} catch (ExecutionException e) {
			LogSystem.error(e, "");
		}
		return null;
	}
	
	@Override
	public List<UserMapper> getUserMapperByPartner(String partnerId) {
		return this.userMapperDaoMysqlImpl.getUserMapperByPartner(partnerId);
	}
	
	@Override
	public UserMapper get(String userId) {
		Optional<UserMapper> option = userIdMapCache.getUnchecked(userId);
		return option.orNull();
	}
	
	public void setUserMapperDaoMysqlImpl(UserMapperDaoMysqlImpl userMapperDaoMysqlImpl) {
		this.userMapperDaoMysqlImpl = userMapperDaoMysqlImpl;
	}
	
	public UserMapperDaoMysqlImpl getUserMapperDaoMysqlImpl() {
		return userMapperDaoMysqlImpl;
	}
	
}
