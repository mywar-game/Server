package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BTrainingConfig;
import com.dataconfig.dao.TrainingConfigDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class TrainingConfigService {
	
	private TrainingConfigDao trainingConfigDao;
	
	/**
	 * 修改动作常量数据
	 * @param actionConstant
	 */
	public void updateOne(BTrainingConfig trainingConfig) {
		trainingConfigDao.update(trainingConfig, DBSource.CFG);
	}

	/**
	 * 查询动作常量数据
	 * @param keyWord
	 * @return
	 */
	public BTrainingConfig findOne(Integer level) {
		return this.trainingConfigDao.loadBy("level", level, DBSource.CFG);
	}
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BTrainingConfig> findPageList(Integer currentPage, Integer pageSize) {
		trainingConfigDao.closeSession(DBSource.CFG);
		return trainingConfigDao.findPage("from BTrainingConfig", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setTrainingConfigDao(TrainingConfigDao trainingConfigDao) {
		this.trainingConfigDao = trainingConfigDao;
	}

	public TrainingConfigDao getTrainingConfigDao() {
		return trainingConfigDao;
	}

}
