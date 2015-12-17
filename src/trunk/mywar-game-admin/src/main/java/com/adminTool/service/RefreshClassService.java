package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.RefreshClass;
import com.adminTool.dao.RefreshClassDao;
import com.framework.common.DBSource;

public class RefreshClassService {

	RefreshClassDao dao;

	
	public List<RefreshClass> findAll() {
		dao.closeSession(DBSource.ADMIN);
		
		return dao.findAll();
	}
	
	public List<RefreshClass> findById(int id) {
		return dao.find("from RefreshClass where id = '" + id + "'", DBSource.ADMIN);
	}
	
	public RefreshClassDao getDao() {
		return dao;
	}

	public void setDao(RefreshClassDao dao) {
		this.dao = dao;
	}
}
