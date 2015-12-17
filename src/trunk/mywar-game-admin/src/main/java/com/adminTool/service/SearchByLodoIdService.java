package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SearchByLodoIdList;
import com.adminTool.dao.SearchByLodoIdDao;
import com.framework.common.DBSource;

public class SearchByLodoIdService {
private SearchByLodoIdDao searchByLodoIdDao;

public SearchByLodoIdDao getSearchByLodoIdDao() {
	return searchByLodoIdDao;
}

public void setSearchByLodoIdDao(SearchByLodoIdDao searchByLodoIdDao) {
	this.searchByLodoIdDao = searchByLodoIdDao;
}
public List<Object> searchbyLodoId(int lodo_id){
	this.searchByLodoIdDao.closeSession(DBSource.CFG);
	List<Object>list=searchByLodoIdDao.findSQL_("select a.lodo_id,b.user_mapper_id,b.partner_id,b.server_id,b.partner_user_id from user a,user_mapper b where a.user_id=b.user_id and a.lodo_id="+lodo_id);
	return list;
}
public List<Object>searchByLodoId2(int lodo_id){
	this.searchByLodoIdDao.closeSession(DBSource.CFG);
	List<Object>list=searchByLodoIdDao.findNewSQL_("select a.user_id from user a,user_mapper b where a.user_id=b.user_id and a.lodo_id="+lodo_id);
	return list;
}
public List<Object> searchLodoId(){
	this.searchByLodoIdDao.closeSession(DBSource.CFG);
	List<Object>list=searchByLodoIdDao.findSQL_("select lodo_id from user where 1=1");
	return list;
}
}
