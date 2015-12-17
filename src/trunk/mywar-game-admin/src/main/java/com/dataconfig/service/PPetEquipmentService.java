package com.dataconfig.service;

import java.util.List;
import com.dataconfig.bo.PPetEquipment;
import com.dataconfig.dao.PPetEquipmentDao;
import com.framework.common.DBSource;

public class PPetEquipmentService {
	
	private PPetEquipmentDao pPetEquipmentDao;

	/**
	 * @return the pPetEquipmentDao
	 */
	public PPetEquipmentDao getpPetEquipmentDao() {
		return pPetEquipmentDao;
	}

	/**
	 * @param pPetEquipmentDao the pPetEquipmentDao to set
	 */
	public void setpPetEquipmentDao(PPetEquipmentDao pPetEquipmentDao) {
		this.pPetEquipmentDao = pPetEquipmentDao;
	}
	
	/**
	 * 添加宠物装备加成常量
	 * @param petEquipment
	 */
	public void addPPetEquipment(PPetEquipment petEquipment) {
		pPetEquipmentDao.save(petEquipment, DBSource.CFG);
	}
	
	/**
	 * 删除宠物装备加成常量
	 * @param petEquipmentId
	 */
	public void delPPetEquipment(Integer petEquipmentId) {
		pPetEquipmentDao.remove(this.findOnePPetEquipment(petEquipmentId), DBSource.CFG);
	}
	
	/**
	 * 更新宠物装备常量
	 * @param petEquipmentId
	 */
	public void updatePPetEquipment(Integer petEquipmentId) {
		pPetEquipmentDao.update(this.findOnePPetEquipment(petEquipmentId), DBSource.CFG);
	}
	
	/**
	 * 查询宠物额外加成列表
	 * @return
	 */
	public List<PPetEquipment> findPPetEquipmentList() {
		pPetEquipmentDao.closeSession(DBSource.CFG);
		return pPetEquipmentDao.findAll();
	}
	
	/**
	 * 查找宠物装备加成常量
	 * @param petEquipmentId
	 * @return
	 */
	public PPetEquipment findOnePPetEquipment(Integer petEquipmentId) {
		return this.pPetEquipmentDao.loadBy("petEquipmentId", petEquipmentId, DBSource.CFG);
	}
	
}
