package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemMallVipTimesRule;
import com.adminTool.dao.SystemMallVipTimesRuleDao;
import com.framework.common.DBSource;

public class UpdateSystemMallVipTimesRuleService {

	private SystemMallVipTimesRuleDao systemMallVipTimesRuleDao;

	public SystemMallVipTimesRuleDao getSystemMallVipTimesRuleDao() {
		return systemMallVipTimesRuleDao;
	}

	public void setSystemMallVipTimesRuleDao(
			SystemMallVipTimesRuleDao systemMallVipTimesRuleDao) {
		this.systemMallVipTimesRuleDao = systemMallVipTimesRuleDao;
	}
	
	public List<SystemMallVipTimesRule> getListByMallId(int mallId) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM SystemMallVipTimesRule where mallId = ");
		sb.append(mallId);
		sb.append(" order by vipLevel asc");
		return systemMallVipTimesRuleDao.find(sb.toString(), DBSource.CFG);
		
	}
	
	public void deleteById(int id) {
		systemMallVipTimesRuleDao.closeSession(DBSource.CFG);
		systemMallVipTimesRuleDao.executeSQL_("delete from system_mall_vip_times_rule where id = " + id);
	}
	
	public void deleteByMallId(int mallId) {
		systemMallVipTimesRuleDao.closeSession(DBSource.CFG);
		systemMallVipTimesRuleDao.executeSQL_("delete from system_mall_vip_times_rule where mall_id = " + mallId);
	}
	
	public void save(List<SystemMallVipTimesRule> list) {
		systemMallVipTimesRuleDao.saveBatch(list, DBSource.CFG);
	}
	
}
