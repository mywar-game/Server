package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.EEquipmentExt;
import com.dataconfig.dao.EquipmentExtDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class EquipmentExtService {
	
	private EquipmentExtDao equipmentExtDao;

	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<EEquipmentExt> findPageList(Integer currentPage, Integer pageSize) {
		equipmentExtDao.closeSession(DBSource.CFG);
		return equipmentExtDao.findPage("from EEquipmentExt", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public void setEquipmentExtDao(EquipmentExtDao equipmentExtDao) {
		this.equipmentExtDao = equipmentExtDao;
	}

	public EquipmentExtDao getEquipmentExtDao() {
		return equipmentExtDao;
	}

}
