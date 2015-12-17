package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.GiftbagDropTool;
import com.adminTool.dao.UpdateDropToolDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class UpdateDropToolService {

	UpdateDropToolDao updateDropToolDao;
	
	public UpdateDropToolDao getUpdateDropToolDao() {
		return updateDropToolDao;
	}

	public void setUpdateDropToolDao(UpdateDropToolDao updateDropToolDao) {
		this.updateDropToolDao = updateDropToolDao;
	}

	public void deleteByDropId(int giftbagDropToolId) {
		updateDropToolDao.closeSession(DBSource.CFG);
		updateDropToolDao.execute("delete GiftbagDropTool where giftbagDropToolId = "
				+ giftbagDropToolId);
	}
	
	public List<GiftbagDropTool> getList() {
		updateDropToolDao.closeSession(DBSource.CFG);
		return updateDropToolDao.find("from GiftbagDropTool where giftbagId = 401 or giftbagId = 402 or giftbagId = 403 or giftbagId = 404 or giftbagId = 405 or giftbagId = 406 or giftbagId = 407", DBSource.CFG);
	}
	
	public void save(GiftbagDropTool tool) {
		updateDropToolDao.save(tool, DBSource.CFG);
	}
	
	public void update(GiftbagDropTool tool) {
		updateDropToolDao.update(tool, DBSource.CFG);
	}
	
	public List<GiftbagDropTool> findAll() {
		updateDropToolDao.closeSession(DBSource.CFG);
		return updateDropToolDao.findAll();
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("在线奖励 同步删除-giftbag_drop_tool开始");
		updateDropToolDao.closeSession(DBSource.CFG);
		updateDropToolDao.executeSQL_("delete from giftbag_drop_tool where 1=1");
		LogSystem.info("在线奖励 同步删除-giftbag_drop_tool结束");
	}
	
	public void synSaveAll(List<GiftbagDropTool> list) {
		LogSystem.info("在线奖励 同步保存-giftbag_drop_tool开始");
		updateDropToolDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("在线奖励 同步保存-giftbag_drop_tool结束");
	}
}
