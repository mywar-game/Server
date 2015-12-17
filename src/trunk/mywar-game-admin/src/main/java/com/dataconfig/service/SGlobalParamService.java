package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.SGlobalParam;
import com.dataconfig.dao.SGlobalParamDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class SGlobalParamService {
	
	private SGlobalParamDao sGlobalParamDao;
	
	public SGlobalParamDao getsGlobalParamDao() {
		return sGlobalParamDao;
	}

	public void setsGlobalParamDao(SGlobalParamDao sGlobalParamDao) {
		this.sGlobalParamDao = sGlobalParamDao;
	}

	/**
	 * 修改某个国际化文件
	 * @param sGlobalParam
	 */
	public void updateOnesGlobalParam(SGlobalParam sGlobalParam) {
		sGlobalParamDao.update(sGlobalParam, DBSource.CFG);
	}

	/**
	 * 查询国际化文件
	 * @param sGlobalParam
	 */
	public IPage<SGlobalParam> findGlobalParamList(int toPage, int i) {
		sGlobalParamDao.closeSession(DBSource.CFG);
		return sGlobalParamDao.findPage("from SGlobalParam", new ArrayList<Object>(), i, toPage);
	}

	public SGlobalParam findOneSGlobalParam(int key) {
		SGlobalParam  sglobalParam = sGlobalParamDao.loadBy("globalKey", key, DBSource.CFG);
		return sglobalParam;
	}

}
