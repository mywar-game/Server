package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.ARewardConstant;
import com.dataconfig.dao.ARewardConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

/**
 * 活动中心奖励
 * @author hzy
 * 2013-6-5
 */
public class ARewardConstantService {
	
	private ARewardConstantDao aRewardConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<ARewardConstant> findPageList(Integer currentPage, Integer pageSize) {
		aRewardConstantDao.closeSession(DBSource.CFG);
		return aRewardConstantDao.findPage("from ARewardConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 获取 aRewardConstantDao 
	 */
	public ARewardConstantDao getaRewardConstantDao() {
		return aRewardConstantDao;
	}

	/**
	 * 设置 aRewardConstantDao 
	 */
	public void setaRewardConstantDao(ARewardConstantDao aRewardConstantDao) {
		this.aRewardConstantDao = aRewardConstantDao;
	}

}
