package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.SEffectConstant;
import com.dataconfig.dao.SEffectConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class SEffectConstantService {
	
	private SEffectConstantDao sEffectConstantDao;
	
	/**
	 * 查询技能效果常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<SEffectConstant> findSEffectConstantPageList(Integer currentPage, Integer pageSize) {
		sEffectConstantDao.closeSession(DBSource.CFG);
		return sEffectConstantDao.findPage("from SEffectConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 查询技能效果常量数据
	 * @param effectId
	 * @return
	 */
	public SEffectConstant findOneSEffectConstant(Integer effectId) {
		return sEffectConstantDao.loadBy("effectId", effectId, DBSource.CFG);
	}
	
	/**
	 * 查询技能效果常量List
	 * @return
	 */
	public List<SEffectConstant> findSEffectConstantList() {
		sEffectConstantDao.closeSession(DBSource.CFG);
		return sEffectConstantDao.findAll();
	}
	
	/**
	 * 查询技能效果常量map
	 * @return
	 */
	public Map<Integer, SEffectConstant> findSEffectConstantListMap() {
		Map<Integer, SEffectConstant> map = new HashMap<Integer, SEffectConstant>();
		List<SEffectConstant> list = this.findSEffectConstantList();
		for (SEffectConstant sEffectConstant : list) {
			map.put(sEffectConstant.getEffectId(), sEffectConstant);
		}
		return map;
	}
	
	/**
	 * 添加技能效果常量数据
	 * @param sEffectConstant
	 */
	public void addOneSEffectConstant(SEffectConstant sEffectConstant) {
		sEffectConstantDao.save(sEffectConstant, DBSource.CFG);
	}
	
	/**
	 * 删除技能效果常量数据
	 * @param effectId
	 */
	public void delOneSEffectConstant(Integer effectId) {
		sEffectConstantDao.remove(this.findOneSEffectConstant(effectId), DBSource.CFG);
	}
	
	/**
	 * 更新技能效果常量数据
	 * @param sEffectConstant
	 */
	public void updateOneSEffectConstant(SEffectConstant sEffectConstant) {
		sEffectConstantDao.update(sEffectConstant, DBSource.CFG);
	}
	
	public SEffectConstantDao getsEffectConstantDao() {
		return sEffectConstantDao;
	}

	public void setsEffectConstantDao(SEffectConstantDao sEffectConstantDao) {
		this.sEffectConstantDao = sEffectConstantDao;
	}
}
