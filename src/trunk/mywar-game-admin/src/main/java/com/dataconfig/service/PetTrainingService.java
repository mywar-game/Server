package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.PetTraining;
import com.dataconfig.dao.PetTrainingDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class PetTrainingService {

	private PetTrainingDao petTrainingDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<PetTraining> findPageList(Integer currentPage, Integer pageSize) {
		petTrainingDao.closeSession(DBSource.CFG);
		return petTrainingDao.findPage("from PetTraining", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setPetTrainingDao(PetTrainingDao petTrainingDao) {
		this.petTrainingDao = petTrainingDao;
	}

	public PetTrainingDao getPetTrainingDao() {
		return petTrainingDao;
	}
	
}
