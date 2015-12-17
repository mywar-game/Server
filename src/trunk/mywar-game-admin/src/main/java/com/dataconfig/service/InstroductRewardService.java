package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.InstroductorReward;
import com.dataconfig.dao.InstroductRewardDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class InstroductRewardService {
	
	private InstroductRewardDao instroductRewardDao;

	/**
	 * @return the instroductRewardDao
	 */
	public InstroductRewardDao getInstroductRewardDao() {
		return instroductRewardDao;
	}

	/**
	 * @param instroductRewardDao the instroductRewardDao to set
	 */
	public void setInstroductRewardDao(InstroductRewardDao instroductRewardDao) {
		this.instroductRewardDao = instroductRewardDao;
	}
	
	 /** 新增推荐奖励
	 * @param instroductorReward
	 */
	public void addInstroductReward(InstroductorReward instroductorReward) {
		instroductRewardDao.save(instroductorReward, DBSource.CFG);
	}
	
	/**
	 * 删除推荐奖励
	 * @param npcId
	 */
	public void delInstroductReward(Integer instroductorRewardId) {
		instroductRewardDao.remove(findOneInstroductReward(instroductorRewardId), DBSource.CFG);
	}
	
	/**
	 * 修改推荐奖励
	 * @param instroductorReward
	 */
	public void updateInstroductReward(InstroductorReward instroductorReward) {
		instroductRewardDao.update(instroductorReward
				, DBSource.CFG);
	}

	/**
	 * 查询好友推荐奖励
	 * @param npcId
	 * @return
	 */
	public InstroductorReward findOneInstroductReward(Integer instroductorRewardId) {
		return this.instroductRewardDao.loadBy("instroductorRewardId", instroductorRewardId, DBSource.CFG);
	}
	
	/**
	 * 查询推荐奖励
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<InstroductorReward> findInstroductRewardList(Integer currentPage, Integer pageSize) {
		instroductRewardDao.closeSession(DBSource.CFG);
		return instroductRewardDao.findPage("from InstroductorReward", new ArrayList<Object>(), pageSize, currentPage);
	}

}
