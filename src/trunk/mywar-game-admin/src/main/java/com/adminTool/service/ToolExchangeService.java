package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ToolExchange;
import com.adminTool.dao.ToolExchangeDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

/**
 * 道具兑换Service
 * 
 * @author yezp
 */
public class ToolExchangeService {

	private ToolExchangeDao toolExchangeDao;

	/**
	 * 根据活动id获取道具兑换
	 * 
	 * @param activityId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<ToolExchange> findToolExchangePageList(int activityId,
			int toPage, int defaultPagesize) {
		toolExchangeDao.closeSession(DBSource.CFG);
		IPage<ToolExchange> list = toolExchangeDao.findPage(
				"from ToolExchange where activityId =" + activityId,
				new ArrayList<Object>(), defaultPagesize, toPage);

		return list;
	}

	/**
	 * 根据活动id删除道具兑换
	 * 
	 * @param activityId
	 */
	public void delToolExchangeByActivityId(int activityId) {
		toolExchangeDao.closeSession(DBSource.CFG);
		toolExchangeDao.execute("delete ToolExchange where activityId = "
				+ activityId);
	}
	
	public List<ToolExchange> findAll(Integer activityId) {
		/*toolExchangeDao.closeSession(DBSource.CFG);*/
		return toolExchangeDao.find("from ToolExchange where activityId = " + activityId, DBSource.CFG);
	}

	/**
	 * 添加道具兑换配置
	 * 
	 * @param toolExchange
	 */
	public void addToolExchange(ToolExchange toolExchange) {
		toolExchangeDao.save(toolExchange, DBSource.CFG);
	}

	public ToolExchangeDao getToolExchangeDao() {
		return toolExchangeDao;
	}

	public void setToolExchangeDao(ToolExchangeDao toolExchangeDao) {
		this.toolExchangeDao = toolExchangeDao;
	}

	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("道具兑换 同步删除-system_tool_exchange开始");
		toolExchangeDao.closeSession(DBSource.CFG);
		toolExchangeDao.executeSQL_("delete from system_tool_exchange where activity_id = " + activityId);
		LogSystem.info("道具兑换 同步删除-system_tool_exchange结束");
	}
	
	public void synSaveAll(List<ToolExchange> list) {
		LogSystem.info("道具兑换 同步保存-system_tool_exchange开始");
		toolExchangeDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("道具兑换 同步保存-system_tool_exchange结束");
	}
}
