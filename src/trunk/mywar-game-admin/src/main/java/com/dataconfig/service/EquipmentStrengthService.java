package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.EEquipmentStrength;
import com.dataconfig.dao.EquipmentStrengthDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class EquipmentStrengthService {
	
	private EquipmentStrengthDao equipmentStrengthDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<EEquipmentStrength> findPageList(Integer currentPage, Integer pageSize) {
		equipmentStrengthDao.closeSession(DBSource.CFG);
		return equipmentStrengthDao.findPage("from EEquipmentStrength", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setEquipmentStrengthDao(EquipmentStrengthDao equipmentStrengthDao) {
		this.equipmentStrengthDao = equipmentStrengthDao;
	}

	public EquipmentStrengthDao getEquipmentStrengthDao() {
		return equipmentStrengthDao;
	}

}
