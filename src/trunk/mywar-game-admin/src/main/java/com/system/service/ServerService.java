package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.IPage;
import com.system.bo.Server;
import com.system.dao.ServerDao;

/**
 * 
 * @author yezp
 */
public class ServerService {

	private ServerDao serverDao;

	/**
	 * 查询服务器列表
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<Server> findServerPageList(int toPage, int defaultPagesize, Integer dbSourceId) {
		serverDao.closeSession(dbSourceId);
		IPage<Server> serverList = serverDao.findPage("from Server",
				new ArrayList<Object>(), defaultPagesize, toPage);

		return serverList;
	}

	/**
	 * 根据数据源获取服务器列表
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<Server> getServerList(Integer dbSourceId) {
		return serverDao
				.find("from Server order by partenerId asc", dbSourceId);
	}

	/**
	 * 添加服务器
	 * 
	 * @param server
	 */
	public void addServer(Server server, Integer dbSourceId) {
		serverDao.save(server, dbSourceId);
	}

	/**
	 * 更新服务器
	 * 
	 * @param server
	 * @param dbSourceId
	 */
	public void updateServer(Server server, Server old, Integer dbSourceId) {
		serverDao.closeSession(dbSourceId);
		String sql = "update Server set serverId = '" + server.getServerId()
				+ "', serverName = '" + server.getServerName() + "', status = "
				+ server.getStatus() + ", partenerId = '"
				+ server.getPartenerId() + "', openTime = '"
				+ server.getOpenTime() + "', aliaServerId = '"
				+ server.getAliaServerId() + "' where serverId = '"
				+ old.getServerId() + "' and partenerId = '"
				+ old.getPartenerId() + "'";

		serverDao.execute(sql);
	}

	/**
	 * 删除服务器
	 * 
	 * @param serverId
	 * @param partnerId
	 */
	public void delServer(String serverId, String partnerId,
			String aliaServerId, Integer dbSourceId) {
		serverDao.remove(
				findOneServer(serverId, partnerId, aliaServerId, dbSourceId),
				dbSourceId);
	}

	/**
	 * 根据条件查找服务器
	 * 
	 * @param serverId
	 * @param partnerId
	 * @return
	 */
	public Server findOneServer(String serverId, String partnerId,
			String aliaServerId, Integer dbSourceId) {
		String sql = "from Server where serverId = '" + serverId
				+ "' and partenerId = '" + partnerId + "'";
		List<Server> serverList = serverDao.find(sql, dbSourceId);
		if (serverList.size() > 0) {
			return serverList.get(0);
		}

		return null;
	}

	public ServerDao getServerDao() {
		return serverDao;
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

}
