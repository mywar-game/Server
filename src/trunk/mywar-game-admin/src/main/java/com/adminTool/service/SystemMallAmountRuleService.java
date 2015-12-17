package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemMallAmountRule;
import com.adminTool.dao.SystemMallAmountRuleDao;
import com.framework.common.DBSource;

public class SystemMallAmountRuleService {

	private SystemMallAmountRuleDao systemMallAmountRuleDao;

	public List<SystemMallAmountRule> getListByMallId(int mallId) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM SystemMallAmountRule where mallId = ");
		sb.append(mallId);
		sb.append(" order by times asc");
		return systemMallAmountRuleDao.find(sb.toString(), DBSource.CFG);
		
	}
	
	public void deleteById(int id) {
		systemMallAmountRuleDao.closeSession(DBSource.CFG);
		systemMallAmountRuleDao.executeSQL_("delete from system_mall_amount_rule where id = " + id);
	}
	
	public void deleteByMallId(int mallId) {
		systemMallAmountRuleDao.closeSession(DBSource.CFG);
		systemMallAmountRuleDao.executeSQL_("delete from system_mall_amount_rule where mall_id = " + mallId);
	}
	
	public void save(List<SystemMallAmountRule> entities) {
		systemMallAmountRuleDao.saveBatch(entities, DBSource.CFG);
	}
	
	public SystemMallAmountRuleDao getSystemMallAmountRuleDao() {
		return systemMallAmountRuleDao;
	}

	public void setSystemMallAmountRuleDao(
			SystemMallAmountRuleDao systemMallAmountRuleDao) {
		this.systemMallAmountRuleDao = systemMallAmountRuleDao;
	}
}
