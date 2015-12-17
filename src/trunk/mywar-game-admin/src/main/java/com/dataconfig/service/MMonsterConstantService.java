package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.MMonsterConstant;
import com.dataconfig.dao.MMonsterConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class MMonsterConstantService {
	private MMonsterConstantDao monsterConstantDao;
	
	/**
	 * 查询怪物分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<MMonsterConstant> findMMonsterConstantList(Integer id, Integer level, String name, Integer currentPage, Integer pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("from MMonsterConstant c where 1 = 1 ");
		if (id != null) {
			sb.append(" and  c.monsterId = " + id);
		}
		if (level != null) {
			sb.append(" and  c.monsterLevel = " + level);
		}
		if (name != null && !"".equals(name)) {
			sb.append(" and  c.monsterName like '%" + name + "%'");
		}
//		System.out.println(sb.toString());
		monsterConstantDao.closeSession(DBSource.CFG);
		return monsterConstantDao.findPage(sb.toString(), new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 查询怪物常量数据
	 * @param monsterId
	 * @return
	 */
	public MMonsterConstant findOneMMonsterConstant(Integer monsterId) {
		return monsterConstantDao.loadBy("monsterId", monsterId, DBSource.CFG);
	}
	
	/**
	 * 查询怪物列表
	 * @return
	 */
	public List<MMonsterConstant> findAllMMonsterConstant() {
		monsterConstantDao.closeSession(DBSource.CFG);
		return monsterConstantDao.findAll();
	}
	
	/**
	 * 查询怪物id和怪物名称map
	 * @return
	 */
	public Map<Integer, String> findMonsterIdNameMap() {
		Map<Integer, String> monsterIdNameMap = new HashMap<Integer, String>();
		monsterConstantDao.closeSession(DBSource.CFG);
		List<Object> list = monsterConstantDao.findSQL_("select MONSTER_ID, MONSTER_NAME from m_monster_constant");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int monsterId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String monsterName = ((Object[]) list.get(i))[1].toString();
				monsterIdNameMap.put(monsterId, monsterName);
			}
		}
		return monsterIdNameMap;
	}
	
	/**
	 * 查询怪物id和怪物名称map
	 * @return
	 */
	public Map<Integer, String> findMonsterCategoryAndNameMap() {
		Map<Integer, String> monsterCategoryAndNameMap = new HashMap<Integer, String>();
		monsterConstantDao.closeSession(DBSource.CFG);
		List<Object> list = monsterConstantDao.findSQL_("SELECT MONSTER_ID%1000 AS category,MONSTER_NAME FROM m_monster_constant where BATTLE_MODEL = 0 GROUP BY MONSTER_NAME");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int category = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String monsterName = ((Object[]) list.get(i))[1].toString();
				monsterCategoryAndNameMap.put(category, monsterName);
			}
		}
		return monsterCategoryAndNameMap;
	}
	
	public Map<Integer, String> findMonsterIdNameMapByCondition(Integer Level, String Name) {
		Map<Integer, String> monsterIdNameMap = new LinkedHashMap<Integer, String>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MONSTER_ID, MONSTER_NAME FROM m_monster_constant WHERE MONSTER_LEVEL = ").append(Level).append(" AND MONSTER_NAME LIKE '%").append(Name).append("%'");
		
		monsterConstantDao.closeSession(DBSource.CFG);
		List<Object> list = monsterConstantDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer monsterId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String monsterName = ((Object[]) list.get(i))[1].toString();
				monsterIdNameMap.put(monsterId, monsterName);
			}
		}
		return monsterIdNameMap;
	}
	/**
	 * 添加怪物常量数据
	 * @param monsterConstant
	 */
	public void addOneMMonsterConstant(MMonsterConstant monsterConstant) {
		monsterConstantDao.save(monsterConstant, DBSource.CFG);
	}
	
	/**
	 * 删除怪物常量数据
	 * @param monsterId
	 */
	public void delOneMMonsterConstant(Integer monsterId) {
		monsterConstantDao.remove(this.findOneMMonsterConstant(monsterId), DBSource.CFG);
	}
	
	/**
	 * 更新怪物常量数据
	 * @param monsterConstant
	 */
	public void updateOneMMonsterConstant(MMonsterConstant monsterConstant) {
		monsterConstantDao.update(monsterConstant, DBSource.CFG);
	}
	
	public void setMonsterConstantDao(MMonsterConstantDao monsterConstantDao) {
		this.monsterConstantDao = monsterConstantDao;
	}

	public MMonsterConstantDao getMonsterConstantDao() {
		return monsterConstantDao;
	}
}
