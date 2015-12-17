package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.IPage;
import com.system.bo.SpecialNotice;
import com.system.dao.SpecialNoticeDao;

/**
 * 登录通知Service
 * 
 * @author lin
 */
public class SpecialNoticeService {

	private SpecialNoticeDao specialNoticeDao;

	/**
	 * 分页查找
	 * 
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<SpecialNotice> findSpecialNoticePageList(Integer dbSourceId, int toPage,
			int defaultPagesize) {
		specialNoticeDao.closeSession(dbSourceId);
		IPage<SpecialNotice> specialNotices = specialNoticeDao.findPage("from SpecialNotice",
				new ArrayList<Object>(), defaultPagesize, toPage);
		return specialNotices;
	}

	/**
	 * 获取登录通知列表
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<SpecialNotice> getSpecialNoticeList(Integer dbSourceId) {
		return specialNoticeDao.find("from SpecialNotice", dbSourceId);
	}

	/**
	 * 删除登录通知信息
	 * 
	 * @param serverId
	 * @param dbSourceId
	 */
	public void delSpecialNotice(String serverId, String partnerId, Integer dbSourceId) {
		SpecialNotice specialNotice = getSpecialNoticeByServerIdAndPartnerId(serverId, partnerId, dbSourceId);
		specialNoticeDao.remove(specialNotice, dbSourceId);
	}

	/**
	 * 获取登录通知
	 * 
	 * @param serverId
	 * @param partnerId
	 * @param dbSourceId
	 * @return
	 */
	// 通过服务器ID 与 渠道ID 获取 SpecialNotice
	public SpecialNotice getSpecialNoticeByServerIdAndPartnerId(String serverId, String partnerId, Integer dbSourceId) {
		return specialNoticeDao.loadByTwo("serverId", serverId, "partnerId", partnerId, dbSourceId);
	}

	/**
	 * 添加登录通知
	 */
	public void addSpecialNotice(SpecialNotice specialNotice, Integer dbSourceId) {
		specialNoticeDao.save(specialNotice, dbSourceId);
	}

	/**
	 * 修改登录通知
	 * 
	 * @param amendNotice
	 * @param dbSourceId
	 */
	public void updateSpecialNotice(SpecialNotice specialNotice, Integer dbSourceId) {
		specialNoticeDao.update(specialNotice, dbSourceId);
	}

	public SpecialNoticeDao getSpecialNoticeDao() {
		return specialNoticeDao;
	}

	public void setSpecialNoticeDao(SpecialNoticeDao specialNoticeDao) {
		this.specialNoticeDao = specialNoticeDao;
	}

}
