package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.IPage;
import com.system.bo.AmendNotice;
import com.system.dao.AmendNoticeDao;

/**
 * 渠道通知Service
 * 
 * @author lin
 */
public class AmendNoticeService {

	private AmendNoticeDao amendNoticeDao;

	/**
	 * 分页查找
	 * 
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<AmendNotice> findAmendNoticePageList(Integer dbSourceId, int toPage,
			int defaultPagesize) {
		amendNoticeDao.closeSession(dbSourceId);
		IPage<AmendNotice> amendNotices = amendNoticeDao.findPage("from AmendNotice",
				new ArrayList<Object>(), defaultPagesize, toPage);
		return amendNotices;
	}

	/**
	 * 获取渠道通知列表
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<AmendNotice> getAmendNoticeList(Integer dbSourceId) {
		return amendNoticeDao.find("from AmendNotice", dbSourceId);
	}

	/**
	 * 删除渠道通知信息
	 * 
	 * @param serverId
	 * @param dbSourceId
	 */
	public void delAmendNotice(String serverId, String partnerId, Integer dbSourceId) {
		AmendNotice amendNotice = getAmendNoticeByServerIdAndPartnerId(serverId, partnerId, dbSourceId);
		amendNoticeDao.remove(amendNotice, dbSourceId);
	}


	// 通过服务器ID 与 渠道ID 获取 AmendNotice
	public AmendNotice getAmendNoticeByServerIdAndPartnerId(String serverId,String partnerId,Integer dbSourceId){
		return amendNoticeDao.loadByTwo("serverId", serverId, "partnerId", partnerId, dbSourceId);
	}
	
	/**
	 * 添加渠道通知
	 */
	public void addAmendNotice(AmendNotice amendNotice, Integer dbSourceId) {
		amendNoticeDao.save(amendNotice, dbSourceId);
	}

	/**
	 * 修改渠道通知
	 * 
	 * @param amendNotice
	 * @param dbSourceId
	 */
	public void updateAmendNotice(AmendNotice amendNotice, Integer dbSourceId) {
		amendNoticeDao.update(amendNotice, dbSourceId);
	}

	public AmendNoticeDao getAmendNoticeDao() {
		return amendNoticeDao;
	}

	public void setAmendNoticeDao(AmendNoticeDao amendNoticeDao) {
		this.amendNoticeDao = amendNoticeDao;
	}

}
