package com.dataconfig.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.SystemHero;
import com.dataconfig.dao.SystemHeroDao;
import com.framework.common.DBSource;

/**
 * 英雄Service
 * 
 * @author yezp
 */
public class SystemHeroService {

	private SystemHeroDao systemHeroDao;

	/**
	 * 获取英雄列表
	 * 
	 * @return
	 */
	public List<SystemHero> getHeroList() {
		return systemHeroDao.find("from SystemHero", DBSource.CFG);
	}
	
	/**
	 * 分类获取英雄列表
	 * 
	 * @return
	 */
	public List<SystemHero> getHeroDistinctList() {
		return systemHeroDao.find("from SystemHero group by heroName", DBSource.CFG);
	}

	/**
	 * 根据heriId获取heroName
	 * 
	 * @return
	 */
	public Map<Integer, String> getHeroNameMap() {
		Map<Integer, String> heroMap = new HashMap<Integer, String>();
		List<SystemHero> heroList = getHeroDistinctList();
		for (SystemHero hero : heroList) {
			heroMap.put(hero.getHeroId(), hero.getHeroName());
		}

		return heroMap;
	}

	/**
	 * 根据heriId获取hero
	 * 
	 * @return
	 */
	public Map<Integer, SystemHero> getHeroMap() {
		Map<Integer, SystemHero> heroMap = new HashMap<Integer, SystemHero>();
		List<SystemHero> heroList = getHeroList();
		for (SystemHero hero : heroList) {
			heroMap.put(hero.getSystemHeroId(), hero);
		}

		return heroMap;
	}

	public SystemHeroDao getSystemHeroDao() {
		return systemHeroDao;
	}

	public void setSystemHeroDao(SystemHeroDao systemHeroDao) {
		this.systemHeroDao = systemHeroDao;
	}

}
