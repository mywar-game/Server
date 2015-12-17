package com.fantingame.game.common.dao.userdata;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public abstract class BaseCacheDao<T> implements ModuleEventHandler{
	private static final long DEFAULT_MAX_SIZE = 8192;
	private static final int DEFAULT_EXPIRE_HOUR = 24;
    //用户id与UserMapper的映射map
	private LoadingCache<String,Optional<T>> cache = CacheBuilder.newBuilder().expireAfterAccess(expireHour(), TimeUnit.HOURS).maximumSize(cacheMaxSize()).build(new CacheLoader<String, Optional<T>>() {
		@Override
		public Optional<T> load(String key) throws Exception {
			return Optional.fromNullable(loadFromDb(key));
		}
	});
	/**
	 * 获取  如果缓存中不存在 则会从db中加载
	 * @param key
	 * @return
	 */
	protected T getEntry(String key){
		return cache.getUnchecked(key).orNull();
	}
	/**
	 * 添加
	 * @param key
	 * @param t
	 */
	protected void addEntry(String key,T t){
		cache.put(key, Optional.fromNullable(t));
	}
	/**
	 * 判断缓存中是否存在  此方法如果存在返回true 不存在返回true 不会自动去加载db中的数据
	 * @param key
	 * @return
	 */
	protected boolean isExitKey(String key){
		Optional<T> t = cache.getIfPresent(key);
		if(t!=null){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 删除
	 * @param key
	 */
	protected void delete(String key){
		cache.invalidate(key);
	}
	/**
	 * 最大缓存容量大小
	 * @return
	 */
	protected long cacheMaxSize(){
		return DEFAULT_MAX_SIZE;
	}
	/**
	 * 过期时间
	 * @return
	 */
	protected int expireHour(){
		return DEFAULT_EXPIRE_HOUR;
	}
	/**
	 * 从db中查询数据
	 * @param key
	 * @return
	 */
	protected abstract T loadFromDb(String key);
	
	
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if(handlerType.equals("user_loginout_event")){
			String userId=baseModuleEvent.getStringValue("userId", "");
			LogSystem.debug(userId+"用户登出，清理缓存"+this.getClass().getSimpleName());
			delete(userId);
		}
	}
	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add("user_loginout_event");
		return list;
	}
	@Override
	public int order() {
		return 1000;
	}
}
