package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BDummy;
import com.dataconfig.dao.BDummyDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class BDummyService {
	
	private BDummyDao bDummyDao;
	
	/**
	 * 新增训练假人常量数据
	 * @param bDummy
	 */
	public void addBDummy(BDummy bDummy) {
		bDummyDao.save(bDummy, DBSource.CFG);
	}
	
	/**
	 * 删除训练假人常量数据
	 * @param dummyId
	 */
	public void delBDummy(Integer dummyId) {
		bDummyDao.remove(findOneBDummy(dummyId), DBSource.CFG);
	}
	
	/**
	 * 修改训练假人常量数据
	 * @param bDummy
	 */
	public void updateOneBDummy(BDummy bDummy) {
		bDummyDao.update(bDummy, DBSource.CFG);
	}

	/**
	 * 查询训练假人常量数据
	 * @param dummyId
	 * @return
	 */
	public BDummy findOneBDummy(Integer dummyId) {
		return this.bDummyDao.loadBy("dummyId", dummyId, DBSource.CFG);
	}
	
	/**
	 * 查询训练假人常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BDummy> findBDummyList(Integer currentPage, Integer pageSize) {
		bDummyDao.closeSession(DBSource.CFG);
		return bDummyDao.findPage("from BDummy", new ArrayList<Object>(), pageSize, currentPage);
	}

	public BDummyDao getbDummyDao() {
		return bDummyDao;
	}

	public void setbDummyDao(BDummyDao bDummyDao) {
		this.bDummyDao = bDummyDao;
	}
}