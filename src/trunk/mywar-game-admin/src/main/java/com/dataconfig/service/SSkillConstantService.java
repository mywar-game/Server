package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.SSkillConstant;
import com.dataconfig.dao.SSkillConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class SSkillConstantService {

	private SSkillConstantDao sSkillConstantDao;

	/**
	 * 查询技能常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<SSkillConstant> findSSkillConstantPageList(Integer id, String name, Integer currentPage, Integer pageSize) {
		String str = "from SSkillConstant";
		if (id != null) {
			str = "from SSkillConstant c where c.skillId = " + id;
		}
		if (name != null && !"".equals(name)) {
			str = "from SSkillConstant c where c.skillName like '%" + name + "%'";
		}
//		System.out.println(str);
		sSkillConstantDao.closeSession(DBSource.CFG);
		return sSkillConstantDao.findPage(str, new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 查询技能常量列表
	 * @return
	 */
	public List<SSkillConstant> findSSkillConstantList() {
		sSkillConstantDao.closeSession(DBSource.CFG);
		return sSkillConstantDao.findAll();
	}
	
	/**
	 * 技能编号和技能名称的map
	 * @return
	 */
	public Map<Integer, String> findSkillIDNameMap() {
		Map<Integer, String> skillIDNameMap = new HashMap<Integer, String>();
		List<SSkillConstant> skillConstantList = this.findSSkillConstantList();
		for (SSkillConstant skillConstant : skillConstantList) {
			skillIDNameMap.put(skillConstant.getSkillId(), skillConstant.getSkillName());
		}
		return skillIDNameMap;
	}
	
	/**
	 * 装备技能编号和技能名称的map
	 * @return
	 */
	public Map<Integer, String> findEquipmentSkillIDNameMapByCondition(Integer id, String name) {
		Map<Integer, String> equipmentSkillIdNameMap = new LinkedHashMap<Integer, String>();
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("SELECT SKILL_ID, SKILL_NAME FROM s_skill_constant WHERE SKILL_TYPE IN (2,3) AND SKILL_ID = " + id);
		}
		if (name != null && !"".equals(name)) {
			sb.append("SELECT SKILL_ID, SKILL_NAME FROM s_skill_constant WHERE SKILL_TYPE IN (2,3) AND SKILL_NAME LIKE '%" + name + "%'");
		}
		if (sb.length() == 0) {
			sb.append("SELECT SKILL_ID, SKILL_NAME FROM s_skill_constant WHERE SKILL_TYPE IN (2,3)");
		}
		sSkillConstantDao.closeSession(DBSource.CFG);
		List<Object> list = sSkillConstantDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			Integer skillId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			String treasureName = ((Object[]) list.get(i))[1].toString();
			equipmentSkillIdNameMap.put(skillId, treasureName);
		}
		return equipmentSkillIdNameMap;
	}
	
	/**
	 * 查询技能常量数据
	 * @param skillConstantId
	 * @return
	 */
	public SSkillConstant findOneSSkillConstant(Integer skillConstantId) {
		return sSkillConstantDao.loadBy("skillConstantId", skillConstantId, DBSource.CFG);
	}
	
	/**
	 * 新增技能常量数据
	 * @param skillConstant
	 */
	public void addOneSSkillConstant(SSkillConstant sSkillConstant) {
		sSkillConstantDao.save(sSkillConstant, DBSource.CFG);
	}
	
	/**
	 * 删除技能常量数据
	 * @param skillConstantId
	 */
	public void delOneSSkillConstant(Integer skillConstantId) {
		sSkillConstantDao.remove(this.findOneSSkillConstant(skillConstantId), DBSource.CFG);
	}
	
	/**
	 * 更新技能常量数据
	 * @param sSkillConstant
	 */
	public void updateOneSkillConstant(SSkillConstant sSkillConstant) {
		sSkillConstantDao.update(sSkillConstant, DBSource.CFG);
	}
	
	public SSkillConstantDao getsSkillConstantDao() {
		return sSkillConstantDao;
	}

	public void setsSkillConstantDao(SSkillConstantDao sSkillConstantDao) {
		this.sSkillConstantDao = sSkillConstantDao;
	}
}
