package com.dataconfig.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.BBuildingConstant;
import com.dataconfig.dao.BBuildingConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class BBuildingConstantService {
	
	private BBuildingConstantDao bBuildingConstantDao;
	
	/**
	 * 新增建筑常量数据
	 * @param bBuildingConstant
	 */
	public void addBBuildingConstant(BBuildingConstant bBuildingConstant) {
		bBuildingConstantDao.save(bBuildingConstant, DBSource.CFG);
	}
	
	/**
	 * 删除建筑常量数据
	 * @param buildingId
	 */
	public void delBBuildingConstant(Integer buildingId) {
		bBuildingConstantDao.remove(findOneBBuildingConstant(buildingId), DBSource.CFG);
	}
	
	/**
	 * 修改建筑常量数据
	 * @param bBuildingConstant
	 */
	public void updateOneBBuildingConstant(BBuildingConstant bBuildingConstant) {
		bBuildingConstantDao.update(bBuildingConstant, DBSource.CFG);
	}

	/**
	 * 查询建筑常量数据
	 * @param buildingId
	 * @return
	 */
	public BBuildingConstant findOneBBuildingConstant(Integer buildingId) {
		return this.bBuildingConstantDao.loadBy("buildingId", buildingId, DBSource.CFG);
	}
	
	/**
	 * 查询一截建筑常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BBuildingConstant> findBBuildingConstantPageList(Integer currentPage, Integer pageSize) {
		bBuildingConstantDao.closeSession(DBSource.CFG);
		return bBuildingConstantDao.findPage("from BBuildingConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 查询建筑常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<BBuildingConstant> findaBBuildingConstantList() {
		bBuildingConstantDao.closeSession(DBSource.CFG);
		return bBuildingConstantDao.findAll();
	}
	
	/**
	 * 查询所有建筑常量的ID和name对应的map
	 * @return
	 */
	public Map<Integer, String> findBuildingIDNameMap() {
		Map<Integer, String> buildingIDNameMap = new LinkedHashMap<Integer, String>();
		List<BBuildingConstant> buildingList = this.findaBBuildingConstantList();
		for (BBuildingConstant buildingConstant : buildingList) {
			buildingIDNameMap.put(buildingConstant.getBuildingId(), buildingConstant.getBuildingName());
		}
		return buildingIDNameMap;
	}
	
	public BBuildingConstantDao getbBuildingConstantDao() {
		return bBuildingConstantDao;
	}

	public void setbBuildingConstantDao(BBuildingConstantDao bBuildingConstantDao) {
		this.bBuildingConstantDao = bBuildingConstantDao;
	}
}