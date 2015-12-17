package com.log.service;

import java.util.ArrayList;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.bo.GuildLog;
import com.log.dao.GuildLogDao;

public class GuildLogService {
	private GuildLogDao guildLogDao;

	public GuildLogDao getGuildLogDao() {
		return guildLogDao;
	}

	public void setGuildLogDao(GuildLogDao guildLogDao) {
		this.guildLogDao = guildLogDao;
	}
	
	/**
	 * 获取日志列表
	 * @param guildId
	 * @param name
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<GuildLog> getGuildLogPageList(Long guildId, String name, String searchOperation, Integer currentPage, Integer pageSize) {
		
		StringBuffer sql = new StringBuffer("from GuildLog where 1 = 1");
	
		if (guildId != null) {
			sql.append(" and guildId = ").append(guildId);
		}
		if (name != null && !("").equals(name)) {
			sql.append(" and guildName = '").append(name).append("'");
		}
		if (searchOperation != null && !("").equals(searchOperation)) {
			sql.append(" and operation = '").append(searchOperation).append("'");
		}
		sql.append(" order by createTime DESC");
		guildLogDao.closeSession(DBSource.LOG);
		return guildLogDao.findPage(sql.toString(), new ArrayList<GuildLog>(), pageSize, currentPage);
	}

}
