package com.adminTool.service;

import java.util.ArrayList;

import com.adminTool.bo.ValentineHero;
import com.adminTool.dao.ValentineHeroDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class ValentineHeroService {
private ValentineHeroDao valentineHeroDao;

public ValentineHeroDao getValentineHeroDao() {
	return valentineHeroDao;
}

public void setValentineHeroDao(ValentineHeroDao valentineHeroDao) {
	this.valentineHeroDao = valentineHeroDao;
}
public IPage<ValentineHero>findValentineHeroPageList(int toPage,
		int defaultPagesize){
	valentineHeroDao.closeSession(DBSource.CFG);
	IPage<ValentineHero>list=valentineHeroDao.findPage("from ValentineHero", new ArrayList<Object>(), defaultPagesize,
			toPage);
	return list;
}
public void add(Integer heroIds1, Integer heroIds2){
	StringBuilder sb = new StringBuilder();
	sb.append("insert into system_valentine_match values(");
	sb.append(heroIds1+","+heroIds2+")");
	this.valentineHeroDao.closeSession(DBSource.CFG);
	valentineHeroDao.executeSQL_(sb.toString());
}
public void delete(Integer heroIds1, Integer heroIds2){
	this.valentineHeroDao.closeSession(DBSource.CFG);
	valentineHeroDao.executeSQL_("delete from system_valentine_match where hero_id1="+heroIds1+" and hero_id2="+heroIds2);
}
}
