package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.PetUnlock;
import com.dataconfig.dao.PetUnlockDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class PetUnlockService {

	private PetUnlockDao petUnlockDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<PetUnlock> findPageList(Integer currentPage, Integer pageSize) {
		petUnlockDao.closeSession(DBSource.CFG);
		return petUnlockDao.findPage("from PetUnlock", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setPetUnlockDao(PetUnlockDao petUnlockDao) {
		this.petUnlockDao = petUnlockDao;
	}

	public PetUnlockDao getPetUnlockDao() {
		return petUnlockDao;
	}
	
}
