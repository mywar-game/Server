package com.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.opensymphony.xwork2.ActionSupport;
import com.system.bo.TDbServer;
import com.system.dao.TDbServerDao;

public class TDbServerService {
	
	private TDbServerDao tDbServerDao;
	
	/**
	 * 新增数据服务器
	 * @param TDbServer
	 */
	public void addTDbServer(TDbServer dbServer) {
		tDbServerDao.save(dbServer, DBSource.ADMIN);
	}
	
	/**
	 * 删除数据服务器
	 * @param key
	 */
	public void delTDbServer(Integer dbServerId) {
		tDbServerDao.remove(findOneTDbServer(dbServerId), DBSource.ADMIN);
	}
	
	/**
	 * 修改数据服务器
	 * @param TDbServer
	 */
	public void updateOneTDbServer(TDbServer dbServer) {
		tDbServerDao.update(dbServer, DBSource.ADMIN);
	}
	
	/**
	 * 查询数据服务器
	 * @param key
	 * @return
	 */
	public TDbServer findOneTDbServer(Integer dbServerId) {
		return this.tDbServerDao.loadBy("dbServerId", dbServerId, DBSource.ADMIN);
	}
	
	/**
	 * 查询数据服务器分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<TDbServer> findTDbServerPageList(Integer currentPage, Integer pageSize) {
		tDbServerDao.closeSession(DBSource.ADMIN);
		return tDbServerDao.findPage("from TDbServer", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 各种类型的最后一个服务器的编号
	 * @return
	 */
	public Map<Integer, Integer> findTypeAndLastSuffixMap(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT b.SERVER_TYPE, b.SERVER_NAME FROM ( SELECT MAX(DB_SERVER_ID) AS lastId FROM t_db_server GROUP BY SERVER_TYPE ) a LEFT JOIN t_db_server b ON a.lastId = b.DB_SERVER_ID");
		tDbServerDao.closeSession(DBSource.ADMIN);
		List<Object> list = tDbServerDao.findSQL_(sql.toString());
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		
		if (list != null && list.size() > 0) {
			ActionSupport actionSupport = new ActionSupport();
			for (int i = 0; i < list.size(); i++) {
				int serverType = Integer.valueOf(((Object[]) list.get(i))[0].toString());
				String serverName = ((Object[]) list.get(i))[1].toString();
				String prefix = actionSupport.getText("dbServer.serverName_"+serverType+"_namePrefix");
				map.put(serverType, Integer.valueOf(serverName.split(prefix)[1]));
			}
		}
		return map;
	}
	
	/**
	 *  根据条件查到的ID和一些信息对应的map
	 * @return
	 */
	public Map<Integer, String> findDbSeverIdAndInfoMapByCondition(Integer searchType, String searchCondition) {
		Map<Integer, String> dbServerIdNameMap = new LinkedHashMap<Integer, String>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DB_SERVER_ID,SERVER_NAME,SERVER_IP,DB_NAME FROM t_db_server WHERE SERVER_TYPE = ").append(searchType).append(" AND (SERVER_NAME LIKE '%");
		sb.append(searchCondition);
		sb.append("%'");
		sb.append(" or SERVER_IP LIKE '%").append(searchCondition).append("%')");
		tDbServerDao.closeSession(DBSource.ADMIN);
		List<Object> list = tDbServerDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			Integer dbServerId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			String name = ((Object[]) list.get(i))[1].toString();
			String serverIp = ((Object[]) list.get(i))[2].toString();
			String dbName = ((Object[]) list.get(i))[3].toString();
			dbServerIdNameMap.put(dbServerId,name+"("+serverIp+" "+dbName+")");
		}
		return dbServerIdNameMap;
	}
	
	public Map<Integer, TDbServer> findIdAndDbServerMapInIds(String ids){
		Map<Integer, TDbServer> map = new HashMap<Integer, TDbServer>();
		if (Tools.isEmpty(ids)) {
			return map;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM t_db_server WHERE DB_SERVER_ID IN (").append(ids).append(")");
		List<TDbServer> list = tDbServerDao.find("from TDbServer where dbServerId in ("+ids+")",DBSource.ADMIN);
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getDbServerId(), list.get(i));
		}
		return map;
	}
	
	/**
	 * 根据类型查询
	 * @param serverType
	 * @return
	 */
	public Map<Integer, TDbServer> findDbServerMapByServerType(int serverType){
		Map<Integer, TDbServer> map = new HashMap<Integer, TDbServer>();
		List<TDbServer> list = tDbServerDao.find("from TDbServer where SERVER_TYPE = "+serverType+"",DBSource.ADMIN);
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getDbServerId(), list.get(i));
		}
		return map;
	}
	
	/**
	 * 查询全部信息
	 * @param serverType
	 * @return
	 */
	public Map<Integer, TDbServer> findDbServerMap(){
		Map<Integer, TDbServer> map = new HashMap<Integer, TDbServer>();
		List<TDbServer> list = tDbServerDao.find("from TDbServer ",DBSource.ADMIN);
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getDbServerId(), list.get(i));
		}
		return map;
	}
	
	public TDbServerDao gettDbServerDao() {
		return tDbServerDao;
	}
	
	public void settDbServerDao(TDbServerDao tDbServerDao) {
		this.tDbServerDao = tDbServerDao;
	}

	/**
	 * 查询
	 */
	public List<Object> findAllDBServer() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DB_SERVER_ID ,SERVER_TYPE,server_Name,server_ip,db_name FROM t_db_server ");
		tDbServerDao.closeSession(DBSource.ADMIN);
		List<Object> list = tDbServerDao.findSQL_(sql.toString());
		return list;
	} 
	

	/**
	 * 查询
	 */
	public List<Object> findAllDBNameAndIDServer() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DB_SERVER_ID ,server_Name,server_ip,db_name FROM t_db_server ");
		tDbServerDao.closeSession(DBSource.ADMIN);
		List<Object> list = tDbServerDao.findSQL_(sql.toString());
		return list;
	} 


}
