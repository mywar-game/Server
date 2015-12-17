package com.dataconfig.service;

import java.util.ArrayList;
import java.util.List;

import com.dataconfig.bo.SParamConfig;
import com.dataconfig.dao.SParamConfigDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class SParamConfigService {
	
	private SParamConfigDao sParamConfigDao;
	
	/**
	 * 新增配置常量数据
	 * @param sParamConfig
	 */
	public void addSParamConfig(SParamConfig sParamConfig) {
		sParamConfigDao.save(sParamConfig, DBSource.CFG);
	}
	
	/**
	 * 删除配置常量数据
	 * @param key
	 */
	public void delSParamConfig(String key) {
		sParamConfigDao.remove(findOneSParamConfig(key), DBSource.CFG);
	}
	
	/**
	 * 修改配置常量数据
	 * @param sParamConfig
	 */
	public void updateOneSParamConfig(SParamConfig sParamConfig) {
		sParamConfigDao.update(sParamConfig, DBSource.CFG);
	}

	/**
	 * 查询配置常量数据
	 * @param key
	 * @return
	 */
	public SParamConfig findOneSParamConfig(String key) {
		return this.sParamConfigDao.loadBy("configkey", key, DBSource.CFG);
	}
	
	/**
	 * 查询配置常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<SParamConfig> findSParamConfigPageList(Integer currentPage, Integer pageSize) {
		sParamConfigDao.closeSession(DBSource.CFG);
		return sParamConfigDao.findPage("from SParamConfig", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**查询配置常量列表
	 * @return
	 */
	public List<SParamConfig> findSParamConfigList() {
		sParamConfigDao.closeSession(DBSource.CFG);
		return sParamConfigDao.findAll();
	}
	
	public SParamConfigDao getsParamConfigDao() {
		return sParamConfigDao;
	}

	public void setsParamConfigDao(SParamConfigDao sParamConfigDao) {
		this.sParamConfigDao = sParamConfigDao;
	}
}