package com.dataconfig.service;

import java.util.List;
import com.dataconfig.bo.PPetExtra;
import com.dataconfig.dao.PPetExtraDao;
import com.framework.common.DBSource;

public class PPetExtraService {
	
	private PPetExtraDao pPetExtraDao;

	/**
	 * @return the pPetExtraDao
	 */
	public PPetExtraDao getpPetExtraDao() {
		return pPetExtraDao;
	}

	/**
	 * @param pPetExtraDao the pPetExtraDao to set
	 */
	public void setpPetExtraDao(PPetExtraDao pPetExtraDao) {
		this.pPetExtraDao = pPetExtraDao;
	}
	
	/**
	 * 添加宠物额外加成常量
	 * @param petExtra
	 */
	public void addPPetExtra(PPetExtra petExtra) {
		pPetExtraDao.save(petExtra, DBSource.CFG);
	}
	
	/**
	 * 删除宠物额外加成常量
	 * @param petExtraId
	 */
	public void delPPetExtra(int type) {
		pPetExtraDao.remove(this.findOnePPetExtra(type), DBSource.CFG);
	}
	
	/**
	 * 查询宠物额外加成常量
	 * @param treasureId
	 * @return
	 */
	public PPetExtra findOnePPetExtra(Integer type) {
		return this.pPetExtraDao.loadBy("type", type, DBSource.CFG);
	}
	
	
	/**
	 * 修改宠物额外加成常量
	 * @param tTreasureConstant
	 */
	public void updateOnePetExtra(PPetExtra petExtra) {
		pPetExtraDao.update(petExtra, DBSource.CFG);
	}
	
	/**
	 * 查询宠物额外加成加成列表
	 * @return
	 */
	public List<PPetExtra> findPPetExtraList() {
		pPetExtraDao.closeSession(DBSource.CFG);
		return pPetExtraDao.findAll();
	}

}
