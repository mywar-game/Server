package com.dataconfig.service;

import java.util.ArrayList;
import java.util.List;

import com.dataconfig.bo.SSensitive;
import com.dataconfig.dao.SensitiveDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class SensitiveService {
	
	private SensitiveDao sensitiveDao;
	
	public SensitiveDao getSensitiveDao() {
		return sensitiveDao;
	}

	public void setSensitiveDao(SensitiveDao sensitiveDao) {
		this.sensitiveDao = sensitiveDao;
	}

	/**
	 * 保存新增敏感词
	 * @param sensitive
	 */
	public void saveSensitiveWords(SSensitive sensitive) {
		sensitiveDao.save(sensitive, DBSource.CFG);
	}
	
	/**
	 * 删除敏感词
	 * @param sensitive
	 */
	public void deleteSensitiveWords(SSensitive sensitive) {
		sensitiveDao.remove(sensitive, DBSource.CFG);
	}
	
	/**
	 * 删除敏感词
	 */
	public IPage<SSensitive> findSensitiveWords(Integer currentPage, Integer pageSize) {
		sensitiveDao.closeSession(DBSource.CFG);
		IPage<SSensitive> sensitiveList = sensitiveDao.findPage("from SSensitive", new ArrayList<Object>(), pageSize, currentPage);
		return sensitiveList;
	}
	
	/**
	 * 查询敏感词
	 */
	public List<SSensitive> findSensitiveWords() {
		sensitiveDao.closeSession(DBSource.CFG);
		List<SSensitive> sensitiveList = sensitiveDao.find("from SSensitive", new ArrayList<Object>());
		return sensitiveList;
	}
}
