package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.RewardConstant;
import com.dataconfig.dao.RewardConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class RewardConstantService {
	
	private RewardConstantDao rewardConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<RewardConstant> findPageList(Integer currentPage, Integer pageSize) {
		rewardConstantDao.closeSession(DBSource.CFG);
		return rewardConstantDao.findPage("from RewardConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setRewardConstantDao(RewardConstantDao rewardConstantDao) {
		this.rewardConstantDao = rewardConstantDao;
	}

	public RewardConstantDao getRewardConstantDao() {
		return rewardConstantDao;
	}

}
