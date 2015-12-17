package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.MonsterAttribute;
import com.dataconfig.dao.MonsterAttributeDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class MonsterAttributeService {
	
	private MonsterAttributeDao monsterAttributeDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<MonsterAttribute> findPageList(Integer currentPage, Integer pageSize) {
		monsterAttributeDao.closeSession(DBSource.CFG);
		return monsterAttributeDao.findPage("from MonsterAttribute", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 获取 monsterAttributeDao 
	 */
	public MonsterAttributeDao getMonsterAttributeDao() {
		return monsterAttributeDao;
	}

	/**
	 * 设置 monsterAttributeDao 
	 */
	public void setMonsterAttributeDao(MonsterAttributeDao monsterAttributeDao) {
		this.monsterAttributeDao = monsterAttributeDao;
	}

}
