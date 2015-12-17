package com.dataconfig.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.HHeroConstant;
import com.dataconfig.dao.HHeroConstantDAO;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class HHeroConstantService {

	private HHeroConstantDAO hHeroConstantDAO;
	
	/**
	 * 查询任务常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<HHeroConstant> findHHeroConstantPageList(Integer heroId, Integer currentPage, Integer pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("from HHeroConstant h");
		if (heroId != null) {
			sb.append(" where h.heroId = " + heroId);
		}
		hHeroConstantDAO.closeSession(DBSource.CFG);
		return hHeroConstantDAO.findPage(sb.toString(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 查询任务常量数据
	 * @param key
	 * @return
	 */
	public HHeroConstant findOneHHeroConstant(Integer heroId) {
		return this.hHeroConstantDAO.loadBy("heroId", heroId, DBSource.CFG);
	}
	
	/**
	 * 新增任务常量数据
	 * @param sParamConfig
	 */
	public void addHHeroConstant(HHeroConstant hheroConstant) {
		hHeroConstantDAO.save(hheroConstant, DBSource.CFG);
	}
	
	/**
	 * 删除任务常量数据
	 * @param key
	 */
	public void delHHeroConstant(Integer heroId) {
		this.hHeroConstantDAO.remove(this.findOneHHeroConstant(heroId), DBSource.CFG);
	}
	
	/**
	 * 更新配置常量数据
	 * @param sParamConfig
	 */
	public void updateOneHHeroConstant(HHeroConstant hheroConstant) {
		hHeroConstantDAO.update(hheroConstant, DBSource.CFG);
	}
	
	/**
	 * 查询英雄id和英雄名称map
	 * @return
	 */
	public Map<Integer, String> findHeroIdNameMap() {
		Map<Integer, String> heroIdNameMap = new LinkedHashMap<Integer, String>();
		hHeroConstantDAO.closeSession(DBSource.CFG);
		List<Object> list = hHeroConstantDAO.findSQL_("select HERO_ID, HERO_NAME from h_hero_constant");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int heroId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String heroName = ((Object[]) list.get(i))[1].toString();
				heroIdNameMap.put(heroId, heroName);
			}
		}
		return heroIdNameMap;
	}
	
	public HHeroConstantDAO gethHeroConstantDAO() {
		return hHeroConstantDAO;
	}
	
	public void sethHeroConstantDAO(HHeroConstantDAO hHeroConstantDAO) {
		this.hHeroConstantDAO = hHeroConstantDAO;
	}
}