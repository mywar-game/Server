package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.IPage;
import com.system.bo.Notice;
import com.system.dao.NoticeDao;

/**
 * 通知Service
 * 
 * @author yezp
 */
public class NoticeService {

	private NoticeDao noticeDao;

	/**
	 * 分页查找
	 * 
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<Notice> findNoticePageList(Integer dbSourceId, int toPage,
			int defaultPagesize) {
		noticeDao.closeSession(dbSourceId);
		IPage<Notice> notices = noticeDao.findPage("from Notice",
				new ArrayList<Object>(), defaultPagesize, toPage);
		return notices;
	}

	/**
	 * 获取通知列表
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<Notice> getNoticeList(Integer dbSourceId) {
		return noticeDao.find("from Notice", dbSourceId);
	}

	/**
	 * 删除通知信息
	 * 
	 * @param serverId
	 * @param dbSourceId
	 */
	public void delNotice(String serverId, Integer dbSourceId) {
		Notice notice = getNoticeByServerId(serverId, dbSourceId);
		noticeDao.remove(notice, dbSourceId);
	}

	/**
	 * 获取通知
	 * 
	 * @param serverId
	 * @param dbSourceId
	 * @return
	 */
	public Notice getNoticeByServerId(String serverId, Integer dbSourceId) {
		return noticeDao.loadBy("serverId", serverId, dbSourceId);
	}

	/**
	 * 添加通知
	 */
	public void addNotice(Notice notice, Integer dbSourceId) {
		noticeDao.save(notice, dbSourceId);
	}

	/**
	 * 修改通知
	 * 
	 * @param notice
	 * @param dbSourceId
	 */
	public void updateNotice(Notice notice, Integer dbSourceId) {
		noticeDao.update(notice, dbSourceId);
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

}
