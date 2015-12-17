package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.MMultipleActivityConfig;
import com.dataconfig.dao.MMultipleActivityConfigDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class MultipleActivityConfigService {

	private MMultipleActivityConfigDao multipleActivityConfigDao;
	
	/**
	 * 新增多倍活动配置数据
	 * @param MMultipleActivityConfig
	 */
	public void addMMultipleActivityConfig(MMultipleActivityConfig multipleActivityConfig) {
		multipleActivityConfigDao.save(multipleActivityConfig, DBSource.CFG);
	}
	
	/**
	 * 删除多倍活动配置数据
	 * @param key
	 */
	public void delMMultipleActivityConfig(Integer configId) {
		multipleActivityConfigDao.remove(findOneMMultipleActivityConfig(configId), DBSource.CFG);
	}
	
	/**
	 * 修改多倍活动配置数据
	 * @param MMultipleActivityConfig
	 */
	public void updateOneMMultipleActivityConfig(MMultipleActivityConfig multipleActivityConfig) {
		multipleActivityConfigDao.update(multipleActivityConfig, DBSource.CFG);
	}
	
	/**
	 * 查询多倍活动配置数据
	 * @param key
	 * @return
	 */
	public MMultipleActivityConfig findOneMMultipleActivityConfig(Integer configId) {
		return this.multipleActivityConfigDao.loadBy("configId", configId, DBSource.CFG);
	}
	
	/**
	 * 查询多倍活动配置分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<MMultipleActivityConfig> findMMultipleActivityConfigPageList(Integer currentPage, Integer pageSize) {
		multipleActivityConfigDao.closeSession(DBSource.CFG);
		return multipleActivityConfigDao.findPage("from MMultipleActivityConfig", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setMultipleActivityConfigDao(MMultipleActivityConfigDao multipleActivityConfigDao) {
		this.multipleActivityConfigDao = multipleActivityConfigDao;
	}

	public MMultipleActivityConfigDao getMultipleActivityConfigDao() {
		return multipleActivityConfigDao;
	}
}
