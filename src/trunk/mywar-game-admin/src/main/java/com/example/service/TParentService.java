package com.example.service;

import com.example.bo.TParent;
import com.example.dao.TParentDao;

public class TParentService {
	private TParentDao parentDao;
	public TParentDao getParentDao() {
		return parentDao;
	}
	public void setParentDao(TParentDao parentDao) {
		this.parentDao = parentDao;
	}
	
	public void AddParent(TParent p) {
		parentDao.save(p, p.getId());
	}
	public TParent getParent(int id) {
		return parentDao.load(id, null);
	}
}
