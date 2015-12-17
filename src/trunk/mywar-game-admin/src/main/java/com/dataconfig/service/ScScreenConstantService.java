package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.ScScreenConstant;
import com.dataconfig.dao.ScScreenConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class ScScreenConstantService {
	
	private ScScreenConstantDao scScreenConstantDao;
	
	/**
	 * 新增 场景常量数据
	 * @param scScreenConstant
	 */
	public void addScScreenConstant(ScScreenConstant scScreenConstant) {
		scScreenConstantDao.save(scScreenConstant, DBSource.CFG);
	}
	
	/**
	 * 删除 场景常量数据
	 * @param screenId
	 */
	public void delScScreenConstant(String screenId) {
		scScreenConstantDao.remove(findOneScScreenConstant(screenId), DBSource.CFG);
	}
	
	/**
	 * 修改 场景常量数据
	 * @param scScreenConstant
	 */
	public void updateOneScScreenConstant(ScScreenConstant scScreenConstant) {
		scScreenConstantDao.update(scScreenConstant, DBSource.CFG);
	}

	/**
	 * 查询 场景常量数据
	 * @param screenId
	 * @return
	 */
	public ScScreenConstant findOneScScreenConstant(String screenId) {
		return this.scScreenConstantDao.loadBy("screenId", screenId, DBSource.CFG);
	}
	
	/**
	 * 查询 场景常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<ScScreenConstant> findScScreenConstantList(Integer currentPage, Integer pageSize) {
		scScreenConstantDao.closeSession(DBSource.CFG);
		return scScreenConstantDao.findPage("from ScScreenConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public ScScreenConstantDao getscScreenConstantDao() {
		return scScreenConstantDao;
	}

	public void setscScreenConstantDao(ScScreenConstantDao scScreenConstantDao) {
		this.scScreenConstantDao = scScreenConstantDao;
	}
}