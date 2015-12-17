package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.AActionConstant;
import com.dataconfig.dao.AActionConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class AActionConstantService {
	private AActionConstantDao actionConstantDao;

	/**
	 * 新增动作常量数据
	 * 
	 * @param actionConstant
	 */
	public void addAActionConstant(AActionConstant actionConstant) {
		actionConstantDao.save(actionConstant, DBSource.CFG);
	}

	/**
	 * 删除动作常量数据
	 * 
	 * @param keyWord
	 */
	public void delAActionConstant(Integer actionId) {
		actionConstantDao
				.remove(findOneAActionConstant(actionId), DBSource.CFG);
	}

	/**
	 * 修改动作常量数据
	 * 
	 * @param actionConstant
	 */
	public void updateOneAActionConstant(AActionConstant actionConstant) {
		actionConstantDao.update(actionConstant, DBSource.CFG);
	}

	/**
	 * 查询动作常量数据
	 * 
	 * @param keyWord
	 * @return
	 */
	public AActionConstant findOneAActionConstant(Integer actionId) {
		return this.actionConstantDao
				.loadBy("actionId", actionId, DBSource.CFG);
	}

	/**
	 * 查询动作常量列表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<AActionConstant> findAActionConstantPageList(
			Integer currentPage, Integer pageSize) {
		actionConstantDao.closeSession(DBSource.CFG);
		return actionConstantDao.findPage("from AActionConstant",
				new ArrayList<Object>(), pageSize, currentPage);
	}

	public List<AActionConstant> findAActionConstantList() {
		actionConstantDao.closeSession(DBSource.ADMIN);
		return actionConstantDao.findAll();
	}

	public List<AActionConstant> findAActionConstantListByType(
			Integer actionType) {
		return actionConstantDao.find(
				"from AActionConstant where actionType = " + actionType,
				DBSource.ADMIN);
	}

	public Map<Integer, AActionConstant> findMap() {
		List<AActionConstant> list = this.findAActionConstantList();
		Map<Integer, AActionConstant> map = new HashMap<Integer, AActionConstant>();
		for (AActionConstant actionConstant : list) {
			map.put(actionConstant.getActionId(), actionConstant);
		}
		return map;
	}

	public void setActionConstantDao(AActionConstantDao actionConstantDao) {
		this.actionConstantDao = actionConstantDao;
	}

	public AActionConstantDao getActionConstantDao() {
		return actionConstantDao;
	}
}
