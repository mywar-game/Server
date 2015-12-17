package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.LLevelupConstant;
import com.dataconfig.dao.LLevelupConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class LLevelupConstantService {
	
	private LLevelupConstantDao lLevelupConstantDao;
	
	/**
	 * 新增等级常量数据
	 * @param lLevelupConstant
	 */
	public void addLLevelupConstant(LLevelupConstant lLevelupConstant) {
		lLevelupConstantDao.save(lLevelupConstant, DBSource.CFG);
	}
	
	/**
	 * 删除等级常量数据
	 * @param level
	 */
	public void delLLevelupConstant(Integer level) {
		lLevelupConstantDao.remove(findOneLLevelupConstant(level), DBSource.CFG);
	}
	
	/**
	 * 修改等级常量数据
	 * @param lLevelupConstant
	 */
	public void updateOneLLevelupConstant(LLevelupConstant lLevelupConstant) {
		lLevelupConstantDao.update(lLevelupConstant, DBSource.CFG);
	}

	/**
	 * 查询等级常量数据
	 * @param level
	 * @return
	 */
	public LLevelupConstant findOneLLevelupConstant(Integer level) {
		return this.lLevelupConstantDao.loadBy("level", level, DBSource.CFG);
	}
	
	/**
	 * 查询等级常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<LLevelupConstant> findLLevelupConstantList(Integer currentPage, Integer pageSize) {
		lLevelupConstantDao.closeSession(DBSource.CFG);
		return lLevelupConstantDao.findPage("from LLevelupConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public LLevelupConstantDao getlLevelupConstantDao() {
		return lLevelupConstantDao;
	}

	public void setlLevelupConstantDao(LLevelupConstantDao lLevelupConstantDao) {
		this.lLevelupConstantDao = lLevelupConstantDao;
	}
}