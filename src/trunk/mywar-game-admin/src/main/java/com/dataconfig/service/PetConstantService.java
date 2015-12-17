package com.dataconfig.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.PPetConstant;
import com.dataconfig.dao.PetConstantDao;
import com.framework.common.DBSource;

public class PetConstantService {

	private PetConstantDao petConstantDao;
	
	/**
	 * 新增日常活动数据
	 * @param sParamConfig
	 */
	public void addPetConstant(PPetConstant petConstant) {
		petConstantDao.save(petConstant, DBSource.CFG);
	}
	
	/**
	 * 删除日常活动数据
	 * @param key
	 */
	public void delPetConstant(Integer petId) {
		petConstantDao.remove(this.findOnePetConstant(petId), DBSource.CFG);
	}
	
	/**
	 * 修改日常活动数据
	 * @param sParamConfig
	 */
	public void updateOnePetConstant(PPetConstant petConstant) {
		petConstantDao.update(petConstant, DBSource.CFG);
	}

	/**查询日常活动列表
	 * @return
	 */
	public List<PPetConstant> findPetConstantList() {
		petConstantDao.closeSession(DBSource.CFG);
		return petConstantDao.findAll();
	}
	
	/**
	 * 查询日常活动列数据
	 * @param key
	 * @return
	 */
	public PPetConstant findOnePetConstant(Integer petId) {
		return this.petConstantDao.loadBy("petId", petId, DBSource.CFG);
	}

	/**
	 * 查询宠物id和宠物名称map
	 * @return
	 */
	public Map<Integer, String> findPetIdNameMap() {
		Map<Integer, String> petIdNameMap = new LinkedHashMap<Integer, String>();
		petConstantDao.closeSession(DBSource.CFG);
		List<Object> list = petConstantDao.findSQL_("select PET_ID, NAME from p_pet_constant");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int petId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String name = ((Object[]) list.get(i))[1].toString();
				petIdNameMap.put(petId, name);
			}
		}
		return petIdNameMap;
	}
	
	/**
	 * @return the petConstantDao
	 */
	public PetConstantDao getPetConstantDao() {
		return petConstantDao;
	}

	/**
	 * @param petConstantDao the petConstantDao to set
	 */
	public void setPetConstantDao(PetConstantDao petConstantDao) {
		this.petConstantDao = petConstantDao;
	}
}
