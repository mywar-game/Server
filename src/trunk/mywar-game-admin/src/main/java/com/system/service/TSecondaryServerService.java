package com.system.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.system.bo.TSecondaryServer;
import com.system.dao.TSecondaryServerDao;

public class TSecondaryServerService {

	private TSecondaryServerDao tSecondaryServerDao;
	
	/**
	 * 新增服务器系统数据
	 * @param TSecondaryServer
	 */
	public void addTSecondaryServer(TSecondaryServer secondaryServer) {
		tSecondaryServerDao.save(secondaryServer, DBSource.ADMIN);
	}
	
	/**
	 * 删除服务器系统数据
	 * @param key
	 */
	public void delTSecondaryServer(Integer serverId) {
		tSecondaryServerDao.remove(findOneTSecondaryServer(serverId), DBSource.ADMIN);
	}
	
	/**
	 * 修改服务器系统数据
	 * @param TSecondaryServer
	 */
	public void updateOneTSecondaryServer(TSecondaryServer secondaryServer) {
		tSecondaryServerDao.update(secondaryServer, DBSource.ADMIN);
	}
	
	/**
	 * 查询服务器系统数据
	 * @param key
	 * @return
	 */
	public TSecondaryServer findOneTSecondaryServer(Integer serverId) {
		return this.tSecondaryServerDao.loadBy("serverId", serverId, DBSource.ADMIN);
	}
	
	/**
	 * 查询服务器系统分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<TSecondaryServer> findTSecondaryServerPageList(Integer currentPage, Integer pageSize) {
		tSecondaryServerDao.closeSession(DBSource.ADMIN);
		return tSecondaryServerDao.findPage("from TSecondaryServer", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 各种类型的最后一个服务器的编号
	 * @return
	 */
	public Map<Integer, Integer> findTypeAndLastServerIdMap(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SERVER_TYPE,MAX(SERVER_ID) FROM t_secondary_server GROUP BY SERVER_TYPE");
		tSecondaryServerDao.closeSession(DBSource.ADMIN);
		List<Object> list = tSecondaryServerDao.findSQL_(sql.toString());
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int serverType = Integer.valueOf(((Object[]) list.get(i))[0].toString());
				int serverId = Integer.valueOf(((Object[]) list.get(i))[1].toString());
				map.put(serverType, serverId);
			}
		}
		return map;
	}

	public void settSecondaryServerDao(TSecondaryServerDao tSecondaryServerDao) {
		this.tSecondaryServerDao = tSecondaryServerDao;
	}

	public TSecondaryServerDao gettSecondaryServerDao() {
		return tSecondaryServerDao;
	}
	
	/**
	 * 查询服务器列表信息
	 * @return
	 */
	public List<Object> findAllServerList() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SERVER_ID,SERVER_TYPE,SERVER_NAME,server_ip FROM t_secondary_server ");
		tSecondaryServerDao.closeSession(DBSource.ADMIN);
		List<Object> list = tSecondaryServerDao.findSQL_(sql.toString());
		return list;
	}

	/**
	 * 查询服务器列表信息
	 * @return
	 */
	public List<Object> findAllServerListNameAndID() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SERVER_ID,SERVER_NAME,server_ip FROM t_secondary_server ");
		tSecondaryServerDao.closeSession(DBSource.ADMIN);
		List<Object> list = tSecondaryServerDao.findSQL_(sql.toString());
		return list;
	}
	
	/**
	 * 查询服务器列表信息
	 * @return
	 */
	public List<Object> findAllServer() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SERVER_ID,server_ip,SERVER_PORT,SERVER_TYPE FROM t_secondary_server ");
		tSecondaryServerDao.closeSession(DBSource.ADMIN);
		List<Object> list = tSecondaryServerDao.findSQL_(sql.toString());
		return list;
	}

}
