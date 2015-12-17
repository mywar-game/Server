package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BBuildingconstraintConstant;
import com.dataconfig.dao.BBuildingconstraintConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class BBuildingconstraintConstantService {
	
	private BBuildingconstraintConstantDao bBuildingconstraintConstantDao;
	
	/**
	 * 新增建筑约束常量数据
	 * @param bBuildingconstraintConstant
	 */
	public void addBBuildingconstraintConstant(BBuildingconstraintConstant bBuildingconstraintConstant) {
		bBuildingconstraintConstantDao.save(bBuildingconstraintConstant, DBSource.CFG);
	}
	
	/**
	 * 删除建筑约束常量数据
	 * @param buildingConstraintId
	 */
	public void delBBuildingconstraintConstant(Integer buildingConstraintId) {
		bBuildingconstraintConstantDao.remove(findOneBBuildingconstraintConstant(buildingConstraintId), DBSource.CFG);
	}
	
	/**
	 * 修改建筑约束常量数据
	 * @param bBuildingconstraintConstant
	 */
	public void updateOneBBuildingconstraintConstant(BBuildingconstraintConstant bBuildingconstraintConstant) {
		bBuildingconstraintConstantDao.update(bBuildingconstraintConstant, DBSource.CFG);
	}

	/**
	 * 查询建筑约束常量数据
	 * @param buildingConstraintId
	 * @return
	 */
	public BBuildingconstraintConstant findOneBBuildingconstraintConstant(Integer buildingConstraintId) {
		return this.bBuildingconstraintConstantDao.loadBy("buildingConstraintId", buildingConstraintId, DBSource.CFG);
	}
	
	/**
	 * 查询建筑约束常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BBuildingconstraintConstant> findBBuildingconstraintConstantList(Integer currentPage, Integer pageSize) {
		bBuildingconstraintConstantDao.closeSession(DBSource.CFG);
		return bBuildingconstraintConstantDao.findPage("from BBuildingconstraintConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public BBuildingconstraintConstantDao getbBuildingconstraintConstantDao() {
		return bBuildingconstraintConstantDao;
	}

	public void setbBuildingconstraintConstantDao(BBuildingconstraintConstantDao bBuildingconstraintConstantDao) {
		this.bBuildingconstraintConstantDao = bBuildingconstraintConstantDao;
	}
}