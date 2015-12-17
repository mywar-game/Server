package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.GlobalRewardConstant;
import com.dataconfig.dao.GlobalRewardConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class GlobalRewardConstantService {
	
	private GlobalRewardConstantDao globalRewardConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<GlobalRewardConstant> findPageList(Integer currentPage, Integer pageSize) {
		globalRewardConstantDao.closeSession(DBSource.CFG);
		return globalRewardConstantDao.findPage("from GlobalRewardConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 获取 globalRewardConstantDao 
	 */
	public GlobalRewardConstantDao getGlobalRewardConstantDao() {
		return globalRewardConstantDao;
	}

	/**
	 * 设置 globalRewardConstantDao 
	 */
	public void setGlobalRewardConstantDao(
			GlobalRewardConstantDao globalRewardConstantDao) {
		this.globalRewardConstantDao = globalRewardConstantDao;
	}

}
