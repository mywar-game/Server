package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.GGuildTechnologyConstant;
import com.dataconfig.dao.GGuildTechnologyConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class GGuildTechnologyConstantService {
	
	private GGuildTechnologyConstantDao guildTechnologyConstantDao;
	
	/**
	 * 新增军团科技常量数据
	 * @param guildTechnologyConstant
	 */
	public void addGGuildTechnologyConstant(GGuildTechnologyConstant guildTechnologyConstant) {
		guildTechnologyConstantDao.save(guildTechnologyConstant, DBSource.CFG);
	}
	
	/**
	 * 删除军团科技常量数据
	 * @param guildTechnologyId
	 */
	public void delGGuildTechnologyConstant(Integer guildTechnologyId) {
		guildTechnologyConstantDao.remove(findOneGGuildTechnologyConstant(guildTechnologyId), DBSource.CFG);
	}
	
	/**
	 * 修改军团科技常量数据
	 * @param guildTechnologyConstant
	 */
	public void updateOneGGuildTechnologyConstant(GGuildTechnologyConstant guildTechnologyConstant) {
		guildTechnologyConstantDao.update(guildTechnologyConstant, DBSource.CFG);
	}

	/**
	 * 查询军团科技常量数据
	 * @param guildTechnologyId
	 * @return
	 */
	public GGuildTechnologyConstant findOneGGuildTechnologyConstant(Integer guildTechnologyId) {
		return this.guildTechnologyConstantDao.loadBy("guildTechnologyId", guildTechnologyId, DBSource.CFG);
	}
	
	/**
	 * 查询军团科技常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<GGuildTechnologyConstant> findGGuildTechnologyConstantList(Integer currentPage, Integer pageSize) {
		guildTechnologyConstantDao.closeSession(DBSource.CFG);
		return guildTechnologyConstantDao.findPage("from GGuildTechnologyConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public GGuildTechnologyConstantDao getGuildTechnologyConstantDao() {
		return guildTechnologyConstantDao;
	}

	public void setGuildTechnologyConstantDao(
			GGuildTechnologyConstantDao guildTechnologyConstantDao) {
		this.guildTechnologyConstantDao = guildTechnologyConstantDao;
	}
}