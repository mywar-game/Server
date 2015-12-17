package com.dataconfig.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.AAchievementConstant;
import com.dataconfig.dao.AchievementConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class AchievementConstantService {

	private AchievementConstantDao achievementConstantDao;
	
	/**
	 * 查询成就常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<AAchievementConstant> findAchievementConstantList(Integer currentPage, Integer pageSize) {
		achievementConstantDao.closeSession(DBSource.CFG);
		return achievementConstantDao.findPage("from AAchievementConstant", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 查询成就常量数据
	 * @param key
	 * @return
	 */
	public AAchievementConstant findOneAchievementConstant(Integer achievementId) {
		return this.achievementConstantDao.loadBy("achievementId", achievementId, DBSource.CFG);
	}
	
	/**
	 * 新增成就常量数据
	 * @param sParamConfig
	 */
	public void addAchievementConstant(AAchievementConstant achievementConstant) {
		achievementConstantDao.save(achievementConstant, DBSource.CFG);
	}
	
	/**
	 * 删除成就常量数据
	 * @param key
	 */
	public void delAchievementConstant(Integer achievementId) {
		this.achievementConstantDao.remove(this.findOneAchievementConstant(achievementId), DBSource.CFG);
	}
	
	/**
	 * 更新成就常量数据
	 * @param sParamConfig
	 */
	public void updateOneAchievementConstant(AAchievementConstant achievementConstant) {
		achievementConstantDao.update(achievementConstant, DBSource.CFG);
	}
	
	/**
	 * 查询成就id和成就名称map
	 * @return
	 */
	public Map<Integer, String> findAchievementIdNameMap() {
		Map<Integer, String> achievementIdNameMap = new LinkedHashMap<Integer, String>();
		achievementConstantDao.closeSession(DBSource.CFG);
		List<Object> list = achievementConstantDao.findSQL_("select ACHIEVEMENT_ID, ACHIEVEMENT_NAME from a_achievement_constant");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int achievementId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String achievementName = ((Object[]) list.get(i))[1].toString();
				achievementIdNameMap.put(achievementId, achievementName);
			}
		}
		return achievementIdNameMap;
	}

	public void setAchievementConstantDao(AchievementConstantDao achievementConstantDao) {
		this.achievementConstantDao = achievementConstantDao;
	}

	public AchievementConstantDao getAchievementConstantDao() {
		return achievementConstantDao;
	}
}
