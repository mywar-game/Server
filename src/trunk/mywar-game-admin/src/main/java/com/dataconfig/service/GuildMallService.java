package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.GuildMall;
import com.dataconfig.dao.GuildMallDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class GuildMallService {
	
	private GuildMallDao guildMallDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<GuildMall> findPageList(Integer currentPage, Integer pageSize) {
		guildMallDao.closeSession(DBSource.CFG);
		return guildMallDao.findPage("from GuildMall", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 获取 guildMallDao 
	 */
	public GuildMallDao getGuildMallDao() {
		return guildMallDao;
	}

	/**
	 * 设置 guildMallDao 
	 */
	public void setGuildMallDao(GuildMallDao guildMallDao) {
		this.guildMallDao = guildMallDao;
	}

}
