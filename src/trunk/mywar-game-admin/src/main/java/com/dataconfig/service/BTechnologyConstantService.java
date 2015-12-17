package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.BTechnologyConstant;
import com.dataconfig.dao.BTechnologyConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

/**
 * @author hzy
 * 2012-7-21
 */
public class BTechnologyConstantService {
	
	/**  */
	private BTechnologyConstantDao bTechnologyConstantDao;
	
	/**
	 * 新增科技常量数据
	 * @param bTechnologyConstant 要添加的科技对象
	 */
	public void addBTechnologyConstant(BTechnologyConstant bTechnologyConstant) {
		bTechnologyConstantDao.save(bTechnologyConstant, DBSource.CFG);
	}
	
	/**
	 * 删除科技常量数据
	 * @param technologyConstantId 要删除的科技常量id
	 */
	public void delBTechnologyConstant(Integer technologyConstantId) {
		bTechnologyConstantDao.remove(findOneBTechnologyConstant(technologyConstantId), DBSource.CFG);
	}
	
	/**
	 * 修改科技常量数据
	 * @param bTechnologyConstant 要修改的科技对象
	 */
	public void updateOneBTechnologyConstant(BTechnologyConstant bTechnologyConstant) {
		bTechnologyConstantDao.update(bTechnologyConstant, DBSource.CFG);
	}

	/**
	 * 查询科技常量数据
	 * @param technologyConstantId 科技id
	 * @return 科技obj
	 */
	public BTechnologyConstant findOneBTechnologyConstant(Integer technologyConstantId) {
		return this.bTechnologyConstantDao.loadBy("technologyConstantId", technologyConstantId, DBSource.CFG);
	}
	
	/**
	 * 查询科技常量列表
	 * @param currentPage 
	 * @param pageSize 
	 * @return 列表
	 */
	public IPage<BTechnologyConstant> findBTechnologyConstantList(Integer currentPage, Integer pageSize) {
		bTechnologyConstantDao.closeSession(DBSource.CFG);
		return bTechnologyConstantDao.findPage("from BTechnologyConstant", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 查询科技id和科技名称map
	 * @return map
	 */
	public Map<Integer, String> findTechnologyIdAndNameMap() {
		Map<Integer, String> technologyIdAndNameMap = new HashMap<Integer, String>();
		bTechnologyConstantDao.closeSession(DBSource.CFG);
		List<Object> list = bTechnologyConstantDao.findSQL_("SELECT TECHNOLOGY_ID, TECHNOLOGY_NAME FROM b_technology_constant GROUP BY TECHNOLOGY_ID");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int technologyId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String name = ((Object[]) list.get(i))[1].toString();
				technologyIdAndNameMap.put(technologyId, name);
			}
		}
		return technologyIdAndNameMap;
	}

	/**
	 * @return the bTechnologyConstantDao
	 */
	public BTechnologyConstantDao getbTechnologyConstantDao() {
		return bTechnologyConstantDao;
	}

	/**
	 * @param bTechnologyConstantDao the bTechnologyConstantDao to set
	 */
	public void setbTechnologyConstantDao(
			BTechnologyConstantDao bTechnologyConstantDao) {
		this.bTechnologyConstantDao = bTechnologyConstantDao;
	}
}