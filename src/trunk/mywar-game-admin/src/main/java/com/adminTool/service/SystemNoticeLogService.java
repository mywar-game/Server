package com.adminTool.service;

import java.util.ArrayList;

import com.adminTool.bo.SystemNoticeLog;
import com.adminTool.dao.SystemNoticeLogDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class SystemNoticeLogService {

	private SystemNoticeLogDao systemNoticeLogDao;

	
	public SystemNoticeLogDao getSystemNoticeLogDao() {
		return systemNoticeLogDao;
	}


	public void setSystemNoticeLogDao(SystemNoticeLogDao systemNoticeLogDao) {
		this.systemNoticeLogDao = systemNoticeLogDao;
	}

	/**
	 * 获取公告分页
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<SystemNoticeLog> findAllSystemNoticeLog(int currentPage, int pageSize) {
		systemNoticeLogDao.closeSession(DBSource.CFG);
		return systemNoticeLogDao.findPage("from SystemNoticeLog", new ArrayList<SystemNoticeLog>(), pageSize, currentPage);
	}
	
	/**
	 * 删除公告
	 * @param noticeId
	 */
	public void delSystemNoticeLog(int noticeId) {
		systemNoticeLogDao.remove(getSystemNoticeLog(noticeId), DBSource.CFG);
	}
	
	/**
	 * 获取公告
	 * @param noticeId
	 * @return
	 */
	public SystemNoticeLog getSystemNoticeLog(int noticeId) {
		return this.systemNoticeLogDao.loadBy("noticeLogId", noticeId, DBSource.CFG);
	}
	
	/**
	 * 新增公告
	 * @param systemNoticeLog
	 */
	public void updateSystemNoticeLog(SystemNoticeLog systemNoticeLog) {
		systemNoticeLogDao.update(systemNoticeLog, DBSource.CFG);
	}
	
	/***
	 * 新增公告
	 * @param systemNoticeLog
	 */
	public void addSystemNoticeLog(SystemNoticeLog systemNoticeLog) {
		systemNoticeLogDao.save(systemNoticeLog, DBSource.CFG);
	}
	
}


