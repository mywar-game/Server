package com.dataconfig.service;

import java.util.List;

import com.dataconfig.bo.UContinueLoginConstant;
import com.dataconfig.dao.UContinueLoginConstantDao;
import com.framework.common.DBSource;

public class UContinueLoginConstantService {

	private UContinueLoginConstantDao continueLoginConstantDao;
	
	/**查询配置常量列表
	 * @return
	 */
	public List<UContinueLoginConstant> findUContinueLoginConstantList() {
		continueLoginConstantDao.closeSession(DBSource.CFG);
		return continueLoginConstantDao.findAll();
	}
	
	/**
	 * 查询配置常量数据
	 * @param key
	 * @return
	 */
	public UContinueLoginConstant findOneUContinueLoginConstant(Integer id) {
		return this.continueLoginConstantDao.loadBy("id", id, DBSource.CFG);
	}
	
	/**
	 * 修改配置常量数据
	 * @param sParamConfig
	 */
	public void updateOneUContinueLoginConstant(UContinueLoginConstant continueLoginConstant) {
		continueLoginConstantDao.update(continueLoginConstant, DBSource.CFG);
	}

	public void setContinueLoginConstantDao(UContinueLoginConstantDao continueLoginConstantDao) {
		this.continueLoginConstantDao = continueLoginConstantDao;
	}

	public UContinueLoginConstantDao getContinueLoginConstantDao() {
		return continueLoginConstantDao;
	}
}
