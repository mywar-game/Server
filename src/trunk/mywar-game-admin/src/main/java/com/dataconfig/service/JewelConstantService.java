package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.JJewelConstant;
import com.dataconfig.dao.JewelConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class JewelConstantService {
	
	private JewelConstantDao jewelConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<JJewelConstant> findPageList(Integer currentPage, Integer pageSize) {
		jewelConstantDao.closeSession(DBSource.CFG);
		return jewelConstantDao.findPage("from JJewelConstant", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 *  查询所有常量的ID和name对应的map
	 * @return
	 */
	public Map<Integer, Integer> findJewjelIdLevelMap() {
		
		Map<Integer, Integer> jewjelIdLevelMap = new HashMap<Integer, Integer>();
		jewelConstantDao.closeSession(DBSource.CFG);
		List<Object> list = jewelConstantDao.findSQL_("SELECT JEWJEL_ID, LEVEL FROM j_jewjel_constant");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int jewjelId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				int level = Integer.parseInt(((Object[]) list.get(i))[1].toString());
				jewjelIdLevelMap.put(jewjelId, level);
			}
		}
		return jewjelIdLevelMap;
	}

	public void setJewelConstantDao(JewelConstantDao jewelConstantDao) {
		this.jewelConstantDao = jewelConstantDao;
	}

	public JewelConstantDao getJewelConstantDao() {
		return jewelConstantDao;
	}

}
