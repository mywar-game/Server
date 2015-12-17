package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.MonsterGeneConstant;
import com.dataconfig.dao.MonsterGeneConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class MonsterGeneConstantService {
	
	private MonsterGeneConstantDao monsterGeneConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<MonsterGeneConstant> findPageList(Integer currentPage, Integer pageSize) {
		monsterGeneConstantDao.closeSession(DBSource.CFG);
		return monsterGeneConstantDao.findPage("from MonsterGeneConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setMonsterGeneConstantDao(MonsterGeneConstantDao monsterGeneConstantDao) {
		this.monsterGeneConstantDao = monsterGeneConstantDao;
	}

	public MonsterGeneConstantDao getMonsterGeneConstantDao() {
		return monsterGeneConstantDao;
	}

}
